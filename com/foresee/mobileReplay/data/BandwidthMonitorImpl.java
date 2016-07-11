package com.foresee.mobileReplay.data;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.os.Process;
import android.util.Log;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class BandwidthMonitorImpl implements BandwidthMonitor {
    private static final String DATA_USAGE_KEY = "data_usage";
    private ConnectivityManager connectivityManager;
    private Application context;
    private float dataUsageCap;
    long txBytesAtStart;
    int uid;

    @Inject
    public BandwidthMonitorImpl(Application application, @Named("DATA_USAGE_CAP") float f) {
        this.uid = -1;
        this.txBytesAtStart = 0;
        this.dataUsageCap = 20.0f;
        this.context = application;
        this.connectivityManager = (ConnectivityManager) application.getSystemService("connectivity");
        if (f <= 0.0f) {
            f = 20.0f;
        }
        this.dataUsageCap = f;
    }

    public void startMobileDataLogging() {
        NetworkInfo activeNetworkInfo = this.connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || activeNetworkInfo.getType() == 0) {
            this.uid = Process.myUid();
            this.txBytesAtStart = TrafficStats.getUidTxBytes(this.uid);
            Log.d("FORESEE_DATA_CAPS", String.format("Bandwidth monitoring started: %d bytes", new Object[]{Long.valueOf(this.txBytesAtStart)}));
        }
    }

    public void stopMobileDataLogging() {
        if (this.uid >= 0) {
            if (Process.myUid() != this.uid) {
                Log.w("FORESEE_DATA_CAPS", "Mobile data logging process mismatch");
                return;
            }
            Log.d("FORESEE_DATA_CAPS", String.format("Bandwidth monitoring ended: %d bytes (%d bytes delta)", new Object[]{Long.valueOf(TrafficStats.getUidTxBytes(this.uid)), Long.valueOf(TrafficStats.getUidTxBytes(this.uid) - this.txBytesAtStart)}));
            addDataUsage(r2);
            this.uid = -1;
        }
    }

    private void addDataUsage(long j) {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences("sessionGroupData.json", 4);
        Log.d("FORESEE_DATA_CAPS", String.format("Current data usage: %.2fMB", new Object[]{Float.valueOf((float) (((double) (sharedPreferences.getLong(DATA_USAGE_KEY, 0) + j)) / 1048576.0d))}));
        Editor edit = sharedPreferences.edit();
        edit.putLong(DATA_USAGE_KEY, r2);
        edit.commit();
    }

    private long retrieveDataUsage() {
        return this.context.getSharedPreferences("sessionGroupData.json", 4).getLong(DATA_USAGE_KEY, 0);
    }

    public boolean willExceedMobileDataLimit(long j) {
        NetworkInfo activeNetworkInfo = this.connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || activeNetworkInfo.getType() == 0) {
            if (this.uid < 0) {
                startMobileDataLogging();
            }
            boolean z = ((double) (((retrieveDataUsage() + TrafficStats.getUidTxBytes(this.uid)) - this.txBytesAtStart) + j)) / 1048576.0d > ((double) this.dataUsageCap);
            if (z) {
                Log.d("FORESEE_DATA_CAPS", String.format("Mobile data usage cap will be exceeded (%.2f + %.2f = %.2f/%.2fMB)", new Object[]{Float.valueOf((float) (((double) r2) / 1048576.0d)), Float.valueOf((float) (((double) j) / 1048576.0d)), Float.valueOf((float) (((double) r4) / 1048576.0d)), Float.valueOf(this.dataUsageCap)}));
                return z;
            }
            Log.d("FORESEE_DATA_CAPS", String.format("Mobile data usage cap will not be exceeded (%.2f + %.2f = %.2f/%.2fMB)", new Object[]{Float.valueOf((float) (((double) r2) / 1048576.0d)), Float.valueOf((float) (((double) j) / 1048576.0d)), Float.valueOf((float) (((double) r4) / 1048576.0d)), Float.valueOf(this.dataUsageCap)}));
            return z;
        }
        Log.d("FORESEE_DATA_CAPS", "Mobile network not in use; ignoring mobile data limits");
        return false;
    }
}

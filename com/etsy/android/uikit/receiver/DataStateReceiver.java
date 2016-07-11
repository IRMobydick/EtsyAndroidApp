package com.etsy.android.uikit.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import com.etsy.android.lib.logger.EtsyDebug;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataStateReceiver extends BroadcastReceiver {
    private static final int MESSAGE_DATA_CONNECTED = 1;
    private static final int MESSAGE_DATA_DISCONNECTED = 2;
    private static final String mTAG = "DataStateReceiver";
    private long mFailureThreshold;
    private boolean mIsDataConnected;
    private long mLastStateChange;
    private final List<DataStateReceiver> mListeners;
    protected Handler mMessageHandler;
    private NetworkInfo mNetworkInfo;
    private int mNetworkType;
    private long mSuccessThreshold;

    /* renamed from: com.etsy.android.uikit.receiver.DataStateReceiver.1 */
    class C09401 extends Handler {
        final /* synthetic */ DataStateReceiver f4004a;

        C09401(DataStateReceiver dataStateReceiver) {
            this.f4004a = dataStateReceiver;
        }

        public final void handleMessage(Message message) {
            if (message.what == DataStateReceiver.MESSAGE_DATA_CONNECTED) {
                this.f4004a.notifyObservers(true);
            } else if (message.what == DataStateReceiver.MESSAGE_DATA_DISCONNECTED) {
                this.f4004a.notifyObservers(false);
            }
        }
    }

    public DataStateReceiver(boolean z) {
        this(z, 0, 0);
    }

    public DataStateReceiver(boolean z, long j, long j2) {
        this.mIsDataConnected = false;
        this.mLastStateChange = 0;
        this.mFailureThreshold = 0;
        this.mSuccessThreshold = 0;
        this.mNetworkInfo = null;
        this.mNetworkType = -1;
        this.mListeners = Collections.synchronizedList(new ArrayList());
        this.mMessageHandler = new C09401(this);
        this.mIsDataConnected = z;
        this.mFailureThreshold = j;
        this.mSuccessThreshold = j2;
    }

    public boolean addListener(DataStateReceiver dataStateReceiver) {
        return this.mListeners.add(dataStateReceiver);
    }

    public boolean removeListener(DataStateReceiver dataStateReceiver) {
        return this.mListeners.remove(dataStateReceiver);
    }

    private void notifyObservers(boolean z) {
        for (DataStateReceiver a : this.mListeners) {
            a.m1129a(z);
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            if (intent.getBooleanExtra("noConnectivity", false)) {
                if (this.mIsDataConnected) {
                    this.mLastStateChange = System.currentTimeMillis();
                }
                this.mIsDataConnected = false;
                EtsyDebug.m1912c(mTAG, "Data Connection Disconnected");
            } else {
                if (!this.mIsDataConnected) {
                    this.mLastStateChange = System.currentTimeMillis();
                }
                this.mIsDataConnected = true;
                EtsyDebug.m1912c(mTAG, "Data Connection Connected");
            }
            updateNetworkInfo(context);
            notifyObserversDelayed(this.mIsDataConnected);
        }
    }

    private void updateNetworkInfo(Context context) {
        this.mNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (this.mNetworkInfo != null) {
            this.mNetworkType = this.mNetworkInfo.getType();
        } else {
            this.mNetworkType = -1;
        }
    }

    public int getActiveNetworkType() {
        return this.mNetworkType;
    }

    public synchronized boolean isDataConnected() {
        boolean z;
        if (this.mLastStateChange > 0) {
            if (!this.mIsDataConnected && System.currentTimeMillis() < this.mLastStateChange + this.mFailureThreshold) {
                z = true;
            } else if (this.mIsDataConnected && System.currentTimeMillis() < this.mLastStateChange + this.mSuccessThreshold) {
                z = false;
            }
        }
        z = this.mIsDataConnected;
        return z;
    }

    protected void notifyObserversDelayed(boolean z) {
        this.mMessageHandler.removeMessages(MESSAGE_DATA_CONNECTED);
        this.mMessageHandler.removeMessages(MESSAGE_DATA_DISCONNECTED);
        if (z) {
            this.mMessageHandler.sendMessageDelayed(this.mMessageHandler.obtainMessage(MESSAGE_DATA_CONNECTED), this.mSuccessThreshold);
        } else {
            this.mMessageHandler.sendMessageDelayed(this.mMessageHandler.obtainMessage(MESSAGE_DATA_DISCONNECTED), this.mFailureThreshold);
        }
    }
}

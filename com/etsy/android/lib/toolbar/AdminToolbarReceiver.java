package com.etsy.android.lib.toolbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.SensorManager;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.util.ShakeDetector;
import com.etsy.android.lib.util.bb;

public abstract class AdminToolbarReceiver extends BroadcastReceiver implements bb {
    protected FragmentActivity mContext;
    private ShakeDetector mDetector;

    protected abstract void onDeviceShakenDetected();

    protected abstract void onReceivedScreenshotAction();

    public AdminToolbarReceiver(FragmentActivity fragmentActivity) {
        this.mContext = fragmentActivity;
        this.mDetector = new ShakeDetector(this);
    }

    public void register() {
        SensorManager sensorManager = (SensorManager) this.mContext.getSystemService("sensor");
        if (sensorManager != null) {
            this.mDetector.start(sensorManager);
        }
        this.mContext.registerReceiver(this, new IntentFilter("com.etsy.toolbar.TAKE_SCREENSHOT"));
    }

    public void unregister() {
        this.mContext.unregisterReceiver(this);
        this.mDetector.stop();
    }

    public void onReceive(Context context, Intent intent) {
        onReceivedScreenshotAction();
    }

    public void hearShake() {
        onDeviceShakenDetected();
    }
}

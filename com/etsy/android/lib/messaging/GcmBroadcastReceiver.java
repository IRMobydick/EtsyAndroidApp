package com.etsy.android.lib.messaging;

import android.content.Context;
import android.content.Intent;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.gcm.GcmReceiver;

public abstract class GcmBroadcastReceiver extends GcmReceiver {
    private static final String TAG;

    protected abstract NotificationIntentDelegate getIntentDelegate();

    static {
        TAG = EtsyDebug.m1891a(GcmBroadcastReceiver.class);
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            EtsyDebug.m1912c(TAG, "onReceive: intent: " + intent.toString());
            if (!"etsynotify/delete".equals(intent.getStringExtra(ResponseConstants.FROM))) {
                setResultCode(-1);
            }
            super.onReceive(context, intent);
        }
    }
}

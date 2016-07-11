package com.etsy.android.messaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.uikit.util.AdHocEventCompatBuilder;
import com.foresee.sdk.events.LifecycleEvent;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

public class ForeSeeBroadcastReceiver extends BroadcastReceiver {
    public static final String FORESEE_ACTION = "com.foresee.sdk.lifecycleEvent";
    protected static final String TAG;

    static {
        TAG = EtsyDebug.m1891a(ForeSeeBroadcastReceiver.class);
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String string = extras.getString(LifecycleEvent.EXTRA_EVENT_TYPE);
                if (string != null) {
                    Object obj = -1;
                    switch (string.hashCode()) {
                        case -879733596:
                            if (string.equals("INVITE_DECLINED")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 64921139:
                            if (string.equals("DEBUG")) {
                                obj = 4;
                                break;
                            }
                            break;
                        case 553626520:
                            if (string.equals("SESSION_STARTED")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 700512285:
                            if (string.equals("INVITE_ACCEPTED")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 1436874948:
                            if (string.equals("INVITE_PRESENTED")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case Task.NETWORK_STATE_CONNECTED /*0*/:
                            trackEvent("foresee_invite_shown");
                            EtsyDebug.m1912c(TAG, "receiver: invite shown");
                            return;
                        case Task.NETWORK_STATE_UNMETERED /*1*/:
                            trackEvent("foresee_invite_declined");
                            EtsyDebug.m1912c(TAG, "receiver: invite declined");
                            return;
                        case Task.NETWORK_STATE_ANY /*2*/:
                            trackEvent("foresee_invite_accepted");
                            EtsyDebug.m1912c(TAG, "receiver: invite accepted");
                            return;
                        case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                            EtsyDebug.m1912c(TAG, "receiver: framework init success");
                            break;
                        case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                            break;
                        default:
                            return;
                    }
                    EtsyDebug.m1912c(TAG, "receiver: ForeSee still active");
                }
            }
        }
    }

    private void trackEvent(@NonNull String str) {
        new AdHocEventCompatBuilder(str).m5515a("home").m5517a();
    }
}

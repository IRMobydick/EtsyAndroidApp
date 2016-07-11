package com.etsy.android.lib.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.etsy.android.lib.logger.EtsyDebug;

public class InstallStateReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        EtsyApplication etsyApplication = (EtsyApplication) context.getApplicationContext();
        if (etsyApplication.isLaunchedForUI()) {
            EtsyDebug.m1906b("InstallStateReceiver", intent.getDataString());
            etsyApplication.updateInstallStates(intent.getAction(), intent.getDataString());
        }
    }
}

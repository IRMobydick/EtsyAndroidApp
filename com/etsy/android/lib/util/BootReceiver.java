package com.etsy.android.lib.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent("com.etsy.android.soe.START_HEADSET_MONITOR"));
    }
}

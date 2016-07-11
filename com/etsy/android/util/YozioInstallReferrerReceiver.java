package com.etsy.android.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.etsy.android.lib.util.AttributionUtil;

public class YozioInstallReferrerReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        AttributionUtil.m3384a(context, intent);
    }
}

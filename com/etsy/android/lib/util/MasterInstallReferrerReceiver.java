package com.etsy.android.lib.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.yozio.android.YozioReferrerReceiver;

public class MasterInstallReferrerReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        new CampaignTrackingReceiver().onReceive(context, intent);
        new YozioReferrerReceiver().onReceive(context, intent);
    }
}

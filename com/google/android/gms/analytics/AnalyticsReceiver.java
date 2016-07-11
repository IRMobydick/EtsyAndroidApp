package com.google.android.gms.analytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.analytics.internal.C1138m;

public final class AnalyticsReceiver extends BroadcastReceiver {
    private C1138m zzTG;

    private C1138m zzkl() {
        if (this.zzTG == null) {
            this.zzTG = new C1138m();
        }
        return this.zzTG;
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public void onReceive(Context context, Intent intent) {
        zzkl().m6229a(context, intent);
    }
}

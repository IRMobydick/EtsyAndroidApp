package com.google.android.gms.analytics;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.analytics.internal.C1134o;
import com.google.android.gms.analytics.internal.C1139n;

public final class AnalyticsService extends Service implements C1134o {
    private C1139n zzTH;

    private C1139n zzkm() {
        if (this.zzTH == null) {
            this.zzTH = new C1139n(this);
        }
        return this.zzTH;
    }

    public boolean callServiceStopSelfResult(int i) {
        return stopSelfResult(i);
    }

    public Context getContext() {
        return this;
    }

    public IBinder onBind(Intent intent) {
        return zzkm().m6235a(intent);
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public void onCreate() {
        super.onCreate();
        zzkm().m6236a();
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public void onDestroy() {
        zzkm().m6237b();
        super.onDestroy();
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public int onStartCommand(Intent intent, int i, int i2) {
        return zzkm().m6234a(intent, i, i2);
    }
}

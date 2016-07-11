package com.google.android.gms.analytics.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.analytics.internal.n.1;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.tm;

/* renamed from: com.google.android.gms.analytics.internal.n */
public final class C1139n {
    private static Boolean f4689d;
    private final Handler f4690a;
    private final C1134o f4691b;
    private final Context f4692c;

    public C1139n(C1134o c1134o) {
        this.f4692c = c1134o.getContext();
        zzaa.zzz(this.f4692c);
        this.f4691b = c1134o;
        this.f4690a = new Handler();
    }

    public static boolean m6231a(Context context) {
        zzaa.zzz(context);
        if (f4689d != null) {
            return f4689d.booleanValue();
        }
        boolean a = t.a(context, "com.google.android.gms.analytics.AnalyticsService");
        f4689d = Boolean.valueOf(a);
        return a;
    }

    private void m6233c() {
        try {
            synchronized (C1138m.f4686a) {
                tm tmVar = C1138m.f4687b;
                if (tmVar != null && tmVar.b()) {
                    tmVar.a();
                }
            }
        } catch (SecurityException e) {
        }
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public int m6234a(Intent intent, int i, int i2) {
        m6233c();
        z a = z.a(this.f4692c);
        g f = a.f();
        if (intent == null) {
            f.e("AnalyticsService started with null intent");
        } else {
            String action = intent.getAction();
            if (a.e().a()) {
                f.a("Device AnalyticsService called. startId, action", Integer.valueOf(i2), action);
            } else {
                f.a("Local AnalyticsService called. startId, action", Integer.valueOf(i2), action);
            }
            if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(action)) {
                a.i().a(new 1(this, i2, a, f));
            }
        }
        return 2;
    }

    public IBinder m6235a(Intent intent) {
        return null;
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public void m6236a() {
        z a = z.a(this.f4692c);
        g f = a.f();
        if (a.e().a()) {
            f.b("Device AnalyticsService is starting up");
        } else {
            f.b("Local AnalyticsService is starting up");
        }
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public void m6237b() {
        z a = z.a(this.f4692c);
        g f = a.f();
        if (a.e().a()) {
            f.b("Device AnalyticsService is shutting down");
        } else {
            f.b("Local AnalyticsService is shutting down");
        }
    }
}

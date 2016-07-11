package com.google.android.gms.analytics.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.tm;

/* renamed from: com.google.android.gms.analytics.internal.m */
public final class C1138m {
    static Object f4686a;
    static tm f4687b;
    static Boolean f4688c;

    static {
        f4686a = new Object();
    }

    public static boolean m6228a(Context context) {
        zzaa.zzz(context);
        if (f4688c != null) {
            return f4688c.booleanValue();
        }
        boolean a = t.a(context, "com.google.android.gms.analytics.AnalyticsReceiver", false);
        f4688c = Boolean.valueOf(a);
        return a;
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public void m6229a(Context context, Intent intent) {
        z a = z.a(context);
        g f = a.f();
        if (intent == null) {
            f.e("AnalyticsReceiver called with null intent");
            return;
        }
        String action = intent.getAction();
        if (a.e().a()) {
            f.a("Device AnalyticsReceiver got", action);
        } else {
            f.a("Local AnalyticsReceiver got", action);
        }
        if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(action)) {
            boolean a2 = C1139n.m6231a(context);
            Intent intent2 = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            intent2.setComponent(new ComponentName(context, "com.google.android.gms.analytics.AnalyticsService"));
            intent2.setAction("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            synchronized (f4686a) {
                context.startService(intent2);
                if (a2) {
                    try {
                        if (f4687b == null) {
                            f4687b = new tm(context, 1, "Analytics WakeLock");
                            f4687b.a(false);
                        }
                        f4687b.a(1000);
                    } catch (SecurityException e) {
                        f.e("Analytics service at risk of not starting. For more reliable analytics, add the WAKE_LOCK permission to your manifest. See http://goo.gl/8Rd3yj for instructions.");
                    }
                    return;
                }
            }
        }
    }
}

package com.etsy.android.ui.user;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.etsy.android.ui.user.k */
public class UserBadgeCountManager {
    public static final AtomicInteger f3610a;
    public static final AtomicInteger f3611b;

    static {
        f3610a = new AtomicInteger();
        f3611b = new AtomicInteger();
    }

    public static void m5064a(int i) {
        f3610a.set(i);
    }

    public static int m5063a() {
        return f3610a.get();
    }

    public static void m5065a(Context context) {
        int max = Math.max(0, f3610a.get() - 1);
        SharedPreferencesUtility.m3129b(context, max);
        f3610a.set(max);
        UserBadgeCountManager.m5069c(context);
    }

    public static void m5067b(int i) {
        f3611b.set(i);
    }

    public static int m5066b() {
        return f3611b.get();
    }

    public static void m5068b(Context context) {
        int max = Math.max(0, f3611b.get() - 1);
        SharedPreferencesUtility.m3135c(context, max);
        f3611b.set(max);
        UserBadgeCountManager.m5069c(context);
    }

    public static void m5069c(Context context) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.etsy.android.badge.count.UPDATE"));
    }
}

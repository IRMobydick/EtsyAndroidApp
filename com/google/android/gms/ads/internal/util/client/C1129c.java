package com.google.android.gms.ads.internal.util.client;

import android.util.Log;
import com.google.android.gms.internal.jw;

@jw
/* renamed from: com.google.android.gms.ads.internal.util.client.c */
public class C1129c {
    public static void m6185a(String str) {
        if (C1129c.m6187a(3)) {
            Log.d("Ads", str);
        }
    }

    public static void m6186a(String str, Throwable th) {
        if (C1129c.m6187a(3)) {
            Log.d("Ads", str, th);
        }
    }

    public static boolean m6187a(int i) {
        return i >= 5 || Log.isLoggable("Ads", i);
    }

    public static void m6188b(String str) {
        if (C1129c.m6187a(6)) {
            Log.e("Ads", str);
        }
    }

    public static void m6189b(String str, Throwable th) {
        if (C1129c.m6187a(6)) {
            Log.e("Ads", str, th);
        }
    }

    public static void m6190c(String str) {
        if (C1129c.m6187a(4)) {
            Log.i("Ads", str);
        }
    }

    public static void m6191c(String str, Throwable th) {
        if (C1129c.m6187a(4)) {
            Log.i("Ads", str, th);
        }
    }

    public static void m6192d(String str) {
        if (C1129c.m6187a(5)) {
            Log.w("Ads", str);
        }
    }

    public static void m6193d(String str, Throwable th) {
        if (C1129c.m6187a(5)) {
            Log.w("Ads", str, th);
        }
    }
}

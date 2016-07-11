package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.concurrent.Future;

@jw
public final class lp {
    public static SharedPreferences m7057a(Context context) {
        return context.getSharedPreferences("admob", 0);
    }

    public static Future m7058a(Context context, lr lrVar) {
        return (Future) new 7(context, lrVar).zzhs();
    }

    public static Future m7059a(Context context, String str) {
        return (Future) new 11(context, str).zzhs();
    }

    public static Future m7060a(Context context, String str, long j) {
        return (Future) new 5(context, str, j).zzhs();
    }

    public static Future m7061a(Context context, boolean z) {
        return (Future) new 1(context, z).zzhs();
    }

    public static Future m7062b(Context context, lr lrVar) {
        return (Future) new 8(context, lrVar).zzhs();
    }

    public static Future m7063b(Context context, boolean z) {
        return (Future) new 9(context, z).zzhs();
    }

    public static Future m7064c(Context context, lr lrVar) {
        return (Future) new 10(context, lrVar).zzhs();
    }

    public static Future m7065c(Context context, boolean z) {
        return (Future) new 3(context, z).zzhs();
    }

    public static Future m7066d(Context context, lr lrVar) {
        return (Future) new 2(context, lrVar).zzhs();
    }

    public static Future m7067e(Context context, lr lrVar) {
        return (Future) new 4(context, lrVar).zzhs();
    }

    public static Future m7068f(Context context, lr lrVar) {
        return (Future) new 6(context, lrVar).zzhs();
    }
}

package com.etsy.android.lib.convos;

import android.content.Context;
import android.os.Bundle;

/* renamed from: com.etsy.android.lib.convos.k */
public class ConvoStateManager {
    public static long m1002a(Context context) {
        if (context != null) {
            return context.getSharedPreferences("convo_prefs", 0).getLong("last_convo", -1);
        }
        return -1;
    }

    public static void m1004a(Context context, long j) {
        if (context != null) {
            context.getSharedPreferences("convo_prefs", 0).edit().putLong("last_convo", j).apply();
        }
    }

    public static void m1005b(Context context) {
        if (context != null) {
            context.getSharedPreferences("convo_prefs", 0).edit().remove("last_convo").apply();
        }
    }

    public static long m1003a(Context context, Bundle bundle) {
        if (bundle != null) {
            return ConvoStateManager.m1002a(context);
        }
        ConvoStateManager.m1005b(context);
        return -1;
    }
}

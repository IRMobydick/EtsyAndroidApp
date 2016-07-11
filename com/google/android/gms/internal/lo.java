package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.ads.internal.util.client.C1129c;

@jw
public final class lo extends C1129c {
    public static boolean m7054a() {
        return ((Boolean) dz.as.m6433c()).booleanValue();
    }

    private static boolean m7055b() {
        return C1129c.m6187a(2) && m7054a();
    }

    public static void m7056e(String str) {
        if (m7055b()) {
            Log.v("Ads", str);
        }
    }
}

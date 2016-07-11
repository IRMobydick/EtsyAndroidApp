package com.etsy.android.lib.util;

import android.os.Build.VERSION;

/* renamed from: com.etsy.android.lib.util.a */
public class AndroidIssuesUtil {
    public static boolean m3164a() {
        return VERSION.SDK_INT < 18;
    }

    public static boolean m3165b() {
        return VERSION.SDK_INT < 19;
    }
}

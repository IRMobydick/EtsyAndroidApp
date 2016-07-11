package com.etsy.android.uikit.p018c;

import android.content.Context;

/* renamed from: com.etsy.android.uikit.c.a */
public final class SystemUiHelper {
    private static final String f3949a;

    static {
        f3949a = SystemUiHelper.class.getSimpleName();
    }

    public static int m5328a(Context context) {
        int i = context.getResources().getConfiguration().orientation;
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 0;
        }
        return -1;
    }
}

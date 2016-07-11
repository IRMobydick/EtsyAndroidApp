package com.etsy.android.lib.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.etsy.android.lib.logger.EtsyDebug;

/* compiled from: IntentLauncherUtil */
public class ah {
    private static final String f2001a;

    static {
        f2001a = EtsyDebug.m1891a(ah.class);
    }

    public static void m3223a(Activity activity, String str) {
        m3222a(activity, new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    public static void m3222a(Activity activity, Intent intent) {
        try {
            activity.startActivity(intent);
        } catch (Throwable e) {
            EtsyDebug.m1917d(f2001a, "No activity found for intent", e);
        }
    }
}

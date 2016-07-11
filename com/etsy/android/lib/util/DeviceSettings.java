package com.etsy.android.lib.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import com.etsy.android.lib.logger.EtsyDebug;

/* renamed from: com.etsy.android.lib.util.z */
public class DeviceSettings {
    private static final String f2070a;

    static {
        f2070a = EtsyDebug.m1891a(DeviceSettings.class);
    }

    @SuppressLint({"InlinedApi"})
    public static boolean m3434a(Context context) {
        if (aa.m3169c()) {
            try {
                if (Secure.getInt(context.getContentResolver(), "location_mode") != 0) {
                    return true;
                }
                return false;
            } catch (Throwable e) {
                EtsyDebug.m1917d(f2070a, "Couldn't find Settings Secure to get location status", e);
                return true;
            }
        } else if (TextUtils.isEmpty(Secure.getString(context.getContentResolver(), "location_providers_allowed"))) {
            return false;
        } else {
            return true;
        }
    }

    public static void m3433a(Activity activity) {
        activity.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
    }
}

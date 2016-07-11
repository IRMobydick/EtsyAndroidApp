package com.etsy.android.lib.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import com.etsy.android.lib.logger.EtsyDebug;
import org.apache.commons.lang3.StringUtils;

/* compiled from: InstallPersistentPrefsUtil */
public class ag {
    private static final String f2000a;

    static {
        f2000a = EtsyDebug.m1891a(ag.class);
    }

    public static String m3214a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("EtsyInstallPrefs", 0);
        String string = sharedPreferences.getString("EtsyUUID", StringUtils.EMPTY);
        if (!string.isEmpty()) {
            return string;
        }
        string = ba.m3309a();
        Editor edit = sharedPreferences.edit();
        edit.putString("EtsyUUID", string);
        edit.apply();
        return string;
    }

    public static void m3216b(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("EtsyInstallPrefs", 0);
        if (!sharedPreferences.getString("EtsyUUID", StringUtils.EMPTY).isEmpty()) {
            Editor edit = sharedPreferences.edit();
            edit.remove("EtsyUUID");
            edit.apply();
        }
    }

    public static String m3218c(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("EtsyInstallPrefs", 0);
        String string = sharedPreferences.getString("guest_id", StringUtils.EMPTY);
        if (!string.isEmpty()) {
            return string;
        }
        string = ba.m3309a();
        Editor edit = sharedPreferences.edit();
        edit.putString("guest_id", string);
        edit.apply();
        return string;
    }

    public static String m3219d(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("EtsyInstallPrefs", 0);
        String string = sharedPreferences.getString("gcm_registration_id", StringUtils.EMPTY);
        if (string.isEmpty()) {
            EtsyDebug.m1906b(f2000a, "Registration not found.");
            return StringUtils.EMPTY;
        } else if (sharedPreferences.getInt("appVersion", RtlSpacingHelper.UNDEFINED) == m3221f(context)) {
            return string;
        } else {
            EtsyDebug.m1906b(f2000a, "App version changed.");
            return StringUtils.EMPTY;
        }
    }

    public static void m3215a(Context context, String str) {
        int f = m3221f(context);
        Editor edit = context.getSharedPreferences("EtsyInstallPrefs", 0).edit();
        edit.putString("gcm_registration_id", str);
        edit.putInt("appVersion", f);
        edit.apply();
    }

    public static String m3220e(Context context) {
        String string = context.getSharedPreferences("EtsyInstallPrefs", 0).getString("gms_instance_id", StringUtils.EMPTY);
        if (!string.isEmpty()) {
            return string;
        }
        EtsyDebug.m1906b(f2000a, "Instance id not found.");
        return StringUtils.EMPTY;
    }

    public static void m3217b(Context context, String str) {
        Editor edit = context.getSharedPreferences("EtsyInstallPrefs", 0).edit();
        edit.putString("gms_instance_id", str);
        edit.apply();
    }

    private static int m3221f(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            throw new RuntimeException("Could not get package name: " + e);
        }
    }
}

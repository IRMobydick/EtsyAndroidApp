package com.etsy.android.lib.logger;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import com.etsy.android.lib.R;
import com.etsy.android.lib.util.CrashUtil;

/* renamed from: com.etsy.android.lib.logger.h */
public class EtsyDebug {
    private static String f1772a;
    private static int f1773b;
    private static boolean f1774c;
    private static boolean f1775d;

    static {
        f1772a = null;
        f1774c = false;
        f1775d = false;
    }

    private static String m1910c(String str) {
        if (f1772a == null) {
            EtsyDebug.m1895a("ETSY:");
        }
        if (str.length() > f1773b) {
            return f1772a + str.substring(0, f1773b - 1);
        }
        return f1772a + str;
    }

    public static String m1891a(Class cls) {
        return EtsyDebug.m1910c(cls.getSimpleName());
    }

    public static boolean m1903a() {
        return f1774c;
    }

    public static void m1901a(boolean z) {
        f1774c = z;
    }

    public static void m1895a(String str) {
        f1772a = str;
        f1773b = 23 - f1772a.length();
    }

    public static boolean m1909b() {
        return f1774c && f1775d;
    }

    public static void m1892a(Context context) {
        Resources resources = context.getResources();
        f1775d = context.getSharedPreferences(resources.getString(R.config_prefs_key), 0).getBoolean(resources.getString(R.config_prefs_output_json), false);
    }

    public static void m1893a(Object obj) {
        if (EtsyDebug.m1903a()) {
            System.out.println(obj);
            System.out.println();
        }
    }

    public static void m1904b(Object obj) {
        if (EtsyDebug.m1903a()) {
            EtsyDebug.m1905b(obj.toString());
        }
    }

    public static void m1911c(Object obj) {
        if (EtsyDebug.m1903a()) {
            System.err.println(obj);
        }
    }

    public static void m1900a(String str, Object... objArr) {
        if (EtsyDebug.m1903a()) {
            System.err.println(String.format(null, str, objArr));
        }
    }

    public static void m1896a(String str, String str2) {
        if (EtsyDebug.m1903a()) {
            Log.v(str, str2);
        }
    }

    public static void m1898a(String str, String str2, Object... objArr) {
        if (EtsyDebug.m1903a()) {
            Log.v(str, String.format(null, str2, objArr));
        }
    }

    public static void m1906b(String str, String str2) {
        if (EtsyDebug.m1903a()) {
            Log.i(str, str2);
        }
    }

    public static void m1897a(String str, String str2, Throwable th) {
        if (EtsyDebug.m1903a()) {
            Log.i(str, str2, th);
        }
    }

    public static void m1908b(String str, String str2, Object... objArr) {
        if (EtsyDebug.m1903a()) {
            Log.i(str, String.format(null, str2, objArr));
        }
    }

    public static void m1912c(String str, String str2) {
        if (EtsyDebug.m1903a()) {
            Log.d(str, str2);
        }
    }

    public static void m1907b(String str, String str2, Throwable th) {
        if (EtsyDebug.m1903a()) {
            Log.d(str, str2, th);
        }
    }

    public static void m1914c(String str, String str2, Object... objArr) {
        if (EtsyDebug.m1903a()) {
            Log.d(str, String.format(null, str2, objArr));
        }
    }

    public static void m1916d(String str, String str2) {
        if (EtsyDebug.m1903a()) {
            Log.w(str, str2);
        }
    }

    public static void m1899a(String str, Throwable th) {
        if (EtsyDebug.m1903a()) {
            Log.w(str, th);
        }
    }

    public static void m1918d(String str, String str2, Object... objArr) {
        if (EtsyDebug.m1903a()) {
            Log.w(str, String.format(null, str2, objArr));
        }
    }

    public static void m1913c(String str, String str2, Throwable th) {
        if (EtsyDebug.m1903a()) {
            Log.w(str, str2, th);
        }
    }

    public static void m1919e(String str, String str2) {
        if (EtsyDebug.m1903a()) {
            Log.e(str, str2);
        }
    }

    public static void m1917d(String str, String str2, Throwable th) {
        if (EtsyDebug.m1903a()) {
            Log.e(str, str2, th);
            CrashUtil.m3037a().m3045a(th);
        }
    }

    public static void m1920e(String str, String str2, Object... objArr) {
        if (EtsyDebug.m1903a()) {
            Log.e(str, String.format(null, str2, objArr));
        }
    }

    public static void m1894a(RuntimeException runtimeException) {
        if (EtsyDebug.m1903a()) {
            throw runtimeException;
        }
    }

    public static void m1902a(boolean z, RuntimeException runtimeException) {
        if (EtsyDebug.m1903a() && z) {
            throw runtimeException;
        }
    }

    protected static void m1905b(String str) {
        int i = 0;
        int i2 = 0;
        while (i2 < str.length()) {
            System.out.println(str.substring(i2, Math.min(str.length(), i2 + 4000)));
            i++;
            i2 = i * 4000;
        }
    }

    public static boolean m1915c() {
        return EtsyDebug.m1903a();
    }
}

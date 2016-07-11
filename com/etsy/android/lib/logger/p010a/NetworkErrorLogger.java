package com.etsy.android.lib.logger.p010a;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.android.volley.NetworkResponse;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.http.HttpResult;
import com.etsy.android.lib.logger.EtsyLogger;
import java.util.Locale;
import java.util.Map;

/* renamed from: com.etsy.android.lib.logger.a.c */
public class NetworkErrorLogger {
    private static String[] f1739a;
    private static int[] f1740b;

    public static void m1819a(@Nullable NetworkResponse networkResponse, String str, String str2, String str3) {
        if (networkResponse != null) {
            NetworkErrorLogger.m1818a(networkResponse.statusCode, str, str2, str3, networkResponse.headers);
        }
    }

    public static void m1820a(@NonNull HttpResult httpResult, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(Locale.US, "device_timestamp=%d", new Object[]{Long.valueOf(System.currentTimeMillis() / 1000)}));
        stringBuilder.append("HTTP ");
        stringBuilder.append(String.format("error %d: ", new Object[]{Integer.valueOf(httpResult.m1036q())}));
        stringBuilder.append(String.format("\"%s\" ", new Object[]{httpResult.toString()}));
        stringBuilder.append(String.format("error: \"%s\"", new Object[]{httpResult.m1039t()}));
        stringBuilder.append(String.format("url=%s ", new Object[]{str}));
        stringBuilder.append(String.format("headers=%s ", new Object[]{NetworkErrorLogger.m1817a(httpResult.m1041v())}));
        if (NetworkErrorLogger.m1821a(httpResult.m1036q())) {
            EtsyLogger.m1966a().m1986a("NetworkResponse", stringBuilder.toString());
        } else {
            EtsyLogger.m1966a().m1996b("NetworkResponse", stringBuilder.toString());
        }
    }

    public static void m1818a(int i, String str, String str2, String str3, Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(Locale.US, "device_timestamp=%d", new Object[]{Long.valueOf(System.currentTimeMillis() / 1000)}));
        stringBuilder.append("HTTP ");
        stringBuilder.append(String.format("error %d: ", new Object[]{Integer.valueOf(i)}));
        stringBuilder.append(String.format("\"%s\" ", new Object[]{str}));
        stringBuilder.append(String.format("owner=%s ", new Object[]{str2}));
        stringBuilder.append(String.format("url=%s ", new Object[]{str3}));
        stringBuilder.append(String.format("headers=%s ", new Object[]{NetworkErrorLogger.m1817a((Map) map)}));
        if (NetworkErrorLogger.m1821a(i)) {
            EtsyLogger.m1966a().m1986a("NetworkResponse", stringBuilder.toString());
        } else {
            EtsyLogger.m1966a().m1996b("NetworkResponse", stringBuilder.toString());
        }
    }

    private static String m1817a(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder("[");
        if (map != null) {
            String[] a = NetworkErrorLogger.m1822a();
            if (a != null) {
                int i = 0;
                for (Object containsKey : a) {
                    if (map.containsKey(containsKey)) {
                        if (i != 0) {
                            stringBuilder.append(", ");
                        }
                        stringBuilder.append(String.format("\"%s: %s\"", new Object[]{containsKey, map.get(containsKey)}));
                        i = 1;
                    }
                }
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private static boolean m1821a(int i) {
        int[] b = NetworkErrorLogger.m1823b();
        if (b == null) {
            return false;
        }
        for (int i2 : b) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    private static String[] m1822a() {
        if (f1739a == null) {
            f1739a = EtsyConfig.m837a().m869d().m889g(EtsyConfigKeys.bT);
        }
        return f1739a;
    }

    private static int[] m1823b() {
        if (f1740b == null) {
            f1740b = EtsyConfig.m837a().m869d().m890h(EtsyConfigKeys.bS);
        }
        return f1740b;
    }
}

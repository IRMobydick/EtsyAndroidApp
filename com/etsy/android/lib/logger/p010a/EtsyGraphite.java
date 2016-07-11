package com.etsy.android.lib.logger.p010a;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/* renamed from: com.etsy.android.lib.logger.a.a */
public class EtsyGraphite {
    private static Random f1735a;

    public static void m1808a(String str, double d) {
        EtsyGraphite.m1810a(str, "timer", d / 1000.0d);
    }

    public static void m1809a(String str, double d, double d2) {
        if (EtsyGraphite.m1813a(d2)) {
            EtsyGraphite.m1808a(str, d);
        }
    }

    public static EtsyGraphite m1814b(String str, double d) {
        return new EtsyGraphite(d, null);
    }

    public static void m1807a(String str) {
        EtsyGraphite.m1810a(str, "counter", 1.0d);
    }

    private static void m1810a(String str, String str2, double d) {
        String str3;
        if (EtsyApplication.get().isSOE()) {
            str3 = ".soe";
        } else {
            str3 = ".boe";
        }
        EtsyGraphite.m1811a(str, str2, "native_apps.android" + str3, d);
    }

    private static void m1811a(String str, String str2, String str3, double d) {
        if (EtsyGraphite.m1812a()) {
            DecimalFormat decimalFormat = new DecimalFormat("#");
            decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
            decimalFormat.setMaximumFractionDigits(6);
            decimalFormat.setMinimumIntegerDigits(1);
            String str4 = str2 + " " + str3 + "." + str + " " + decimalFormat.format(d) + " " + decimalFormat.format(((double) System.currentTimeMillis()) / 1000.0d);
            EtsyLogger.m1966a().m1996b("EtsyGraphite", str4);
            EtsyDebug.m1906b("EtsyGraphite", str4);
        }
    }

    public static void m1805a(@Nullable Bundle bundle, double d) {
        if (bundle != null && EtsyGraphite.m1813a(d)) {
            bundle.putLong("graphite_arg_start_time", System.nanoTime());
        }
    }

    public static void m1806a(@Nullable Bundle bundle, String str) {
        if (bundle != null && bundle.containsKey("graphite_arg_start_time")) {
            EtsyGraphite.m1808a(str, (double) TimeUnit.MILLISECONDS.convert(System.nanoTime() - bundle.getLong("graphite_arg_start_time"), TimeUnit.NANOSECONDS));
            bundle.remove("graphite_arg_start_time");
        }
    }

    static {
        f1735a = new Random();
    }

    private static boolean m1813a(double d) {
        if (d > 0.0d && f1735a.nextDouble() <= d) {
            return true;
        }
        return false;
    }

    private static boolean m1812a() {
        return EtsyGraphite.m1815b() ? EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.cu) : true;
    }

    private static boolean m1815b() {
        try {
            return EtsyConfig.m837a().m871e();
        } catch (IllegalStateException e) {
            return false;
        }
    }
}

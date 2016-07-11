package com.etsy.android.lib.logger;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.AdjustEvent;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;

/* renamed from: com.etsy.android.lib.logger.g */
public class EtsyAdjust {
    private static boolean f1771a;

    static {
        f1771a = false;
    }

    public static void m1880a() {
        if (EtsyAdjust.m1889h()) {
            Adjust.trackEvent(new AdjustEvent("onwscb"));
        }
    }

    public static void m1883b() {
        if (EtsyAdjust.m1889h()) {
            Adjust.trackEvent(new AdjustEvent("1nwyu9"));
        }
    }

    public static void m1881a(double d, String str) {
        if (EtsyAdjust.m1889h()) {
            AdjustEvent adjustEvent = new AdjustEvent("wtgguo");
            adjustEvent.setRevenue(d, str);
            Adjust.trackEvent(adjustEvent);
        }
    }

    public static void m1884c() {
        if (EtsyAdjust.m1889h()) {
            Adjust.trackEvent(new AdjustEvent("xf8j5f"));
        }
    }

    public static void m1885d() {
        if (EtsyAdjust.m1889h()) {
            Adjust.trackEvent(new AdjustEvent("3p819t"));
        }
    }

    public static void m1886e() {
        if (EtsyAdjust.m1889h()) {
            Adjust.trackEvent(new AdjustEvent("ulsgdl"));
        }
    }

    public static void m1882a(AdjustConfig adjustConfig) {
        if (EtsyAdjust.m1889h()) {
            Adjust.onCreate(adjustConfig);
            f1771a = true;
        }
    }

    public static void m1887f() {
        if (EtsyAdjust.m1889h()) {
            Adjust.onResume();
        }
    }

    public static void m1888g() {
        if (EtsyAdjust.m1889h()) {
            Adjust.onPause();
        }
    }

    private static boolean m1889h() {
        boolean c = EtsyAdjust.m1890i() ? EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.bu) : true;
        if (f1771a) {
            Adjust.setEnabled(c);
        }
        return c;
    }

    private static boolean m1890i() {
        try {
            return EtsyConfig.m837a().m871e();
        } catch (IllegalStateException e) {
            return false;
        }
    }
}

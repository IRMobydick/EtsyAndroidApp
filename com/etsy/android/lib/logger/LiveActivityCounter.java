package com.etsy.android.lib.logger;

import com.etsy.android.lib.core.posts.PostManagerKicker;
import com.etsy.android.uikit.util.AdHocEventCompatBuilder;

/* renamed from: com.etsy.android.lib.logger.t */
public class LiveActivityCounter {
    private static final String f1838a;
    private static int f1839b;

    static {
        f1838a = EtsyDebug.m1891a(LiveActivityCounter.class);
        f1839b = 0;
    }

    public static void m2050a() {
        f1839b++;
        EtsyDebug.m1914c(f1838a, "activityLives activity count = %d", Integer.valueOf(f1839b));
    }

    public static void m2051b() {
        f1839b--;
        EtsyDebug.m1914c(f1838a, "activityDies activity count = %d", Integer.valueOf(f1839b));
    }

    public static boolean m2052c() {
        return f1839b < 1;
    }

    public static void m2053d() {
        EtsyDebug.m1906b(f1838a, "runCheckAppIsBackgrounded");
        if (LiveActivityCounter.m2052c()) {
            new AdHocEventCompatBuilder("entered_background").m5515a("app").m5517a();
            EtsyDebug.m1906b(f1838a, "runCheckAppIsBackgrounded - backgrounded");
            EtsyLogger.m1966a().m1998c();
            PostManagerKicker.m1675b();
        }
    }
}

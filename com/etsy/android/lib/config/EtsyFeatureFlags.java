package com.etsy.android.lib.config;

import java.util.Locale;

/* renamed from: com.etsy.android.lib.config.o */
public class EtsyFeatureFlags {
    public static boolean m914a() {
        return EtsyConfig.m837a().m869d().m884b() || EtsyFeatureFlags.m918e();
    }

    private static boolean m916c() {
        return InstallInfo.m919a().m926c() == EtsyBuild.ALPHA;
    }

    private static boolean m917d() {
        return InstallInfo.m919a().m937n();
    }

    private static boolean m918e() {
        return EtsyFeatureFlags.m916c() || EtsyFeatureFlags.m917d();
    }

    public static boolean m915b() {
        return EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.f1219l) && Locale.getDefault().equals(Locale.US);
    }
}

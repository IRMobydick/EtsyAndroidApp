package com.etsy.android.util;

import com.etsy.android.EtsyBuildHolder;
import com.etsy.android.lib.config.EtsyBuild;
import com.etsy.android.lib.devconfigs.BuildConfigSettings;

/* renamed from: com.etsy.android.util.c */
public class EtsyBuildHelper {
    public static boolean m5706a() {
        return EtsyBuildHolder.f1073a == EtsyBuild.GOOGLE_PLAY_BETA;
    }

    public static boolean m5707b() {
        return EtsyBuildHolder.f1073a == EtsyBuild.ALPHA;
    }

    public static boolean m5708c() {
        return false;
    }

    public static boolean m5709d() {
        return EtsyBuildHelper.m5707b() || EtsyBuildHelper.m5708c();
    }

    public static BuildConfigSettings m5710e() {
        BuildConfigSettings buildConfigSettings = new BuildConfigSettings();
        buildConfigSettings.f1703b = "release/boe-4.42.0";
        buildConfigSettings.f1702a = "28 Jun 2016 AD at 17:08 EDT";
        buildConfigSettings.f1704c = "560bced60ef131af7110e0293905b41778f1df08";
        return buildConfigSettings;
    }
}

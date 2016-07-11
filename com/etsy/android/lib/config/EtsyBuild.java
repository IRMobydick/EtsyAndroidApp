package com.etsy.android.lib.config;

import java.util.HashMap;
import java.util.Map;

public enum EtsyBuild {
    UNIT_TEST("unit_test"),
    ALPHA("internal"),
    GOOGLE_PLAY("play"),
    GOOGLE_PLAY_BETA("play_beta");
    
    private static Map<String, EtsyBuild> f1147a;
    String target;

    static {
        f1147a = new HashMap();
        EtsyBuild[] values = values();
        int length = values.length;
        int i;
        while (i < length) {
            EtsyBuild etsyBuild = values[i];
            f1147a.put(etsyBuild.target, etsyBuild);
            i++;
        }
    }

    private EtsyBuild(String str) {
        this.target = str;
    }

    public static EtsyBuild fromString(String str) {
        return (EtsyBuild) f1147a.get(str);
    }
}

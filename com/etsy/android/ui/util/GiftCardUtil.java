package com.etsy.android.ui.util;

import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.models.GiftCard.Balance;
import java.util.HashMap;

/* renamed from: com.etsy.android.ui.util.x */
public class GiftCardUtil {
    private static HashMap<String, Balance> f3765a;

    public static boolean m5198a() {
        return GiftCardUtil.m5199b() && EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.f1220m);
    }

    public static boolean m5199b() {
        return EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.f1221n);
    }

    public static HashMap<String, Balance> m5200c() {
        return f3765a;
    }

    public static void m5197a(HashMap<String, Balance> hashMap) {
        f3765a = hashMap;
    }

    public static void m5201d() {
        f3765a = null;
    }
}

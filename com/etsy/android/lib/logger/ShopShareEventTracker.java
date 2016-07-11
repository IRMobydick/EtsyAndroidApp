package com.etsy.android.lib.logger;

import com.etsy.android.lib.logger.p010a.EtsyGraphite;
import com.etsy.android.lib.models.shopshare.ShareItem;
import com.etsy.android.uikit.util.AdHocEventCompatBuilder;
import java.util.HashMap;
import java.util.Map.Entry;

/* renamed from: com.etsy.android.lib.logger.x */
public class ShopShareEventTracker {
    public static HashMap<String, Object> m2088a(ShareItem shareItem) {
        HashMap<String, Object> hashMap = new HashMap();
        for (Entry entry : shareItem.getTrackingParameters().entrySet()) {
            hashMap.put(((AnalyticsLogAttribute) entry.getKey()).toString(), entry.getValue());
        }
        return hashMap;
    }

    public static void m2089a(String str, String str2) {
        ShopShareEventTracker.m2093b(str, str, str2, null);
    }

    public static void m2092b(String str, String str2) {
        ShopShareEventTracker.m2091a(str, str2, null);
    }

    public static void m2091a(String str, String str2, HashMap<String, Object> hashMap) {
        if (str2.equals("homescreen_recommended")) {
            str = String.format("homescreen.%s", new Object[]{str});
        } else if (str2.equals("shop_shares_feed")) {
            str = String.format("feed.%s", new Object[]{str});
        }
        ShopShareEventTracker.m2090a(str, str, str2, hashMap);
    }

    private static void m2090a(String str, String str2, String str3, HashMap<String, Object> hashMap) {
        EtsyLogger.m1966a().m1997b(ShopShareEventTracker.m2087a(str), str3, hashMap);
        EtsyGraphite.m1807a(String.format("shop_shares.%s", new Object[]{str2}));
    }

    private static void m2093b(String str, String str2, String str3, HashMap<String, Object> hashMap) {
        EtsyGraphite.m1807a(String.format("shop_shares.%s", new Object[]{str2}));
        new AdHocEventCompatBuilder(ShopShareEventTracker.m2087a(str)).m5515a(str3).m5516a((HashMap) hashMap).m5517a();
    }

    private static String m2087a(String str) {
        return String.format("shop_shares_%s", new Object[]{str}).replaceAll("\\.", "_");
    }
}

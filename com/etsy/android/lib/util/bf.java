package com.etsy.android.lib.util;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.messaging.EtsyRoute;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.lib.models.apiv3.ShopHomePage;
import com.etsy.android.lib.models.apiv3.ShopV3;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.uikit.ui.shop.BaseShopHomeFragment;
import com.etsy.android.uikit.ui.shop.ShopHomeInitialLoadConfiguration;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: ShopHomeUtil */
public class bf {
    @SafeVarargs
    public static List<String> m3323a(@NonNull List<? extends ListingLike>... listArr) {
        List<String> arrayList = new ArrayList();
        for (List list : listArr) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                arrayList.add(((ListingLike) list.get(i)).getListingId().getId());
            }
        }
        return arrayList;
    }

    public static HashMap<AnalyticsLogAttribute, Object> m3322a(@NonNull ShopHomePage shopHomePage) {
        Object obj;
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap();
        ShopV3 shop = shopHomePage.getShop();
        hashMap.put(AnalyticsLogAttribute.SHOP_ID, shop.getShopId());
        hashMap.put(AnalyticsLogAttribute.USER_ID, shop.getUserId());
        if (!shopHomePage.getFeaturedListings().isEmpty()) {
            hashMap.put(AnalyticsLogAttribute.FEATURED_LISTING_IDS, m3323a(r2));
        }
        if (!shopHomePage.getShopListings().isEmpty()) {
            hashMap.put(AnalyticsLogAttribute.LISTING_IDS, m3323a(r2));
        }
        List modules = shop.getModules();
        hashMap.put(AnalyticsLogAttribute.MODULE_FEATURED_ITEMS_ENABLED, Boolean.valueOf(modules.contains(ShopV3.MODULE_FEATURED_ITEMS)));
        hashMap.put(AnalyticsLogAttribute.MODULE_ABOUT_ENABLED, Boolean.valueOf(modules.contains(ShopV3.MODULE_ABOUT)));
        hashMap.put(AnalyticsLogAttribute.MODULE_LOCAL_ENABLED, Boolean.valueOf(modules.contains(ShopV3.MODULE_LOCAL)));
        hashMap.put(AnalyticsLogAttribute.PAGE_SOLD_ITEMS_ENABLED, Boolean.valueOf(modules.contains(ShopV3.PAGE_SOLD_ITEMS)));
        switch (shop.getBrandingOption()) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                obj = "new_small_banner";
                break;
            case Task.NETWORK_STATE_ANY /*2*/:
                obj = "new_large_banner";
                break;
            default:
                obj = "new_no_branding";
                break;
        }
        hashMap.put(AnalyticsLogAttribute.BRANDING_TYPE, obj);
        return hashMap;
    }

    public static HashMap<String, Object> m3327b(@NonNull ShopHomePage shopHomePage) {
        Object obj;
        HashMap<String, Object> hashMap = new HashMap();
        ShopV3 shop = shopHomePage.getShop();
        hashMap.put(ResponseConstants.SHOP_ID, shop.getShopId());
        hashMap.put(ResponseConstants.USER_ID, shop.getUserId());
        if (!shopHomePage.getFeaturedListings().isEmpty()) {
            hashMap.put("featured_listing_ids", m3323a(shopHomePage.getFeaturedListings()));
        }
        if (!shopHomePage.getShopListings().isEmpty()) {
            hashMap.put(ResponseConstants.LISTING_IDS, m3323a(r2));
        }
        List modules = shop.getModules();
        hashMap.put("module_featured_items_enabled", Boolean.valueOf(modules.contains(ShopV3.MODULE_FEATURED_ITEMS)));
        hashMap.put("module_about_enabled", Boolean.valueOf(modules.contains(ShopV3.MODULE_ABOUT)));
        hashMap.put("module_local_enabled", Boolean.valueOf(modules.contains(ShopV3.MODULE_LOCAL)));
        hashMap.put("module_policies_enabled", Boolean.valueOf(modules.contains(ShopV3.PAGE_SOLD_ITEMS)));
        switch (shop.getBrandingOption()) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                obj = "new_small_banner";
                break;
            case Task.NETWORK_STATE_ANY /*2*/:
                obj = "new_large_banner";
                break;
            default:
                obj = "new_no_branding";
                break;
        }
        hashMap.put("branding_type", obj);
        return hashMap;
    }

    public static boolean m3325a(@NonNull Intent intent, @NonNull String str) {
        return "external_url".equals(str) && intent.getData() != null && intent.getData().getHost() != null && ad.m3192c(intent.getData().getHost());
    }

    public static String m3321a(@NonNull ShopHomeInitialLoadConfiguration shopHomeInitialLoadConfiguration) {
        switch (shopHomeInitialLoadConfiguration.f4115b) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return ResponseConstants.ITEMS;
            case Task.NETWORK_STATE_ANY /*2*/:
                return ResponseConstants.ABOUT;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                return ResponseConstants.POLICIES;
            default:
                return ActivityFeedEntity.SHOP;
        }
    }

    public static void m3324a(@NonNull EtsyApiV3Request<ShopHomePage> etsyApiV3Request, @Nullable ShopHomeInitialLoadConfiguration shopHomeInitialLoadConfiguration) {
        if (shopHomeInitialLoadConfiguration != null && shopHomeInitialLoadConfiguration.f4115b == 1) {
            Map map = shopHomeInitialLoadConfiguration.f4114a;
            if (map != null && !map.isEmpty()) {
                if (map.containsKey(BaseShopHomeFragment.SEARCH_PARAM_SECTION_ID)) {
                    etsyApiV3Request.m1385a(BaseShopHomeFragment.SEARCH_PARAM_SECTION_ID, (String) map.get(BaseShopHomeFragment.SEARCH_PARAM_SECTION_ID));
                } else if (map.containsKey(BaseShopHomeFragment.SEARCH_PARAM_SEARCH_QUERY)) {
                    etsyApiV3Request.m1385a(BaseShopHomeFragment.SEARCH_PARAM_SEARCH_QUERY, (String) map.get(BaseShopHomeFragment.SEARCH_PARAM_SEARCH_QUERY));
                }
                if (map.containsKey("order")) {
                    etsyApiV3Request.m1385a(BaseShopHomeFragment.SEARCH_PARAM_SORT_ORDER, (String) map.get("order"));
                }
            }
        }
    }

    public static boolean m3326a(@NonNull EtsyRoute etsyRoute) {
        Map f = etsyRoute.m2278f();
        return f.containsKey(BaseShopHomeFragment.SEARCH_PARAM_SECTION_ID) || f.containsKey(BaseShopHomeFragment.SEARCH_PARAM_SEARCH_QUERY) || f.containsKey("order");
    }
}

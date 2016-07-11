package com.etsy.android.ui.local;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.LocalMarketCard;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.uikit.util.AdHocEventCompatBuilder;
import java.util.HashMap;

/* renamed from: com.etsy.android.ui.local.a */
public class LocalAnalytics {
    public static void m4312a(@NonNull Location location) {
        AdHocEventCompatBuilder a = new AdHocEventCompatBuilder("location_obtained").m5515a("local_browse");
        LocalAnalytics.m4317a(a, location.getLatitude(), location.getLongitude());
        a.m5517a();
    }

    public static void m4311a(double d, double d2) {
        EtsyLogger.m1966a().m1997b("search_area", "local_browse", LocalAnalytics.m4320b(d, d2));
    }

    public static void m4319a(String str) {
        EtsyLogger.m1966a().m2001d("map_expanded", str);
    }

    public static void m4315a(@NonNull LocalMarketCard localMarketCard, @Nullable LocalBrowseManager localBrowseManager) {
        AdHocEventCompatBuilder a = new AdHocEventCompatBuilder("map_pin_tapped").m5515a(localBrowseManager == null ? "local_browse_nearby" : localBrowseManager.getPageInView());
        LocalAnalytics.m4318a(a, localMarketCard);
        a.m5517a();
    }

    public static void m4323b(String str) {
        EtsyLogger.m1966a().m2001d("list_view_expanded", str);
    }

    public static void m4322b(LocalMarketCard localMarketCard, LocalBrowseManager localBrowseManager) {
        LocalAnalytics.m4316a(localMarketCard, localBrowseManager == null ? "local_browse_nearby" : localBrowseManager.getPageInView());
    }

    public static void m4316a(LocalMarketCard localMarketCard, String str) {
        EtsyLogger.m1966a().m1997b("local_market_tapped", str, LocalAnalytics.m4310a(localMarketCard));
    }

    public static void m4313a(LocalMarket localMarket) {
        EtsyLogger.m1966a().m1997b("local_event_map_view", "local_event_view", LocalAnalytics.m4324c(localMarket));
    }

    public static void m4321b(LocalMarket localMarket) {
        EtsyLogger.m1966a().m1997b("local_event_calendar_add", "local_event_view", LocalAnalytics.m4324c(localMarket));
    }

    public static void m4314a(LocalMarket localMarket, EtsyId etsyId) {
        HashMap c = LocalAnalytics.m4324c(localMarket);
        if (etsyId != null) {
            c.put(ResponseConstants.SHOP_ID, etsyId.toString());
        }
        EtsyLogger.m1966a().m1997b("local_event_other_shop_tapped", "local_event_view", c);
    }

    private static void m4317a(@NonNull AdHocEventCompatBuilder adHocEventCompatBuilder, double d, double d2) {
        adHocEventCompatBuilder.m5516a(LocalAnalytics.m4320b(d, d2)).m5512a(AnalyticsLogAttribute.LAT, Double.valueOf(d)).m5512a(AnalyticsLogAttribute.LON, Double.valueOf(d2));
    }

    @NonNull
    private static HashMap<String, Object> m4320b(double d, double d2) {
        return new LocalAnalytics$1(d, d2);
    }

    private static void m4318a(@NonNull AdHocEventCompatBuilder adHocEventCompatBuilder, @NonNull LocalMarketCard localMarketCard) {
        adHocEventCompatBuilder.m5516a(LocalAnalytics.m4310a(localMarketCard)).m5514a((ITrackedObject) localMarketCard);
    }

    @NonNull
    private static HashMap<String, Object> m4310a(LocalMarketCard localMarketCard) {
        return new LocalAnalytics$2(localMarketCard);
    }

    @NonNull
    private static HashMap<String, Object> m4324c(LocalMarket localMarket) {
        return new LocalAnalytics$3(localMarket);
    }
}

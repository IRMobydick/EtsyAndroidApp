package com.etsy.android.lib.util.p013b;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.interfaces.LocalMarketLike;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.a;
import com.google.android.gms.maps.model.b;

/* renamed from: com.etsy.android.lib.util.b.b */
public class LocalMarketsUtil {
    private static final String f2013a;
    private static final a f2014b;
    private static final a f2015c;
    private static final a f2016d;
    private static final a f2017e;

    static {
        f2013a = EtsyDebug.m1891a(LocalMarketsUtil.class);
        f2014b = b.a(R.pin_event_off);
        f2015c = b.a(R.pin_event_on);
        f2016d = b.a(R.pin_store_off);
        f2017e = b.a(R.pin_store_on);
    }

    @Nullable
    public static MarkerOptions m3305a(@NonNull LocalMarket localMarket) {
        MarkerOptions markerOptions = new MarkerOptions();
        try {
            markerOptions.position(localMarket.getLatLng());
            a aVar = localMarket.isWholesaleStore() ? f2016d : f2014b;
            if (aVar == null) {
                return markerOptions;
            }
            markerOptions.icon(aVar);
            markerOptions.anchor(0.5f, 0.5f);
            return markerOptions;
        } catch (Throwable e) {
            EtsyDebug.m1917d(f2013a, "Failed creating marker icon from BitmapDescriptorFactory", e);
            return markerOptions;
        } catch (Throwable e2) {
            EtsyDebug.m1917d(f2013a, "Bad latitude and longitude for local market with ID " + localMarket.getLocalMarketId(), e2);
            EtsyLogger.m1966a().m1986a(f2013a, "NumberFormatException in latitude / longitude for local market with ID " + localMarket.getLocalMarketId());
            return null;
        }
    }

    public static a m3306a(@NonNull LocalMarketLike localMarketLike) {
        return LocalMarket.MARKET_TYPE_SELLER_EVENT.equals(localMarketLike.getMarketType()) ? f2014b : f2016d;
    }

    public static a m3307b(@NonNull LocalMarketLike localMarketLike) {
        return LocalMarket.MARKET_TYPE_SELLER_EVENT.equals(localMarketLike.getMarketType()) ? f2015c : f2017e;
    }

    public static Drawable m3304a(LocalMarketLike localMarketLike, @NonNull Resources resources) {
        return resources.getDrawable(LocalMarket.MARKET_TYPE_SELLER_EVENT.equals(localMarketLike.getMarketType()) ? R.pin_event_off : R.pin_store_off);
    }
}

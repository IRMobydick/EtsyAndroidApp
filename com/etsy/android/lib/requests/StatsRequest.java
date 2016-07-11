package com.etsy.android.lib.requests;

import android.support.annotation.Nullable;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.StatsRevenues;
import com.etsy.android.lib.models.StatsSource;
import com.etsy.android.lib.models.StatsSummary;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class StatsRequest<Result extends BaseModel> extends EtsyRequest<Result> {
    public static final int TYPE_FAVORITES = 1;
    public static final int TYPE_ORDERS = 2;
    public static final int TYPE_REVENUE = 3;
    public static final int TYPE_VIEWS = 0;
    private static final long serialVersionUID = 1235035283711425946L;

    public enum EtsyShopChannelType {
        ETSY_SHOP("etsy-shop", StatsRequest.TYPE_VIEWS),
        CUSTOM_SHOP("custom-shop", StatsRequest.TYPE_FAVORITES),
        ALL_SHOPS("all-shops", StatsRequest.TYPE_ORDERS);
        
        private final String channelType;
        public final int spinnerPosition;

        private EtsyShopChannelType(String str, int i) {
            this.channelType = str;
            this.spinnerPosition = i;
        }

        public String toString() {
            return this.channelType;
        }

        public static EtsyShopChannelType getChannelFromString(@Nullable String str) {
            if (!EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.cp)) {
                return ETSY_SHOP;
            }
            if (ETSY_SHOP.channelType.equals(str)) {
                return ETSY_SHOP;
            }
            if (CUSTOM_SHOP.channelType.equals(str)) {
                return CUSTOM_SHOP;
            }
            if (ALL_SHOPS.channelType.equals(str)) {
                return ALL_SHOPS;
            }
            return ETSY_SHOP;
        }
    }

    public StatsRequest(String str, RequestMethod requestMethod, Class<Result> cls) {
        super(str, requestMethod, cls);
    }

    public static StatsRequest<StatsSummary> getShopStatsSummary(EtsyId etsyId, Calendar calendar, Calendar calendar2) {
        return getShopStatsSummary(etsyId, calendar, calendar2, EtsyShopChannelType.ETSY_SHOP);
    }

    public static StatsRequest<StatsSummary> getShopStatsSummary(EtsyId etsyId, Calendar calendar, Calendar calendar2, EtsyShopChannelType etsyShopChannelType) {
        StatsRequest<StatsSummary> statsRequest = new StatsRequest("/stats/shop/" + etsyId.getId(), RequestMethod.GET, StatsSummary.class);
        statsRequest.addParams(createDateParams(calendar, calendar2));
        if (EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.cp)) {
            Map hashMap = new HashMap();
            hashMap.put("channel", etsyShopChannelType.toString());
            statsRequest.addParams(hashMap);
        }
        return statsRequest;
    }

    public static StatsRequest<StatsRevenues> getStatsRevenues(EtsyId etsyId, Calendar calendar, Calendar calendar2, EtsyShopChannelType etsyShopChannelType) {
        StatsRequest<StatsRevenues> statsRequest = new StatsRequest("/stats/summary/shop/" + etsyId.getId() + "/revenues", RequestMethod.GET, StatsRevenues.class);
        statsRequest.addParams(createDateParams(calendar, calendar2));
        if (EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.cp)) {
            Map hashMap = new HashMap();
            hashMap.put("channel", etsyShopChannelType.toString());
            statsRequest.addParams(hashMap);
        }
        return statsRequest;
    }

    public static StatsRequest<StatsSource> getStatsSources(EtsyId etsyId, int i, Calendar calendar, Calendar calendar2, EtsyShopChannelType etsyShopChannelType) {
        String str = StringUtils.EMPTY;
        switch (i) {
            case TYPE_VIEWS /*0*/:
                str = "/views";
                break;
            case TYPE_FAVORITES /*1*/:
                str = "/favorites";
                break;
        }
        StatsRequest<StatsSource> statsRequest = new StatsRequest("/stats/summary/shop/" + etsyId.getId() + str, RequestMethod.GET, StatsSource.class);
        statsRequest.addParams(createDateParams(calendar, calendar2));
        if (EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.cp)) {
            Map hashMap = new HashMap();
            hashMap.put("channel", etsyShopChannelType.toString());
            statsRequest.addParams(hashMap);
        }
        return statsRequest;
    }

    private static HashMap<String, String> createDateParams(Calendar calendar, Calendar calendar2) {
        HashMap<String, String> hashMap = new HashMap();
        if (!(calendar == null || calendar2 == null)) {
            int i = calendar.get(TYPE_ORDERS) + TYPE_FAVORITES;
            int i2 = calendar.get(5) + TYPE_FAVORITES;
            int i3 = calendar.get(TYPE_FAVORITES);
            int i4 = calendar2.get(TYPE_ORDERS) + TYPE_FAVORITES;
            int i5 = calendar2.get(5) + TYPE_FAVORITES;
            int i6 = calendar2.get(TYPE_FAVORITES);
            hashMap.put("start_month", String.valueOf(i));
            hashMap.put("start_day", String.valueOf(i2));
            hashMap.put("start_year", String.valueOf(i3));
            hashMap.put("end_month", String.valueOf(i4));
            hashMap.put("end_day", String.valueOf(i5));
            hashMap.put("end_year", String.valueOf(i6));
        }
        return hashMap;
    }
}

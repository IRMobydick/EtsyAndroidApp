package com.etsy.android.lib.requests;

import com.etsy.android.lib.config.InstallInfo;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.models.UserCounters;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class UserCountersRequest extends EtsyRequest<UserCounters> {
    private static final String CART_COUNT = "cart_count";
    private static final RequestMethod DEFAULT_METHOD;
    private static final String GIFTCARD_BALANCE = "giftcard_balance";
    private static final String OPEN_ORDERS_COUNT = "open_orders_count";
    private static final String REQUEST_URL = "/personalizedinfo";
    private static final String SHOP_ACTIVITY_COUNT = "shop_activity_count";
    private static final String UNREAD_CONVOS_COUNT = "new_convo_count";
    private static final String USER_ACTIVITY_COUNT = "new_activity_count";

    static {
        DEFAULT_METHOD = RequestMethod.GET;
    }

    public UserCountersRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, UserCounters.class);
    }

    public static UserCountersRequest baseRequest() {
        return new UserCountersRequest(REQUEST_URL, DEFAULT_METHOD);
    }

    public static UserCountersRequest getAllCounts() {
        UserCountersRequest baseRequest = baseRequest();
        String join = StringUtils.join(new String[]{USER_ACTIVITY_COUNT, SHOP_ACTIVITY_COUNT, CART_COUNT, GIFTCARD_BALANCE, UNREAD_CONVOS_COUNT, OPEN_ORDERS_COUNT}, ",");
        Map hashMap = new HashMap();
        hashMap.put("fields", join);
        baseRequest.addParams(hashMap);
        return baseRequest;
    }

    public static UserCountersRequest getSellerAppBadgeCounts() {
        UserCountersRequest baseRequest = baseRequest();
        String join = StringUtils.join(new String[]{SHOP_ACTIVITY_COUNT, UNREAD_CONVOS_COUNT, OPEN_ORDERS_COUNT}, ",");
        Map hashMap = new HashMap();
        hashMap.put("fields", join);
        baseRequest.addParams(hashMap);
        return baseRequest;
    }

    public static UserCountersRequest getCartCount() {
        UserCountersRequest baseRequest = baseRequest();
        String str = CART_COUNT;
        Map hashMap = new HashMap();
        hashMap.put("fields", str);
        if (!aj.m1101a().m1118d()) {
            hashMap.put("guest_id", InstallInfo.m919a().m928e());
        }
        baseRequest.addParams(hashMap);
        return baseRequest;
    }

    public static UserCountersRequest getOpenOrdersCount() {
        UserCountersRequest baseRequest = baseRequest();
        String str = OPEN_ORDERS_COUNT;
        Map hashMap = new HashMap();
        hashMap.put("fields", str);
        baseRequest.addParams(hashMap);
        return baseRequest;
    }

    public static UserCountersRequest getUnreadConvosCount() {
        UserCountersRequest baseRequest = baseRequest();
        String str = UNREAD_CONVOS_COUNT;
        Map hashMap = new HashMap();
        hashMap.put("fields", str);
        baseRequest.addParams(hashMap);
        return baseRequest;
    }

    public static UserCountersRequest getGiftcardBalance() {
        UserCountersRequest baseRequest = baseRequest();
        String str = GIFTCARD_BALANCE;
        Map hashMap = new HashMap();
        hashMap.put("fields", str);
        baseRequest.addParams(hashMap);
        return baseRequest;
    }

    public static UserCountersRequest getNewShopActivityCount() {
        UserCountersRequest baseRequest = baseRequest();
        String str = SHOP_ACTIVITY_COUNT;
        Map hashMap = new HashMap();
        hashMap.put("fields", str);
        baseRequest.addParams(hashMap);
        return baseRequest;
    }

    public static UserCountersRequest getNewUserActivityCount() {
        UserCountersRequest baseRequest = baseRequest();
        String str = USER_ACTIVITY_COUNT;
        Map hashMap = new HashMap();
        hashMap.put("fields", str);
        baseRequest.addParams(hashMap);
        return baseRequest;
    }
}

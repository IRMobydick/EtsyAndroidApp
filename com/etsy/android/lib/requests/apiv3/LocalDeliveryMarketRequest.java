package com.etsy.android.lib.requests.apiv3;

import android.content.Context;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.models.apiv3.LocalDeliveryMarket;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import com.etsy.android.lib.util.SharedPreferencesUtility;

public class LocalDeliveryMarketRequest extends EtsyRequest<LocalDeliveryMarket> {
    public LocalDeliveryMarketRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, LocalDeliveryMarket.class, EndpointType.APIv3);
    }

    public static LocalDeliveryMarketRequest get(Context context) {
        String i = SharedPreferencesUtility.m3148i(context);
        LocalDeliveryMarketRequest localDeliveryMarketRequest = new LocalDeliveryMarketRequest("/shipping/local-delivery/available-market?country_iso=" + i + "&zip=" + SharedPreferencesUtility.m3150j(context), RequestMethod.GET);
        if (aj.m1101a().m1118d()) {
            localDeliveryMarketRequest.setV3Scope(APIv3Scope.MEMBER);
        } else {
            localDeliveryMarketRequest.setV3Scope(APIv3Scope.PUBLIC);
        }
        return localDeliveryMarketRequest;
    }
}

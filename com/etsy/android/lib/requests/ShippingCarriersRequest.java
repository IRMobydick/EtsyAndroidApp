package com.etsy.android.lib.requests;

import com.etsy.android.lib.models.ShippingCarrier;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.HashMap;
import java.util.Map;

public class ShippingCarriersRequest extends EtsyRequest<ShippingCarrier> {
    public ShippingCarriersRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, ShippingCarrier.class);
    }

    public static ShippingCarriersRequest getShippingCarriers() {
        String str = "/shipping/carriers";
        return new ShippingCarriersRequest("/shipping/carriers", RequestMethod.GET);
    }

    public static ShippingCarriersRequest getShippingCarrierForTrackingNumber(String str) {
        String str2 = "/shipping/validateTracking";
        ShippingCarriersRequest shippingCarriersRequest = new ShippingCarriersRequest("/shipping/validateTracking", RequestMethod.GET);
        Map hashMap = new HashMap();
        hashMap.put("tracking_num", str);
        shippingCarriersRequest.addParams(hashMap);
        return shippingCarriersRequest;
    }
}

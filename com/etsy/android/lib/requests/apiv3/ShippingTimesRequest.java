package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.models.ShippingTimes;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class ShippingTimesRequest extends EtsyRequest<ShippingTimes> {
    private static final long serialVersionUID = -2624982331314050953L;

    public ShippingTimesRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, ShippingTimes.class, EndpointType.APIv3);
    }

    public static ShippingTimesRequest getShippingTimes() {
        return new ShippingTimesRequest("/shipping/processing-times", RequestMethod.GET);
    }
}

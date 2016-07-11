package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.models.apiv3.ShippingAddressPreference;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class ShippingAddressRequest extends EtsyRequest<ShippingAddressPreference> {
    public ShippingAddressRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, ShippingAddressPreference.class, EndpointType.APIv3);
    }

    public static ShippingAddressRequest getDefaultShippingAddressPreference() {
        String str = "/addresses/ship-to";
        ShippingAddressRequest shippingAddressRequest = new ShippingAddressRequest("/addresses/ship-to", RequestMethod.GET);
        shippingAddressRequest.setV3Scope(APIv3Scope.MEMBER);
        return shippingAddressRequest;
    }
}

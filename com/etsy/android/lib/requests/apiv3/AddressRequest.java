package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.models.apiv3.UserAddressV3;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class AddressRequest extends EtsyRequest<UserAddressV3> {
    public AddressRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, UserAddressV3.class, EndpointType.APIv3);
    }

    public static AddressRequest delete(UserAddressV3 userAddressV3) {
        AddressRequest addressRequest = new AddressRequest("/addresses/" + userAddressV3.getUserAddressId().getId(), RequestMethod.DELETE);
        addressRequest.setV3Scope(APIv3Scope.MEMBER);
        return addressRequest;
    }
}

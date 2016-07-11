package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.models.UserAddress;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class IPPReaderAddressesRequest extends EtsyRequest<UserAddress> {
    private static final long serialVersionUID = 3132443247668219518L;

    public IPPReaderAddressesRequest(String str) {
        super(str, RequestMethod.GET, UserAddress.class, EndpointType.APIv3);
    }

    public static IPPReaderAddressesRequest getAddresses() {
        IPPReaderAddressesRequest iPPReaderAddressesRequest = new IPPReaderAddressesRequest("/in-person/addresses");
        iPPReaderAddressesRequest.setV3Scope(APIv3Scope.MEMBER);
        return iPPReaderAddressesRequest;
    }
}

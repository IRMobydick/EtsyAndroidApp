package com.etsy.android.lib.requests;

import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class ShopsRequest extends EtsyRequest<Shop> {
    private static final long serialVersionUID = 1235035283711425946L;

    public ShopsRequest(String str, RequestMethod requestMethod, String str2) {
        super(str, requestMethod, Shop.class, EndpointType.API, str2);
    }

    public static ShopsRequest findAllShops() {
        return new ShopsRequest("/shops", RequestMethod.GET, null);
    }

    public static ShopsRequest getShop(EtsyId etsyId) {
        return new ShopsRequest("/shops/" + etsyId.getId(), RequestMethod.GET, null);
    }

    public static ShopsRequest updateShop(EtsyId etsyId, String str) {
        return new ShopsRequest("/shops/" + etsyId.getId(), RequestMethod.PUT, str);
    }
}

package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class ShopCardRequest extends EtsyRequest<ShopCard> {
    public ShopCardRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, ShopCard.class, EndpointType.APIv3);
        setV3Scope(APIv3Scope.MEMBER);
    }

    public static ShopCardRequest getFavoriteShopsSellingNearby() {
        ShopCardRequest shopCardRequest = new ShopCardRequest("/localmarkets/shops/favorites", RequestMethod.GET);
        shopCardRequest.setV3Bespoke(true);
        return shopCardRequest;
    }
}

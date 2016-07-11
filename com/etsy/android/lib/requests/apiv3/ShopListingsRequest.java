package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.models.apiv3.ShopListing;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class ShopListingsRequest extends EtsyRequest<ShopListing> {
    private static final long serialVersionUID = 4461679651861714014L;

    public ShopListingsRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, ShopListing.class, EndpointType.APIv3);
    }

    public static ShopListingsRequest copyListing(EtsyId etsyId, EtsyId etsyId2) {
        ShopListingsRequest shopListingsRequest = new ShopListingsRequest("/listings/" + etsyId2.getId() + "/copy", RequestMethod.POST);
        APIv3Scope.SHOP.setIdentifier(etsyId.getId());
        shopListingsRequest.setV3Scope(APIv3Scope.SHOP);
        return shopListingsRequest;
    }
}

package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.shopshare.ShareItemList;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class ShareItemListRequest extends EtsyRequest<ShareItemList> {
    private static final int LIMIT = 10;

    public ShareItemListRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, ShareItemList.class, EndpointType.APIv3);
    }

    public static ShareItemListRequest getFeed(EtsyId etsyId) {
        ShareItemListRequest shareItemListRequest = new ShareItemListRequest("/shares", RequestMethod.GET);
        APIv3Scope.SHOP.setIdentifier(etsyId.getId());
        shareItemListRequest.setV3Scope(APIv3Scope.SHOP);
        shareItemListRequest.setV3Bespoke(true);
        return shareItemListRequest;
    }
}

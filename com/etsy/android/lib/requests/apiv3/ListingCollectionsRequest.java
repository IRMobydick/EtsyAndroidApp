package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.models.apiv3.CheckableListingCollection;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class ListingCollectionsRequest extends EtsyRequest<CheckableListingCollection> {
    private static final long serialVersionUID = 4461679651861714014L;

    public ListingCollectionsRequest(String str) {
        super(str, RequestMethod.GET, CheckableListingCollection.class, EndpointType.APIv3);
    }

    public static ListingCollectionsRequest getListingCollections(EtsyId etsyId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("/listings/");
        stringBuilder.append(etsyId.getId());
        stringBuilder.append("/collections");
        ListingCollectionsRequest listingCollectionsRequest = new ListingCollectionsRequest(stringBuilder.toString());
        listingCollectionsRequest.setV3Scope(APIv3Scope.MEMBER);
        return listingCollectionsRequest;
    }
}

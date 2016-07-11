package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class CollectionsRequest extends EtsyRequest<Collection> {
    public static final int MISSING_ERROR_CODE = 404;
    private static final long serialVersionUID = 4461679651861714014L;

    public CollectionsRequest(String str) {
        super(str, RequestMethod.GET, Collection.class, EndpointType.APIv3);
    }

    public static CollectionsRequest getCollections(EtsyId etsyId, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        if (z) {
            stringBuilder.append("/collections");
        } else {
            stringBuilder.append("/users/");
            stringBuilder.append(etsyId.toString());
            stringBuilder.append("/collections");
        }
        CollectionsRequest collectionsRequest = new CollectionsRequest(stringBuilder.toString());
        if (z) {
            collectionsRequest.setV3Scope(APIv3Scope.MEMBER);
        }
        return collectionsRequest;
    }
}

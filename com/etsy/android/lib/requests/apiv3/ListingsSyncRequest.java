package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.apiv3.editable.EditableListingV3;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class ListingsSyncRequest<Result extends BaseModel> extends EtsyRequest<Result> {
    private static final String TAG;

    static {
        TAG = EtsyDebug.m1891a(ListingsSyncRequest.class);
    }

    public ListingsSyncRequest(String str, RequestMethod requestMethod, Class<Result> cls) {
        super(str, requestMethod, cls, EndpointType.APIv3);
    }

    public static EtsyApiV3Request<EditableListingV3> getMyShopListings(String str) {
        return new EtsyApiV3Request(EditableListingV3.class, String.format("/etsyapps/v3/bespoke/shop/%s/listings", new Object[]{str}));
    }
}

package com.etsy.android.lib.requests.apiv3;

import android.content.Context;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ListingShippingDetails;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import java.util.HashMap;
import java.util.Map;

public class ListingShippingRequest extends EtsyRequest<ListingShippingDetails> {
    private ListingShippingRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, ListingShippingDetails.class, EndpointType.APIv3);
    }

    public static ListingShippingRequest getListingShippingDetails(Context context, EtsyId etsyId) {
        ListingShippingRequest listingShippingRequest = new ListingShippingRequest("/listings/" + etsyId.getId() + "/shipping-details", RequestMethod.GET);
        listingShippingRequest.setV3Scope(APIv3Scope.MEMBER);
        listingShippingRequest.setV3Bespoke(true);
        Map hashMap = new HashMap();
        if (!aj.m1101a().m1118d()) {
            hashMap.put(ResponseConstants.COUNTRY_ID, SharedPreferencesUtility.m3148i(context));
            hashMap.put(ResponseConstants.POSTAL_CODE, SharedPreferencesUtility.m3150j(context));
        }
        if (hashMap.size() > 0) {
            listingShippingRequest.addParams(hashMap);
        }
        return listingShippingRequest;
    }
}

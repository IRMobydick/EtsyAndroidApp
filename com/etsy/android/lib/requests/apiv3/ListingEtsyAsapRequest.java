package com.etsy.android.lib.requests.apiv3;

import android.text.TextUtils;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ListingEtsyAsap;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.HashMap;
import java.util.Map;

public class ListingEtsyAsapRequest extends EtsyRequest<ListingEtsyAsap> {
    public ListingEtsyAsapRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, ListingEtsyAsap.class, EndpointType.APIv3);
    }

    public static ListingEtsyAsapRequest getListingOffersEtsyAsap(EtsyId etsyId, String str, String str2) {
        ListingEtsyAsapRequest listingEtsyAsapRequest = new ListingEtsyAsapRequest("/listings/" + etsyId.getId() + "/shipping/etsy-asap", RequestMethod.GET);
        Map hashMap = new HashMap();
        hashMap.put(ResponseConstants.COUNTRY_ID, str);
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put(ResponseConstants.POSTAL_CODE, str2);
        }
        if (aj.m1101a().m1118d()) {
            listingEtsyAsapRequest.setV3Scope(APIv3Scope.MEMBER);
        } else {
            listingEtsyAsapRequest.setV3Scope(APIv3Scope.PUBLIC);
        }
        listingEtsyAsapRequest.addParams(hashMap);
        return listingEtsyAsapRequest;
    }
}

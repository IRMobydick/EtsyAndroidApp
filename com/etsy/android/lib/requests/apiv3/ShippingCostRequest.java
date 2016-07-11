package com.etsy.android.lib.requests.apiv3;

import android.text.TextUtils;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ShippingOption;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.HashMap;
import java.util.Map;

public class ShippingCostRequest extends EtsyRequest<ShippingOption> {
    public ShippingCostRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, ShippingOption.class, EndpointType.APIv3);
    }

    public static ShippingCostRequest getStandardShippingOptionForListing(EtsyId etsyId, String str, String str2) {
        ShippingCostRequest shippingCostRequest = new ShippingCostRequest("/listings/" + etsyId.getId() + "/shipping/standard-option", RequestMethod.GET);
        Map hashMap = new HashMap();
        hashMap.put(ResponseConstants.COUNTRY_ID, str);
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put(ResponseConstants.POSTAL_CODE, str2);
        }
        if (aj.m1101a().m1118d()) {
            shippingCostRequest.setV3Scope(APIv3Scope.MEMBER);
            hashMap.put(ResponseConstants.UPDATE_PREFERENCE, "true");
        }
        shippingCostRequest.addParams(hashMap);
        return shippingCostRequest;
    }
}

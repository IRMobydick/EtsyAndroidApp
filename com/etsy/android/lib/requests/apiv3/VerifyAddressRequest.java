package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.core.ad;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.AddressesComparison;
import com.etsy.android.lib.models.apiv3.UserAddressV3;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.HashMap;

public class VerifyAddressRequest extends EtsyRequest<AddressesComparison> {
    private static final String TAG;

    static {
        TAG = EtsyDebug.m1891a(VerifyAddressRequest.class);
    }

    public VerifyAddressRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, AddressesComparison.class, EndpointType.APIv3);
    }

    public static VerifyAddressRequest post(int i, UserAddressV3 userAddressV3) {
        String str = "/addresses/comparisons";
        VerifyAddressRequest verifyAddressRequest = new VerifyAddressRequest("/addresses/comparisons", RequestMethod.POST);
        verifyAddressRequest.setV3Scope(APIv3Scope.PUBLIC);
        verifyAddressRequest.setContentType(JSON_CONTENT_TYPE);
        HashMap hashMap = new HashMap();
        hashMap.put(ResponseConstants.FIRST_LINE, userAddressV3.getFirstLine());
        hashMap.put(ResponseConstants.SECOND_LINE, userAddressV3.getSecondLine());
        hashMap.put(ResponseConstants.CITY, userAddressV3.getCity());
        hashMap.put(ResponseConstants.STATE, userAddressV3.getState());
        hashMap.put(ResponseConstants.ZIP, userAddressV3.getZip());
        hashMap.put(ResponseConstants.COUNTRY_ID, userAddressV3.getCountryId().getId());
        hashMap.put(ResponseConstants.PROVIDER_ID, Integer.valueOf(i));
        try {
            verifyAddressRequest.setPayload(ad.m1081a().m1083b().writeValueAsBytes(hashMap));
        } catch (Throwable e) {
            EtsyDebug.m1917d(TAG, "Problem parsing map to JSON", e);
        }
        return verifyAddressRequest;
    }
}

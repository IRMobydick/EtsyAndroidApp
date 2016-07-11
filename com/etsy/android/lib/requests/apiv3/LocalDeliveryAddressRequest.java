package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.core.ad;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.UserAddressV3;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.HashMap;

public class LocalDeliveryAddressRequest extends EtsyRequest<UserAddressV3> {
    private static final String TAG;

    static {
        TAG = EtsyDebug.m1891a(LocalDeliveryAddressRequest.class);
    }

    public LocalDeliveryAddressRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, UserAddressV3.class, EndpointType.APIv3);
    }

    public static LocalDeliveryAddressRequest list(int i) {
        LocalDeliveryAddressRequest localDeliveryAddressRequest = new LocalDeliveryAddressRequest("/local-delivery/" + i + "/addresses", RequestMethod.GET);
        localDeliveryAddressRequest.setV3Scope(APIv3Scope.MEMBER);
        return localDeliveryAddressRequest;
    }

    public static LocalDeliveryAddressRequest save(int i, UserAddressV3 userAddressV3) {
        RequestMethod requestMethod;
        if (userAddressV3.getUserAddressId().hasId()) {
            requestMethod = RequestMethod.PUT;
        } else {
            requestMethod = RequestMethod.POST;
        }
        LocalDeliveryAddressRequest localDeliveryAddressRequest = new LocalDeliveryAddressRequest("/local-delivery/" + i + "/addresses", requestMethod);
        localDeliveryAddressRequest.setV3Scope(APIv3Scope.MEMBER);
        localDeliveryAddressRequest.setContentType(JSON_CONTENT_TYPE);
        HashMap hashMap = new HashMap();
        hashMap.put(ResponseConstants.ADDRESS, userAddressV3.getApiData());
        try {
            localDeliveryAddressRequest.setPayload(ad.m1081a().m1083b().writeValueAsBytes(hashMap));
        } catch (Throwable e) {
            EtsyDebug.m1917d(TAG, "Problem parsing map to JSON", e);
        }
        return localDeliveryAddressRequest;
    }
}

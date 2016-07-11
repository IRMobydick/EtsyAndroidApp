package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.core.ad;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ShippingPostmatesDelivery;
import com.etsy.android.lib.models.apiv3.UserAddressV3;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.HashMap;

public class LocalDeliveryRequest extends EtsyRequest<ShippingPostmatesDelivery> {
    private static final String TAG;

    static {
        TAG = EtsyDebug.m1891a(LocalDeliveryRequest.class);
    }

    public LocalDeliveryRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, ShippingPostmatesDelivery.class, EndpointType.APIv3);
    }

    public static LocalDeliveryRequest create(EtsyId etsyId, UserAddressV3 userAddressV3, String str) {
        String str2 = "/shipping/local/delivery";
        LocalDeliveryRequest localDeliveryRequest = new LocalDeliveryRequest("/shipping/local/delivery", RequestMethod.POST);
        localDeliveryRequest.setV3Scope(APIv3Scope.SHOP);
        localDeliveryRequest.setContentType(JSON_CONTENT_TYPE);
        HashMap hashMap = new HashMap();
        hashMap.put(ResponseConstants.RECEIPT_ID, etsyId.getId());
        hashMap.put(ResponseConstants.PICKUP_NOTES, str);
        hashMap.put(ResponseConstants.PICKUP_ADDRESS, userAddressV3.getApiData());
        try {
            localDeliveryRequest.setPayload(ad.m1081a().m1083b().writeValueAsBytes(hashMap));
        } catch (Throwable e) {
            EtsyDebug.m1917d(TAG, "Problem parsing map to JSON", e);
        }
        return localDeliveryRequest;
    }

    public static LocalDeliveryRequest cancel(EtsyId etsyId, EtsyId etsyId2) {
        String str = "/shipping/local/delivery/cancel";
        LocalDeliveryRequest localDeliveryRequest = new LocalDeliveryRequest("/shipping/local/delivery/cancel", RequestMethod.POST);
        localDeliveryRequest.setV3Scope(APIv3Scope.SHOP);
        localDeliveryRequest.setContentType(JSON_CONTENT_TYPE);
        HashMap hashMap = new HashMap();
        hashMap.put(ResponseConstants.RECEIPT_ID, etsyId.getId());
        hashMap.put(ResponseConstants.DELIVERY_ID, etsyId2.getId());
        try {
            localDeliveryRequest.setPayload(ad.m1081a().m1083b().writeValueAsBytes(hashMap));
        } catch (Throwable e) {
            EtsyDebug.m1917d(TAG, "Problem parsing map to JSON", e);
        }
        return localDeliveryRequest;
    }
}

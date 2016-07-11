package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.apiv3.BespokeScheduleLocalDeliveryOverlay;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class BespokeScheduleLocalDeliveryRequest<Result extends BaseModel> extends EtsyRequest<BespokeScheduleLocalDeliveryOverlay> {
    public BespokeScheduleLocalDeliveryRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, BespokeScheduleLocalDeliveryOverlay.class, EndpointType.APIv3);
    }

    public static BespokeScheduleLocalDeliveryRequest<BespokeScheduleLocalDeliveryOverlay> get(EtsyId etsyId) {
        BespokeScheduleLocalDeliveryRequest<BespokeScheduleLocalDeliveryOverlay> bespokeScheduleLocalDeliveryRequest = new BespokeScheduleLocalDeliveryRequest("/receipt/" + etsyId.getId() + "/shipping/schedule-local-delivery", RequestMethod.GET);
        bespokeScheduleLocalDeliveryRequest.setV3Scope(APIv3Scope.SHOP);
        bespokeScheduleLocalDeliveryRequest.setV3Bespoke(true);
        return bespokeScheduleLocalDeliveryRequest;
    }
}

package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class DCReminderEmailRequest extends EtsyRequest<EmptyResult> {
    private static final long serialVersionUID = -5846608221830965505L;

    public DCReminderEmailRequest(String str) {
        super(str, RequestMethod.POST, EmptyResult.class, EndpointType.APIv3);
    }

    public static DCReminderEmailRequest sendReminderEmail(EtsyId etsyId) {
        DCReminderEmailRequest dCReminderEmailRequest = new DCReminderEmailRequest("/in-person/direct-checkout/onboarding/reminder-email");
        APIv3Scope.SHOP.setIdentifier(etsyId.toString());
        dCReminderEmailRequest.setV3Scope(APIv3Scope.SHOP);
        return dCReminderEmailRequest;
    }
}

package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.models.Payment;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class PaymentRequest extends EtsyRequest<Payment> {
    private static final long serialVersionUID = -2624982331314050953L;

    public PaymentRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, Payment.class, EndpointType.APIv3);
    }

    public static PaymentRequest getPaymentsByReceiptIds(EtsyId etsyId, APIv3Scope aPIv3Scope) {
        PaymentRequest paymentRequest = new PaymentRequest("/payments/from-receipts", RequestMethod.GET);
        aPIv3Scope.setIdentifier(etsyId.getId());
        paymentRequest.setV3Scope(aPIv3Scope);
        return paymentRequest;
    }
}

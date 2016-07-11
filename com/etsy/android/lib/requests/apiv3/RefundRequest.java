package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.apiv3.PaymentSuccessResponse;
import com.etsy.android.lib.models.apiv3.RefundReason;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class RefundRequest<Result extends BaseModel> extends EtsyRequest<Result> {
    private static final long serialVersionUID = -1782733545383096172L;

    public RefundRequest(String str, Class<Result> cls, RequestMethod requestMethod) {
        super(str, requestMethod, cls, EndpointType.APIv3);
    }

    public static RefundRequest<PaymentSuccessResponse> issueFullRefund(EtsyId etsyId, EtsyId etsyId2) {
        RefundRequest<PaymentSuccessResponse> refundRequest = new RefundRequest("/receipts/" + etsyId + "/full-refund", PaymentSuccessResponse.class, RequestMethod.PUT);
        APIv3Scope.SHOP.setIdentifier(etsyId2.getId());
        refundRequest.setV3Scope(APIv3Scope.SHOP);
        return refundRequest;
    }

    public static RefundRequest<RefundReason> getRefundReasons() {
        return new RefundRequest("/refund-reasons", RefundReason.class, RequestMethod.GET);
    }
}

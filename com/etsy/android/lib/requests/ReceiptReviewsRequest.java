package com.etsy.android.lib.requests;

import com.etsy.android.lib.models.ReceiptReview;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class ReceiptReviewsRequest extends EtsyRequest<ReceiptReview> {
    private static final long serialVersionUID = 1235035283711425946L;

    public ReceiptReviewsRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, ReceiptReview.class);
    }

    public static ReceiptReviewsRequest getAllReviews(EtsyId etsyId) {
        return new ReceiptReviewsRequest("/shops/" + etsyId.getId() + "/reviews", RequestMethod.GET);
    }

    public static ReceiptReviewsRequest getReviewOfTransaction(EtsyId etsyId) {
        return new ReceiptReviewsRequest("/feedback/transactions/" + etsyId.getId(), RequestMethod.GET);
    }
}

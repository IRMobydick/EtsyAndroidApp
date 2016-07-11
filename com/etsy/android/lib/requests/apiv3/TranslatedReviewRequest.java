package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.models.apiv3.TranslatedReview;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.HashMap;
import java.util.Map;

public class TranslatedReviewRequest extends EtsyRequest<TranslatedReview> {
    public TranslatedReviewRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, TranslatedReview.class, EndpointType.APIv3);
    }

    public static TranslatedReviewRequest getTranslatedReview(EtsyId etsyId, EtsyId etsyId2, String str) {
        TranslatedReviewRequest translatedReviewRequest = new TranslatedReviewRequest("/translations/shops/" + etsyId + "/reviews/" + etsyId2, RequestMethod.GET);
        Map hashMap = new HashMap();
        hashMap.put(EtsyRequest.PARAM_LANGUAGE, str);
        translatedReviewRequest.addParams(hashMap);
        return translatedReviewRequest;
    }
}

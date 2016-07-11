package com.etsy.android.lib.logger;

import com.etsy.android.lib.models.ResponseConstants;
import java.util.HashMap;

final class ViewAnalyticsParameters$5 extends HashMap<String, aa> {
    ViewAnalyticsParameters$5() {
        put("convo_id", new aa("convo_id", AnalyticsLogAttribute.CONVO_ID, ac.f1745b));
        put(ResponseConstants.RECEIPT_ID, new aa(ResponseConstants.RECEIPT_ID, AnalyticsLogAttribute.RECEIPT_ID, ac.f1746c));
        put(ResponseConstants.LISTING_ID, new aa(ResponseConstants.LISTING_ID, AnalyticsLogAttribute.LISTING_ID, ac.f1746c));
        put(ResponseConstants.LOCAL_MARKET_ID, new aa(ResponseConstants.LOCAL_MARKET_ID, AnalyticsLogAttribute.LOCAL_MARKET_ID, ac.f1746c));
        put("username", new aa("username", AnalyticsLogAttribute.USER_NAME, ac.f1744a));
        put(ResponseConstants.URL, new aa(ResponseConstants.URL, AnalyticsLogAttribute.URL, ac.f1744a));
        put("accepted_structured_policies", new aa("accepted_structured_policies", AnalyticsLogAttribute.ACCEPTED_STRUCTURED_POLICIES, ac.f1747d));
        put(".loc", new aa(".loc", AnalyticsLogAttribute.LOC, ac.f1744a));
        put(".ref", new aa(".ref", AnalyticsLogAttribute.REFERRER, ac.f1744a));
    }
}

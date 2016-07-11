package com.etsy.android.lib.requests;

import com.adjust.sdk.Constants;
import com.android.volley.Request.Priority;
import com.etsy.android.lib.core.posts.EtsyRequestPost;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.SearchAdsMetadata;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.nio.charset.Charset;

public class SearchAdsLogRequest extends EtsyRequestPost<EmptyResult> {
    private static final String LOG_CLICK_ENDPOINT = "/s2/service/logclick";
    private static final String LOG_IMPRESSION_ENDPOINT = "/s2/service/log";
    private static final Priority REQUEST_PRIORITY;
    private static final long serialVersionUID = -7476364561674445663L;

    static {
        REQUEST_PRIORITY = Priority.LOW;
    }

    public SearchAdsLogRequest(EtsyRequest<EmptyResult> etsyRequest) {
        super(etsyRequest);
        etsyRequest.setPriority(REQUEST_PRIORITY);
    }

    public static SearchAdsLogRequest logSearchAdsClick(ListingLike listingLike) {
        EtsyRequest etsyRequest = new EtsyRequest(LOG_CLICK_ENDPOINT, RequestMethod.POST, EmptyResult.class, EndpointType.ETSY);
        String str = "sref2=" + listingLike.getSearchAdsMetadata().getSref();
        etsyRequest.setContentType("application/x-www-form-urlencoded");
        etsyRequest.setPayload(str.getBytes(Charset.forName(Constants.ENCODING)));
        return new SearchAdsLogRequest(etsyRequest);
    }

    public static SearchAdsLogRequest logSearchAdsImpression(ListingLike listingLike) {
        EtsyRequest etsyRequest = new EtsyRequest(LOG_IMPRESSION_ENDPOINT, RequestMethod.POST, EmptyResult.class, EndpointType.ETSY);
        SearchAdsMetadata searchAdsMetadata = listingLike.getSearchAdsMetadata();
        String format = String.format("metaData=%s&metaDataHmac=%s", new Object[]{searchAdsMetadata.getMetadataJson(), searchAdsMetadata.getMetadataHash()});
        etsyRequest.setContentType("application/x-www-form-urlencoded");
        etsyRequest.setPayload(format.getBytes(Charset.forName(Constants.ENCODING)));
        return new SearchAdsLogRequest(etsyRequest);
    }

    public int getVersionCode() {
        return 1;
    }
}

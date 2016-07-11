package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.models.apiv3.bughunt.Leader;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class BugHuntLeaderboardRequest extends EtsyRequest<Leader> {
    private static final String URL = "/bughunt/leaders";

    public BugHuntLeaderboardRequest() {
        super(URL, RequestMethod.GET, Leader.class, EndpointType.APIv3);
    }
}

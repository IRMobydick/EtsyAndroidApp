package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.models.apiv3.WhenMade;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class WhenMadeRequest extends EtsyRequest<WhenMade> {
    private static final long serialVersionUID = 4461679651861714014L;

    public WhenMadeRequest(String str) {
        super(str, RequestMethod.GET, WhenMade.class, EndpointType.APIv3);
    }

    public static WhenMadeRequest getWhenMadeList() {
        return new WhenMadeRequest("/when-made");
    }
}

package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class NextRequest<Result extends BaseModel> extends EtsyRequest<Result> {
    private static final long serialVersionUID = 4461679651861714014L;

    public NextRequest(String str, Class<Result> cls) {
        super(str, RequestMethod.GET, cls, EndpointType.APIv3);
        setIsNextLink(true);
    }
}

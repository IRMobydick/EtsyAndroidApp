package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class LeaveReviewRequest<Result extends BaseModel> extends EtsyRequest<Result> {
    private static final long serialVersionUID = -9211933273389513048L;

    public LeaveReviewRequest(String str, RequestMethod requestMethod, Class<Result> cls) {
        super(str, requestMethod, cls, EndpointType.APIv3);
    }
}

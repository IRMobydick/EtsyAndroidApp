package com.etsy.android.lib.requests;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.Region;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class RegionsRequest<Result extends BaseModel> extends EtsyRequest {
    private static final long serialVersionUID = -8156137735120277271L;

    public RegionsRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, Region.class);
    }

    public static RegionsRequest<Region> getEligibleRegions() {
        return new RegionsRequest("/regions/eligible", RequestMethod.GET);
    }
}

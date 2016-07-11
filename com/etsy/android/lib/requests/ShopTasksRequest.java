package com.etsy.android.lib.requests;

import com.etsy.android.lib.models.ShopTask;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class ShopTasksRequest extends EtsyRequest<ShopTask> {
    private static final long serialVersionUID = 1235035283711425946L;

    public ShopTasksRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, ShopTask.class, EndpointType.API);
    }

    public static ShopTasksRequest getShopTasks() {
        return new ShopTasksRequest("/tasks", RequestMethod.GET);
    }
}

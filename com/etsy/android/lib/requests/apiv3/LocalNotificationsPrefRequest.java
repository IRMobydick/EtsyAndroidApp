package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.HashMap;
import java.util.Map;

public class LocalNotificationsPrefRequest extends EtsyRequest<EmptyResult> {
    private static final long serialVersionUID = -1526165277584131887L;

    public LocalNotificationsPrefRequest(String str) {
        super(str, RequestMethod.POST, EmptyResult.class, EndpointType.APIv3);
    }

    public static LocalNotificationsPrefRequest getLocalNotificationsPrefRequest(boolean z) {
        LocalNotificationsPrefRequest localNotificationsPrefRequest = new LocalNotificationsPrefRequest("/user-preferences");
        Map hashMap = new HashMap();
        hashMap.put("preference_name", ResponseConstants.RECEIVE_LOCAL_MARKET_BUYER_UPDATES);
        hashMap.put("preference_value", String.valueOf(z));
        localNotificationsPrefRequest.addBodyParams(hashMap);
        localNotificationsPrefRequest.setV3Scope(APIv3Scope.MEMBER);
        return localNotificationsPrefRequest;
    }
}

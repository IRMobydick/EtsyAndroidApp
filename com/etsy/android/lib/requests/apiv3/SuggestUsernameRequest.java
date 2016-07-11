package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.SuggestUsernameResult;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.HashMap;
import java.util.Map;

public class SuggestUsernameRequest extends EtsyRequest<SuggestUsernameResult> {
    public SuggestUsernameRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, SuggestUsernameResult.class, EndpointType.APIv3);
        setV3Scope(APIv3Scope.PUBLIC);
    }

    public static SuggestUsernameRequest getSuggestions(String str, String str2, String str3) {
        SuggestUsernameRequest suggestUsernameRequest = new SuggestUsernameRequest("/suggest-username", RequestMethod.GET);
        suggestUsernameRequest.setSigned(false);
        Map hashMap = new HashMap();
        hashMap.put(ResponseConstants.EMAIL, str3);
        hashMap.put(ResponseConstants.FIRST_NAME, str);
        hashMap.put(ResponseConstants.LAST_NAME, str2);
        suggestUsernameRequest.addParams(hashMap);
        return suggestUsernameRequest;
    }
}

package com.etsy.android.lib.requests;

import com.etsy.android.lib.models.ExternalAccountResult;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.HashMap;
import java.util.Map;

public class ExternalAccountRequest extends EtsyRequest<ExternalAccountResult> {
    public ExternalAccountRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, ExternalAccountResult.class);
    }

    public static ExternalAccountRequest hasExternalAccount(String str, String str2, String str3, String str4) {
        ExternalAccountRequest externalAccountRequest = new ExternalAccountRequest("/external-account/is-valid/" + str, RequestMethod.POST);
        externalAccountRequest.setSigned(false);
        Map hashMap = new HashMap();
        hashMap.put(ResponseConstants.NAME, str2);
        hashMap.put(ResponseConstants.EMAIL, str3);
        hashMap.put("external_account_id", str);
        hashMap.put("external_account_type_name", str4);
        externalAccountRequest.addParams(hashMap);
        return externalAccountRequest;
    }
}

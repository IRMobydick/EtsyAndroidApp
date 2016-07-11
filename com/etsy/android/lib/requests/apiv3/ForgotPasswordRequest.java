package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.core.ad;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.HashMap;
import java.util.Map;

public class ForgotPasswordRequest extends EtsyRequest<EmptyResult> {
    private static final String TAG;

    static {
        TAG = EtsyDebug.m1891a(ForgotPasswordRequest.class);
    }

    public ForgotPasswordRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, EmptyResult.class, EndpointType.APIv3);
    }

    public static ForgotPasswordRequest postEmailAddress(String str) {
        ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest("/forgot-password", RequestMethod.POST);
        forgotPasswordRequest.setContentType(JSON_CONTENT_TYPE);
        Map hashMap = new HashMap();
        hashMap.put(ResponseConstants.EMAIL_ADDRESS, str);
        try {
            forgotPasswordRequest.setPayload(ad.m1081a().m1083b().writeValueAsBytes(hashMap));
        } catch (Throwable e) {
            EtsyDebug.m1917d(TAG, "Problem parsing map to JSON", e);
        }
        forgotPasswordRequest.setSigned(false);
        return forgotPasswordRequest;
    }
}

package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.core.ad;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ValidatedZip;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.HashMap;

public class ValidateLocalZipRequest extends EtsyRequest<ValidatedZip> {
    private static final String TAG;

    static {
        TAG = EtsyDebug.m1891a(ValidateLocalZipRequest.class);
    }

    public ValidateLocalZipRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, ValidatedZip.class, EndpointType.APIv3);
    }

    public static ValidateLocalZipRequest post(int i, String str) {
        String str2 = "/shipping/local-delivery/validate-zip";
        ValidateLocalZipRequest validateLocalZipRequest = new ValidateLocalZipRequest("/shipping/local-delivery/validate-zip", RequestMethod.POST);
        validateLocalZipRequest.setV3Scope(APIv3Scope.PUBLIC);
        validateLocalZipRequest.setContentType(JSON_CONTENT_TYPE);
        HashMap hashMap = new HashMap();
        hashMap.put(ResponseConstants.ZIP, str);
        hashMap.put(ResponseConstants.MARKET, Integer.valueOf(i));
        try {
            validateLocalZipRequest.setPayload(ad.m1081a().m1083b().writeValueAsBytes(hashMap));
        } catch (Throwable e) {
            EtsyDebug.m1917d(TAG, "Problem parsing map to JSON", e);
        }
        return validateLocalZipRequest;
    }
}

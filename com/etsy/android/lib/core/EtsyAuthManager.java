package com.etsy.android.lib.core;

import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import com.etsy.android.lib.util.bh;

/* renamed from: com.etsy.android.lib.core.f */
class EtsyAuthManager extends EtsyRequestJob<EmptyResult> {
    private EtsyAuthManager() {
    }

    protected EtsyRequest<EmptyResult> m1304a() {
        return new EtsyRequest("/access-tokens", RequestMethod.DELETE, EmptyResult.class);
    }

    protected void m1305a(EtsyResult etsyResult) {
        if (etsyResult.m1049a()) {
            EtsyDebug.m1896a(EtsyAuthManager.f1462a, "ApiKeyAccessToken revocation succeeded.");
        } else if (bh.m3340a(etsyResult.m1052c())) {
            EtsyDebug.m1919e(EtsyAuthManager.f1462a, "ApiKeyAccessToken revocation failed with error: " + etsyResult.m1052c());
        } else {
            EtsyDebug.m1919e(EtsyAuthManager.f1462a, "ApiKeyAccessToken revocation failed with unknown error.");
        }
    }
}

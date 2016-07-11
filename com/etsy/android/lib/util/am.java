package com.etsy.android.lib.util;

import com.etsy.android.lib.config.InstallInfo;
import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.requests.DeviceRequest;
import com.etsy.android.lib.requests.EtsyRequest;

/* compiled from: MessagingUtil */
class am extends EtsyRequestJob<EmptyResult> {
    private am() {
    }

    protected EtsyRequest<EmptyResult> m3255a() {
        return DeviceRequest.unregisterDevice(InstallInfo.m919a().m925b());
    }

    protected void m3256a(EtsyResult etsyResult) {
        if (etsyResult.m1049a()) {
            EtsyDebug.m1896a(ak.f2005a, "Notification unregister ID with Etsy successful.");
        } else if (bh.m3340a(etsyResult.m1052c())) {
            EtsyDebug.m1919e(ak.f2005a, "Notification unregister ID with Etsy failed with error: " + etsyResult.m1052c());
        } else {
            EtsyDebug.m1919e(ak.f2005a, "Notification unregister ID with Etsy failed.");
        }
    }
}

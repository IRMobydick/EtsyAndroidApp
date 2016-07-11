package com.etsy.android.lib.util;

import com.etsy.android.lib.config.InstallInfo;
import com.etsy.android.lib.core.EtsyAuthManager;
import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.requests.DeviceRequest;
import com.etsy.android.lib.requests.EtsyRequest;
import java.util.HashMap;
import java.util.Map;

/* compiled from: MessagingUtil */
class al extends EtsyRequestJob<EmptyResult> {
    private String f2006a;

    public al(String str) {
        this.f2006a = str;
    }

    protected EtsyRequest<EmptyResult> m3252a() {
        EtsyRequest registerDevice = DeviceRequest.registerDevice();
        Map hashMap = new HashMap();
        hashMap.put("notification_token", this.f2006a);
        hashMap.put("device_type", AppBuild.ANDROID_PLATFORM);
        hashMap.put("environment_id", EtsyAuthManager.m1168a());
        hashMap.put("app_identifier", InstallInfo.m919a().m930g());
        hashMap.put("device_udid", InstallInfo.m919a().m925b());
        hashMap.put("version", InstallInfo.m919a().m929f());
        registerDevice.addParams(hashMap);
        return registerDevice;
    }

    protected void m3253a(EtsyResult etsyResult) {
        if (etsyResult.m1049a()) {
            EtsyDebug.m1896a(ak.f2005a, "Notification register ID with Etsy successful.");
        } else if (bh.m3340a(etsyResult.m1052c())) {
            EtsyDebug.m1919e(ak.f2005a, "Notification register ID with Etsy failed with error: " + etsyResult.m1052c());
        } else {
            EtsyDebug.m1919e(ak.f2005a, "Notification register ID with Etsy failed.");
        }
    }
}

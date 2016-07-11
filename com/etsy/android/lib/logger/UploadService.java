package com.etsy.android.lib.logger;

import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.requests.AnalyticsRequest;
import com.etsy.android.lib.requests.EtsyRequest;

/* renamed from: com.etsy.android.lib.logger.y */
class UploadService extends EtsyRequestJob<EmptyResult> {
    private final String f1852a;

    private UploadService(String str) {
        this.f1852a = str;
    }

    protected EtsyRequest<EmptyResult> m2094a() {
        if (EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.bQ)) {
            return AnalyticsRequest.uploadDataCompressed(this.f1852a);
        }
        return AnalyticsRequest.uploadData(this.f1852a);
    }
}

package com.etsy.android.lib.core.http.url;

import android.support.annotation.NonNull;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.requests.HttpUtil;

/* renamed from: com.etsy.android.lib.core.http.url.d */
public final class EtsyApiV3Url extends EtsyApiUrl<EtsyApiV3Url, EtsyApiV3Url> {
    protected /* synthetic */ BaseHttpUrl m1544a() {
        return m1546d();
    }

    protected /* synthetic */ BaseHttpUrl m1545b() {
        return m1547e();
    }

    public EtsyApiV3Url(@NonNull String str) {
        super(EtsyConfig.m837a().m869d().m883b(EtsyConfigKeys.cw), EtsyApiV3Url.m1543a(str));
    }

    private static String m1543a(@NonNull String str) {
        String pathPart = HttpUtil.getPathPart(EtsyConfig.m837a().m869d().m883b(EtsyConfigKeys.cy));
        if (str.startsWith(pathPart)) {
            return str;
        }
        return pathPart + str;
    }

    protected EtsyApiV3Url m1546d() {
        return this;
    }

    protected EtsyApiV3Url m1547e() {
        return new EtsyApiV3Url();
    }
}

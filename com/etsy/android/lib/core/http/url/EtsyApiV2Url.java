package com.etsy.android.lib.core.http.url;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.HttpUtil;
import com.etsy.android.lib.util.CurrencyUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Locale;

/* renamed from: com.etsy.android.lib.core.http.url.c */
public final class EtsyApiV2Url extends EtsyApiUrl<EtsyApiV2Url, EtsyApiV2Url> {
    @Nullable
    private String f1600c;

    protected /* synthetic */ BaseHttpUrl m1537a() {
        return m1541d();
    }

    protected /* synthetic */ BaseHttpUrl m1539b() {
        return m1542e();
    }

    public EtsyApiV2Url(@NonNull String str) {
        super(EtsyConfig.m837a().m869d().m883b(EtsyConfigKeys.cw), EtsyApiV2Url.m1535c(str));
        m1536f();
    }

    public EtsyApiV2Url m1538a(String str) {
        m1527a("fields", str);
        return m1541d();
    }

    public EtsyApiV2Url m1540b(String str) {
        m1527a("includes", str);
        return m1541d();
    }

    @JsonIgnore
    private EtsyApiV2Url m1536f() {
        Locale locale = Locale.getDefault();
        m1527a(EtsyRequest.PARAM_CURRENCY, CurrencyUtil.m3091i());
        m1527a(EtsyRequest.PARAM_REGION, locale.getCountry());
        m1534a(locale);
        return m1541d();
    }

    private void m1534a(@NonNull Locale locale) {
        if (TextUtils.isEmpty(this.f1600c)) {
            m1527a(EtsyRequest.PARAM_LANGUAGE, locale.getLanguage());
            return;
        }
        EtsyDebug.m1906b(EtsyApiV2Url.f1593a, "Overriding device locale API language param with language: " + this.f1600c);
        m1527a(EtsyRequest.PARAM_LANGUAGE, this.f1600c);
    }

    private static String m1535c(@NonNull String str) {
        String pathPart = HttpUtil.getPathPart(EtsyConfig.m837a().m869d().m883b(EtsyConfigKeys.cx));
        if (str.startsWith(pathPart)) {
            return str;
        }
        return pathPart + str;
    }

    protected EtsyApiV2Url m1541d() {
        return this;
    }

    protected EtsyApiV2Url m1542e() {
        return new EtsyApiV2Url();
    }
}

package com.etsy.android.lib.core.http.request;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.android.volley.toolbox.BasicNetwork;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.config.InstallInfo;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.http.url.EtsyApiUrl;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.util.CurrencyUtil;
import java.util.Locale;

/* renamed from: com.etsy.android.lib.core.http.request.g */
public abstract class EtsyApiRequest<ResultType extends BaseModel, UrlBuilderTarget extends EtsyApiUrl, UrlBuilderClass extends EtsyApiUrl<UrlBuilderTarget, UrlBuilderClass>, RequestType extends EtsyApiRequest<ResultType, RequestType, ResultVersion, UrlBuilderTarget>, ResultVersion extends EtsyResult<ResultType>, BuilderClass extends EtsyApiRequest<ResultType, UrlBuilderTarget, UrlBuilderClass, RequestType, ResultVersion, BuilderClass>> extends BaseHttpRequest<ResultVersion, UrlBuilderTarget, UrlBuilderClass, RequestType, BuilderClass> {
    protected String f1585d;
    @NonNull
    private final Class<ResultType> f1586e;
    private boolean f1587f;

    protected EtsyApiRequest(@NonNull Class<ResultType> cls, @NonNull UrlBuilderClass urlBuilderClass) {
        super(urlBuilderClass);
        this.f1585d = null;
        this.f1587f = true;
        m1429f();
        this.f1586e = cls;
    }

    public BuilderClass m1432b(boolean z) {
        this.f1587f = z;
        return (EtsyApiRequest) m1390b();
    }

    public boolean m1434e() {
        return this.f1587f;
    }

    public BuilderClass m1431b(int i) {
        ((EtsyApiUrl) this.b).m1532a(i);
        return (EtsyApiRequest) m1390b();
    }

    public BuilderClass m1433c(int i) {
        ((EtsyApiUrl) this.b).m1533b(i);
        return (EtsyApiRequest) m1390b();
    }

    private void m1429f() {
        m1391b("User-agent", InstallInfo.m919a().m935l());
        m1391b("X-Etsy-Device", InstallInfo.m919a().m925b());
        m1430g();
        m1391b("Accept-Encoding", BasicNetwork.ENCODING_GZIP);
        if (EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.bC)) {
            m1391b(EtsyRequest.HEADER_X_EXPERIMENTAL_API, "true");
        }
    }

    private void m1430g() {
        String language;
        Locale locale = Locale.getDefault();
        if (TextUtils.isEmpty(this.f1585d)) {
            language = locale.getLanguage();
        } else {
            EtsyDebug.m1906b(EtsyApiRequest.f1557a, "Overriding device language on API request with: " + this.f1585d);
            language = this.f1585d;
        }
        m1391b("X-Detected-Locale", String.format("%s|%s|%s", new Object[]{CurrencyUtil.m3091i(), language, locale.getCountry()}));
    }
}

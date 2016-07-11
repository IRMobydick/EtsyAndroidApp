package com.etsy.android.lib.core;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.BasicNetwork;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.config.InstallInfo;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.HttpUtil;
import com.etsy.android.lib.util.CurrencyUtil;
import com.etsy.android.uikit.view.FullImageView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

@Deprecated
/* renamed from: com.etsy.android.lib.core.z */
public class HttpRequestJobBuilder<Result extends BaseModel> implements Method {
    private static final String f1673a;
    private final Map<String, String> f1674b;
    private final Map<String, String> f1675c;
    private final Map<String, String> f1676d;
    private String f1677e;
    private EtsyJobResponse<Result> f1678f;
    private EtsyJobResponse f1679g;
    private EtsyJobResponse f1680h;
    private ac f1681i;
    private ab<Result> f1682j;
    private String f1683k;
    private String f1684l;
    private int f1685m;
    private boolean f1686n;
    private boolean f1687o;
    private Class<Result> f1688p;
    private String f1689q;
    private String f1690r;
    private int f1691s;
    private int f1692t;
    private float f1693u;
    private boolean f1694v;

    static {
        f1673a = EtsyDebug.m1891a(HttpRequestJobBuilder.class);
    }

    public static <Result extends BaseModel> HttpRequestJobBuilder<Result> m1713a(Class<Result> cls, String str, int i) {
        return HttpRequestJobBuilder.m1714a(cls, str, i, null);
    }

    public static <Result extends BaseModel> HttpRequestJobBuilder<Result> m1712a(Class<Result> cls, String str) {
        return HttpRequestJobBuilder.m1714a(cls, str, 0, null);
    }

    public static <Result extends BaseModel> HttpRequestJobBuilder<Result> m1714a(Class<Result> cls, String str, int i, String str2) {
        return new HttpRequestJobBuilder(cls, EtsyConfig.m837a().m869d().m883b(EtsyConfigKeys.cw), str, i).m1715a(str2).m1716b().m1718c();
    }

    private HttpRequestJobBuilder(Class<Result> cls, String str, String str2, int i) {
        this.f1674b = new ArrayMap(0);
        this.f1675c = new ArrayMap(0);
        this.f1676d = new ArrayMap(5);
        this.f1685m = 0;
        this.f1686n = false;
        this.f1687o = true;
        this.f1689q = null;
        this.f1690r = HttpUtil.URL_FORM_CONTENT_TYPE;
        this.f1691s = DefaultRetryPolicy.DEFAULT_TIMEOUT_MS;
        this.f1692t = 1;
        this.f1693u = FullImageView.ASPECT_RATIO_SQUARE;
        this.f1694v = true;
        if (cls == null) {
            throw new IllegalArgumentException("responseClass can't be null.");
        } else if (str == null) {
            throw new IllegalArgumentException("host can't be null.");
        } else if (str2 == null) {
            throw new IllegalArgumentException("path can't be null.");
        } else {
            this.f1688p = cls;
            this.f1683k = str;
            this.f1684l = str2;
            this.f1685m = i;
        }
    }

    private HttpRequestJobBuilder<Result> m1716b() {
        this.f1676d.put("User-agent", InstallInfo.m919a().m935l());
        this.f1676d.put("X-Etsy-Device", InstallInfo.m919a().m925b());
        this.f1676d.put("X-Detected-Locale", EtsyRequest.getDetectedLocaleHttpHeaderValue(StringUtils.EMPTY));
        this.f1676d.put("Accept-Encoding", BasicNetwork.ENCODING_GZIP);
        if (EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.bC)) {
            this.f1676d.put(EtsyRequest.HEADER_X_EXPERIMENTAL_API, "true");
        }
        return this;
    }

    @JsonIgnore
    private HttpRequestJobBuilder<Result> m1718c() {
        if (this.f1684l.contains("/v2/")) {
            Locale locale = Locale.getDefault();
            this.f1674b.put(EtsyRequest.PARAM_CURRENCY, CurrencyUtil.m3091i());
            this.f1674b.put(EtsyRequest.PARAM_REGION, locale.getCountry());
            if (TextUtils.isEmpty(this.f1689q)) {
                this.f1674b.put(EtsyRequest.PARAM_LANGUAGE, locale.getLanguage());
            } else {
                EtsyDebug.m1906b(f1673a, "Overriding device locale API language param with language: " + this.f1689q);
                this.f1674b.put(EtsyRequest.PARAM_LANGUAGE, this.f1689q);
            }
            this.f1674b.put("api_key", EtsyConfig.m837a().m869d().m883b(EtsyConfigKeys.cI));
        }
        return this;
    }

    public HttpRequestJobBuilder<Result> m1744a(String str, String str2) {
        this.f1674b.put(str, str2);
        return this;
    }

    public HttpRequestJobBuilder<Result> m1745a(Map<String, String> map) {
        if (map != null) {
            this.f1674b.putAll(map);
        }
        return this;
    }

    public HttpRequestJobBuilder<Result> m1738a(int i) {
        if (!this.f1684l.contains("offset")) {
            this.f1674b.put("offset", String.valueOf(i));
        }
        return this;
    }

    public HttpRequestJobBuilder<Result> m1747b(int i) {
        if (!this.f1684l.contains("limit")) {
            this.f1674b.put("limit", String.valueOf(i));
        }
        return this;
    }

    public HttpRequestJobBuilder<Result> m1748b(String str, String str2) {
        this.f1675c.put(str, str2);
        return this;
    }

    public HttpRequestJobBuilder<Result> m1746a(boolean z) {
        this.f1686n = z;
        return this;
    }

    private HttpRequestJobBuilder<Result> m1715a(String str) {
        this.f1689q = str;
        return this;
    }

    public HttpRequestJobBuilder<Result> m1743a(EtsyJobResponse<Result> etsyJobResponse) {
        this.f1678f = etsyJobResponse;
        return this;
    }

    public HttpRequestJobBuilder<Result> m1741a(EtsyJobResponse etsyJobResponse) {
        this.f1679g = etsyJobResponse;
        return this;
    }

    public HttpRequestJobBuilder<Result> m1742a(EtsyJobResponse etsyJobResponse) {
        this.f1680h = etsyJobResponse;
        return this;
    }

    public HttpRequestJobBuilder<Result> m1740a(ac acVar) {
        this.f1681i = acVar;
        return this;
    }

    public HttpRequestJobBuilder<Result> m1739a(ab<Result> abVar) {
        this.f1682j = abVar;
        return this;
    }

    public EtsyRequestJob<Result> m1737a() {
        return new aa();
    }
}

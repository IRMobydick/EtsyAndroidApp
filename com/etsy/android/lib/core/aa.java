package com.etsy.android.lib.core;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Request.Priority;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.etsy.android.lib.core.p005a.EtsyV3Result;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.p010a.NetworkErrorLogger;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.HttpUtil;
import com.etsy.android.lib.toolbar.AdminToolbar;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* compiled from: HttpRequestJobBuilder */
class aa extends EtsyRequestJob<Result> {
    final /* synthetic */ HttpRequestJobBuilder f1410a;
    private final String f1411c;
    private String f1412d;

    /* renamed from: com.etsy.android.lib.core.aa.1 */
    class HttpRequestJobBuilder extends JsonRequest<EtsyResult<Result>> {
        final /* synthetic */ aa f1409a;

        HttpRequestJobBuilder(aa aaVar, int i, String str, String str2, Listener listener, ErrorListener errorListener) {
            this.f1409a = aaVar;
            super(i, str, str2, listener, errorListener);
        }

        public Response<EtsyResult<Result>> parseNetworkResponse(NetworkResponse networkResponse) {
            EtsyResult etsyResult;
            if (this.f1409a.f1410a.f1684l.contains("/v2/")) {
                etsyResult = new EtsyResult(networkResponse, this.f1409a.f1410a.f1688p);
            } else {
                etsyResult = new EtsyV3Result(networkResponse, this.f1409a.f1410a.f1688p);
            }
            AdminToolbar.m2991a(getUrl(), networkResponse);
            if (!etsyResult.m1049a()) {
                NetworkErrorLogger.m1819a(networkResponse, etsyResult.m1052c(), this.f1409a.f1410a.f1688p != null ? this.f1409a.f1410a.f1688p.getSimpleName() : StringUtils.EMPTY, getUrl());
            }
            this.f1409a.m1077b(etsyResult);
            return Response.success(etsyResult, HttpHeaderParser.parseCacheHeaders(networkResponse));
        }

        public Map<String, String> getHeaders() {
            Map<String, String> a = AuthHelper.m1162a(getUrl(), this.f1409a.f1412d, this.f1409a.f1410a.f1685m, this.f1409a.f1410a.f1694v, this.f1409a.f1410a.f1687o);
            a.putAll(this.f1409a.f1410a.f1676d);
            return a;
        }

        public String getBodyContentType() {
            return this.f1409a.f1410a.f1690r;
        }

        public Priority getPriority() {
            return Priority.NORMAL;
        }
    }

    private aa(HttpRequestJobBuilder httpRequestJobBuilder) {
        this.f1410a = httpRequestJobBuilder;
        this.f1411c = EtsyDebug.m1891a(aa.class);
    }

    protected void b_() {
        super.b_();
        if (this.f1410a.f1681i != null) {
            this.f1410a.f1681i.m1080a();
        }
    }

    protected EtsyRequest<Result> m1074a() {
        return null;
    }

    protected Request<EtsyResult<Result>> m1078c() {
        this.b.m1677a(this.f1410a.f1688p);
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.f1410a.f1683k);
            stringBuilder.append(this.f1410a.f1684l);
            String createUrlEncodingParams = HttpUtil.createUrlEncodingParams(this.f1410a.f1674b);
            this.f1412d = StringUtils.EMPTY;
            if (!createUrlEncodingParams.isEmpty()) {
                if (this.f1410a.f1684l.indexOf(63) == -1) {
                    this.f1412d = "?";
                } else if (this.f1410a.f1684l.charAt(this.f1410a.f1684l.length() - 1) != '&') {
                    this.f1412d = "&";
                }
                this.f1412d += createUrlEncodingParams;
                stringBuilder.append(this.f1412d);
            }
            Request httpRequestJobBuilder = new HttpRequestJobBuilder(this, this.f1410a.f1685m, stringBuilder.toString(), HttpUtil.createBody(this.f1410a.f1677e, this.f1410a.f1675c, this.f1410a.f1690r), m704d(), m705e());
            EtsyDebug.m1914c(this.f1411c, "onCreateVolleyRequest - METHOD:%s URL:%s", HttpUtil.getRequestMethodString(this.f1410a.f1685m), r3);
            AdminToolbar.m2992a(HttpUtil.getRequestMethodString(this.f1410a.f1685m), httpRequestJobBuilder.getUrl());
            httpRequestJobBuilder.setShouldCache(this.f1410a.f1686n);
            httpRequestJobBuilder.setTag(m706f());
            httpRequestJobBuilder.setRetryPolicy(new DefaultRetryPolicy(this.f1410a.f1691s, this.f1410a.f1692t, this.f1410a.f1693u));
            return httpRequestJobBuilder;
        } catch (Throwable e) {
            EtsyDebug.m1917d(this.f1411c, "Error - createVolleyRequest - request will be skipped", e);
            return null;
        }
    }

    protected void m1077b(EtsyResult<Result> etsyResult) {
        super.m719b((EtsyResult) etsyResult);
        if (this.f1410a.f1682j != null) {
            this.f1410a.f1682j.m1079a(etsyResult);
        }
    }

    protected void m1075a(EtsyResult<Result> etsyResult) {
        super.m717a((EtsyResult) etsyResult);
        if (etsyResult.m1049a()) {
            if (etsyResult.m1058i()) {
                if (this.f1410a.f1678f != null) {
                    this.f1410a.f1678f.m696a(etsyResult.m1056g(), etsyResult.m1055f(), etsyResult);
                }
            } else if (this.f1410a.f1679g != null) {
                this.f1410a.f1679g.m694a(etsyResult);
            }
        } else if (this.f1410a.f1680h != null) {
            this.f1410a.f1680h.m692a(etsyResult.m1036q(), etsyResult.m1052c(), etsyResult);
        }
    }
}

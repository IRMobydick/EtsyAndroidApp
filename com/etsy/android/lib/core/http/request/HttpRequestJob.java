package com.etsy.android.lib.core.http.request;

import android.support.annotation.NonNull;
import com.etsy.android.lib.core.http.HttpResult;
import com.etsy.android.lib.core.http.url.HttpUrl;

/* renamed from: com.etsy.android.lib.core.http.request.u */
public final class HttpRequestJob extends BaseHttpRequestJob<HttpRequest, HttpResult, HttpUrl, HttpRequestJob, HttpRequestJob> {
    protected /* synthetic */ BaseHttpRequestJob m1475a() {
        return m1477d();
    }

    protected /* synthetic */ BaseHttpRequestJob m1476b() {
        return m1478e();
    }

    public HttpRequestJob(@NonNull HttpRequest httpRequest) {
        super(httpRequest);
    }

    protected HttpRequestJob m1477d() {
        return this;
    }

    protected HttpRequestJob m1478e() {
        return new HttpRequestJob(this);
    }
}

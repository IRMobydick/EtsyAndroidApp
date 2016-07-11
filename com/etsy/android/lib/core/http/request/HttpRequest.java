package com.etsy.android.lib.core.http.request;

import com.etsy.android.lib.core.http.HttpResult;
import com.etsy.android.lib.core.http.url.HttpUrl;

/* renamed from: com.etsy.android.lib.core.http.request.s */
public class HttpRequest extends BaseHttpRequest<HttpResult, HttpUrl, HttpUrl, HttpRequest, HttpRequest> {
    protected /* synthetic */ BaseHttpRequest m1469b() {
        return m1471e();
    }

    protected /* synthetic */ BaseHttpRequest m1470c() {
        return m1472f();
    }

    protected HttpRequest m1471e() {
        return this;
    }

    protected HttpRequest m1472f() {
        return new HttpRequest(this);
    }
}

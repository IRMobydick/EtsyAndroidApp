package com.etsy.android.lib.core.http.request;

import android.support.annotation.CallSuper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Request.Priority;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.etsy.android.lib.core.http.HttpResult;
import com.etsy.android.lib.core.http.body.BaseHttpBody;
import com.etsy.android.lib.core.http.url.BaseHttpUrl;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.p010a.NetworkErrorLogger;
import com.etsy.android.lib.toolbar.AdminToolbar;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.etsy.android.lib.core.http.request.b */
public abstract class BaseHttpRequestJob<RequestType extends BaseHttpRequest<RequestType, ResultType, UrlBuilderTarget>, ResultType extends HttpResult, UrlBuilderTarget extends BaseHttpUrl> extends Request<ResultType> {
    private static final String f1572c;
    @NonNull
    protected final RequestType f1573a;
    @NonNull
    protected Priority f1574b;
    @NonNull
    private final Map<BaseHttpRequestJob<ResultType>, BaseHttpRequestJob> f1575d;

    protected abstract ResultType m1407a(@NonNull NetworkResponse networkResponse);

    protected abstract ResultType m1408a(@NonNull VolleyError volleyError);

    protected /* synthetic */ void deliverResponse(Object obj) {
        m1410a((HttpResult) obj);
    }

    static {
        f1572c = EtsyDebug.m1891a(BaseHttpRequestJob.class);
    }

    protected BaseHttpRequestJob(@NonNull BaseHttpRequestJob<RequestType, ResultType, UrlBuilderTarget, ?, ?> baseHttpRequestJob) {
        super(baseHttpRequestJob.f1578a.getRequestMethod(), baseHttpRequestJob.f1578a.getUrl(), null);
        this.f1574b = Priority.NORMAL;
        this.f1573a = baseHttpRequestJob.f1578a;
        this.f1575d = baseHttpRequestJob.f1580c;
        this.f1574b = baseHttpRequestJob.f1579b;
        setRetryPolicy(new DefaultRetryPolicy(baseHttpRequestJob.f1581f, baseHttpRequestJob.f1582g, baseHttpRequestJob.f1583h));
        setShouldCache(baseHttpRequestJob.f1584i);
    }

    public final Response<ResultType> parseNetworkResponse(@NonNull NetworkResponse networkResponse) {
        EtsyDebug.m1912c(f1572c, "parseNetworkResponse for " + this.f1573a.getUrl());
        HttpResult a = m1407a(networkResponse);
        AdminToolbar.m2991a(getUrl(), networkResponse);
        if (!a.m1034b()) {
            NetworkErrorLogger.m1820a(a, getUrl());
        }
        return Response.success(a, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }

    @CallSuper
    @NonNull
    public Map<String, String> getHeaders() {
        return this.f1573a.getHeaders();
    }

    @NonNull
    public final RequestType m1409a() {
        return this.f1573a;
    }

    @NonNull
    public final String getBodyContentType() {
        String bodyContentType = this.f1573a.getBodyContentType();
        if (bodyContentType == null) {
            return super.getBodyContentType();
        }
        return bodyContentType;
    }

    @NonNull
    public Priority getPriority() {
        return this.f1574b;
    }

    @MainThread
    protected final void m1410a(ResultType resultType) {
        for (Entry entry : this.f1575d.entrySet()) {
            ((BaseHttpRequestJob) entry.getValue()).m1331a((BaseHttpRequestJob) entry.getKey(), resultType);
        }
    }

    @MainThread
    public final void deliverError(VolleyError volleyError) {
        m1411b(volleyError);
    }

    public final ResultType m1411b(VolleyError volleyError) {
        ResultType a = m1408a(volleyError);
        NetworkErrorLogger.m1820a(a, getUrl());
        for (Entry entry : this.f1575d.entrySet()) {
            ((BaseHttpRequestJob) entry.getValue()).m1331a((BaseHttpRequestJob) entry.getKey(), a);
        }
        return a;
    }

    protected final VolleyError parseNetworkError(VolleyError volleyError) {
        return super.parseNetworkError(volleyError);
    }

    public final int getMethod() {
        return this.f1573a.getRequestMethod();
    }

    public final String getUrl() {
        return this.f1573a.getUrl();
    }

    public byte[] getBody() {
        byte[] bArr = null;
        BaseHttpBody body = this.f1573a.getBody();
        if (body != null) {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                body.writeBody(byteArrayOutputStream);
                bArr = byteArrayOutputStream.toByteArray();
            } catch (Throwable e) {
                EtsyDebug.m1917d(f1572c, "Error writing full body; sent nothing", e);
            }
        }
        return bArr;
    }

    public final void cancel() {
        super.cancel();
    }

    public final boolean isCanceled() {
        return super.isCanceled();
    }

    @Deprecated
    public final void addMarker(String str) {
        super.addMarker(str);
    }

    @Deprecated
    public final Request<?> setCacheEntry(Cache.Entry entry) {
        return super.setCacheEntry(entry);
    }

    @Deprecated
    public final Cache.Entry getCacheEntry() {
        return super.getCacheEntry();
    }

    @Deprecated
    public final RetryPolicy getRetryPolicy() {
        return super.getRetryPolicy();
    }

    @Deprecated
    public final void markDelivered() {
        super.markDelivered();
    }

    @Deprecated
    public final boolean hasHadResponseDelivered() {
        return super.hasHadResponseDelivered();
    }

    @Deprecated
    public final ErrorListener getErrorListener() {
        return super.getErrorListener();
    }
}

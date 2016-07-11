package com.etsy.android.lib.core.http;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.etsy.android.lib.core.http.request.BaseHttpRequestJob;

/* renamed from: com.etsy.android.lib.core.http.b */
public class VolleyResultHandler<RequestResult extends HttpResult> extends BaseHttpRequestJob<RequestResult> {
    @NonNull
    private final Listener<RequestResult> f1541a;
    @Nullable
    private final ErrorListener f1542b;

    public VolleyResultHandler(@NonNull Listener<RequestResult> listener, @Nullable ErrorListener errorListener) {
        this.f1541a = listener;
        this.f1542b = errorListener;
    }

    public void m1337a(@NonNull RequestResult requestResult) {
        if (requestResult.m1034b()) {
            this.f1541a.onResponse(requestResult);
        } else if (this.f1542b != null) {
            this.f1542b.onErrorResponse(requestResult.m1037r());
        }
    }
}

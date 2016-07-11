package com.etsy.android.lib.core.http.request;

import android.support.annotation.NonNull;
import com.etsy.android.lib.core.http.url.IchtApiUrl;
import com.etsy.android.lib.core.p005a.EtsyV3Result;
import com.etsy.android.lib.models.BaseModel;

/* renamed from: com.etsy.android.lib.core.http.request.v */
public final class IchtApiRequest<ResultType extends BaseModel> extends BaseHttpRequest<EtsyV3Result<ResultType>, IchtApiUrl, IchtApiUrl, IchtApiRequest<ResultType>, IchtApiRequest<ResultType>> {
    @NonNull
    private final Class<ResultType> f1590d;

    protected /* synthetic */ BaseHttpRequest m1480b() {
        return m1483f();
    }

    protected /* synthetic */ BaseHttpRequest m1481c() {
        return m1482e();
    }

    protected IchtApiRequest<ResultType> m1482e() {
        return new IchtApiRequest();
    }

    protected IchtApiRequest<ResultType> m1483f() {
        return this;
    }
}

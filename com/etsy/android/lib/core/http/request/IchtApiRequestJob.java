package com.etsy.android.lib.core.http.request;

import android.support.annotation.NonNull;
import com.etsy.android.lib.core.http.url.IchtApiUrl;
import com.etsy.android.lib.core.p005a.EtsyV3Result;
import com.etsy.android.lib.models.BaseModel;

/* renamed from: com.etsy.android.lib.core.http.request.x */
public final class IchtApiRequestJob<ResultType extends BaseModel> extends BaseHttpRequestJob<IchtApiRequest<ResultType>, EtsyV3Result<ResultType>, IchtApiUrl, IchtApiRequestJob<ResultType>, IchtApiRequestJob<ResultType>> {
    protected /* synthetic */ BaseHttpRequestJob m1489a() {
        return m1491d();
    }

    protected /* synthetic */ BaseHttpRequestJob m1490b() {
        return m1492e();
    }

    protected IchtApiRequestJob(@NonNull IchtApiRequest<ResultType> ichtApiRequest) {
        super(ichtApiRequest);
    }

    protected IchtApiRequestJob<ResultType> m1491d() {
        return this;
    }

    public static <Result extends BaseModel> IchtApiRequestJob<Result> m1488a(IchtApiRequest<Result> ichtApiRequest) {
        return new IchtApiRequestJob(ichtApiRequest);
    }

    protected IchtApiRequestJob<ResultType> m1492e() {
        return new IchtApiRequestJob();
    }
}

package com.etsy.android.lib.core.http.request;

import android.support.annotation.NonNull;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.http.url.EtsyApiV2Url;
import com.etsy.android.lib.models.BaseModel;

/* renamed from: com.etsy.android.lib.core.http.request.m */
public class EtsyApiV2RequestJob<ResultType extends BaseModel> extends EtsyApiRequestJob<EtsyApiV2Request<ResultType>, ResultType, EtsyResult<ResultType>, EtsyApiV2Url, EtsyApiV2RequestJob<ResultType>, EtsyApiV2RequestJob<ResultType>> {
    protected /* synthetic */ BaseHttpRequestJob m1450a() {
        return m1452d();
    }

    protected /* synthetic */ BaseHttpRequestJob m1451b() {
        return m1453e();
    }

    protected EtsyApiV2RequestJob(@NonNull EtsyApiV2Request<ResultType> etsyApiV2Request) {
        super(etsyApiV2Request);
    }

    public static <Result extends BaseModel> EtsyApiV2RequestJob<Result> m1449a(EtsyApiV2Request<Result> etsyApiV2Request) {
        return new EtsyApiV2RequestJob(etsyApiV2Request);
    }

    protected EtsyApiV2RequestJob<ResultType> m1452d() {
        return this;
    }

    protected EtsyApiV2RequestJob<ResultType> m1453e() {
        return new EtsyApiV2RequestJob();
    }
}

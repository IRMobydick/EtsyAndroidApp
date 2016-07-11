package com.etsy.android.lib.core.http.request;

import android.support.annotation.NonNull;
import com.etsy.android.lib.core.http.url.EtsyApiV3Url;
import com.etsy.android.lib.core.p005a.EtsyV3Result;
import com.etsy.android.lib.models.BaseModel;

/* compiled from: PersistentEtsyApiV3Request */
public class aa<ResultType extends BaseModel> extends PersistentEtsyApiRequest<EtsyApiV3Request<ResultType>, ResultType, EtsyV3Result<ResultType>, EtsyApiV3Url, PersistentEtsyApiV3Request<ResultType>, aa<ResultType>> {
    protected /* synthetic */ BasePersistentHttpRequest m1401a() {
        return m1402c();
    }

    protected aa(@NonNull EtsyApiV3Request<ResultType> etsyApiV3Request) {
        super(etsyApiV3Request);
    }

    public static <Result extends BaseModel> aa<Result> m1400a(EtsyApiV3Request<Result> etsyApiV3Request) {
        return new aa(etsyApiV3Request);
    }

    protected PersistentEtsyApiV3Request<ResultType> m1402c() {
        return new PersistentEtsyApiV3Request();
    }
}

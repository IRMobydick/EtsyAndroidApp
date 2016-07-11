package com.etsy.android.lib.core.http.request;

import android.support.annotation.NonNull;
import com.etsy.android.lib.core.http.url.EtsyApiV3Url;
import com.etsy.android.lib.core.p005a.EtsyV3Result;
import com.etsy.android.lib.models.BaseModel;

/* renamed from: com.etsy.android.lib.core.http.request.o */
public final class EtsyApiV3Request<ResultType extends BaseModel> extends EtsyApiRequest<ResultType, EtsyApiV3Url, EtsyApiV3Url, EtsyApiV3Request<ResultType>, EtsyV3Result<ResultType>, EtsyApiV3Request<ResultType>> {
    protected /* synthetic */ BaseHttpRequest m1455b() {
        return m1459g();
    }

    protected /* synthetic */ BaseHttpRequest m1456c() {
        return m1458f();
    }

    public /* bridge */ /* synthetic */ boolean m1457e() {
        return super.m1434e();
    }

    public EtsyApiV3Request(Class<ResultType> cls, String str) {
        this((Class) cls, new EtsyApiV3Url(str));
    }

    public EtsyApiV3Request(@NonNull Class<ResultType> cls, @NonNull EtsyApiV3Url etsyApiV3Url) {
        super(cls, etsyApiV3Url);
    }

    public static <T extends BaseModel> EtsyApiV3Request<T> m1454a(Class<T> cls, String str) {
        return new EtsyApiV3Request((Class) cls, str);
    }

    protected EtsyApiV3Request<ResultType> m1458f() {
        return new EtsyApiV3Request();
    }

    protected EtsyApiV3Request<ResultType> m1459g() {
        return this;
    }
}

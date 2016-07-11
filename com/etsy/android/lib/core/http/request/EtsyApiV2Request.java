package com.etsy.android.lib.core.http.request;

import android.support.annotation.NonNull;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.AuthHelper;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.http.url.EtsyApiV2Url;
import com.etsy.android.lib.models.BaseModel;

/* renamed from: com.etsy.android.lib.core.http.request.k */
public final class EtsyApiV2Request<ResultType extends BaseModel> extends EtsyApiRequest<ResultType, EtsyApiV2Url, EtsyApiV2Url, EtsyApiV2Request<ResultType>, EtsyResult<ResultType>, EtsyApiV2Request<ResultType>> {
    protected /* synthetic */ BaseHttpRequest m1436b() {
        return m1443g();
    }

    protected /* synthetic */ BaseHttpRequest m1438c() {
        return m1442f();
    }

    public /* synthetic */ BaseHttpRequest m1440d() {
        return m1444h();
    }

    public /* bridge */ /* synthetic */ boolean m1441e() {
        return super.m1434e();
    }

    public EtsyApiV2Request(Class<ResultType> cls, String str) {
        this((Class) cls, new EtsyApiV2Url(str));
    }

    public EtsyApiV2Request(@NonNull Class<ResultType> cls, @NonNull EtsyApiV2Url etsyApiV2Url) {
        super(cls, etsyApiV2Url);
    }

    public static <T extends BaseModel> EtsyApiV2Request<T> m1435a(Class<T> cls, String str) {
        return new EtsyApiV2Request((Class) cls, str);
    }

    public EtsyApiV2Request<ResultType> m1437b(String str) {
        ((EtsyApiV2Url) this.b).m1538a(str);
        return m1443g();
    }

    public EtsyApiV2Request<ResultType> m1439c(String str) {
        ((EtsyApiV2Url) this.b).m1540b(str);
        return m1443g();
    }

    protected EtsyApiV2Request<ResultType> m1442f() {
        return new EtsyApiV2Request();
    }

    protected EtsyApiV2Request<ResultType> m1443g() {
        return this;
    }

    public EtsyApiV2Request<ResultType> m1444h() {
        if (!m1441e() || AuthHelper.m1165c() == null) {
            m1385a("api_key", EtsyConfig.m837a().m869d().m883b(EtsyConfigKeys.cI));
        }
        return (EtsyApiV2Request) super.m1393d();
    }
}

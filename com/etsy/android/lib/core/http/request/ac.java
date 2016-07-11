package com.etsy.android.lib.core.http.request;

import com.etsy.android.lib.core.http.url.IchtApiUrl;
import com.etsy.android.lib.core.p005a.EtsyV3Result;
import com.etsy.android.lib.models.BaseModel;

/* compiled from: PersistentIchtApiRequest */
public class ac<ResultType extends BaseModel> extends BasePersistentHttpRequest<IchtApiRequest<ResultType>, EtsyV3Result<ResultType>, IchtApiUrl, PersistentIchtApiRequest<ResultType>, ac<ResultType>> {
    protected /* synthetic */ BasePersistentHttpRequest m1405a() {
        return m1406c();
    }

    protected PersistentIchtApiRequest<ResultType> m1406c() {
        return new PersistentIchtApiRequest();
    }
}

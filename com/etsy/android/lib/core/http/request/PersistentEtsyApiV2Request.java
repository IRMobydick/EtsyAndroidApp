package com.etsy.android.lib.core.http.request;

import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.http.url.EtsyApiV2Url;
import com.etsy.android.lib.models.BaseModel;

/* renamed from: com.etsy.android.lib.core.http.request.z */
public class PersistentEtsyApiV2Request<ResultType extends BaseModel> extends PersistentEtsyApiRequest<EtsyApiV2Request<ResultType>, ResultType, EtsyResult<ResultType>, EtsyApiV2Url, PersistentEtsyApiV2Request<ResultType>, PersistentEtsyApiV2Request<ResultType>> {
    protected /* synthetic */ BasePersistentHttpRequest m1493a() {
        return m1494c();
    }

    protected PersistentEtsyApiV2Request<ResultType> m1494c() {
        return new PersistentEtsyApiV2Request();
    }
}

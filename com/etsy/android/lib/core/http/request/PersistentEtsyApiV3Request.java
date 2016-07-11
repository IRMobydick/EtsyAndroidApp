package com.etsy.android.lib.core.http.request;

import com.etsy.android.lib.core.http.request.PersistentEtsyApiRequest.PersistentEtsyApiResultHandler;
import com.etsy.android.lib.core.http.url.EtsyApiV3Url;
import com.etsy.android.lib.core.p005a.EtsyV3Result;
import com.etsy.android.lib.models.BaseModel;

public class PersistentEtsyApiV3Request<ResultType extends BaseModel> extends PersistentEtsyApiRequest<ResultType, EtsyApiV3Request<ResultType>, EtsyV3Result<ResultType>, EtsyApiV3Url> {
    private static final long serialVersionUID = -7451023050237893062L;

    public abstract class PersistentEtsyApiV3ResultHandler<ResultType extends BaseModel> extends PersistentEtsyApiResultHandler<ResultType, EtsyV3Result<ResultType>> {
        private static final long serialVersionUID = 6115275238933827881L;
    }

    private PersistentEtsyApiV3Request(aa<ResultType> aaVar) {
        super(aaVar);
    }
}

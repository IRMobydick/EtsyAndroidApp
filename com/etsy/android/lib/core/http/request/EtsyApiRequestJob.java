package com.etsy.android.lib.core.http.request;

import android.support.annotation.NonNull;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.http.url.EtsyApiUrl;
import com.etsy.android.lib.models.BaseModel;

/* renamed from: com.etsy.android.lib.core.http.request.i */
public abstract class EtsyApiRequestJob<RequestType extends EtsyApiRequest<ResultType, RequestType, ResultVersion, UrlBuilderTarget>, ResultType extends BaseModel, ResultVersion extends EtsyResult<ResultType>, UrlBuilderTarget extends EtsyApiUrl, BuilderTarget extends EtsyApiRequestJob<RequestType, ResultType, ResultVersion, UrlBuilderTarget>, BuilderClass extends EtsyApiRequestJob<RequestType, ResultType, ResultVersion, UrlBuilderTarget, BuilderTarget, BuilderClass>> extends BaseHttpRequestJob<RequestType, ResultVersion, UrlBuilderTarget, BuilderTarget, BuilderClass> {
    protected EtsyApiRequestJob(@NonNull RequestType requestType) {
        super(requestType);
    }
}

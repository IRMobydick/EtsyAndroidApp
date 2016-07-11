package com.etsy.android.lib.core.http.request;

import android.support.annotation.NonNull;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.http.url.EtsyApiUrl;
import com.etsy.android.lib.models.BaseModel;

/* renamed from: com.etsy.android.lib.core.http.request.y */
public abstract class PersistentEtsyApiRequest<RequestType extends EtsyApiRequest<ResultType, RequestType, ResultVersion, UrlBuilderTarget>, ResultType extends BaseModel, ResultVersion extends EtsyResult<ResultType>, UrlBuilderTarget extends EtsyApiUrl, BuilderTarget extends PersistentEtsyApiRequest<ResultType, RequestType, ResultVersion, UrlBuilderTarget>, BuilderClass extends PersistentEtsyApiRequest<RequestType, ResultType, ResultVersion, UrlBuilderTarget, BuilderTarget, BuilderClass>> extends BasePersistentHttpRequest<RequestType, ResultVersion, UrlBuilderTarget, BuilderTarget, BuilderClass> {
    protected PersistentEtsyApiRequest(@NonNull RequestType requestType) {
        super(requestType);
    }
}

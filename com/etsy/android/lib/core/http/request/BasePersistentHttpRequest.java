package com.etsy.android.lib.core.http.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.core.http.HttpResult;
import com.etsy.android.lib.core.http.url.BaseHttpUrl;
import com.etsy.android.lib.core.posts.PostInfo;

/* renamed from: com.etsy.android.lib.core.http.request.f */
public abstract class BasePersistentHttpRequest<RequestType extends BaseHttpRequest<RequestType, ResultType, UrlBuilderTarget>, ResultType extends HttpResult, UrlBuilderTarget extends BaseHttpUrl, BuilderTarget extends BasePersistentHttpRequest<RequestType, ResultType, UrlBuilderTarget>, BuilderClass extends BasePersistentHttpRequest<RequestType, ResultType, UrlBuilderTarget, BuilderTarget, BuilderClass>> {
    @NonNull
    private RequestType f1567a;
    @Nullable
    private PostInfo f1568b;
    @Nullable
    private OnPersistentResultHandler<ResultType> f1569c;
    @Nullable
    private OnPersistedHandler f1570d;
    private int f1571e;

    protected abstract BuilderTarget m1398a();

    protected BasePersistentHttpRequest(@NonNull RequestType requestType) {
        this.f1571e = 0;
        this.f1567a = requestType;
    }

    public final BuilderTarget m1399b() {
        return m1398a();
    }
}

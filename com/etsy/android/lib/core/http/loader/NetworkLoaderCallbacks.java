package com.etsy.android.lib.core.http.loader;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import com.etsy.android.lib.core.http.HttpResult;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;

/* renamed from: com.etsy.android.lib.core.http.loader.c */
public class NetworkLoaderCallbacks<ResultType extends HttpResult> {
    @NonNull
    private final BaseHttpRequest<?, ResultType, ?> f1551a;
    @Nullable
    private NetworkLoader<ResultType> f1552b;
    private final int f1553c;
    @NonNull
    private final LoaderManager f1554d;

    public NetworkLoaderCallbacks(int i, @NonNull LoaderManager loaderManager, @NonNull BaseHttpRequest<?, ResultType, ?> baseHttpRequest) {
        this.f1553c = i;
        this.f1554d = loaderManager;
        this.f1551a = baseHttpRequest;
    }

    @NonNull
    public NetworkLoaderCallbacks<ResultType> m1377a(@Nullable NetworkLoader<ResultType> networkLoader) {
        this.f1552b = networkLoader;
        return this;
    }

    public final NetworkLoaderCallbacks<ResultType> m1376a() {
        return new NetworkLoaderCallbacks();
    }
}

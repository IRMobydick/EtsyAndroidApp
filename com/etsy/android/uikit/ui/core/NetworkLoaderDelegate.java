package com.etsy.android.uikit.ui.core;

import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import com.etsy.android.lib.core.http.HttpResult;
import com.etsy.android.lib.core.http.loader.NetworkLoader;
import com.etsy.android.lib.core.http.loader.NetworkLoaderCallbacks;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.android.lib.logger.EtsyDebug;

/* renamed from: com.etsy.android.uikit.ui.core.b */
public class NetworkLoaderDelegate {
    private static final String f4076a;

    static {
        f4076a = EtsyDebug.m1891a(NetworkLoaderDelegate.class);
    }

    public static <ResultType extends HttpResult> void m5448a(@NonNull LoaderManager loaderManager, int i, @NonNull BaseHttpRequest<?, ResultType, ?> baseHttpRequest, @NonNull NetworkLoader<ResultType> networkLoader) {
        Loader initLoader = loaderManager.initLoader(i, null, new NetworkLoaderCallbacks(i, loaderManager, baseHttpRequest).m1377a((NetworkLoader) networkLoader).m1376a());
        String str = f4076a;
        String str2 = "loadDataFromNetwork: got loader (%d)";
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(initLoader == null ? 0 : initLoader.hashCode());
        EtsyDebug.m1914c(str, str2, objArr);
    }
}

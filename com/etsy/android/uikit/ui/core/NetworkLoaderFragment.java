package com.etsy.android.uikit.ui.core;

import android.support.annotation.NonNull;
import com.etsy.android.lib.core.http.HttpResult;
import com.etsy.android.lib.core.http.loader.NetworkLoader;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;

public abstract class NetworkLoaderFragment extends TrackingBaseFragment {
    public <ResultType extends HttpResult> void loadDataFromNetwork(int i, @NonNull BaseHttpRequest<?, ResultType, ?> baseHttpRequest, @NonNull NetworkLoader<ResultType> networkLoader) {
        NetworkLoaderDelegate.m5448a(getLoaderManager(), i, baseHttpRequest, networkLoader);
    }
}

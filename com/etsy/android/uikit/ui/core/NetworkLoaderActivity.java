package com.etsy.android.uikit.ui.core;

import android.support.annotation.NonNull;
import com.etsy.android.lib.core.http.HttpResult;
import com.etsy.android.lib.core.http.loader.NetworkLoader;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.android.uikit.nav.TrackingBaseActivity;

public abstract class NetworkLoaderActivity extends TrackingBaseActivity {
    public <ResultType extends HttpResult> void loadDataFromNetwork(int i, @NonNull BaseHttpRequest<?, ResultType, ?> baseHttpRequest, @NonNull NetworkLoader<ResultType> networkLoader) {
        NetworkLoaderDelegate.m5448a(getSupportLoaderManager(), i, baseHttpRequest, networkLoader);
    }
}

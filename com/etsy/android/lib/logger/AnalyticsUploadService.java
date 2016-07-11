package com.etsy.android.lib.logger;

import android.support.annotation.NonNull;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.ServerError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.etsy.android.lib.requests.GZippedJsonRequest;

/* renamed from: com.etsy.android.lib.logger.e */
class AnalyticsUploadService extends GZippedJsonRequest<Boolean> {
    public AnalyticsUploadService(@NonNull String str, @NonNull String str2, @NonNull Listener<Boolean> listener, @NonNull ErrorListener errorListener) {
        super(1, str, str2, listener, errorListener);
    }

    public Response<Boolean> parseNetworkResponse(@NonNull NetworkResponse networkResponse) {
        if (networkResponse.statusCode < Callback.DEFAULT_DRAG_ANIMATION_DURATION || networkResponse.statusCode >= 300) {
            return Response.error(new ServerError());
        }
        return Response.success(Boolean.valueOf(true), HttpHeaderParser.parseCacheHeaders(networkResponse));
    }
}

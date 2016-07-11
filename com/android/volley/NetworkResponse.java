package com.android.volley;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import java.util.Collections;
import java.util.Map;

public class NetworkResponse {
    public final byte[] data;
    public final Map<String, String> headers;
    public final boolean notModified;
    public final int statusCode;

    public NetworkResponse(int i, byte[] bArr, Map<String, String> map, boolean z) {
        this.statusCode = i;
        this.data = bArr;
        this.headers = map;
        this.notModified = z;
    }

    public NetworkResponse(byte[] bArr) {
        this(Callback.DEFAULT_DRAG_ANIMATION_DURATION, bArr, Collections.emptyMap(), false);
    }

    public NetworkResponse(byte[] bArr, Map<String, String> map) {
        this(Callback.DEFAULT_DRAG_ANIMATION_DURATION, bArr, map, false);
    }
}

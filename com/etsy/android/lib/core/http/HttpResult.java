package com.etsy.android.lib.core.http;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.etsy.android.lib.logger.EtsyDebug;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.lib.core.http.a */
public class HttpResult {
    private static final String f1386a;
    private VolleyError f1387b;
    @Nullable
    private NetworkResponse f1388c;

    static {
        f1386a = EtsyDebug.m1891a(HttpResult.class);
    }

    public HttpResult(@Nullable VolleyError volleyError) {
        this.f1387b = volleyError;
        EtsyDebug.m1917d(f1386a, "Volley error : parseError", (Throwable) volleyError);
        if (volleyError != null && volleyError.networkResponse != null) {
            this.f1388c = volleyError.networkResponse;
        }
    }

    public HttpResult(@Nullable NetworkResponse networkResponse) {
        this.f1388c = networkResponse;
    }

    public int m1036q() {
        if (this.f1388c != null) {
            return this.f1388c.statusCode;
        }
        return -1;
    }

    @Nullable
    public final VolleyError m1037r() {
        return this.f1387b;
    }

    @Nullable
    protected final NetworkResponse m1038s() {
        return this.f1388c;
    }

    @NonNull
    public final String m1039t() {
        if (m1037r() != null) {
            return m1037r().toString();
        }
        return StringUtils.EMPTY;
    }

    @Nullable
    public final byte[] m1040u() {
        if (this.f1388c != null) {
            return this.f1388c.data;
        }
        return null;
    }

    @NonNull
    public final String toString() {
        String str = StringUtils.EMPTY;
        if (!(m1040u() == null || m1041v() == null)) {
            try {
                return new String(m1040u(), HttpHeaderParser.parseCharset(m1041v()));
            } catch (Throwable e) {
                EtsyDebug.m1917d(f1386a, "Error parsing network response", e);
            }
        }
        return str;
    }

    @Nullable
    public final Map<String, String> m1041v() {
        if (this.f1388c != null) {
            return this.f1388c.headers;
        }
        return null;
    }

    @Nullable
    public final String m1035c(@NonNull String str) {
        if (this.f1388c == null || this.f1388c.headers == null) {
            return null;
        }
        return (String) this.f1388c.headers.get(str);
    }

    public boolean m1034b() {
        return m1036q() >= Callback.DEFAULT_DRAG_ANIMATION_DURATION && m1036q() < 300;
    }
}

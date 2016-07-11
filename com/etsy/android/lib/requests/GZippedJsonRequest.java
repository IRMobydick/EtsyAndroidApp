package com.etsy.android.lib.requests;

import android.text.format.Formatter;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.JsonRequest;
import com.etsy.android.lib.config.InstallInfo;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.uikit.view.FullImageView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

public abstract class GZippedJsonRequest<T> extends JsonRequest<T> {
    private static final String TAG;
    private static final int requestTimeoutMs = 25000;

    static {
        TAG = EtsyDebug.m1891a(GZippedJsonRequest.class);
    }

    public GZippedJsonRequest(int i, String str, String str2, Listener<T> listener, ErrorListener errorListener) {
        super(i, str, str2, listener, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(requestTimeoutMs, 1, FullImageView.ASPECT_RATIO_SQUARE));
    }

    public Map<String, String> getHeaders() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("Content-Encoding", BasicNetwork.ENCODING_GZIP);
        hashMap.put("User-agent", InstallInfo.m919a().m935l());
        hashMap.put("X-Etsy-Device", InstallInfo.m919a().m925b());
        return hashMap;
    }

    public GZippedJsonRequest(String str, String str2, Listener<T> listener, ErrorListener errorListener) {
        super(str, str2, listener, errorListener);
    }

    public byte[] getBody() {
        GZIPOutputStream gZIPOutputStream;
        Throwable e;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            try {
                gZIPOutputStream.write(super.getBody());
                if (gZIPOutputStream != null) {
                    try {
                        gZIPOutputStream.close();
                    } catch (Throwable e2) {
                        EtsyDebug.m1917d(TAG, "getBody gzip close error", e2);
                    }
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    EtsyDebug.m1917d(TAG, "getBody gzip error", e2);
                    if (gZIPOutputStream != null) {
                        try {
                            gZIPOutputStream.close();
                        } catch (Throwable e22) {
                            EtsyDebug.m1917d(TAG, "getBody gzip close error", e22);
                        }
                    }
                    EtsyDebug.m1914c(TAG, "getBody: Gzipped body size: %s", Formatter.formatFileSize(EtsyApplication.get(), (long) byteArrayOutputStream.size()));
                    return byteArrayOutputStream.toByteArray();
                } catch (Throwable th) {
                    e22 = th;
                    if (gZIPOutputStream != null) {
                        try {
                            gZIPOutputStream.close();
                        } catch (Throwable e4) {
                            EtsyDebug.m1917d(TAG, "getBody gzip close error", e4);
                        }
                    }
                    throw e22;
                }
            }
        } catch (IOException e5) {
            e22 = e5;
            gZIPOutputStream = null;
            EtsyDebug.m1917d(TAG, "getBody gzip error", e22);
            if (gZIPOutputStream != null) {
                gZIPOutputStream.close();
            }
            EtsyDebug.m1914c(TAG, "getBody: Gzipped body size: %s", Formatter.formatFileSize(EtsyApplication.get(), (long) byteArrayOutputStream.size()));
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th2) {
            e22 = th2;
            gZIPOutputStream = null;
            if (gZIPOutputStream != null) {
                gZIPOutputStream.close();
            }
            throw e22;
        }
        EtsyDebug.m1914c(TAG, "getBody: Gzipped body size: %s", Formatter.formatFileSize(EtsyApplication.get(), (long) byteArrayOutputStream.size()));
        return byteArrayOutputStream.toByteArray();
    }
}

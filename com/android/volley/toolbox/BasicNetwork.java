package com.android.volley.toolbox;

import android.os.SystemClock;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.text.TextUtils;
import com.android.volley.Cache.Entry;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.google.android.gms.common.api.CommonStatusCodes;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.impl.cookie.DateUtils;

public class BasicNetwork implements Network {
    protected static final boolean DEBUG;
    private static int DEFAULT_POOL_SIZE = 0;
    public static final String ENCODING_GZIP = "gzip";
    private static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
    private static int SLOW_REQUEST_THRESHOLD_MS;
    protected final HttpStack mHttpStack;
    protected final ByteArrayPool mPool;

    static {
        DEBUG = VolleyLog.DEBUG;
        SLOW_REQUEST_THRESHOLD_MS = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
        DEFAULT_POOL_SIZE = ItemAnimator.FLAG_APPEARED_IN_PRE_LAYOUT;
    }

    public BasicNetwork(HttpStack httpStack) {
        this(httpStack, new ByteArrayPool(DEFAULT_POOL_SIZE));
    }

    public BasicNetwork(HttpStack httpStack, ByteArrayPool byteArrayPool) {
        this.mHttpStack = httpStack;
        this.mPool = byteArrayPool;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.volley.NetworkResponse performRequest(com.android.volley.Request<?> r13) {
        /*
        r12 = this;
        r10 = android.os.SystemClock.elapsedRealtime();
    L_0x0004:
        r2 = 0;
        r5 = 0;
        r1 = new java.util.HashMap;
        r1.<init>();
        r0 = new java.util.HashMap;	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x00be }
        r0.<init>();	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x00be }
        r3 = r13.getCacheEntry();	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x00be }
        r12.addCacheHeaders(r0, r3);	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x00be }
        r3 = r12.mHttpStack;	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x00be }
        r8 = r3.performRequest(r13, r0);	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x00be }
        r6 = r8.getStatusLine();	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x010e }
        r9 = r6.getStatusCode();	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x010e }
        r0 = r8.getAllHeaders();	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x010e }
        r7 = convertHeaders(r0);	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x010e }
        r0 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        if (r9 != r0) goto L_0x0048;
    L_0x0031:
        r0 = new com.android.volley.NetworkResponse;	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x0111 }
        r2 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        r1 = r13.getCacheEntry();	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x0111 }
        if (r1 != 0) goto L_0x0041;
    L_0x003b:
        r1 = 0;
    L_0x003c:
        r3 = 1;
        r0.<init>(r2, r1, r7, r3);	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x0111 }
    L_0x0040:
        return r0;
    L_0x0041:
        r1 = r13.getCacheEntry();	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x0111 }
        r1 = r1.data;	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x0111 }
        goto L_0x003c;
    L_0x0048:
        r0 = r8.getEntity();	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x0111 }
        if (r0 == 0) goto L_0x0088;
    L_0x004e:
        r0 = r8.getEntity();	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x0111 }
        r1 = r12.isGzipResponse(r7);	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x0111 }
        r5 = r12.entityToBytes(r0, r1);	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x0111 }
    L_0x005a:
        r0 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x0111 }
        r2 = r0 - r10;
        r1 = r12;
        r4 = r13;
        r1.logSlowRequests(r2, r4, r5, r6);	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x0111 }
        r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r9 == r0) goto L_0x008c;
    L_0x0069:
        r0 = 201; // 0xc9 float:2.82E-43 double:9.93E-322;
        if (r9 == r0) goto L_0x008c;
    L_0x006d:
        r0 = 202; // 0xca float:2.83E-43 double:1.0E-321;
        if (r9 == r0) goto L_0x008c;
    L_0x0071:
        r0 = 204; // 0xcc float:2.86E-43 double:1.01E-321;
        if (r9 == r0) goto L_0x008c;
    L_0x0075:
        r0 = new java.io.IOException;	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x0111 }
        r0.<init>();	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x0111 }
        throw r0;	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x0111 }
    L_0x007b:
        r0 = move-exception;
        r0 = "socket";
        r1 = new com.android.volley.TimeoutError;
        r1.<init>();
        attemptRetryOnException(r0, r13, r1);
        goto L_0x0004;
    L_0x0088:
        r0 = 0;
        r5 = new byte[r0];	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x0111 }
        goto L_0x005a;
    L_0x008c:
        r0 = new com.android.volley.NetworkResponse;	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x0111 }
        r1 = 0;
        r0.<init>(r9, r5, r7, r1);	 Catch:{ SocketTimeoutException -> 0x007b, ConnectTimeoutException -> 0x0093, MalformedURLException -> 0x00a0, IOException -> 0x0111 }
        goto L_0x0040;
    L_0x0093:
        r0 = move-exception;
        r0 = "connection";
        r1 = new com.android.volley.TimeoutError;
        r1.<init>();
        attemptRetryOnException(r0, r13, r1);
        goto L_0x0004;
    L_0x00a0:
        r0 = move-exception;
        r1 = new java.lang.RuntimeException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Bad URL ";
        r2 = r2.append(r3);
        r3 = r13.getUrl();
        r2 = r2.append(r3);
        r2 = r2.toString();
        r1.<init>(r2, r0);
        throw r1;
    L_0x00be:
        r0 = move-exception;
    L_0x00bf:
        r3 = 0;
        if (r2 == 0) goto L_0x00fc;
    L_0x00c2:
        r0 = r2.getStatusLine();
        r0 = r0.getStatusCode();
        r2 = "Unexpected response code %d for %s";
        r4 = 2;
        r4 = new java.lang.Object[r4];
        r6 = 0;
        r7 = java.lang.Integer.valueOf(r0);
        r4[r6] = r7;
        r6 = 1;
        r7 = r13.getUrl();
        r4[r6] = r7;
        com.android.volley.VolleyLog.m643e(r2, r4);
        if (r5 == 0) goto L_0x0108;
    L_0x00e2:
        r2 = new com.android.volley.NetworkResponse;
        r3 = 0;
        r2.<init>(r0, r5, r1, r3);
        r1 = 401; // 0x191 float:5.62E-43 double:1.98E-321;
        if (r0 == r1) goto L_0x00f0;
    L_0x00ec:
        r1 = 403; // 0x193 float:5.65E-43 double:1.99E-321;
        if (r0 != r1) goto L_0x0102;
    L_0x00f0:
        r0 = "auth";
        r1 = new com.android.volley.AuthFailureError;
        r1.<init>(r2);
        attemptRetryOnException(r0, r13, r1);
        goto L_0x0004;
    L_0x00fc:
        r1 = new com.android.volley.NoConnectionError;
        r1.<init>(r0);
        throw r1;
    L_0x0102:
        r0 = new com.android.volley.ServerError;
        r0.<init>(r2);
        throw r0;
    L_0x0108:
        r0 = new com.android.volley.NetworkError;
        r0.<init>(r3);
        throw r0;
    L_0x010e:
        r0 = move-exception;
        r2 = r8;
        goto L_0x00bf;
    L_0x0111:
        r0 = move-exception;
        r1 = r7;
        r2 = r8;
        goto L_0x00bf;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.BasicNetwork.performRequest(com.android.volley.Request):com.android.volley.NetworkResponse");
    }

    private boolean isGzipResponse(Map<String, String> map) {
        if (map == null || !map.containsKey(HEADER_CONTENT_ENCODING)) {
            return DEBUG;
        }
        String str = (String) map.get(HEADER_CONTENT_ENCODING);
        if (TextUtils.isEmpty(str) || !str.contains(ENCODING_GZIP)) {
            return DEBUG;
        }
        return true;
    }

    private void logSlowRequests(long j, Request<?> request, byte[] bArr, StatusLine statusLine) {
        if (DEBUG || j > ((long) SLOW_REQUEST_THRESHOLD_MS)) {
            String str = "HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]";
            Object[] objArr = new Object[5];
            objArr[0] = request;
            objArr[1] = Long.valueOf(j);
            objArr[2] = bArr != null ? Integer.valueOf(bArr.length) : "null";
            objArr[3] = Integer.valueOf(statusLine.getStatusCode());
            objArr[4] = Integer.valueOf(request.getRetryPolicy().getCurrentRetryCount());
            VolleyLog.m642d(str, objArr);
        }
    }

    private static void attemptRetryOnException(String str, Request<?> request, VolleyError volleyError) {
        RetryPolicy retryPolicy = request.getRetryPolicy();
        int timeoutMs = request.getTimeoutMs();
        try {
            retryPolicy.retry(volleyError);
            request.addMarker(String.format("%s-retry [timeout=%s]", new Object[]{str, Integer.valueOf(timeoutMs)}));
        } catch (VolleyError e) {
            request.addMarker(String.format("%s-timeout-giveup [timeout=%s]", new Object[]{str, Integer.valueOf(timeoutMs)}));
            throw e;
        }
    }

    private void addCacheHeaders(Map<String, String> map, Entry entry) {
        if (entry != null) {
            if (entry.etag != null) {
                map.put("If-None-Match", entry.etag);
            }
            if (entry.serverDate > 0) {
                map.put("If-Modified-Since", DateUtils.formatDate(new Date(entry.serverDate)));
            }
        }
    }

    protected void logError(String str, String str2, long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        VolleyLog.m645v("HTTP ERROR(%s) %d ms to fetch %s", str, Long.valueOf(elapsedRealtime - j), str2);
    }

    private byte[] entityToBytes(HttpEntity httpEntity, boolean z) {
        Throwable th;
        PoolingByteArrayOutputStream poolingByteArrayOutputStream = new PoolingByteArrayOutputStream(this.mPool, (int) httpEntity.getContentLength());
        InputStream content;
        try {
            content = httpEntity.getContent();
            if (content == null) {
                try {
                    throw new ServerError();
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        httpEntity.consumeContent();
                        if (content != null) {
                            content.close();
                        }
                    } catch (IOException e) {
                        VolleyLog.m645v("Error occured when calling consumingContent", new Object[0]);
                    }
                    this.mPool.returnBuf(null);
                    poolingByteArrayOutputStream.close();
                    throw th;
                }
            }
            if (z) {
                content = new GZIPInputStream(content);
            }
            byte[] buf = this.mPool.getBuf(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
            while (true) {
                int read = content.read(buf);
                if (read == -1) {
                    break;
                }
                poolingByteArrayOutputStream.write(buf, 0, read);
            }
            byte[] toByteArray = poolingByteArrayOutputStream.toByteArray();
            try {
                httpEntity.consumeContent();
                if (content != null) {
                    content.close();
                }
            } catch (IOException e2) {
                VolleyLog.m645v("Error occured when calling consumingContent", new Object[0]);
            }
            this.mPool.returnBuf(buf);
            poolingByteArrayOutputStream.close();
            return toByteArray;
        } catch (Throwable th3) {
            th = th3;
            content = null;
            httpEntity.consumeContent();
            if (content != null) {
                content.close();
            }
            this.mPool.returnBuf(null);
            poolingByteArrayOutputStream.close();
            throw th;
        }
    }

    private static Map<String, String> convertHeaders(Header[] headerArr) {
        Map<String, String> hashMap = new HashMap();
        for (int i = 0; i < headerArr.length; i++) {
            hashMap.put(headerArr[i].getName(), headerArr[i].getValue());
        }
        return hashMap;
    }
}

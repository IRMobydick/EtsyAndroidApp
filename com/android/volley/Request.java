package com.android.volley;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.android.volley.Cache.Entry;
import com.android.volley.Response.ErrorListener;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public abstract class Request<T> implements Comparable<Request<T>> {
    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
    private static final long SLOW_REQUEST_THRESHOLD_MS = 3000;
    private Entry mCacheEntry;
    private boolean mCanceled;
    private final int mDefaultTrafficStatsTag;
    private final ErrorListener mErrorListener;
    private final MarkerLog mEventLog;
    private final int mMethod;
    private long mRequestBirthTime;
    private RequestQueue mRequestQueue;
    private boolean mResponseDelivered;
    private RetryPolicy mRetryPolicy;
    private Integer mSequence;
    private boolean mShouldCache;
    private String mTagHashcode;
    private final String mUrl;

    /* renamed from: com.android.volley.Request.1 */
    class C03681 implements Runnable {
        final /* synthetic */ String val$tag;
        final /* synthetic */ long val$threadId;

        C03681(String str, long j) {
            this.val$tag = str;
            this.val$threadId = j;
        }

        public void run() {
            Request.this.mEventLog.add(this.val$tag, this.val$threadId);
            Request.this.mEventLog.finish(toString());
        }
    }

    public interface Method {
        public static final int DELETE = 3;
        public static final int DEPRECATED_GET_OR_POST = -1;
        public static final int GET = 0;
        public static final int HEAD = 4;
        public static final int OPTIONS = 5;
        public static final int PATCH = 7;
        public static final int POST = 1;
        public static final int PUT = 2;
        public static final int TRACE = 6;
    }

    public enum Priority {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    protected abstract void deliverResponse(T t);

    public abstract Response<T> parseNetworkResponse(NetworkResponse networkResponse);

    @Deprecated
    public Request(String str, ErrorListener errorListener) {
        this(-1, str, errorListener);
    }

    public Request(int i, String str, ErrorListener errorListener) {
        MarkerLog markerLog;
        if (MarkerLog.ENABLED) {
            markerLog = new MarkerLog();
        } else {
            markerLog = null;
        }
        this.mEventLog = markerLog;
        this.mShouldCache = true;
        this.mCanceled = false;
        this.mResponseDelivered = false;
        this.mRequestBirthTime = 0;
        this.mCacheEntry = null;
        this.mMethod = i;
        this.mUrl = str;
        this.mErrorListener = errorListener;
        setRetryPolicy(new DefaultRetryPolicy());
        this.mTagHashcode = StringUtils.EMPTY;
        this.mDefaultTrafficStatsTag = findDefaultTrafficStatsTag(str);
    }

    public int getMethod() {
        return this.mMethod;
    }

    public Request<?> setTag(Object obj) {
        this.mTagHashcode = createTag(obj);
        return this;
    }

    public Request<?> setTag(String str) {
        if (str == null) {
            this.mTagHashcode = StringUtils.EMPTY;
        } else {
            this.mTagHashcode = str;
        }
        return this;
    }

    public static String createTag(Object obj) {
        if (obj == null) {
            return StringUtils.EMPTY;
        }
        return String.format(Locale.US, "%s.%d", new Object[]{obj.getClass().getSimpleName(), Integer.valueOf(obj.hashCode())});
    }

    public String getTag() {
        return this.mTagHashcode;
    }

    public ErrorListener getErrorListener() {
        return this.mErrorListener;
    }

    public int getTrafficStatsTag() {
        return this.mDefaultTrafficStatsTag;
    }

    private static int findDefaultTrafficStatsTag(String str) {
        if (!TextUtils.isEmpty(str)) {
            Uri parse = Uri.parse(str);
            if (parse != null) {
                String host = parse.getHost();
                if (host != null) {
                    return host.hashCode();
                }
            }
        }
        return 0;
    }

    public Request<?> setRetryPolicy(RetryPolicy retryPolicy) {
        this.mRetryPolicy = retryPolicy;
        return this;
    }

    public void addMarker(String str) {
        if (MarkerLog.ENABLED) {
            this.mEventLog.add(str, Thread.currentThread().getId());
        } else if (this.mRequestBirthTime == 0) {
            this.mRequestBirthTime = SystemClock.elapsedRealtime();
        }
    }

    void finish(String str) {
        if (this.mRequestQueue != null) {
            this.mRequestQueue.finish(this);
        }
        if (MarkerLog.ENABLED) {
            long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new C03681(str, id));
                return;
            }
            this.mEventLog.add(str, id);
            this.mEventLog.finish(toString());
            return;
        }
        if (SystemClock.elapsedRealtime() - this.mRequestBirthTime >= SLOW_REQUEST_THRESHOLD_MS) {
            VolleyLog.m642d("%d ms: %s", Long.valueOf(SystemClock.elapsedRealtime() - this.mRequestBirthTime), toString());
        }
    }

    public Request<?> setRequestQueue(RequestQueue requestQueue) {
        this.mRequestQueue = requestQueue;
        return this;
    }

    public final Request<?> setSequence(int i) {
        this.mSequence = Integer.valueOf(i);
        return this;
    }

    public final int getSequence() {
        if (this.mSequence != null) {
            return this.mSequence.intValue();
        }
        throw new IllegalStateException("getSequence called before setSequence");
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getCacheKey() {
        return getUrl();
    }

    public Request<?> setCacheEntry(Entry entry) {
        this.mCacheEntry = entry;
        return this;
    }

    public Entry getCacheEntry() {
        return this.mCacheEntry;
    }

    public void cancel() {
        this.mCanceled = true;
    }

    public boolean isCanceled() {
        return this.mCanceled;
    }

    public Map<String, String> getHeaders() {
        return Collections.emptyMap();
    }

    @Deprecated
    protected Map<String, String> getPostParams() {
        return getParams();
    }

    @Deprecated
    protected String getPostParamsEncoding() {
        return getParamsEncoding();
    }

    @Deprecated
    public String getPostBodyContentType() {
        return getBodyContentType();
    }

    @Deprecated
    public byte[] getPostBody() {
        Map postParams = getPostParams();
        if (postParams == null || postParams.size() <= 0) {
            return null;
        }
        return encodeParameters(postParams, getPostParamsEncoding());
    }

    protected Map<String, String> getParams() {
        return null;
    }

    protected String getParamsEncoding() {
        return DEFAULT_PARAMS_ENCODING;
    }

    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
    }

    public byte[] getBody() {
        Map params = getParams();
        if (params == null || params.size() <= 0) {
            return null;
        }
        return encodeParameters(params, getParamsEncoding());
    }

    private byte[] encodeParameters(Map<String, String> map, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (Map.Entry entry : map.entrySet()) {
                stringBuilder.append(URLEncoder.encode((String) entry.getKey(), str));
                stringBuilder.append('=');
                stringBuilder.append(URLEncoder.encode((String) entry.getValue(), str));
                stringBuilder.append('&');
            }
            return stringBuilder.toString().getBytes(str);
        } catch (Throwable e) {
            throw new RuntimeException("Encoding not supported: " + str, e);
        }
    }

    public final Request<?> setShouldCache(boolean z) {
        this.mShouldCache = z;
        return this;
    }

    public final boolean shouldCache() {
        return this.mShouldCache;
    }

    public Priority getPriority() {
        return Priority.NORMAL;
    }

    public final int getTimeoutMs() {
        return this.mRetryPolicy.getCurrentTimeout();
    }

    public RetryPolicy getRetryPolicy() {
        return this.mRetryPolicy;
    }

    public void markDelivered() {
        this.mResponseDelivered = true;
    }

    public boolean hasHadResponseDelivered() {
        return this.mResponseDelivered;
    }

    protected VolleyError parseNetworkError(VolleyError volleyError) {
        return volleyError;
    }

    public void deliverError(VolleyError volleyError) {
        if (this.mErrorListener != null) {
            this.mErrorListener.onErrorResponse(volleyError);
        }
    }

    public int compareTo(Request<T> request) {
        Priority priority = getPriority();
        Priority priority2 = request.getPriority();
        return priority == priority2 ? this.mSequence.intValue() - request.mSequence.intValue() : priority2.ordinal() - priority.ordinal();
    }

    public String toString() {
        return (this.mCanceled ? "[X] " : "[ ] ") + getUrl() + " " + ("0x" + Integer.toHexString(getTrafficStatsTag())) + " " + getPriority() + " " + this.mSequence;
    }
}

package com.android.volley;

import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.lang3.StringUtils;

public class RequestQueue {
    private static final int DEFAULT_NETWORK_THREAD_POOL_SIZE = 4;
    private final Cache mCache;
    private CacheDispatcher mCacheDispatcher;
    private final PriorityBlockingQueue<Request<?>> mCacheQueue;
    private final Set<Request<?>> mCurrentRequests;
    private final ResponseDelivery mDelivery;
    private NetworkDispatcher[] mDispatchers;
    private final Network mNetwork;
    private final PriorityBlockingQueue<Request<?>> mNetworkQueue;
    private OnRequestRemovedCallback mOnRequestRemovedCallback;
    private AtomicInteger mSequenceGenerator;
    private final Map<String, Queue<Request<?>>> mWaitingRequests;

    public interface RequestFilter {
        boolean apply(Request<?> request);
    }

    /* renamed from: com.android.volley.RequestQueue.1 */
    class C03691 implements RequestFilter {
        final /* synthetic */ Object val$tag;

        C03691(Object obj) {
            this.val$tag = obj;
        }

        public boolean apply(Request<?> request) {
            return Request.createTag(this.val$tag).equals(request.getTag());
        }
    }

    /* renamed from: com.android.volley.RequestQueue.2 */
    class C03702 implements RequestFilter {
        final /* synthetic */ String val$tagHashcode;

        C03702(String str) {
            this.val$tagHashcode = str;
        }

        public boolean apply(Request<?> request) {
            return this.val$tagHashcode.equals(request.getTag());
        }
    }

    public interface OnRequestRemovedCallback {
        void onRequestRemoved(Request request);
    }

    public RequestQueue(Cache cache, Network network, int i, ResponseDelivery responseDelivery) {
        this.mSequenceGenerator = new AtomicInteger();
        this.mWaitingRequests = new HashMap();
        this.mCurrentRequests = new HashSet();
        this.mCacheQueue = new PriorityBlockingQueue();
        this.mNetworkQueue = new PriorityBlockingQueue();
        this.mCache = cache;
        this.mNetwork = network;
        this.mDispatchers = new NetworkDispatcher[i];
        this.mDelivery = responseDelivery;
    }

    public RequestQueue(Cache cache, Network network, int i) {
        this(cache, network, i, new ExecutorDelivery(new Handler(Looper.getMainLooper())));
    }

    public RequestQueue(Cache cache, Network network) {
        this(cache, network, DEFAULT_NETWORK_THREAD_POOL_SIZE);
    }

    public void start() {
        stop();
        this.mCacheDispatcher = new CacheDispatcher(this.mCacheQueue, this.mNetworkQueue, this.mCache, this.mDelivery);
        this.mCacheDispatcher.start();
        for (int i = 0; i < this.mDispatchers.length; i++) {
            NetworkDispatcher networkDispatcher = new NetworkDispatcher(this.mNetworkQueue, this.mNetwork, this.mCache, this.mDelivery);
            this.mDispatchers[i] = networkDispatcher;
            networkDispatcher.start();
        }
    }

    public void stop() {
        if (this.mCacheDispatcher != null) {
            this.mCacheDispatcher.quit();
        }
        for (int i = 0; i < this.mDispatchers.length; i++) {
            if (this.mDispatchers[i] != null) {
                this.mDispatchers[i].quit();
            }
        }
    }

    public int getSequenceNumber() {
        return this.mSequenceGenerator.incrementAndGet();
    }

    public Cache getCache() {
        return this.mCache;
    }

    public void cancelAll(RequestFilter requestFilter) {
        synchronized (this.mCurrentRequests) {
            for (Request request : this.mCurrentRequests) {
                if (requestFilter.apply(request)) {
                    request.cancel();
                }
            }
        }
    }

    public void cancelAll(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Cannot cancelAll with a null tag");
        }
        cancelAll(new C03691(obj));
    }

    public void cancelAll(String str) {
        if (str == null || StringUtils.EMPTY.equals(str)) {
            throw new IllegalArgumentException("Cannot cancelAll with a null tag");
        }
        cancelAll(new C03702(str));
    }

    public <T> Request<T> add(Request<T> request) {
        request.setRequestQueue(this);
        synchronized (this.mCurrentRequests) {
            this.mCurrentRequests.add(request);
        }
        request.setSequence(getSequenceNumber());
        request.addMarker("add-to-queue");
        if (request.shouldCache()) {
            synchronized (this.mWaitingRequests) {
                String cacheKey = request.getCacheKey();
                if (this.mWaitingRequests.containsKey(cacheKey)) {
                    Queue queue = (Queue) this.mWaitingRequests.get(cacheKey);
                    if (queue == null) {
                        queue = new LinkedList();
                    }
                    queue.add(request);
                    this.mWaitingRequests.put(cacheKey, queue);
                    if (VolleyLog.DEBUG) {
                        VolleyLog.m645v("Request for cacheKey=%s is in flight, putting on hold.", cacheKey);
                    }
                } else {
                    this.mWaitingRequests.put(cacheKey, null);
                    this.mCacheQueue.add(request);
                }
            }
        } else {
            this.mNetworkQueue.add(request);
        }
        return request;
    }

    void finish(Request<?> request) {
        synchronized (this.mCurrentRequests) {
            this.mCurrentRequests.remove(request);
            if (this.mOnRequestRemovedCallback != null) {
                this.mOnRequestRemovedCallback.onRequestRemoved(request);
            }
        }
        if (request.shouldCache()) {
            synchronized (this.mWaitingRequests) {
                Queue queue = (Queue) this.mWaitingRequests.remove(request.getCacheKey());
                if (queue != null) {
                    if (VolleyLog.DEBUG) {
                        VolleyLog.m645v("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(queue.size()), r2);
                    }
                    this.mCacheQueue.addAll(queue);
                }
            }
        }
    }

    public void setOnRemovedCallback(OnRequestRemovedCallback onRequestRemovedCallback) {
        this.mOnRequestRemovedCallback = onRequestRemovedCallback;
    }
}

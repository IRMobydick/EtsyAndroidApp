package com.android.volley.toolbox;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class ImageLoader {
    private int mBatchResponseDelayMs;
    private final HashMap<String, BatchedImageRequest> mBatchedResponses;
    private final ImageCache mCache;
    private final Handler mHandler;
    private final HashMap<String, BatchedImageRequest> mInFlightRequests;
    private final RequestQueue mRequestQueue;
    private Runnable mRunnable;

    public interface ImageListener extends ErrorListener {
        void onResponse(ImageContainer imageContainer, boolean z);
    }

    /* renamed from: com.android.volley.toolbox.ImageLoader.1 */
    final class C03731 implements ImageListener {
        final /* synthetic */ int val$defaultImageResId;
        final /* synthetic */ int val$errorImageResId;
        final /* synthetic */ ImageView val$view;

        C03731(int i, ImageView imageView, int i2) {
            this.val$errorImageResId = i;
            this.val$view = imageView;
            this.val$defaultImageResId = i2;
        }

        public void onErrorResponse(VolleyError volleyError) {
            if (this.val$errorImageResId != 0) {
                this.val$view.setImageResource(this.val$errorImageResId);
            }
        }

        public void onResponse(ImageContainer imageContainer, boolean z) {
            if (imageContainer.getBitmap() != null) {
                this.val$view.setImageBitmap(imageContainer.getBitmap());
            } else if (this.val$defaultImageResId != 0) {
                this.val$view.setImageResource(this.val$defaultImageResId);
            }
        }
    }

    /* renamed from: com.android.volley.toolbox.ImageLoader.2 */
    class C03742 implements Listener<Bitmap> {
        final /* synthetic */ String val$cacheKey;

        C03742(String str) {
            this.val$cacheKey = str;
        }

        public void onResponse(Bitmap bitmap) {
            ImageLoader.this.onGetImageSuccess(this.val$cacheKey, bitmap);
        }
    }

    /* renamed from: com.android.volley.toolbox.ImageLoader.3 */
    class C03753 implements ErrorListener {
        final /* synthetic */ String val$cacheKey;

        C03753(String str) {
            this.val$cacheKey = str;
        }

        public void onErrorResponse(VolleyError volleyError) {
            ImageLoader.this.onGetImageError(this.val$cacheKey, volleyError);
        }
    }

    /* renamed from: com.android.volley.toolbox.ImageLoader.4 */
    class C03764 implements Runnable {
        C03764() {
        }

        public void run() {
            for (BatchedImageRequest batchedImageRequest : ImageLoader.this.mBatchedResponses.values()) {
                Iterator it = batchedImageRequest.mContainers.iterator();
                while (it.hasNext()) {
                    ImageContainer imageContainer = (ImageContainer) it.next();
                    if (imageContainer.mListener != null) {
                        if (batchedImageRequest.getError() == null) {
                            imageContainer.mBitmap = batchedImageRequest.mResponseBitmap;
                            imageContainer.mListener.onResponse(imageContainer, false);
                        } else {
                            imageContainer.mListener.onErrorResponse(batchedImageRequest.getError());
                        }
                    }
                }
            }
            ImageLoader.this.mBatchedResponses.clear();
            ImageLoader.this.mRunnable = null;
        }
    }

    class BatchedImageRequest {
        private final LinkedList<ImageContainer> mContainers;
        private VolleyError mError;
        private final Request<?> mRequest;
        private Bitmap mResponseBitmap;

        public BatchedImageRequest(Request<?> request, ImageContainer imageContainer) {
            this.mContainers = new LinkedList();
            this.mRequest = request;
            this.mContainers.add(imageContainer);
        }

        public void setError(VolleyError volleyError) {
            this.mError = volleyError;
        }

        public VolleyError getError() {
            return this.mError;
        }

        public void addContainer(ImageContainer imageContainer) {
            this.mContainers.add(imageContainer);
        }

        public boolean removeContainerAndCancelIfNecessary(ImageContainer imageContainer) {
            this.mContainers.remove(imageContainer);
            if (this.mContainers.size() != 0) {
                return false;
            }
            this.mRequest.cancel();
            return true;
        }
    }

    public interface ImageCache {
        Bitmap getBitmap(String str);

        void putBitmap(String str, Bitmap bitmap);
    }

    public class ImageContainer {
        private Bitmap mBitmap;
        private final String mCacheKey;
        private final ImageListener mListener;
        private final String mRequestUrl;

        public ImageContainer(Bitmap bitmap, String str, String str2, ImageListener imageListener) {
            this.mBitmap = bitmap;
            this.mRequestUrl = str;
            this.mCacheKey = str2;
            this.mListener = imageListener;
        }

        public void cancelRequest() {
            if (this.mListener != null) {
                BatchedImageRequest batchedImageRequest = (BatchedImageRequest) ImageLoader.this.mInFlightRequests.get(this.mCacheKey);
                if (batchedImageRequest == null) {
                    batchedImageRequest = (BatchedImageRequest) ImageLoader.this.mBatchedResponses.get(this.mCacheKey);
                    if (batchedImageRequest != null) {
                        batchedImageRequest.removeContainerAndCancelIfNecessary(this);
                        if (batchedImageRequest.mContainers.size() == 0) {
                            ImageLoader.this.mBatchedResponses.remove(this.mCacheKey);
                        }
                    }
                } else if (batchedImageRequest.removeContainerAndCancelIfNecessary(this)) {
                    ImageLoader.this.mInFlightRequests.remove(this.mCacheKey);
                }
            }
        }

        public Bitmap getBitmap() {
            return this.mBitmap;
        }

        public String getRequestUrl() {
            return this.mRequestUrl;
        }
    }

    public ImageLoader(RequestQueue requestQueue, ImageCache imageCache) {
        this.mBatchResponseDelayMs = 100;
        this.mInFlightRequests = new HashMap();
        this.mBatchedResponses = new HashMap();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mRequestQueue = requestQueue;
        this.mCache = imageCache;
    }

    public static ImageListener getImageListener(ImageView imageView, int i, int i2) {
        return new C03731(i2, imageView, i);
    }

    public boolean isCached(String str, int i, int i2) {
        throwIfNotOnMainThread();
        return this.mCache.getBitmap(getCacheKey(str, i, i2)) != null;
    }

    public ImageContainer get(String str, ImageListener imageListener) {
        return get(str, imageListener, 0, 0);
    }

    public ImageContainer get(String str, ImageListener imageListener, int i, int i2) {
        throwIfNotOnMainThread();
        String cacheKey = getCacheKey(str, i, i2);
        Bitmap bitmap = this.mCache.getBitmap(cacheKey);
        if (bitmap != null) {
            ImageContainer imageContainer = new ImageContainer(bitmap, str, null, null);
            imageListener.onResponse(imageContainer, true);
            return imageContainer;
        }
        ImageContainer imageContainer2 = new ImageContainer(null, str, cacheKey, imageListener);
        imageListener.onResponse(imageContainer2, true);
        BatchedImageRequest batchedImageRequest = (BatchedImageRequest) this.mInFlightRequests.get(cacheKey);
        if (batchedImageRequest != null) {
            batchedImageRequest.addContainer(imageContainer2);
            return imageContainer2;
        }
        Request makeImageRequest = makeImageRequest(str, i, i2, cacheKey);
        this.mRequestQueue.add(makeImageRequest);
        this.mInFlightRequests.put(cacheKey, new BatchedImageRequest(makeImageRequest, imageContainer2));
        return imageContainer2;
    }

    protected Request<Bitmap> makeImageRequest(String str, int i, int i2, String str2) {
        return new ImageRequest(str, new C03742(str2), i, i2, Config.ARGB_8888, new C03753(str2));
    }

    public void setBatchedResponseDelay(int i) {
        this.mBatchResponseDelayMs = i;
    }

    protected void onGetImageSuccess(String str, Bitmap bitmap) {
        this.mCache.putBitmap(str, bitmap);
        BatchedImageRequest batchedImageRequest = (BatchedImageRequest) this.mInFlightRequests.remove(str);
        if (batchedImageRequest != null) {
            batchedImageRequest.mResponseBitmap = bitmap;
            batchResponse(str, batchedImageRequest);
        }
    }

    protected void onGetImageError(String str, VolleyError volleyError) {
        BatchedImageRequest batchedImageRequest = (BatchedImageRequest) this.mInFlightRequests.remove(str);
        if (batchedImageRequest != null) {
            batchedImageRequest.setError(volleyError);
            batchResponse(str, batchedImageRequest);
        }
    }

    private void batchResponse(String str, BatchedImageRequest batchedImageRequest) {
        this.mBatchedResponses.put(str, batchedImageRequest);
        if (this.mRunnable == null) {
            this.mRunnable = new C03764();
            this.mHandler.postDelayed(this.mRunnable, (long) this.mBatchResponseDelayMs);
        }
    }

    private void throwIfNotOnMainThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("ImageLoader must be invoked from the main thread.");
        }
    }

    public static String getCacheKey(String str, int i, int i2) {
        return new StringBuilder(str.length() + 12).append("#W").append(i).append("#H").append(i2).append(str).toString();
    }
}

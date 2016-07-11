package com.etsy.android.lib.core.img;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import java.io.File;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.lib.core.img.f */
class ImageDownloader extends AsyncTask<Void, Void, Bitmap> {
    final /* synthetic */ ImageDownloader f1626a;
    private final String f1627b;
    private final String f1628c;
    private final ImageListener f1629d;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m1619a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m1620a((Bitmap) obj);
    }

    public ImageDownloader(ImageDownloader imageDownloader, String str, String str2, ImageListener imageListener) {
        this.f1626a = imageDownloader;
        this.f1627b = str;
        this.f1628c = str2;
        this.f1629d = imageListener;
    }

    protected Bitmap m1619a(Void... voidArr) {
        Bitmap a = ImageHelper.m1630a(new File(this.f1627b.replaceFirst("file://", StringUtils.EMPTY)));
        if (a != null) {
            this.f1626a.f1624d.putBitmap(this.f1628c, a);
        }
        return a;
    }

    protected void m1620a(Bitmap bitmap) {
        if (this.f1629d != null) {
            ImageListener imageListener = this.f1629d;
            ImageLoader b = this.f1626a.f1625e;
            b.getClass();
            imageListener.onResponse(new ImageContainer(bitmap, this.f1627b, this.f1628c, this.f1629d), false);
        }
    }
}
) objArr);
        }

        protected Void m1606a(Void... voidArr) {
            try {
                File[] listFiles = this.f1619a.listFiles();
                if (listFiles != null) {
                    for (File delete : listFiles) {
                        delete.delete();
                    }
                }
                this.f1619a.delete();
            } catch (Exception e) {
                EtsyDebug.m1916d(ImageDownloader.f1621a, "error cleaning up files in legacy cache");
            }
            return null;
        }
    }

    static {
        f1621a = EtsyDebug.m1891a(ImageDownloader.class);
        f1622b = null;
    }

    public static ImageDownloader m1609a() {
        if (f1622b == null) {
            f1622b = new ImageDownloader();
        }
        return f1622b;
    }

    private ImageDownloader() {
    }

    public void m1616a(Context context) {
        if (this.f1624d == null) {
            this.f1624d = new BitmapLruCache();
            this.f1623c = ai.m1096a(context);
            this.f1625e = new ImageLoader(this.f1623c, this.f1624d);
            this.f1625e.setBatchedResponseDelay(0);
            m1613b(context);
        }
    }

    public ImageContainer m1615a(ImageDownloadRequest imageDownloadRequest) {
        if (this.f1625e == null) {
            throw new IllegalStateException("Please call init() on this ImageDownloader.INSTANCE before loading images");
        } else if (imageDownloadRequest == null || TextUtils.isEmpty(imageDownloadRequest.m1598b())) {
            EtsyDebug.m1919e("ImageDownloader", "invalid ImageDownloadRequest");
            return null;
        } else if (!URLUtil.isValidUrl(imageDownloadRequest.m1598b())) {
            throw new IllegalStateException("Invalid image url passed through ImageDownloadRequest to getImage");
        } else if (m1610a(imageDownloadRequest.m1598b())) {
            return m1607a(imageDownloadRequest.m1598b(), imageDownloadRequest.m1601d(), imageDownloadRequest.m1603e(), imageDownloadRequest.m1593a());
        } else {
            if (imageDownloadRequest.m1603e() == -1 || imageDownloadRequest.m1601d() == -1) {
                return this.f1625e.get(imageDownloadRequest.m1598b(), imageDownloadRequest.m1593a());
            }
            return this.f1625e.get(imageDownloadRequest.m1598b(), imageDownloadRequest.m1593a(), imageDownloadRequest.m1601d(), imageDownloadRequest.m1603e());
        }
    }

    public Bitmap m1614a(String str, int i, int i2, boolean z, boolean z2) {
        if (str == null) {
            return null;
        }
        Bitmap bitmap;
        String cacheKey = ImageLoader.getCacheKey(str, i, i2);
        if (z2) {
            bitmap = this.f1624d.getBitmap(cacheKey);
            if (bitmap != null) {
                return bitmap;
            }
        }
        if (m1610a(str)) {
            bitmap = ImageHelper.m1633a(str, i, i2);
            if (!z) {
                return bitmap;
            }
            this.f1624d.putBitmap(cacheKey, bitmap);
            return bitmap;
        }
        ErrorListener newFuture = RequestFuture.newFuture();
        Request imageRequest = new ImageRequest(str, newFuture, i, i2, Config.ARGB_8888, newFuture);
        if (imageRequest != null) {
            try {
                this.f1623c.add(imageRequest);
                bitmap = (Bitmap) newFuture.get();
                if (bitmap == null || !z) {
                    return bitmap;
                }
                this.f1624d.putBitmap(cacheKey, bitmap);
                return bitmap;
            } catch (InterruptedException e) {
                EtsyDebug.m1920e(f1621a, "Interrupted Downloading Synchronous Image message:%s url:%s", e.getMessage(), imageRequest.getUrl());
            } catch (ExecutionException e2) {
                if (e2.getCause() instanceof VolleyError) {
                    VolleyError volleyError = (VolleyError) e2.getCause();
                    if (volleyError.networkResponse != null) {
                        String str2 = StringUtils.EMPTY;
                        if (volleyError.networkResponse.headers != null) {
                            str2 = (String) volleyError.networkResponse.headers.get("X-Error-Detail");
                        }
                        EtsyDebug.m1920e(f1621a, "Error Downloading Synchronous Image message:%s errorheader:%s url:%s", volleyError.getMessage(), str2, imageRequest.getUrl());
                    }
                }
            }
        }
        return null;
    }

    public boolean m1617a(String str, int i, int i2) {
        if (this.f1624d != null && this.f1625e != null) {
            return !TextUtils.isEmpty(str) && this.f1625e.isCached(str, i, i2);
        } else {
            throw new IllegalStateException("Please call init() on this ImageDownloader.INSTANCE before loading images");
        }
    }

    public boolean m1618b(ImageDownloadRequest imageDownloadRequest) {
        if (this.f1625e == null) {
            throw new IllegalStateException("Please call init() on this ImageDownloader.INSTANCE before loading images");
        } else if (imageDownloadRequest == null || TextUtils.isEmpty(imageDownloadRequest.m1598b())) {
            return false;
        } else {
            if (imageDownloadRequest.m1603e() == -1 || imageDownloadRequest.m1601d() == -1) {
                return this.f1625e.isCached(imageDownloadRequest.m1598b(), 0, 0);
            }
            return this.f1625e.isCached(imageDownloadRequest.m1598b(), imageDownloadRequest.m1601d(), imageDownloadRequest.m1603e());
        }
    }

    private boolean m1610a(String str) {
        return URLUtil.isFileUrl(str);
    }

    private ImageContainer m1607a(String str, int i, int i2, ImageListener imageListener) {
        if (i == -1) {
            i = 0;
        }
        if (i2 == -1) {
            i2 = 0;
        }
        String cacheKey = ImageLoader.getCacheKey(str, i, i2);
        Bitmap bitmap = this.f1624d.getBitmap(cacheKey);
        if (bitmap != null) {
            ImageLoader imageLoader = this.f1625e;
            imageLoader.getClass();
            ImageContainer imageContainer = new ImageContainer(bitmap, str, null, null);
            imageListener.onResponse(imageContainer, true);
            return imageContainer;
        }
        ImageLoader imageLoader2 = this.f1625e;
        imageLoader2.getClass();
        ImageContainer imageContainer2 = new ImageContainer(null, str, cacheKey, imageListener);
        ap.m1142a(new ImageDownloader(this, str, cacheKey, imageListener), new Void[0]);
        imageListener.onResponse(imageContainer2, true);
        return imageContainer2;
    }

    private void m1613b(Context context) {
        try {
            File a = af.m3198a(context, "etsyImgCache");
            if (a != null && a.exists()) {
                ap.m1142a(new ImageDownloader(this, a), new Void[0]);
            }
        } catch (Throwable e) {
            EtsyDebug.m1917d(f1621a, "cleanUpLegacyDiskCache error - ignoring", e);
        }
    }
}

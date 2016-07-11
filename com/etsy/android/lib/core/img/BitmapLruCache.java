package com.etsy.android.lib.core.img;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.util.LruCache;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.etsy.android.lib.logger.EtsyDebug;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

public class BitmapLruCache extends LruCache<String, Bitmap> implements ImageCache {
    private static final boolean DBG = false;
    private static final String TAG;

    /* renamed from: com.etsy.android.lib.core.img.BitmapLruCache.1 */
    /* synthetic */ class C04611 {
        static final /* synthetic */ int[] f1601a;

        static {
            f1601a = new int[Config.values().length];
            try {
                f1601a[Config.ARGB_8888.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1601a[Config.RGB_565.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f1601a[Config.ARGB_4444.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f1601a[Config.ALPHA_8.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    static {
        TAG = EtsyDebug.m1891a(BitmapLruCache.class);
    }

    public BitmapLruCache() {
        super(getCacheMemorySize());
    }

    private static int getCacheMemorySize() {
        return ((int) (Runtime.getRuntime().maxMemory() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID)) / 8;
    }

    public Bitmap getBitmap(String str) {
        return (Bitmap) get(str);
    }

    public void putBitmap(String str, Bitmap bitmap) {
        if (bitmap != null) {
            put(str, bitmap);
        }
    }

    protected int sizeOf(String str, Bitmap bitmap) {
        return ((getBytesPerPixel(bitmap) * bitmap.getRowBytes()) * bitmap.getHeight()) / AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT;
    }

    protected void entryRemoved(boolean z, String str, Bitmap bitmap, Bitmap bitmap2) {
        super.entryRemoved(z, str, bitmap, bitmap2);
    }

    public void flush() {
        synchronized (this) {
            trimToSize(0);
        }
    }

    private static int getBytesPerPixel(Bitmap bitmap) {
        if (bitmap.getConfig() == null) {
            return 1;
        }
        switch (C04611.f1601a[bitmap.getConfig().ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return 4;
            case Task.NETWORK_STATE_ANY /*2*/:
                return 2;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                return 2;
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                return 1;
            default:
                return 1;
        }
    }
}

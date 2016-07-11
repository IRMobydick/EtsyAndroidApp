package com.etsy.android.lib.core.img;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.format.Formatter;
import android.widget.ImageView;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.etsy.android.lib.config.EtsyFeatureFlags;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.logger.EtsyDebug;

/* renamed from: com.etsy.android.lib.core.img.c */
public class ImageDownloadListener implements ImageListener {
    private static long f1607a;
    private ImageView f1608b;
    private int f1609c;
    private int f1610d;
    private int f1611e;

    static {
        f1607a = 0;
    }

    public ImageDownloadListener(ImageView imageView) {
        this.f1609c = 17170445;
        this.f1610d = 0;
        this.f1611e = 17170445;
        this.f1608b = imageView;
    }

    public void onErrorResponse(VolleyError volleyError) {
        if (this.f1608b != null && this.f1611e != 0 && this.f1611e != 17170445) {
            this.f1608b.setImageResource(this.f1611e);
            m1580a(this.f1608b);
        }
    }

    public void onResponse(ImageContainer imageContainer, boolean z) {
        if (imageContainer.getBitmap() != null) {
            boolean z2;
            if (z) {
                z2 = false;
            } else {
                z2 = true;
            }
            m1581a(imageContainer, z2);
        } else if (this.f1608b == null) {
        } else {
            if (this.f1610d != 0) {
                m1585c(this.f1610d);
                m1580a(this.f1608b);
            } else if (this.f1609c != 0) {
                m1586d(this.f1609c);
                m1580a(this.f1608b);
            }
        }
    }

    public boolean m1583b() {
        return (this.f1610d == 0 && this.f1609c == 0) ? false : true;
    }

    public void m1584c() {
        if (this.f1608b == null) {
            return;
        }
        if (this.f1610d != 0) {
            m1585c(this.f1610d);
        } else if (this.f1609c != 0) {
            m1586d(this.f1609c);
        }
    }

    public void m1585c(int i) {
        this.f1608b.setImageDrawable(new ColorDrawable(i));
    }

    public void m1586d(int i) {
        this.f1608b.setImageResource(this.f1609c);
    }

    public void m1581a(ImageContainer imageContainer, boolean z) {
        m1579a(imageContainer.getBitmap(), z);
        if (EtsyFeatureFlags.m914a()) {
            int byteCount = imageContainer.getBitmap().getByteCount();
            if (f1607a > Long.MAX_VALUE - ((long) byteCount)) {
                EtsyDebug.m1912c("ImageSizeTracker", "Resetting session byte size, maxed out long");
                f1607a = 0;
            }
            f1607a += (long) byteCount;
            Context context = EtsyApplication.get();
            if (byteCount >= AccessibilityNodeInfoCompat.ACTION_COLLAPSE) {
                EtsyDebug.m1919e("ImageSizeTracker", "Size dl'd img, HUGE: " + Formatter.formatFileSize(context, (long) byteCount) + " : " + imageContainer.getRequestUrl());
            } else {
                EtsyDebug.m1912c("ImageSizeTracker", "Size dl'd img: " + Formatter.formatFileSize(context, (long) byteCount) + " : " + imageContainer.getRequestUrl());
            }
            EtsyDebug.m1912c("ImageSizeTracker", "Current img total dl size: " + Formatter.formatFileSize(context, f1607a));
        }
    }

    public void m1579a(Bitmap bitmap, boolean z) {
        if (this.f1608b != null) {
            if (z) {
                Drawable transitionDrawable;
                Drawable drawable = this.f1608b.getDrawable();
                Resources resources = this.f1608b.getResources();
                if (drawable != null) {
                    transitionDrawable = new TransitionDrawable(new Drawable[]{drawable, new BitmapDrawable(resources, bitmap)});
                } else {
                    transitionDrawable = new TransitionDrawable(new Drawable[]{resources.getDrawable(17170445), new BitmapDrawable(resources, bitmap)});
                }
                transitionDrawable.setCrossFadeEnabled(false);
                this.f1608b.setImageDrawable(transitionDrawable);
                transitionDrawable.startTransition(300);
            } else {
                this.f1608b.setImageBitmap(bitmap);
            }
            this.f1608b.setVisibility(0);
            m1580a(this.f1608b);
        }
    }

    protected void m1580a(ImageView imageView) {
    }

    public ImageView m1577a() {
        return this.f1608b;
    }

    public void m1582b(int i) {
        this.f1610d = i;
    }

    public void m1578a(int i) {
        this.f1611e = i;
    }
}

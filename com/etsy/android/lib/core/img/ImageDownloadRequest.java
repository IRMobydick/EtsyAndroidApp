package com.etsy.android.lib.core.img;

import android.support.annotation.Nullable;
import android.widget.ImageView;

/* renamed from: com.etsy.android.lib.core.img.d */
public class ImageDownloadRequest {
    private String f1614a;
    private ImageView f1615b;
    private int f1616c;
    private int f1617d;
    private ImageDownloadListener f1618e;

    public ImageDownloadRequest(String str, ImageView imageView) {
        this.f1616c = -1;
        this.f1617d = -1;
        this.f1614a = str;
        this.f1615b = imageView;
        this.f1618e = new ImageDownloadListener(this.f1615b);
    }

    public ImageDownloadListener m1593a() {
        return this.f1618e;
    }

    public void m1596a(ImageDownloadListener imageDownloadListener) {
        this.f1618e = imageDownloadListener;
    }

    public String m1598b() {
        return this.f1614a;
    }

    @Nullable
    public ImageView m1599c() {
        return this.f1615b;
    }

    public ImageDownloadRequest m1595a(int i, int i2) {
        this.f1616c = i;
        this.f1617d = i2;
        return this;
    }

    public int m1601d() {
        return this.f1616c;
    }

    public ImageDownloadRequest m1594a(int i) {
        this.f1616c = i;
        return this;
    }

    public int m1603e() {
        return this.f1617d;
    }

    public ImageDownloadRequest m1597b(int i) {
        this.f1617d = i;
        return this;
    }

    public boolean m1604f() {
        return this.f1618e.m1583b();
    }

    public void m1605g() {
        this.f1618e.m1584c();
    }

    public ImageDownloadRequest m1600c(int i) {
        this.f1618e.m1578a(i);
        return this;
    }

    public ImageDownloadRequest m1602d(int i) {
        this.f1618e.m1582b(i);
        return this;
    }
}

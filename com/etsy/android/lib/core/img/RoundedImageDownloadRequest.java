package com.etsy.android.lib.core.img;

import android.graphics.Bitmap;
import android.widget.ImageView;

/* renamed from: com.etsy.android.lib.core.img.h */
public class RoundedImageDownloadRequest extends ImageDownloadRequest {
    private int f1634a;

    /* renamed from: com.etsy.android.lib.core.img.h.1 */
    class RoundedImageDownloadRequest extends ImageDownloadListener {
        final /* synthetic */ ImageDownloadListener f1632a;
        final /* synthetic */ RoundedImageDownloadRequest f1633b;

        RoundedImageDownloadRequest(RoundedImageDownloadRequest roundedImageDownloadRequest, ImageView imageView, ImageDownloadListener imageDownloadListener) {
            this.f1633b = roundedImageDownloadRequest;
            this.f1632a = imageDownloadListener;
            super(imageView);
        }

        public void m1644a(Bitmap bitmap, boolean z) {
            if (this.f1633b.f1634a > 0) {
                bitmap = ImageHelper.m1628a(bitmap, this.f1633b.f1634a, this.f1633b.m1601d(), this.f1633b.m1603e());
            }
            this.f1632a.m1579a(bitmap, z);
        }
    }

    public RoundedImageDownloadRequest(String str, ImageView imageView, int i) {
        super(str, imageView);
        this.f1634a = i;
        m1596a(new RoundedImageDownloadRequest(this, m1599c()));
    }

    public RoundedImageDownloadRequest(String str, ImageDownloadListener imageDownloadListener, int i) {
        super(str, null);
        this.f1634a = i;
        m1596a(new RoundedImageDownloadRequest(this, null, imageDownloadListener));
    }
}

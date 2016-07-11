package com.etsy.android.uikit.adapter;

import com.etsy.android.lib.models.shopshare.ShareAnnotation;
import com.etsy.android.lib.util.p012a.ShopShareAnnotationUtil;
import com.etsy.android.uikit.view.TaggableImageView;

/* renamed from: com.etsy.android.uikit.adapter.a */
public class AnnotationAdapter implements TaggableImageView {
    final /* synthetic */ AnnotationAdapter f3922a;
    private ShareAnnotation f3923b;

    AnnotationAdapter(AnnotationAdapter annotationAdapter, ShareAnnotation shareAnnotation) {
        this.f3922a = annotationAdapter;
        this.f3923b = shareAnnotation;
    }

    public int m5303a() {
        return ShopShareAnnotationUtil.m3162a(this.f3923b.getCoordinates(), this.f3922a.mShareMedia != null ? this.f3922a.mShareMedia.getOriginalWidth() : this.f3922a.mBitmap.getWidth(), this.f3922a.mShareMedia != null ? this.f3922a.mShareMedia.getOriginalHeight() : this.f3922a.mBitmap.getHeight(), this.f3922a.mImageView).f1904x + this.f3922a.offsetX;
    }

    public int m5304b() {
        return ShopShareAnnotationUtil.m3162a(this.f3923b.getCoordinates(), this.f3922a.mShareMedia != null ? this.f3922a.mShareMedia.getOriginalWidth() : this.f3922a.mBitmap.getWidth(), this.f3922a.mShareMedia != null ? this.f3922a.mShareMedia.getOriginalHeight() : this.f3922a.mBitmap.getHeight(), this.f3922a.mImageView).f1905y + this.f3922a.offsetY;
    }

    public long m5305c() {
        return this.f3923b.getShareAnnotationId();
    }

    public ShareAnnotation m5306d() {
        return this.f3923b;
    }
}

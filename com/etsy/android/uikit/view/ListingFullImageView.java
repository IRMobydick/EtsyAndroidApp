package com.etsy.android.uikit.view;

import android.content.Context;
import android.util.AttributeSet;

public class ListingFullImageView extends FullImageView {
    public static final float ASPECT_RATIO_STANDARD = 0.75f;

    public ListingFullImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ListingFullImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ListingFullImageView(Context context) {
        super(context);
    }

    public void setUseStandardRatio(boolean z) {
        this.mAspectRatio = z ? ASPECT_RATIO_STANDARD : 0.0f;
    }

    protected void loadImage() {
        Object obj = Math.abs(this.mAspectRatio - ASPECT_RATIO_STANDARD) < 1.0E-7f ? 1 : null;
        int measuredWidth = getMeasuredWidth();
        this.mImageBatch.m1571a(obj != null ? this.mImage.get4to3ImageUrlForPixelWidth(measuredWidth) : this.mImage.getFullCardImageUrlForPixelWidth(measuredWidth), this, measuredWidth, getMeasuredHeight(), this.mLoadingColor);
    }
}

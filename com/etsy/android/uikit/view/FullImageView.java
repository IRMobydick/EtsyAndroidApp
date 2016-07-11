package com.etsy.android.uikit.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.IFullImage;

public class FullImageView extends ForegroundImageView {
    public static final float ASPECT_FULL_HEIGHT = 0.0f;
    public static final float ASPECT_RATIO_SQUARE = 1.0f;
    public static final int IMAGE_SHAPE_CIRCULAR = 1;
    public static final int IMAGE_SHAPE_RECTANGULAR = 2;
    public static final float SHOP_ABOUT_IMAGE_HEIGHT_RATIO = 0.6158f;
    protected float mAspectRatio;
    protected IFullImage mImage;
    protected ImageBatch mImageBatch;
    protected int mImageFullHeight;
    protected int mImageFullWidth;
    protected boolean mImageLoadRequested;
    protected int mImageShape;
    protected int mLoadingColor;

    public FullImageView(Context context) {
        super(context);
        this.mAspectRatio = ASPECT_FULL_HEIGHT;
        this.mImageFullWidth = IMAGE_SHAPE_CIRCULAR;
        this.mImageFullHeight = IMAGE_SHAPE_CIRCULAR;
        this.mImageShape = IMAGE_SHAPE_RECTANGULAR;
        init(context, null);
    }

    public FullImageView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAspectRatio = ASPECT_FULL_HEIGHT;
        this.mImageFullWidth = IMAGE_SHAPE_CIRCULAR;
        this.mImageFullHeight = IMAGE_SHAPE_CIRCULAR;
        this.mImageShape = IMAGE_SHAPE_RECTANGULAR;
        init(context, attributeSet);
    }

    public FullImageView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAspectRatio = ASPECT_FULL_HEIGHT;
        this.mImageFullWidth = IMAGE_SHAPE_CIRCULAR;
        this.mImageFullHeight = IMAGE_SHAPE_CIRCULAR;
        this.mImageShape = IMAGE_SHAPE_RECTANGULAR;
        init(context, attributeSet);
    }

    protected void init(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        if (attributeSet == null || context == null) {
            setHeightRatio(ASPECT_FULL_HEIGHT);
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.FullImageView);
        setHeightRatio(obtainStyledAttributes.getFloat(R.FullImageView_imageAspect, ASPECT_FULL_HEIGHT));
        obtainStyledAttributes.recycle();
    }

    public void setHeightRatio(float f) {
        this.mAspectRatio = f;
    }

    public void setImageInfo(IFullImage iFullImage, ImageBatch imageBatch) {
        setImageInfo(iFullImage, imageBatch, IMAGE_SHAPE_RECTANGULAR);
    }

    public void setImageInfo(IFullImage iFullImage, ImageBatch imageBatch, int i) {
        this.mImage = iFullImage;
        this.mImageBatch = imageBatch;
        this.mImageLoadRequested = false;
        this.mImageShape = i;
        if (iFullImage != null) {
            this.mLoadingColor = iFullImage.getImageColor();
            this.mImageFullHeight = iFullImage.getFullHeight();
            this.mImageFullWidth = iFullImage.getFullWidth();
        }
        requestLayout();
    }

    protected void onMeasure(int i, int i2) {
        float f;
        if ((((double) Math.abs(this.mAspectRatio - ASPECT_FULL_HEIGHT)) < 1.0E-7d ? IMAGE_SHAPE_CIRCULAR : null) != null) {
            f = ((float) this.mImageFullHeight) / ((float) this.mImageFullWidth);
        } else {
            f = this.mAspectRatio;
        }
        int size = MeasureSpec.getSize(i);
        setMeasuredDimension(size, (int) (f * ((float) size)));
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.mImage != null && !this.mImageLoadRequested) {
            this.mImageLoadRequested = true;
            loadImage();
        }
    }

    protected void loadImage() {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        String fullCardImageUrlForPixelWidth = this.mImage.getFullCardImageUrlForPixelWidth(measuredWidth);
        if (this.mImageShape == IMAGE_SHAPE_RECTANGULAR) {
            this.mImageBatch.m1571a(fullCardImageUrlForPixelWidth, this, measuredWidth, measuredHeight, this.mLoadingColor);
        } else {
            this.mImageBatch.m1575b(fullCardImageUrlForPixelWidth, this, measuredWidth, this.mLoadingColor);
        }
    }

    public void cleanUp() {
        this.mImageBatch = null;
        this.mImage = null;
        setImageDrawable(null);
    }
}

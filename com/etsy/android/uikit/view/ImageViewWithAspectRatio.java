package com.etsy.android.uikit.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.core.img.ImageDownloadListener;
import com.etsy.android.lib.core.img.ImageDownloadRequest;
import com.etsy.android.lib.models.BaseModelImage;
import com.google.android.gms.gcm.Task;

public class ImageViewWithAspectRatio extends ImageView {
    public static final double STANDARD_IMAGE_ASPECT_RATIO = 0.75d;
    private CropType mCropType;
    private boolean mDoMaintainAspectRatio;
    private double mHeightRatio;
    private BaseModelImage mImage;
    private ImageBatch mImageBatch;
    private boolean mImageLoadRequested;
    private ImageDownloadListener mListener;
    private int mLoadingColor;
    private boolean mUseStandardRatio;

    /* renamed from: com.etsy.android.uikit.view.ImageViewWithAspectRatio.1 */
    /* synthetic */ class C10181 {
        static final /* synthetic */ int[] f4213a;

        static {
            f4213a = new int[CropType.values().length];
            try {
                f4213a[CropType.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4213a[CropType.BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum CropType {
        TOP,
        BOTTOM,
        NONE
    }

    public ImageViewWithAspectRatio(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mUseStandardRatio = false;
        this.mDoMaintainAspectRatio = false;
        this.mCropType = CropType.NONE;
        init(context, attributeSet);
    }

    public ImageViewWithAspectRatio(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mUseStandardRatio = false;
        this.mDoMaintainAspectRatio = false;
        this.mCropType = CropType.NONE;
        init(context, attributeSet);
    }

    public ImageViewWithAspectRatio(Context context) {
        super(context);
        this.mUseStandardRatio = false;
        this.mDoMaintainAspectRatio = false;
        this.mCropType = CropType.NONE;
        init(context, null);
    }

    private void init(Context context, @Nullable AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.ImageViewWithAspectRatio);
            setAspectRatio((double) obtainStyledAttributes.getFloat(R.ImageViewWithAspectRatio_heightRatio, 0.0f));
            this.mDoMaintainAspectRatio = obtainStyledAttributes.getBoolean(R.ImageViewWithAspectRatio_doMaintainAspect, this.mDoMaintainAspectRatio);
            obtainStyledAttributes.recycle();
        }
    }

    public void setAspectRatio(double d) {
        this.mHeightRatio = d;
        this.mUseStandardRatio = Math.abs(d - STANDARD_IMAGE_ASPECT_RATIO) < 1.0E-7d;
    }

    public void setUseStandardRatio(boolean z) {
        this.mUseStandardRatio = z;
        if (z) {
            this.mHeightRatio = STANDARD_IMAGE_ASPECT_RATIO;
        }
    }

    public void setImageDownloadListener(ImageDownloadListener imageDownloadListener) {
        this.mListener = imageDownloadListener;
    }

    public void setImageInfo(BaseModelImage baseModelImage, ImageBatch imageBatch) {
        this.mImage = baseModelImage;
        this.mImageBatch = imageBatch;
        this.mImageLoadRequested = false;
        if (baseModelImage != null) {
            this.mLoadingColor = baseModelImage.getImageColor();
        }
        requestLayout();
    }

    private void specialCrop() {
        if (getDrawable() != null) {
            float f;
            float f2;
            float f3;
            int intrinsicWidth = getDrawable().getIntrinsicWidth();
            int intrinsicHeight = getDrawable().getIntrinsicHeight();
            int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
            int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
            Matrix matrix = new Matrix();
            if (intrinsicWidth * height > width * intrinsicHeight) {
                f = ((float) height) / ((float) intrinsicHeight);
                f2 = f;
                f = (((float) width) - (((float) intrinsicWidth) * f)) * 0.5f;
                f3 = 0.0f;
            } else {
                f = ((float) width) / ((float) intrinsicWidth);
                f3 = ((float) height) - (((float) intrinsicHeight) * f);
                f2 = f;
                f = 0.0f;
            }
            matrix.setScale(f2, f2);
            switch (C10181.f4213a[this.mCropType.ordinal()]) {
                case Task.NETWORK_STATE_UNMETERED /*1*/:
                    matrix.postTranslate((float) ((int) (f + 0.5f)), 0.0f);
                    break;
                case Task.NETWORK_STATE_ANY /*2*/:
                    matrix.postTranslate((float) ((int) (f + 0.5f)), (float) ((int) (f3 + 0.5f)));
                    break;
                default:
                    matrix.postTranslate((float) ((int) (f + 0.5f)), (float) ((int) ((f3 * 0.5f) + 0.5f)));
                    break;
            }
            setScaleType(ScaleType.MATRIX);
            setImageMatrix(matrix);
        }
    }

    public void setSpecialCrop(CropType cropType) {
        if (cropType != null) {
            this.mCropType = cropType;
        }
    }

    public void setDoMaintainAspectRatio(boolean z) {
        this.mDoMaintainAspectRatio = z;
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        double fullHeight = (this.mImage == null || !this.mDoMaintainAspectRatio) ? this.mHeightRatio : (double) (((float) this.mImage.getFullHeight()) / ((float) this.mImage.getFullWidth()));
        this.mHeightRatio = fullHeight;
        int i3 = (int) (((double) size) * this.mHeightRatio);
        if (this.mImage != null) {
            setMeasuredDimension(size, i3);
            if (!this.mImageLoadRequested) {
                setBackgroundColor(this.mLoadingColor);
                if (size > 0) {
                    String str;
                    this.mImageLoadRequested = true;
                    if (this.mUseStandardRatio) {
                        str = this.mImage.get4to3ImageUrlForPixelWidth(size);
                    } else {
                        str = this.mImage.getImageUrlForPixelWidth(size);
                    }
                    ImageDownloadRequest imageDownloadRequest = new ImageDownloadRequest(str, this);
                    imageDownloadRequest.m1595a(size, i3);
                    if (!(this.mImageBatch.m1573a(str, size, i3) || this.mListener == null)) {
                        imageDownloadRequest.m1596a(this.mListener);
                    }
                    this.mImageBatch.m1565a(imageDownloadRequest);
                }
            }
        } else if (this.mHeightRatio != 0.0d) {
            setMeasuredDimension(size, i3);
        } else {
            super.onMeasure(i, i2);
        }
        if (this.mCropType != CropType.NONE) {
            specialCrop();
        }
    }

    public void cleanup() {
        setImageDrawable(null);
    }
}

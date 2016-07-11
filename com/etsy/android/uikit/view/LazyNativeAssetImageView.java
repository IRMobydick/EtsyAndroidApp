package com.etsy.android.uikit.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.etsy.android.lib.R;

public class LazyNativeAssetImageView extends ImageView {
    @DrawableRes
    private int mImgResId;

    public LazyNativeAssetImageView(Context context) {
        super(context);
        this.mImgResId = -1;
    }

    public LazyNativeAssetImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mImgResId = -1;
        init(context, attributeSet);
    }

    public LazyNativeAssetImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mImgResId = -1;
        init(context, attributeSet);
    }

    @TargetApi(21)
    public LazyNativeAssetImageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mImgResId = -1;
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.LazyNativeAssetImageView, 0, 0);
        this.mImgResId = obtainStyledAttributes.getResourceId(R.LazyNativeAssetImageView_img, -1);
        obtainStyledAttributes.recycle();
    }

    public void setImageDrawable(Drawable drawable) {
        this.mImgResId = -1;
        super.setImageDrawable(drawable);
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.mImgResId = -1;
        super.setImageBitmap(bitmap);
    }

    public void setImageURI(Uri uri) {
        this.mImgResId = -1;
        super.setImageURI(uri);
    }

    public void setImageResource(int i) {
        this.mImgResId = i;
        showImageResIfVisible(i, getVisibility());
    }

    protected void onVisibilityChanged(@NonNull View view, int i) {
        super.onVisibilityChanged(view, i);
        showImageResIfVisible(this.mImgResId, i);
    }

    private void showImageResIfVisible(int i, int i2) {
        if (i != -1) {
            if (i2 == 0) {
                super.setImageResource(i);
            } else {
                super.setImageDrawable(null);
            }
        }
    }
}

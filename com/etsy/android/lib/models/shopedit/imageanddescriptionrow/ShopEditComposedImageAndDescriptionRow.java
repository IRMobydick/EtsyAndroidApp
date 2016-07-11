package com.etsy.android.lib.models.shopedit.imageanddescriptionrow;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView.ScaleType;
import com.etsy.android.lib.models.IFullImage;

/* renamed from: com.etsy.android.lib.models.shopedit.imageanddescriptionrow.f */
abstract class ShopEditComposedImageAndDescriptionRow implements IShopEditImageAndDescriptionRow {
    @NonNull
    public abstract ShopEditImageAndDescriptionRow getWrappedImageAndDescriptionRow();

    ShopEditComposedImageAndDescriptionRow() {
    }

    @Nullable
    public IFullImage getImage() {
        return getWrappedImageAndDescriptionRow().getImage();
    }

    @Nullable
    public Drawable getDrawable() {
        return getWrappedImageAndDescriptionRow().getDrawable();
    }

    @Nullable
    public CharSequence getDescription() {
        return getWrappedImageAndDescriptionRow().getDescription();
    }

    @Nullable
    public CharSequence getHint() {
        return getWrappedImageAndDescriptionRow().getHint();
    }

    public int getImageType() {
        return getWrappedImageAndDescriptionRow().getImageType();
    }

    public int getImageShape() {
        return getWrappedImageAndDescriptionRow().getImageShape();
    }

    public float getHeightRatio() {
        return getWrappedImageAndDescriptionRow().getHeightRatio();
    }

    public ScaleType getScaleType() {
        return getWrappedImageAndDescriptionRow().getScaleType();
    }

    public int getViewType() {
        return getWrappedImageAndDescriptionRow().getViewType();
    }

    public void restoreComplexStateIfNecessary(@NonNull Context context) {
    }

    public boolean isLoading() {
        return getWrappedImageAndDescriptionRow().isLoading();
    }

    public void setLoading(boolean z) {
        getWrappedImageAndDescriptionRow().setLoading(z);
    }
}

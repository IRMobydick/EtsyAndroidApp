package com.etsy.android.lib.models.shopedit.brandingimagerow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.shopedit.ShopEditActionableItem;
import com.etsy.android.lib.models.shopedit.ShopEditPresentationItem;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class ShopEditBrandingImageRow implements ShopEditActionableItem, ShopEditPresentationItem {
    public static final int IMAGE_TYPE_COVER_PHOTO = 1;
    public static final int IMAGE_TYPE_ICON = 2;
    Image mImage;
    int mImageType;

    @Retention(RetentionPolicy.SOURCE)
    public @interface BrandingImageType {
    }

    @NonNull
    public abstract CharSequence getHintText();

    ShopEditBrandingImageRow() {
    }

    public ShopEditBrandingImageRow(@Nullable Image image, int i) {
        this.mImage = image;
        this.mImageType = i;
    }

    @Nullable
    public Image getImage() {
        return this.mImage;
    }

    public int getImageType() {
        return this.mImageType;
    }

    public int getViewType() {
        return IMAGE_TYPE_COVER_PHOTO;
    }

    public void restoreComplexStateIfNecessary(@NonNull Context context) {
    }
}

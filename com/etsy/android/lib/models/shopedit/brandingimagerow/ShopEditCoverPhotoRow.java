package com.etsy.android.lib.models.shopedit.brandingimagerow;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.shopedit.ShopEditActionableItem;
import com.etsy.android.lib.models.shopedit.ShopEditDelegate;
import com.etsy.android.lib.models.shopedit.ShopEditPresentationItem;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import org.parceler.Parcel;

@Parcel
public class ShopEditCoverPhotoRow extends ShopEditBrandingImageRow implements ShopEditActionableItem {
    int mBrandingOption;
    String mHintText;

    ShopEditCoverPhotoRow() {
    }

    public ShopEditCoverPhotoRow(@Nullable Image image, int i, @NonNull Context context) {
        super(image, 1);
        this.mBrandingOption = i;
        this.mHintText = context.getString(R.add_a_cover_photo);
    }

    @NonNull
    public CharSequence getHintText() {
        return this.mHintText;
    }

    public void editActionInitiated(int i, @NonNull ShopEditDelegate shopEditDelegate, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter) {
        shopEditDelegate.editCoverPhoto(this.mImage, this.mBrandingOption);
    }

    public boolean isActionEnabled() {
        return true;
    }

    public void updateWithEditResult(@NonNull ShopEditDelegate shopEditDelegate, @NonNull RecyclerView recyclerView, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter, int i, int i2, @NonNull Intent intent, @NonNull Context context, int i3) {
    }
}

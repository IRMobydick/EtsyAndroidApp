package com.etsy.android.lib.models.shopedit.brandingimagerow;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.shopedit.ShopEditDelegate;
import com.etsy.android.lib.models.shopedit.ShopEditPresentationItem;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;
import org.parceler.Parcels;

@Parcel
public class ShopEditIconRow extends ShopEditBrandingImageRow {
    ShopEditIconRow() {
    }

    public ShopEditIconRow(@Nullable Image image) {
        super(image, 2);
    }

    @NonNull
    public CharSequence getHintText() {
        return StringUtils.EMPTY;
    }

    public void editActionInitiated(int i, @NonNull ShopEditDelegate shopEditDelegate, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter) {
        shopEditDelegate.editShopIcon(this.mImage);
    }

    public boolean isActionEnabled() {
        return true;
    }

    public void updateWithEditResult(@NonNull ShopEditDelegate shopEditDelegate, @NonNull RecyclerView recyclerView, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter, int i, int i2, @NonNull Intent intent, @NonNull Context context, int i3) {
        if (i == 1001 && i2 == 1011 && intent.hasExtra("shop_icon")) {
            Image image = (Image) Parcels.m7495a(intent.getParcelableExtra("shop_icon"));
            if (image != null) {
                this.mImage = image;
                baseRecyclerViewAdapter.notifyItemChanged(i3);
            }
        }
    }
}

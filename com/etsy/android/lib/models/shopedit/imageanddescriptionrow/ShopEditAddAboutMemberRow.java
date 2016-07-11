package com.etsy.android.lib.models.shopedit.imageanddescriptionrow;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView.ScaleType;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.IFullImage;
import com.etsy.android.lib.models.ShopAboutMember;
import com.etsy.android.lib.models.shopedit.ShopEditActionableItem;
import com.etsy.android.lib.models.shopedit.ShopEditDelegate;
import com.etsy.android.lib.models.shopedit.ShopEditPresentationItem;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.view.FullImageView;
import org.parceler.Parcel;
import org.parceler.Parcels;

@Parcel
public class ShopEditAddAboutMemberRow extends ShopEditComposedImageAndDescriptionRow implements ShopEditActionableItem {
    ShopEditImageAndDescriptionRow mImageAndDescriptionRow;

    public /* bridge */ /* synthetic */ CharSequence getDescription() {
        return super.getDescription();
    }

    public /* bridge */ /* synthetic */ Drawable getDrawable() {
        return super.getDrawable();
    }

    public /* bridge */ /* synthetic */ float getHeightRatio() {
        return super.getHeightRatio();
    }

    public /* bridge */ /* synthetic */ CharSequence getHint() {
        return super.getHint();
    }

    public /* bridge */ /* synthetic */ IFullImage getImage() {
        return super.getImage();
    }

    public /* bridge */ /* synthetic */ int getImageShape() {
        return super.getImageShape();
    }

    public /* bridge */ /* synthetic */ int getImageType() {
        return super.getImageType();
    }

    public /* bridge */ /* synthetic */ ScaleType getScaleType() {
        return super.getScaleType();
    }

    public /* bridge */ /* synthetic */ int getViewType() {
        return super.getViewType();
    }

    public /* bridge */ /* synthetic */ boolean isLoading() {
        return super.isLoading();
    }

    public /* bridge */ /* synthetic */ void setLoading(boolean z) {
        super.setLoading(z);
    }

    ShopEditAddAboutMemberRow() {
    }

    public ShopEditAddAboutMemberRow(@NonNull Context context) {
        this.mImageAndDescriptionRow = ShopEditImageAndDescriptionRow.newAddImageRow(FullImageView.ASPECT_RATIO_SQUARE, 1, R.add_a_member, context);
    }

    @NonNull
    public ShopEditImageAndDescriptionRow getWrappedImageAndDescriptionRow() {
        return this.mImageAndDescriptionRow;
    }

    public void editActionInitiated(int i, @NonNull ShopEditDelegate shopEditDelegate, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter) {
        shopEditDelegate.addShopMember();
    }

    public boolean isActionEnabled() {
        return true;
    }

    public void updateWithEditResult(@NonNull ShopEditDelegate shopEditDelegate, @NonNull RecyclerView recyclerView, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter, int i, int i2, @NonNull Intent intent, @NonNull Context context, int i3) {
        if (i == 1001 && i2 == 1011) {
            ShopAboutMember shopAboutMember = (ShopAboutMember) Parcels.m7495a(intent.getParcelableExtra("shop_member"));
            if (shopAboutMember != null) {
                baseRecyclerViewAdapter.getItems().add(i3, new ShopEditAboutMemberRow(shopAboutMember, context));
                baseRecyclerViewAdapter.notifyItemInserted(i3);
            }
        }
    }

    public void restoreComplexStateIfNecessary(@NonNull Context context) {
        this.mImageAndDescriptionRow.setDrawable(ShopEditImageAndDescriptionRow.buildAddImageRowDrawable(1, context));
    }
}

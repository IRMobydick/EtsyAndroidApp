package com.etsy.android.lib.models.shopedit.imageanddescriptionrow;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView.ScaleType;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.IFullImage;
import com.etsy.android.lib.models.ShopAboutImage;
import com.etsy.android.lib.models.shopedit.ShopEditActionableItem;
import com.etsy.android.lib.models.shopedit.ShopEditDelegate;
import com.etsy.android.lib.models.shopedit.ShopEditPresentationItem;
import com.etsy.android.lib.models.shopedit.imageanddescriptionrow.ShopEditImageAndDescriptionRow.Builder;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.view.FullImageView;
import java.util.List;
import org.parceler.Parcel;
import org.parceler.Parcels;

@Parcel
public class ShopEditAboutImageRow extends ShopEditComposedImageAndDescriptionRow implements ShopEditActionableItem {
    ShopAboutImage mAboutImage;
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

    public /* bridge */ /* synthetic */ void restoreComplexStateIfNecessary(Context context) {
        super.restoreComplexStateIfNecessary(context);
    }

    public /* bridge */ /* synthetic */ void setLoading(boolean z) {
        super.setLoading(z);
    }

    ShopEditAboutImageRow() {
    }

    public ShopEditAboutImageRow(@NonNull ShopAboutImage shopAboutImage, @NonNull Context context) {
        this.mImageAndDescriptionRow = buildImageAndDescriptionRowFromAboutImage(shopAboutImage, context);
        this.mAboutImage = shopAboutImage;
    }

    @NonNull
    public ShopEditImageAndDescriptionRow getWrappedImageAndDescriptionRow() {
        return this.mImageAndDescriptionRow;
    }

    public void editActionInitiated(int i, @NonNull ShopEditDelegate shopEditDelegate, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter) {
        shopEditDelegate.editAboutImage(this.mAboutImage);
    }

    public void updateWithEditResult(@NonNull ShopEditDelegate shopEditDelegate, @NonNull RecyclerView recyclerView, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter, int i, int i2, @NonNull Intent intent, @NonNull Context context, int i3) {
        if (i == 1001 && i2 == 1011) {
            ShopAboutImage shopAboutImage = (ShopAboutImage) Parcels.m7495a(intent.getParcelableExtra("about_image"));
            if (shopAboutImage != null) {
                this.mImageAndDescriptionRow = buildImageAndDescriptionRowFromAboutImage(shopAboutImage, context);
                this.mAboutImage = shopAboutImage;
                baseRecyclerViewAdapter.notifyItemChanged(i3);
                return;
            }
            int i4;
            List items = baseRecyclerViewAdapter.getItems();
            int size = items.size();
            for (int i5 = i3; i5 < size; i5++) {
                if (!(((ShopEditPresentationItem) items.get(i5)) instanceof ShopEditAboutImageRow)) {
                    i4 = i5;
                    break;
                }
            }
            i4 = -1;
            if (!(items.get(i4) instanceof ShopEditAddAboutImageRow)) {
                items.add(i4, new ShopEditAddAboutImageRow(context));
                baseRecyclerViewAdapter.notifyItemInserted(i4);
            }
            items.remove(i3);
            baseRecyclerViewAdapter.notifyItemRemoved(i3);
        }
    }

    private static ShopEditImageAndDescriptionRow buildImageAndDescriptionRowFromAboutImage(@NonNull ShopAboutImage shopAboutImage, @NonNull Context context) {
        return new Builder(shopAboutImage.getImage()).description(shopAboutImage.getCaption()).hint(context.getString(R.caption_hint)).imageShape(2).heightRatio(FullImageView.SHOP_ABOUT_IMAGE_HEIGHT_RATIO).build();
    }

    public boolean isActionEnabled() {
        return true;
    }
}

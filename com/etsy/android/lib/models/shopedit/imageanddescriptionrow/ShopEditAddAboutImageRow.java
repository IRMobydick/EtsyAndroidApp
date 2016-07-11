package com.etsy.android.lib.models.shopedit.imageanddescriptionrow;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView.ScaleType;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.models.IFullImage;
import com.etsy.android.lib.models.ShopAboutImage;
import com.etsy.android.lib.models.shopedit.ShopEditActionableItem;
import com.etsy.android.lib.models.shopedit.ShopEditDelegate;
import com.etsy.android.lib.models.shopedit.ShopEditPresentationItem;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.view.FullImageView;
import java.util.List;
import org.parceler.Parcel;
import org.parceler.Parcels;

@Parcel
public class ShopEditAddAboutImageRow extends ShopEditComposedImageAndDescriptionRow implements ShopEditActionableItem {
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

    ShopEditAddAboutImageRow() {
    }

    public ShopEditAddAboutImageRow(@NonNull Context context) {
        this.mImageAndDescriptionRow = ShopEditImageAndDescriptionRow.newAddImageRow(FullImageView.SHOP_ABOUT_IMAGE_HEIGHT_RATIO, 2, R.add_a_photo_and_caption, context);
    }

    @NonNull
    public ShopEditImageAndDescriptionRow getWrappedImageAndDescriptionRow() {
        return this.mImageAndDescriptionRow;
    }

    public void editActionInitiated(int i, @NonNull ShopEditDelegate shopEditDelegate, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter) {
        shopEditDelegate.addAboutImage();
    }

    public boolean isActionEnabled() {
        return true;
    }

    public void updateWithEditResult(@NonNull ShopEditDelegate shopEditDelegate, @NonNull RecyclerView recyclerView, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter, int i, int i2, @NonNull Intent intent, @NonNull Context context, int i3) {
        if (i == 1001 && i2 == 1011) {
            ShopAboutImage shopAboutImage = (ShopAboutImage) Parcels.m7495a(intent.getParcelableExtra("about_image"));
            if (shopAboutImage != null) {
                List items = baseRecyclerViewAdapter.getItems();
                items.add(i3, new ShopEditAboutImageRow(shopAboutImage, context));
                baseRecyclerViewAdapter.notifyItemInserted(i3);
                int d = EtsyConfig.m837a().m869d().m886d(EtsyConfigKeys.f1244e);
                int i4 = i3;
                int i5 = 0;
                while (i4 >= 0 && (((ShopEditPresentationItem) items.get(i4)) instanceof ShopEditAboutImageRow)) {
                    i5++;
                    i4--;
                }
                if (i5 >= d) {
                    int i6 = i3 + 1;
                    items.remove(i6);
                    baseRecyclerViewAdapter.notifyItemRemoved(i6);
                }
            }
        }
    }

    public void restoreComplexStateIfNecessary(@NonNull Context context) {
        this.mImageAndDescriptionRow.setDrawable(ShopEditImageAndDescriptionRow.buildAddImageRowDrawable(2, context));
    }
}

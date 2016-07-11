package com.etsy.android.lib.models.shopedit.imageanddescriptionrow;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.widget.ImageView.ScaleType;
import com.etsy.android.lib.models.IFullImage;
import com.etsy.android.lib.models.ShopAboutMember;
import com.etsy.android.lib.models.shopedit.ShopEditActionableItem;
import com.etsy.android.lib.models.shopedit.ShopEditDelegate;
import com.etsy.android.lib.models.shopedit.ShopEditPresentationItem;
import com.etsy.android.lib.models.shopedit.imageanddescriptionrow.ShopEditImageAndDescriptionRow.Builder;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.util.GraphikUtil;
import com.etsy.android.uikit.view.FullImageView;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;
import org.parceler.Parcels;

@Parcel
public class ShopEditAboutMemberRow extends ShopEditComposedImageAndDescriptionRow implements ShopEditActionableItem {
    ShopEditImageAndDescriptionRow mImageAndDescriptionRow;
    ShopAboutMember mShopAboutMember;

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

    ShopEditAboutMemberRow() {
    }

    public ShopEditAboutMemberRow(@NonNull ShopAboutMember shopAboutMember, @NonNull Context context) {
        this.mShopAboutMember = shopAboutMember;
        this.mImageAndDescriptionRow = imageAndDescriptionRowFromAboutMember(shopAboutMember, context);
    }

    @NonNull
    public ShopEditImageAndDescriptionRow getWrappedImageAndDescriptionRow() {
        return this.mImageAndDescriptionRow;
    }

    public void editActionInitiated(int i, @NonNull ShopEditDelegate shopEditDelegate, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter) {
        shopEditDelegate.editShopMember(this.mShopAboutMember);
    }

    public boolean isActionEnabled() {
        return true;
    }

    public void updateWithEditResult(@NonNull ShopEditDelegate shopEditDelegate, @NonNull RecyclerView recyclerView, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter, int i, int i2, @NonNull Intent intent, @NonNull Context context, int i3) {
        if (i == 1001 && i2 == 1011 && intent.hasExtra("shop_member")) {
            ShopAboutMember shopAboutMember = (ShopAboutMember) Parcels.m7495a(intent.getParcelableExtra("shop_member"));
            if (shopAboutMember != null) {
                this.mImageAndDescriptionRow = imageAndDescriptionRowFromAboutMember(shopAboutMember, context);
                baseRecyclerViewAdapter.notifyItemChanged(i3);
                this.mShopAboutMember = shopAboutMember;
                return;
            }
            baseRecyclerViewAdapter.getItems().remove(i3);
            baseRecyclerViewAdapter.notifyItemRemoved(i3);
        }
    }

    private static ShopEditImageAndDescriptionRow imageAndDescriptionRowFromAboutMember(@NonNull ShopAboutMember shopAboutMember, @NonNull Context context) {
        return new Builder(shopAboutMember.getImage()).description(buildShopAboutMemberDescription(shopAboutMember, context)).imageShape(1).heightRatio(FullImageView.ASPECT_RATIO_SQUARE).build();
    }

    private static CharSequence buildShopAboutMemberDescription(@NonNull ShopAboutMember shopAboutMember, @NonNull Context context) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(shopAboutMember.getName() + "\n" + StringUtils.join(shopAboutMember.getCapitalizedRoleList(), ", "));
        spannableStringBuilder.setSpan(GraphikUtil.m5544a(context), 0, spannableStringBuilder.length(), 33);
        return spannableStringBuilder.append("\n").append(shopAboutMember.getBio());
    }
}

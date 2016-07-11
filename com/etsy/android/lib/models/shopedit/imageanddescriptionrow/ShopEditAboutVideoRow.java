package com.etsy.android.lib.models.shopedit.imageanddescriptionrow;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView.ScaleType;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.IFullImage;
import com.etsy.android.lib.models.ShopAbout;
import com.etsy.android.lib.models.ShopAboutVideo;
import com.etsy.android.lib.models.shopedit.ShopEditActionableItem;
import com.etsy.android.lib.models.shopedit.ShopEditDelegate;
import com.etsy.android.lib.models.shopedit.ShopEditPresentationItem;
import com.etsy.android.lib.models.shopedit.ShopVideoShareData;
import com.etsy.android.lib.models.shopedit.imageanddescriptionrow.ShopEditImageAndDescriptionRow.Builder;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.view.FullImageView;
import org.parceler.Parcel;

@Parcel
public class ShopEditAboutVideoRow extends ShopEditComposedImageAndDescriptionRow implements ShopEditActionableItem {
    boolean mCurrentlyUploadingVideo;
    ShopEditImageAndDescriptionRow mImageAndDescriptionRow;
    ShopVideoShareData mShareData;
    @Nullable
    ShopAboutVideo mVideo;

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

    ShopEditAboutVideoRow() {
    }

    public ShopEditAboutVideoRow(boolean z, @Nullable ShopAbout shopAbout, @NonNull Context context) {
        this.mShareData = new ShopVideoShareData(shopAbout);
        ShopAboutVideo shopAboutVideo = null;
        if (!(shopAbout == null || shopAbout.getVideos() == null || shopAbout.getVideos().isEmpty())) {
            shopAboutVideo = (ShopAboutVideo) shopAbout.getVideos().get(0);
        }
        updateContentForState(shopAboutVideo, z, context);
    }

    @NonNull
    public ShopEditImageAndDescriptionRow getWrappedImageAndDescriptionRow() {
        return this.mImageAndDescriptionRow;
    }

    public void updateContentForState(@Nullable ShopAboutVideo shopAboutVideo, boolean z, @NonNull Context context) {
        ShopEditImageAndDescriptionRow newAddImageRow;
        this.mCurrentlyUploadingVideo = z;
        this.mVideo = shopAboutVideo;
        if (shopAboutVideo == null && !z) {
            newAddImageRow = ShopEditImageAndDescriptionRow.newAddImageRow(FullImageView.SHOP_ABOUT_IMAGE_HEIGHT_RATIO, 2, R.add_a_video, context);
        } else if (shopAboutVideo != null && shopAboutVideo.hasError()) {
            newAddImageRow = ShopEditImageAndDescriptionRow.newErrorRow(FullImageView.SHOP_ABOUT_IMAGE_HEIGHT_RATIO, 2, R.shop_video_error_hint, context);
        } else if (shopAboutVideo == null || !shopAboutVideo.videoIsReady()) {
            newAddImageRow = new Builder((Drawable) null).description(context.getString(R.shop_video_upload_hint)).heightRatio(FullImageView.SHOP_ABOUT_IMAGE_HEIGHT_RATIO).build();
            newAddImageRow.setLoading(true);
        } else {
            newAddImageRow = new Builder(shopAboutVideo.getThumbnail().getWrappedImage()).description(context.getString(R.video_hint)).heightRatio(FullImageView.SHOP_ABOUT_IMAGE_HEIGHT_RATIO).build();
        }
        this.mImageAndDescriptionRow = newAddImageRow;
    }

    public void editActionInitiated(int i, @NonNull ShopEditDelegate shopEditDelegate, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter) {
        shopEditDelegate.editShopVideo(this.mVideo, this.mShareData);
    }

    public boolean isActionEnabled() {
        return this.mCurrentlyUploadingVideo;
    }

    public void updateWithEditResult(@NonNull ShopEditDelegate shopEditDelegate, @NonNull RecyclerView recyclerView, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter, int i, int i2, @NonNull Intent intent, @NonNull Context context, int i3) {
        boolean z = false;
        if (i == 1001 && i2 == 1011) {
            if (!intent.getBooleanExtra("shop_video_deleted", false)) {
                z = true;
            }
            updateContentForState(null, z, context);
            baseRecyclerViewAdapter.notifyItemChanged(i3);
            shopEditDelegate.reloadContent();
        }
    }
}

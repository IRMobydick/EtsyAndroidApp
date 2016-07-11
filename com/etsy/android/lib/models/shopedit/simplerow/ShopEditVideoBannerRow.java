package com.etsy.android.lib.models.shopedit.simplerow;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import com.etsy.android.lib.models.ShopAbout;
import com.etsy.android.lib.models.ShopAboutVideo;
import com.etsy.android.lib.models.shopedit.ShopEditActionableItem;
import com.etsy.android.lib.models.shopedit.ShopEditDelegate;
import com.etsy.android.lib.models.shopedit.ShopEditPresentationItem;
import com.etsy.android.lib.models.shopedit.ShopEditRemovableItem;
import com.etsy.android.lib.models.shopedit.ShopVideoShareData;
import com.etsy.android.lib.models.shopedit.imageanddescriptionrow.ShopEditAboutVideoRow;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class ShopEditVideoBannerRow extends ShopEditSimpleItemRow implements ShopEditActionableItem, ShopEditRemovableItem {
    ShopVideoShareData mShareData;
    @Nullable
    ShopAboutVideo mVideo;

    public ShopEditVideoBannerRow(@Nullable ShopAbout shopAbout) {
        super(10);
        ShopAboutVideo shopAboutVideo = null;
        if (!(shopAbout == null || shopAbout.getVideos() == null || shopAbout.getVideos().isEmpty())) {
            shopAboutVideo = (ShopAboutVideo) shopAbout.getVideos().get(0);
        }
        this.mVideo = shopAboutVideo;
        this.mShareData = new ShopVideoShareData(shopAbout);
    }

    public ShopEditVideoBannerRow() {
        super(10);
    }

    public void editActionInitiated(int i, @NonNull ShopEditDelegate shopEditDelegate, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter) {
        baseRecyclerViewAdapter.getItems().remove(i);
        baseRecyclerViewAdapter.notifyItemRemoved(i);
        shopEditDelegate.editShopVideo(this.mVideo, this.mShareData);
    }

    public boolean isActionEnabled() {
        return true;
    }

    public void updateWithEditResult(@NonNull ShopEditDelegate shopEditDelegate, @NonNull RecyclerView recyclerView, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter, int i, int i2, @NonNull Intent intent, @NonNull Context context, int i3) {
        ShopEditAboutVideoRow shopEditAboutVideoRow;
        List items = baseRecyclerViewAdapter.getItems();
        int i4 = 0;
        int size = items.size();
        while (i4 < size) {
            ShopEditPresentationItem shopEditPresentationItem = (ShopEditPresentationItem) items.get(i4);
            if (shopEditPresentationItem instanceof ShopEditAboutVideoRow) {
                shopEditAboutVideoRow = (ShopEditAboutVideoRow) shopEditPresentationItem;
                break;
            }
            i4++;
        }
        i4 = -1;
        shopEditAboutVideoRow = null;
        if (shopEditAboutVideoRow != null && i == 1001 && i2 == 1011) {
            shopEditAboutVideoRow.updateContentForState(null, !intent.getBooleanExtra("shop_video_deleted", false), context);
            baseRecyclerViewAdapter.notifyItemChanged(i3);
            shopEditDelegate.reloadContent();
            recyclerView.scrollToPosition(i4 + 1);
        }
    }
}

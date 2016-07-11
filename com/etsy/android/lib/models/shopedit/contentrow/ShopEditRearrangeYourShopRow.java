package com.etsy.android.lib.models.shopedit.contentrow;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.shopedit.ShopEditActionableItem;
import com.etsy.android.lib.models.shopedit.ShopEditDelegate;
import com.etsy.android.lib.models.shopedit.ShopEditPresentationItem;
import com.etsy.android.lib.models.shopedit.contentrow.ShopEditContentRow.Builder;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import org.parceler.Parcel;

@Parcel
public class ShopEditRearrangeYourShopRow extends ShopEditComposedContentRow implements ShopEditActionableItem {
    ShopEditContentRow mWrappedContentRow;

    public /* bridge */ /* synthetic */ CharSequence getContent() {
        return super.getContent();
    }

    public /* bridge */ /* synthetic */ int getContentMaxLines() {
        return super.getContentMaxLines();
    }

    public /* bridge */ /* synthetic */ CharSequence getHint() {
        return super.getHint();
    }

    public /* bridge */ /* synthetic */ CharSequence getTitle() {
        return super.getTitle();
    }

    public /* bridge */ /* synthetic */ int getViewType() {
        return super.getViewType();
    }

    public /* bridge */ /* synthetic */ boolean shouldIncludeBottomExtraPadding() {
        return super.shouldIncludeBottomExtraPadding();
    }

    ShopEditRearrangeYourShopRow() {
    }

    public ShopEditRearrangeYourShopRow(@NonNull Context context) {
        this.mWrappedContentRow = new Builder(3).title(context.getString(R.rearrange_your_shop_button_title)).content(context.getString(R.rearrange_your_shop_hint)).build();
    }

    public void editActionInitiated(int i, @NonNull ShopEditDelegate shopEditDelegate, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter) {
        shopEditDelegate.goToShopRearrange();
    }

    public boolean isActionEnabled() {
        return true;
    }

    public void updateWithEditResult(@NonNull ShopEditDelegate shopEditDelegate, @NonNull RecyclerView recyclerView, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter, int i, int i2, @NonNull Intent intent, @NonNull Context context, int i3) {
    }

    @NonNull
    ShopEditContentRow getContentRow() {
        return this.mWrappedContentRow;
    }

    public void restoreComplexStateIfNecessary(@NonNull Context context) {
    }
}

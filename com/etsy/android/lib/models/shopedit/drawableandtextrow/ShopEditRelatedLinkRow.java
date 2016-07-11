package com.etsy.android.lib.models.shopedit.drawableandtextrow;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.ShopAbout.Link;
import com.etsy.android.lib.models.shopedit.ShopEditActionableItem;
import com.etsy.android.lib.models.shopedit.ShopEditDelegate;
import com.etsy.android.lib.models.shopedit.ShopEditPresentationItem;
import com.etsy.android.lib.models.shopedit.drawableandtextrow.ShopEditAddRelatedLinksRow.RelatedLinksSectionInfo;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import org.parceler.Parcel;

@Parcel
public class ShopEditRelatedLinkRow extends ShopEditDrawableAndTextRow implements ShopEditActionableItem {
    @NonNull
    Link mLink;
    @NonNull
    RelatedLinksSectionInfo mLinksSectionInfo;

    ShopEditRelatedLinkRow() {
    }

    public ShopEditRelatedLinkRow(@NonNull RelatedLinksSectionInfo relatedLinksSectionInfo, @NonNull Link link, @NonNull Context context) {
        this.mLinksSectionInfo = relatedLinksSectionInfo;
        this.mDrawable = buildLinkDrawable(link, context);
        this.mLink = link;
        this.mText = link.getUrl();
    }

    private static Drawable buildLinkDrawable(@NonNull Link link, @NonNull Context context) {
        return IconDrawable.m775a(context.getResources()).m780a(link.getTypeIcon()).m782c(R.shop_edit_text_row_icon_size).m781b(R.text_grey).m777a();
    }

    public void restoreComplexStateIfNecessary(@NonNull Context context) {
        this.mDrawable = buildLinkDrawable(this.mLink, context);
    }

    public void editActionInitiated(int i, @NonNull ShopEditDelegate shopEditDelegate, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter) {
        shopEditDelegate.editLink(this.mLink, this.mLinksSectionInfo.mAllLinks);
    }

    public void updateWithEditResult(@NonNull ShopEditDelegate shopEditDelegate, @NonNull RecyclerView recyclerView, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter, int i, int i2, @NonNull Intent intent, @NonNull Context context, int i3) {
        ShopEditAddRelatedLinksRow.handleEditResult(this.mLinksSectionInfo, i3, baseRecyclerViewAdapter, i, i2, intent, context);
    }

    public boolean isActionEnabled() {
        return true;
    }
}

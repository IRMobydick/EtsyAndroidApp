package com.etsy.android.lib.models.shopedit.contentrow;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import com.etsy.android.lib.models.ResponseConstants.Includes;
import com.etsy.android.lib.models.apiv3.FAQ;
import com.etsy.android.lib.models.shopedit.ShopEditActionableItem;
import com.etsy.android.lib.models.shopedit.ShopEditDelegate;
import com.etsy.android.lib.models.shopedit.ShopEditPresentationItem;
import com.etsy.android.lib.models.shopedit.contentrow.ShopEditContentRow.Builder;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import org.parceler.Parcel;
import org.parceler.Parcels;

@Parcel
public class ShopEditFAQRow extends ShopEditComposedContentRow implements ShopEditActionableItem {
    @NonNull
    ShopEditContentRow mContentRow;
    @NonNull
    FAQ mFaq;

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

    ShopEditFAQRow() {
    }

    public ShopEditFAQRow(@NonNull FAQ faq) {
        this.mFaq = faq;
        this.mContentRow = buildContentRowWithFAQ(faq);
    }

    private static ShopEditContentRow buildContentRowWithFAQ(@NonNull FAQ faq) {
        return new Builder(7).title(faq.getQuestion()).content(faq.getAnswer()).build();
    }

    @NonNull
    ShopEditContentRow getContentRow() {
        return this.mContentRow;
    }

    public void editActionInitiated(int i, @NonNull ShopEditDelegate shopEditDelegate, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter) {
        shopEditDelegate.editFaq(this.mFaq);
    }

    public void updateWithEditResult(@NonNull ShopEditDelegate shopEditDelegate, @NonNull RecyclerView recyclerView, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter, int i, int i2, @NonNull Intent intent, @NonNull Context context, int i3) {
        if (i == 1088) {
            switch (i2) {
                case 1089:
                    if (intent.hasExtra(Includes.FAQ)) {
                        FAQ faq = (FAQ) Parcels.m7495a(intent.getParcelableExtra(Includes.FAQ));
                        this.mFaq = faq;
                        this.mContentRow = buildContentRowWithFAQ(faq);
                        baseRecyclerViewAdapter.notifyItemChanged(i3);
                    }
                case 1090:
                    baseRecyclerViewAdapter.getItems().remove(this);
                    baseRecyclerViewAdapter.notifyItemRemoved(i3);
                default:
            }
        }
    }

    public boolean isActionEnabled() {
        return true;
    }

    public void restoreComplexStateIfNecessary(@NonNull Context context) {
    }
}

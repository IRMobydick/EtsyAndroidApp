package com.etsy.android.lib.models.shopedit.drawableandtextrow;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.ResponseConstants.Includes;
import com.etsy.android.lib.models.apiv3.FAQ;
import com.etsy.android.lib.models.shopedit.ShopEditDelegate;
import com.etsy.android.lib.models.shopedit.ShopEditPresentationItem;
import com.etsy.android.lib.models.shopedit.contentrow.ShopEditFAQRow;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import org.parceler.Parcel;
import org.parceler.Parcels;

@Parcel
public class ShopEditAddFaqRow extends ShopEditAddContentRow {
    public /* bridge */ /* synthetic */ boolean isActionEnabled() {
        return super.isActionEnabled();
    }

    public /* bridge */ /* synthetic */ void restoreComplexStateIfNecessary(Context context) {
        super.restoreComplexStateIfNecessary(context);
    }

    ShopEditAddFaqRow() {
    }

    public ShopEditAddFaqRow(@NonNull Context context) {
        super(R.faq_add, context);
    }

    public void editActionInitiated(int i, @NonNull ShopEditDelegate shopEditDelegate, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter) {
        shopEditDelegate.addFaq();
    }

    public void updateWithEditResult(@NonNull ShopEditDelegate shopEditDelegate, @NonNull RecyclerView recyclerView, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter, int i, int i2, @NonNull Intent intent, @NonNull Context context, int i3) {
        if (i == 1088 && i2 == 1089 && intent.hasExtra(Includes.FAQ)) {
            baseRecyclerViewAdapter.getItems().add(i3, new ShopEditFAQRow((FAQ) Parcels.m7495a(intent.getParcelableExtra(Includes.FAQ))));
            baseRecyclerViewAdapter.notifyItemInserted(i3);
        }
    }
}

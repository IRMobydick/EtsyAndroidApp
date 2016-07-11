package com.etsy.android.lib.models.shopedit.drawableandtextrow;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.SellerDetails;
import com.etsy.android.lib.models.shopedit.ShopEditDelegate;
import com.etsy.android.lib.models.shopedit.ShopEditPresentationItem;
import com.etsy.android.lib.models.shopedit.contentrow.ShopEditSellerDetailsRow;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import java.util.List;
import org.parceler.Parcel;
import org.parceler.Parcels;

@Parcel
public class ShopEditAddSellerDetailsRow extends ShopEditAddContentRow {
    public /* bridge */ /* synthetic */ boolean isActionEnabled() {
        return super.isActionEnabled();
    }

    public /* bridge */ /* synthetic */ void restoreComplexStateIfNecessary(Context context) {
        super.restoreComplexStateIfNecessary(context);
    }

    ShopEditAddSellerDetailsRow() {
    }

    public ShopEditAddSellerDetailsRow(@NonNull Context context) {
        super(R.seller_details_add, context);
    }

    public void editActionInitiated(int i, @NonNull ShopEditDelegate shopEditDelegate, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter) {
        shopEditDelegate.editSellerDetails(null);
    }

    public void updateWithEditResult(@NonNull ShopEditDelegate shopEditDelegate, @NonNull RecyclerView recyclerView, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter, int i, int i2, @NonNull Intent intent, @NonNull Context context, int i3) {
        if (i == 1085 && i2 == 1086 && intent.hasExtra(ResponseConstants.SELLER_DETAILS)) {
            SellerDetails sellerDetails = (SellerDetails) Parcels.m7495a(intent.getParcelableExtra(ResponseConstants.SELLER_DETAILS));
            List items = baseRecyclerViewAdapter.getItems();
            int i4 = i3 - 1;
            if (items.get(i4) instanceof ShopEditSellerDetailsRow) {
                items.set(i4, new ShopEditSellerDetailsRow(sellerDetails, context));
                baseRecyclerViewAdapter.notifyItemChanged(i4);
                items.remove(i3);
                baseRecyclerViewAdapter.notifyItemRemoved(i3);
            }
        }
    }
}

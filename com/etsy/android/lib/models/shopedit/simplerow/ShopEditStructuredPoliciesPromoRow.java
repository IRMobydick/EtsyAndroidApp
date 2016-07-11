package com.etsy.android.lib.models.shopedit.simplerow;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.EditStructuredPoliciesShopContext;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.StructuredShopPolicies;
import com.etsy.android.lib.models.shopedit.ShopEditActionableItem;
import com.etsy.android.lib.models.shopedit.ShopEditDelegate;
import com.etsy.android.lib.models.shopedit.ShopEditPresentationItem;
import com.etsy.android.lib.models.shopedit.contentrow.ShopEditBasicFieldRow;
import com.etsy.android.lib.models.shopedit.contentrow.ShopEditStructuredPoliciesRow;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcel;
import org.parceler.Parcels;

@Parcel
public class ShopEditStructuredPoliciesPromoRow extends ShopEditSimpleItemRow implements ShopEditActionableItem {
    EditStructuredPoliciesShopContext mShopContext;
    @Nullable
    StructuredShopPolicies mShopPolicies;

    public ShopEditStructuredPoliciesPromoRow(@NonNull EditStructuredPoliciesShopContext editStructuredPoliciesShopContext, @Nullable StructuredShopPolicies structuredShopPolicies) {
        super(8);
        this.mShopContext = editStructuredPoliciesShopContext;
        this.mShopPolicies = structuredShopPolicies;
    }

    public void editActionInitiated(int i, @NonNull ShopEditDelegate shopEditDelegate, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter) {
        shopEditDelegate.editStructuredPolicies(this.mShopPolicies, this.mShopContext);
    }

    public boolean isActionEnabled() {
        return true;
    }

    public void updateWithEditResult(@NonNull ShopEditDelegate shopEditDelegate, @NonNull RecyclerView recyclerView, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter, int i, int i2, @NonNull Intent intent, @NonNull Context context, int i3) {
        if (i == 1080 && i2 == 1084) {
            if (intent.hasExtra(ResponseConstants.STRUCTURED_POLICIES)) {
                this.mShopPolicies = (StructuredShopPolicies) Parcels.m7495a(intent.getParcelableExtra(ResponseConstants.STRUCTURED_POLICIES));
            }
            boolean hasExtra = intent.hasExtra("accepted_structured_policies");
            boolean booleanExtra = intent.getBooleanExtra("accepted_structured_policies", false);
            if (hasExtra && booleanExtra) {
                this.mShopContext.setUsingStructuredPolicies(true);
                List items = baseRecyclerViewAdapter.getItems();
                List arrayList = new ArrayList();
                int i4 = i3 + 1;
                int size = items.size();
                for (int i5 = i4; i5 < size; i5++) {
                    ShopEditPresentationItem shopEditPresentationItem = (ShopEditPresentationItem) items.get(i5);
                    if (!(shopEditPresentationItem instanceof ShopEditBasicFieldRow)) {
                        break;
                    }
                    ShopEditBasicFieldRow shopEditBasicFieldRow = (ShopEditBasicFieldRow) shopEditPresentationItem;
                    if (!shopEditBasicFieldRow.isOldShopPoliciesFieldRow()) {
                        break;
                    }
                    arrayList.add(shopEditBasicFieldRow);
                }
                items.remove(i3);
                items.removeAll(arrayList);
                baseRecyclerViewAdapter.notifyItemRangeRemoved(i3, arrayList.size() + 1);
                items.add(i3, new ShopEditStructuredPoliciesRow(this.mShopContext, this.mShopPolicies, context, arrayList));
                baseRecyclerViewAdapter.notifyItemInserted(i3);
                recyclerView.scrollToPosition(i3 - 1);
                Snackbar.make((View) recyclerView, R.structured_policies_published, -1).show();
            }
        }
    }
}

package com.etsy.android.lib.models.shopedit.contentrow;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.EditStructuredPoliciesShopContext;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.StructuredShopPolicies;
import com.etsy.android.lib.models.shopedit.ShopEditActionableItem;
import com.etsy.android.lib.models.shopedit.ShopEditDelegate;
import com.etsy.android.lib.models.shopedit.ShopEditPresentationItem;
import com.etsy.android.lib.models.shopedit.contentrow.ShopEditContentRow.Builder;
import com.etsy.android.lib.models.shopedit.simplerow.ShopEditStructuredPoliciesPromoRow;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;
import org.parceler.Parcels;

@Parcel
public class ShopEditStructuredPoliciesRow extends ShopEditComposedContentRow implements ShopEditActionableItem {
    private static final SimpleDateFormat DATE_FORMAT;
    private static final String TAG;
    ShopEditContentRow mContentRow;
    @Nullable
    List<ShopEditBasicFieldRow> mOldPoliciesRowItems;
    EditStructuredPoliciesShopContext mShopContext;
    StructuredShopPolicies mShopPolicies;

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

    static {
        TAG = EtsyDebug.m1891a(ShopEditStructuredPoliciesRow.class);
        DATE_FORMAT = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
    }

    ShopEditStructuredPoliciesRow() {
    }

    public ShopEditStructuredPoliciesRow(@NonNull EditStructuredPoliciesShopContext editStructuredPoliciesShopContext, @Nullable StructuredShopPolicies structuredShopPolicies, @NonNull Context context, @Nullable List<ShopEditBasicFieldRow> list) {
        this.mShopPolicies = structuredShopPolicies;
        this.mContentRow = contentRowFromStructuredPolicies(structuredShopPolicies, context);
        this.mOldPoliciesRowItems = list;
        this.mShopContext = editStructuredPoliciesShopContext;
    }

    public void editActionInitiated(int i, @NonNull ShopEditDelegate shopEditDelegate, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter) {
        shopEditDelegate.editStructuredPolicies(this.mShopPolicies, this.mShopContext);
    }

    public boolean isActionEnabled() {
        return true;
    }

    public void updateWithEditResult(@NonNull ShopEditDelegate shopEditDelegate, @NonNull RecyclerView recyclerView, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter, int i, int i2, @NonNull Intent intent, @NonNull Context context, int i3) {
        if (i == 1080 && i2 == 1084) {
            boolean z;
            if (intent.hasExtra(ResponseConstants.STRUCTURED_POLICIES)) {
                StructuredShopPolicies structuredShopPolicies = (StructuredShopPolicies) Parcels.m7495a(intent.getParcelableExtra(ResponseConstants.STRUCTURED_POLICIES));
                this.mContentRow = contentRowFromStructuredPolicies(structuredShopPolicies, context);
                this.mShopPolicies = structuredShopPolicies;
            }
            if (!intent.hasExtra("accepted_structured_policies") || intent.getBooleanExtra("accepted_structured_policies", false)) {
                z = false;
            } else {
                z = true;
            }
            if (z) {
                this.mShopContext.setUsingStructuredPolicies(false);
                List items = baseRecyclerViewAdapter.getItems();
                items.remove(i3);
                baseRecyclerViewAdapter.notifyItemRemoved(i3);
                items.add(i3, new ShopEditStructuredPoliciesPromoRow(this.mShopContext, this.mShopPolicies));
                int size = this.mOldPoliciesRowItems != null ? this.mOldPoliciesRowItems.size() : 0;
                if (size > 0) {
                    items.addAll(i3 + 1, this.mOldPoliciesRowItems);
                }
                baseRecyclerViewAdapter.notifyItemRangeInserted(i3, size + 1);
                return;
            }
            baseRecyclerViewAdapter.notifyItemChanged(i3);
        }
    }

    @NonNull
    ShopEditContentRow getContentRow() {
        return this.mContentRow;
    }

    public void restoreComplexStateIfNecessary(@NonNull Context context) {
    }

    @NonNull
    private static ShopEditContentRow contentRowFromStructuredPolicies(@Nullable StructuredShopPolicies structuredShopPolicies, @NonNull Context context) {
        CharSequence string;
        Builder title = new Builder(3).title(context.getString(R.shop_edit_policies_row_heading));
        if (structuredShopPolicies != null) {
            string = context.getString(R.last_updated_on, new Object[]{DATE_FORMAT.format(structuredShopPolicies.getLastUpdatedDate())});
        } else {
            string = StringUtils.EMPTY;
        }
        return title.content(string).build();
    }
}

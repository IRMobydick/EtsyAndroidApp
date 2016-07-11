package com.etsy.android.lib.models.shopedit.contentrow;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.SellerDetails;
import com.etsy.android.lib.models.shopedit.ShopEditActionableItem;
import com.etsy.android.lib.models.shopedit.ShopEditDelegate;
import com.etsy.android.lib.models.shopedit.ShopEditPresentationItem;
import com.etsy.android.lib.models.shopedit.contentrow.ShopEditContentRow.Builder;
import com.etsy.android.lib.models.shopedit.drawableandtextrow.ShopEditAddSellerDetailsRow;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.foresee.sdk.configuration.MeasureConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;
import org.parceler.Parcels;

@Parcel
public class ShopEditSellerDetailsRow extends ShopEditComposedContentRow implements ShopEditActionableItem {
    @NonNull
    ShopEditContentRow mEditContentRow;
    @Nullable
    SellerDetails mSellerDetails;

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

    ShopEditSellerDetailsRow() {
    }

    public ShopEditSellerDetailsRow(@Nullable SellerDetails sellerDetails, @NonNull Context context) {
        this.mSellerDetails = sellerDetails;
        this.mEditContentRow = buildContentRowFromSellerDetails(sellerDetails, context);
    }

    @NonNull
    ShopEditContentRow getContentRow() {
        return this.mEditContentRow;
    }

    private static ShopEditContentRow buildContentRowFromSellerDetails(@Nullable SellerDetails sellerDetails, @NonNull Context context) {
        boolean z;
        if (sellerDetails == null || !sellerDetails.hasDetails()) {
            z = false;
        } else {
            z = true;
        }
        return new Builder(3).title(getSellerDetailsHeading(z, context)).content(z ? sellerDetails.getFormattedDetails() : StringUtils.EMPTY).contentMaxLines(MeasureConfiguration.DISABLED).includeBottomPadding(false).build();
    }

    private static CharSequence getSellerDetailsHeading(boolean z, @NonNull Context context) {
        CharSequence string = context.getString(R.seller_details_title);
        if (z) {
            return string;
        }
        String b = EtsyConfig.m837a().m869d().m883b(EtsyConfigKeys.f1256b);
        Spanned append = new SpannableStringBuilder(string).append("\n").append(context.getString(R.structured_policies_europe_warning_title)).append(" ").append(Html.fromHtml(context.getString(R.seller_details_europe_warning, new Object[]{b})));
        int length = string.length() + 1;
        append.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.text_grey)), length, append.length(), 33);
        append.setSpan(new AbsoluteSizeSpan(context.getResources().getDimensionPixelSize(R.text_small)), length, append.length(), 33);
        return EtsyLinkify.m5481a(context, append, true, true, null);
    }

    public void editActionInitiated(int i, @NonNull ShopEditDelegate shopEditDelegate, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter) {
        shopEditDelegate.editSellerDetails(this.mSellerDetails);
    }

    public void updateWithEditResult(@NonNull ShopEditDelegate shopEditDelegate, @NonNull RecyclerView recyclerView, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter, int i, int i2, @NonNull Intent intent, @NonNull Context context, int i3) {
        if (i == 1085) {
            switch (i2) {
                case 1086:
                    if (intent.hasExtra(ResponseConstants.SELLER_DETAILS)) {
                        SellerDetails sellerDetails = (SellerDetails) Parcels.m7495a(intent.getParcelableExtra(ResponseConstants.SELLER_DETAILS));
                        this.mEditContentRow = buildContentRowFromSellerDetails(sellerDetails, context);
                        this.mSellerDetails = sellerDetails;
                        break;
                    }
                    return;
                case 1087:
                    this.mEditContentRow = buildContentRowFromSellerDetails(null, context);
                    baseRecyclerViewAdapter.getItems().add(i3 + 1, new ShopEditAddSellerDetailsRow(context));
                    this.mSellerDetails = null;
                    break;
                default:
                    return;
            }
            baseRecyclerViewAdapter.notifyItemChanged(i3);
        }
    }

    public boolean isActionEnabled() {
        return this.mSellerDetails != null && this.mSellerDetails.hasDetails();
    }

    public void restoreComplexStateIfNecessary(@NonNull Context context) {
    }
}

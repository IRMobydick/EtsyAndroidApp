package com.etsy.android.lib.models.view.shop.section;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.apiv3.SellerDetails;
import com.etsy.android.lib.models.apiv3.ShopV3;
import com.etsy.android.uikit.viewholder.a.a.a;
import org.apache.commons.lang3.StringUtils;

public class ShopHomeMoreInfoSectionViewModel extends ShopHomeBaseSectionViewModel {
    private a mContactShopClickListener;
    private String mContactShopText;
    @Nullable
    private CharSequence mFormattedSellerDetails;
    private ShopV3 mShop;

    public ShopHomeMoreInfoSectionViewModel(@NonNull CharSequence charSequence, @Nullable SellerDetails sellerDetails, @NonNull ShopV3 shopV3, @Nullable a aVar, @NonNull Resources resources) {
        super(charSequence);
        this.mShop = shopV3;
        this.mContactShopClickListener = aVar;
        this.mContactShopText = resources.getString(R.seller_details_contact, new Object[]{shopV3.getShopName()});
        if (sellerDetails != null && sellerDetails.hasDetails()) {
            CharSequence spannableStringBuilder = new SpannableStringBuilder(sellerDetails.getFormattedDetails());
            if (aVar != null) {
                spannableStringBuilder.append("\n").append(this.mContactShopText);
            }
            this.mFormattedSellerDetails = spannableStringBuilder;
        }
    }

    @Nullable
    public CharSequence getFormattedSellerDetails() {
        return this.mFormattedSellerDetails;
    }

    @NonNull
    public ShopV3 getShop() {
        return this.mShop;
    }

    public CharSequence getText() {
        return StringUtils.EMPTY;
    }

    @Nullable
    public a getStructuredPoliciesViewClickListener() {
        return this.mContactShopClickListener;
    }

    @NonNull
    public String getContactShopText() {
        return this.mContactShopText;
    }
}

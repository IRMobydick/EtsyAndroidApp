package com.etsy.android.ui.core;

import android.widget.Toast;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.CartsRequest;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.ui.cart.CartUtil;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.etsy.android.ui.core.a */
class ListingFragment extends EtsyRequestJob<EmptyResult> {
    final /* synthetic */ ListingFragment f2736a;
    private Listing f2737c;

    public ListingFragment(ListingFragment listingFragment, Listing listing) {
        this.f2736a = listingFragment;
        this.f2737c = listing;
    }

    protected void b_() {
        this.f2736a.mBtnCart.setBackgroundResource(2130837693);
        this.f2736a.mBtnCart.showLoading();
    }

    protected EtsyRequest<EmptyResult> m3976a() {
        EtsyRequest<EmptyResult> addToCart = CartsRequest.addToCart();
        Map hashMap = new HashMap();
        hashMap.put(ResponseConstants.LISTING_ID, this.f2737c.getListingId().getId());
        EtsyId access$2600 = this.f2736a.getResolvedOfferingId();
        if (access$2600 != null) {
            hashMap.put("listing_inventory_id", access$2600.getId());
        } else if (this.f2737c.hasVariations()) {
            hashMap.put(ResponseConstants.SELECTED_VARIATIONS, this.f2737c.getSelectedVariations());
        }
        Object valueOf = Integer.valueOf(1);
        if (this.f2736a.mSpinnerQuantity.getVisibility() == 0) {
            valueOf = (Integer) this.f2736a.mSpinnerQuantity.getSelectedItem();
        }
        addToCart.setViewTracker(this.f2736a.getAnalyticsContext());
        hashMap.put(ResponseConstants.QUANTITY, String.valueOf(valueOf));
        addToCart.addParams(hashMap);
        return addToCart;
    }

    protected void m3977a(EtsyResult<EmptyResult> etsyResult) {
        this.f2736a.mBtnCart.hideLoading();
        if (etsyResult.m1049a()) {
            this.f2736a.mIsInCart = true;
            this.f2736a.showInCartButton();
            CartUtil.m3885a(this.f2736a.mActivity);
            return;
        }
        this.f2736a.mIsInCart = false;
        this.f2736a.showAddToCartButton();
        Toast.makeText(this.f2736a.mActivity, R.cart_error, 0).show();
    }
}

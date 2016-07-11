package com.etsy.android.ui.cart;

import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.util.bl;

/* renamed from: com.etsy.android.ui.cart.e */
class CartFragment implements af<Cart> {
    final /* synthetic */ CartFragment f2466a;

    private CartFragment(CartFragment cartFragment) {
        this.f2466a = cartFragment;
    }

    public void m3795a() {
        this.f2466a.f2460a.f2459a.showCartLoading(this.f2466a.f2461b, true);
    }

    public void m3796a(EtsyResult<Cart> etsyResult) {
        if (etsyResult.m1049a()) {
            this.f2466a.f2460a.f2459a.getRequestQueue().m1697a((Object) this, new CartFragment(this.f2466a.f2460a.f2459a, this.f2466a.f2461b, String.valueOf(this.f2466a.f2462c.getListingId()), String.valueOf(this.f2466a.f2462c.getListingCustomizationId())));
            return;
        }
        EtsyDebug.m1920e(CartFragment.TAG, "Unsuccessful variations quantity update %s", etsyResult.m1052c());
        this.f2466a.f2460a.f2459a.showCartLoading(this.f2466a.f2461b, false);
        bl.m3365b(this.f2466a.f2460a.f2459a.mActivity, (int) R.variation_unable_to_update);
    }
}

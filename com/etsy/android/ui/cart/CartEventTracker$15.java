package com.etsy.android.ui.cart;

import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.HashMap;

final class CartEventTracker$15 extends HashMap<String, Object> {
    final /* synthetic */ Cart val$cart;
    final /* synthetic */ int val$countryId;
    final /* synthetic */ String val$postalCode;
    final /* synthetic */ String val$shippingOptionId;

    CartEventTracker$15(Cart cart, int i, String str, String str2) {
        this.val$cart = cart;
        this.val$countryId = i;
        this.val$postalCode = str;
        this.val$shippingOptionId = str2;
        put(CartActivity.INT_CART_ID, Integer.valueOf(this.val$cart.getCartId()));
        put(ResponseConstants.COUNTRY_ID, Integer.valueOf(this.val$countryId));
        put(ResponseConstants.POSTAL_CODE, this.val$postalCode);
        put("shipping_option_id", this.val$shippingOptionId);
    }
}

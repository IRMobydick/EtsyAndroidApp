package com.etsy.android.ui.cart;

import com.etsy.android.lib.models.Cart;
import java.util.HashMap;

final class CartEventTracker$8 extends HashMap<String, Object> {
    final /* synthetic */ Cart val$cart;

    CartEventTracker$8(Cart cart) {
        this.val$cart = cart;
        put(CartActivity.INT_CART_ID, Integer.valueOf(this.val$cart.getCartId()));
        put(CartFragment.PAYMENT_METHOD, this.val$cart.getLastPaymentMethod() != null ? this.val$cart.getLastPaymentMethod().getType() : null);
    }
}

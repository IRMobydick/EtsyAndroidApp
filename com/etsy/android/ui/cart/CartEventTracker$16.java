package com.etsy.android.ui.cart;

import com.etsy.android.lib.models.Cart;
import java.util.HashMap;

final class CartEventTracker$16 extends HashMap<String, Object> {
    final /* synthetic */ Cart val$cart;
    final /* synthetic */ String val$paymentMethod;

    CartEventTracker$16(Cart cart, String str) {
        this.val$cart = cart;
        this.val$paymentMethod = str;
        put(CartActivity.INT_CART_ID, Integer.valueOf(this.val$cart.getCartId()));
        put(CartFragment.PAYMENT_METHOD, this.val$paymentMethod);
    }
}

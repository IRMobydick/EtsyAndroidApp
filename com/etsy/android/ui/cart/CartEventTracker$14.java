package com.etsy.android.ui.cart;

import com.etsy.android.lib.models.Cart;
import java.util.HashMap;

final class CartEventTracker$14 extends HashMap<String, Object> {
    final /* synthetic */ Cart val$cart;

    CartEventTracker$14(Cart cart) {
        this.val$cart = cart;
        put(CartActivity.INT_CART_ID, Integer.valueOf(this.val$cart.getCartId()));
    }
}

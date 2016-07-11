package com.etsy.android.ui.cart;

import com.etsy.android.lib.models.Cart;
import java.util.HashMap;

final class CartEventTracker$11 extends HashMap<String, Object> {
    final /* synthetic */ Cart val$cart;
    final /* synthetic */ int val$resultCode;

    CartEventTracker$11(Cart cart, int i) {
        this.val$cart = cart;
        this.val$resultCode = i;
        put(CartActivity.INT_CART_ID, Integer.valueOf(this.val$cart.getCartId()));
        put("activity_result_code", Integer.valueOf(this.val$resultCode));
    }
}

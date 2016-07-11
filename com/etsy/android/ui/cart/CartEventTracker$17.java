package com.etsy.android.ui.cart;

import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.HashMap;

final class CartEventTracker$17 extends HashMap<String, Object> {
    final /* synthetic */ Cart val$cart;
    final /* synthetic */ int val$quantity;

    CartEventTracker$17(Cart cart, int i) {
        this.val$cart = cart;
        this.val$quantity = i;
        put(CartActivity.INT_CART_ID, Integer.valueOf(this.val$cart.getCartId()));
        put(ResponseConstants.QUANTITY, Integer.valueOf(this.val$quantity));
    }
}

package com.etsy.android.ui.cart;

import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.requests.CartsRequest;
import java.util.HashMap;

final class CartEventTracker$4 extends HashMap<String, Object> {
    final /* synthetic */ Cart val$cart;
    final /* synthetic */ String val$couponCode;

    CartEventTracker$4(Cart cart, String str) {
        this.val$cart = cart;
        this.val$couponCode = str;
        put(CartActivity.INT_CART_ID, Integer.valueOf(this.val$cart.getCartId()));
        put(CartsRequest.PARAM_COUPON_CODE, this.val$couponCode);
    }
}

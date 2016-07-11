package com.etsy.android.ui.cart;

import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.HashMap;

final class CartEventTracker$2 extends HashMap<String, Object> {
    final /* synthetic */ Cart val$cart;
    final /* synthetic */ EtsyId val$orderId;

    CartEventTracker$2(EtsyId etsyId, Cart cart) {
        this.val$orderId = etsyId;
        this.val$cart = cart;
        put(ResponseConstants.RECEIPT_ID, this.val$orderId.getId());
        put(CartActivity.INT_CART_ID, Integer.valueOf(this.val$cart.getCartId()));
    }
}

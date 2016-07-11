package com.etsy.android.ui.nav;

import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.requests.CartsRequest;
import com.etsy.android.ui.cart.CartActivity;
import com.etsy.android.ui.cart.CartFragment;
import java.util.HashMap;

class NavTracker$1 extends HashMap<String, Object> {
    final /* synthetic */ NavTracker this$0;
    final /* synthetic */ Cart val$cart;
    final /* synthetic */ StringBuilder val$listingIdsBuilder;
    final /* synthetic */ String val$paymentMethodType;

    NavTracker$1(NavTracker navTracker, Cart cart, StringBuilder stringBuilder, String str) {
        this.this$0 = navTracker;
        this.val$cart = cart;
        this.val$listingIdsBuilder = stringBuilder;
        this.val$paymentMethodType = str;
        put(CartActivity.INT_CART_ID, Integer.valueOf(this.val$cart.getCartId()));
        put(CartsRequest.PARAM_COUPON_CODE, this.val$cart.getCouponCode());
        put(ResponseConstants.TOTAL, this.val$cart.getTotal());
        put("subtotal", this.val$cart.getSubtotal());
        put("ship_total", this.val$cart.getShippingCost());
        put(ResponseConstants.DISCOUNT_AMOUNT, this.val$cart.getDiscountAmount());
        put(ResponseConstants.LISTING_IDS, this.val$listingIdsBuilder.toString());
        put(CartFragment.PAYMENT_METHOD, this.val$paymentMethodType);
        put("is_giftcard_cart", Boolean.valueOf(false));
    }
}

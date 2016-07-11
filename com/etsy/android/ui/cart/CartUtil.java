package com.etsy.android.ui.cart;

import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.models.CartListing;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.requests.CartsRequest;
import com.etsy.android.lib.requests.EtsyRequest;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.etsy.android.ui.cart.x */
public class CartUtil extends CartUtil<Cart> {
    private final CartListing f2632a;
    private int f2633c;

    public CartUtil(CartListing cartListing, int i, af<Cart> afVar) {
        super(afVar);
        this.f2632a = cartListing;
        this.f2633c = i;
    }

    protected EtsyRequest<Cart> m3904a() {
        EtsyRequest<Cart> updateCartListingQuantity = CartsRequest.updateCartListingQuantity();
        Map hashMap = new HashMap();
        hashMap.put(ResponseConstants.LISTING_ID, String.valueOf(this.f2632a.getListingId()));
        if (this.f2632a.getListingCustomizationId() > 0) {
            hashMap.put("listing_customization_id", String.valueOf(this.f2632a.getListingCustomizationId()));
        }
        hashMap.put(ResponseConstants.QUANTITY, String.valueOf(this.f2633c));
        hashMap.put("includes", CartsRequest.CART_INCLUDES);
        updateCartListingQuantity.addParams(hashMap);
        return updateCartListingQuantity;
    }
}

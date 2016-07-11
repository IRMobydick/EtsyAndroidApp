package com.etsy.android.ui.util;

import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.ui.cart.CartActivity;
import java.util.HashMap;

class RealTimeShippingCalculatorController$12 extends HashMap<String, Object> {
    final /* synthetic */ RealTimeShippingCalculatorController this$0;
    final /* synthetic */ Country val$destinationCountry;
    final /* synthetic */ String val$destinationZip;

    RealTimeShippingCalculatorController$12(RealTimeShippingCalculatorController realTimeShippingCalculatorController, Country country, String str) {
        this.this$0 = realTimeShippingCalculatorController;
        this.val$destinationCountry = country;
        this.val$destinationZip = str;
        put(CartActivity.INT_CART_ID, Integer.valueOf(this.this$0.f3820d.getCartId()));
        put(ResponseConstants.COUNTRY_ID, this.val$destinationCountry != null ? Integer.valueOf(this.val$destinationCountry.getCountryId()) : null);
        put(ResponseConstants.POSTAL_CODE, this.val$destinationZip);
    }
}

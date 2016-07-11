package com.etsy.android.ui.core.listingpanel;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.ui.core.listingpanel.ListingPanelShippingAndPolicies.ListingPanelShippingAndPolicies;
import java.util.HashMap;

class ListingPanelShippingAndPolicies$12$1 extends HashMap<String, Object> {
    final /* synthetic */ ListingPanelShippingAndPolicies this$1;
    final /* synthetic */ String val$error;

    ListingPanelShippingAndPolicies$12$1(ListingPanelShippingAndPolicies listingPanelShippingAndPolicies, String str) {
        this.this$1 = listingPanelShippingAndPolicies;
        this.val$error = str;
        put("error_type", this.val$error);
        put(ResponseConstants.LISTING_ID, this.this$1.f2897a.b.getListingId());
        put(ResponseConstants.COUNTRY_ID, this.this$1.f2897a.f2910H != null ? Integer.valueOf(this.this$1.f2897a.f2910H.getCountryId()) : null);
        put(ResponseConstants.POSTAL_CODE, this.this$1.f2897a.f2911I);
    }
}

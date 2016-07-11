package com.etsy.android.ui.core.listingpanel;

import com.etsy.android.lib.models.ResponseConstants;
import java.util.HashMap;

class ListingPanelShippingAndPolicies$9 extends HashMap<String, Object> {
    final /* synthetic */ ListingPanelShippingAndPolicies this$0;

    ListingPanelShippingAndPolicies$9(ListingPanelShippingAndPolicies listingPanelShippingAndPolicies) {
        this.this$0 = listingPanelShippingAndPolicies;
        put(ResponseConstants.LISTING_ID, this.this$0.b.getListingId());
        put(ResponseConstants.COUNTRY_ID, this.this$0.f2910H != null ? Integer.valueOf(this.this$0.f2910H.getCountryId()) : null);
        put(ResponseConstants.POSTAL_CODE, this.this$0.f2911I);
    }
}

package com.etsy.android.ui.core.listingpanel;

import com.etsy.android.lib.models.ResponseConstants;
import java.util.HashMap;

class ListingPanelShipping$8 extends HashMap<String, Object> {
    final /* synthetic */ ListingPanelShipping this$0;

    ListingPanelShipping$8(ListingPanelShipping listingPanelShipping) {
        this.this$0 = listingPanelShipping;
        put(ResponseConstants.LISTING_ID, this.this$0.b.getListingId());
        put(ResponseConstants.COUNTRY_ID, this.this$0.f2882y != null ? Integer.valueOf(this.this$0.f2882y.getCountryId()) : null);
        put(ResponseConstants.POSTAL_CODE, this.this$0.f2883z);
    }
}

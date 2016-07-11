package com.etsy.android.ui.core.listingpanel;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.ui.core.listingpanel.ListingPanelShipping.ListingPanelShipping;
import java.util.HashMap;

class ListingPanelShipping$11$1 extends HashMap<String, Object> {
    final /* synthetic */ ListingPanelShipping this$1;
    final /* synthetic */ String val$error;

    ListingPanelShipping$11$1(ListingPanelShipping listingPanelShipping, String str) {
        this.this$1 = listingPanelShipping;
        this.val$error = str;
        put("error_type", this.val$error);
        put(ResponseConstants.LISTING_ID, this.this$1.f2858a.b.getListingId());
        put(ResponseConstants.COUNTRY_ID, this.this$1.f2858a.f2882y != null ? Integer.valueOf(this.this$1.f2858a.f2882y.getCountryId()) : null);
        put(ResponseConstants.POSTAL_CODE, this.this$1.f2858a.f2883z);
    }
}

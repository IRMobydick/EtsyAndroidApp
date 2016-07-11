package com.etsy.android.ui.nav;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.HashMap;

class NavTracker$4 extends HashMap<String, Object> {
    final /* synthetic */ NavTracker this$0;
    final /* synthetic */ EtsyId val$listingId;

    NavTracker$4(NavTracker navTracker, EtsyId etsyId) {
        this.this$0 = navTracker;
        this.val$listingId = etsyId;
        put(ResponseConstants.LISTING_ID, this.val$listingId.getId());
    }
}

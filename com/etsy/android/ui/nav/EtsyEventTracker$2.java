package com.etsy.android.ui.nav;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.HashMap;

final class EtsyEventTracker$2 extends HashMap<String, Object> {
    final /* synthetic */ EtsyId val$listingId;

    EtsyEventTracker$2(EtsyId etsyId) {
        this.val$listingId = etsyId;
        put(ResponseConstants.LISTING_ID, this.val$listingId.getId());
    }
}

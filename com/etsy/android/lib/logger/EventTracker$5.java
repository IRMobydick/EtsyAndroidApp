package com.etsy.android.lib.logger;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.HashMap;

final class EventTracker$5 extends HashMap<String, Object> {
    final /* synthetic */ EtsyId val$listingId;

    EventTracker$5(EtsyId etsyId) {
        this.val$listingId = etsyId;
        put(ResponseConstants.LISTING_ID, this.val$listingId.getId());
    }
}

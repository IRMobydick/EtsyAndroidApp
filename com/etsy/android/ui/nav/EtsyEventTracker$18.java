package com.etsy.android.ui.nav;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.HashMap;

final class EtsyEventTracker$18 extends HashMap<String, Object> {
    final /* synthetic */ String val$collectionKey;
    final /* synthetic */ EtsyId val$listingId;

    EtsyEventTracker$18(EtsyId etsyId, String str) {
        this.val$listingId = etsyId;
        this.val$collectionKey = str;
        put(ResponseConstants.LISTING_ID, this.val$listingId.getId());
        put("collection_key", this.val$collectionKey);
    }
}

package com.etsy.android.lib.logger;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.HashMap;

final class EventTracker$11 extends HashMap<String, Object> {
    final /* synthetic */ EtsyId val$userId;

    EventTracker$11(EtsyId etsyId) {
        this.val$userId = etsyId;
        put(ResponseConstants.USER_ID, this.val$userId.getId());
    }
}

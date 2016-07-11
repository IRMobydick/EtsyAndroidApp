package com.etsy.android.ui.nav;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.HashMap;

final class EtsyEventTracker$12 extends HashMap<String, Object> {
    final /* synthetic */ EtsyId val$transactionId;

    EtsyEventTracker$12(EtsyId etsyId) {
        this.val$transactionId = etsyId;
        put(ResponseConstants.TRANSACTION_ID, this.val$transactionId.getId());
    }
}

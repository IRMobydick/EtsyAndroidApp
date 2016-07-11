package com.etsy.android.ui.nav;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.HashMap;

class NavTracker$7 extends HashMap<String, Object> {
    final /* synthetic */ NavTracker this$0;
    final /* synthetic */ EtsyId val$receiptId;

    NavTracker$7(NavTracker navTracker, EtsyId etsyId) {
        this.this$0 = navTracker;
        this.val$receiptId = etsyId;
        put(ResponseConstants.RECEIPT_ID, this.val$receiptId.getId());
    }
}

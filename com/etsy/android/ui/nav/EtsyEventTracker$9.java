package com.etsy.android.ui.nav;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.HashMap;

final class EtsyEventTracker$9 extends HashMap<String, Object> {
    final /* synthetic */ boolean val$isEditing;
    final /* synthetic */ EtsyId val$receiptId;
    final /* synthetic */ EtsyId val$transactionId;

    EtsyEventTracker$9(boolean z, EtsyId etsyId, EtsyId etsyId2) {
        this.val$isEditing = z;
        this.val$transactionId = etsyId;
        this.val$receiptId = etsyId2;
        put("hasTransactionReview", Boolean.valueOf(this.val$isEditing));
        put(ResponseConstants.TRANSACTION_ID, this.val$transactionId.getId());
        put(ResponseConstants.RECEIPT_ID, this.val$receiptId.getId());
    }
}

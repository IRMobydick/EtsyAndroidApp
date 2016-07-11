package com.etsy.android.ui.nav;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.HashMap;

final class EtsyEventTracker$21 extends HashMap<String, Object> {
    final /* synthetic */ EtsyId val$shopId;

    EtsyEventTracker$21(EtsyId etsyId) {
        this.val$shopId = etsyId;
        put(ResponseConstants.SHOP_ID, this.val$shopId.getId());
    }
}

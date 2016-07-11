package com.etsy.android.ui.nav;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.HashMap;

class NavTracker$5 extends HashMap<String, Object> {
    final /* synthetic */ NavTracker this$0;
    final /* synthetic */ EtsyId val$shopId;

    NavTracker$5(NavTracker navTracker, EtsyId etsyId) {
        this.this$0 = navTracker;
        this.val$shopId = etsyId;
        put(ResponseConstants.SHOP_ID, this.val$shopId.getId());
    }
}

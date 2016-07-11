package com.etsy.android.ui.nav;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.HashMap;

class NavTracker$3 extends HashMap<String, Object> {
    final /* synthetic */ NavTracker this$0;
    final /* synthetic */ EtsyId val$marketId;

    NavTracker$3(NavTracker navTracker, EtsyId etsyId) {
        this.this$0 = navTracker;
        this.val$marketId = etsyId;
        put(ResponseConstants.LOCAL_MARKET_ID, this.val$marketId.toString());
    }
}

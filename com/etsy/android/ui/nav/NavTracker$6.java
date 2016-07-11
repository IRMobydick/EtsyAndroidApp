package com.etsy.android.ui.nav;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.HashMap;

class NavTracker$6 extends HashMap<String, Object> {
    final /* synthetic */ NavTracker this$0;
    final /* synthetic */ EtsyId val$userId;

    NavTracker$6(NavTracker navTracker, EtsyId etsyId) {
        this.this$0 = navTracker;
        this.val$userId = etsyId;
        put(ResponseConstants.USER_ID, this.val$userId.getId());
    }
}

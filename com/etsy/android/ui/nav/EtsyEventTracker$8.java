package com.etsy.android.ui.nav;

import com.etsy.android.lib.models.ResponseConstants;
import java.util.HashMap;

final class EtsyEventTracker$8 extends HashMap<String, Object> {
    final /* synthetic */ int val$rating;

    EtsyEventTracker$8(int i) {
        this.val$rating = i;
        put(ResponseConstants.RATING, Integer.valueOf(this.val$rating));
    }
}

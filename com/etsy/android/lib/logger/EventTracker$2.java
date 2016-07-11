package com.etsy.android.lib.logger;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.ui.cart.SavedCartItemsFragment;
import java.util.HashMap;

final class EventTracker$2 extends HashMap<String, Object> {
    final /* synthetic */ int val$listingsCount;
    final /* synthetic */ String val$pageInView;
    final /* synthetic */ StringBuilder val$sb;
    final /* synthetic */ int val$totalResults;

    EventTracker$2(StringBuilder stringBuilder, int i, String str, int i2) {
        this.val$sb = stringBuilder;
        this.val$listingsCount = i;
        this.val$pageInView = str;
        this.val$totalResults = i2;
        put(ResponseConstants.LISTING_IDS, this.val$sb.toString());
        put("listings_count", Integer.valueOf(this.val$listingsCount));
        put(SavedCartItemsFragment.PAGE, this.val$pageInView);
        put("total_results", Integer.valueOf(this.val$totalResults));
    }
}

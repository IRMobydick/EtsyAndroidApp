package com.etsy.android.ui.nav;

import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.HashMap;

final class EtsyEventTracker$6 extends HashMap<String, Object> {
    final /* synthetic */ String val$query;
    final /* synthetic */ String val$searchCategory;
    final /* synthetic */ int val$totalResults;

    EtsyEventTracker$6(String str, String str2, int i) {
        this.val$searchCategory = str;
        this.val$query = str2;
        this.val$totalResults = i;
        put("search_type", ResponseConstants.ITEMS);
        if (!TextUtils.isEmpty(this.val$searchCategory)) {
            put("category", "items/".concat(this.val$searchCategory).toLowerCase());
        }
        put(ResponseConstants.QUERY, this.val$query);
        put("total_results", Integer.valueOf(this.val$totalResults));
    }
}

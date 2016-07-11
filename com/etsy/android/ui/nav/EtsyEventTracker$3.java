package com.etsy.android.ui.nav;

import com.etsy.android.ui.cart.SavedCartItemsFragment;
import java.util.HashMap;

final class EtsyEventTracker$3 extends HashMap<String, Object> {
    final /* synthetic */ int val$count;
    final /* synthetic */ String val$pageInView;
    final /* synthetic */ StringBuilder val$sb;

    EtsyEventTracker$3(StringBuilder stringBuilder, int i, String str) {
        this.val$sb = stringBuilder;
        this.val$count = i;
        this.val$pageInView = str;
        put("shop_ids", this.val$sb.toString());
        put("shops_count", Integer.valueOf(this.val$count));
        put(SavedCartItemsFragment.PAGE, this.val$pageInView);
    }
}

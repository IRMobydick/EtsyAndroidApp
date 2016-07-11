package com.etsy.android.ui.local;

import com.etsy.android.lib.models.LocalMarketCard;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.HashMap;

final class LocalAnalytics$2 extends HashMap<String, Object> {
    final /* synthetic */ LocalMarketCard val$market;

    LocalAnalytics$2(LocalMarketCard localMarketCard) {
        this.val$market = localMarketCard;
        if (this.val$market != null) {
            put(ResponseConstants.LOCAL_MARKET_ID, this.val$market.getLocalMarketId().toString());
            put("local_market_type", this.val$market.getMarketType());
        }
    }
}

package com.etsy.android.ui.local;

import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.HashMap;

final class LocalAnalytics$3 extends HashMap<String, Object> {
    final /* synthetic */ LocalMarket val$market;

    LocalAnalytics$3(LocalMarket localMarket) {
        this.val$market = localMarket;
        if (this.val$market != null) {
            put(ResponseConstants.LOCAL_MARKET_ID, this.val$market.getLocalMarketId().toString());
            put("local_market_type", this.val$market.getMarketType());
        }
    }
}

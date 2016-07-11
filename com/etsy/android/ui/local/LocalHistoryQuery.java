package com.etsy.android.ui.local;

import android.net.Uri;
import com.etsy.android.contentproviders.EtsyProvider;

/* renamed from: com.etsy.android.ui.local.j */
public class LocalHistoryQuery {
    public static final Uri f3080a;
    public static final String[] f3081b;

    static {
        f3080a = EtsyProvider.f1098a;
        f3081b = new String[]{"local_market_history._id", "local_market_history.local_market_id"};
    }
}

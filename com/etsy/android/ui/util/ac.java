package com.etsy.android.ui.util;

import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.uikit.IEtsyEndlessListFragment;

/* compiled from: StockResponseFactory */
public class ac implements EtsyJobResponse {
    protected IEtsyEndlessListFragment f3659a;

    public ac(IEtsyEndlessListFragment iEtsyEndlessListFragment) {
        this.f3659a = iEtsyEndlessListFragment;
    }

    public void m5092a(EtsyResult etsyResult) {
        if (this.f3659a.getApiOffset() == 0) {
            this.f3659a.showEmptyView();
        } else {
            this.f3659a.stopEndless();
        }
    }
}

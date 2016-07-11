package com.etsy.android.ui.util;

import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.uikit.IEtsyEndlessListFragment;

/* compiled from: StockResponseFactory */
public class ad implements EtsyJobResponse {
    protected IEtsyEndlessListFragment f3660a;

    public ad(IEtsyEndlessListFragment iEtsyEndlessListFragment) {
        this.f3660a = iEtsyEndlessListFragment;
    }

    public void m5093a(int i, String str, EtsyResult etsyResult) {
        if (this.f3660a.getApiOffset() == 0) {
            this.f3660a.showErrorView();
        } else {
            this.f3660a.showEndlessError();
        }
    }
}

package com.etsy.android.lib.util;

import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.uikit.IEtsyEndlessListFragment;
import com.etsy.android.uikit.adapter.BaseModelArrayAdapter;

@Deprecated
/* compiled from: EndlessListUtil */
public class ac {
    public static int m3185a(EtsyResult etsyResult, BaseModelArrayAdapter baseModelArrayAdapter, IEtsyEndlessListFragment iEtsyEndlessListFragment) {
        int i = 0;
        if (etsyResult != null && etsyResult.m1049a()) {
            int f = etsyResult.m1055f();
            baseModelArrayAdapter.addAll(etsyResult.m1056g());
            i = etsyResult.m1056g().size();
            if (baseModelArrayAdapter.getRealCount() == 0) {
                iEtsyEndlessListFragment.showEmptyView();
            } else if (baseModelArrayAdapter.getRealCount() >= f) {
                iEtsyEndlessListFragment.stopEndless();
                iEtsyEndlessListFragment.showListView();
            } else {
                iEtsyEndlessListFragment.removeEndlessError();
                iEtsyEndlessListFragment.startEndless();
                iEtsyEndlessListFragment.showListView();
            }
        } else if (baseModelArrayAdapter.getRealCount() > 0) {
            iEtsyEndlessListFragment.showEndlessError();
        } else {
            iEtsyEndlessListFragment.showErrorView();
        }
        return i;
    }

    public static int m3186a(BaseModelArrayAdapter baseModelArrayAdapter) {
        return baseModelArrayAdapter.getRealCount();
    }
}

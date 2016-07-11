package com.etsy.android.ui.search.v2;

import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.ui.search.SearchShopsJob;
import com.etsy.android.ui.util.EtsyCardUtil;
import java.util.Collection;

/* renamed from: com.etsy.android.ui.search.v2.t */
class SearchResultsShopsFragment extends SearchShopsJob {
    final /* synthetic */ SearchResultsShopsFragment f3372a;

    public SearchResultsShopsFragment(SearchResultsShopsFragment searchResultsShopsFragment, String str, int i, int i2) {
        this.f3372a = searchResultsShopsFragment;
        super(str, i, i2, false);
    }

    protected void m4947a(EtsyResult<Shop> etsyResult) {
        if (etsyResult.m1049a() && etsyResult.m1056g() != null) {
            Collection g = etsyResult.m1056g();
            int f = etsyResult.m1055f();
            if (this.f3372a.getActivity() == null || f <= 0) {
                if (this.f3372a.mListView.getVisibility() != 0) {
                    this.f3372a.showListView();
                }
                this.f3372a.mEmptySuggestions.setVisibility(0);
                return;
            }
            this.f3372a.mEmptySuggestions.setVisibility(8);
            this.f3372a.mShopsSectionTitle.setText(EtsyCardUtil.m5107a(this.f3372a.mActivity, f));
            this.f3372a.mResultsAdapter.addAll(g);
            this.f3372a.mResultsAdapter.notifyDataSetChanged();
            if (this.f3372a.mListView.getVisibility() != 0) {
                this.f3372a.showListView();
            }
            if (this.f3372a.mResultsAdapter.getCount() >= f) {
                this.f3372a.stopEndless();
            } else {
                this.f3372a.startEndless();
            }
            access$512(this.f3372a, 30);
        } else if (etsyResult.m1060k()) {
            this.f3372a.showErrorView();
        }
    }
}

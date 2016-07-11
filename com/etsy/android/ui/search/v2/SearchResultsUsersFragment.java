package com.etsy.android.ui.search.v2;

import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.models.User;
import com.etsy.android.ui.search.SearchUsersJob;
import com.etsy.android.ui.util.EtsyCardUtil;
import java.util.Collection;

/* renamed from: com.etsy.android.ui.search.v2.u */
class SearchResultsUsersFragment extends SearchUsersJob {
    final /* synthetic */ SearchResultsUsersFragment f3373a;

    public SearchResultsUsersFragment(SearchResultsUsersFragment searchResultsUsersFragment, String str, int i, int i2) {
        this.f3373a = searchResultsUsersFragment;
        super(str, i, i2, false);
    }

    protected void m4949a(EtsyResult<User> etsyResult) {
        if (etsyResult.m1049a() && etsyResult.m1056g() != null) {
            Collection g = etsyResult.m1056g();
            int f = etsyResult.m1055f();
            if (this.f3373a.getActivity() == null || f <= 0) {
                if (this.f3373a.mListView.getVisibility() != 0) {
                    this.f3373a.showListView();
                }
                this.f3373a.mEmptySuggestions.setVisibility(0);
                return;
            }
            this.f3373a.mEmptySuggestions.setVisibility(8);
            this.f3373a.mUsersSectionTitle.setText(EtsyCardUtil.m5111b(this.f3373a.mActivity, f));
            this.f3373a.mResultsAdapter.addAll(g);
            this.f3373a.mResultsAdapter.notifyDataSetChanged();
            if (this.f3373a.mListView.getVisibility() != 0) {
                this.f3373a.showListView();
            }
            if (this.f3373a.mResultsAdapter.getCount() >= f) {
                this.f3373a.stopEndless();
            } else {
                this.f3373a.startEndless();
            }
            access$512(this.f3373a, 30);
        } else if (etsyResult.m1060k()) {
            this.f3373a.showErrorView();
        }
    }
}

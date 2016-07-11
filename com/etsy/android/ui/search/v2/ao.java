package com.etsy.android.ui.search.v2;

import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import com.etsy.android.uikit.IEtsyFragment;
import com.etsy.android.uikit.util.FragmentBackstackUtil;
import org.apache.commons.lang3.StringUtils;

/* compiled from: SearchV2Activity */
class ao implements OnBackStackChangedListener, OnQueryTextListener, OnFocusChangeListener {
    final /* synthetic */ SearchV2Activity f3335a;

    ao(SearchV2Activity searchV2Activity) {
        this.f3335a = searchV2Activity;
    }

    public void onFocusChange(View view, boolean z) {
        if (z && this.f3335a.mViewMode != 1) {
            this.f3335a.enterSuggestMode();
        }
    }

    public boolean onQueryTextSubmit(String str) {
        return false;
    }

    public boolean onQueryTextChange(String str) {
        if (this.f3335a.mViewMode == 1 && this.f3335a.mInputLayout.getTabSelected() == 0) {
            SearchSuggestionsLayout suggestionsLayout = this.f3335a.mInputLayout.getSuggestionsLayout();
            if (suggestionsLayout != null) {
                suggestionsLayout.onQueryTextChange(str);
            }
        }
        return false;
    }

    public void onBackStackChanged() {
        IEtsyFragment c = FragmentBackstackUtil.m5543c(this.f3335a.getSupportFragmentManager());
        if (c != null) {
            if (c instanceof ISearchResultsFragment) {
                this.f3335a.mViewMode = 2;
                this.f3335a.mTabLayout.setVisibility(8);
                this.f3335a.mSearchViewHelper.m4736a(((ISearchResultsFragment) c).getQuery(), false);
            } else if ((c instanceof SearchTaxonomyCategoryPageFragment) || (c instanceof RootTaxonomyCategoryPageFragment)) {
                this.f3335a.mViewMode = 0;
                this.f3335a.mTabLayout.setVisibility(8);
                this.f3335a.mSearchViewHelper.m4735a(StringUtils.EMPTY);
                this.f3335a.mSearchViewHelper.m4738b();
            }
            this.f3335a.updateActionBarAfterModeChange();
        }
    }
}

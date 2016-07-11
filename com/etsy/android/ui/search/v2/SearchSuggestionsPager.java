package com.etsy.android.ui.search.v2;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.ui.search.SearchViewHelper;

public class SearchSuggestionsPager extends ViewPager {
    private static final String STATE_CURRENT_TAB = "currentTab";
    private static final String STATE_SUPER = "super";
    public static final int TAB_ITEMS = 0;
    public static final int TAB_NONE = -1;
    public static final int TAB_PEOPLE = 2;
    public static final int TAB_SHOPS = 1;
    private int mLastFinishedTab;
    private SearchViewHelper mSearchViewHelperProvider;
    private int mSelectedTab;
    private SearchSuggestionsLayout mSuggestionsLayout;

    /* renamed from: com.etsy.android.ui.search.v2.SearchSuggestionsPager.1 */
    class C07941 extends PagerAdapter {
        final /* synthetic */ LayoutInflater f3302a;
        final /* synthetic */ SearchSuggestionsPager f3303b;

        C07941(SearchSuggestionsPager searchSuggestionsPager, LayoutInflater layoutInflater) {
            this.f3303b = searchSuggestionsPager;
            this.f3302a = layoutInflater;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View inflate;
            TextView textView;
            switch (i) {
                case SearchSuggestionsPager.TAB_ITEMS /*0*/:
                    inflate = this.f3302a.inflate(2130903274, viewGroup, false);
                    textView = (TextView) inflate.findViewById(2131755905);
                    ((ImageView) inflate.findViewById(2131755904)).setImageResource(2130837931);
                    textView.setText(R.new_search_items_description);
                    this.f3303b.mSuggestionsLayout = (SearchSuggestionsLayout) inflate.findViewById(2131755906);
                    this.f3303b.mSuggestionsLayout.setSearchViewHelper(this.f3303b.mSearchViewHelperProvider.getSearchViewHelper());
                    viewGroup.addView(inflate);
                    return inflate;
                case SearchSuggestionsPager.TAB_SHOPS /*1*/:
                    inflate = this.f3302a.inflate(2130903273, viewGroup, false);
                    textView = (TextView) inflate.findViewById(2131755905);
                    ((ImageView) inflate.findViewById(2131755904)).setImageResource(2130837933);
                    textView.setText(R.new_search_shops_description);
                    viewGroup.addView(inflate);
                    return inflate;
                case SearchSuggestionsPager.TAB_PEOPLE /*2*/:
                    inflate = this.f3302a.inflate(2130903273, viewGroup, false);
                    textView = (TextView) inflate.findViewById(2131755905);
                    ((ImageView) inflate.findViewById(2131755904)).setImageResource(2130837932);
                    textView.setText(R.new_search_people_description);
                    viewGroup.addView(inflate);
                    return inflate;
                default:
                    return null;
            }
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        public void finishUpdate(ViewGroup viewGroup) {
            super.finishUpdate(viewGroup);
            if (this.f3303b.mLastFinishedTab != this.f3303b.mSelectedTab) {
                this.f3303b.mLastFinishedTab = this.f3303b.mSelectedTab;
                if (this.f3303b.mSelectedTab == 0) {
                    this.f3303b.mSuggestionsLayout.refreshUiState();
                }
            }
        }

        public CharSequence getPageTitle(int i) {
            switch (i) {
                case SearchSuggestionsPager.TAB_ITEMS /*0*/:
                    return this.f3303b.getResources().getString(R.new_search_category_items);
                case SearchSuggestionsPager.TAB_SHOPS /*1*/:
                    return this.f3303b.getResources().getString(R.new_search_category_shops);
                case SearchSuggestionsPager.TAB_PEOPLE /*2*/:
                    return this.f3303b.getResources().getString(R.new_search_category_people);
                default:
                    return null;
            }
        }

        public int getCount() {
            return 3;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

    public SearchSuggestionsPager(Context context) {
        super(context);
        this.mLastFinishedTab = TAB_NONE;
        init(context);
    }

    public SearchSuggestionsPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mLastFinishedTab = TAB_NONE;
        init(context);
    }

    private void init(Context context) {
        setAdapter(new C07941(this, LayoutInflater.from(context)));
        setCurrentItem(TAB_ITEMS);
        this.mSelectedTab = TAB_ITEMS;
    }

    public void setCurrentItem(int i) {
        this.mLastFinishedTab = TAB_NONE;
        super.setCurrentItem(i);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable != null) {
            Bundle bundle = (Bundle) parcelable;
            super.onRestoreInstanceState(bundle.getParcelable(STATE_SUPER));
            this.mSelectedTab = bundle.getInt(STATE_CURRENT_TAB);
            setCurrentItem(this.mSelectedTab);
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable(STATE_SUPER, super.onSaveInstanceState());
        bundle.putInt(STATE_CURRENT_TAB, this.mSelectedTab);
        return bundle;
    }

    public void setupWithTabLayout(TabLayout tabLayout) {
        tabLayout.setupWithViewPager(this);
        addOnPageChangeListener(new af(this, tabLayout));
    }

    public void setSearchViewHelperProvider(SearchViewHelper searchViewHelper) {
        this.mSearchViewHelperProvider = searchViewHelper;
    }

    public int getTabSelected() {
        return this.mSelectedTab;
    }

    public SearchSuggestionsLayout getSuggestionsLayout() {
        return this.mSuggestionsLayout;
    }
}

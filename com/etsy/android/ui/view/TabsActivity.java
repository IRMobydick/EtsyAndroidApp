package com.etsy.android.ui.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.View;
import com.etsy.android.lib.R;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.NavTracker;

public abstract class TabsActivity extends BOENavDrawerActivity implements OnPageChangeListener {
    private int mActionBarHeight;
    protected View mEmptyView;
    protected View mErrorView;
    protected View mLoadingView;
    protected NavTracker mNavTracker;
    protected TabLayout mSlidingTabLayout;
    protected ViewPager mViewPager;

    protected abstract PagerAdapter getViewPagerAdapter();

    protected abstract void logPageAtPosition(int i);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mNavTracker = new NavTracker();
        setContentView(2130903080);
        this.mLoadingView = findViewById(R.loading_view);
        this.mEmptyView = findViewById(R.empty_view);
        this.mErrorView = findViewById(R.no_internet);
        this.mViewPager = (ViewPager) findViewById(2131755314);
        this.mActionBarHeight = getResources().getDimensionPixelOffset(R.actionbar_height);
        this.mSlidingTabLayout = getAppBarHelper().addTabLayout();
        this.mViewPager.addOnPageChangeListener(this);
    }

    protected void setupTabs() {
        this.mViewPager.setAdapter(getViewPagerAdapter());
        this.mSlidingTabLayout.setupWithViewPager(this.mViewPager);
    }

    protected void selectTab(int i) {
        this.mViewPager.setCurrentItem(i, false);
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(2131820551, menu);
        return true;
    }

    protected void showTabsView() {
        this.mViewPager.setVisibility(0);
        this.mErrorView.setVisibility(8);
        this.mEmptyView.setVisibility(8);
        this.mLoadingView.setVisibility(8);
    }

    protected void showLoadingView() {
        this.mViewPager.setVisibility(8);
        this.mErrorView.setVisibility(8);
        this.mEmptyView.setVisibility(8);
        this.mLoadingView.setVisibility(0);
    }

    protected void showErrorView() {
        this.mViewPager.setVisibility(8);
        this.mErrorView.setVisibility(0);
        this.mEmptyView.setVisibility(8);
        this.mLoadingView.setVisibility(8);
    }

    protected void showEmptyView() {
        this.mViewPager.setVisibility(8);
        this.mErrorView.setVisibility(8);
        this.mEmptyView.setVisibility(0);
        this.mLoadingView.setVisibility(8);
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        logPageAtPosition(i);
    }

    public void onPageScrollStateChanged(int i) {
    }
}

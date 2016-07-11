package com.etsy.android.uikit;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.iconsy.AbstractFontIcon;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.util.bl;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.ui.core.NetworkLoaderFragment;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.List;

public abstract class BaseRecyclerViewListFragment<T> extends NetworkLoaderFragment implements OnRefreshListener, IEtsyCommonListFragment {
    private int[] mAccentColors;
    protected BaseRecyclerViewAdapter<T> mAdapter;
    protected Button mEmptyButton;
    protected ImageView mEmptyImage;
    protected TextView mEmptySubtext;
    protected TextView mEmptyText;
    protected View mEmptyView;
    protected View mErrorView;
    protected LayoutManager mLayoutManager;
    private boolean mLoading;
    protected View mLoadingView;
    protected RecyclerView mRecyclerView;
    private boolean mRefreshing;
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    /* renamed from: com.etsy.android.uikit.BaseRecyclerViewListFragment.1 */
    class C09181 extends TrackingOnClickListener {
        final /* synthetic */ BaseRecyclerViewListFragment f3894a;

        C09181(BaseRecyclerViewListFragment baseRecyclerViewListFragment) {
            this.f3894a = baseRecyclerViewListFragment;
        }

        public void onViewClick(View view) {
            this.f3894a.onRetry();
        }
    }

    protected abstract void onLoadContent();

    @LayoutRes
    public int getLayoutId() {
        return R.fragment_baserecyclerview;
    }

    @NonNull
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(getLayoutId(), viewGroup, false);
        this.mSwipeRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.swipe_refresh_layout);
        this.mSwipeRefreshLayout.setOnRefreshListener(this);
        this.mSwipeRefreshLayout.setColorSchemeColors(getColorSchemeColors());
        this.mRecyclerView = (RecyclerView) inflate.findViewById(R.recycler_view);
        if (this.mLayoutManager == null) {
            this.mLayoutManager = new LinearLayoutManager(getActivity());
        }
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mEmptyView = inflate.findViewById(R.empty_view);
        this.mEmptyText = (TextView) inflate.findViewById(R.empty_view_text);
        this.mEmptySubtext = (TextView) inflate.findViewById(R.empty_view_subtext);
        this.mEmptyButton = (Button) inflate.findViewById(R.empty_button);
        this.mEmptyImage = (ImageView) inflate.findViewById(R.empty_image);
        this.mErrorView = inflate.findViewById(R.no_internet);
        this.mLoadingView = inflate.findViewById(R.loading_view);
        View findViewById = this.mErrorView.findViewById(R.btn_retry_internet);
        if (findViewById != null) {
            findViewById.setOnClickListener(new C09181(this));
        }
        return inflate;
    }

    public void onDestroyView() {
        this.mLayoutManager = null;
        this.mEmptyView = null;
        this.mErrorView = null;
        this.mLoadingView = null;
        this.mRecyclerView = null;
        this.mSwipeRefreshLayout = null;
        super.onDestroyView();
    }

    protected int[] getColorSchemeColors() {
        if (this.mAccentColors == null || this.mAccentColors.length == 0) {
            this.mAccentColors = new int[]{bl.m3353a(getActivity())};
        }
        return this.mAccentColors;
    }

    public void setColorSchemeColors(int[] iArr) {
        this.mAccentColors = iArr;
    }

    protected void onLoadSuccess(List<T> list, int i) {
        setLoading(false);
        if (this.mRefreshing) {
            this.mSwipeRefreshLayout.setRefreshing(false);
            this.mRefreshing = false;
            this.mAdapter.clearData();
        }
        this.mAdapter.addItems(list);
        if (isEmpty()) {
            showEmptyView();
        } else {
            showListView();
        }
    }

    protected boolean isEmpty() {
        return this.mAdapter.getDataItemCount() == 0;
    }

    protected void onLoadFailure() {
        setLoading(false);
        if (this.mRefreshing) {
            this.mSwipeRefreshLayout.setRefreshing(false);
            this.mRefreshing = false;
        }
        showErrorView();
    }

    protected void onPreLoadContent() {
        if (!this.mRefreshing) {
            showLoadingView();
        }
    }

    public boolean canLoadContent() {
        return !this.mLoading;
    }

    public boolean isLoading() {
        return this.mLoading;
    }

    public boolean isRefreshing() {
        return this.mRefreshing;
    }

    protected void setRefreshing(boolean z) {
        this.mRefreshing = z;
    }

    protected final void loadContent() {
        if (canLoadContent()) {
            onPreLoadContent();
            setLoading(true);
            onLoadContent();
        }
    }

    public void displayLoadingView(boolean z) {
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(z ? 0 : 8);
        }
    }

    public void setLoading(boolean z) {
        this.mLoading = z;
    }

    public void showLoadingView() {
        if (this.mEmptyView != null) {
            this.mEmptyView.setVisibility(8);
        }
        if (this.mErrorView != null) {
            this.mErrorView.setVisibility(8);
        }
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(0);
        }
        if (this.mRecyclerView != null) {
            this.mRecyclerView.setVisibility(8);
        }
    }

    protected void onRetry() {
        loadContent();
    }

    public void showEmptyView() {
        if (this.mEmptyView != null) {
            this.mEmptyView.setVisibility(0);
        }
        if (this.mErrorView != null) {
            this.mErrorView.setVisibility(8);
        }
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(8);
        }
        if (this.mRecyclerView != null) {
            this.mRecyclerView.setVisibility(8);
        }
    }

    public void showListView() {
        if (this.mEmptyView != null) {
            this.mEmptyView.setVisibility(8);
        }
        if (this.mErrorView != null) {
            this.mErrorView.setVisibility(8);
        }
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(8);
        }
        if (this.mRecyclerView != null) {
            this.mRecyclerView.setVisibility(0);
        }
    }

    public void showErrorView() {
        if (this.mEmptyView != null) {
            this.mEmptyView.setVisibility(8);
        }
        if (this.mErrorView != null) {
            this.mErrorView.setVisibility(0);
        }
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(8);
        }
        if (this.mRecyclerView != null) {
            this.mRecyclerView.setVisibility(8);
        }
    }

    protected IconDrawable createEmptyIcon(AbstractFontIcon abstractFontIcon) {
        return IconDrawable.m775a(getResources()).m780a(abstractFontIcon).m779a(getResources().getColor(R.even_lighter_grey)).m778a((float) getResources().getDimensionPixelSize(R.empty_icon_size)).m777a();
    }

    public void onRefresh() {
        if (!this.mRefreshing) {
            this.mRefreshing = true;
            loadContent();
        }
    }
}

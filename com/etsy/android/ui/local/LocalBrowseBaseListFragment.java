package com.etsy.android.ui.local;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.LocalMarketCard;
import com.etsy.android.lib.models.apiv3.LocalBrowseModule;
import com.etsy.android.lib.models.apiv3.LocalBrowseResponse;
import com.etsy.android.ui.cardview.viewholders.LocalBrowseBaseHeaderViewHolder;
import com.etsy.android.ui.cardview.viewholders.LocalBrowseMarketViewHolder;
import com.etsy.android.ui.cardview.viewholders.LocalBrowseSectionFooterViewHolder;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.BaseRecyclerViewListFragment;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.List;

public abstract class LocalBrowseBaseListFragment<T> extends BaseRecyclerViewListFragment<T> implements LocalBrowseUpdateListener {
    protected LocalBrowseBaseHeaderViewHolder mHeaderListener;
    protected LocalBrowseManager mLocalBrowseManager;
    private LocalMarketCardMarginDecoration mMarketCardDecoration;
    protected LocalBrowseMarketViewHolder mMarketListener;
    protected LocalBrowseSectionFooterViewHolder mSectionListener;

    /* renamed from: com.etsy.android.ui.local.LocalBrowseBaseListFragment.1 */
    class C07221 implements LocalBrowseBaseHeaderViewHolder {
        final /* synthetic */ LocalBrowseBaseListFragment f3011a;

        C07221(LocalBrowseBaseListFragment localBrowseBaseListFragment) {
            this.f3011a = localBrowseBaseListFragment;
        }

        public void m4278a() {
            if (this.f3011a.mLocalBrowseManager != null) {
                this.f3011a.mLocalBrowseManager.onClickMapArea();
            }
        }
    }

    /* renamed from: com.etsy.android.ui.local.LocalBrowseBaseListFragment.2 */
    class C07232 implements LocalBrowseMarketViewHolder {
        final /* synthetic */ LocalBrowseBaseListFragment f3012a;

        C07232(LocalBrowseBaseListFragment localBrowseBaseListFragment) {
            this.f3012a = localBrowseBaseListFragment;
        }

        public void m4279a(LocalMarketCard localMarketCard) {
            LocalAnalytics.m4322b(localMarketCard, this.f3012a.mLocalBrowseManager);
            Nav.m4681a(this.f3012a.getActivity()).m4476a(localMarketCard.getLocalMarketId(), false);
        }
    }

    /* renamed from: com.etsy.android.ui.local.LocalBrowseBaseListFragment.3 */
    class C07243 implements LocalBrowseSectionFooterViewHolder {
        final /* synthetic */ LocalBrowseBaseListFragment f3013a;

        C07243(LocalBrowseBaseListFragment localBrowseBaseListFragment) {
            this.f3013a = localBrowseBaseListFragment;
        }

        public void m4280a(LocalBrowseModule localBrowseModule) {
        }
    }

    /* renamed from: com.etsy.android.ui.local.LocalBrowseBaseListFragment.4 */
    class C07254 extends SpanSizeLookup {
        final /* synthetic */ BaseRecyclerViewAdapter f3014a;
        final /* synthetic */ int f3015b;
        final /* synthetic */ LocalBrowseBaseListFragment f3016c;

        C07254(LocalBrowseBaseListFragment localBrowseBaseListFragment, BaseRecyclerViewAdapter baseRecyclerViewAdapter, int i) {
            this.f3016c = localBrowseBaseListFragment;
            this.f3014a = baseRecyclerViewAdapter;
            this.f3015b = i;
        }

        public int getSpanSize(int i) {
            return this.f3014a.getSpanSize(i, this.f3015b);
        }
    }

    /* renamed from: com.etsy.android.ui.local.LocalBrowseBaseListFragment.5 */
    class C07265 extends TrackingOnClickListener {
        final /* synthetic */ LocalBrowseBaseListFragment f3017a;

        C07265(LocalBrowseBaseListFragment localBrowseBaseListFragment) {
            this.f3017a = localBrowseBaseListFragment;
        }

        public void onViewClick(View view) {
            if (this.f3017a.mLocalBrowseManager != null) {
                this.f3017a.mLocalBrowseManager.expandSearchRadius();
            }
        }
    }

    /* renamed from: com.etsy.android.ui.local.LocalBrowseBaseListFragment.6 */
    class C07276 extends TrackingOnClickListener {
        final /* synthetic */ LocalBrowseBaseListFragment f3018a;

        C07276(LocalBrowseBaseListFragment localBrowseBaseListFragment) {
            this.f3018a = localBrowseBaseListFragment;
        }

        public void onViewClick(View view) {
            if (this.f3018a.mLocalBrowseManager != null) {
                this.f3018a.mLocalBrowseManager.onClickMapArea();
            }
        }
    }

    /* renamed from: com.etsy.android.ui.local.LocalBrowseBaseListFragment.7 */
    class C07287 extends OnScrollListener {
        final /* synthetic */ LocalBrowseBaseListFragment f3019a;

        C07287(LocalBrowseBaseListFragment localBrowseBaseListFragment) {
            this.f3019a = localBrowseBaseListFragment;
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            if (((BaseRecyclerViewAdapter) recyclerView.getAdapter()).getHeaderCount() <= 0 || recyclerView.getChildAdapterPosition(recyclerView.getChildAt(0)) != 0) {
                recyclerView.setVerticalScrollBarEnabled(true);
            } else {
                recyclerView.setVerticalScrollBarEnabled(false);
            }
        }
    }

    protected abstract void getOrFetchResults();

    public LocalBrowseBaseListFragment() {
        this.mHeaderListener = new C07221(this);
        this.mMarketListener = new C07232(this);
        this.mSectionListener = new C07243(this);
    }

    @LayoutRes
    public int getLayoutId() {
        return 2130903214;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mLocalBrowseManager = (LocalBrowseManager) activity;
            this.mLocalBrowseManager.registerUpdateListener(this);
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement LocalBrowseManager");
        }
    }

    private void setUpLayoutManager(BaseRecyclerViewAdapter baseRecyclerViewAdapter) {
        int integer = getResources().getInteger(2131558410);
        LayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), integer);
        SpanSizeLookup c07254 = new C07254(this, baseRecyclerViewAdapter, integer);
        c07254.setSpanIndexCacheEnabled(true);
        gridLayoutManager.setSpanSizeLookup(c07254);
        this.mMarketCardDecoration = new LocalMarketCardMarginDecoration(getActivity(), c07254, true);
        this.mLayoutManager = gridLayoutManager;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setUpLayoutManager(this.mAdapter);
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        onCreateView.findViewById(R.empty_button).setOnClickListener(new C07265(this));
        OnClickListener c07276 = new C07276(this);
        onCreateView.findViewById(2131756324).setOnClickListener(c07276);
        onCreateView.findViewById(2131756344).setOnClickListener(c07276);
        onCreateView.findViewById(2131755721).setVisibility(0);
        this.mRecyclerView.addItemDecoration(this.mMarketCardDecoration);
        this.mRecyclerView.addOnScrollListener(new C07287(this));
        return onCreateView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mLocalBrowseManager.isRequestPending()) {
            showLoadingView();
        } else {
            getOrFetchResults();
        }
    }

    public void onDetach() {
        super.onDetach();
        this.mLocalBrowseManager.unregisterUpdateListener(this);
        this.mLocalBrowseManager = null;
    }

    protected void onLoadContent() {
        getOrFetchResults();
    }

    public void onInitialLocation(Location location) {
    }

    public void onBrowseRequestPending() {
        showLoadingView();
    }

    public void onBrowseResultsSuccess(LocalBrowseResponse localBrowseResponse) {
        setLoading(false);
    }

    public void onSearchResultsSuccess(List<LocalMarketCard> list) {
        setLoading(false);
    }

    public void onResultsError() {
        setLoading(false);
        showErrorView();
    }

    public void onResultsEmpty() {
        setLoading(false);
        showEmptyView();
    }

    public void onToggleListPanel() {
        if (this.mLocalBrowseManager == null) {
            return;
        }
        if (this.mLocalBrowseManager.isListPanelShowing()) {
            this.mRecyclerView.scrollToPosition(0);
        } else {
            this.mRecyclerView.setVerticalScrollBarEnabled(false);
        }
    }

    public void onExpandSearchArea() {
    }
}

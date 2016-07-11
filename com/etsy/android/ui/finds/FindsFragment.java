package com.etsy.android.ui.finds;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.HttpRequestJobBuilder;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsPage;
import com.etsy.android.ui.cardview.CardViewFactoryRecyclerViewAdapter;
import com.etsy.android.ui.homescreen.CardRecyclerViewBaseFragment;
import com.etsy.android.ui.nav.EtsyEventTracker;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class FindsFragment extends CardRecyclerViewBaseFragment {
    private static final String TAG;
    private String mAnchorListing;
    private Map<String, Boolean> mEventsTracked;
    private FindsPage mFindsPage;
    private boolean mIsDraft;
    private String mSlug;

    /* renamed from: com.etsy.android.ui.finds.FindsFragment.1 */
    class C06921 extends OnScrollListener {
        final /* synthetic */ FindsFragment f2967a;

        C06921(FindsFragment findsFragment) {
            this.f2967a = findsFragment;
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
            this.f2967a.trackScrollToBottom();
        }
    }

    /* renamed from: com.etsy.android.ui.finds.FindsFragment.2 */
    class C06932 implements EtsyJobResponse {
        final /* synthetic */ FindsFragment f2968a;

        C06932(FindsFragment findsFragment) {
            this.f2968a = findsFragment;
        }

        public void m4248a(EtsyResult etsyResult) {
            this.f2968a.onLoadSuccessWithoutPagination();
            this.f2968a.mEmptyText.setText(this.f2968a.getEmptyMessage());
            EtsyDebug.m1912c(FindsFragment.TAG, "Empty");
        }
    }

    /* renamed from: com.etsy.android.ui.finds.FindsFragment.3 */
    class C06943 implements EtsyJobResponse {
        final /* synthetic */ FindsFragment f2969a;

        C06943(FindsFragment findsFragment) {
            this.f2969a = findsFragment;
        }

        public void m4249a(int i, String str, EtsyResult etsyResult) {
            this.f2969a.onLoadFailure();
            EtsyDebug.m1912c(FindsFragment.TAG, str);
        }
    }

    /* renamed from: com.etsy.android.ui.finds.FindsFragment.4 */
    class C06954 implements EtsyJobResponse<FindsPage> {
        final /* synthetic */ FindsFragment f2970a;

        C06954(FindsFragment findsFragment) {
            this.f2970a = findsFragment;
        }

        public void m4250a(List<FindsPage> list, int i, EtsyResult<FindsPage> etsyResult) {
            this.f2970a.showContentView();
            FindsRecyclerViewAdapter findsRecyclerViewAdapter = (FindsRecyclerViewAdapter) this.f2970a.getAdapter();
            if (this.f2970a.mSwipeRefreshLayout.isRefreshing()) {
                findsRecyclerViewAdapter.clear();
            }
            this.f2970a.mFindsPage = (FindsPage) etsyResult.m1056g().get(0);
            this.f2970a.trackPageLoad();
            findsRecyclerViewAdapter.setFindsPage(this.f2970a.mFindsPage);
            this.f2970a.onLoadSuccessWithoutPagination();
        }
    }

    public FindsFragment() {
        this.mSlug = StringUtils.EMPTY;
        this.mIsDraft = false;
        this.mEventsTracked = new HashMap();
    }

    static {
        TAG = EtsyDebug.m1891a(FindsFragment.class);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSlug = getArguments().getString("finds_slug");
        this.mAnchorListing = getArguments().getString("ANCHOR_LISTING_ID");
        this.mIsDraft = getArguments().getBoolean("finds_is_draft");
    }

    protected void initAdapter() {
        if (this.mAdapter == null) {
            this.mAdapter = new FindsRecyclerViewAdapter(getActivity(), getImageBatch(), "finds_page", getAnalyticsContext());
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        getAdapter().onSaveInstanceState(bundle);
        super.onSaveInstanceState(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        int applyDimension = (int) TypedValue.applyDimension(1, layoutInflater.getContext().getResources().getDimension(2131361976), layoutInflater.getContext().getResources().getDisplayMetrics());
        this.mRecyclerView.setPadding(applyDimension / 2, 0, applyDimension / 2, applyDimension);
        applyDimension = getSpanCount();
        LayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), applyDimension);
        this.mRecyclerView.setLayoutManager(gridLayoutManager);
        this.mRecyclerView.addOnScrollListener(new C06921(this));
        CardViewFactoryRecyclerViewAdapter adapter = getAdapter();
        adapter.setMaxSpanSize(applyDimension);
        adapter.setPageInView("finds_page");
        gridLayoutManager.setSpanSizeLookup(adapter.getSpanSizeLookup());
        getActivity().setTitle(getResources().getString(R.editors_picks));
        if (bundle != null) {
            adapter.onRestoreInstanceState(bundle);
        }
        if (adapter.getItemCount() == 0) {
            resetAndLoadContent();
        }
        return onCreateView;
    }

    protected int getSpanCount() {
        return getResources().getInteger(2131558457);
    }

    public void onRefresh() {
        this.mEmptyView.setVisibility(8);
        this.mEventsTracked.remove("finds_page");
        removeCachedResponses();
        super.onRefresh();
    }

    protected void onLoadContent() {
        if (this.mSlug.isEmpty()) {
            EtsyDebug.m1912c(TAG, "No slug has been set for this editors picks page.");
            return;
        }
        HttpRequestJobBuilder a = HttpRequestJobBuilder.m1712a(FindsPage.class, "/etsyapps/v3/bespoke/member/finds-page/${slug}$/modules".replace("${slug}$", this.mSlug)).m1743a(new C06954(this)).m1742a(new C06943(this)).m1741a(new C06932(this));
        if (this.mIsDraft) {
            a.m1744a("view_draft_content", Boolean.toString(true));
        }
        if (!TextUtils.isEmpty(this.mAnchorListing)) {
            a.m1744a(ResponseConstants.ANCHOR_LISTING_ID, this.mAnchorListing);
        }
        String a2 = getRequestQueue().m1697a((Object) this, a.m1737a());
        if (a2 != null) {
            this.mRequestCachedKeysUrls.add(a2);
        }
    }

    private void trackPageLoad() {
        if (!this.mEventsTracked.containsKey("finds_page")) {
            EtsyEventTracker.m4553a(getAnalyticsContext(), this.mFindsPage.getSlug(), this.mFindsPage.getFindsPageId(), this.mFindsPage.getFindsPagePublishedId());
            this.mEventsTracked.put("finds_page", Boolean.valueOf(true));
        }
    }

    private void trackScrollToBottom() {
        if (this.mRecyclerView != null && this.mFindsPage != null && !this.mEventsTracked.containsKey("scroll_to_bottom")) {
            if (((GridLayoutManager) this.mRecyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition() == this.mRecyclerView.getAdapter().getItemCount() - 1) {
                this.mEventsTracked.put("scroll_to_bottom", Boolean.valueOf(true));
                EtsyEventTracker.m4562b(getAnalyticsContext(), this.mFindsPage.getSlug(), this.mFindsPage.getFindsPageId(), this.mFindsPage.getFindsPagePublishedId());
            }
        }
    }
}

package com.etsy.android.ui.homescreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.http.body.BaseHttpBody;
import com.etsy.android.lib.core.http.body.FormBody;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.EtsyApiV3RequestJob;
import com.etsy.android.lib.core.http.url.EtsyApiV3Url;
import com.etsy.android.lib.core.p005a.EtsyV3Result;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.Segment;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.lib.models.homescreen.LandingPageInfo;
import com.etsy.android.lib.models.homescreen.LandingPageLink;
import com.etsy.android.ui.cardview.CardViewFactoryRecyclerViewAdapter;
import com.etsy.android.ui.cardview.CardViewHolderFactory;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.util.EtsyBuildHelper;
import java.util.List;
import java.util.Map;
import org.parceler.Parcels;

public class LandingPageFragment extends CardRecyclerViewBaseFragment {
    private static final String KEY_NEXT_LINK = "next_link";
    protected static final int OPTION_CHANGE_LAYOUT = 1;
    private static final String TAG;
    protected final int LIMIT;
    String mApiPathNextLink;
    LandingPageInfo mPageLink;

    /* renamed from: com.etsy.android.ui.homescreen.LandingPageFragment.1 */
    class C07181 extends EtsyApiV3RequestJob<T> {
        final /* synthetic */ LandingPageFragment f3002a;

        /* renamed from: com.etsy.android.ui.homescreen.LandingPageFragment.1.1 */
        class C07171 extends TrackingOnClickListener {
            final /* synthetic */ C07181 f3001a;

            C07171(C07181 c07181) {
                this.f3001a = c07181;
            }

            public void onViewClick(View view) {
                this.f3001a.f3002a.onRefresh();
            }
        }

        C07181(LandingPageFragment landingPageFragment) {
            this.f3002a = landingPageFragment;
        }

        public void m4270a(@NonNull List<T> list, int i, @NonNull EtsyV3Result<T> etsyV3Result) {
            if (list.isEmpty()) {
                if (this.f3002a.getAdapter().getItemCount() == 0) {
                    this.f3002a.showEmptyView();
                    this.f3002a.mEmptyText.setText(this.f3002a.getEmptyMessage());
                    this.f3002a.mEmptyButton.setVisibility(0);
                    this.f3002a.mEmptyButton.setText(R.try_again);
                    this.f3002a.mEmptyButton.setOnClickListener(new C07171(this));
                }
                this.f3002a.onLoadSuccessWithoutPagination();
                return;
            }
            this.f3002a.populateOnSuccess(etsyV3Result);
        }

        public void m4268a(int i, String str, @NonNull EtsyV3Result<T> etsyV3Result) {
            this.f3002a.onLoadFailure();
            EtsyDebug.m1912c(LandingPageFragment.TAG, "onError");
            EtsyDebug.m1919e(LandingPageFragment.TAG, str);
        }
    }

    public LandingPageFragment() {
        this.LIMIT = 30;
        this.mApiPathNextLink = null;
    }

    static {
        TAG = EtsyDebug.m1891a(LandingPageFragment.class);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mPageLink = (LandingPageInfo) Parcels.m7495a(getArguments().getParcelable(ResponseConstants.PAGE_LINK));
        if (this.mPageLink != null) {
            getActivity().setTitle(this.mPageLink.getPageTitle());
            if (this.mPageLink.getLayout() == 0) {
                setLayoutManager(StaggeredGridLayoutManager.class);
            } else if (this.mPageLink.getLayout() == 2) {
                setLayoutManager(GridLayoutManager.class);
            } else if (this.mPageLink.getLayout() == OPTION_CHANGE_LAYOUT) {
                setLayoutManager(LinearLayoutManager.class);
            }
            getAdapter().setPageInView(this.mPageLink.getEventName());
            if (bundle != null) {
                getAdapter().onRestoreInstanceState(bundle);
                this.mApiPathNextLink = bundle.getString(KEY_NEXT_LINK);
            }
            if (this.mAdapter.getItemCount() == 0) {
                resetAndLoadContent();
            }
        }
        return onCreateView;
    }

    public void onResume() {
        super.onResume();
        EtsyLogger.m1966a().m1985a(this.mPageLink.getEventName());
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        getAdapter().onSaveInstanceState(bundle);
        bundle.putString(KEY_NEXT_LINK, this.mApiPathNextLink);
    }

    protected void onLoadContent() {
        EtsyApiV3RequestJob createBuilder = createBuilder();
        if (createBuilder == null) {
            EtsyDebug.m1919e(TAG, "This card type cannot be rendered in a Landing Page yet: " + this.mPageLink.getPageType());
            return;
        }
        String a = getRequestQueue().m1696a((Object) this, createBuilder.m1426c());
        if (a != null) {
            this.mRequestCachedKeysUrls.add(a);
        }
    }

    public boolean canLoadContent() {
        return this.mPageLink != null && super.canLoadContent();
    }

    protected <T extends BaseModel> EtsyApiV3RequestJob<T> createJobBuilder(EtsyApiV3Request<T> etsyApiV3Request) {
        etsyApiV3Request.m1388a(true);
        EtsyApiV3RequestJob<T> a = EtsyApiV3RequestJob.m1464a((EtsyApiV3Request) etsyApiV3Request.m1393d());
        a.m1423a(new C07181(this), (Fragment) this);
        return a;
    }

    protected EtsyApiV3RequestJob<? extends BaseModel> createBuilder() {
        EtsyApiV3Url etsyApiV3Url;
        BaseHttpBody f;
        if (TextUtils.isEmpty(this.mApiPathNextLink)) {
            etsyApiV3Url = (EtsyApiV3Url) ((EtsyApiV3Url) new EtsyApiV3Url(this.mPageLink.getAPIPath()).m1529a(getParams())).m1533b(30);
            f = ((FormBody) new FormBody().m1339a(getBodyParams())).m1345f();
        } else {
            etsyApiV3Url = new EtsyApiV3Url(this.mApiPathNextLink);
            f = null;
        }
        String pageType = this.mPageLink.getPageType();
        if (LandingPageLink.PAGE_TYPE_LISTINGS.equals(pageType) || LandingPageLink.PAGE_TYPE_RECENTLY_VIEWED_LISTINGS.equals(pageType)) {
            return createJobBuilder((EtsyApiV3Request) ((EtsyApiV3Request) new EtsyApiV3Request(ListingCard.class, etsyApiV3Url).m1382a(this.mPageLink.getRequestMethod())).m1383a(f));
        }
        if (LandingPageLink.PAGE_TYPE_SHOPS.equals(pageType)) {
            return createJobBuilder((EtsyApiV3Request) ((EtsyApiV3Request) new EtsyApiV3Request(ShopCard.class, etsyApiV3Url).m1382a(this.mPageLink.getRequestMethod())).m1383a(f));
        }
        if (LandingPageLink.PAGE_TYPE_SEGMENTS.equals(pageType)) {
            return createJobBuilder((EtsyApiV3Request) ((EtsyApiV3Request) new EtsyApiV3Request(Segment.class, etsyApiV3Url).m1382a(this.mPageLink.getRequestMethod())).m1383a(f));
        }
        return null;
    }

    protected <T extends BaseModel> void populateOnSuccess(EtsyV3Result<T> etsyV3Result) {
        CardViewFactoryRecyclerViewAdapter adapter = getAdapter();
        boolean isRefreshing = this.mSwipeRefreshLayout.isRefreshing();
        if (isRefreshing) {
            adapter.clear();
        }
        this.mSwipeRefreshLayout.setRefreshing(false);
        int itemCount = adapter.getItemCount();
        for (BaseModel addItem : etsyV3Result.m1056g()) {
            adapter.addItem(addItem);
        }
        if (isRefreshing) {
            adapter.notifyDataSetChanged();
        } else {
            adapter.notifyItemRangeInserted(itemCount, etsyV3Result.m1054e());
        }
        if (30 > etsyV3Result.m1054e() || (this.mApiPathNextLink != null && this.mApiPathNextLink.equals(etsyV3Result.m1070p()))) {
            setContentExhausted(true);
        } else {
            this.mApiPathNextLink = etsyV3Result.m1070p();
        }
        onLoadSuccessWithOffsetPagination(etsyV3Result.m1054e(), etsyV3Result.m1055f());
    }

    protected Map<String, String> getParams() {
        return this.mPageLink.getParams();
    }

    protected Map<String, String> getBodyParams() {
        return null;
    }

    public void onRefresh() {
        this.mEmptyView.setVisibility(8);
        this.mApiPathNextLink = null;
        removeCachedResponses();
        resetAndLoadContent();
    }

    public int getLoadTriggerPosition() {
        return 15;
    }

    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        super.onPrepareOptionsMenu(menu);
        if (EtsyBuildHelper.m5709d()) {
            menu.add(0, OPTION_CHANGE_LAYOUT, 0, R.change_layout);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != OPTION_CHANGE_LAYOUT) {
            return super.onOptionsItemSelected(menuItem);
        }
        nextLayoutManager();
        return true;
    }

    protected void nextLayoutManager() {
        int spanCount = getSpanCount();
        CardViewFactoryRecyclerViewAdapter adapter = getAdapter();
        CardViewHolderFactory cardViewHolderFactory = (CardViewHolderFactory) adapter.getViewHolderFactory();
        cardViewHolderFactory.m3654b(false);
        if (this.mRecyclerView.getLayoutManager().getClass().equals(GridLayoutManager.class)) {
            this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapter.setMaxSpanSize(OPTION_CHANGE_LAYOUT);
        } else if (this.mRecyclerView.getLayoutManager().getClass().equals(LinearLayoutManager.class)) {
            this.mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(spanCount, OPTION_CHANGE_LAYOUT));
            this.mRecyclerView.setHasFixedSize(false);
            cardViewHolderFactory.m3654b(true);
        } else if (this.mRecyclerView.getLayoutManager().getClass().equals(StaggeredGridLayoutManager.class)) {
            LayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), spanCount);
            this.mRecyclerView.setLayoutManager(gridLayoutManager);
            adapter.setMaxSpanSize(gridLayoutManager.getSpanCount());
            gridLayoutManager.setSpanSizeLookup(adapter.getSpanSizeLookup());
        }
    }
}

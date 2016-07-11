package com.etsy.android.ui.search.v2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.HttpRequestJobBuilder;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.lib.models.apiv3.SearchCategoryRedirectPage;
import com.etsy.android.lib.models.apiv3.TaxonomyCategory;
import com.etsy.android.lib.models.cardviewelement.Page;
import com.etsy.android.lib.models.cardviewelement.SearchPageLink;
import com.etsy.android.ui.cardview.CardViewFactoryRecyclerViewAdapter;
import com.etsy.android.ui.homescreen.CardRecyclerViewBaseFragment;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.cardview.ICardViewElement;
import com.etsy.android.uikit.viewholder.a.b;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcels;

public class SearchTaxonomyCategoryPageFragment extends CardRecyclerViewBaseFragment {
    public static final String TAG;
    private final String PAGE_IN_VIEW;
    private SearchAdsImpressionsLogger mAdsImpressionsLogger;
    private String mAnchorListingId;
    private Page mPage;
    private TaxonomyNode mTaxonomyNode;

    /* renamed from: com.etsy.android.ui.search.v2.SearchTaxonomyCategoryPageFragment.1 */
    class C07951 extends SpanSizeLookup {
        final /* synthetic */ SearchTaxonomyCategoryPageFragment f3304a;

        C07951(SearchTaxonomyCategoryPageFragment searchTaxonomyCategoryPageFragment) {
            this.f3304a = searchTaxonomyCategoryPageFragment;
        }

        public int getSpanSize(int i) {
            CardViewFactoryRecyclerViewAdapter access$000 = this.f3304a.getAdapter();
            if (i >= access$000.getItemCount()) {
                return this.f3304a.getSpanCount();
            }
            if (access$000.getItemViewType(i) == 14) {
                return this.f3304a.getSpanCount();
            }
            return access$000.getSpanSizeLookup().getSpanSize(i);
        }
    }

    /* renamed from: com.etsy.android.ui.search.v2.SearchTaxonomyCategoryPageFragment.2 */
    class C07962 extends b<ICardViewElement> {
        final /* synthetic */ SearchTaxonomyCategoryPageFragment f3305a;

        C07962(SearchTaxonomyCategoryPageFragment searchTaxonomyCategoryPageFragment, String str, FragmentActivity fragmentActivity, AnalyticsTracker analyticsTracker) {
            this.f3305a = searchTaxonomyCategoryPageFragment;
            super(str, fragmentActivity, analyticsTracker);
        }

        public void m4862a(ICardViewElement iCardViewElement) {
            TaxonomyNode taxonomyNode;
            if (iCardViewElement instanceof SearchPageLink) {
                SearchPageLink searchPageLink = (SearchPageLink) iCardViewElement;
                taxonomyNode = new TaxonomyNode(searchPageLink.getTaxonomyId().getId(), searchPageLink.getPageTitle());
                this.f3305a.logTouchEvent("view_more_results_click", taxonomyNode.getTaxonomyNodeId().toString());
                if (this.f3305a.getActivity() instanceof SearchV2Activity) {
                    SearchV2Activity.addSearchResultsFragment(this.c, StringUtils.EMPTY, taxonomyNode);
                }
            } else if (iCardViewElement instanceof TaxonomyCategory) {
                TaxonomyCategory taxonomyCategory = (TaxonomyCategory) iCardViewElement;
                taxonomyNode = taxonomyCategory.buildTaxonomyNode();
                if (!(this.f3305a.mTaxonomyNode == null || taxonomyNode == null)) {
                    this.f3305a.logTouchEvent("category_click", taxonomyNode.getTaxonomyNodeId().toString());
                }
                if (taxonomyCategory.getPageLink().isCategoryPage()) {
                    SearchV2Activity.addCategoryPageFragment(this.c, taxonomyNode, null);
                } else {
                    SearchV2Activity.addSearchResultsFragment(this.c, StringUtils.EMPTY, taxonomyNode);
                }
            }
        }
    }

    /* renamed from: com.etsy.android.ui.search.v2.SearchTaxonomyCategoryPageFragment.3 */
    class C07973 implements EtsyJobResponse {
        final /* synthetic */ SearchTaxonomyCategoryPageFragment f3306a;

        C07973(SearchTaxonomyCategoryPageFragment searchTaxonomyCategoryPageFragment) {
            this.f3306a = searchTaxonomyCategoryPageFragment;
        }

        public void m4864a(int i, String str, EtsyResult etsyResult) {
            this.f3306a.showErrorView();
        }
    }

    /* renamed from: com.etsy.android.ui.search.v2.SearchTaxonomyCategoryPageFragment.4 */
    class C07984 implements EtsyJobResponse<Page> {
        final /* synthetic */ SearchTaxonomyCategoryPageFragment f3307a;

        C07984(SearchTaxonomyCategoryPageFragment searchTaxonomyCategoryPageFragment) {
            this.f3307a = searchTaxonomyCategoryPageFragment;
        }

        public void m4865a(List<Page> list, int i, EtsyResult<Page> etsyResult) {
            EtsyDebug.m1912c(SearchTaxonomyCategoryPageFragment.TAG, "Success");
            this.f3307a.setPage((Page) etsyResult.m1056g().get(0));
        }
    }

    public SearchTaxonomyCategoryPageFragment() {
        this.PAGE_IN_VIEW = "native_category_page";
        this.mAdsImpressionsLogger = new SearchAdsImpressionsLogger();
    }

    static {
        TAG = EtsyDebug.m1891a(SearchTaxonomyCategoryPageFragment.class);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments.containsKey("SEARCH_TAXONOMY_NODE")) {
            this.mTaxonomyNode = (TaxonomyNode) Parcels.m7495a(arguments.getParcelable("SEARCH_TAXONOMY_NODE"));
            if (this.mTaxonomyNode != null) {
                EtsyDebug.m1912c(TAG, "Taxonomy Node is " + this.mTaxonomyNode.toString());
            }
        } else if (bundle == null && arguments.containsKey("SEARCH_CATEGORY_REDIRECT")) {
            SearchCategoryRedirectPage searchCategoryRedirectPage = (SearchCategoryRedirectPage) Parcels.m7495a(arguments.getParcelable("SEARCH_CATEGORY_REDIRECT"));
            this.mPage = searchCategoryRedirectPage.getCategoryLandingPage();
            this.mTaxonomyNode = searchCategoryRedirectPage.getTaxonomyNode();
            arguments.remove("SEARCH_CATEGORY_REDIRECT");
        }
        if (arguments.containsKey("ANCHOR_LISTING_ID")) {
            this.mAnchorListingId = arguments.getString("ANCHOR_LISTING_ID");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        onCreateView.setBackgroundColor(getResources().getColor(R.background_main_v2));
        int spanCount = getSpanCount();
        LayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), spanCount);
        gridLayoutManager.setSpanSizeLookup(new C07951(this));
        getAdapter().setPageInView("native_category_page");
        getAdapter().setMaxSpanSize(spanCount);
        this.mRecyclerView.setLayoutManager(gridLayoutManager);
        this.mRecyclerView.addOnScrollListener(this.mAdsImpressionsLogger);
        b c07962 = new C07962(this, "native_category_page", getActivity(), getAnalyticsContext());
        getAdapter().getViewHolderFactory().m3644a(3, c07962);
        getAdapter().getViewHolderFactory().m3644a(7, c07962);
        getAdapter().getViewHolderFactory().m3644a(15, c07962);
        if (bundle != null) {
            getAdapter().onRestoreInstanceState(bundle);
        } else if (this.mPage != null) {
            setPage(this.mPage);
            this.mPage = null;
        } else {
            loadContent();
        }
        return onCreateView;
    }

    public void onResume() {
        super.onResume();
        HashMap hashMap = new HashMap();
        if (this.mTaxonomyNode != null) {
            hashMap.put(ResponseConstants.TAXONOMY_ID, this.mTaxonomyNode.getTaxonomyNodeId().toString());
        }
        EtsyLogger.m1966a().m1992a("native_category_page", hashMap);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        getAdapter().onSaveInstanceState(bundle);
        this.mAdsImpressionsLogger.onSaveInstanceState(bundle);
    }

    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        this.mAdsImpressionsLogger.onRestoreInstanceState(bundle);
    }

    protected void onLoadContent() {
        HttpRequestJobBuilder a = HttpRequestJobBuilder.m1712a(Page.class, "/etsyapps/v3/bespoke/member/category-taxonomy-page").m1743a(new C07984(this)).m1742a(new C07973(this));
        if (this.mTaxonomyNode != null) {
            a.m1744a(ResponseConstants.TAXONOMY_ID, this.mTaxonomyNode.getTaxonomyNodeId().toString());
        }
        if (this.mAnchorListingId != null) {
            a.m1744a(ResponseConstants.ANCHOR_LISTING_ID, this.mAnchorListingId);
        }
        Locale locale = Locale.getDefault();
        if (locale != null) {
            String country = locale.getCountry();
            if (!TextUtils.isEmpty(country)) {
                a.m1744a("ship_to", country);
            }
        }
        getRequestQueue().m1699a(a.m1737a());
    }

    private void setPage(Page page) {
        BaseActivity baseActivity = (BaseActivity) getActivity();
        if (baseActivity != null) {
            showContentView();
            CardViewFactoryRecyclerViewAdapter adapter = getAdapter();
            if (this.mSwipeRefreshLayout.isRefreshing()) {
                adapter.clear();
                this.mAdsImpressionsLogger.reset();
            }
            this.mSwipeRefreshLayout.setRefreshing(false);
            baseActivity.getAppBarHelper().setTitle(page.getTitle());
            adapter.addPage(page);
            onLoadSuccessWithoutPagination();
        }
    }

    private void logTouchEvent(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put(ResponseConstants.TAXONOMY_ID, str2);
        EtsyLogger.m1966a().m1997b(str, "native_category_page", hashMap);
    }
}

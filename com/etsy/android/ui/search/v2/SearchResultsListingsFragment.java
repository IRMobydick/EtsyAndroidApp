package com.etsy.android.ui.search.v2;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.iconsy.FontSets;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.iconsy.views.IconSelectorDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.EtsyJobBuilder;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyNetworkJob;
import com.etsy.android.lib.core.EtsyRequestQueue;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.logger.p010a.EtsyGraphite;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.lib.models.apiv3.FacetCount;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.apiv3.LocalDeliveryMarket;
import com.etsy.android.lib.models.apiv3.SearchCategoryRedirectPage;
import com.etsy.android.lib.models.apiv3.SearchGroup;
import com.etsy.android.lib.models.apiv3.SearchWithAds;
import com.etsy.android.lib.models.cardviewelement.BasicSectionHeader;
import com.etsy.android.lib.models.cardviewelement.ListSection;
import com.etsy.android.lib.models.cardviewelement.SearchFilterHeader;
import com.etsy.android.lib.requests.apiv3.LocalDeliveryMarketRequest;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.ui.cardview.CardViewFactoryRecyclerViewAdapter;
import com.etsy.android.ui.cardview.CardViewHolderFactory;
import com.etsy.android.ui.homescreen.CardRecyclerViewBaseFragment;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.ui.search.v2.MutableSearchOptions.Data;
import com.etsy.android.uikit.cardview.EtsyAsapSearchTooltip;
import com.etsy.android.uikit.cardview.ICardViewElement;
import com.etsy.android.uikit.util.AdHocEventCompatBuilder;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.viewholder.a.b;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcels;

public class SearchResultsListingsFragment extends CardRecyclerViewBaseFragment implements ISearchResultsFragment {
    private static final int ITEMS_PER_PAGE = 30;
    private static final String SAVE_API_OFFSET = "api_offset";
    private static final String SAVE_CATEGORY_FACETS = "category_facets";
    private static final String SAVE_DISPLAYED_ADS = "displayed_ads";
    private static final String SAVE_ETSY_ASAP_ELIGIBILE = "save_etsy_asap_eligibile";
    private static final String SAVE_FILTER_SHEET_IS_SHOWING = "filter_sheet_is_showing";
    private static final String SAVE_RESULT_COUNT = "result_count";
    private static final String SAVE_SEARCH_OPTIONS = "search_options";
    public static final String TAG;
    private static final int TYPE_LISTING = 0;
    final int ETSY_ASAP_ELIGIBLE;
    final int ETSY_ASAP_INELIGIBLE;
    final int ETSY_ASAP_UNKNOWN;
    protected SearchAdsImpressionsLogger mAdsImpressionsLogger;
    @Nullable
    private String mAnchorListingId;
    List<FacetCount> mCategoryFacets;
    SearchFilterHeader mFilterHeader;
    SearchFiltersSheet mFiltersSheet;
    protected SearchWithAds mForwardedSearchWithAds;
    int mIsEtsyAsapEligible;
    final SearchResultsListingsFragment mListeners;
    String mQuery;
    private int mResultCount;
    protected MutableSearchOptions mSearchOptions;
    SearchResultsListingsFragment mSpanSizeLookup;
    private TaxonomyNode mTaxonomyNode;

    /* renamed from: com.etsy.android.ui.search.v2.SearchResultsListingsFragment.10 */
    final class AnonymousClass10 implements OnClickListener {
        final /* synthetic */ SearchFiltersSheet f3284a;

        AnonymousClass10(SearchFiltersSheet searchFiltersSheet) {
            this.f3284a = searchFiltersSheet;
        }

        public void onClick(View view) {
            this.f3284a.m4922b();
        }
    }

    /* renamed from: com.etsy.android.ui.search.v2.SearchResultsListingsFragment.1 */
    class C07821 extends b<ICardViewElement> {
        final /* synthetic */ SearchResultsListingsFragment f3285a;

        C07821(SearchResultsListingsFragment searchResultsListingsFragment, String str, FragmentActivity fragmentActivity, AnalyticsTracker analyticsTracker) {
            this.f3285a = searchResultsListingsFragment;
            super(str, fragmentActivity, analyticsTracker);
        }

        public void m4843a(ICardViewElement iCardViewElement) {
            m4842a((SearchGroup) iCardViewElement);
        }

        public void m4842a(SearchGroup searchGroup) {
            SearchV2Activity.addSearchResultsFragment(this.f3285a.getActivity(), this.f3285a.mQuery, searchGroup.buildTaxonomyNode());
        }
    }

    /* renamed from: com.etsy.android.ui.search.v2.SearchResultsListingsFragment.2 */
    class C07832 implements SearchRequests {
        final /* synthetic */ LinearLayout f3286a;
        final /* synthetic */ View f3287b;
        final /* synthetic */ SearchResultsListingsFragment f3288c;

        C07832(SearchResultsListingsFragment searchResultsListingsFragment, LinearLayout linearLayout, View view) {
            this.f3288c = searchResultsListingsFragment;
            this.f3286a = linearLayout;
            this.f3287b = view;
        }

        public void m4848a(List<String> list) {
            if (this.f3288c.getActivity() != null) {
                Activity activity = this.f3288c.getActivity();
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    this.f3286a.addView(SearchResultsListingsFragment.createSimplifiedQueryView(activity, (String) list.get(i)));
                }
                if (!list.isEmpty()) {
                    this.f3287b.setVisibility(0);
                }
            }
        }

        public void m4847a() {
        }
    }

    /* renamed from: com.etsy.android.ui.search.v2.SearchResultsListingsFragment.3 */
    class C07843 extends b<ICardViewElement> {
        final /* synthetic */ SearchResultsListingsFragment f3289a;

        C07843(SearchResultsListingsFragment searchResultsListingsFragment, String str, FragmentActivity fragmentActivity, AnalyticsTracker analyticsTracker) {
            this.f3289a = searchResultsListingsFragment;
            super(str, fragmentActivity, analyticsTracker);
        }

        public void m4849a(ICardViewElement iCardViewElement) {
            this.f3289a.mFiltersSheet.m4832a(this.f3289a.mCategoryFacets);
        }
    }

    /* renamed from: com.etsy.android.ui.search.v2.SearchResultsListingsFragment.4 */
    class C07854 extends b<ICardViewElement> {
        final /* synthetic */ SearchResultsListingsFragment f3290a;

        C07854(SearchResultsListingsFragment searchResultsListingsFragment, String str, FragmentActivity fragmentActivity, AnalyticsTracker analyticsTracker) {
            this.f3290a = searchResultsListingsFragment;
            super(str, fragmentActivity, analyticsTracker);
        }

        public void m4852a(ICardViewElement iCardViewElement) {
            this.f3290a.mFiltersSheet.m4832a(this.f3290a.mCategoryFacets);
            EtsyLogger.m1966a().m2001d("local_delivery_search_tooltip_tapped", this.b);
            EtsyGraphite.m1807a("local_delivery.search_tooltip.tapped");
        }

        public void m4851a(int i) {
            this.f3290a.getAdapter().removeItem(i);
            SharedPreferencesUtility.m3137c(this.f3290a.getActivity(), "show_etsy_asap_search_tooltip", false);
            EtsyLogger.m1966a().m2001d("local_delivery_search_tooltip_dismissed", this.b);
            EtsyGraphite.m1807a("local_delivery.search_tooltip.dismissed");
        }
    }

    /* renamed from: com.etsy.android.ui.search.v2.SearchResultsListingsFragment.5 */
    class C07865 implements EtsyJobResponse {
        final /* synthetic */ SearchResultsListingsFragment f3291a;

        C07865(SearchResultsListingsFragment searchResultsListingsFragment) {
            this.f3291a = searchResultsListingsFragment;
        }

        public void m4854a(int i, String str, EtsyResult etsyResult) {
            EtsyDebug.m1919e(SearchResultsListingsFragment.TAG, "Unexpected error response from Etsy ASAP Market request: (" + i + ") " + str);
            this.f3291a.loadResults();
        }
    }

    /* renamed from: com.etsy.android.ui.search.v2.SearchResultsListingsFragment.6 */
    class C07876 implements EtsyJobResponse<LocalDeliveryMarket> {
        final /* synthetic */ SearchResultsListingsFragment f3292a;

        C07876(SearchResultsListingsFragment searchResultsListingsFragment) {
            this.f3292a = searchResultsListingsFragment;
        }

        public void m4855a(List<LocalDeliveryMarket> list, int i, EtsyResult<LocalDeliveryMarket> etsyResult) {
            if (!list.isEmpty()) {
                LocalDeliveryMarket localDeliveryMarket = (LocalDeliveryMarket) list.get(0);
                this.f3292a.mFiltersSheet.m4834a(localDeliveryMarket.isAvailable());
                this.f3292a.mIsEtsyAsapEligible = localDeliveryMarket.isAvailable() ? 2 : 1;
            }
            this.f3292a.loadResults();
        }
    }

    /* renamed from: com.etsy.android.ui.search.v2.SearchResultsListingsFragment.7 */
    class C07887 implements EtsyJobResponse {
        final /* synthetic */ SearchResultsListingsFragment f3293a;

        C07887(SearchResultsListingsFragment searchResultsListingsFragment) {
            this.f3293a = searchResultsListingsFragment;
        }

        public void m4856a(EtsyResult etsyResult) {
            EtsyDebug.m1919e(SearchResultsListingsFragment.TAG, "Unexpected empty response from Etsy ASAP Market request");
            this.f3293a.loadResults();
        }
    }

    /* renamed from: com.etsy.android.ui.search.v2.SearchResultsListingsFragment.8 */
    class C07898 implements SearchRequests {
        final /* synthetic */ SearchResultsListingsFragment f3294a;

        C07898(SearchResultsListingsFragment searchResultsListingsFragment) {
            this.f3294a = searchResultsListingsFragment;
        }

        public void m4860a(SearchWithAds searchWithAds) {
            this.f3294a.setSearchWithAdsResults(searchWithAds);
        }

        public void m4859a() {
            this.f3294a.onLoadFailure();
        }
    }

    /* renamed from: com.etsy.android.ui.search.v2.SearchResultsListingsFragment.9 */
    final class C07909 extends TrackingOnClickListener {
        final /* synthetic */ Activity f3295a;
        final /* synthetic */ String f3296b;

        C07909(AnalyticsLogAttribute analyticsLogAttribute, Object obj, Activity activity, String str) {
            this.f3295a = activity;
            this.f3296b = str;
            super(analyticsLogAttribute, obj);
        }

        public void onViewClick(View view) {
            Nav.m4681a(this.f3295a).m4488a(this.f3296b, null, null, null, null);
        }
    }

    public SearchResultsListingsFragment() {
        this.ETSY_ASAP_UNKNOWN = 0;
        this.ETSY_ASAP_INELIGIBLE = 1;
        this.ETSY_ASAP_ELIGIBLE = 2;
        this.mAnchorListingId = null;
        this.mIsEtsyAsapEligible = 0;
        this.mListeners = new SearchResultsListingsFragment(this);
        this.mAdsImpressionsLogger = new SearchAdsImpressionsLogger();
    }

    static {
        TAG = EtsyDebug.m1891a(SearchResultsListingsFragment.class);
    }

    @LayoutRes
    public int getLayoutId() {
        return 2130903220;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments == null) {
            this.mQuery = StringUtils.EMPTY;
        } else if (bundle == null && arguments.containsKey("SEARCH_CATEGORY_REDIRECT")) {
            SearchCategoryRedirectPage searchCategoryRedirectPage = (SearchCategoryRedirectPage) Parcels.m7495a(arguments.getParcelable("SEARCH_CATEGORY_REDIRECT"));
            this.mForwardedSearchWithAds = searchCategoryRedirectPage.getSearchResults();
            this.mTaxonomyNode = searchCategoryRedirectPage.getTaxonomyNode();
            arguments.remove("SEARCH_CATEGORY_REDIRECT");
            this.mQuery = searchCategoryRedirectPage.getFilterParam(SearchCategoryRedirectPage.PARAM_QUERY);
            SearchV2Activity.getSearchViewHelper(getActivity()).m4735a(this.mQuery);
        } else {
            this.mQuery = arguments.getString("SEARCH_QUERY", StringUtils.EMPTY);
            this.mAnchorListingId = arguments.getString("ANCHOR_LISTING_ID", null);
            if (bundle == null) {
                this.mTaxonomyNode = (TaxonomyNode) Parcels.m7495a(arguments.getParcelable("SEARCH_TAXONOMY_NODE"));
                Data data = (Data) arguments.getParcelable("SEARCH_OPTIONS");
                if (data != null) {
                    this.mSearchOptions = data.toSearchOptions(new ArrayList());
                    return;
                }
                return;
            }
            this.mTaxonomyNode = null;
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.mFiltersSheet != null) {
            bundle.putBoolean(SAVE_FILTER_SHEET_IS_SHOWING, this.mFiltersSheet.m4837d());
        }
        bundle.putInt(SAVE_RESULT_COUNT, this.mResultCount);
        bundle.putInt(SAVE_API_OFFSET, getApiOffset());
        this.mAdsImpressionsLogger.onSaveInstanceState(bundle);
        getAdapter().onSaveInstanceState(bundle);
        if (this.mCategoryFacets != null) {
            bundle.putParcelable(SAVE_CATEGORY_FACETS, Parcels.m7494a(this.mCategoryFacets));
        }
        if (this.mSearchOptions != null) {
            bundle.putParcelable(SAVE_SEARCH_OPTIONS, this.mSearchOptions.m4791x());
        }
        bundle.putInt(SAVE_ETSY_ASAP_ELIGIBILE, this.mIsEtsyAsapEligible);
        super.onSaveInstanceState(bundle);
    }

    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        this.mAdsImpressionsLogger.onRestoreInstanceState(bundle);
    }

    private void init(Bundle bundle) {
        if (bundle != null) {
            this.mIsEtsyAsapEligible = bundle.getInt(SAVE_ETSY_ASAP_ELIGIBILE);
            this.mCategoryFacets = (List) Parcels.m7495a(bundle.getParcelable(SAVE_CATEGORY_FACETS));
            Data data = (Data) bundle.getParcelable(SAVE_SEARCH_OPTIONS);
            if (!(data == null || this.mCategoryFacets == null)) {
                this.mSearchOptions = data.toSearchOptions(this.mCategoryFacets);
            }
            this.mResultCount = bundle.getInt(SAVE_RESULT_COUNT);
            setApiOffset(bundle.getInt(SAVE_API_OFFSET));
            getAdapter().onRestoreInstanceState(bundle);
        }
        if (this.mSearchOptions == null) {
            this.mSearchOptions = new MutableSearchOptions();
        }
    }

    @NonNull
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        SpanSizeLookup searchResultsListingsFragment = new SearchResultsListingsFragment(this);
        int i = searchResultsListingsFragment.f3367e;
        LayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), i);
        gridLayoutManager.setSpanCount(i);
        gridLayoutManager.setSpanSizeLookup(searchResultsListingsFragment);
        this.mLayoutManager = gridLayoutManager;
        this.mSpanSizeLookup = searchResultsListingsFragment;
        CardViewFactoryRecyclerViewAdapter adapter = getAdapter();
        String str = "search";
        adapter.setPageInView("search");
        adapter.setMaxSpanSize(i);
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mRecyclerView.addOnScrollListener(this.mAdsImpressionsLogger);
        CardViewHolderFactory cardViewHolderFactory = (CardViewHolderFactory) adapter.getViewHolderFactory();
        cardViewHolderFactory.m3644a(14, (b) new C07821(this, "search", getActivity(), getAnalyticsContext()));
        cardViewHolderFactory.m3644a(13, (b) new C07843(this, "search", getActivity(), getAnalyticsContext()));
        cardViewHolderFactory.m3644a(36, (b) new C07854(this, "search", getActivity(), getAnalyticsContext()));
        init(bundle);
        this.mFiltersSheet = new SearchFiltersSheet(onCreateView, this.mSearchOptions, this.mListeners);
        SearchV2Activity.getSearchViewHelper(getActivity()).m4738b();
        if (this.mForwardedSearchWithAds != null) {
            initTopLevelFacet();
            setSearchWithAdsResults(this.mForwardedSearchWithAds);
            this.mForwardedSearchWithAds = null;
        } else if (isEmpty()) {
            loadContent();
        }
        if (bundle != null && bundle.getBoolean(SAVE_FILTER_SHEET_IS_SHOWING, false)) {
            this.mFiltersSheet.m4832a(this.mCategoryFacets);
        }
        this.mEmptyView.setVisibility(8);
        return onCreateView;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.setVisibility(0);
    }

    public void onDestroyView() {
        this.mFiltersSheet.m4839f();
        this.mFiltersSheet = null;
        getRequestQueue().m1700a((Object) this);
        super.onDestroyView();
    }

    protected void initEmptyView(View view) {
        this.mEmptyView = view.findViewById(R.empty_view);
        this.mEmptyText = (TextView) view.findViewById(R.empty_view_text);
        this.mEmptySubtext = (TextView) view.findViewById(R.empty_view_subtext);
        this.mEmptyButton = (Button) view.findViewById(R.btn_retry_internet);
        this.mEmptyImage = (ImageView) view.findViewById(R.empty_image);
    }

    protected int getSpanCount() {
        EtsyDebug.m1894a(new UnsupportedOperationException("getSpanCount is invalidfor this Fragment"));
        return -1;
    }

    private void getBuyerAsapEligible() {
        getRequestQueue().m1697a((Object) this, EtsyJobBuilder.m1307a(LocalDeliveryMarketRequest.get(getActivity())).m1319a(new C07887(this)).m1321a(new C07876(this)).m1320a(new C07865(this)).m1324a());
    }

    private void loadSearchResultsWithTaxonomyFilter() {
        initTopLevelFacet();
        loadSearchResults();
    }

    private void initTopLevelFacet() {
        if (this.mTaxonomyNode != null) {
            this.mFiltersSheet.m4831a(new FacetCount(this.mTaxonomyNode.getTaxonomyNodeId().getId(), this.mTaxonomyNode.getName(), 0));
            this.mTaxonomyNode = null;
        }
    }

    private void loadSearchResults() {
        int apiOffset = getApiOffset();
        if ((apiOffset == 0 ? 1 : null) != null) {
            if (!isRefreshing()) {
                showLoadingView();
            }
            this.mAdsImpressionsLogger.reset();
        }
        EtsyNetworkJob a = SearchRequests.m4942a(this.mQuery, this.mAnchorListingId, this.mFiltersSheet.m4838e(), SearchRequests.f3357a, apiOffset, ITEMS_PER_PAGE, new C07898(this));
        EtsyRequestQueue requestQueue = getRequestQueue();
        requestQueue.m1700a((Object) this);
        requestQueue.m1697a((Object) this, a);
    }

    void setSearchWithAdsResults(SearchWithAds searchWithAds) {
        int i;
        int i2 = 1;
        boolean z = getApiOffset() == 0;
        List categoryFacetCounts = searchWithAds.getFacetCountListMap().getCategoryFacetCounts();
        if (categoryFacetCounts != null && categoryFacetCounts.size() > 0) {
            this.mCategoryFacets = categoryFacetCounts;
            FacetCount a = this.mFiltersSheet.m4838e().m4751a();
            if (a != null) {
                this.mFiltersSheet.m4833a(categoryFacetCounts, a.getId());
            }
        }
        if (z) {
            this.mAdapter.clear();
            this.mResultCount = searchWithAds.getCount();
        }
        boolean z2 = z && this.mIsEtsyAsapEligible == 2 && getConfigMap().m885c(EtsyConfigKeys.bY) && SharedPreferencesUtility.m3133b(getActivity(), "show_etsy_asap_search_tooltip", true);
        if (searchWithAds.getCount() > 0) {
            onAddItems(searchWithAds, z, z2);
        }
        int size = searchWithAds.getListingCardList().size();
        if (z2) {
            i = 1;
        } else {
            i = 0;
        }
        i += size;
        size = searchWithAds.getCount();
        if (!z2) {
            i2 = 0;
        }
        onLoadSuccessWithOffsetPagination(i, i2 + size);
    }

    private void onAddItems(SearchWithAds searchWithAds, boolean z, boolean z2) {
        String string = getString(R.search_header_subtitle, this.mQuery);
        CardViewFactoryRecyclerViewAdapter adapter = getAdapter();
        ListingCard anchorListing = searchWithAds.getAnchorListing();
        if (anchorListing != null) {
            ListSection listSection = new ListSection();
            List arrayList = new ArrayList();
            anchorListing.setViewType(34);
            arrayList.add(anchorListing);
            listSection.setItems(arrayList);
            adapter.addListSection(listSection);
        }
        List searchGroups = searchWithAds.getSearchGroups();
        if (searchGroups.size() > 0) {
            new BasicSectionHeader().setTitle(getString(R.results));
            ListSection listSection2 = new ListSection();
            listSection2.setHeader(new BasicSectionHeader(getString(R.search_header_title_groups), string));
            if (searchGroups.size() >= 2 && searchGroups.size() % 2 != 0) {
                ((SearchGroup) searchGroups.get(0)).setWide(1);
                ((SearchGroup) searchGroups.get(1)).setWide(2);
            }
            listSection2.setItems(searchGroups);
            adapter.addListSection(listSection2);
        }
        if (!z || searchGroups.size() <= 0) {
            if (z) {
                this.mFilterHeader = new SearchFilterHeader(getString(R.search_filter));
                adapter.addItem(this.mFilterHeader);
            }
            if (z2) {
                adapter.addItem(new EtsyAsapSearchTooltip());
                new AdHocEventCompatBuilder("local_delivery_search_tooltip_show").m5515a(adapter.getPageInView()).m5513a(getAnalyticsContext()).m5517a();
                EtsyGraphite.m1807a("local_delivery.search_tooltip.show");
            }
            for (ListingCard anchorListing2 : searchWithAds.getListingCardList()) {
                adapter.addItem(anchorListing2);
            }
        } else {
            ListSection listSection3 = new ListSection();
            listSection3.setHeader(new BasicSectionHeader(getString(R.all_items), string));
            List arrayList2 = new ArrayList();
            this.mFilterHeader = new SearchFilterHeader(getString(R.search_filter));
            arrayList2.add(this.mFilterHeader);
            if (z2) {
                arrayList2.add(new EtsyAsapSearchTooltip());
                new AdHocEventCompatBuilder("local_delivery_search_tooltip_show").m5515a(adapter.getPageInView()).m5513a(getAnalyticsContext()).m5517a();
                EtsyGraphite.m1807a("local_delivery.search_tooltip.show");
            }
            arrayList2.addAll(searchWithAds.getListingCardList());
            listSection3.setItems(arrayList2);
            adapter.addListSection(listSection3);
        }
        if (this.mFilterHeader != null) {
            this.mFilterHeader.setTitle(getResources().getQuantityString(R.item_lowercase_quantity, searchWithAds.getCount(), new Object[]{NumberFormat.getInstance().format((long) searchWithAds.getCount())}));
            this.mFilterHeader.setSubtitle(buildActiveOptionsString(this.mFiltersSheet.m4841h()));
        }
        this.mSpanSizeLookup.m4946a();
    }

    protected String buildActiveOptionsString(List<SearchFiltersSheet> list) {
        StringBuilder stringBuilder = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(((SearchFiltersSheet) list.get(i)).m4921a());
        }
        return stringBuilder.toString();
    }

    protected void onLoadContent() {
        if (this.mIsEtsyAsapEligible == 0) {
            getBuyerAsapEligible();
        } else {
            loadResults();
        }
    }

    protected void loadResults() {
        if (this.mTaxonomyNode != null) {
            loadSearchResultsWithTaxonomyFilter();
        } else {
            loadSearchResults();
        }
    }

    public int getLoadTriggerPosition() {
        return 16;
    }

    public boolean handleBackPressed() {
        if (this.mFiltersSheet == null || !this.mFiltersSheet.m4837d()) {
            return super.handleBackPressed();
        }
        return this.mFiltersSheet.m4836c();
    }

    static View createSimplifiedQueryView(Activity activity, String str) {
        Resources resources = activity.getResources();
        View textView = new TextView(activity);
        textView.setLayoutParams(new LayoutParams(-2, -2));
        int dimensionPixelSize = resources.getDimensionPixelSize(R.fixed_medium);
        textView.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        textView.setTextAppearance(activity, R.TextBlue);
        textView.setText(str);
        textView.setOnClickListener(new C07909(AnalyticsLogAttribute.QUERY, str, activity, str));
        return textView;
    }

    private static View createFilterRemovalView(Activity activity, SearchFiltersSheet searchFiltersSheet) {
        Resources resources = activity.getResources();
        View textView = new TextView(activity);
        int dimensionPixelSize = resources.getDimensionPixelSize(R.fixed_medium);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        textView.setLayoutParams(layoutParams);
        textView.setBackgroundResource(R.btn_white_selector_v2);
        textView.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        textView.setCompoundDrawablePadding(dimensionPixelSize);
        textView.setTextAppearance(activity, R.TextMidGrey);
        textView.setText(searchFiltersSheet.m4921a());
        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, IconSelectorDrawable.create(IconDrawable.m775a(resources).m781b((int) R.blue).m784e(17).m780a(FontSets.m767a((int) R.ic_standard_delete)).m782c(R.text_medium)), null);
        textView.setOnClickListener(new AnonymousClass10(searchFiltersSheet));
        return textView;
    }

    public void showEmptyView() {
        Resources resources = this.mEmptyView.getResources();
        TextView textView = (TextView) this.mEmptyView.findViewById(2131756335);
        if (TextUtils.isEmpty(this.mQuery)) {
            textView.setText(R.new_search_empty_view_title_for_no_query);
        } else {
            textView.setText(resources.getString(R.new_search_empty_view_title, new Object[]{this.mQuery}));
        }
        LinearLayout linearLayout = (LinearLayout) this.mEmptyView.findViewById(2131756337);
        linearLayout.removeAllViews();
        List g = this.mFiltersSheet.m4840g();
        int size = g.size();
        for (int i = 0; i < size; i++) {
            linearLayout.addView(createFilterRemovalView(getActivity(), (SearchFiltersSheet) g.get(i)));
        }
        this.mEmptyView.findViewById(2131756336).setVisibility(g.isEmpty() ? 8 : 0);
        View findViewById = this.mEmptyView.findViewById(2131756338);
        findViewById.setVisibility(8);
        linearLayout = (LinearLayout) this.mEmptyView.findViewById(2131756339);
        linearLayout.removeAllViews();
        getRequestQueue().m1699a(SearchRequests.m4941a(this.mQuery, new C07832(this, linearLayout, findViewById)));
        super.showEmptyView();
    }

    public String getQuery() {
        return this.mQuery;
    }
}

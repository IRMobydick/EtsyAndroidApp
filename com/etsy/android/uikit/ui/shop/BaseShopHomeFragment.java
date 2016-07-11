package com.etsy.android.uikit.ui.shop;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.http.loader.NetworkLoader;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.EtsyApiV3RequestJob;
import com.etsy.android.lib.core.http.url.p009a.EtsyV3Urls;
import com.etsy.android.lib.core.p005a.EtsyV3Result;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ShopSection;
import com.etsy.android.lib.models.apiv3.FAQ;
import com.etsy.android.lib.models.apiv3.FAQs;
import com.etsy.android.lib.models.apiv3.ShopHomePage;
import com.etsy.android.lib.models.apiv3.ShopListingsSearchResult;
import com.etsy.android.lib.models.apiv3.ShopV3;
import com.etsy.android.lib.models.apiv3.TranslatedPrivacyPolicy;
import com.etsy.android.lib.models.apiv3.TranslatedReview;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.ShopHomeSortOption;
import com.etsy.android.lib.models.view.shop.FAQTranslationData;
import com.etsy.android.lib.models.view.shop.ShopHomeReviewViewModel;
import com.etsy.android.lib.models.view.shop.section.ShopHomeStructuredPoliciesSectionViewModel;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.util.aj;
import com.etsy.android.lib.util.bf;
import com.etsy.android.lib.util.bl;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.BaseRecyclerViewListFragment;
import com.etsy.android.uikit.util.MachineTranslationViewState;
import com.etsy.android.uikit.util.ShopHomeSpacingUtil.ItemDecoration;
import com.etsy.android.uikit.util.shop.ShopHomeRouter;
import com.etsy.android.uikit.util.shop.ShopHomeScrollListener;
import com.etsy.android.uikit.util.shop.ShopHomeStateManager;
import com.etsy.android.uikit.util.shop.ShopHomeTabsUtil;
import com.etsy.android.uikit.viewholder.shop.ShopHomeItemDividerDecoration;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcels;

public abstract class BaseShopHomeFragment extends BaseRecyclerViewListFragment<Pair<Integer, ?>> implements ShopHomeRouter, ShopHomeStateManager {
    private static final int DEFAULT_LISTINGS_LIMIT = 24;
    private static final String DID_INITIAL_PAGE_LOAD = "did_initial_page_load";
    private static final int PAGE_DATA_REQUEST_ID = 1;
    private static final String RECYCLERVIEW_LAYOUT = "recyclerview_layout";
    public static final String SEARCH_PARAM_SEARCH_QUERY = "search_query";
    public static final String SEARCH_PARAM_SECTION_ID = "section_id";
    private static final String SEARCH_PARAM_SHOP = "shop";
    public static final String SEARCH_PARAM_SORT_ORDER = "sort_order";
    private static final String SEARCH_PARAM_USER = "user";
    private static final String SHOP_DATA = "shop_data";
    private static final String SHOP_STATE = "shop_state";
    protected boolean mDidInitialPageLoad;
    @Nullable
    protected ShopHomeInitialLoadConfiguration mInitialLoadConfig;
    private OnTabSelectedListener mOnTabSelectedListener;
    ShopHomeScrollListener mScrollListener;
    @Nullable
    protected EtsyId mShopId;
    protected String mShopName;
    protected TabLayout mTabLayout;

    /* renamed from: com.etsy.android.uikit.ui.shop.BaseShopHomeFragment.1 */
    class C09861 implements OnTabSelectedListener {
        final /* synthetic */ BaseShopHomeFragment f4094a;

        C09861(BaseShopHomeFragment baseShopHomeFragment) {
            this.f4094a = baseShopHomeFragment;
        }

        public void onTabSelected(Tab tab) {
            this.f4094a.navigateToTabSection(tab, true);
            this.f4094a.mScrollListener.disableTabChangeObservingUntilScrollCompletion();
            this.f4094a.didSelectTab(ShopHomeTabsUtil.m5620c(tab));
        }

        public void onTabUnselected(Tab tab) {
        }

        public void onTabReselected(Tab tab) {
            this.f4094a.navigateToTabSection(tab, true);
            this.f4094a.didSelectTab(ShopHomeTabsUtil.m5620c(tab));
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.shop.BaseShopHomeFragment.2 */
    class C09872 extends DefaultItemAnimator {
        final /* synthetic */ BaseShopHomeFragment f4095a;

        C09872(BaseShopHomeFragment baseShopHomeFragment) {
            this.f4095a = baseShopHomeFragment;
        }

        public boolean canReuseUpdatedViewHolder(@NonNull ViewHolder viewHolder) {
            return viewHolder.getItemViewType() != 7;
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.shop.BaseShopHomeFragment.3 */
    class C09883 extends NetworkLoader<ShopHomePage> {
        final /* synthetic */ WeakReference f4096a;
        final /* synthetic */ BaseShopHomeFragment f4097b;

        C09883(BaseShopHomeFragment baseShopHomeFragment, WeakReference weakReference) {
            this.f4097b = baseShopHomeFragment;
            this.f4096a = weakReference;
        }

        public void m5456a(@NonNull List<ShopHomePage> list, int i, @NonNull EtsyV3Result<ShopHomePage> etsyV3Result) {
            Activity activity = this.f4097b.getActivity();
            ShopHomeAdapter shopHomeAdapter = (ShopHomeAdapter) this.f4097b.mAdapter;
            BaseShopHomeFragment baseShopHomeFragment = (BaseShopHomeFragment) this.f4096a.get();
            if (list.size() > 0 && shopHomeAdapter != null && activity != null && baseShopHomeFragment != null) {
                ShopHomePage shopHomePage = (ShopHomePage) list.get(0);
                ShopV3 shop = shopHomePage.getShop();
                this.f4097b.mShopId = shop.getShopId();
                this.f4097b.mShopName = shop.getShopName();
                this.f4097b.handlePageData(shopHomePage, ShopHomeStateManager.m5574a(shopHomePage.getShopSections(), shop, baseShopHomeFragment, baseShopHomeFragment.getResources()), null);
                activity.invalidateOptionsMenu();
                this.f4097b.showListView();
                this.f4097b.stopLoad();
                this.f4097b.mDidInitialPageLoad = true;
                this.f4097b.onPageLoaded(shopHomePage);
            }
        }

        public void m5455a(int i, @Nullable String str, @NonNull EtsyV3Result<ShopHomePage> etsyV3Result) {
            this.f4097b.stopLoad();
            this.f4097b.showErrorView();
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.shop.BaseShopHomeFragment.4 */
    class C09904 extends EtsyApiV3RequestJob<ShopListingsSearchResult> {
        final /* synthetic */ WeakReference f4100a;
        final /* synthetic */ boolean f4101b;
        final /* synthetic */ BaseShopHomeFragment f4102c;

        /* renamed from: com.etsy.android.uikit.ui.shop.BaseShopHomeFragment.4.1 */
        class C09891 implements Runnable {
            final /* synthetic */ List f4098a;
            final /* synthetic */ C09904 f4099b;

            C09891(C09904 c09904, List list) {
                this.f4099b = c09904;
                this.f4098a = list;
            }

            public void run() {
                this.f4099b.f4102c.onNewListingsResponse((ShopListingsSearchResult) this.f4098a.get(0));
            }
        }

        C09904(BaseShopHomeFragment baseShopHomeFragment, WeakReference weakReference, boolean z) {
            this.f4102c = baseShopHomeFragment;
            this.f4100a = weakReference;
            this.f4101b = z;
        }

        public void m5459a(@NonNull List<ShopListingsSearchResult> list, int i, @NonNull EtsyV3Result<ShopListingsSearchResult> etsyV3Result) {
            boolean z = false;
            BaseShopHomeFragment baseShopHomeFragment = (BaseShopHomeFragment) this.f4100a.get();
            if (baseShopHomeFragment != null && baseShopHomeFragment.getActivity() != null) {
                ShopHomeAdapter shopHomeAdapter = (ShopHomeAdapter) baseShopHomeFragment.mAdapter;
                if (shopHomeAdapter != null) {
                    TabLayout tabLayout = baseShopHomeFragment.mTabLayout;
                    if (tabLayout != null && list.size() > 0) {
                        ShopListingsSearchResult shopListingsSearchResult = (ShopListingsSearchResult) list.get(0);
                        if (!this.f4101b) {
                            z = true;
                        }
                        shopHomeAdapter.configureForNewListingResults(shopListingsSearchResult, tabLayout, z, etsyV3Result.m1055f());
                        this.f4102c.stopLoad();
                        this.f4102c.mRecyclerView.postDelayed(new C09891(this, list), 400);
                    }
                }
            }
        }

        public void m5457a(int i, @Nullable String str, @NonNull EtsyV3Result<ShopListingsSearchResult> etsyV3Result) {
            if (((BaseShopHomeFragment) this.f4100a.get()) != null) {
                this.f4102c.stopLoad();
                if (this.f4101b) {
                    bl.m3367b(this.f4102c.getString(R.error_loading_more_listings), this.f4102c.mRecyclerView);
                    ((ShopHomeAdapter) this.f4102c.mAdapter).loadMoreListingsDidFail();
                }
            }
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.shop.BaseShopHomeFragment.5 */
    class C09915 extends EtsyApiV3RequestJob<TranslatedReview> {
        final /* synthetic */ ShopHomeReviewViewModel f4103a;
        final /* synthetic */ BaseShopHomeFragment f4104b;

        C09915(BaseShopHomeFragment baseShopHomeFragment, ShopHomeReviewViewModel shopHomeReviewViewModel) {
            this.f4104b = baseShopHomeFragment;
            this.f4103a = shopHomeReviewViewModel;
        }

        public void m5463a(@NonNull List<TranslatedReview> list, int i, @NonNull EtsyV3Result<TranslatedReview> etsyV3Result) {
            if (list.size() > 0 && this.f4104b.getActivity() != null && this.f4104b.mAdapter != null) {
                this.f4103a.setTranslatedReviewMessage(((TranslatedReview) list.get(0)).getTranslatedReview());
                ((ShopHomeAdapter) this.f4104b.mAdapter).notifyShopHomeItemChanged(BaseShopHomeFragment.DEFAULT_LISTINGS_LIMIT, this.f4103a);
            }
        }

        public void m5461a(int i, @Nullable String str, @NonNull EtsyV3Result<TranslatedReview> etsyV3Result) {
            this.f4103a.getTranslationState().setErrorLoadingTranslation();
            ((ShopHomeAdapter) this.f4104b.mAdapter).notifyShopHomeItemChanged(BaseShopHomeFragment.DEFAULT_LISTINGS_LIMIT, this.f4103a);
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.shop.BaseShopHomeFragment.6 */
    class C09926 extends EtsyApiV3RequestJob<TranslatedPrivacyPolicy> {
        final /* synthetic */ ShopHomeStructuredPoliciesSectionViewModel f4105a;
        final /* synthetic */ BaseShopHomeFragment f4106b;

        C09926(BaseShopHomeFragment baseShopHomeFragment, ShopHomeStructuredPoliciesSectionViewModel shopHomeStructuredPoliciesSectionViewModel) {
            this.f4106b = baseShopHomeFragment;
            this.f4105a = shopHomeStructuredPoliciesSectionViewModel;
        }

        public void m5467a(@NonNull List<TranslatedPrivacyPolicy> list, int i, @NonNull EtsyV3Result<TranslatedPrivacyPolicy> etsyV3Result) {
            if (list.size() > 0 && this.f4106b.getActivity() != null && this.f4106b.mAdapter != null) {
                this.f4105a.setTranslatedOtherPolicyText(((TranslatedPrivacyPolicy) list.get(0)).getTranslatedOtherPolicy());
                ((ShopHomeAdapter) this.f4106b.mAdapter).notifyShopHomeItemChanged(32, this.f4105a);
            }
        }

        public void m5465a(int i, @Nullable String str, @NonNull EtsyV3Result<TranslatedPrivacyPolicy> etsyV3Result) {
            this.f4105a.getOtherTranslationState().setErrorLoadingTranslation();
            ((ShopHomeAdapter) this.f4106b.mAdapter).notifyShopHomeItemChanged(32, this.f4105a);
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.shop.BaseShopHomeFragment.7 */
    class C09937 extends EtsyApiV3RequestJob<FAQ> {
        final /* synthetic */ MachineTranslationViewState f4107a;
        final /* synthetic */ FAQTranslationData f4108b;
        final /* synthetic */ FAQs f4109c;
        final /* synthetic */ BaseShopHomeFragment f4110d;

        C09937(BaseShopHomeFragment baseShopHomeFragment, MachineTranslationViewState machineTranslationViewState, FAQTranslationData fAQTranslationData, FAQs fAQs) {
            this.f4110d = baseShopHomeFragment;
            this.f4107a = machineTranslationViewState;
            this.f4108b = fAQTranslationData;
            this.f4109c = fAQs;
        }

        public void m5471a(@NonNull List<FAQ> list, int i, @NonNull EtsyV3Result<FAQ> etsyV3Result) {
            if (list.size() > 0 && this.f4110d.getActivity() != null && this.f4110d.mAdapter != null) {
                this.f4107a.setSuccessLoadingTranslation();
                ((ShopHomeAdapter) this.f4110d.mAdapter).notifyShopHomeItemChanged(39, this.f4108b);
                this.f4109c.updateTranslatedFAQs(list);
                Iterator it = this.f4109c.iterator();
                while (it.hasNext()) {
                    ((ShopHomeAdapter) this.f4110d.mAdapter).notifyShopHomeItemChanged(34, (FAQ) it.next());
                }
            }
        }

        public void m5469a(int i, @Nullable String str, @NonNull EtsyV3Result<FAQ> etsyV3Result) {
            this.f4108b.getTranslationState().setErrorLoadingTranslation();
            ((ShopHomeAdapter) this.f4110d.mAdapter).notifyShopHomeItemChanged(32, this.f4108b);
        }
    }

    protected abstract void didChangeTabSelectionOnScroll(@Nullable String str);

    protected abstract void didSelectTab(@Nullable String str);

    protected abstract void onNewListingsResponse(@NonNull ShopListingsSearchResult shopListingsSearchResult);

    protected abstract void onPageLoaded(@NonNull ShopHomePage shopHomePage);

    public BaseShopHomeFragment() {
        this.mShopName = StringUtils.EMPTY;
        this.mOnTabSelectedListener = new C09861(this);
        this.mScrollListener = new ShopHomeScrollListener(this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        Bundle bundle2 = bundle != null ? bundle : arguments;
        this.mShopId = (EtsyId) Parcels.m7495a(bundle2.getParcelable(ResponseConstants.SHOP_ID));
        this.mShopName = bundle2.getString(ResponseConstants.SHOP_NAME, StringUtils.EMPTY);
        this.mAdapter = new ShopHomeAdapter(getActivity(), getImageBatch(), getAnalyticsContext(), this);
        boolean z = bundle != null && bundle.getBoolean(DID_INITIAL_PAGE_LOAD);
        this.mDidInitialPageLoad = z;
        if (!this.mDidInitialPageLoad && arguments.containsKey("shop_home_load_configuration")) {
            this.mInitialLoadConfig = (ShopHomeInitialLoadConfiguration) Parcels.m7495a(arguments.getParcelable("shop_home_load_configuration"));
        }
        setHasOptionsMenu(true);
    }

    @NonNull
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mLayoutManager = ((ShopHomeAdapter) this.mAdapter).getLayoutManager();
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        SwipeRefreshLayout swipeRefreshLayout = this.mSwipeRefreshLayout;
        int[] iArr = new int[PAGE_DATA_REQUEST_ID];
        iArr[0] = R.brand_orange;
        swipeRefreshLayout.setColorSchemeResources(iArr);
        RecyclerView recyclerView = this.mRecyclerView;
        recyclerView.setHasFixedSize(true);
        int dimensionPixelOffset = onCreateView.getResources().getDimensionPixelOffset(R.shop2_home_side_margin);
        recyclerView.setPadding(dimensionPixelOffset, 0, dimensionPixelOffset, 0);
        recyclerView.setItemAnimator(new C09872(this));
        recyclerView.addItemDecoration(new ShopHomeItemDividerDecoration(onCreateView.getContext()));
        recyclerView.addItemDecoration(new ItemDecoration(onCreateView.getResources()));
        recyclerView.setScrollBarStyle(50331648);
        return onCreateView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        TabLayout addTabLayout = ((BaseActivity) getActivity()).getAppBarHelper().addTabLayout();
        addTabLayout.setTabGravity(PAGE_DATA_REQUEST_ID);
        this.mTabLayout = addTabLayout;
        if (bundle == null || !bundle.containsKey(SHOP_DATA)) {
            loadContent();
            return;
        }
        ShopHomeStateManager shopHomeStateManager;
        ShopHomePage shopHomePage = (ShopHomePage) Parcels.m7495a(bundle.getParcelable(SHOP_DATA));
        if (bundle.containsKey(SHOP_STATE)) {
            shopHomeStateManager = (ShopHomeStateManager) Parcels.m7495a(bundle.getParcelable(SHOP_STATE));
            shopHomeStateManager.m5586a((ShopHomeStateManager) this);
        } else {
            shopHomeStateManager = ShopHomeStateManager.m5574a(shopHomePage.getShopSections(), shopHomePage.getShop(), this, getResources());
        }
        handlePageData(shopHomePage, shopHomeStateManager, bundle);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        ShopHomeAdapter shopHomeAdapter = (ShopHomeAdapter) this.mAdapter;
        Object pageData = shopHomeAdapter.getPageData();
        if (pageData != null) {
            bundle.putParcelable(SHOP_DATA, Parcels.m7494a(pageData));
        }
        Object stateManager = shopHomeAdapter.getStateManager();
        if (stateManager != null) {
            bundle.putParcelable(SHOP_STATE, Parcels.m7494a(stateManager));
        }
        bundle.putParcelable(RECYCLERVIEW_LAYOUT, this.mRecyclerView.getLayoutManager().onSaveInstanceState());
        bundle.putParcelable(ResponseConstants.SHOP_ID, Parcels.m7494a(this.mShopId));
        bundle.putString(ResponseConstants.SHOP_NAME, this.mShopName);
        bundle.putBoolean(DID_INITIAL_PAGE_LOAD, this.mDidInitialPageLoad);
    }

    public void onLoadContent() {
        fetchPageData();
    }

    private void fetchPageData() {
        EtsyApiV3Request a = EtsyApiV3Request.m1454a(ShopHomePage.class, this.mShopId != null ? EtsyV3Urls.m1506a(this.mShopId) : EtsyV3Urls.m1508a(this.mShopName));
        if (((ShopHomeAdapter) this.mAdapter).getPageData() == null) {
            bf.m3324a(a, this.mInitialLoadConfig);
        }
        loadDataFromNetwork(PAGE_DATA_REQUEST_ID, a.m1393d(), new C09883(this, new WeakReference(this)));
    }

    protected void handlePageData(@NonNull ShopHomePage shopHomePage, @NonNull ShopHomeStateManager shopHomeStateManager, @Nullable Bundle bundle) {
        ShopHomeAdapter shopHomeAdapter = (ShopHomeAdapter) this.mAdapter;
        ShopHomeInitialLoadConfiguration shopHomeInitialLoadConfiguration = this.mInitialLoadConfig;
        boolean z = (this.mDidInitialPageLoad || shopHomeInitialLoadConfiguration == null) ? false : true;
        if (z) {
            shopHomeStateManager.m5585a(shopHomeInitialLoadConfiguration);
        }
        shopHomeAdapter.configureData(this.mTabLayout, shopHomePage, shopHomeStateManager);
        if (z) {
            navigateToTabSection(bf.m3321a(shopHomeInitialLoadConfiguration), false);
        } else if (bundle != null) {
            this.mRecyclerView.getLayoutManager().onRestoreInstanceState(bundle.getParcelable(RECYCLERVIEW_LAYOUT));
            changeTabSelectionIfNecessary();
        }
        this.mRecyclerView.addOnScrollListener(this.mScrollListener);
        this.mTabLayout.setOnTabSelectedListener(this.mOnTabSelectedListener);
    }

    public void performSearch(@NonNull ShopHomeStateManager shopHomeStateManager) {
        searchListings(shopHomeStateManager, false);
    }

    public void loadMoreListings(@NonNull ShopHomeStateManager shopHomeStateManager) {
        ((ShopHomeAdapter) this.mAdapter).startedLoadingMoreListings();
        searchListings(shopHomeStateManager, true);
    }

    private void searchListings(@NonNull ShopHomeStateManager shopHomeStateManager, boolean z) {
        getRequestQueue().m1696a((Object) this, ((EtsyApiV3RequestJob) EtsyApiV3RequestJob.m1464a(freshSearchRequest(shopHomeStateManager, this.mShopId, z)).m1423a(new C09904(this, new WeakReference(this), z), (Fragment) this)).m1426c());
    }

    protected void stopLoad() {
        this.mSwipeRefreshLayout.setRefreshing(false);
        setLoading(false);
        setRefreshing(false);
    }

    private static EtsyApiV3Request<ShopListingsSearchResult> freshSearchRequest(@NonNull ShopHomeStateManager shopHomeStateManager, @NonNull EtsyId etsyId, boolean z) {
        int i;
        EtsyApiV3Request a = EtsyApiV3Request.m1454a(ShopListingsSearchResult.class, EtsyV3Urls.m1509b(etsyId));
        a.m1433c(DEFAULT_LISTINGS_LIMIT);
        if (z) {
            i = shopHomeStateManager.m5605i();
        } else {
            i = 0;
        }
        a.m1431b(i);
        String trim = shopHomeStateManager.m5592b().trim();
        ShopSection e = shopHomeStateManager.m5601e();
        ShopHomeSortOption f = shopHomeStateManager.m5602f();
        if (trim.length() > 0) {
            a.m1385a(SEARCH_PARAM_SEARCH_QUERY, trim);
        } else if (!e.isAllItemsSection()) {
            a.m1385a(SEARCH_PARAM_SECTION_ID, e.getShopSectionId().toString());
        }
        a.m1385a(SEARCH_PARAM_SORT_ORDER, f.getOptionId());
        return (EtsyApiV3Request) a.m1393d();
    }

    public void translateReviewMessage(@NonNull ShopHomeReviewViewModel shopHomeReviewViewModel) {
        String c = aj.m3235c();
        EtsyApiV3Request a = EtsyApiV3Request.m1454a(TranslatedReview.class, EtsyV3Urls.m1507a(shopHomeReviewViewModel.getReview().getTransactionId(), this.mShopId));
        a.m1385a(EtsyRequest.PARAM_LANGUAGE, c);
        getRequestQueue().m1696a((Object) this, ((EtsyApiV3RequestJob) EtsyApiV3RequestJob.m1464a((EtsyApiV3Request) a.m1393d()).m1423a(new C09915(this, shopHomeReviewViewModel), (Fragment) this)).m1426c());
    }

    public void translatePrivacyOther(@NonNull ShopHomeStructuredPoliciesSectionViewModel shopHomeStructuredPoliciesSectionViewModel) {
        String c = aj.m3235c();
        EtsyApiV3Request a = EtsyApiV3Request.m1454a(TranslatedPrivacyPolicy.class, EtsyV3Urls.m1511c(this.mShopId));
        a.m1385a(EtsyRequest.PARAM_LANGUAGE, c);
        getRequestQueue().m1696a((Object) this, ((EtsyApiV3RequestJob) EtsyApiV3RequestJob.m1464a((EtsyApiV3Request) a.m1393d()).m1423a(new C09926(this, shopHomeStructuredPoliciesSectionViewModel), (Fragment) this)).m1426c());
    }

    public void translateFAQs(@NonNull FAQs fAQs, FAQTranslationData fAQTranslationData) {
        MachineTranslationViewState translationState = fAQTranslationData.getTranslationState();
        if (translationState.hasLoadedTranslation()) {
            translationState.toggleShowingOriginal();
            Iterator it = fAQs.iterator();
            while (it.hasNext()) {
                FAQ faq = (FAQ) it.next();
                faq.setShowTranslatedFAQ(!translationState.isShowingOriginal());
                ((ShopHomeAdapter) this.mAdapter).notifyShopHomeItemChanged(34, faq);
            }
            ((ShopHomeAdapter) this.mAdapter).notifyShopHomeItemChanged(39, fAQTranslationData);
            return;
        }
        String c = aj.m3235c();
        EtsyApiV3Request a = EtsyApiV3Request.m1454a(FAQ.class, EtsyV3Urls.m1513d(this.mShopId));
        a.m1385a(EtsyRequest.PARAM_LANGUAGE, c);
        getRequestQueue().m1696a((Object) this, ((EtsyApiV3RequestJob) EtsyApiV3RequestJob.m1464a((EtsyApiV3Request) a.m1393d()).m1423a(new C09937(this, translationState, fAQTranslationData, fAQs), (Fragment) this)).m1426c());
    }

    public void refreshFilterSpinners() {
        ShopHomeAdapter shopHomeAdapter = (ShopHomeAdapter) this.mAdapter;
        if (shopHomeAdapter != null) {
            shopHomeAdapter.refreshFilterSpinners();
        }
    }

    public void refreshSearchBox() {
        ShopHomeAdapter shopHomeAdapter = (ShopHomeAdapter) this.mAdapter;
        if (shopHomeAdapter != null) {
            shopHomeAdapter.refreshSearchBox();
        }
    }

    public void didClearSearch() {
        ShopHomeAdapter shopHomeAdapter = (ShopHomeAdapter) this.mAdapter;
        if (shopHomeAdapter != null) {
            shopHomeAdapter.refreshSearchBox();
            shopHomeAdapter.hideSearchDescription();
        }
    }

    public void navigateToTabSection(String str, boolean z) {
        Tab a = ShopHomeTabsUtil.m5616a(this.mTabLayout, str);
        if (a != null) {
            navigateToTabSection(a, z);
        }
    }

    public void navigateToTabSection(@NonNull Tab tab, boolean z) {
        int a = ShopHomeTabsUtil.m5615a(tab);
        if (a >= 0) {
            RecyclerView recyclerView = this.mRecyclerView;
            OnScrollListener onScrollListener = this.mScrollListener;
            recyclerView.removeOnScrollListener(onScrollListener);
            if (z) {
                recyclerView.smoothScrollToPosition(a);
            } else {
                recyclerView.scrollToPosition(a);
            }
            recyclerView.addOnScrollListener(onScrollListener);
        }
    }

    public void changeTabSelectionIfNecessary() {
        int findFirstVisibleItemPosition = ((GridLayoutManager) this.mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        TabLayout tabLayout = this.mTabLayout;
        tabLayout.setOnTabSelectedListener(null);
        int tabCount = tabLayout.getTabCount();
        for (int i = 0; i < tabCount; i += PAGE_DATA_REQUEST_ID) {
            Tab tabAt = tabLayout.getTabAt(i);
            int a = ShopHomeTabsUtil.m5615a(tabAt);
            int b = ShopHomeTabsUtil.m5619b(tabAt);
            if (a <= findFirstVisibleItemPosition && ((b < 0 || findFirstVisibleItemPosition <= b) && tabAt != null && !tabAt.isSelected())) {
                tabAt.select();
                didChangeTabSelectionOnScroll(ShopHomeTabsUtil.m5620c(tabAt));
                break;
            }
        }
        tabLayout.setOnTabSelectedListener(this.mOnTabSelectedListener);
    }
}

package com.etsy.android.ui.shop2;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.http.body.BaseHttpBody;
import com.etsy.android.lib.core.http.body.FormBody;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.EtsyApiV3RequestJob;
import com.etsy.android.lib.core.http.url.p009a.EtsyV3Urls;
import com.etsy.android.lib.core.p005a.EtsyV3Result;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.ITrackingView;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ShopAboutVideo;
import com.etsy.android.lib.models.ShopSection;
import com.etsy.android.lib.models.apiv3.ListingMemberData;
import com.etsy.android.lib.models.apiv3.ShopHomeMemberData;
import com.etsy.android.lib.models.apiv3.ShopHomePage;
import com.etsy.android.lib.models.apiv3.ShopListingsSearchResult;
import com.etsy.android.lib.models.apiv3.ShopV3;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.ShopHomeSortOption;
import com.etsy.android.lib.models.interfaces.ShopShareable;
import com.etsy.android.lib.util.bf;
import com.etsy.android.ui.cardview.p014a.ListingCardClickHandler;
import com.etsy.android.ui.nav.EtsyActivityNavigator;
import com.etsy.android.ui.nav.EtsyEventTracker;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.ui.util.FavoriteUtil;
import com.etsy.android.uikit.nav.ActivityNavigator.AnimationMode;
import com.etsy.android.uikit.ui.shop.BaseShopHomeFragment;
import com.etsy.android.uikit.ui.shop.ShopHomeAdapter;
import com.etsy.android.uikit.util.SocialShareUtil;
import com.etsy.android.uikit.util.SocialShareUtil.ShareType;
import com.etsy.android.uikit.util.shop.ShopHomeStateManager;
import com.etsy.android.uikit.viewholder.a.a;
import com.foresee.sdk.configuration.Configuration;
import java.lang.ref.WeakReference;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class ShopHomeFragment extends BaseShopHomeFragment {
    private static final String TAG;
    boolean mIsSignedIn;
    private final BroadcastReceiver mListingStateReceiver;
    private FavoriteUtil mShopFavoriteUtil;
    @Nullable
    private EtsyAction mSignInAction;

    /* renamed from: com.etsy.android.ui.shop2.ShopHomeFragment.1 */
    class C08111 extends BroadcastReceiver {
        final /* synthetic */ ShopHomeFragment f3432a;

        C08111(ShopHomeFragment shopHomeFragment) {
            this.f3432a = shopHomeFragment;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(EtsyAction.STATE_CHANGE.getAction())) {
                this.f3432a.listingStateChanged(intent.getExtras());
            }
        }
    }

    /* renamed from: com.etsy.android.ui.shop2.ShopHomeFragment.2 */
    class C08122 extends EtsyApiV3RequestJob<ShopHomeMemberData> {
        final /* synthetic */ WeakReference f3433a;
        final /* synthetic */ ShopHomeFragment f3434b;

        C08122(ShopHomeFragment shopHomeFragment, WeakReference weakReference) {
            this.f3434b = shopHomeFragment;
            this.f3433a = weakReference;
        }

        public void m4987a(@NonNull List<ShopHomeMemberData> list, int i, @NonNull EtsyV3Result<ShopHomeMemberData> etsyV3Result) {
            ShopHomeFragment shopHomeFragment = (ShopHomeFragment) this.f3433a.get();
            if (shopHomeFragment != null && shopHomeFragment.getActivity() != null && !list.isEmpty() && shopHomeFragment.mAdapter != null) {
                this.f3434b.handleMemberDataResponse((ShopHomeMemberData) list.get(0));
            }
        }

        public void m4985a(int i, @Nullable String str, @NonNull EtsyV3Result<ShopHomeMemberData> etsyV3Result) {
        }
    }

    /* renamed from: com.etsy.android.ui.shop2.ShopHomeFragment.3 */
    class C08133 extends EtsyApiV3RequestJob<ListingMemberData> {
        final /* synthetic */ WeakReference f3435a;
        final /* synthetic */ ShopHomeFragment f3436b;

        C08133(ShopHomeFragment shopHomeFragment, WeakReference weakReference) {
            this.f3436b = shopHomeFragment;
            this.f3435a = weakReference;
        }

        public void m4991a(@NonNull List<ListingMemberData> list, int i, @NonNull EtsyV3Result<ListingMemberData> etsyV3Result) {
            ShopHomeFragment shopHomeFragment = (ShopHomeFragment) this.f3435a.get();
            if (shopHomeFragment != null && shopHomeFragment.getActivity() != null && !list.isEmpty() && shopHomeFragment.mAdapter != null) {
                ((ShopHomeAdapter) this.f3436b.mAdapter).applyListingsMemberInfo(list);
            }
        }

        public void m4989a(int i, @Nullable String str, @NonNull EtsyV3Result<ListingMemberData> etsyV3Result) {
        }
    }

    /* renamed from: com.etsy.android.ui.shop2.ShopHomeFragment.4 */
    class C08144 extends EtsyApiV3RequestJob<EmptyResult> {
        final /* synthetic */ boolean f3437a;
        final /* synthetic */ ShopHomeFragment f3438b;

        C08144(ShopHomeFragment shopHomeFragment, boolean z) {
            this.f3438b = shopHomeFragment;
            this.f3437a = z;
        }

        public void m4995a(@NonNull List<EmptyResult> list, int i, @NonNull EtsyV3Result<EmptyResult> etsyV3Result) {
            if (this.f3438b.mAdapter != null && this.f3438b.getActivity() != null) {
                ((ShopHomeAdapter) this.f3438b.mAdapter).handleVacationSubscriptionStatusChange(this.f3437a);
            }
        }

        public void m4993a(int i, @Nullable String str, @NonNull EtsyV3Result<EmptyResult> etsyV3Result) {
        }
    }

    public ShopHomeFragment() {
        this.mListingStateReceiver = new C08111(this);
    }

    static {
        TAG = EtsyDebug.m1891a(ShopHomeFragment.class);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mShopFavoriteUtil = new FavoriteUtil(getActivity(), null, "shop_home", getAnalyticsContext());
        this.mIsSignedIn = aj.m1101a().m1118d();
    }

    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.mListingStateReceiver, new IntentFilter(EtsyAction.STATE_CHANGE.getAction()));
    }

    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.mListingStateReceiver);
        boolean z = this.mIsSignedIn;
        boolean d = aj.m1101a().m1118d();
        this.mIsSignedIn = d;
        if (z != d) {
            ShopHomeAdapter shopHomeAdapter = (ShopHomeAdapter) this.mAdapter;
            ShopHomePage pageData = shopHomeAdapter.getPageData();
            if (pageData != null && d) {
                getMemberData(pageData);
            } else if (pageData == null) {
                loadContent();
            }
            ShopHomeStateManager stateManager = shopHomeAdapter.getStateManager();
            if (stateManager != null && pageData != null) {
                if (d && pageData.getShop().getUserId().equals(aj.m1101a().m1125k())) {
                    z = true;
                } else {
                    z = false;
                }
                stateManager.m5598c(z);
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.mListingStateReceiver);
    }

    void listingStateChanged(@NonNull Bundle bundle) {
        String string = bundle.getString(ResponseConstants.ID);
        if (!TextUtils.isEmpty(string)) {
            ((ShopHomeAdapter) this.mAdapter).listingStateChanged(new EtsyId(string), bundle);
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        MenuItem findItem = menu.findItem(R.menu_share);
        ShopHomeAdapter shopHomeAdapter = (ShopHomeAdapter) this.mAdapter;
        if (shopHomeAdapter == null || shopHomeAdapter.getPageData() == null) {
            findItem.setVisible(false);
        } else {
            findItem.setVisible(true);
        }
    }

    protected void handlePageData(@NonNull ShopHomePage shopHomePage, @NonNull ShopHomeStateManager shopHomeStateManager, @Nullable Bundle bundle) {
        super.handlePageData(shopHomePage, shopHomeStateManager, bundle);
        if (!shopHomeStateManager.m5612p() && !shopHomeStateManager.m5611o() && this.mIsSignedIn) {
            getMemberData(shopHomePage);
        }
    }

    protected void onPageLoaded(@NonNull ShopHomePage shopHomePage) {
        EtsyEventTracker.m4551a(getAnalyticsContext(), shopHomePage);
    }

    private void getMemberData(@NonNull ShopHomePage shopHomePage) {
        ShopV3 shop = shopHomePage.getShop();
        getRequestQueue().m1696a((Object) this, ((EtsyApiV3RequestJob) EtsyApiV3RequestJob.m1464a((EtsyApiV3Request) ((EtsyApiV3Request) ((EtsyApiV3Request) ((EtsyApiV3Request) EtsyApiV3Request.m1454a(ShopHomeMemberData.class, EtsyV3Urls.m1515e(shop.getShopId())).m1382a(1)).m1432b(true)).m1383a((BaseHttpBody) (FormBody) ((FormBody) ((FormBody) new FormBody().m1342b("listing_ids[]", bf.m3323a(shopHomePage.getFeaturedListings(), shopHomePage.getShopListings()))).m1341b("vacation_subscription_status_required", Boolean.toString(shop.isVacation()))).m1345f())).m1393d()).m1423a(new C08122(this, new WeakReference(this)), (Fragment) this)).m1426c());
    }

    protected void handleMemberDataResponse(@NonNull ShopHomeMemberData shopHomeMemberData) {
        ((ShopHomeAdapter) this.mAdapter).configureForMemberData(shopHomeMemberData);
    }

    protected void onNewListingsResponse(@NonNull ShopListingsSearchResult shopListingsSearchResult) {
        if (!shopListingsSearchResult.getListings().isEmpty()) {
            getRequestQueue().m1696a((Object) this, ((EtsyApiV3RequestJob) EtsyApiV3RequestJob.m1464a((EtsyApiV3Request) ((EtsyApiV3Request) ((EtsyApiV3Request) ((EtsyApiV3Request) EtsyApiV3Request.m1454a(ListingMemberData.class, "/member/users/listings-favorites-collections").m1382a(1)).m1432b(true)).m1383a((BaseHttpBody) (FormBody) ((FormBody) new FormBody().m1342b("listing_ids[]", bf.m3323a(shopListingsSearchResult.getListings()))).m1345f())).m1393d()).m1423a(new C08133(this, new WeakReference(this)), (Fragment) this)).m1426c());
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.menu_share) {
            return super.onOptionsItemSelected(menuItem);
        }
        shareShop();
        return true;
    }

    private void shareShop() {
        ShopHomePage pageData = ((ShopHomeAdapter) this.mAdapter).getPageData();
        if (pageData != null) {
            ShopShareable shop = pageData.getShop();
            SocialShareUtil.m5156a("shop_home", ShareType.SHOP_SHARE, getActivity().getLocalClassName());
            EtsyEventTracker.m4571d(getAnalyticsContext(), shop.getShopId());
            ((EtsyActivityNavigator) Nav.m4682a(getActivity()).m4683a().m3013a((ITrackingView) this)).m4483a(shop);
        }
    }

    public void showConvoComposeModal() {
        String loginName = ((ShopHomeAdapter) this.mAdapter).getPageData().getShop().getOwner().getLoginName();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Bundle bundle = new Bundle();
            if (aj.m1101a().m1118d()) {
                bundle.putString("username", loginName);
                ((EtsyActivityNavigator) Nav.m4682a(activity).m4683a().m3013a((ITrackingView) this)).m4516d(bundle);
                return;
            }
            bundle.putString("username", loginName);
            ((EtsyActivityNavigator) Nav.m4682a(activity).m4683a().m3013a((ITrackingView) this)).m4452a(EtsyAction.CONTACT_USER, bundle);
        }
    }

    public a getListingCardClickListener() {
        return new ListingCardClickHandler("shop_home", getActivity(), (ShopHomeAdapter) this.mAdapter, getAnalyticsContext());
    }

    public com.etsy.android.uikit.viewholder.a.a.a getStructuredPoliciesViewClickListener() {
        return new com.etsy.android.uikit.viewholder.a.a.a(this);
    }

    public void navigateToLocalMarket(@NonNull LocalMarket localMarket) {
        ((EtsyActivityNavigator) Nav.m4682a(getActivity()).m4683a().m3013a((ITrackingView) this)).m4460a(localMarket, true);
    }

    public void navigateToShopAboutVideo(@NonNull ShopAboutVideo shopAboutVideo) {
        ((EtsyActivityNavigator) Nav.m4681a(getActivity()).m4446a(AnimationMode.FADE_IN_OUT).m3013a((ITrackingView) this)).m4504b(this.mShopId, shopAboutVideo.getVideoUrl());
    }

    public void navigateToUserProfile(@NonNull EtsyId etsyId) {
        ((EtsyActivityNavigator) Nav.m4681a(getActivity()).m3013a((ITrackingView) this)).m4511c(etsyId);
    }

    public void navigateToAppreciationPhotoPage(@NonNull EtsyId etsyId) {
        ((EtsyActivityNavigator) Nav.m4681a(getActivity()).m3013a((ITrackingView) this)).m4526f(etsyId);
    }

    public void navigateToReviewsPage() {
        EtsyEventTracker.m4596j(getAnalyticsContext());
        ((EtsyActivityNavigator) Nav.m4681a(getActivity()).m3013a((ITrackingView) this)).m4523e(this.mShopId, null);
    }

    public void onShopFavorerStatusChanged(boolean z) {
        ShopHomeAdapter shopHomeAdapter = (ShopHomeAdapter) this.mAdapter;
        if (shopHomeAdapter != null) {
            if (aj.m1101a().m1118d()) {
                this.mShopFavoriteUtil.m5171a(shopHomeAdapter.getPageData().getShop(), null, z);
            } else {
                Nav.m4682a(getActivity()).m4683a().m4453a(EtsyAction.FAVORITE, StringUtils.EMPTY);
            }
        }
    }

    public void didSubmitSearchQuery(@NonNull String str) {
        EtsyEventTracker.m4577e(getAnalyticsContext(), str);
    }

    public void didSelectSortOption(@NonNull ShopHomeSortOption shopHomeSortOption) {
        EtsyEventTracker.m4572d(getAnalyticsContext(), shopHomeSortOption.getOptionId());
    }

    public void didSelectSection(@NonNull ShopSection shopSection) {
        EtsyEventTracker.m4566c(getAnalyticsContext(), shopSection.getShopSectionId());
    }

    protected void didSelectTab(@Nullable String str) {
        if (str != null) {
            EtsyEventTracker.m4582f(getAnalyticsContext(), str);
        }
    }

    protected void didChangeTabSelectionOnScroll(@Nullable String str) {
        if (str != null) {
            EtsyEventTracker.m4587g(getAnalyticsContext(), str);
        }
    }

    public void didViewAnnouncement() {
        EtsyEventTracker.m4584g(getAnalyticsContext());
    }

    public void didHideAnnouncement() {
        EtsyEventTracker.m4589h(getAnalyticsContext());
    }

    public void didClickVacationSubscribeButton(boolean z) {
        if (aj.m1101a().m1118d()) {
            toggleVacationNotificationSettings(z);
            return;
        }
        ((EtsyActivityNavigator) Nav.m4681a(getActivity()).m3013a((ITrackingView) this)).m4444a(300, (Fragment) this).m4453a(EtsyAction.SUBSCRIBE_VACATION_NOTIFICATION, StringUtils.EMPTY);
        this.mSignInAction = EtsyAction.SUBSCRIBE_VACATION_NOTIFICATION;
    }

    public void navigateToTermsAndConditions(@NonNull String str) {
        Nav.m4681a(getActivity()).m4534h(str);
    }

    private void toggleVacationNotificationSettings(boolean z) {
        getRequestQueue().m1696a((Object) this, ((EtsyApiV3RequestJob) EtsyApiV3RequestJob.m1464a((EtsyApiV3Request) ((EtsyApiV3Request) ((EtsyApiV3Request) ((EtsyApiV3Request) EtsyApiV3Request.m1454a(EmptyResult.class, EtsyV3Urls.m1517f(this.mShopId)).m1382a(1)).m1432b(true)).m1383a((BaseHttpBody) (FormBody) ((FormBody) new FormBody().m1341b(Configuration.NOTIFICATION_LAYOUT_NAME, Boolean.toString(z))).m1345f())).m1393d()).m1423a(new C08144(this, z), (Fragment) this)).m1426c());
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 300 || this.mSignInAction != EtsyAction.SUBSCRIBE_VACATION_NOTIFICATION) {
            return;
        }
        if (i2 == 311) {
            toggleVacationNotificationSettings(true);
        } else {
            ((ShopHomeAdapter) this.mAdapter).handleVacationSubscriptionStatusChange(false);
        }
    }

    public void didClickRelatedLink(@NonNull String str) {
        Uri parse = Uri.parse(str);
        Context activity = getActivity();
        Intent intent = new Intent("android.intent.action.VIEW", parse);
        intent.putExtra("com.android.browser.application_id", activity.getPackageName());
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            EtsyDebug.m1916d(TAG, "Activity was not found for intent, " + intent.toString());
        }
    }

    public void didClearSearch() {
        super.didClearSearch();
        EtsyEventTracker.m4580f(getAnalyticsContext());
    }

    public void loadMoreListings(@NonNull ShopHomeStateManager shopHomeStateManager) {
        super.loadMoreListings(shopHomeStateManager);
        EtsyEventTracker.m4593i(getAnalyticsContext());
    }

    @NonNull
    public String getTrackingName() {
        return "shop_home";
    }
}

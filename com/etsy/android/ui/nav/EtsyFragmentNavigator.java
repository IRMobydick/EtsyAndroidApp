package com.etsy.android.ui.nav;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.config.InstallInfo;
import com.etsy.android.lib.convos.SendListener;
import com.etsy.android.lib.logger.EventTracker;
import com.etsy.android.lib.logger.p010a.EtsyGraphite;
import com.etsy.android.lib.models.Attendee;
import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.models.Conversation;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.PaymentMethod;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.CartsRequest;
import com.etsy.android.lib.ui.nav.TrackingNavigator;
import com.etsy.android.ui.EtsyWebFragment;
import com.etsy.android.ui.cart.CartActivity;
import com.etsy.android.ui.cart.CartFragment;
import com.etsy.android.ui.convos.ConvoComposeFragment;
import com.etsy.android.ui.convos.ConvoListFragment;
import com.etsy.android.ui.convos.ConvoThreadFragment;
import com.etsy.android.ui.core.CollectionFragment;
import com.etsy.android.ui.core.DetailedImageFragment;
import com.etsy.android.ui.core.ListingFragment;
import com.etsy.android.ui.core.ShopAboutDetailedImageFragment;
import com.etsy.android.ui.core.ShopAboutVideoFragment;
import com.etsy.android.ui.core.TreasuryFragment;
import com.etsy.android.ui.favorites.CollectionsEditFragment;
import com.etsy.android.ui.favorites.ListingCollectionsCreateFragment;
import com.etsy.android.ui.favorites.MyFavoriteLocalShopsFragment;
import com.etsy.android.ui.finds.FindsFragment;
import com.etsy.android.ui.homescreen.LandingPageFragment;
import com.etsy.android.ui.homescreen.RecentlyViewedListingsFragment;
import com.etsy.android.ui.homescreen.ShopSharePageFragment;
import com.etsy.android.ui.local.LocalBrowseMapFragment;
import com.etsy.android.ui.local.marketdetails.LocalDatesAttendingFragment;
import com.etsy.android.ui.local.marketdetails.LocalMarketV2Fragment;
import com.etsy.android.ui.local.marketdetails.LocalStoreInfoDetailsFragment;
import com.etsy.android.ui.local.marketdetails.SocialShareLocalMarketBrokerFragment;
import com.etsy.android.ui.promos.VersionPromo;
import com.etsy.android.ui.search.v2.MutableSearchOptions;
import com.etsy.android.ui.search.v2.RootTaxonomyCategoryPageFragment;
import com.etsy.android.ui.search.v2.SearchCategoryPageRedirectFragment;
import com.etsy.android.ui.search.v2.SearchResultsListingsFragment;
import com.etsy.android.ui.search.v2.SearchResultsShopsFragment;
import com.etsy.android.ui.search.v2.SearchResultsUsersFragment;
import com.etsy.android.ui.search.v2.SearchTaxonomyCategoryPageFragment;
import com.etsy.android.ui.shop.AppreciationPhotoLandingPageFragment;
import com.etsy.android.ui.shop.FeedbackFragment;
import com.etsy.android.ui.shop2.ShopHomeFragment;
import com.etsy.android.ui.user.AppreciationPhotoListFragment;
import com.etsy.android.ui.user.CurrencySelectFragment;
import com.etsy.android.ui.user.LeaveFeedbackFragment;
import com.etsy.android.ui.user.LegalInfoFragment;
import com.etsy.android.ui.user.PhabletsFragment;
import com.etsy.android.ui.user.PurchasesFragment;
import com.etsy.android.ui.user.ReceiptFragment;
import com.etsy.android.ui.user.ShareFeedbackFragment;
import com.etsy.android.ui.user.profile.UserProfileFragment;
import com.etsy.android.ui.util.CollectionUtil;
import com.etsy.android.uikit.image.CropImageFragment;
import com.etsy.android.uikit.nav.FragmentNavigator;
import com.etsy.android.uikit.nav.FragmentNavigator.AnimationMode;
import com.etsy.android.uikit.nav.FragmentNavigator.FragmentTransactionMode;
import com.etsy.android.uikit.share.SocialShareAppreciationPhotoBrokerFragment;
import com.etsy.android.uikit.share.SocialShareListingBrokerFragment;
import com.etsy.android.uikit.share.SocialShareShop2BrokerFragment;
import com.etsy.android.uikit.share.SocialShareShopBrokerFragment;
import com.etsy.android.uikit.ui.settings.NotificationSettingsFragment;
import com.etsy.android.uikit.util.SocialShareUtil;
import java.io.Serializable;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcels;

/* renamed from: com.etsy.android.ui.nav.d */
public class EtsyFragmentNavigator extends FragmentNavigator<EtsyFragmentNavigator> {
    private final NavTracker f3204n;
    private float f3205o;

    protected /* synthetic */ TrackingNavigator m4636c() {
        return m4641d();
    }

    EtsyFragmentNavigator(@NonNull FragmentActivity fragmentActivity, FragmentTransactionMode fragmentTransactionMode) {
        super(fragmentActivity, fragmentTransactionMode);
        this.k = R.nav_content_frame;
        this.l = fragmentTransactionMode == FragmentTransactionMode.REPLACE ? AnimationMode.SLIDING : AnimationMode.NONE;
        this.f3204n = new NavTracker();
        this.f3205o = (float) EtsyConfig.m837a().m869d().m887e(EtsyConfigKeys.f1234a);
        EtsyGraphite.m1805a(this.i, (double) this.f3205o);
    }

    @NonNull
    protected EtsyFragmentNavigator m4641d() {
        return this;
    }

    public EtsyFragmentNavigator m4645e() {
        this.g = false;
        return this;
    }

    public EtsyFragmentNavigator m4623a(Bundle bundle) {
        if (bundle != null) {
            this.i = bundle;
            EtsyGraphite.m1805a(this.i, (double) this.f3205o);
        }
        return this;
    }

    public EtsyFragmentNavigator m4622a(int i) {
        this.j = i;
        return this;
    }

    public EtsyFragmentNavigator m4625a(AnimationMode animationMode) {
        this.l = animationMode;
        return this;
    }

    public EtsyFragmentNavigator m4624a(FragmentManager fragmentManager) {
        this.e = fragmentManager;
        return this;
    }

    public EtsyFragmentNavigator m4626a(String str) {
        this.d = true;
        this.f = str;
        return this;
    }

    public ListingFragment m4619a(EtsyId etsyId) {
        this.i.putSerializable(ResponseConstants.LISTING_ID, etsyId);
        Fragment listingFragment = new ListingFragment();
        listingFragment.setArguments(this.i);
        m4605a(listingFragment);
        m3014b().m1847c().m885c(EtsyConfigKeys.f1224q);
        this.f3204n.m4690a(m3014b(), etsyId, this.i);
        return listingFragment;
    }

    public Fragment m4614a(VersionPromo versionPromo) {
        Fragment fragment = versionPromo.getFragment();
        this.i.putSerializable("version_promo", versionPromo);
        fragment.setArguments(this.i);
        m4605a(fragment);
        return fragment;
    }

    public Fragment m4612a(EtsyId etsyId, EtsyId etsyId2) {
        Fragment shopHomeFragment = new ShopHomeFragment();
        this.i.putParcelable(ResponseConstants.SHOP_ID, Parcels.m7494a((Object) etsyId));
        this.f3204n.m4691a(m3014b(), etsyId, etsyId2, this.i);
        shopHomeFragment.setArguments(this.i);
        m4605a(shopHomeFragment);
        return shopHomeFragment;
    }

    public Fragment m4631b(String str) {
        Fragment shopHomeFragment = new ShopHomeFragment();
        this.i.putString(ResponseConstants.SHOP_NAME, str);
        this.f3204n.m4691a(m3014b(), null, null, this.i);
        shopHomeFragment.setArguments(this.i);
        m4605a(shopHomeFragment);
        return shopHomeFragment;
    }

    public FeedbackFragment m4634b(EtsyId etsyId) {
        Fragment feedbackFragment = new FeedbackFragment();
        this.i.putSerializable(ResponseConstants.SHOP_ID, etsyId);
        feedbackFragment.setArguments(this.i);
        m4605a(feedbackFragment);
        this.f3204n.m4694a(etsyId, this.i);
        return feedbackFragment;
    }

    public FeedbackFragment m4627a(Shop shop) {
        Fragment feedbackFragment = new FeedbackFragment();
        this.i.putSerializable(ActivityFeedEntity.SHOP, shop);
        feedbackFragment.setArguments(this.i);
        m4605a(feedbackFragment);
        if (shop != null) {
            this.f3204n.m4694a(shop.getShopId(), this.i);
        }
        return feedbackFragment;
    }

    public LeaveFeedbackFragment m4649f() {
        Fragment leaveFeedbackFragment = new LeaveFeedbackFragment();
        leaveFeedbackFragment.setArguments(this.i);
        m4605a(leaveFeedbackFragment);
        return leaveFeedbackFragment;
    }

    public ShareFeedbackFragment m4651g() {
        Fragment shareFeedbackFragment = new ShareFeedbackFragment();
        shareFeedbackFragment.setArguments(this.i);
        m4605a(shareFeedbackFragment);
        return shareFeedbackFragment;
    }

    public UserProfileFragment m4638c(EtsyId etsyId) {
        this.i.putSerializable(ResponseConstants.USER_ID, etsyId);
        Fragment userProfileFragment = new UserProfileFragment();
        userProfileFragment.setArguments(this.i);
        m4605a(userProfileFragment);
        return userProfileFragment;
    }

    public MyFavoriteLocalShopsFragment m4653h() {
        Fragment myFavoriteLocalShopsFragment = new MyFavoriteLocalShopsFragment();
        myFavoriteLocalShopsFragment.setArguments(this.i);
        m4605a(myFavoriteLocalShopsFragment);
        this.f3204n.m4719r();
        return myFavoriteLocalShopsFragment;
    }

    public CartFragment m4656i() {
        Fragment cartFragment = new CartFragment();
        cartFragment.setArguments(this.i);
        m4605a(cartFragment);
        this.f3204n.m4711j();
        return cartFragment;
    }

    public CartFragment m4616a(Cart cart, EtsyId etsyId, boolean z) {
        Fragment cartFragment = new CartFragment();
        if (!(etsyId == null || !etsyId.hasId() || cart == null)) {
            this.i.putSerializable(CartFragment.CHECKED_OUT_CART, cart);
            this.i.putSerializable(CartFragment.LAST_ORDER_ID, etsyId);
            this.i.putBoolean("should_show_social_invites_prompt", z);
        }
        cartFragment.setArguments(this.i);
        m4605a(cartFragment);
        this.f3204n.m4711j();
        return cartFragment;
    }

    public EtsyWebFragment m4637c(String str) {
        Fragment etsyWebFragment = new EtsyWebFragment();
        Serializable hashMap = new HashMap();
        hashMap.put(ResponseConstants.URL, str);
        this.i.putSerializable("parameters", hashMap);
        etsyWebFragment.setArguments(this.i);
        m4605a(etsyWebFragment);
        return etsyWebFragment;
    }

    public EtsyWebFragment m4632b(EtsyId etsyId, EtsyId etsyId2) {
        Fragment etsyWebFragment = new EtsyWebFragment();
        Serializable hashMap = new HashMap();
        if (etsyId != null) {
            hashMap.put(ResponseConstants.RECEIPT_ID, etsyId.getId());
        }
        if (etsyId2 != null) {
            hashMap.put(ResponseConstants.RECEIPT_SHIPPING_ID, etsyId2.getId());
        }
        this.i.putSerializable("parameters", hashMap);
        this.i.putInt("redirect_id", 9);
        etsyWebFragment.setArguments(this.i);
        m4605a(etsyWebFragment);
        return etsyWebFragment;
    }

    public EtsyWebFragment m4615a(Cart cart, HashMap<String, String> hashMap) {
        PaymentMethod lastPaymentMethod = cart.getLastPaymentMethod();
        Serializable a = m4608a(cart, lastPaymentMethod);
        if (hashMap != null) {
            a.putAll(hashMap);
        }
        this.i.putSerializable("parameters", a);
        this.i.putSerializable(CartFragment.CHECKED_OUT_CART, cart);
        this.i.putInt("redirect_id", 1);
        this.i.putString("TRACKING_NAME", "cart_checkout");
        Fragment etsyWebFragment = new EtsyWebFragment();
        etsyWebFragment.setArguments(this.i);
        m4605a(etsyWebFragment);
        this.f3204n.m4692a(cart, lastPaymentMethod);
        return etsyWebFragment;
    }

    private HashMap<String, String> m4608a(Cart cart, PaymentMethod paymentMethod) {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put(CartActivity.INT_CART_ID, String.valueOf(cart.getCartId()));
        if (cart.hasGiftCardApplied() && paymentMethod != null && paymentMethod.isDirectCheckout()) {
            hashMap.put(CartsRequest.PARAM_APPLY_GIFTCARD, String.valueOf(1));
        }
        hashMap.put(CartFragment.PAYMENT_METHOD, paymentMethod != null ? paymentMethod.getType() : StringUtils.EMPTY);
        return hashMap;
    }

    public EtsyWebFragment m4658j() {
        Fragment etsyWebFragment = new EtsyWebFragment();
        Serializable hashMap = new HashMap();
        hashMap.put("version", InstallInfo.m919a().m929f());
        this.i.putSerializable("parameters", hashMap);
        this.i.putInt("redirect_id", 4);
        this.i.putString("TRACKING_NAME", "help");
        etsyWebFragment.setArguments(this.i);
        m4605a(etsyWebFragment);
        this.f3204n.m2085e();
        return etsyWebFragment;
    }

    public EtsyWebFragment m4659k() {
        Fragment etsyWebFragment = new EtsyWebFragment();
        Object hashMap = new HashMap();
        if (m3014b().m1847c().m885c(EtsyConfigKeys.aI)) {
            hashMap.put(ResponseConstants.URL, m3014b().m1847c().m883b(EtsyConfigKeys.aK));
        } else {
            hashMap.put(ResponseConstants.URL, m3014b().m1847c().m883b(EtsyConfigKeys.aD));
        }
        this.i.putString("TRACKING_NAME", "terms_of_use");
        this.i.putSerializable("parameters", hashMap);
        etsyWebFragment.setArguments(this.i);
        m4605a(etsyWebFragment);
        this.f3204n.m4714m();
        return etsyWebFragment;
    }

    public EtsyWebFragment m4660l() {
        Fragment etsyWebFragment = new EtsyWebFragment();
        Serializable hashMap = new HashMap();
        hashMap.put(ResponseConstants.URL, m3014b().m1847c().m883b(EtsyConfigKeys.aG));
        this.i.putString("TRACKING_NAME", "privacy_policy");
        this.i.putSerializable("parameters", hashMap);
        etsyWebFragment.setArguments(this.i);
        m4605a(etsyWebFragment);
        this.f3204n.m4715n();
        return etsyWebFragment;
    }

    public EtsyWebFragment m4661m() {
        Fragment etsyWebFragment = new EtsyWebFragment();
        Serializable hashMap = new HashMap();
        hashMap.put(ResponseConstants.URL, EtsyConfig.m837a().m869d().m883b(EtsyConfigKeys.aJ));
        this.i.putSerializable("parameters", hashMap);
        this.i.putString("TRACKING_NAME", "settings_legal");
        etsyWebFragment.setArguments(this.i);
        m4605a(etsyWebFragment);
        this.f3204n.m4713l();
        return etsyWebFragment;
    }

    public EtsyWebFragment m4640d(String str) {
        Fragment etsyWebFragment = new EtsyWebFragment();
        Serializable hashMap = new HashMap();
        hashMap.put(ResponseConstants.URL, str);
        this.i.putSerializable("parameters", hashMap);
        etsyWebFragment.setArguments(this.i);
        m4605a(etsyWebFragment);
        return etsyWebFragment;
    }

    public EtsyWebFragment m4662n() {
        Fragment etsyWebFragment = new EtsyWebFragment();
        Serializable hashMap = new HashMap();
        hashMap.put(ResponseConstants.URL, EtsyConfig.m837a().m869d().m883b(EtsyConfigKeys.aF));
        this.i.putSerializable("parameters", hashMap);
        this.i.putString("TRACKING_NAME", "electronic_communications_policy");
        etsyWebFragment.setArguments(this.i);
        m4605a(etsyWebFragment);
        this.f3204n.m4716o();
        return etsyWebFragment;
    }

    public NotificationSettingsFragment m4663o() {
        Fragment notificationSettingsFragment = new NotificationSettingsFragment();
        notificationSettingsFragment.setArguments(this.i);
        m4605a(notificationSettingsFragment);
        this.f3204n.m2084d();
        return notificationSettingsFragment;
    }

    public EtsyWebFragment m4644e(String str) {
        Fragment etsyWebFragment = new EtsyWebFragment();
        Serializable hashMap = new HashMap();
        hashMap.put(ResponseConstants.URL, str);
        this.i.putSerializable("parameters", hashMap);
        etsyWebFragment.setArguments(this.i);
        m4605a(etsyWebFragment);
        return etsyWebFragment;
    }

    public CurrencySelectFragment m4628a(CurrencySelectFragment currencySelectFragment) {
        Fragment currencySelectFragment2 = new CurrencySelectFragment();
        currencySelectFragment2.setCurrencySelectedCallback(currencySelectFragment);
        m4605a(currencySelectFragment2);
        this.f3204n.m4712k();
        return currencySelectFragment2;
    }

    public LegalInfoFragment m4664p() {
        Fragment legalInfoFragment = new LegalInfoFragment();
        m4605a(legalInfoFragment);
        this.f3204n.m4713l();
        return legalInfoFragment;
    }

    public ListingCollectionsCreateFragment m4621a(CollectionUtil collectionUtil, EtsyId etsyId, String str) {
        Fragment listingCollectionsCreateFragment = new ListingCollectionsCreateFragment();
        this.i.putSerializable(ResponseConstants.LISTING_ID, etsyId);
        this.i.putString(ResponseConstants.LISTING_IMAGE_URL, str);
        listingCollectionsCreateFragment.setArguments(this.i);
        listingCollectionsCreateFragment.setListingCollectionCreationListener(collectionUtil);
        m4605a(listingCollectionsCreateFragment);
        this.f3204n.m4722u();
        return listingCollectionsCreateFragment;
    }

    public CollectionsEditFragment m4620a(Collection collection) {
        Fragment collectionsEditFragment = new CollectionsEditFragment();
        this.i.putSerializable(Collection.TYPE_COLLECTION, collection);
        collectionsEditFragment.setArguments(this.i);
        m4605a(collectionsEditFragment);
        this.f3204n.m4721t();
        return collectionsEditFragment;
    }

    public LocalBrowseMapFragment m4665q() {
        Fragment localBrowseMapFragment = new LocalBrowseMapFragment();
        localBrowseMapFragment.setArguments(this.i);
        m4605a(localBrowseMapFragment);
        return localBrowseMapFragment;
    }

    public Fragment m4613a(EtsyId etsyId, boolean z) {
        this.i.putSerializable(ResponseConstants.LOCAL_MARKET_ID, etsyId);
        this.i.putBoolean("show_local_browse_link", z);
        Fragment localMarketV2Fragment = new LocalMarketV2Fragment();
        localMarketV2Fragment.setArguments(this.i);
        m4605a(localMarketV2Fragment);
        this.f3204n.m4693a(etsyId);
        return localMarketV2Fragment;
    }

    public Fragment m4611a(LocalMarket localMarket, boolean z) {
        Fragment localMarketV2Fragment = new LocalMarketV2Fragment();
        this.i.putParcelable(ResponseConstants.LOCAL_MARKET, Parcels.m7494a((Object) localMarket));
        this.i.putBoolean("show_local_browse_link", z);
        localMarketV2Fragment.setArguments(this.i);
        m4605a(localMarketV2Fragment);
        this.f3204n.m4693a(localMarket.getLocalMarketId());
        return localMarketV2Fragment;
    }

    public Fragment m4609a(Attendee attendee, LocalMarket localMarket) {
        this.i.putSerializable("attendee", attendee);
        this.i.putSerializable(ResponseConstants.LOCAL_MARKET, localMarket);
        Fragment localDatesAttendingFragment = new LocalDatesAttendingFragment();
        localDatesAttendingFragment.setArguments(this.i);
        m4605a(localDatesAttendingFragment);
        return localDatesAttendingFragment;
    }

    public Fragment m4610a(LocalMarket localMarket) {
        this.i.putSerializable(ResponseConstants.LOCAL_MARKET, localMarket);
        Fragment localStoreInfoDetailsFragment = new LocalStoreInfoDetailsFragment();
        localStoreInfoDetailsFragment.setArguments(this.i);
        m4605a(localStoreInfoDetailsFragment);
        return localStoreInfoDetailsFragment;
    }

    public ReceiptFragment m4642d(EtsyId etsyId) {
        this.i.putSerializable(ResponseConstants.RECEIPT_ID, etsyId);
        Fragment receiptFragment = new ReceiptFragment();
        receiptFragment.setArguments(this.i);
        m4605a(receiptFragment);
        this.f3204n.m4699b(m3014b(), etsyId, this.i);
        return receiptFragment;
    }

    public TreasuryFragment m4648f(String str) {
        this.i.putString("treasury_id", str);
        Fragment treasuryFragment = new TreasuryFragment();
        treasuryFragment.setArguments(this.i);
        m4605a(treasuryFragment);
        this.f3204n.m4707f(m3014b(), str);
        return treasuryFragment;
    }

    public CollectionFragment m4633b(Collection collection) {
        this.i.putSerializable(Collection.TYPE_COLLECTION, collection);
        Fragment collectionFragment = new CollectionFragment();
        collectionFragment.setArguments(this.i);
        m4605a(collectionFragment);
        return collectionFragment;
    }

    public ConvoListFragment m4666r() {
        Fragment instance = ConvoListFragment.getInstance();
        instance.setArguments(this.i);
        m4605a(instance);
        this.f3204n.m2075a();
        return instance;
    }

    public ConvoComposeFragment m4617a(SendListener sendListener) {
        Fragment convoComposeFragment = new ConvoComposeFragment();
        if (this.i.containsKey("convo_id")) {
            this.i.putString("TRACKING_NAME", "conversations_thread_reply");
        } else {
            this.i.putString("TRACKING_NAME", "conversations_new");
        }
        convoComposeFragment.setArguments(this.i);
        m4605a(convoComposeFragment);
        this.f3204n.m4698b(this.i);
        return convoComposeFragment;
    }

    public ConvoThreadFragment m4618a(Conversation conversation, boolean z) {
        this.i.putSerializable("conversation", conversation);
        this.i.putBoolean("convo_change_read_state", z);
        Fragment instance = ConvoThreadFragment.getInstance();
        instance.setArguments(this.i);
        m4605a(instance);
        this.f3204n.m2077a(m3014b(), String.valueOf(conversation.getConversationId()));
        return instance;
    }

    public DetailedImageFragment m4667s() {
        Fragment detailedImageFragment = new DetailedImageFragment();
        detailedImageFragment.setArguments(this.i);
        m4605a(detailedImageFragment);
        this.f3204n.m4710i();
        return detailedImageFragment;
    }

    public ShopAboutDetailedImageFragment m4668t() {
        Fragment shopAboutDetailedImageFragment = new ShopAboutDetailedImageFragment();
        shopAboutDetailedImageFragment.setArguments(this.i);
        m4605a(shopAboutDetailedImageFragment);
        this.f3204n.m4710i();
        return shopAboutDetailedImageFragment;
    }

    public void m4629a(String str, @Nullable MutableSearchOptions mutableSearchOptions, @Nullable String str2, @Nullable TaxonomyNode taxonomyNode) {
        Fragment searchResultsListingsFragment = new SearchResultsListingsFragment();
        this.i.putString("SEARCH_QUERY", str);
        if (mutableSearchOptions != null) {
            this.i.putParcelable("SEARCH_OPTIONS", mutableSearchOptions.m4791x());
        }
        if (str2 != null) {
            this.i.putString("ANCHOR_LISTING_ID", str2);
        }
        if (taxonomyNode != null) {
            this.i.putParcelable("SEARCH_TAXONOMY_NODE", Parcels.m7494a((Object) taxonomyNode));
        }
        searchResultsListingsFragment.setArguments(this.i);
        m4605a(searchResultsListingsFragment);
        this.f3204n.m4700b(m3014b(), str);
    }

    public void m4652g(String str) {
        Fragment searchResultsUsersFragment = new SearchResultsUsersFragment();
        this.i.putString("SEARCH_QUERY", str);
        searchResultsUsersFragment.setArguments(this.i);
        m4605a(searchResultsUsersFragment);
        this.f3204n.m4704d(m3014b(), str);
    }

    public void m4654h(String str) {
        Fragment searchResultsShopsFragment = new SearchResultsShopsFragment();
        this.i.putString("SEARCH_QUERY", str);
        searchResultsShopsFragment.setArguments(this.i);
        m4605a(searchResultsShopsFragment);
        this.f3204n.m4702c(m3014b(), str);
    }

    public PurchasesFragment m4669u() {
        Fragment purchasesFragment = new PurchasesFragment();
        purchasesFragment.setArguments(this.i);
        m4605a(purchasesFragment);
        this.f3204n.m4717p();
        return purchasesFragment;
    }

    public PhabletsFragment m4670v() {
        Fragment phabletsFragment = new PhabletsFragment();
        phabletsFragment.setArguments(this.i);
        m4605a(phabletsFragment);
        return phabletsFragment;
    }

    public Fragment m4630b(Bundle bundle) {
        Fragment landingPageFragment = new LandingPageFragment();
        this.i.putAll(bundle);
        landingPageFragment.setArguments(this.i);
        m4605a(landingPageFragment);
        return landingPageFragment;
    }

    public Fragment m4635c(Bundle bundle) {
        Fragment recentlyViewedListingsFragment = new RecentlyViewedListingsFragment();
        this.i.putAll(bundle);
        recentlyViewedListingsFragment.setArguments(this.i);
        m4605a(recentlyViewedListingsFragment);
        return recentlyViewedListingsFragment;
    }

    public Fragment m4639d(Bundle bundle) {
        Fragment shopSharePageFragment = new ShopSharePageFragment();
        this.i.putAll(bundle);
        shopSharePageFragment.setArguments(this.i);
        m4605a(shopSharePageFragment);
        return shopSharePageFragment;
    }

    public Fragment m4643e(Bundle bundle) {
        Fragment shopAboutVideoFragment = new ShopAboutVideoFragment();
        this.i.putAll(bundle);
        shopAboutVideoFragment.setArguments(this.i);
        m4605a(shopAboutVideoFragment);
        return shopAboutVideoFragment;
    }

    public EtsyWebFragment m4655i(String str) {
        Fragment etsyWebFragment = new EtsyWebFragment();
        Serializable hashMap = new HashMap();
        hashMap.put(ResponseConstants.URL, str);
        this.i.putSerializable("parameters", hashMap);
        etsyWebFragment.setArguments(this.i);
        m4605a(etsyWebFragment);
        return etsyWebFragment;
    }

    public AppreciationPhotoLandingPageFragment m4646e(EtsyId etsyId) {
        this.i.putSerializable(ResponseConstants.TRANSACTION_ID, etsyId);
        Fragment appreciationPhotoLandingPageFragment = new AppreciationPhotoLandingPageFragment();
        appreciationPhotoLandingPageFragment.setArguments(this.i);
        m4605a(appreciationPhotoLandingPageFragment);
        return appreciationPhotoLandingPageFragment;
    }

    public Fragment m4671w() {
        Fragment socialShareListingBrokerFragment;
        if (this.i.containsKey(ActivityFeedEntity.LISTING)) {
            socialShareListingBrokerFragment = new SocialShareListingBrokerFragment();
        } else if (this.i.containsKey(ActivityFeedEntity.SHOP)) {
            socialShareListingBrokerFragment = new SocialShareShopBrokerFragment();
        } else if (this.i.containsKey(ResponseConstants.APPRECIATION_PHOTO)) {
            socialShareListingBrokerFragment = new SocialShareAppreciationPhotoBrokerFragment();
        } else if (this.i.containsKey(ResponseConstants.LOCAL_MARKET)) {
            socialShareListingBrokerFragment = new SocialShareLocalMarketBrokerFragment();
        } else if (this.i.containsKey("shop2")) {
            socialShareListingBrokerFragment = new SocialShareShop2BrokerFragment();
        } else {
            throw new RuntimeException("Args did not contain Shareable model");
        }
        m4625a(AnimationMode.SLIDE_BOTTOM);
        socialShareListingBrokerFragment.setArguments(this.i);
        m4605a(socialShareListingBrokerFragment);
        EventTracker.m2035c(m3014b());
        EtsyGraphite.m1807a(SocialShareUtil.m5154a("share_sheet_shown"));
        return socialShareListingBrokerFragment;
    }

    public Fragment m4672x() {
        Fragment cropImageFragment = new CropImageFragment();
        cropImageFragment.setArguments(this.i);
        m4605a(cropImageFragment);
        return cropImageFragment;
    }

    public Fragment m4673y() {
        Fragment rootTaxonomyCategoryPageFragment = new RootTaxonomyCategoryPageFragment();
        rootTaxonomyCategoryPageFragment.setArguments(this.i);
        m4605a(rootTaxonomyCategoryPageFragment);
        return rootTaxonomyCategoryPageFragment;
    }

    public Fragment m4647f(Bundle bundle) {
        Fragment searchTaxonomyCategoryPageFragment = new SearchTaxonomyCategoryPageFragment();
        this.i.putAll(bundle);
        searchTaxonomyCategoryPageFragment.setArguments(this.i);
        m4605a(searchTaxonomyCategoryPageFragment);
        return searchTaxonomyCategoryPageFragment;
    }

    public Fragment m4674z() {
        Fragment appreciationPhotoListFragment = new AppreciationPhotoListFragment();
        appreciationPhotoListFragment.setArguments(this.i);
        m4605a(appreciationPhotoListFragment);
        return appreciationPhotoListFragment;
    }

    public Fragment m4657j(String str) {
        Fragment findsFragment = new FindsFragment();
        this.i.putString("finds_slug", str);
        findsFragment.setArguments(this.i);
        m4605a(findsFragment);
        return findsFragment;
    }

    public Fragment m4650g(Bundle bundle) {
        Fragment searchCategoryPageRedirectFragment = new SearchCategoryPageRedirectFragment();
        searchCategoryPageRedirectFragment.setArguments(bundle);
        m4605a(searchCategoryPageRedirectFragment);
        return searchCategoryPageRedirectFragment;
    }
}

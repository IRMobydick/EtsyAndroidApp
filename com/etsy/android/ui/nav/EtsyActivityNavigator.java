package com.etsy.android.ui.nav;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.Attendee;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.models.Conversation;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.Receipt;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.lib.models.Transaction;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.apiv3.ListingCollection;
import com.etsy.android.lib.models.apiv3.LocalBrowseModule;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.models.finds.FindsUrl;
import com.etsy.android.lib.models.homescreen.LandingPageInfo;
import com.etsy.android.lib.models.homescreen.LandingPageLink;
import com.etsy.android.lib.models.interfaces.AppreciationPhotoLike;
import com.etsy.android.lib.models.interfaces.BasicListingLike;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.models.interfaces.ShopLike;
import com.etsy.android.lib.models.interfaces.ShopShareable;
import com.etsy.android.lib.ui.nav.TrackingNavigator;
import com.etsy.android.lib.util.ExternalAccountUtil.SignInFlow;
import com.etsy.android.lib.util.aa;
import com.etsy.android.lib.util.ad;
import com.etsy.android.lib.util.bh;
import com.etsy.android.ui.CropImageActivity;
import com.etsy.android.ui.EtsyPreferenceActivity;
import com.etsy.android.ui.EtsyWebActivity;
import com.etsy.android.ui.cart.CartActivity;
import com.etsy.android.ui.cart.CartEventTracker;
import com.etsy.android.ui.cart.CartFragment;
import com.etsy.android.ui.cart.CartWithSavedActivity;
import com.etsy.android.ui.convos.ConvoBaseActivity;
import com.etsy.android.ui.convos.ConvoComposeActivity;
import com.etsy.android.ui.convos.ConvoComposeDialogActivity;
import com.etsy.android.ui.convos.ConvoViewActivity;
import com.etsy.android.ui.core.CoreActivity;
import com.etsy.android.ui.core.DetailedImageActivity;
import com.etsy.android.ui.core.EtsyDialogLauncherActivity;
import com.etsy.android.ui.core.ShopAboutVideoActivity;
import com.etsy.android.ui.dialog.TextInfoActivity;
import com.etsy.android.ui.favorites.CollectionEditActivity;
import com.etsy.android.ui.favorites.FavoritesActivity;
import com.etsy.android.ui.favorites.ListingCollectionsActivity;
import com.etsy.android.ui.favorites.MyFavoriteLocalShopsActivity;
import com.etsy.android.ui.finds.FindsActivity;
import com.etsy.android.ui.giftcards.GiftCardCreateActivity;
import com.etsy.android.ui.homescreen.HomescreenTabsActivity;
import com.etsy.android.ui.homescreen.LandingPageActivity;
import com.etsy.android.ui.homescreen.RecentlyViewedListingsActivity;
import com.etsy.android.ui.homescreen.ShopSharePageActivity;
import com.etsy.android.ui.local.LocalSearchActivity;
import com.etsy.android.ui.local.marketdetails.LocalDatesAttendingActivity;
import com.etsy.android.ui.local.marketdetails.LocalEventActivity;
import com.etsy.android.ui.local.marketdetails.LocalStoreInfoDetailsActivity;
import com.etsy.android.ui.promos.VersionPromo;
import com.etsy.android.ui.promos.VersionPromoActivity;
import com.etsy.android.ui.search.v2.MutableSearchOptions;
import com.etsy.android.ui.search.v2.SearchV2Activity;
import com.etsy.android.ui.shop.AppreciationPhotoLandingPageActivity;
import com.etsy.android.ui.shop.ShopSubActivity;
import com.etsy.android.ui.user.AppreciationPhotoActivity;
import com.etsy.android.ui.user.CirclesActivity;
import com.etsy.android.ui.user.LeaveFeedbackActivity;
import com.etsy.android.ui.user.LeaveFeedbackDialogActivity;
import com.etsy.android.ui.user.LegalInfoActivity;
import com.etsy.android.ui.user.NotificationSettingsActivity;
import com.etsy.android.ui.user.PhabletsActivity;
import com.etsy.android.ui.user.PurchasesActivity;
import com.etsy.android.ui.user.ReceiptActivity;
import com.etsy.android.ui.user.SettingsActivity;
import com.etsy.android.ui.user.ShareDialogActivity;
import com.etsy.android.ui.user.ShareFeedbackDialogActivity;
import com.etsy.android.ui.user.SocialShareDialogActivity;
import com.etsy.android.ui.user.SupportFeedbackActivity;
import com.etsy.android.ui.user.UserActivity;
import com.etsy.android.ui.user.auth.ForgotPasswordDialogActivity;
import com.etsy.android.ui.user.auth.SignInActivity;
import com.etsy.android.uikit.image.CropImageUtil.Options;
import com.etsy.android.uikit.nav.ActivityNavigator;
import com.etsy.android.uikit.nav.ActivityNavigator.AnimationMode;
import com.etsy.android.uikit.ui.core.BaseDialogFragment;
import com.etsy.android.uikit.ui.core.TextDialogFragment;
import com.etsy.android.uikit.ui.shop.ShopHomeInitialLoadConfiguration;
import com.etsy.android.uikit.util.TabletSupportUtil;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import org.parceler.Parcels;

/* renamed from: com.etsy.android.ui.nav.b */
public class EtsyActivityNavigator extends ActivityNavigator<EtsyActivityNavigator> {
    private static final String f3186l;
    private boolean f3187m;
    private Intent[] f3188n;
    private Bundle f3189o;

    protected /* synthetic */ TrackingNavigator m4508c() {
        return m4515d();
    }

    static {
        f3186l = EtsyDebug.m1891a(EtsyActivityNavigator.class);
    }

    EtsyActivityNavigator(Activity activity) {
        super(activity);
        this.g = AnimationMode.SLIDE_RIGHT;
    }

    @NonNull
    protected EtsyActivityNavigator m4515d() {
        return this;
    }

    public EtsyActivityNavigator m4445a(Bundle bundle) {
        this.f3189o = bundle;
        return this;
    }

    public EtsyActivityNavigator m4520e() {
        this.b = true;
        this.a = true;
        return this;
    }

    public EtsyActivityNavigator m4525f() {
        this.c = true;
        return this;
    }

    public EtsyActivityNavigator m4447a(@NonNull String str) {
        this.f = str;
        return this;
    }

    public EtsyActivityNavigator m4446a(AnimationMode animationMode) {
        this.g = animationMode;
        return this;
    }

    public EtsyActivityNavigator m4443a(int i) {
        this.e = i;
        return this;
    }

    public EtsyActivityNavigator m4444a(int i, Fragment fragment) {
        this.e = i;
        this.k = fragment;
        return this;
    }

    public EtsyActivityNavigator m4529g() {
        this.d = true;
        return this;
    }

    public void m4533h() {
        Intent intent = new Intent(this.i, GiftCardCreateActivity.class);
        EtsyLogger.m1966a().m1985a("create_gift_card");
        m4509c(intent);
    }

    public void m4452a(EtsyAction etsyAction, Bundle bundle) {
        Intent intent = new Intent(this.i, SignInActivity.class);
        intent.setAction(etsyAction.getAction());
        intent.putExtra(EtsyAction.ACTION_TYPE_NAME, etsyAction.getName());
        intent.putExtra(etsyAction.getName(), bundle);
        m4443a(300);
        m4435a(intent, etsyAction);
    }

    public void m4453a(EtsyAction etsyAction, String str) {
        Intent intent = new Intent(this.i, SignInActivity.class);
        intent.setAction(etsyAction.getAction());
        intent.putExtra(EtsyAction.ACTION_TYPE_NAME, etsyAction.getName());
        intent.putExtra(etsyAction.getName(), str);
        m4443a(300);
        m4435a(intent, etsyAction);
    }

    public void m4495a(boolean z) {
        Intent intent = new Intent(this.i, SignInActivity.class);
        intent.putExtra(SignInActivity.EXTRA_SIGN_IN, false);
        m4435a(intent, z ? EtsyAction.VIEW_FEED : EtsyAction.VIEW);
    }

    public void m4451a(EtsyAction etsyAction) {
        Intent intent = new Intent(this.i, SignInActivity.class);
        intent.setAction(etsyAction.getAction());
        intent.putExtra(EtsyAction.ACTION_TYPE_NAME, etsyAction.getName());
        m4443a(301);
        m4435a(intent, etsyAction);
    }

    private void m4435a(Intent intent, EtsyAction etsyAction) {
        intent.putExtra(FindsModule.FIELD_TYPE, false);
        this.h = true;
        this.g = AnimationMode.FADE_SLOW;
        EtsyEventTracker.m4550a(m3014b(), etsyAction);
        m4509c(intent);
    }

    public void m4467a(EtsyId etsyId) {
        Intent intent = new Intent(this.i, CoreActivity.class);
        intent.putExtra(ResponseConstants.LISTING_ID, etsyId);
        m4509c(intent);
    }

    public void m4470a(EtsyId etsyId, Bundle bundle) {
        Intent intent = new Intent(this.i, CoreActivity.class);
        intent.putExtra(ResponseConstants.LISTING_ID, etsyId);
        intent.putExtra("referral_args", bundle);
        m4509c(intent);
    }

    public void m4501b(EtsyId etsyId) {
        Intent intent = new Intent(this.i, CoreActivity.class);
        intent.putExtra(ResponseConstants.SHOP_ID, etsyId);
        m4509c(intent);
    }

    public void m4473a(EtsyId etsyId, EtsyId etsyId2) {
        Intent intent = new Intent(this.i, CoreActivity.class);
        intent.putExtra(ResponseConstants.SHOP_ID, etsyId);
        intent.putExtra(ResponseConstants.USER_ID, etsyId2);
        m4509c(intent);
    }

    public void m4502b(EtsyId etsyId, Bundle bundle) {
        Intent intent = new Intent(this.i, CoreActivity.class);
        intent.putExtra(ResponseConstants.SHOP_ID, etsyId);
        intent.putExtra("referral_args", bundle);
        m4509c(intent);
    }

    public void m4472a(@NonNull EtsyId etsyId, @Nullable Bundle bundle, @Nullable ShopHomeInitialLoadConfiguration shopHomeInitialLoadConfiguration) {
        Intent intent = new Intent(this.i, CoreActivity.class);
        intent.putExtra(ResponseConstants.SHOP_ID, etsyId);
        if (shopHomeInitialLoadConfiguration != null) {
            intent.putExtra("shop_home_load_configuration", Parcels.m7494a((Object) shopHomeInitialLoadConfiguration));
        }
        if (bundle != null) {
            intent.putExtra("referral_args", bundle);
        }
        m4509c(intent);
    }

    public void m4486a(@NonNull String str, @Nullable Bundle bundle, @Nullable ShopHomeInitialLoadConfiguration shopHomeInitialLoadConfiguration) {
        Intent intent = new Intent(this.i, CoreActivity.class);
        intent.putExtra(ResponseConstants.SHOP_NAME, str);
        if (shopHomeInitialLoadConfiguration != null) {
            intent.putExtra("shop_home_load_configuration", Parcels.m7494a((Object) shopHomeInitialLoadConfiguration));
        }
        if (bundle != null) {
            intent.putExtra("referral_args", bundle);
        }
        m4509c(intent);
    }

    public void m4485a(String str, Bundle bundle) {
        Intent intent = new Intent(this.i, CoreActivity.class);
        intent.putExtra(ResponseConstants.SHOP_NAME, str);
        intent.putExtra("referral_args", bundle);
        m4509c(intent);
    }

    public void m4512c(EtsyId etsyId, Bundle bundle) {
        m4471a(etsyId, bundle, 3);
    }

    public void m4518d(EtsyId etsyId, Bundle bundle) {
        m4471a(etsyId, bundle, 2);
    }

    public void m4471a(EtsyId etsyId, Bundle bundle, int i) {
        m4472a(etsyId, bundle, new ShopHomeInitialLoadConfiguration(i));
    }

    public void m4461a(Shop shop) {
        Intent intent = new Intent(this.i, ShopSubActivity.class);
        intent.putExtra(ActivityFeedEntity.SHOP, shop);
        m4509c(intent);
    }

    public void m4523e(EtsyId etsyId, Bundle bundle) {
        Intent intent = new Intent(this.i, ShopSubActivity.class);
        intent.putExtra("referral_args", bundle);
        intent.putExtra(ResponseConstants.SHOP_ID, etsyId);
        m4509c(intent);
    }

    public void m4506b(String str) {
        Intent intent = new Intent(this.i, CoreActivity.class);
        intent.putExtra("treasury_id", str);
        m4509c(intent);
    }

    public void m4464a(Collection collection) {
        Intent intent = new Intent(this.i, CoreActivity.class);
        intent.putExtra(Collection.TYPE_COLLECTION, collection);
        m4509c(intent);
    }

    public void m4484a(VersionPromo versionPromo) {
        Intent intent = new Intent(this.i, VersionPromoActivity.class);
        intent.putExtra("version_promo", versionPromo);
        this.h = true;
        this.g = AnimationMode.FADE_SLOW;
        m4509c(intent);
    }

    public void m4511c(EtsyId etsyId) {
        Intent intent = new Intent(this.i, CoreActivity.class);
        intent.putExtra(ResponseConstants.USER_ID, etsyId);
        m4509c(intent);
    }

    public void m4527f(EtsyId etsyId, Bundle bundle) {
        Intent intent = new Intent(this.i, CoreActivity.class);
        intent.putExtra(ResponseConstants.USER_ID, etsyId);
        intent.putExtra("referral_args", bundle);
        m4509c(intent);
    }

    public void m4517d(EtsyId etsyId) {
        Intent intent = new Intent(this.i, CoreActivity.class);
        intent.putExtra(ResponseConstants.RECEIPT_ID, etsyId);
        m4509c(intent);
    }

    public void m4531g(EtsyId etsyId, Bundle bundle) {
        Intent intent = new Intent(this.i, CoreActivity.class);
        intent.putExtra(ResponseConstants.RECEIPT_ID, etsyId);
        intent.putExtra(FindsModule.FIELD_TYPE, false);
        intent.putExtra("referral_args", bundle);
        m4509c(intent);
    }

    public void m4493a(ArrayList<BaseModelImage> arrayList, int i) {
        m4494a((ArrayList) arrayList, i, false);
    }

    public void m4494a(ArrayList<BaseModelImage> arrayList, int i, boolean z) {
        m4436a(new Intent(this.i, DetailedImageActivity.class), (ArrayList) arrayList, i, z);
    }

    private void m4436a(Intent intent, ArrayList<BaseModelImage> arrayList, int i, boolean z) {
        intent.putExtra("image_list", arrayList);
        intent.putExtra("position", i);
        intent.putExtra("SHOW_THUMBNAILS", z);
        m4509c(intent);
    }

    public void m4535i() {
        m4509c(new Intent(this.i, UserActivity.class));
    }

    public void m4536j() {
        Intent intent = new Intent(this.i, SettingsActivity.class);
        this.h = true;
        this.g = AnimationMode.FADE_SLOW;
        m4509c(intent);
    }

    public void m4537k() {
        Intent intent = new Intent(this.i, NotificationSettingsActivity.class);
        this.h = true;
        this.g = AnimationMode.FADE_SLOW;
        m4509c(intent);
    }

    public void m4538l() {
        m4509c(new Intent(this.i, PhabletsActivity.class));
    }

    public void m4539m() {
        Intent intent = new Intent(this.i, SupportFeedbackActivity.class);
        this.h = true;
        this.g = AnimationMode.FADE_SLOW;
        m4509c(intent);
    }

    public void m4540n() {
        m4449a(new Intent());
    }

    public void m4449a(Intent intent) {
        intent.setClass(this.i, SearchV2Activity.class);
        m4509c(intent);
    }

    public void m4477a(FindsUrl findsUrl) {
        Object taxonomyNode = findsUrl.getTaxonomyNode();
        Object query = findsUrl.getQuery();
        Object marketplace = findsUrl.getMarketplace();
        EtsyId anchorListingId = findsUrl.getAnchorListingId();
        Object maxPrice = findsUrl.getMaxPrice();
        Object minPrice = findsUrl.getMinPrice();
        Intent intent = new Intent(this.i, SearchV2Activity.class);
        if (!TextUtils.isEmpty(query)) {
            intent.setAction("android.intent.action.SEARCH");
            intent.putExtra(ResponseConstants.QUERY, query);
        }
        if (taxonomyNode != null) {
            intent.putExtra("SEARCH_TAXONOMY_NODE", Parcels.m7494a(taxonomyNode));
        }
        if (anchorListingId != null) {
            intent.putExtra("ANCHOR_LISTING_ID", anchorListingId.toString());
        }
        if (!TextUtils.isEmpty(marketplace)) {
            intent.putExtra("SEARCH_MARKETPLACE_NAME", marketplace);
        }
        if (!TextUtils.isEmpty(maxPrice)) {
            intent.putExtra("SEARCH_MAX_PRICE", maxPrice);
        }
        if (!TextUtils.isEmpty(minPrice)) {
            intent.putExtra("SEARCH_MIN_PRICE", minPrice);
        }
        m4509c(intent);
    }

    public void m4462a(@NonNull TaxonomyNode taxonomyNode, @Nullable String str) {
        Intent intent = new Intent(this.i, SearchV2Activity.class);
        intent.putExtra("SEARCH_TAXONOMY_NODE", Parcels.m7494a((Object) taxonomyNode));
        intent.putExtra("SEARCH_TYPE", "SEARCH_TYPE_CATEGORY");
        if (str != null) {
            intent.putExtra("ANCHOR_LISTING_ID", str);
        }
        this.g = AnimationMode.SLIDE_RIGHT;
        m4509c(intent);
    }

    public void m4488a(@Nullable String str, @Nullable MutableSearchOptions mutableSearchOptions, @Nullable TaxonomyNode taxonomyNode, @Nullable String str2, Bundle bundle) {
        new NavTracker().m4701c(bundle);
        Intent intent = new Intent(this.i, SearchV2Activity.class);
        intent.setAction("android.intent.action.SEARCH");
        if (str != null) {
            intent.putExtra(ResponseConstants.QUERY, str);
        }
        if (mutableSearchOptions != null) {
            intent.putExtra("SEARCH_OPTIONS", mutableSearchOptions.m4791x());
        }
        if (taxonomyNode != null) {
            intent.putExtra("SEARCH_TAXONOMY_NODE", Parcels.m7494a((Object) taxonomyNode));
        }
        intent.putExtra("ANCHOR_LISTING_ID", str2);
        intent.putExtra("SEARCH_TYPE", "SEARCH_TYPE_LISTING");
        intent.putExtra("SEARCH_INITIATED_FROM_WITHIN_APP", false);
        this.g = AnimationMode.SLIDE_RIGHT;
        m4509c(intent);
    }

    public void m4492a(String str, HashMap<String, String> hashMap) {
        Intent intent = new Intent(this.i, SearchV2Activity.class);
        intent.putExtra("SEARCH_CATEGORY_REDIRECT", str);
        intent.putExtra("SEARCH_CATEGORY_REDIRECT_QUERY_PARAMS", hashMap);
        intent.putExtra("SEARCH_INITIATED_FROM_WITHIN_APP", false);
        this.g = AnimationMode.SLIDE_RIGHT;
        m4509c(intent);
    }

    public void m4541o() {
        Intent intent = new Intent();
        if (m3014b().m1847c().m885c(EtsyConfigKeys.bl)) {
            intent.setClass(this.i, CartWithSavedActivity.class);
        } else {
            intent.setClass(this.i, CartActivity.class);
        }
        m4509c(intent);
    }

    public void m4456a(Cart cart, EtsyId etsyId, boolean z) {
        Intent intent = new Intent();
        if (m3014b().m1847c().m885c(EtsyConfigKeys.bl)) {
            intent.setClass(this.i, CartWithSavedActivity.class);
        } else {
            intent.setClass(this.i, CartActivity.class);
        }
        if (!(etsyId == null || !etsyId.hasId() || cart == null)) {
            intent.putExtra(CartFragment.CHECKED_OUT_CART, cart);
            intent.putExtra(CartFragment.LAST_ORDER_ID, etsyId);
            intent.putExtra("should_show_social_invites_prompt", z);
        }
        m4509c(intent);
    }

    public void m4474a(EtsyId etsyId, String str) {
        Intent intent = new Intent();
        if (m3014b().m1847c().m885c(EtsyConfigKeys.bl)) {
            intent.setClass(this.i, CartWithSavedActivity.class);
        } else {
            intent.setClass(this.i, CartActivity.class);
        }
        intent.putExtra(CartFragment.CURRENT_CART_ID, etsyId);
        intent.putExtra(CartFragment.CART_ERROR_MESSAGE, str);
        m4509c(intent);
    }

    public void m4542p() {
        Intent intent = new Intent();
        if (m3014b().m1847c().m885c(EtsyConfigKeys.bl)) {
            intent.setClass(this.i, CartWithSavedActivity.class);
        } else {
            intent.setClass(this.i, CartActivity.class);
        }
        intent.putExtra(CartWithSavedActivity.NAV_TO_SAVED, true);
        m4509c(intent);
    }

    public void m4543q() {
        m4507b(false);
    }

    public void m4507b(boolean z) {
        this.f3187m = true;
        Intent intent = new Intent();
        intent.putExtra("HOME_RESET", z);
        intent.setClass(this.i, HomescreenTabsActivity.class);
        m4509c(intent);
    }

    public void m4498b(Bundle bundle) {
        this.f3187m = true;
        Intent intent = new Intent();
        intent.setClass(this.i, HomescreenTabsActivity.class);
        intent.putExtras(bundle);
        m4509c(intent);
    }

    public void m4514c(String str) {
        this.f3187m = true;
        Intent intent = new Intent();
        intent.setClass(this.i, HomescreenTabsActivity.class);
        intent.putExtra(ResponseConstants.PAGE_LINK, str);
        m4509c(intent);
    }

    public void m4519d(String str) {
        Intent intent = new Intent(this.i, LandingPageActivity.class);
        Object landingPageLink = new LandingPageLink();
        landingPageLink.setPageTitle(this.i.getString(R.similar_items));
        landingPageLink.setApiPath(aj.m1101a().m1118d() ? "/etsyapps/v3/member/personalization/similar-listings" : "/etsyapps/v3/public/personalization/similar-listings");
        HashMap params = landingPageLink.getParams();
        params.put(ResponseConstants.LISTING_IDS, str);
        params.put("limit", "28");
        params.put("variant", "RecommenderSystems_SimilarListingsTfidf_Strings");
        landingPageLink.setEventName("browselistings");
        intent.putExtra(ResponseConstants.PAGE_LINK, Parcels.m7494a(landingPageLink));
        m4509c(intent);
    }

    public void m4496b(int i) {
        Intent intent = new Intent();
        intent.setClass(this.i, EtsyWebActivity.class);
        intent.putExtra(FindsModule.FIELD_TYPE, i);
        m4509c(intent);
    }

    public void m4466a(@Nullable LocalBrowseModule localBrowseModule, boolean z) {
        Intent intent = new Intent(this.i, LocalSearchActivity.class);
        intent.putExtra(LocalSearchActivity.START_FULLSCREEN_MAP, z);
        if (localBrowseModule != null) {
            intent.putExtra("local_browse_module", Parcels.m7494a((Object) localBrowseModule));
            if (localBrowseModule.getLandingPage() != null) {
                EtsyLogger.m1966a().m1985a(localBrowseModule.getLandingPage().getAnalyticsEventName());
            }
        } else {
            EtsyLogger.m1966a().m1985a("local_browse_nearby");
        }
        m4509c(intent);
    }

    public void m4476a(EtsyId etsyId, boolean z) {
        Intent intent = new Intent(this.i, LocalEventActivity.class);
        intent.putExtra(ResponseConstants.LOCAL_MARKET_ID, etsyId);
        intent.putExtra("show_local_browse_link", z);
        m4509c(intent);
    }

    public void m4460a(LocalMarket localMarket, boolean z) {
        Intent intent = new Intent(this.i, LocalEventActivity.class);
        intent.putExtra(ResponseConstants.LOCAL_MARKET, Parcels.m7494a((Object) localMarket));
        intent.putExtra("show_local_browse_link", z);
        m4509c(intent);
    }

    public void m4454a(Attendee attendee, LocalMarket localMarket) {
        Intent intent = new Intent(this.i, LocalDatesAttendingActivity.class);
        intent.putExtra("attendee", attendee);
        intent.putExtra(ResponseConstants.LOCAL_MARKET, localMarket);
        this.g = AnimationMode.SLIDE_BOTTOM;
        m4509c(intent);
    }

    public void m4459a(LocalMarket localMarket) {
        Intent intent = new Intent(this.i, LocalStoreInfoDetailsActivity.class);
        intent.putExtra(ResponseConstants.LOCAL_MARKET, localMarket);
        this.g = AnimationMode.SLIDE_BOTTOM;
        m4509c(intent);
    }

    public void m4524e(String str) {
        Intent intent = new Intent();
        intent.setClass(this.i, EtsyWebActivity.class);
        intent.putExtra(FindsModule.FIELD_TYPE, 3);
        intent.putExtra(ResponseConstants.URL, str);
        m4509c(intent);
    }

    public void m4503b(EtsyId etsyId, EtsyId etsyId2) {
        Intent intent = new Intent(this.i, ReceiptActivity.class);
        intent.setAction("android.intent.action.VIEW");
        intent.putExtra(ResponseConstants.RECEIPT_ID, etsyId);
        Intent intent2 = new Intent();
        intent2.setClass(this.i, EtsyWebActivity.class);
        intent2.putExtra(FindsModule.FIELD_TYPE, 3);
        intent2.putExtra(ResponseConstants.RECEIPT_ID, etsyId);
        intent2.putExtra(ResponseConstants.RECEIPT_SHIPPING_ID, etsyId2);
        TaskStackBuilder create = TaskStackBuilder.create(this.i);
        create.addNextIntentWithParentStack(intent);
        create.addNextIntent(intent2);
        create.startActivities();
    }

    public void m4455a(Cart cart) {
        CartEventTracker.m3731e(m3014b(), cart, "cart_view");
        Intent intent = new Intent();
        intent.setClass(this.i, EtsyWebActivity.class);
        intent.putExtra(CartFragment.CHECKED_OUT_CART, cart);
        intent.putExtra(FindsModule.FIELD_TYPE, 2);
        this.g = AnimationMode.SLIDE_BOTTOM;
        m4509c(intent);
    }

    public void m4457a(Cart cart, String str, HashMap<String, String> hashMap) {
        CartEventTracker.m3731e(m3014b(), cart, "cart_view");
        Intent intent = new Intent();
        intent.setClass(this.i, EtsyWebActivity.class);
        intent.putExtra(CartFragment.CHECKED_OUT_CART, cart);
        intent.putExtra(FindsModule.FIELD_TYPE, 2);
        intent.putExtra("google_wallet_transaction_id", str);
        intent.putExtra("google_wallet_web_params", hashMap);
        m4509c(intent);
    }

    public void m4528f(String str) {
        try {
            if (ad.m3192c(new URL(str).getHost())) {
                Intent intent = new Intent();
                intent.setClass(this.i, EtsyWebActivity.class);
                intent.putExtra(FindsModule.FIELD_TYPE, 11);
                intent.putExtra(ResponseConstants.URL, str);
                m4509c(intent);
            }
        } catch (MalformedURLException e) {
        }
    }

    public void m4544r() {
        Intent intent = new Intent();
        intent.setClass(this.i, EtsyPreferenceActivity.class);
        m4509c(intent);
    }

    public void m4510c(Bundle bundle) {
        Intent intent = new Intent(this.i, ListingCollectionsActivity.class);
        intent.putExtras(bundle);
        m4497b(intent);
    }

    public void m4481a(ListingLike listingLike) {
        Intent intent = new Intent(this.i, ListingCollectionsActivity.class);
        intent.putExtra(ActivityFeedEntity.LISTING, listingLike);
        m4497b(intent);
    }

    protected void m4497b(Intent intent) {
        this.h = true;
        this.g = AnimationMode.FADE_SLOW;
        m4509c(intent);
    }

    public void m4500b(Collection collection) {
        Intent intent = new Intent(this.i, CollectionEditActivity.class);
        intent.putExtra(Collection.TYPE_COLLECTION, collection);
        this.h = true;
        this.g = AnimationMode.FADE_SLOW;
        m4509c(intent);
    }

    public void m4478a(LandingPageInfo landingPageInfo) {
        Intent intent = new Intent(this.i, LandingPageActivity.class);
        intent.putExtra(ResponseConstants.PAGE_LINK, Parcels.m7494a((Object) landingPageInfo));
        m4509c(intent);
    }

    public void m4505b(LandingPageInfo landingPageInfo) {
        Intent intent = new Intent(this.i, RecentlyViewedListingsActivity.class);
        intent.putExtra(ResponseConstants.PAGE_LINK, Parcels.m7494a((Object) landingPageInfo));
        m4509c(intent);
    }

    public void m4513c(LandingPageInfo landingPageInfo) {
        Intent intent = new Intent(this.i, ShopSharePageActivity.class);
        intent.putExtra(ResponseConstants.PAGE_LINK, Parcels.m7494a((Object) landingPageInfo));
        m4509c(intent);
    }

    public void m4516d(Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        m4439e(intent);
    }

    public void m4545s() {
        m4439e(new Intent());
    }

    private void m4439e(Intent intent) {
        if (new TabletSupportUtil(this.i).m5621a()) {
            intent.setClass(this.i, ConvoComposeDialogActivity.class);
            this.h = true;
        } else {
            intent.setClass(this.i, ConvoComposeActivity.class);
            this.g = AnimationMode.SLIDE_BOTTOM;
        }
        m4509c(intent);
    }

    public void m4546t() {
        Intent intent = new Intent();
        intent.setClass(this.i, ConvoBaseActivity.class);
        m4509c(intent);
    }

    public void m4458a(Conversation conversation, boolean z) {
        Intent intent = new Intent(this.i, ConvoViewActivity.class);
        intent.putExtra("conversation", conversation);
        intent.putExtra("convo_change_read_state", z);
        m4509c(intent);
    }

    public void m4532g(String str) {
        Intent intent = new Intent(this.i, ConvoBaseActivity.class);
        intent.putExtra("convo_id", str);
        m4509c(intent);
    }

    public void m4468a(EtsyId etsyId, int i) {
        m4469a(etsyId, i, null);
    }

    public void m4469a(EtsyId etsyId, int i, String str) {
        Intent intent = new Intent(this.i, FavoritesActivity.class);
        intent.putExtra(ResponseConstants.USER_ID, etsyId);
        intent.putExtra(FindsModule.FIELD_TYPE, i);
        if (bh.m3340a(str)) {
            intent.putExtra("username", str);
        }
        m4509c(intent);
    }

    public void m4547u() {
        m4509c(new Intent(this.i, MyFavoriteLocalShopsActivity.class));
    }

    public void m4475a(EtsyId etsyId, String str, int i) {
        Intent intent = new Intent(this.i, CirclesActivity.class);
        intent.putExtra(ResponseConstants.USER_ID, etsyId);
        intent.putExtra("username", str);
        intent.putExtra(FindsModule.FIELD_TYPE, i);
        m4509c(intent);
    }

    public void m4548v() {
        m4509c(new Intent(this.i, PurchasesActivity.class));
    }

    public void m4487a(String str, SignInFlow signInFlow) {
        Intent intent = new Intent(this.i, ForgotPasswordDialogActivity.class);
        intent.putExtra("username", str);
        intent.putExtra("sign_in_flow", signInFlow);
        this.h = true;
        m4509c(intent);
    }

    public void m4463a(Transaction transaction, User user, String str, Receipt receipt) {
        Intent x = m4442x();
        x.putExtra("transaction", transaction);
        x.putExtra(ActivityFeedEntity.USER, user);
        x.putExtra(ResponseConstants.SHOP_NAME, str);
        x.putExtra(ResponseConstants.TRANSACTION_ID, transaction.getTransactionId());
        if (receipt != null) {
            EtsyEventTracker.m4552a(m3014b(), receipt.getReceiptId(), transaction.getTransactionId(), transaction.getReview() != null);
        }
        m4509c(x);
    }

    public void m4522e(EtsyId etsyId) {
        Intent x = m4442x();
        x.putExtra(ResponseConstants.TRANSACTION_ID, etsyId);
        m4509c(x);
    }

    private Intent m4442x() {
        if (new TabletSupportUtil(this.i).m5621a()) {
            Intent intent = new Intent(this.i, LeaveFeedbackDialogActivity.class);
            this.h = true;
            this.g = AnimationMode.FADE_SLOW;
            return intent;
        }
        intent = new Intent(this.i, LeaveFeedbackActivity.class);
        this.g = AnimationMode.SLIDE_BOTTOM;
        return intent;
    }

    public void m4521e(Bundle bundle) {
        this.h = true;
        this.g = AnimationMode.FADE_SLOW;
        Intent intent = new Intent(this.i, ShareFeedbackDialogActivity.class);
        intent.putExtras(bundle);
        Transaction transaction = (Transaction) bundle.getSerializable("transaction");
        if (transaction != null) {
            EtsyEventTracker.m4555a(transaction.getTransactionId());
        }
        m4509c(intent);
    }

    public void m4490a(String str, String str2, String str3, String str4) {
        Intent intent = new Intent(this.i, ShareDialogActivity.class);
        intent.putExtra(ResponseConstants.SUBJECT, str);
        intent.putExtra(FindsModule.FIELD_TEXT, str2);
        intent.putExtra(ResponseConstants.URL, str3);
        intent.putExtra(ResponseConstants.IMAGE_URL, str4);
        this.h = true;
        m4509c(intent);
    }

    public void m4483a(ShopShareable shopShareable) {
        Intent intent = new Intent(this.i, SocialShareDialogActivity.class);
        intent.putExtra("shop2", Parcels.m7494a((Object) shopShareable));
        this.h = true;
        m4509c(intent);
    }

    public void m4482a(ShopLike shopLike) {
        Intent intent = new Intent(this.i, SocialShareDialogActivity.class);
        intent.putExtra(ActivityFeedEntity.SHOP, shopLike);
        this.h = true;
        m4509c(intent);
    }

    public void m4480a(BasicListingLike basicListingLike) {
        Intent intent = new Intent(this.i, SocialShareDialogActivity.class);
        intent.putExtra(ActivityFeedEntity.LISTING, basicListingLike);
        this.h = true;
        m4509c(intent);
    }

    public void m4465a(ListingCollection listingCollection, ListingLike listingLike) {
        Intent intent = new Intent(this.i, SocialShareDialogActivity.class);
        intent.putExtra(ActivityFeedEntity.LISTING, listingLike);
        intent.putExtra(Collection.TYPE_COLLECTION, listingCollection);
        this.h = true;
        m4509c(intent);
    }

    public void m4479a(AppreciationPhotoLike appreciationPhotoLike) {
        Intent intent = new Intent(this.i, SocialShareDialogActivity.class);
        intent.putExtra(ResponseConstants.APPRECIATION_PHOTO, appreciationPhotoLike);
        this.h = true;
        m4509c(intent);
    }

    public void m4499b(LocalMarket localMarket) {
        Intent intent = new Intent(this.i, SocialShareDialogActivity.class);
        intent.putExtra(ResponseConstants.LOCAL_MARKET, Parcels.m7494a((Object) localMarket));
        this.h = true;
        m4509c(intent);
    }

    public void m4504b(EtsyId etsyId, String str) {
        Intent intent = new Intent(this.i, ShopAboutVideoActivity.class);
        intent.putExtra("video_url", str);
        intent.putExtra(ResponseConstants.SHOP_ID, etsyId);
        EtsyEventTracker.m4576e(m3014b(), etsyId);
        m4509c(intent);
    }

    public void m4549w() {
        m4509c(new Intent(this.i, LegalInfoActivity.class));
    }

    public void m4450a(@NonNull Uri uri, @NonNull Uri uri2, @Nullable Options options) {
        if (options == null) {
            options = new Options();
        }
        Intent intent = new Intent(this.i, CropImageActivity.class);
        intent.putExtra("source_uri", uri.toString());
        intent.putExtra("dest_uri", uri2.toString());
        intent.putExtra(ResponseConstants.OPTIONS, options);
        m4509c(intent);
    }

    public void m4526f(EtsyId etsyId) {
        Intent intent = new Intent(this.i, AppreciationPhotoLandingPageActivity.class);
        intent.putExtra(ResponseConstants.TRANSACTION_ID, etsyId);
        m4509c(intent);
    }

    public void m4530g(EtsyId etsyId) {
        Intent intent = new Intent(this.i, AppreciationPhotoActivity.class);
        intent.putExtra(ResponseConstants.USER_ID, etsyId);
        m4509c(intent);
    }

    public void m4489a(String str, String str2) {
        this.h = true;
        this.g = AnimationMode.FADE_SLOW;
        Intent intent = new Intent(this.i, TextInfoActivity.class);
        intent.putExtra(FindsModule.FIELD_TITLE, str);
        intent.putExtra(FindsModule.FIELD_TEXT, str2);
        m4509c(intent);
    }

    public void m4491a(String str, String str2, boolean z) {
        Intent intent = new Intent(this.i, FindsActivity.class);
        intent.putExtra("finds_slug", str);
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("ANCHOR_LISTING_ID", str2);
        }
        intent.putExtra("finds_is_draft", z);
        m4509c(intent);
    }

    public void m4534h(@NonNull String str) {
        m4448a((int) R.terms_and_conditions_title, str);
    }

    public void m4448a(@StringRes int i, @NonNull String str) {
        Bundle bundle = new Bundle();
        bundle.putString(FindsModule.FIELD_TEXT, str);
        m4437a(TextDialogFragment.class, i, bundle);
    }

    protected void m4509c(Intent intent) {
        if (!(this.f3189o == null || intent.hasExtra("referral_args"))) {
            intent.putExtra("referral_args", this.f3189o);
        }
        if (this.h && this.g != AnimationMode.FADE_SLOW) {
            this.g = AnimationMode.FADE_SLOW;
        }
        Intent d = m4434d(intent);
        if (this.e > 0) {
            if (this.k != null) {
                this.k.startActivityForResult(d, this.e);
            } else {
                if (this.c && !this.f3187m && !this.i.getClass().equals(HomescreenTabsActivity.class)) {
                    Intent intent2 = new Intent(this.i, HomescreenTabsActivity.class);
                    intent2.addFlags(67108864);
                    this.i.startActivity(ActivityNavigator.m4428a(intent2, AnimationMode.DEFAULT_OUT));
                } else if (this.c) {
                    d.addFlags(67108864);
                }
                this.i.startActivityForResult(d, this.e);
            }
            ActivityNavigator.m4429a(this.i, this.g);
            return;
        }
        if (!this.c || this.f3187m || this.i.getClass().equals(HomescreenTabsActivity.class)) {
            if (this.c) {
                d.addFlags(67108864);
            }
            m4440f(d);
        } else {
            boolean z;
            intent2 = new Intent(this.i, HomescreenTabsActivity.class);
            intent2.addFlags(67108864);
            Intent a = ActivityNavigator.m4428a(intent2, AnimationMode.DEFAULT_OUT);
            if (aa.m3169c()) {
                z = false;
            } else {
                z = ContextCompat.startActivities(this.i, m4438a(d, a));
            }
            if (!z) {
                this.i.startActivity(a);
                m4440f(d);
            }
        }
        ActivityNavigator.m4429a(this.i, this.g);
    }

    private void m4440f(Intent intent) {
        if (this.f3188n == null || this.f3188n.length <= 0) {
            this.i.startActivity(intent);
            return;
        }
        TaskStackBuilder create = TaskStackBuilder.create(this.i);
        for (Intent addNextIntent : m4441g(intent)) {
            create.addNextIntent(addNextIntent);
        }
        create.startActivities();
    }

    private Intent[] m4441g(Intent intent) {
        return m4438a(intent, null);
    }

    private Intent[] m4438a(Intent intent, Intent intent2) {
        ArrayList arrayList = new ArrayList();
        if (intent2 != null) {
            arrayList.add(intent2);
        }
        if (this.f3188n != null && this.f3188n.length > 0) {
            Collections.addAll(arrayList, this.f3188n);
        }
        if (intent != null) {
            arrayList.add(intent);
        }
        return (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
    }

    private void m4437a(Class<? extends BaseDialogFragment> cls, @StringRes int i, Bundle bundle) {
        m4432a(EtsyDialogLauncherActivity.class, cls, i, bundle);
    }
}

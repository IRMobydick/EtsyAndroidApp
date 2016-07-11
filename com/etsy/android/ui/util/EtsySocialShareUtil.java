package com.etsy.android.ui.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.apiv3.ListingCollection;
import com.etsy.android.lib.models.interfaces.BasicListingLike;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.models.interfaces.ShopLike;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.share.SocialSharePopup;
import com.etsy.android.uikit.util.SocialShareUtil;
import com.etsy.android.uikit.util.SocialShareUtil.ShareType;

/* renamed from: com.etsy.android.ui.util.n */
public class EtsySocialShareUtil extends SocialShareUtil {

    /* renamed from: com.etsy.android.ui.util.n.1 */
    final class EtsySocialShareUtil implements SocialSharePopup {
        final /* synthetic */ FragmentActivity f3721a;
        final /* synthetic */ ShareType f3722b;
        final /* synthetic */ ShopLike f3723c;

        EtsySocialShareUtil(FragmentActivity fragmentActivity, ShareType shareType, ShopLike shopLike) {
            this.f3721a = fragmentActivity;
            this.f3722b = shareType;
            this.f3723c = shopLike;
        }

        public void m5150a() {
            SharedPreferencesUtility.m3151j(this.f3721a, this.f3722b.getName());
            Nav.m4682a(this.f3721a).m4683a().m4482a(this.f3723c);
        }
    }

    /* renamed from: com.etsy.android.ui.util.n.2 */
    final class EtsySocialShareUtil implements SocialSharePopup {
        final /* synthetic */ boolean f3724a;
        final /* synthetic */ FragmentActivity f3725b;
        final /* synthetic */ ShareType f3726c;
        final /* synthetic */ BasicListingLike f3727d;

        EtsySocialShareUtil(boolean z, FragmentActivity fragmentActivity, ShareType shareType, BasicListingLike basicListingLike) {
            this.f3724a = z;
            this.f3725b = fragmentActivity;
            this.f3726c = shareType;
            this.f3727d = basicListingLike;
        }

        public void m5151a() {
            if (!this.f3724a) {
                SharedPreferencesUtility.m3151j(this.f3725b, this.f3726c.getName());
            }
            Nav.m4682a(this.f3725b).m4683a().m4480a(this.f3727d);
        }
    }

    /* renamed from: com.etsy.android.ui.util.n.3 */
    final class EtsySocialShareUtil implements SocialSharePopup {
        final /* synthetic */ FragmentActivity f3728a;
        final /* synthetic */ ListingCollection f3729b;
        final /* synthetic */ ListingLike f3730c;

        EtsySocialShareUtil(FragmentActivity fragmentActivity, ListingCollection listingCollection, ListingLike listingLike) {
            this.f3728a = fragmentActivity;
            this.f3729b = listingCollection;
            this.f3730c = listingLike;
        }

        public void m5152a() {
            SharedPreferencesUtility.m3151j(this.f3728a, ShareType.ADD_TO_LIST.getName());
            Nav.m4682a(this.f3728a).m4683a().m4465a(this.f3729b, this.f3730c);
        }
    }

    private static void m5160a(FragmentActivity fragmentActivity, @NonNull AnalyticsTracker analyticsTracker, ShopLike shopLike) {
        ShareType shareType = ShareType.FAVORITE_SHOP;
        if (!EtsySocialShareUtil.m5161a((Context) fragmentActivity, analyticsTracker, shareType)) {
            SocialShareUtil.m5156a(fragmentActivity.getLocalClassName(), shareType, shopLike.getShopId().getId());
            SocialSharePopup socialSharePopup = new SocialSharePopup((Activity) fragmentActivity);
            socialSharePopup.m5406a((int) R.social_share_shop_prompt);
            socialSharePopup.m5407a(new EtsySocialShareUtil(fragmentActivity, shareType, shopLike));
            socialSharePopup.m5405a();
        }
    }

    private static void m5159a(FragmentActivity fragmentActivity, @NonNull AnalyticsTracker analyticsTracker, BasicListingLike basicListingLike, boolean z) {
        ShareType shareType;
        if (z) {
            shareType = ShareType.POST_PURCHASE;
        } else {
            shareType = ShareType.FAVORITE_ITEM;
        }
        if (z || !EtsySocialShareUtil.m5161a((Context) fragmentActivity, analyticsTracker, shareType)) {
            SocialShareUtil.m5156a(fragmentActivity.getLocalClassName(), shareType, basicListingLike.getListingId().getId());
            SocialSharePopup socialSharePopup = new SocialSharePopup((Activity) fragmentActivity);
            socialSharePopup.m5406a(z ? R.social_share_pp_prompt : R.social_share_listing_prompt);
            socialSharePopup.m5407a(new EtsySocialShareUtil(z, fragmentActivity, shareType, basicListingLike));
            socialSharePopup.m5405a();
        }
    }

    public static void m5158a(FragmentActivity fragmentActivity, @NonNull AnalyticsTracker analyticsTracker, ListingCollection listingCollection, ListingLike listingLike) {
        if (!EtsySocialShareUtil.m5161a((Context) fragmentActivity, analyticsTracker, ShareType.ADD_TO_LIST)) {
            SocialShareUtil.m5156a(fragmentActivity.getLocalClassName(), ShareType.ADD_TO_LIST, listingCollection.getKey());
            SocialSharePopup socialSharePopup = new SocialSharePopup((Activity) fragmentActivity);
            socialSharePopup.m5406a((int) R.social_share_collection_prompt);
            socialSharePopup.m5407a(new EtsySocialShareUtil(fragmentActivity, listingCollection, listingLike));
            socialSharePopup.m5405a();
        }
    }

    private static boolean m5161a(Context context, @NonNull AnalyticsTracker analyticsTracker, ShareType shareType) {
        boolean z = false;
        if (!analyticsTracker.m1847c().m885c(EtsyConfigKeys.bn)) {
            if (SharedPreferencesUtility.m3146h(context, shareType.getName()) + 604800000 > System.currentTimeMillis()) {
                z = true;
            }
            if (!z) {
                SharedPreferencesUtility.m3149i(context, shareType.getName());
            }
        }
        return z;
    }

    public static void m5157a(FragmentActivity fragmentActivity, @NonNull AnalyticsTracker analyticsTracker, BaseModel baseModel, boolean z) {
        if (baseModel instanceof ShopLike) {
            EtsySocialShareUtil.m5160a(fragmentActivity, analyticsTracker, (ShopLike) baseModel);
        } else if (baseModel instanceof BasicListingLike) {
            EtsySocialShareUtil.m5159a(fragmentActivity, analyticsTracker, (BasicListingLike) baseModel, z);
        }
    }
}

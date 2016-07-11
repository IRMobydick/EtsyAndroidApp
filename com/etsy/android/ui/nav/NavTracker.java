package com.etsy.android.ui.nav;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.logger.EtsyAdjust;
import com.etsy.android.lib.logger.EtsyFacebookTracker;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.logger.LogTracker;
import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.models.CartListing;
import com.etsy.android.lib.models.PaymentMethod;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.UserNote;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.util.CurrencyUtil;
import com.etsy.android.lib.util.ExternalAccountUtil.SignInFlow;
import com.etsy.android.uikit.util.AdHocEventCompatBuilder;
import com.google.android.gms.gcm.Task;
import java.util.HashMap;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.ui.nav.f */
public class NavTracker extends LogTracker {
    public void m4700b(@NonNull AnalyticsTracker analyticsTracker, String str) {
        analyticsTracker.m1847c().m885c(EtsyConfigKeys.f1237b);
        new AdHocEventCompatBuilder("searched").m5515a("search_items").m5518b(str).m5512a(AnalyticsLogAttribute.QUERY, (Object) str).m5513a(analyticsTracker).m5517a();
    }

    public void m4702c(@Nullable AnalyticsTracker analyticsTracker, String str) {
        new AdHocEventCompatBuilder("searched").m5515a("search_shops").m5518b(str).m5512a(AnalyticsLogAttribute.QUERY, (Object) str).m5513a(analyticsTracker).m5517a();
    }

    public void m4704d(@Nullable AnalyticsTracker analyticsTracker, String str) {
        new AdHocEventCompatBuilder("searched").m5515a("search_users").m5518b(str).m5512a(AnalyticsLogAttribute.QUERY, (Object) str).m5513a(analyticsTracker).m5517a();
    }

    public void m4695a(String str) {
        this.a.m2001d("country_tapped", str);
    }

    public void m4706e(@Nullable AnalyticsTracker analyticsTracker, String str) {
        new AdHocEventCompatBuilder("select_variation").m5515a(str).m5513a(analyticsTracker).m5517a();
    }

    public void m4708g() {
        this.a.m2001d("coupon_code_tapped", "cart_view");
    }

    public void m4709h() {
        this.a.m2001d("note_to_seller_tapped", "cart_view");
    }

    public void m4710i() {
        this.a.m1985a("listing_image_zoom");
    }

    public void m4711j() {
        this.a.m1985a("cart_view");
    }

    public void m4712k() {
        Locale locale = Locale.getDefault();
        HashMap hashMap = new HashMap();
        hashMap.put(EtsyRequest.PARAM_LANGUAGE, locale.getLanguage());
        hashMap.put(EtsyRequest.PARAM_REGION, locale.getCountry());
        hashMap.put(EtsyRequest.PARAM_CURRENCY, CurrencyUtil.m3091i());
        this.a.m1992a("view_locale_preferences", hashMap);
    }

    public void m4713l() {
        this.a.m1985a("settings_legal");
    }

    public void m4714m() {
        this.a.m1985a("terms_of_use");
    }

    public void m4715n() {
        this.a.m1985a("privacy_policy");
    }

    public void m4716o() {
        this.a.m1985a("electronic_communications_policy");
    }

    public void m4717p() {
        this.a.m1985a("your_purchases");
    }

    public void m4718q() {
        this.a.m1985a("listing_variation_options");
    }

    public void m4692a(Cart cart, PaymentMethod paymentMethod) {
        String type = paymentMethod != null ? paymentMethod.getType() : StringUtils.EMPTY;
        StringBuilder stringBuilder = new StringBuilder();
        for (CartListing listingId : cart.getCartListings()) {
            stringBuilder.append(listingId.getListingId().getId()).append(",");
        }
        this.a.m1992a("cart_checkout", new NavTracker$1(this, cart, stringBuilder, type));
    }

    public void m4697a(boolean z, EtsyId etsyId) {
        String str = (!etsyId.hasId() || aj.m1101a().m1126m().equals(etsyId)) ? z ? "your_circles_following" : "your_circles_followers" : z ? "people_circles_following" : "people_circles_followers";
        this.a.m1985a(str);
    }

    public void m4696a(boolean z) {
        this.a.m1985a(z ? "your_favorites" : "profile_favorites");
    }

    public void m4689a(int i, boolean z) {
        String str;
        switch (i) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                if (!z) {
                    str = "profile_favorite_listings";
                    break;
                } else {
                    str = "your_favorite_items";
                    break;
                }
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                if (!z) {
                    str = "profile_favorite_shops";
                    break;
                } else {
                    str = "your_favorite_shops";
                    break;
                }
            default:
                if (!z) {
                    str = "profile_favorite_treasuries";
                    break;
                } else {
                    str = "your_favorite_treasuries";
                    break;
                }
        }
        this.a.m1985a(str);
    }

    public void m4719r() {
        this.a.m1985a("your_favorite_shops_local");
    }

    public void m4720s() {
        this.a.m1985a("list_open");
    }

    public void m4721t() {
        this.a.m1985a("list_edit_overlay_open");
    }

    public void m4722u() {
        this.a.m1985a("list_create_open");
    }

    public void m4723v() {
        this.a.m1999c("your_account_settings", aj.m1101a().m1118d() ? "logged_in_user" : "logged_out_user");
    }

    public void m4724w() {
        this.a.m1985a("login_nag");
    }

    public void m4703c(SignInFlow signInFlow) {
        m2079a("login_2_factor", signInFlow);
    }

    public void m4705d(SignInFlow signInFlow) {
        m2079a("forgot_password_dialog", signInFlow);
    }

    public void m4707f(@Nullable AnalyticsTracker analyticsTracker, String str) {
        new AdHocEventCompatBuilder(ActivityFeedEntity.TREASURY).m5515a("internal_url").m5516a(new NavTracker$2(this, str)).m5512a(AnalyticsLogAttribute.TREASURY_ID, (Object) str).m5513a(analyticsTracker).m5517a();
        this.a.m1999c("view_treasury_list", str);
    }

    public void m4698b(Bundle bundle) {
        if (bundle.containsKey("convo_id")) {
            m2083c();
        } else {
            m2081b();
        }
    }

    public void m4693a(EtsyId etsyId) {
        this.a.m1992a("local_event_view", new NavTracker$3(this, etsyId));
    }

    public void m4690a(@Nullable AnalyticsTracker analyticsTracker, EtsyId etsyId, Bundle bundle) {
        if (etsyId != null) {
            new AdHocEventCompatBuilder(ActivityFeedEntity.LISTING).m5515a(m4687d(bundle)).m5516a(new NavTracker$4(this, etsyId)).m5511a(AnalyticsLogAttribute.LISTING_ID, etsyId).m5513a(analyticsTracker).m5517a();
            HashMap e = m4688e(bundle);
            e.put(ResponseConstants.LISTING_ID, etsyId.getId());
            this.a.m1992a("view_listing", e);
            EtsyAdjust.m1886e();
            EtsyFacebookTracker.m1934c(etsyId);
        }
    }

    public void m4691a(@Nullable AnalyticsTracker analyticsTracker, @Nullable EtsyId etsyId, @Nullable EtsyId etsyId2, Bundle bundle) {
        if (etsyId != null) {
            new AdHocEventCompatBuilder(ActivityFeedEntity.SHOP).m5515a(m4687d(bundle)).m5516a(new NavTracker$5(this, etsyId)).m5511a(AnalyticsLogAttribute.SHOP_ID, etsyId).m5513a(analyticsTracker).m5517a();
            HashMap e = m4688e(bundle);
            e.put("shop_shop_id", etsyId.getId());
            if (etsyId2 != null && etsyId2.hasId()) {
                e.put(ResponseConstants.USER_ID, etsyId2.getId());
            }
            EtsyLogger.m1966a().m1992a("shop_home", e);
        }
    }

    public void m4694a(EtsyId etsyId, Bundle bundle) {
        if (etsyId != null) {
            HashMap e = m4688e(bundle);
            e.put("shop_shop_id", etsyId.getId());
            EtsyLogger.m1966a().m1992a("shop_reviews", e);
        }
    }

    public void m4699b(@Nullable AnalyticsTracker analyticsTracker, EtsyId etsyId, Bundle bundle) {
        if (etsyId != null) {
            new AdHocEventCompatBuilder(UserNote.SUBJECT_TYPE_RECEIPT).m5515a(m4687d(bundle)).m5516a(new NavTracker$7(this, etsyId)).m5511a(AnalyticsLogAttribute.RECEIPT_ID, etsyId).m5513a(analyticsTracker).m5517a();
            HashMap e = m4688e(bundle);
            e.put(ResponseConstants.RECEIPT_ID, etsyId.getId());
            this.a.m1992a("view_receipt", e);
        }
    }

    public void m4701c(Bundle bundle) {
        EtsyLogger.m1966a().m1992a("search", m4688e(bundle));
    }

    private String m4687d(Bundle bundle) {
        if (bundle == null || !bundle.containsKey("source_type")) {
            return "internal_url";
        }
        return bundle.getString("source_type");
    }

    private HashMap<String, Object> m4688e(Bundle bundle) {
        HashMap<String, Object> hashMap = new HashMap();
        if (!(bundle == null || bundle.getBundle("referrer_bundle") == null)) {
            Bundle bundle2 = bundle.getBundle("referrer_bundle");
            for (String str : bundle2.keySet()) {
                if (!TextUtils.isEmpty(bundle2.getString(str))) {
                    hashMap.put(str, bundle2.getString(str));
                }
            }
        }
        return hashMap;
    }
}

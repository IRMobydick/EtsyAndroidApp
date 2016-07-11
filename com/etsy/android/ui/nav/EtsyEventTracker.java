package com.etsy.android.ui.nav;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.logger.EtsyAdjust;
import com.etsy.android.lib.logger.EtsyFacebookTracker;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.logger.EventTracker;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.logger.p010a.EtsyGraphite;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.FavoriteUser;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ShopHomePage;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.ExternalAccountUtil.SignInFlow;
import com.etsy.android.lib.util.bf;
import com.etsy.android.lib.util.bh;
import com.etsy.android.uikit.util.AdHocEventCompatBuilder;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.etsy.android.ui.nav.c */
public class EtsyEventTracker extends EventTracker {
    public static void m4568c(@Nullable AnalyticsTracker analyticsTracker, String str) {
        new AdHocEventCompatBuilder("display_new_carts").m5515a("cart_view").m5516a(new EtsyEventTracker$1(str)).m5512a(AnalyticsLogAttribute.CART_IDS, (Object) str).m5513a(analyticsTracker).m5517a();
    }

    public static void m4560b(@Nullable AnalyticsTracker analyticsTracker, EtsyId etsyId) {
        new AdHocEventCompatBuilder("add_to_cart").m5515a("view_listing").m5516a(new EtsyEventTracker$2(etsyId)).m5511a(AnalyticsLogAttribute.LISTING_ID, etsyId).m5513a(analyticsTracker).m5517a();
        EtsyAdjust.m1880a();
        EtsyFacebookTracker.m1927a(etsyId);
    }

    public static void m4554a(@Nullable AnalyticsTracker analyticsTracker, List<FavoriteUser> list, boolean z) {
        int size = list.size();
        if (size > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (FavoriteUser userId : list) {
                stringBuilder.append(userId.getUserId().getId()).append(",");
            }
            String str = z ? "your_favorite_shops" : "profile_favorite_shops";
            new AdHocEventCompatBuilder("load_more_favorite_shops").m5515a(str).m5516a(new EtsyEventTracker$3(stringBuilder, size, str)).m5512a(AnalyticsLogAttribute.SHOP_IDS, stringBuilder.toString()).m5513a(analyticsTracker).m5517a();
        }
    }

    public static void m4550a(@Nullable AnalyticsTracker analyticsTracker, EtsyAction etsyAction) {
        new AdHocEventCompatBuilder("login_nag_displayed").m5515a("login_nag").m5516a(new EtsyEventTracker$4(etsyAction)).m5513a(analyticsTracker).m5512a(AnalyticsLogAttribute.REFERRER, etsyAction.getName()).m5517a();
    }

    public static void m4558b() {
        EtsyLogger.m1966a().m2001d("login_nag_sign_in_button_tapped", "login_nag");
    }

    public static void m4565c() {
        EtsyLogger.m1966a().m2001d("login_nag_dismissed", "login_nag");
    }

    public static void m4570d() {
        EtsyLogger.m1966a().m2001d("login_nag_register_button_tapped", "login_nag");
    }

    public static void m4574e() {
        EtsyLogger.m1966a().m2001d("login_nag_google_button_tapped", "login_nag");
    }

    public static void m4579f() {
        EtsyLogger.m1966a().m2001d("login_nag_facebook_button_tapped", "login_nag");
    }

    public static void m4586g(@Nullable AnalyticsTracker analyticsTracker, SignInFlow signInFlow) {
        EventTracker.m2014a(analyticsTracker, "sign_in_button_tapped", "login_view", signInFlow);
    }

    public static void m4590h(@Nullable AnalyticsTracker analyticsTracker, SignInFlow signInFlow) {
        EventTracker.m2014a(analyticsTracker, "sign_in_aborted", "login_view", signInFlow);
    }

    public static void m4594i(@Nullable AnalyticsTracker analyticsTracker, SignInFlow signInFlow) {
        EventTracker.m2014a(analyticsTracker, "register_button_tapped", "register_view", signInFlow);
    }

    public static void m4567c(@Nullable AnalyticsTracker analyticsTracker, SignInFlow signInFlow, HashMap<String, Object> hashMap) {
        EventTracker.m2015a(analyticsTracker, "registration_failed", "user_xauth", signInFlow, hashMap);
    }

    public static void m4575e(@Nullable AnalyticsTracker analyticsTracker) {
        new AdHocEventCompatBuilder("social_registration_failure").m5515a("login_nag").m5516a(new EtsyEventTracker$5()).m5512a(AnalyticsLogAttribute.ERROR_TYPE, (Object) "social_connect_general_error").m5513a(analyticsTracker).m5517a();
    }

    public static void m4597j(@Nullable AnalyticsTracker analyticsTracker, SignInFlow signInFlow) {
        EventTracker.m2014a(analyticsTracker, "register_aborted", "register_view", signInFlow);
    }

    public static void m4599k(@Nullable AnalyticsTracker analyticsTracker, SignInFlow signInFlow) {
        EventTracker.m2014a(analyticsTracker, "two_factor_submitted", "two_factor", signInFlow);
    }

    public static void m4601l(@Nullable AnalyticsTracker analyticsTracker, SignInFlow signInFlow) {
        EventTracker.m2014a(analyticsTracker, "two_factor_resend", "two_factor", signInFlow);
    }

    public static void m4602m(@Nullable AnalyticsTracker analyticsTracker, SignInFlow signInFlow) {
        EventTracker.m2014a(analyticsTracker, "two_factor_aborted", "two_factor", signInFlow);
    }

    public static void m4551a(@NonNull AnalyticsTracker analyticsTracker, @NonNull ShopHomePage shopHomePage) {
        new AdHocEventCompatBuilder("shop_home_complimentary").m5514a((ITrackedObject) shopHomePage).m5516a(bf.m3327b(shopHomePage)).m5515a("shop_home").m5516a(bf.m3327b(shopHomePage)).m5513a(analyticsTracker).m5517a();
    }

    public static void m4572d(@NonNull AnalyticsTracker analyticsTracker, @NonNull String str) {
        new AdHocEventCompatBuilder("selected_shop_items_sort").m5515a("shop_home").m5518b(str).m5513a(analyticsTracker).m5512a(AnalyticsLogAttribute.SORT_OPTION_ID, (Object) str).m5517a();
    }

    public static void m4566c(@NonNull AnalyticsTracker analyticsTracker, @NonNull EtsyId etsyId) {
        new AdHocEventCompatBuilder("selected_shop_section").m5515a("shop_home").m5518b(etsyId.toString()).m5511a(AnalyticsLogAttribute.SHOP_SECTION_ID, etsyId).m5513a(analyticsTracker).m5517a();
    }

    public static void m4577e(@Nullable AnalyticsTracker analyticsTracker, String str) {
        new AdHocEventCompatBuilder("searched").m5515a("shop_home").m5518b(str).m5513a(analyticsTracker).m5512a(AnalyticsLogAttribute.QUERY, (Object) str).m5517a();
    }

    public static void m4580f(@NonNull AnalyticsTracker analyticsTracker) {
        new AdHocEventCompatBuilder("cleared_search").m5515a("shop_home").m5513a(analyticsTracker).m5517a();
    }

    public static void m4571d(@NonNull AnalyticsTracker analyticsTracker, @NonNull EtsyId etsyId) {
        HashMap hashMap = new HashMap(1);
        hashMap.put(ResponseConstants.SHOP_ID, etsyId);
        new AdHocEventCompatBuilder("share_shop").m5515a("shop_home").m5516a(hashMap).m5511a(AnalyticsLogAttribute.SHOP_ID, etsyId).m5513a(analyticsTracker).m5517a();
    }

    public static void m4582f(@NonNull AnalyticsTracker analyticsTracker, @NonNull String str) {
        new AdHocEventCompatBuilder("clicked_tab_nav_" + str).m5515a("shop_home").m5513a(analyticsTracker).m5517a();
    }

    public static void m4587g(@NonNull AnalyticsTracker analyticsTracker, @NonNull String str) {
        new AdHocEventCompatBuilder("scrolled_to_shop_tab_section_" + str).m5515a("shop_home").m5513a(analyticsTracker).m5517a();
    }

    public static void m4584g(@NonNull AnalyticsTracker analyticsTracker) {
        new AdHocEventCompatBuilder("shop_announcement_view").m5515a("shop_home").m5513a(analyticsTracker).m5517a();
    }

    public static void m4589h(@NonNull AnalyticsTracker analyticsTracker) {
        new AdHocEventCompatBuilder("shop_announcement_hide").m5515a("shop_home").m5513a(analyticsTracker).m5517a();
    }

    public static void m4593i(@NonNull AnalyticsTracker analyticsTracker) {
        new AdHocEventCompatBuilder("load_more_listings").m5515a("shop_home").m5513a(analyticsTracker).m5517a();
    }

    public static void m4596j(@NonNull AnalyticsTracker analyticsTracker) {
        new AdHocEventCompatBuilder("shop_home_to_all_reviews").m5515a("shop_home").m5513a(analyticsTracker).m5517a();
    }

    public static void m4583g() {
        EtsyLogger.m1966a().m1997b("listing_tab_click", "view_listing", new EtsyEventTracker$7());
    }

    public static void m4588h() {
        EtsyLogger.m1966a().m2001d("click_edit_transaction_rating", "view_receipt");
    }

    public static void m4592i() {
        EtsyLogger.m1966a().m2001d("click_transaction_rating", "view_receipt");
    }

    public static void m4559b(int i) {
        EtsyLogger.m1966a().m1997b("submit_transaction_rating", "view_receipt", new EtsyEventTracker$8(i));
    }

    public static void m4552a(@Nullable AnalyticsTracker analyticsTracker, EtsyId etsyId, EtsyId etsyId2, boolean z) {
        new AdHocEventCompatBuilder("purchases_review").m5515a("purchases_review").m5516a(new EtsyEventTracker$9(z, etsyId2, etsyId)).m5513a(analyticsTracker).m5511a(AnalyticsLogAttribute.RECEIPT_ID, etsyId).m5511a(AnalyticsLogAttribute.TRANSACTION_ID, etsyId2).m5512a(AnalyticsLogAttribute.HAS_TRANSACTION_REVIEW, Boolean.valueOf(z)).m5517a();
    }

    public static void m4555a(EtsyId etsyId) {
        EtsyLogger.m1966a().m1990a("purchase_review_share_prompt_show", "view_receipt", new EtsyEventTracker$10(etsyId));
    }

    public static void m4563b(EtsyId etsyId) {
        EtsyLogger.m1966a().m1997b("purchase_review_share_click", "view_receipt", new EtsyEventTracker$11(etsyId));
    }

    public static void m4569c(EtsyId etsyId) {
        EtsyLogger.m1966a().m1997b("purchase_review_share_prompt_dismiss", "view_receipt", new EtsyEventTracker$12(etsyId));
    }

    public static void m4591h(EtsyId etsyId, String str) {
        EtsyLogger.m1966a().m1997b("appreciation_photo_show_click", str, new EtsyEventTracker$13(etsyId));
    }

    public static void m4573d(String str, String str2) {
        if (bh.m3340a(str2)) {
            new AdHocEventCompatBuilder("collection_create").m5515a(str2).m5516a(new EtsyEventTracker$14(str)).m5518b(str).m5512a(AnalyticsLogAttribute.COLLECTION_NAME, (Object) str).m5517a();
        }
    }

    public static void m4557a(String str, String str2, String str3, String str4) {
        if (bh.m3340a(str4)) {
            new AdHocEventCompatBuilder("list_edit").m5515a(str4).m5516a(new EtsyEventTracker$15(str, str2, str3)).m5518b(str).m5512a(AnalyticsLogAttribute.COLLECTION_KEY, (Object) str).m5512a(AnalyticsLogAttribute.COLLECTION_NAME, (Object) str2).m5512a(AnalyticsLogAttribute.PRIVACY_LEVEL, (Object) str3).m5517a();
        }
    }

    public static void m4578e(String str, String str2) {
        if (bh.m3340a(str2)) {
            new AdHocEventCompatBuilder("list_delete").m5515a(str2).m5516a(new EtsyEventTracker$16(str)).m5518b(str).m5512a(AnalyticsLogAttribute.COLLECTION_KEY, (Object) str).m5517a();
        }
    }

    public static void m4556a(EtsyId etsyId, String str, String str2) {
        if (bh.m3340a(str2)) {
            new AdHocEventCompatBuilder("collection_add").m5515a(str2).m5516a(new EtsyEventTracker$17(etsyId, str)).m5518b(etsyId.getId()).m5511a(AnalyticsLogAttribute.LISTING_ID, etsyId).m5512a(AnalyticsLogAttribute.COLLECTION_KEY, (Object) str).m5517a();
        }
    }

    public static void m4564b(EtsyId etsyId, String str, String str2) {
        if (bh.m3340a(str2)) {
            new AdHocEventCompatBuilder("collection_remove").m5515a(str2).m5516a(new EtsyEventTracker$18(etsyId, str)).m5518b(etsyId.getId()).m5511a(AnalyticsLogAttribute.LISTING_ID, etsyId).m5512a(AnalyticsLogAttribute.COLLECTION_KEY, (Object) str).m5517a();
        }
    }

    public static void m4595j() {
        EventTracker.m2039c("home");
    }

    public static void m4598k() {
        new AdHocEventCompatBuilder("deeplink_android_deferred").m5515a("deeplinking").m5517a();
    }

    public static void m4600l() {
        new AdHocEventCompatBuilder("deeplink_android_nondeferred").m5515a("deeplinking").m5517a();
    }

    public static void m4553a(@Nullable AnalyticsTracker analyticsTracker, String str, EtsyId etsyId, EtsyId etsyId2) {
        new AdHocEventCompatBuilder("finds_page").m5515a("finds_page").m5516a(new EtsyEventTracker$19(etsyId, etsyId2, str)).m5511a(AnalyticsLogAttribute.FINDS_PAGE_ID, etsyId).m5511a(AnalyticsLogAttribute.FINDS_PAGE_PUBLISHED_ID, etsyId2).m5512a(AnalyticsLogAttribute.FINDS_PAGE_SLUG, (Object) str).m5513a(analyticsTracker).m5517a();
    }

    public static void m4562b(@Nullable AnalyticsTracker analyticsTracker, String str, EtsyId etsyId, EtsyId etsyId2) {
        new AdHocEventCompatBuilder("scroll_to_bottom").m5515a("finds_page").m5516a(new EtsyEventTracker$20(etsyId, etsyId2, str)).m5511a(AnalyticsLogAttribute.FINDS_PAGE_ID, etsyId).m5511a(AnalyticsLogAttribute.FINDS_PAGE_PUBLISHED_ID, etsyId2).m5512a(AnalyticsLogAttribute.FINDS_PAGE_SLUG, (Object) str).m5513a(analyticsTracker).m5517a();
    }

    private static void m4561b(@Nullable AnalyticsTracker analyticsTracker, @NonNull EtsyId etsyId, @NonNull String str) {
        new AdHocEventCompatBuilder(str).m5515a(ResponseConstants.SHOP_ABOUT).m5516a(new EtsyEventTracker$21(etsyId)).m5511a(AnalyticsLogAttribute.SHOP_ID, etsyId).m5513a(analyticsTracker).m5517a();
    }

    public static void m4576e(@Nullable AnalyticsTracker analyticsTracker, EtsyId etsyId) {
        EtsyEventTracker.m4561b(analyticsTracker, etsyId, "shop_about_video_tapped");
    }

    public static void m4581f(@Nullable AnalyticsTracker analyticsTracker, EtsyId etsyId) {
        EtsyEventTracker.m4561b(analyticsTracker, etsyId, "shop_about_video_played");
    }

    public static void m4585g(@Nullable AnalyticsTracker analyticsTracker, EtsyId etsyId) {
        EtsyEventTracker.m4561b(analyticsTracker, etsyId, "shop_about_video_playback_error");
        EtsyGraphite.m1807a("shop.about.video.playback_error");
    }
}

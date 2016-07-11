package com.etsy.android.lib.logger;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.etsy.android.lib.messaging.EtsyNotification;
import com.etsy.android.lib.messaging.InboxStyleNotification;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.util.CurrencyUtil;
import com.etsy.android.lib.util.ExternalAccountUtil.SignInFlow;
import com.etsy.android.lib.util.bh;
import com.etsy.android.uikit.util.AdHocEventCompatBuilder;
import com.etsy.android.uikit.util.SocialShareUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* renamed from: com.etsy.android.lib.logger.q */
public class EventTracker {
    public static void m2020a(String str, @NonNull ad adVar) {
        new AdHocEventCompatBuilder("became_active").m5515a(str).m5513a((AnalyticsTracker) adVar).m5517a();
    }

    public static void m2025a(Throwable th) {
        EventTracker.m2024a("app", th);
    }

    public static void m2024a(String str, Throwable th) {
        EtsyLogger.m1966a().m1991a(str, th, true);
    }

    public static void m2007a(@Nullable AnalyticsTracker analyticsTracker, @NonNull EtsyId etsyId) {
        new AdHocEventCompatBuilder("listing_image_swipe").m5515a("view_listing").m5516a(new EventTracker$1(etsyId)).m5513a(analyticsTracker).m5511a(AnalyticsLogAttribute.LISTING_ID, etsyId).m5517a();
    }

    public static void m2015a(@Nullable AnalyticsTracker analyticsTracker, String str, String str2, SignInFlow signInFlow, HashMap<String, Object> hashMap) {
        hashMap.put("flow_type", signInFlow.toString());
        new AdHocEventCompatBuilder(str).m5515a(str2).m5516a((HashMap) hashMap).m5512a(AnalyticsLogAttribute.FLOW_TYPE, signInFlow.toString()).m5513a(analyticsTracker).m5517a();
    }

    public static void m2014a(@Nullable AnalyticsTracker analyticsTracker, String str, String str2, SignInFlow signInFlow) {
        EventTracker.m2015a(analyticsTracker, str, str2, signInFlow, new HashMap());
    }

    public static void m2019a(String str) {
        new AdHocEventCompatBuilder("signout").m5515a(str).m5517a();
    }

    public static void m2009a(@NonNull AnalyticsTracker analyticsTracker, SignInFlow signInFlow) {
        EventTracker.m2012a(analyticsTracker, "login_view", signInFlow);
    }

    public static void m2010a(@Nullable AnalyticsTracker analyticsTracker, SignInFlow signInFlow, HashMap<String, Object> hashMap) {
        EventTracker.m2013a(analyticsTracker, "user_xauth", signInFlow, (HashMap) hashMap);
    }

    public static void m2012a(@Nullable AnalyticsTracker analyticsTracker, String str, SignInFlow signInFlow) {
        EventTracker.m2014a(analyticsTracker, "login", str, signInFlow);
        EtsyAdjust.m1885d();
        EtsyFacebookTracker.m1932b(signInFlow);
    }

    public static void m2027b(@NonNull AnalyticsTracker analyticsTracker, SignInFlow signInFlow) {
        EventTracker.m2014a(analyticsTracker, "user_create", "register_view", signInFlow);
        EtsyAdjust.m1884c();
        EtsyFacebookTracker.m1928a(signInFlow);
    }

    public static void m2030b(@NonNull AnalyticsTracker analyticsTracker, String str, SignInFlow signInFlow) {
        HashMap hashMap = new HashMap();
        hashMap.put(ResponseConstants.SUGGESTIONS, str);
        EventTracker.m2015a(analyticsTracker, "username_suggestion", "register_view", signInFlow, hashMap);
    }

    public static void m2036c(@NonNull AnalyticsTracker analyticsTracker, SignInFlow signInFlow) {
        EventTracker.m2014a(analyticsTracker, "username_suggestion_too_late", "register_view", signInFlow);
    }

    public static void m2042d(@NonNull AnalyticsTracker analyticsTracker, SignInFlow signInFlow) {
        EventTracker.m2014a(analyticsTracker, "email_picked_autocomplete", "register_view", signInFlow);
    }

    public static void m2045e(@NonNull AnalyticsTracker analyticsTracker, SignInFlow signInFlow) {
        EventTracker.m2014a(analyticsTracker, "single_email_populated", "register_view", signInFlow);
    }

    public static void m2013a(@Nullable AnalyticsTracker analyticsTracker, String str, SignInFlow signInFlow, HashMap<String, Object> hashMap) {
        EventTracker.m2015a(analyticsTracker, "login_failed", str, signInFlow, hashMap);
    }

    public static void m2028b(@NonNull AnalyticsTracker analyticsTracker, SignInFlow signInFlow, HashMap<String, Object> hashMap) {
        EventTracker.m2015a(analyticsTracker, "two_factor_login_failed", "user_xauth", signInFlow, hashMap);
    }

    public static void m2047f(@NonNull AnalyticsTracker analyticsTracker, SignInFlow signInFlow) {
        EventTracker.m2037c(analyticsTracker, "login_view", signInFlow);
    }

    public static void m2037c(@NonNull AnalyticsTracker analyticsTracker, String str, SignInFlow signInFlow) {
        EventTracker.m2014a(analyticsTracker, "login_two_factor", str, signInFlow);
    }

    public static void m2005a(@Nullable View view) {
        new AdHocEventCompatBuilder("seller_details_convo").m5515a("shop_home").m5510a(view).m5517a();
    }

    public static void m2016a(@Nullable AnalyticsTracker analyticsTracker, List<? extends ListingLike> list, String str, int i) {
        if (bh.m3340a(str)) {
            int size = list.size();
            if (size > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (ListingLike listingId : list) {
                    stringBuilder.append(listingId.getListingId().getId()).append(",");
                }
                new AdHocEventCompatBuilder("load_more_listings").m5515a(str).m5516a(new EventTracker$2(stringBuilder, size, str, i)).m5512a(AnalyticsLogAttribute.LISTING_IDS, stringBuilder.toString()).m5512a(AnalyticsLogAttribute.TOTAL_RESULTS, Integer.valueOf(i)).m5512a(AnalyticsLogAttribute.LISTINGS_COUNT, Integer.valueOf(size)).m5513a(analyticsTracker).m5517a();
            }
        }
    }

    public static void m2032b(String str) {
        if (bh.m3340a(str)) {
            EtsyLogger.m1966a().m2001d("visit_shop_button_tapped", str);
        }
    }

    public static void m2008a(@NonNull AnalyticsTracker analyticsTracker, EtsyId etsyId, String str) {
        if (bh.m3340a(str)) {
            new AdHocEventCompatBuilder("selected_listing").m5515a(str).m5516a(new EventTracker$3(etsyId)).m5511a(AnalyticsLogAttribute.LISTING_ID, etsyId).m5513a(analyticsTracker).m5517a();
        }
    }

    public static void m2018a(EtsyId etsyId, String str) {
        if (etsyId != null && bh.m3340a(str)) {
            EtsyLogger.m1966a().m1987a("contact_button_tapped", str, etsyId.getId());
        }
    }

    public static void m2031b(EtsyId etsyId, String str) {
        if (bh.m3340a(str)) {
            new AdHocEventCompatBuilder("favorite_item").m5515a(str).m5516a(new EventTracker$4(etsyId)).m5518b(String.valueOf(etsyId)).m5511a(AnalyticsLogAttribute.LISTING_ID, etsyId).m5517a();
        }
        EtsyAdjust.m1883b();
        EtsyFacebookTracker.m1931b(etsyId);
    }

    public static void m2038c(EtsyId etsyId, String str) {
        if (bh.m3340a(str)) {
            new AdHocEventCompatBuilder("remove_favorite_item").m5515a(str).m5516a(new EventTracker$5(etsyId)).m5518b(String.valueOf(etsyId)).m5511a(AnalyticsLogAttribute.LISTING_ID, etsyId).m5517a();
        }
    }

    public static void m2022a(String str, String str2) {
        if (bh.m3340a(str2)) {
            new AdHocEventCompatBuilder("favorite_treasury").m5515a(str2).m5516a(new EventTracker$6(str)).m5518b(str).m5512a(AnalyticsLogAttribute.TREASURY_ID, (Object) str).m5517a();
        }
    }

    public static void m2034b(String str, String str2) {
        if (bh.m3340a(str2)) {
            new AdHocEventCompatBuilder("remove_favorite_treasury").m5515a(str2).m5516a(new EventTracker$7(str)).m5518b(str).m5512a(AnalyticsLogAttribute.TREASURY_ID, (Object) str).m5517a();
        }
    }

    public static void m2043d(EtsyId etsyId, String str) {
        if (bh.m3340a(str)) {
            new AdHocEventCompatBuilder("favorite_shop").m5515a(str).m5516a(new EventTracker$8(etsyId)).m5518b(String.valueOf(etsyId)).m5511a(AnalyticsLogAttribute.TARGET_USER_ID, etsyId).m5517a();
        }
    }

    public static void m2046e(EtsyId etsyId, String str) {
        if (bh.m3340a(str)) {
            new AdHocEventCompatBuilder("remove_favorite_shop").m5515a(str).m5516a(new EventTracker$9(etsyId)).m5518b(String.valueOf(etsyId)).m5511a(AnalyticsLogAttribute.TARGET_USER_ID, etsyId).m5517a();
        }
    }

    public static void m2048f(EtsyId etsyId, String str) {
        if (bh.m3340a(str)) {
            new AdHocEventCompatBuilder("circles_add").m5515a(str).m5516a(new EventTracker$10(etsyId)).m5518b(String.valueOf(etsyId)).m5511a(AnalyticsLogAttribute.TARGET_USER_ID, etsyId).m5517a();
        }
    }

    public static void m2049g(EtsyId etsyId, String str) {
        if (bh.m3340a(str)) {
            new AdHocEventCompatBuilder("circles_remove").m5515a(str).m5516a(new EventTracker$11(etsyId)).m5518b(String.valueOf(etsyId)).m5511a(AnalyticsLogAttribute.TARGET_USER_ID, etsyId).m5517a();
        }
    }

    public static void m2039c(String str) {
        new AdHocEventCompatBuilder("upgrade_promo_shown").m5515a(str).m5517a();
    }

    public static void m2011a(@Nullable AnalyticsTracker analyticsTracker, String str) {
        Locale locale = Locale.getDefault();
        new AdHocEventCompatBuilder("set_locale_preferences").m5515a(str).m5516a(new EventTracker$12(locale)).m5512a(AnalyticsLogAttribute.LANGUAGE, locale.getLanguage()).m5512a(AnalyticsLogAttribute.CURRENCY, CurrencyUtil.m3091i()).m5513a(analyticsTracker).m5517a();
    }

    public static void m2040c(String str, String str2) {
        new AdHocEventCompatBuilder(str).m5515a(str2).m5517a();
    }

    public static void m2021a(String str, AnalyticsTracker analyticsTracker) {
        new AdHocEventCompatBuilder("opt_out_push").m5513a(analyticsTracker).m5512a(AnalyticsLogAttribute.NOTIFICATION_TYPE, (Object) str).m5517a();
    }

    public static void m2033b(String str, AnalyticsTracker analyticsTracker) {
        new AdHocEventCompatBuilder("dismiss_opt_out_banner").m5513a(analyticsTracker).m5512a(AnalyticsLogAttribute.NOTIFICATION_TYPE, (Object) str).m5517a();
    }

    public static void m2017a(EtsyNotification etsyNotification, String str) {
        if (etsyNotification != null) {
            if (!(etsyNotification instanceof InboxStyleNotification) || etsyNotification.m2109c() <= 0) {
                EventTracker.m2023a(etsyNotification.m2110d().getName(), etsyNotification.m2112f(), str);
                return;
            }
            for (InboxStyleNotification c : ((InboxStyleNotification) etsyNotification).m2131m()) {
                EventTracker.m2023a(etsyNotification.m2110d().getName(), c.m2290c(), str);
            }
        }
    }

    public static void m2023a(String str, String str2, String str3) {
        new AdHocEventCompatBuilder("notification_opened").m5515a("your_notifications").m5516a(new EventTracker$13(str, str2, str3)).m5512a(AnalyticsLogAttribute.NOTIFICATION_ID, (Object) str2).m5512a(AnalyticsLogAttribute.NOTIFICATION_TYPE, (Object) str).m5512a(AnalyticsLogAttribute.LANDING_PAGE_TYPE, (Object) str3).m5517a();
    }

    public static void m2004a(int i) {
        new AdHocEventCompatBuilder("google_connection_error").m5515a("login_nag").m5516a(new EventTracker$14(i)).m5512a(AnalyticsLogAttribute.GOOGLE_ERROR_CODE, Integer.valueOf(i)).m5517a();
    }

    public static void m2006a(@Nullable AnalyticsTracker analyticsTracker) {
        new AdHocEventCompatBuilder("toast_shown").m5515a("social_share_sheet").m5516a(SocialShareUtil.m5155a()).m5513a(analyticsTracker).m5517a();
    }

    public static void m2026b(@Nullable AnalyticsTracker analyticsTracker) {
        new AdHocEventCompatBuilder("toast_ignored").m5515a("social_share_sheet").m5516a(SocialShareUtil.m5155a()).m5513a(analyticsTracker).m5517a();
    }

    public static void m2003a() {
        EtsyLogger.m1966a().m1997b("toast_tapped", "social_share_sheet", SocialShareUtil.m5155a());
    }

    public static void m2035c(@Nullable AnalyticsTracker analyticsTracker) {
        new AdHocEventCompatBuilder("share_sheet_shown").m5515a("social_share_sheet").m5516a(SocialShareUtil.m5155a()).m5513a(analyticsTracker).m5517a();
    }

    public static void m2041d(@Nullable AnalyticsTracker analyticsTracker) {
        new AdHocEventCompatBuilder("share_sheet_dismissed").m5515a("social_share_sheet").m5516a(SocialShareUtil.m5155a()).m5513a(analyticsTracker).m5517a();
    }

    public static void m2044d(String str) {
        HashMap a = SocialShareUtil.m5155a();
        a.put("si_channel", str);
        EtsyLogger.m1966a().m1997b("action_tapped", "social_share_sheet", a);
    }

    public static void m2029b(@Nullable AnalyticsTracker analyticsTracker, String str) {
        HashMap a = SocialShareUtil.m5155a();
        a.put("si_channel", str);
        new AdHocEventCompatBuilder("action_failure").m5515a("social_share_sheet").m5516a(a).m5512a(AnalyticsLogAttribute.SI_CHANNEL, (Object) str).m5513a(analyticsTracker).m5517a();
    }
}

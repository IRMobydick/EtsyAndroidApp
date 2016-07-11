package com.etsy.android.ui.cart;

import android.support.annotation.Nullable;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.logger.EtsyAdjust;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyFacebookTracker;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.ReceiptsRequest;
import com.etsy.android.uikit.util.AdHocEventCompatBuilder;
import com.google.ads.conversiontracking.C1058a;

/* renamed from: com.etsy.android.ui.cart.a */
public class CartEventTracker {
    public static void m3718a(@Nullable AnalyticsTracker analyticsTracker, Cart cart, String str) {
        if (cart != null) {
            new AdHocEventCompatBuilder("changed_note_to_seller").m5515a(str).m5509a((long) cart.getCartId()).m5516a(new CartEventTracker$1(cart)).m5514a((ITrackedObject) cart).m5513a(analyticsTracker).m5517a();
        }
    }

    public static void m3717a(@Nullable AnalyticsTracker analyticsTracker, Cart cart, EtsyId etsyId) {
        if (cart != null && etsyId != null) {
            new AdHocEventCompatBuilder(ReceiptsRequest.STATUS_COMPLETED).m5515a("cart_payment").m5516a(new CartEventTracker$2(etsyId, cart)).m5509a(etsyId.getIdAsLong()).m5514a((ITrackedObject) cart).m5511a(AnalyticsLogAttribute.RECEIPT_ID, etsyId).m5513a(analyticsTracker).m5517a();
            try {
                EtsyAdjust.m1881a(cart.getTotal().getAmount().doubleValue(), cart.getTotal().getCurrency().getCurrencyCode());
            } catch (NumberFormatException e) {
                EtsyDebug.m1894a(new RuntimeException("Cart total was not a double"));
            }
            EtsyFacebookTracker.m1926a(cart, etsyId);
            C1058a.m5803a(EtsyApplication.get(), "995917074", "MnVaCK-77VwQkvrx2gM", cart.getTotal().getAmount().toString(), cart.getTotal().getCurrency().getCurrencyCode(), false);
        }
    }

    public static void m3721a(@Nullable AnalyticsTracker analyticsTracker, Cart cart, String str, String str2) {
        if (cart != null) {
            new AdHocEventCompatBuilder("coupon_invalid").m5515a(str).m5516a(new CartEventTracker$3(cart, str2)).m5509a((long) cart.getCartId()).m5514a((ITrackedObject) cart).m5512a(AnalyticsLogAttribute.COUPON_CODE, (Object) str2).m5513a(analyticsTracker).m5517a();
        }
    }

    public static void m3725b(@Nullable AnalyticsTracker analyticsTracker, Cart cart, String str, String str2) {
        if (cart != null) {
            new AdHocEventCompatBuilder("coupon_valid").m5515a(str).m5509a((long) cart.getCartId()).m5516a(new CartEventTracker$4(cart, str2)).m5514a((ITrackedObject) cart).m5512a(AnalyticsLogAttribute.COUPON_CODE, (Object) str2).m5513a(analyticsTracker).m5517a();
        }
    }

    public static void m3723b(@Nullable AnalyticsTracker analyticsTracker, Cart cart, String str) {
        if (cart != null) {
            new AdHocEventCompatBuilder("deleted").m5515a(str).m5509a((long) cart.getCartId()).m5516a(new CartEventTracker$5(cart)).m5514a((ITrackedObject) cart).m5513a(analyticsTracker).m5517a();
        }
    }

    public static void m3727c(@Nullable AnalyticsTracker analyticsTracker, Cart cart, String str) {
        if (cart != null) {
            new AdHocEventCompatBuilder("selected_shop_delete").m5515a(str).m5509a((long) cart.getCartId()).m5516a(new CartEventTracker$6(cart)).m5514a((ITrackedObject) cart).m5513a(analyticsTracker).m5517a();
        }
    }

    public static void m3730d(@Nullable AnalyticsTracker analyticsTracker, Cart cart, String str) {
        if (cart != null) {
            new AdHocEventCompatBuilder("delete_all_shop_items").m5515a(str).m5509a((long) cart.getCartId()).m5516a(new CartEventTracker$7(cart)).m5514a((ITrackedObject) cart).m5513a(analyticsTracker).m5517a();
        }
    }

    public static void m3731e(@Nullable AnalyticsTracker analyticsTracker, Cart cart, String str) {
        if (cart != null) {
            Object type;
            AdHocEventCompatBuilder a = new AdHocEventCompatBuilder("enter_checkout").m5515a(str).m5509a((long) cart.getCartId()).m5516a(new CartEventTracker$8(cart)).m5514a((ITrackedObject) cart);
            AnalyticsLogAttribute analyticsLogAttribute = AnalyticsLogAttribute.PAYMENT_METHOD;
            if (cart.getLastPaymentMethod() != null) {
                type = cart.getLastPaymentMethod().getType();
            } else {
                type = null;
            }
            a.m5512a(analyticsLogAttribute, type).m5513a(analyticsTracker).m5517a();
        }
    }

    public static void m3732f(@Nullable AnalyticsTracker analyticsTracker, Cart cart, String str) {
        if (cart != null) {
            new AdHocEventCompatBuilder("checkout_not_logged_in").m5515a(str).m5509a((long) cart.getCartId()).m5516a(new CartEventTracker$9(cart)).m5514a((ITrackedObject) cart).m5513a(analyticsTracker).m5517a();
        }
    }

    public static void m3722a(Cart cart, String str) {
        if (cart != null) {
            new AdHocEventCompatBuilder("load_full_wallet_start").m5515a(str).m5509a((long) cart.getCartId()).m5516a(new CartEventTracker$10(cart)).m5514a((ITrackedObject) cart).m5517a();
        }
    }

    public static void m3719a(@Nullable AnalyticsTracker analyticsTracker, Cart cart, String str, int i) {
        if (cart != null) {
            new AdHocEventCompatBuilder("load_full_wallet_complete").m5515a(str).m5509a((long) cart.getCartId()).m5516a(new CartEventTracker$11(cart, i)).m5514a((ITrackedObject) cart).m5512a(AnalyticsLogAttribute.ACTIVITY_RESULT_CODE, Integer.valueOf(i)).m5513a(analyticsTracker).m5517a();
        }
    }

    public static void m3726b(Cart cart, String str) {
        if (cart != null) {
            new AdHocEventCompatBuilder("load_masked_wallet_start").m5515a(str).m5509a((long) cart.getCartId()).m5516a(new CartEventTracker$12(cart)).m5514a((ITrackedObject) cart).m5517a();
        }
    }

    public static void m3724b(@Nullable AnalyticsTracker analyticsTracker, Cart cart, String str, int i) {
        if (cart != null) {
            new AdHocEventCompatBuilder("load_masked_wallet_complete").m5515a(str).m5509a((long) cart.getCartId()).m5516a(new CartEventTracker$13(cart, i)).m5514a((ITrackedObject) cart).m5512a(AnalyticsLogAttribute.ACTIVITY_RESULT_CODE, Integer.valueOf(i)).m5513a(analyticsTracker).m5517a();
        }
    }

    public static void m3733g(@Nullable AnalyticsTracker analyticsTracker, Cart cart, String str) {
        if (cart != null) {
            new AdHocEventCompatBuilder("remove_giftcard").m5515a(str).m5509a((long) cart.getCartId()).m5516a(new CartEventTracker$14(cart)).m5514a((ITrackedObject) cart).m5513a(analyticsTracker).m5517a();
        }
    }

    public static void m3720a(@Nullable AnalyticsTracker analyticsTracker, Cart cart, String str, int i, String str2, String str3) {
        if (cart != null) {
            new AdHocEventCompatBuilder("country_tapped").m5515a(str).m5509a((long) cart.getCartId()).m5516a(new CartEventTracker$15(cart, i, str2, str3)).m5514a((ITrackedObject) cart).m5512a(AnalyticsLogAttribute.COUNTRY_ID, Integer.valueOf(i)).m5512a(AnalyticsLogAttribute.SHIPPING_OPTION_ID, (Object) str3).m5512a(AnalyticsLogAttribute.POSTAL_CODE, (Object) str2).m5513a(analyticsTracker).m5517a();
        }
    }

    public static void m3729c(@Nullable AnalyticsTracker analyticsTracker, Cart cart, String str, String str2) {
        if (cart != null) {
            new AdHocEventCompatBuilder("changed_payment_method").m5515a(str).m5509a((long) cart.getCartId()).m5516a(new CartEventTracker$16(cart, str2)).m5514a((ITrackedObject) cart).m5512a(AnalyticsLogAttribute.PAYMENT_METHOD, (Object) str2).m5513a(analyticsTracker).m5517a();
        }
    }

    public static void m3728c(@Nullable AnalyticsTracker analyticsTracker, Cart cart, String str, int i) {
        if (cart != null) {
            new AdHocEventCompatBuilder("quantity_updated").m5515a(str).m5509a((long) cart.getCartId()).m5516a(new CartEventTracker$17(cart, i)).m5514a((ITrackedObject) cart).m5512a(AnalyticsLogAttribute.QUANTITY, Integer.valueOf(i)).m5513a(analyticsTracker).m5517a();
        }
    }
}

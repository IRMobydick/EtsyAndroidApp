package com.etsy.android.lib.logger;

import android.content.Context;
import android.os.Bundle;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.core.EtsyJobBuilder;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.ad;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.logger.p010a.EtsyGraphite;
import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.models.CartListing;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.CartsRequest;
import com.etsy.android.lib.util.ArrayUtil;
import com.etsy.android.lib.util.ExternalAccountUtil.SignInFlow;
import com.facebook.appevents.AppEventsLogger;
import com.fasterxml.jackson.core.JsonProcessingException;

/* renamed from: com.etsy.android.lib.logger.i */
public class EtsyFacebookTracker {
    private static boolean f1779a;

    /* renamed from: com.etsy.android.lib.logger.i.1 */
    final class EtsyFacebookTracker implements EtsyJobBuilder<Cart> {
        final /* synthetic */ Cart f1777a;
        final /* synthetic */ EtsyId f1778b;

        /* renamed from: com.etsy.android.lib.logger.i.1.1 */
        class EtsyFacebookTracker implements ArrayUtil<CartListing, String> {
            final /* synthetic */ EtsyFacebookTracker f1776a;

            EtsyFacebookTracker(EtsyFacebookTracker etsyFacebookTracker) {
                this.f1776a = etsyFacebookTracker;
            }

            public String m1923a(CartListing cartListing) {
                return cartListing.isValid() ? cartListing.getListingId().getId() : null;
            }
        }

        EtsyFacebookTracker(Cart cart, EtsyId etsyId) {
            this.f1777a = cart;
            this.f1778b = etsyId;
        }

        public void m1924a(EtsyResult<Cart> etsyResult) {
            Cart cart;
            if (!etsyResult.m1049a() || etsyResult.m1056g().isEmpty()) {
                cart = this.f1777a;
                EtsyGraphite.m1807a("appgrowth.fb_analytics.cart_fetch_error");
            } else {
                cart = (Cart) etsyResult.m1056g().get(0);
                if (this.f1777a.getInvalidCartListings().size() != cart.getInvalidCartListings().size()) {
                    EtsyGraphite.m1807a("appgrowth.fb_analytics.cart_state_different");
                }
            }
            Bundle bundle = new Bundle();
            bundle.putString("fb_content_type", "product");
            try {
                bundle.putString("fb_content_id", ad.m1081a().m1083b().writeValueAsString(ArrayUtil.m3308a(cart.getCartListings(), new EtsyFacebookTracker(this))));
            } catch (JsonProcessingException e) {
                EtsyLogger.m1966a().m1996b("EtsyFacebookTracker", "Error parsing listing ids from cart");
            }
            bundle.putString("fb_order_id", this.f1778b.getId());
            AppEventsLogger.c(EtsyApplication.get()).a(cart.getTotal().getAmount(), cart.getTotal().getCurrency(), bundle);
        }
    }

    static {
        f1779a = false;
    }

    public static void m1927a(EtsyId etsyId) {
        if (EtsyFacebookTracker.m1929a()) {
            Bundle bundle = new Bundle();
            bundle.putString("fb_content_type", "product");
            bundle.putString("fb_content_id", etsyId.getId());
            AppEventsLogger.c(EtsyApplication.get()).a("fb_mobile_add_to_cart", bundle);
        }
    }

    public static void m1931b(EtsyId etsyId) {
        if (EtsyFacebookTracker.m1929a()) {
            Bundle bundle = new Bundle();
            bundle.putString("fb_content_type", "product");
            bundle.putString("fb_content_id", etsyId.getId());
            AppEventsLogger.c(EtsyApplication.get()).a("fb_mobile_add_to_wishlist", bundle);
        }
    }

    public static void m1926a(Cart cart, EtsyId etsyId) {
        if (EtsyFacebookTracker.m1929a()) {
            aj.m1101a().m1123i().m1699a(EtsyJobBuilder.m1307a(CartsRequest.getUserCart(cart.getCartId())).m1317a(new EtsyFacebookTracker(cart, etsyId)).m1324a());
        }
    }

    public static void m1928a(SignInFlow signInFlow) {
        if (EtsyFacebookTracker.m1929a()) {
            Bundle bundle = new Bundle();
            bundle.putString("fb_registration_method", signInFlow.toString());
            AppEventsLogger.c(EtsyApplication.get()).a("fb_mobile_complete_registration", bundle);
        }
    }

    public static void m1932b(SignInFlow signInFlow) {
        if (EtsyFacebookTracker.m1929a()) {
            Bundle bundle = new Bundle();
            bundle.putString("sign_in_method", signInFlow.toString());
            AppEventsLogger.c(EtsyApplication.get()).a("sign_in", bundle);
        }
    }

    public static void m1934c(EtsyId etsyId) {
        if (EtsyFacebookTracker.m1929a()) {
            Bundle bundle = new Bundle();
            bundle.putString("fb_content_type", "product");
            bundle.putString("fb_content_id", etsyId.getId());
            AppEventsLogger.c(EtsyApplication.get()).a("fb_mobile_content_view", bundle);
        }
    }

    public static void m1925a(Context context) {
        if (EtsyFacebookTracker.m1929a()) {
            f1779a = true;
            AppEventsLogger.a(context.getApplicationContext());
        }
    }

    public static void m1930b(Context context) {
        if (EtsyFacebookTracker.m1929a() || f1779a) {
            f1779a = false;
            AppEventsLogger.b(context.getApplicationContext());
        }
    }

    private static boolean m1929a() {
        return EtsyFacebookTracker.m1933b() ? EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.bt) : true;
    }

    private static boolean m1933b() {
        try {
            return EtsyConfig.m837a().m871e();
        } catch (IllegalStateException e) {
            return false;
        }
    }
}

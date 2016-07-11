package com.etsy.android.ui.cart.p015a;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.CartListing;
import com.etsy.android.ui.cart.aa;
import com.etsy.android.ui.nav.Nav;

/* renamed from: com.etsy.android.ui.cart.a.b */
public class CartListingClickHandler extends CartGroupActionClickHandler {
    public CartListingClickHandler(@NonNull aa aaVar, String str, @NonNull FragmentActivity fragmentActivity, @NonNull AnalyticsTracker analyticsTracker) {
        super(aaVar, str, fragmentActivity, analyticsTracker);
    }

    public void m3714a(@NonNull CartGroupItem cartGroupItem) {
        m3715a((CartListing) cartGroupItem.getData());
    }

    public void m3715a(@NonNull CartListing cartListing) {
        Nav.m4682a(this.c).m4683a().m4467a(cartListing.getListingId());
    }
}

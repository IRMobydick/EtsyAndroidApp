package com.etsy.android.ui.cart.p015a;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.models.apiv3.cart.CartGroupAction;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.ui.cart.aa;
import com.etsy.android.uikit.viewholder.a.b;

/* renamed from: com.etsy.android.ui.cart.a.a */
public class CartGroupActionClickHandler extends b<CartGroupItem> {
    private final aa f2391a;

    public CartGroupActionClickHandler(@NonNull aa aaVar, String str, FragmentActivity fragmentActivity, @NonNull AnalyticsTracker analyticsTracker) {
        super(str, fragmentActivity, analyticsTracker);
        this.f2391a = aaVar;
    }

    public void m3711a(int i, @NonNull CartGroupAction cartGroupAction) {
        this.f2391a.performAction(i, cartGroupAction);
    }

    public void m3712a(CartGroupItem cartGroupItem) {
    }
}

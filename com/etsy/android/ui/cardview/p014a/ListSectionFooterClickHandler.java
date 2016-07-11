package com.etsy.android.ui.cardview.p014a;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.logger.ShopShareEventTracker;
import com.etsy.android.lib.models.homescreen.LandingPageInfo;
import com.etsy.android.lib.models.homescreen.LandingPageLink;
import com.etsy.android.ui.cart.SavedCartItemsFragment;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.viewholder.a.b;
import java.util.HashMap;

/* renamed from: com.etsy.android.ui.cardview.a.e */
public class ListSectionFooterClickHandler extends b<LandingPageLink> {
    public ListSectionFooterClickHandler(String str, FragmentActivity fragmentActivity, @NonNull AnalyticsTracker analyticsTracker) {
        super(str, fragmentActivity, analyticsTracker);
    }

    public void m3577a(LandingPageLink landingPageLink) {
        m3579b(landingPageLink);
    }

    public void m3579b(LandingPageLink landingPageLink) {
        if (this.b != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(SavedCartItemsFragment.PAGE, landingPageLink.getEventName());
            EtsyLogger.m1966a().m1997b("homescreen_tapped_view_all", this.b, hashMap);
        }
        if (landingPageLink.getPageType().equals(LandingPageLink.PAGE_TYPE_RECENTLY_VIEWED_LISTINGS)) {
            landingPageLink.setRequestMethod(1);
            Nav.m4682a(this.c).m4683a().m4505b((LandingPageInfo) landingPageLink);
        } else if (landingPageLink.getPageType().equals(LandingPageLink.PAGE_TYPE_LISTINGS) || landingPageLink.getPageType().equals(LandingPageLink.PAGE_TYPE_SHOPS)) {
            Nav.m4682a(this.c).m4683a().m4478a((LandingPageInfo) landingPageLink);
        } else if (landingPageLink.getPageType().equals(LandingPageLink.PAGE_TYPE_SHOP_SHARES)) {
            ShopShareEventTracker.m2092b("shop_more_photos.tapped", this.b);
            Nav.m4682a(this.c).m4683a().m4513c((LandingPageInfo) landingPageLink);
        }
    }
}

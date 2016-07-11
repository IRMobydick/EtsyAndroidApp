package com.etsy.android.ui.cardview.p014a;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.logger.ShopShareEventTracker;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.homescreen.LandingPageLink;
import com.etsy.android.lib.models.homescreen.MessageCard;
import com.etsy.android.lib.util.ay;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.viewholder.a.b;
import java.util.HashMap;

/* renamed from: com.etsy.android.ui.cardview.a.g */
public class MessageCardClickHandler extends b<MessageCard> {
    public MessageCardClickHandler(String str, FragmentActivity fragmentActivity, @NonNull AnalyticsTracker analyticsTracker) {
        super(str, fragmentActivity, analyticsTracker);
    }

    public void m3590a(MessageCard messageCard) {
        m3592b(messageCard);
    }

    public void m3592b(MessageCard messageCard) {
        if (messageCard != null) {
            if (!(this.b == null || messageCard.getLink().equals("trending_items"))) {
                HashMap hashMap = new HashMap();
                hashMap.put(ResponseConstants.LINK_PATH, messageCard.getLink());
                EtsyLogger.m1966a().m1997b("homescreen_tapped_message_link", this.b, hashMap);
            }
            if (messageCard.getLink().endsWith("search")) {
                Nav.m4682a(this.c).m4683a().m4540n();
            } else if (messageCard.getLink().startsWith("homescreen/")) {
                Nav.m4682a(this.c).m4683a().m4514c(messageCard.getLink());
            } else if (messageCard.getLink().equals("browse")) {
                Nav.m4682a(this.c).m4683a().m4540n();
            } else if (messageCard.getLink().equals("trending_items")) {
                r0 = new LandingPageLink();
                r0.setPageType(LandingPageLink.PAGE_TYPE_LISTINGS);
                r0.setApiPath("/etsyapps/v3/public/trending-listing-cards?limit=30");
                r0.setPageTitle(this.c.getResources().getString(R.trending_items_title));
                Nav.m4682a(this.c).m4683a().m4478a(r0);
            } else if (messageCard.getLink().equals("shop_shares")) {
                ShopShareEventTracker.m2092b("promo_banner_tapped", this.b);
                ay.m3290b("shop-share-mobile-promo");
                r0 = new LandingPageLink();
                r0.setPageType(LandingPageLink.PAGE_TYPE_SHOP_SHARES);
                r0.setApiPath("/etsyapps/v3/bespoke/member/shop/shares/activity?limit=10");
                r0.setPageTitle(messageCard.getLinkTitle());
                r0.setOption("show_onboarding", true);
                Nav.m4682a(this.c).m4683a().m4513c(r0);
            }
        }
    }
}

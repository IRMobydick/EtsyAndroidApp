package com.etsy.android.ui.cardview.p014a;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.interfaces.BasicShopLike;
import com.etsy.android.ui.nav.EtsyActivityNavigator;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.ui.util.FavoriteUtil;
import com.etsy.android.uikit.viewholder.a.b;
import java.util.HashMap;

/* renamed from: com.etsy.android.ui.cardview.a.i */
public class ShopCardClickHandler extends b<ShopCard> {
    private FavoriteUtil f2245a;

    public /* synthetic */ void m3621a(Object obj) {
        m3624b((ShopCard) obj);
    }

    public ShopCardClickHandler(String str, FragmentActivity fragmentActivity, @NonNull AnalyticsTracker analyticsTracker) {
        super(str, fragmentActivity, analyticsTracker);
        this.f2245a = new FavoriteUtil(this.c, null, str, analyticsTracker);
    }

    public void m3618a(ShopCard shopCard) {
        if (this.b != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(ResponseConstants.SHOP_ID, shopCard.getShopId().getId());
            EtsyLogger.m1966a().m1997b("homescreen_tapped_shop", this.b, hashMap);
        }
        Nav.m4682a(this.c).m4683a().m4501b(shopCard.getShopId());
    }

    public void m3624b(ShopCard shopCard) {
        m3618a(shopCard);
    }

    public void m3617a(int i) {
    }

    public boolean m3623a() {
        return false;
    }

    public void m3619a(ShopCard shopCard, ImageView imageView, boolean z) {
        EtsyId shopId = shopCard.getShopId();
        if (aj.m1101a().m1118d()) {
            this.f2245a.m5168a(imageView, R.ic_listing_unfavorite_selector, R.ic_listing_favorite_selector, z);
            this.f2245a.m5171a((BasicShopLike) shopCard, null, z);
            shopCard.setIsFavorite(!z);
            return;
        }
        ((EtsyActivityNavigator) Nav.m4681a(this.c).m3012a((View) imageView)).m4453a(EtsyAction.FAVORITE, shopId.getId());
    }

    public void m3622a(String str) {
        if (aj.m1101a().m1118d()) {
            Bundle bundle = new Bundle();
            bundle.putString("username", str);
            Nav.m4681a(this.c).m4516d(bundle);
            return;
        }
        Nav.m4681a(this.c).m4453a(EtsyAction.CONTACT_USER, str);
    }

    public void m3620a(EtsyId etsyId) {
        Nav.m4682a(this.c).m4683a().m4512c(etsyId, null);
    }
}

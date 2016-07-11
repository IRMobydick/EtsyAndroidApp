package com.etsy.android.ui.cardview.p014a;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.HttpRequestJobBuilder;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.logger.ShopShareEventTracker;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.models.shopshare.ShareAnnotation;
import com.etsy.android.lib.models.shopshare.ShopShareCard;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.adapter.AnnotationAdapter;
import com.etsy.android.uikit.cardview.ICardViewAdapter;
import com.etsy.android.uikit.view.TaggableImageView;
import com.etsy.android.uikit.viewholder.a.b;
import com.etsy.android.util.e;
import java.util.List;

/* renamed from: com.etsy.android.ui.cardview.a.j */
public class ShopShareCardClickHandler extends b<ShopShareCard> {
    private ICardViewAdapter f2252a;

    /* renamed from: com.etsy.android.ui.cardview.a.j.1 */
    class ShopShareCardClickHandler implements EtsyJobResponse {
        final /* synthetic */ String f2246a;
        final /* synthetic */ ShopShareCardClickHandler f2247b;

        ShopShareCardClickHandler(ShopShareCardClickHandler shopShareCardClickHandler, String str) {
            this.f2247b = shopShareCardClickHandler;
            this.f2246a = str;
        }

        public void m3625a(int i, String str, EtsyResult etsyResult) {
            ShopShareEventTracker.m2089a(String.format("feed.%s.%s", new Object[]{this.f2246a, "failure"}), this.f2247b.b);
        }
    }

    /* renamed from: com.etsy.android.ui.cardview.a.j.2 */
    class ShopShareCardClickHandler implements EtsyJobResponse {
        final /* synthetic */ String f2248a;
        final /* synthetic */ ShopShareCardClickHandler f2249b;

        ShopShareCardClickHandler(ShopShareCardClickHandler shopShareCardClickHandler, String str) {
            this.f2249b = shopShareCardClickHandler;
            this.f2248a = str;
        }

        public void m3626a(EtsyResult etsyResult) {
            ShopShareEventTracker.m2089a(String.format("feed.%s.%s", new Object[]{this.f2248a, BaseMessage.TYPE_SUCCESS}), this.f2249b.b);
        }
    }

    /* renamed from: com.etsy.android.ui.cardview.a.j.3 */
    class ShopShareCardClickHandler implements EtsyJobResponse<EmptyResult> {
        final /* synthetic */ String f2250a;
        final /* synthetic */ ShopShareCardClickHandler f2251b;

        ShopShareCardClickHandler(ShopShareCardClickHandler shopShareCardClickHandler, String str) {
            this.f2251b = shopShareCardClickHandler;
            this.f2250a = str;
        }

        public void m3627a(List<EmptyResult> list, int i, EtsyResult<EmptyResult> etsyResult) {
            ShopShareEventTracker.m2089a(String.format("feed.%s.%s", new Object[]{this.f2250a, BaseMessage.TYPE_SUCCESS}), this.f2251b.b);
        }
    }

    public ShopShareCardClickHandler(String str, FragmentActivity fragmentActivity, ICardViewAdapter iCardViewAdapter, @NonNull AnalyticsTracker analyticsTracker) {
        super(str, fragmentActivity, analyticsTracker);
        this.f2252a = iCardViewAdapter;
    }

    public void m3634a(ShopShareCard shopShareCard) {
    }

    public void m3635a(TaggableImageView taggableImageView, ShopShareCard shopShareCard) {
        ShareAnnotation d = ((AnnotationAdapter) taggableImageView).m5306d();
        ShopShareEventTracker.m2091a("tag.tapped", this.b, ShopShareEventTracker.m2088a(shopShareCard.getShareItem()));
        Nav.m4682a(this.c).m4683a().m4470a(d.getObjectId(), m3630b());
    }

    public void m3638b(ShopShareCard shopShareCard) {
        ShopShareEventTracker.m2091a("photo.tapped", this.b, ShopShareEventTracker.m2088a(shopShareCard.getShareItem()));
        if (this.b.equals("homescreen_recommended")) {
            Nav.m4682a(this.c).m4683a().m4513c(shopShareCard.getLandingPage());
            return;
        }
        Nav.m4682a(this.c).m4683a().m4470a(shopShareCard.getShareItem().getPrimaryAnnotation().getObjectId(), m3630b());
    }

    public void m3639c(ShopShareCard shopShareCard) {
        e.a(this.c, this.d, shopShareCard.getShareItem().getShareId());
    }

    public void m3633a(int i, ShopShareCard shopShareCard) {
        m3629a(i, shopShareCard, ResponseConstants.OBJECT);
        this.f2252a.onRemoveItem(i);
    }

    public void m3637b(int i, ShopShareCard shopShareCard) {
        m3629a(i, shopShareCard, ResponseConstants.SUBJECT);
        this.f2252a.onRemoveItem(i);
    }

    public void m3640d(ShopShareCard shopShareCard) {
        ShopShareEventTracker.m2092b("shop.tapped", this.b);
        EtsyId ownerId = shopShareCard.getOwnerId();
        if (ownerId == null) {
            ownerId = ((ListingCard) shopShareCard.getShareItem().getPrimaryAnnotation().getObject()).getShopId();
        }
        Nav.m4682a(this.c).m4683a().m4502b(ownerId, m3630b());
    }

    private Bundle m3630b() {
        Bundle bundle = new Bundle();
        bundle.putString("shop_stats_referrer", "shop_updates");
        Bundle bundle2 = new Bundle();
        bundle2.putBundle("referrer_bundle", bundle);
        return bundle2;
    }

    private void m3629a(int i, ShopShareCard shopShareCard, String str) {
        String format = String.format("/etsyapps/v3/member/shop/shares/activity/%s/%s/%s/block", new Object[]{shopShareCard.getActivityId(), Integer.valueOf(shopShareCard.getOwnerType()), shopShareCard.getOwnerId()});
        String str2 = str.equals(ResponseConstants.OBJECT) ? "block_this_post" : "block_seller_posts";
        aj.m1101a().m1123i().m1699a(HttpRequestJobBuilder.m1713a(EmptyResult.class, format, 1).m1748b(FindsModule.FIELD_TYPE, str).m1743a(new ShopShareCardClickHandler(this, str2)).m1741a(new ShopShareCardClickHandler(this, str2)).m1742a(new ShopShareCardClickHandler(this, str2)).m1737a());
    }
}

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
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.requests.SearchAdsLogRequest;
import com.etsy.android.ui.nav.EtsyActivityNavigator;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.ui.util.FavoriteUtil;
import com.etsy.android.uikit.cardview.ICardViewAdapter;
import com.etsy.android.uikit.viewholder.a.a;
import java.util.HashMap;

/* renamed from: com.etsy.android.ui.cardview.a.f */
public class ListingCardClickHandler extends a {
    private FavoriteUtil f2235a;
    private ICardViewAdapter f2236e;

    /* renamed from: com.etsy.android.ui.cardview.a.f.1 */
    class ListingCardClickHandler implements FavoriteUtil {
        final /* synthetic */ int f2233a;
        final /* synthetic */ ListingCardClickHandler f2234b;

        ListingCardClickHandler(ListingCardClickHandler listingCardClickHandler, int i) {
            this.f2234b = listingCardClickHandler;
            this.f2233a = i;
        }

        public void m3580a() {
            this.f2234b.f2236e.onItemChanged(this.f2233a);
        }

        public void m3581b() {
            this.f2234b.f2236e.onItemChanged(this.f2233a);
        }
    }

    public /* synthetic */ void m3586a(Object obj) {
        m3589c((ListingLike) obj);
    }

    public ListingCardClickHandler(String str, FragmentActivity fragmentActivity, ICardViewAdapter iCardViewAdapter, @NonNull AnalyticsTracker analyticsTracker) {
        super(str, fragmentActivity, analyticsTracker);
        this.f2235a = new FavoriteUtil(this.c, null, str, analyticsTracker);
        this.f2236e = iCardViewAdapter;
    }

    public void m3584a(ListingLike listingLike) {
        if (this.b != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(ResponseConstants.LISTING_ID, listingLike.getListingId().getId());
            EtsyLogger.m1966a().m1997b("homescreen_tapped_listing", this.b, hashMap);
        }
        if (listingLike.isAd()) {
            aj.m1101a().m1124j().m1663a(SearchAdsLogRequest.logSearchAdsClick(listingLike));
        }
        Nav.m4682a(this.c).m4683a().m4467a(listingLike.getListingId());
    }

    public void m3585a(ListingLike listingLike, ImageView imageView, boolean z, int i) {
        EtsyId listingId = listingLike.getListingId();
        if (aj.m1101a().m1118d()) {
            this.f2235a.m5173a(this.b);
            this.f2235a.m5168a(imageView, R.ic_listing_unfavorite_selector, R.ic_listing_favorite_selector, z);
            this.f2235a.m5172a(listingLike, new ListingCardClickHandler(this, i), z);
            return;
        }
        ((EtsyActivityNavigator) Nav.m4681a(this.c).m3012a((View) imageView)).m4453a(EtsyAction.FAVORITE, listingId.getId());
    }

    public void m3588b(ListingLike listingLike) {
        if (listingLike != null) {
            if (aj.m1101a().m1118d()) {
                Nav.m4681a(this.c).m4443a(601).m4481a(listingLike);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putSerializable(ActivityFeedEntity.LISTING, listingLike);
            Nav.m4681a(this.c).m4452a(EtsyAction.MANAGE_ITEM_COLLECTIONS, bundle);
        }
    }

    public void m3589c(ListingLike listingLike) {
        m3584a(listingLike);
    }

    public void m3583a(int i) {
        this.f2236e.onRemoveItem(i);
    }

    public boolean m3587a() {
        return this.f2236e.canRemoveItems();
    }
}

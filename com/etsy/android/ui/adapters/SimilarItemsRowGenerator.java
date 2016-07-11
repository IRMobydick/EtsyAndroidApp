package com.etsy.android.ui.adapters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.bh;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.nav.EtsyActivityNavigator;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.ui.util.FavoriteUtil;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ListingFullImageView;

/* renamed from: com.etsy.android.ui.adapters.m */
public class SimilarItemsRowGenerator extends ListingRowBaseGenerator<ListingCard> {
    private SimilarItemsRowGenerator f2217n;
    private FragmentActivity f2218o;
    private EtsyFragment f2219p;
    private FavoriteUtil f2220q;
    private String f2221r;

    /* renamed from: com.etsy.android.ui.adapters.m.1 */
    class SimilarItemsRowGenerator extends TrackingOnClickListener {
        final /* synthetic */ String f2209a;
        final /* synthetic */ ListingRowBaseGenerator f2210b;
        final /* synthetic */ ListingCard f2211c;
        final /* synthetic */ SimilarItemsRowGenerator f2212d;

        /* renamed from: com.etsy.android.ui.adapters.m.1.1 */
        class SimilarItemsRowGenerator implements FavoriteUtil {
            final /* synthetic */ SimilarItemsRowGenerator f2208a;

            SimilarItemsRowGenerator(SimilarItemsRowGenerator similarItemsRowGenerator) {
                this.f2208a = similarItemsRowGenerator;
            }

            public void m3543a() {
                this.f2208a.f2211c.setIsFavorite(true);
            }

            public void m3544b() {
                this.f2208a.f2211c.setIsFavorite(false);
            }
        }

        SimilarItemsRowGenerator(SimilarItemsRowGenerator similarItemsRowGenerator, ITrackedObject[] iTrackedObjectArr, String str, ListingRowBaseGenerator listingRowBaseGenerator, ListingCard listingCard) {
            this.f2212d = similarItemsRowGenerator;
            this.f2209a = str;
            this.f2210b = listingRowBaseGenerator;
            this.f2211c = listingCard;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            if (aj.m1101a().m1118d()) {
                this.f2212d.f2220q.m5168a(this.f2210b.f2185d, R.ic_listing_unfavorite_selector, R.ic_listing_favorite_selector, this.f2211c.isFavorite());
                this.f2212d.f2220q.m5170a(this.f2211c.getListingId(), new SimilarItemsRowGenerator(this), this.f2211c.isFavorite());
                return;
            }
            ((EtsyActivityNavigator) Nav.m4682a(this.f2212d.f2218o).m4683a().m3012a(view)).m4453a(EtsyAction.FAVORITE, String.valueOf(this.f2209a));
        }
    }

    /* renamed from: com.etsy.android.ui.adapters.m.2 */
    class SimilarItemsRowGenerator extends TrackingOnClickListener {
        final /* synthetic */ ListingCard f2213a;
        final /* synthetic */ SimilarItemsRowGenerator f2214b;

        SimilarItemsRowGenerator(SimilarItemsRowGenerator similarItemsRowGenerator, ITrackedObject[] iTrackedObjectArr, ListingCard listingCard) {
            this.f2214b = similarItemsRowGenerator;
            this.f2213a = listingCard;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            if (aj.m1101a().m1118d()) {
                Nav.m4681a(this.f2214b.f2218o).m4444a(601, this.f2214b.f2219p).m4481a(this.f2213a);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putSerializable(ActivityFeedEntity.LISTING, this.f2213a);
            ((EtsyActivityNavigator) Nav.m4681a(this.f2214b.f2218o).m3012a(view)).m4452a(EtsyAction.MANAGE_ITEM_COLLECTIONS, bundle);
        }
    }

    /* renamed from: com.etsy.android.ui.adapters.m.3 */
    class SimilarItemsRowGenerator extends TrackingOnClickListener {
        final /* synthetic */ ListingCard f2215a;
        final /* synthetic */ SimilarItemsRowGenerator f2216b;

        SimilarItemsRowGenerator(SimilarItemsRowGenerator similarItemsRowGenerator, ITrackedObject[] iTrackedObjectArr, ListingCard listingCard) {
            this.f2216b = similarItemsRowGenerator;
            this.f2215a = listingCard;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            if (this.f2216b.f2217n != null) {
                this.f2216b.f2217n.m3556a(this.f2215a);
            }
        }
    }

    public SimilarItemsRowGenerator(FragmentActivity fragmentActivity, @NonNull EtsyFragment etsyFragment, ImageBatch imageBatch) {
        super(fragmentActivity, imageBatch, 2130903324);
        this.f2218o = fragmentActivity;
        this.f2219p = etsyFragment;
        this.f2220q = new FavoriteUtil(fragmentActivity, etsyFragment, "view_listing", etsyFragment.getAnalyticsContext());
        this.f2221r = SharedPreferencesUtility.m3147h(fragmentActivity);
    }

    public int m3553d() {
        return 0;
    }

    protected int m3554e() {
        return m3510g().getDimensionPixelSize(R.fixed_small);
    }

    protected void m3555f() {
        super.m3509f();
        float f = 2.67f;
        if (this.h.m5621a()) {
            if (this.h.m5624d()) {
                f = 5.67f;
            } else {
                f = 3.67f;
            }
        }
        this.k = (int) (((float) m3527c()) / f);
        this.m = this.k - (this.d * 2);
        this.l = (int) (((float) this.m) * ListingFullImageView.ASPECT_RATIO_STANDARD);
    }

    protected void m3550a(int i, int i2, ListingRowBaseGenerator<ListingCard> listingRowBaseGenerator, ListingCard listingCard) {
        boolean hasCollections;
        String etsyId = listingCard.getListingId().toString();
        BaseModelImage listingImage = listingCard.getListingImage();
        if (listingImage != null) {
            m3513j().m1571a(listingImage.getImageUrlForPixelWidth(i), listingRowBaseGenerator.f2184c, i, i2, listingImage.getImageColor());
        }
        if (m3546a(listingCard.getShopName())) {
            listingRowBaseGenerator.f2185d.setVisibility(8);
        } else {
            listingRowBaseGenerator.f2185d.setVisibility(0);
            listingRowBaseGenerator.f2185d.setImageResource(listingCard.isFavorite() ? R.ic_listing_favorite_selector : R.ic_listing_unfavorite_selector);
            listingRowBaseGenerator.f2185d.setOnClickListener(new SimilarItemsRowGenerator(this, new ITrackedObject[]{listingCard}, etsyId, listingRowBaseGenerator, listingCard));
        }
        if (aj.m1101a().m1118d()) {
            hasCollections = listingCard.hasCollections();
        } else {
            hasCollections = false;
        }
        listingRowBaseGenerator.f2186e.setImageResource(hasCollections ? R.ic_listing_lists_added : R.ic_listing_lists);
        listingRowBaseGenerator.f2186e.setOnClickListener(new SimilarItemsRowGenerator(this, new ITrackedObject[]{listingCard}, listingCard));
        listingRowBaseGenerator.f2185d.setVisibility(8);
        listingRowBaseGenerator.f2186e.setVisibility(8);
        listingRowBaseGenerator.f2187f.setText(listingCard.getTitle());
        if (listingCard.isSoldOut()) {
            listingRowBaseGenerator.f2188g.setText(R.sold_out);
        } else {
            listingRowBaseGenerator.f2188g.setText(listingCard.getPrice().formatWithConditionalCurrencyCode());
        }
        if (listingRowBaseGenerator.f2189h != null) {
            listingRowBaseGenerator.f2189h.setText(listingCard.getShopName());
        }
        listingRowBaseGenerator.f2183b.setOnClickListener(new SimilarItemsRowGenerator(this, new ITrackedObject[]{listingCard}, listingCard));
        listingRowBaseGenerator.f2183b.setVisibility(0);
    }

    public void m3552a(SimilarItemsRowGenerator similarItemsRowGenerator) {
        this.f2217n = similarItemsRowGenerator;
    }

    private boolean m3546a(String str) {
        if (aj.m1101a().m1118d() && bh.m3340a(this.f2221r)) {
            return this.f2221r.equals(str);
        }
        return false;
    }
}

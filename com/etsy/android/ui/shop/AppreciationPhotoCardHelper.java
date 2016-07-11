package com.etsy.android.ui.shop;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayout.LayoutParams;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.ui.nav.EtsyActivityNavigator;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.ui.util.FavoriteUtil;
import com.etsy.android.uikit.util.GraphikUtil;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ListingFullImageView;

/* renamed from: com.etsy.android.ui.shop.b */
class AppreciationPhotoCardHelper {
    TextView f3422a;
    TextView f3423b;
    TextView f3424c;
    ListingFullImageView f3425d;
    ImageView f3426e;
    ImageView f3427f;
    ListingCard f3428g;
    final /* synthetic */ AppreciationPhotoCardHelper f3429h;

    /* renamed from: com.etsy.android.ui.shop.b.1 */
    class AppreciationPhotoCardHelper extends TrackingOnClickListener {
        final /* synthetic */ FragmentActivity f3412a;
        final /* synthetic */ ListingCard f3413b;
        final /* synthetic */ AppreciationPhotoCardHelper f3414c;

        AppreciationPhotoCardHelper(AppreciationPhotoCardHelper appreciationPhotoCardHelper, FragmentActivity fragmentActivity, ListingCard listingCard) {
            this.f3414c = appreciationPhotoCardHelper;
            this.f3412a = fragmentActivity;
            this.f3413b = listingCard;
        }

        public void onViewClick(View view) {
            Nav.m4682a(this.f3412a).m4683a().m4467a(this.f3413b.getListingId());
        }
    }

    /* renamed from: com.etsy.android.ui.shop.b.2 */
    class AppreciationPhotoCardHelper extends TrackingOnClickListener {
        final /* synthetic */ FragmentActivity f3416a;
        final /* synthetic */ ListingCard f3417b;
        final /* synthetic */ AppreciationPhotoCardHelper f3418c;

        /* renamed from: com.etsy.android.ui.shop.b.2.1 */
        class AppreciationPhotoCardHelper implements FavoriteUtil {
            final /* synthetic */ AppreciationPhotoCardHelper f3415a;

            AppreciationPhotoCardHelper(AppreciationPhotoCardHelper appreciationPhotoCardHelper) {
                this.f3415a = appreciationPhotoCardHelper;
            }

            public void m4975a() {
                this.f3415a.f3417b.setIsFavorite(true);
            }

            public void m4976b() {
                this.f3415a.f3417b.setIsFavorite(false);
            }
        }

        AppreciationPhotoCardHelper(AppreciationPhotoCardHelper appreciationPhotoCardHelper, FragmentActivity fragmentActivity, ListingCard listingCard) {
            this.f3418c = appreciationPhotoCardHelper;
            this.f3416a = fragmentActivity;
            this.f3417b = listingCard;
        }

        public void onViewClick(View view) {
            if (aj.m1101a().m1118d()) {
                this.f3418c.f3429h.f3402b.m5168a(this.f3418c.f3426e, R.ic_listing_unfavorite_selector, R.ic_listing_favorite_selector, this.f3417b.isFavorite());
                this.f3418c.f3429h.f3402b.m5172a(this.f3417b, new AppreciationPhotoCardHelper(this), this.f3417b.isFavorite());
                return;
            }
            ((EtsyActivityNavigator) Nav.m4682a(this.f3416a).m4683a().m3012a(view)).m4453a(EtsyAction.FAVORITE, String.valueOf(this.f3417b.getListingId()));
        }
    }

    /* renamed from: com.etsy.android.ui.shop.b.3 */
    class AppreciationPhotoCardHelper extends TrackingOnClickListener {
        final /* synthetic */ ListingCard f3419a;
        final /* synthetic */ FragmentActivity f3420b;
        final /* synthetic */ AppreciationPhotoCardHelper f3421c;

        AppreciationPhotoCardHelper(AppreciationPhotoCardHelper appreciationPhotoCardHelper, ITrackedObject[] iTrackedObjectArr, ListingCard listingCard, FragmentActivity fragmentActivity) {
            this.f3421c = appreciationPhotoCardHelper;
            this.f3419a = listingCard;
            this.f3420b = fragmentActivity;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            if (aj.m1101a().m1118d()) {
                Nav.m4681a(this.f3420b).m4444a(601, this.f3421c.f3429h.f3403c).m4481a(this.f3419a);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putSerializable(ActivityFeedEntity.LISTING, this.f3419a);
            ((EtsyActivityNavigator) Nav.m4681a(this.f3420b).m3013a(this.f3421c.f3429h.f3403c)).m4452a(EtsyAction.MANAGE_ITEM_COLLECTIONS, bundle);
        }
    }

    private AppreciationPhotoCardHelper(AppreciationPhotoCardHelper appreciationPhotoCardHelper, View view) {
        this.f3429h = appreciationPhotoCardHelper;
        this.f3422a = (TextView) view.findViewById(R.listing_title);
        this.f3425d = (ListingFullImageView) view.findViewById(R.listing_image);
        this.f3424c = (TextView) view.findViewById(R.listing_price);
        this.f3423b = (TextView) view.findViewById(R.listing_shop);
        this.f3426e = (ImageView) view.findViewById(R.btn_fav);
        this.f3427f = (ImageView) view.findViewById(R.btn_lists);
    }

    private void m4977a(ListingCard listingCard, boolean z) {
        boolean hasCollections;
        FragmentActivity activity = this.f3429h.f3403c.getActivity();
        this.f3428g = listingCard;
        this.f3425d.setUseStandardRatio(true);
        this.f3425d.setImageInfo(listingCard.getListingImage(), this.f3429h.f3403c.getImageBatch());
        this.f3422a.setText(listingCard.getTitle());
        if (listingCard.isSoldOut() || !z) {
            if (!GraphikUtil.m5547a(this.f3424c, R.typeface_bold)) {
                this.f3424c.setTypeface(this.f3424c.getTypeface(), 1);
            }
            this.f3424c.setTextColor(activity.getResources().getColor(R.grey));
            this.f3424c.setText(!z ? R.unavailable : R.sold);
        } else {
            this.f3424c.setText(listingCard.getPrice().formatWithConditionalCurrencyCode());
        }
        this.f3423b.setVisibility(8);
        ((LayoutParams) this.f3424c.getLayoutParams()).setGravity(119);
        this.f3429h.f3410j.setOnClickListener(new AppreciationPhotoCardHelper(this, activity, listingCard));
        if (!z) {
            this.f3426e.setVisibility(8);
            this.f3427f.setVisibility(8);
        }
        this.f3426e.setImageResource(listingCard.isFavorite() ? R.ic_listing_favorite_selector : R.ic_listing_unfavorite_selector);
        this.f3426e.setOnClickListener(new AppreciationPhotoCardHelper(this, activity, listingCard));
        if (aj.m1101a().m1118d()) {
            hasCollections = listingCard.hasCollections();
        } else {
            hasCollections = false;
        }
        this.f3427f.setImageResource(hasCollections ? R.ic_listing_lists_added : R.ic_listing_lists);
        this.f3427f.setOnClickListener(new AppreciationPhotoCardHelper(this, new ITrackedObject[]{listingCard}, listingCard, activity));
    }
}

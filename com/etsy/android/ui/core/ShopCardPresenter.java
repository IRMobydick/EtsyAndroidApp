package com.etsy.android.ui.core;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.EventTracker;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.models.ShopAboutMember;
import com.etsy.android.lib.models.UserProfile;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.lib.models.apiv3.ShopIcon;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.bh;
import com.etsy.android.lib.util.fonts.EtsyFontIcons;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.adapters.ListingRowBaseGenerator;
import com.etsy.android.ui.adapters.ListingRowGenerator;
import com.etsy.android.ui.nav.EtsyActivityNavigator;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.ui.util.FavoriteUtil;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.etsy.android.ui.core.e */
public class ShopCardPresenter implements ListingRowGenerator {
    private final int f2755a;
    private FragmentActivity f2756b;
    private EtsyFragment f2757c;
    private FavoriteUtil f2758d;
    private ImageBatch f2759e;
    private String f2760f;
    private String f2761g;
    private final int f2762h;
    private final int f2763i;
    private final ListingRowGenerator f2764j;
    private List<ListingRowBaseGenerator<Listing>> f2765k;

    /* renamed from: com.etsy.android.ui.core.e.1 */
    class ShopCardPresenter extends TrackingOnClickListener {
        final /* synthetic */ Shop f2745a;
        final /* synthetic */ ShopCardPresenter f2746b;

        ShopCardPresenter(ShopCardPresenter shopCardPresenter, ITrackedObject[] iTrackedObjectArr, Shop shop) {
            this.f2746b = shopCardPresenter;
            this.f2745a = shop;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            if (this.f2745a.getUser() == null || !this.f2745a.getUser().getUserId().hasId()) {
                Nav.m4682a(this.f2746b.f2756b).m4683a().m4501b(this.f2745a.getShopId());
            } else {
                Nav.m4682a(this.f2746b.f2756b).m4683a().m4473a(this.f2745a.getShopId(), this.f2745a.getUser().getUserId());
            }
            EventTracker.m2032b(this.f2746b.f2760f);
        }
    }

    /* renamed from: com.etsy.android.ui.core.e.2 */
    class ShopCardPresenter extends TrackingOnClickListener {
        final /* synthetic */ Shop f2747a;
        final /* synthetic */ ShopCardPresenter f2748b;

        ShopCardPresenter(ShopCardPresenter shopCardPresenter, ITrackedObject[] iTrackedObjectArr, Shop shop) {
            this.f2748b = shopCardPresenter;
            this.f2747a = shop;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            Nav.m4682a(this.f2748b.f2756b).m4683a().m4518d(this.f2747a.getShopId(), null);
        }
    }

    /* renamed from: com.etsy.android.ui.core.e.3 */
    class ShopCardPresenter extends TrackingOnClickListener {
        final /* synthetic */ Listing f2750a;
        final /* synthetic */ ListingRowBaseGenerator f2751b;
        final /* synthetic */ ShopCardPresenter f2752c;

        /* renamed from: com.etsy.android.ui.core.e.3.1 */
        class ShopCardPresenter implements FavoriteUtil {
            final /* synthetic */ ShopCardPresenter f2749a;

            ShopCardPresenter(ShopCardPresenter shopCardPresenter) {
                this.f2749a = shopCardPresenter;
            }

            public void m3984a() {
                this.f2749a.f2750a.setIsFavorite(true);
            }

            public void m3985b() {
                this.f2749a.f2750a.setIsFavorite(false);
            }
        }

        ShopCardPresenter(ShopCardPresenter shopCardPresenter, ITrackedObject[] iTrackedObjectArr, Listing listing, ListingRowBaseGenerator listingRowBaseGenerator) {
            this.f2752c = shopCardPresenter;
            this.f2750a = listing;
            this.f2751b = listingRowBaseGenerator;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            if (aj.m1101a().m1118d()) {
                this.f2752c.f2758d.m5168a(this.f2751b.f2185d, R.ic_listing_unfavorite_selector, R.ic_listing_favorite_selector, this.f2750a.isFavorite());
                this.f2752c.f2758d.m5170a(this.f2750a.getListingId(), new ShopCardPresenter(this), this.f2750a.isFavorite());
                return;
            }
            ((EtsyActivityNavigator) Nav.m4682a(this.f2752c.f2756b).m4683a().m3012a(view)).m4453a(EtsyAction.FAVORITE, String.valueOf(this.f2750a.getListingId()));
        }
    }

    /* renamed from: com.etsy.android.ui.core.e.4 */
    class ShopCardPresenter extends TrackingOnClickListener {
        final /* synthetic */ Listing f2753a;
        final /* synthetic */ ShopCardPresenter f2754b;

        ShopCardPresenter(ShopCardPresenter shopCardPresenter, ITrackedObject[] iTrackedObjectArr, Listing listing) {
            this.f2754b = shopCardPresenter;
            this.f2753a = listing;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            if (aj.m1101a().m1118d()) {
                Nav.m4681a(this.f2754b.f2756b).m4444a(601, this.f2754b.f2757c).m4481a(this.f2753a);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putSerializable(ActivityFeedEntity.LISTING, this.f2753a);
            ((EtsyActivityNavigator) Nav.m4681a(this.f2754b.f2756b).m3013a(this.f2754b.f2757c)).m4452a(EtsyAction.MANAGE_ITEM_COLLECTIONS, bundle);
        }
    }

    public ShopCardPresenter(FragmentActivity fragmentActivity, @NonNull EtsyFragment etsyFragment, ImageBatch imageBatch, int i) {
        this.f2756b = fragmentActivity;
        this.f2757c = etsyFragment;
        this.f2759e = imageBatch;
        this.f2760f = "view_listing";
        this.f2758d = new FavoriteUtil(fragmentActivity, etsyFragment, this.f2760f, etsyFragment.getAnalyticsContext());
        Resources b = m3990b();
        this.f2764j = new ListingRowGenerator(this.f2756b, this.f2759e, 2130903321);
        this.f2764j.m3528c(i);
        this.f2764j.m3525b(2130837598);
        this.f2764j.m3532e(b.getDimensionPixelSize(R.padding_medium));
        this.f2764j.m3530d(0);
        this.f2764j.m3533f(b.getDimensionPixelSize(R.padding_medium));
        this.f2764j.m3537a((ListingRowGenerator) this);
        this.f2764j.m3524b();
        this.f2762h = b.getDimensionPixelSize(2131361933);
        this.f2763i = b.getDimensionPixelSize(R.gen_avatar_corners_small);
        this.f2755a = b.getDimensionPixelSize(2131361935);
        this.f2761g = SharedPreferencesUtility.m3147h(fragmentActivity);
        this.f2765k = new ArrayList();
    }

    private ImageBatch m3987a() {
        return this.f2759e;
    }

    private Resources m3990b() {
        return this.f2756b.getResources();
    }

    public ShopCardPresenter m3994a(View view) {
        ShopCardPresenter shopCardPresenter = new ShopCardPresenter();
        shopCardPresenter.f2766a = (ImageView) view.findViewById(R.shop_avatar);
        shopCardPresenter.f2767b = (TextView) view.findViewById(R.shop_name);
        shopCardPresenter.f2768c = (TextView) view.findViewById(2131755346);
        shopCardPresenter.f2769d = (TextView) view.findViewById(2131756418);
        shopCardPresenter.f2770e = (TextView) view.findViewById(2131756234);
        shopCardPresenter.f2771f = (TextView) view.findViewById(2131756240);
        shopCardPresenter.f2772g = (ImageView) view.findViewById(2131756239);
        shopCardPresenter.f2776k = (LinearLayout) view.findViewById(2131756236);
        shopCardPresenter.f2773h = view.findViewById(2131756235);
        shopCardPresenter.f2775j = view.findViewById(2131756238);
        shopCardPresenter.f2774i = view.findViewById(2131756237);
        return shopCardPresenter;
    }

    public void m3996a(ShopCardPresenter shopCardPresenter, Shop shop, int i, int i2, boolean z) {
        Resources b = m3990b();
        if (shop != null && shop.getUser() != null) {
            String iconUrl;
            UserProfile profile = shop.getUser().getProfile();
            shopCardPresenter.f2767b.setText(shop.getShopName());
            if (z) {
                shopCardPresenter.f2770e.setText(b.getString(R.made_by, new Object[]{r7}));
                shopCardPresenter.f2770e.setVisibility(0);
            } else {
                shopCardPresenter.f2770e.setVisibility(8);
            }
            if (TextUtils.isEmpty(shop.getTitle())) {
                shopCardPresenter.f2769d.setVisibility(8);
            } else {
                shopCardPresenter.f2769d.setVisibility(0);
                shopCardPresenter.f2769d.setText(shop.getTitle());
            }
            String a = bh.m3335a(profile);
            if (bh.m3340a(a)) {
                IconDrawable a2 = IconDrawable.m775a(m3990b()).m780a(EtsyFontIcons.LOCATION).m779a(m3990b().getColor(R.light_grey)).m778a((float) m3990b().getDimensionPixelSize(R.text_small));
                shopCardPresenter.f2768c.setCompoundDrawablePadding(m3990b().getDimensionPixelSize(R.fixed_medium));
                shopCardPresenter.f2768c.setCompoundDrawablesWithIntrinsicBounds(a2.m777a(), null, null, null);
                shopCardPresenter.f2768c.setText(a);
            } else {
                shopCardPresenter.f2768c.setVisibility(8);
            }
            if (bh.m3340a(shop.getIconUrl(((Integer) ShopIcon.IMG_SIZE_75.first).intValue()))) {
                iconUrl = shop.getIconUrl(((Integer) ShopIcon.IMG_SIZE_75.first).intValue());
            } else {
                iconUrl = profile.getImageUrl75x75();
            }
            m3987a().m1576b(iconUrl, shopCardPresenter.f2766a, this.f2763i, this.f2762h, this.f2762h);
            OnClickListener shopCardPresenter2 = new ShopCardPresenter(this, new ITrackedObject[]{shop}, shop);
            shopCardPresenter.f2773h.setOnClickListener(shopCardPresenter2);
            shopCardPresenter.f2774i.setOnClickListener(shopCardPresenter2);
            if (shop.hasAbout()) {
                shopCardPresenter.f2775j.setVisibility(0);
                OnClickListener shopCardPresenter3 = new ShopCardPresenter(this, new ITrackedObject[]{shop}, shop);
                shopCardPresenter.f2772g.setImageBitmap(null);
                List members = shop.getAbout().getMembers();
                if (members.size() > 0) {
                    shopCardPresenter.f2772g.setVisibility(0);
                    m3987a().m1574b(((ShopAboutMember) members.get(0)).getImageUrl190x190(), shopCardPresenter.f2772g, this.f2755a);
                } else {
                    shopCardPresenter.f2772g.setVisibility(8);
                }
                shopCardPresenter.f2775j.setOnClickListener(shopCardPresenter3);
                shopCardPresenter.f2771f.setText(b.getQuantityString(R.meet_the_owners_of, members.size(), new Object[]{r7}));
            } else {
                shopCardPresenter.f2775j.setVisibility(8);
                shopCardPresenter.f2775j.setOnClickListener(null);
            }
            if (i > 0) {
                m3988a(shopCardPresenter.f2776k, shop.getListings(), i, i2);
            }
        }
    }

    private void m3988a(LinearLayout linearLayout, List<Listing> list, int i, int i2) {
        if (list.size() == 0 || i2 == 0) {
            linearLayout.setVisibility(8);
            return;
        }
        int a = this.f2764j.m3516a();
        int min = Math.min(i, list.size() / a);
        if (min == 0) {
            linearLayout.setVisibility(8);
            return;
        }
        for (int i3 = 0; i3 < min; i3++) {
            int i4 = i3 * a;
            View a2 = this.f2764j.m3517a(null);
            for (int i5 = 0; i5 < a; i5++) {
                int i6 = i4 + i5;
                if (i6 >= list.size()) {
                    break;
                }
                Listing listing = (Listing) list.get(i6);
                ListingRowBaseGenerator a3 = this.f2764j.m3520a(a2.getTag(), listing, i5);
                if (a3 != null) {
                    boolean hasCollections;
                    this.f2765k.add(a3);
                    if (m3989a(listing.getShopName())) {
                        a3.f2185d.setVisibility(8);
                    } else {
                        a3.f2185d.setVisibility(0);
                        a3.f2185d.setImageResource(listing.isFavorite() ? R.ic_listing_favorite_selector : R.ic_listing_unfavorite_selector);
                        a3.f2185d.setOnClickListener(new ShopCardPresenter(this, new ITrackedObject[]{listing}, listing, a3));
                    }
                    if (aj.m1101a().m1118d()) {
                        hasCollections = listing.hasCollections();
                    } else {
                        hasCollections = false;
                    }
                    a3.f2186e.setImageResource(hasCollections ? R.ic_listing_lists_added : R.ic_listing_lists);
                    a3.f2186e.setOnClickListener(new ShopCardPresenter(this, new ITrackedObject[]{listing}, listing));
                    a3.f2185d.setVisibility(8);
                    a3.f2186e.setVisibility(8);
                }
            }
            linearLayout.addView(a2);
        }
        linearLayout.setVisibility(0);
    }

    public void m3995a(Listing listing) {
        if (listing != null) {
            Nav.m4681a(this.f2756b).m4467a(listing.getListingId());
        }
    }

    public void m3997a(String str, boolean z) {
        for (ListingRowBaseGenerator listingRowBaseGenerator : this.f2765k) {
            Listing listing = (Listing) listingRowBaseGenerator.f2182a;
            if (str.equals(listing.getListingId().toString())) {
                listing.setHasCollections(z);
                listingRowBaseGenerator.f2186e.setImageResource(z ? R.ic_listing_lists_added : R.ic_listing_lists);
            }
        }
    }

    private boolean m3989a(String str) {
        if (aj.m1101a().m1118d() && bh.m3340a(this.f2761g)) {
            return this.f2761g.equals(str);
        }
        return false;
    }
}

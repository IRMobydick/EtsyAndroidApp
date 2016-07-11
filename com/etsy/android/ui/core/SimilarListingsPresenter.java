package com.etsy.android.ui.core;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.HttpRequestJobBuilder;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.adapters.ListingRowBaseGenerator;
import com.etsy.android.ui.adapters.SimilarItemsRowGenerator;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.etsy.android.ui.core.h */
public class SimilarListingsPresenter implements SimilarItemsLayout {
    private BOENavDrawerActivity f2784a;
    private EtsyFragment f2785b;
    private ImageBatch f2786c;
    private View f2787d;
    private SimilarItemsLayout f2788e;
    private List<ListingRowBaseGenerator<ListingCard>> f2789f;
    private boolean f2790g;
    private boolean f2791h;

    /* renamed from: com.etsy.android.ui.core.h.1 */
    class SimilarListingsPresenter implements SimilarItemsRowGenerator {
        final /* synthetic */ SimilarListingsPresenter f2777a;

        SimilarListingsPresenter(SimilarListingsPresenter similarListingsPresenter) {
            this.f2777a = similarListingsPresenter;
        }

        public void m3999a(ListingCard listingCard) {
            EtsyLogger.m1966a().m2001d("similar_listing_selected", "view_listing");
            Nav.m4682a(this.f2777a.f2784a).m4683a().m4467a(listingCard.getListingId());
        }
    }

    /* renamed from: com.etsy.android.ui.core.h.2 */
    class SimilarListingsPresenter implements EtsyJobResponse {
        final /* synthetic */ SimilarListingsPresenter f2778a;

        SimilarListingsPresenter(SimilarListingsPresenter similarListingsPresenter) {
            this.f2778a = similarListingsPresenter;
        }

        public void m4000a(int i, String str, EtsyResult etsyResult) {
            if (!this.f2778a.f2791h) {
                this.f2778a.f2787d.setVisibility(8);
                this.f2778a.f2788e.setVisibility(8);
            }
        }
    }

    /* renamed from: com.etsy.android.ui.core.h.3 */
    class SimilarListingsPresenter implements EtsyJobResponse {
        final /* synthetic */ SimilarListingsPresenter f2779a;

        SimilarListingsPresenter(SimilarListingsPresenter similarListingsPresenter) {
            this.f2779a = similarListingsPresenter;
        }

        public void m4001a(EtsyResult etsyResult) {
            if (!this.f2779a.f2791h) {
                this.f2779a.f2787d.setVisibility(8);
                this.f2779a.f2788e.setVisibility(8);
            }
        }
    }

    /* renamed from: com.etsy.android.ui.core.h.4 */
    class SimilarListingsPresenter implements EtsyJobResponse<ListingCard> {
        final /* synthetic */ SimilarItemsRowGenerator f2781a;
        final /* synthetic */ Listing f2782b;
        final /* synthetic */ SimilarListingsPresenter f2783c;

        /* renamed from: com.etsy.android.ui.core.h.4.1 */
        class SimilarListingsPresenter extends TrackingOnClickListener {
            final /* synthetic */ SimilarListingsPresenter f2780a;

            SimilarListingsPresenter(SimilarListingsPresenter similarListingsPresenter, ITrackedObject... iTrackedObjectArr) {
                this.f2780a = similarListingsPresenter;
                super(iTrackedObjectArr);
            }

            public void onViewClick(View view) {
                Nav.m4682a(this.f2780a.f2783c.f2784a).m4683a().m4519d(this.f2780a.f2782b.getListingId().getId());
            }
        }

        SimilarListingsPresenter(SimilarListingsPresenter similarListingsPresenter, SimilarItemsRowGenerator similarItemsRowGenerator, Listing listing) {
            this.f2783c = similarListingsPresenter;
            this.f2781a = similarItemsRowGenerator;
            this.f2782b = listing;
        }

        public void m4002a(List<ListingCard> list, int i, EtsyResult<ListingCard> etsyResult) {
            if (!this.f2783c.f2791h) {
                this.f2783c.f2787d.setVisibility(0);
                this.f2783c.f2788e.setVisibility(0);
                int size = list.size();
                ViewGroup viewGroup = (ViewGroup) this.f2781a.m3518a(null, size);
                for (int i2 = 0; i2 < size; i2++) {
                    ListingRowBaseGenerator a = this.f2781a.m3520a(viewGroup.getTag(), list.get(i2), i2);
                    if (a != null) {
                        this.f2783c.f2789f.add(a);
                    }
                }
                View inflate = LayoutInflater.from(this.f2783c.f2784a).inflate(2130903362, viewGroup, false);
                inflate.setOnClickListener(new SimilarListingsPresenter(this, this.f2782b));
                viewGroup.addView(inflate);
                this.f2783c.f2788e.removeAllViews();
                this.f2783c.f2788e.addView(viewGroup);
            }
        }
    }

    public SimilarListingsPresenter(@NonNull EtsyFragment etsyFragment, View view, ImageBatch imageBatch) {
        this.f2790g = false;
        this.f2791h = false;
        this.f2784a = (BOENavDrawerActivity) etsyFragment.getActivity();
        this.f2785b = etsyFragment;
        this.f2786c = imageBatch;
        this.f2788e = (SimilarItemsLayout) view.findViewById(2131756243);
        this.f2787d = view.findViewById(2131756242);
        this.f2789f = new ArrayList();
        this.f2790g = etsyFragment.getConfigMap().m885c(EtsyConfigKeys.f1193L);
    }

    public void m4011b(Listing listing) {
        if (this.f2790g) {
            this.f2788e.setListing(listing, this);
        } else {
            m4009a(listing);
        }
    }

    public void m4008a() {
        this.f2784a.getRequestQueue().m1700a((Object) this);
        this.f2784a = null;
        this.f2785b = null;
        this.f2786c = null;
        this.f2787d = null;
        this.f2788e = null;
        this.f2789f = null;
        this.f2791h = true;
    }

    public void m4010a(String str, boolean z) {
        for (ListingRowBaseGenerator listingRowBaseGenerator : this.f2789f) {
            ListingCard listingCard = (ListingCard) listingRowBaseGenerator.f2182a;
            if (str.equals(listingCard.getListingId().toString())) {
                listingCard.setHasCollections(z);
                listingRowBaseGenerator.f2186e.setImageResource(z ? R.ic_listing_lists_added : R.ic_listing_lists);
            }
        }
    }

    public void m4009a(Listing listing) {
        String str;
        this.f2784a.getResources();
        SimilarItemsRowGenerator similarItemsRowGenerator = new SimilarItemsRowGenerator(this.f2784a, this.f2785b, this.f2786c);
        similarItemsRowGenerator.m3525b(2130837598);
        similarItemsRowGenerator.m3552a(new SimilarListingsPresenter(this));
        Class cls = ListingCard.class;
        if (aj.m1101a().m1118d()) {
            str = "/etsyapps/v3/member/personalization/similar-listings";
        } else {
            str = "/etsyapps/v3/public/personalization/similar-listings";
        }
        this.f2784a.getRequestQueue().m1697a((Object) this, HttpRequestJobBuilder.m1712a(cls, str).m1744a(ResponseConstants.LISTING_IDS, listing.getListingId().getId()).m1744a("limit", "6").m1744a("variant", "RecommenderSystems_SimilarListingsTfidf_Strings").m1743a(new SimilarListingsPresenter(this, similarItemsRowGenerator, listing)).m1741a(new SimilarListingsPresenter(this)).m1742a(new SimilarListingsPresenter(this)).m1737a());
    }
}

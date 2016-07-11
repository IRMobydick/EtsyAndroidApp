package com.etsy.android.ui.adapters;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.ListingImage;
import com.etsy.android.uikit.util.TrackingOnClickListener;

/* renamed from: com.etsy.android.ui.adapters.h */
public class ListingRowGenerator extends ListingRowBaseGenerator<Listing> {
    private ListingRowGenerator f2194n;

    /* renamed from: com.etsy.android.ui.adapters.h.1 */
    class ListingRowGenerator extends TrackingOnClickListener {
        final /* synthetic */ Listing f2192a;
        final /* synthetic */ ListingRowGenerator f2193b;

        ListingRowGenerator(ListingRowGenerator listingRowGenerator, ITrackedObject[] iTrackedObjectArr, Listing listing) {
            this.f2193b = listingRowGenerator;
            this.f2192a = listing;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            if (this.f2193b.f2194n != null) {
                this.f2193b.f2194n.m3538a(this.f2192a);
            }
        }
    }

    public ListingRowGenerator(FragmentActivity fragmentActivity, ImageBatch imageBatch, int i) {
        super(fragmentActivity, imageBatch, i);
    }

    public ListingRowGenerator(FragmentActivity fragmentActivity, ImageBatch imageBatch, int i, int i2) {
        super(fragmentActivity, imageBatch, i, i2);
    }

    public void m3537a(ListingRowGenerator listingRowGenerator) {
        this.f2194n = listingRowGenerator;
    }

    protected void m3535a(int i, int i2, ListingRowBaseGenerator<Listing> listingRowBaseGenerator, Listing listing) {
        listingRowBaseGenerator.f2187f.setText(listing.getTitle());
        if (listingRowBaseGenerator.f2188g != null) {
            if (listing.isSoldOut()) {
                listingRowBaseGenerator.f2188g.setText(R.sold_out);
            } else {
                listingRowBaseGenerator.f2188g.setText(listing.getPrice().formatWithConditionalCurrencyCode());
            }
        }
        ListingImage image = listing.getImage();
        String str = null;
        if (image != null) {
            str = image.getImageUrlForPixelWidth(i);
        }
        if (image == null || !image.hasImageColor()) {
            m3513j().m1570a(str, listingRowBaseGenerator.f2184c, i, i2);
        } else {
            m3513j().m1571a(str, listingRowBaseGenerator.f2184c, i, i2, image.getImageColor());
        }
        listingRowBaseGenerator.f2183b.setOnClickListener(new ListingRowGenerator(this, new ITrackedObject[]{listing}, listing));
        if (!(listingRowBaseGenerator.f2189h == null || listing.getShop() == null)) {
            listingRowBaseGenerator.f2189h.setText(listing.getShop().getShopName());
        }
        listingRowBaseGenerator.f2183b.setBackgroundResource(this.a);
        listingRowBaseGenerator.f2183b.setVisibility(0);
    }
}

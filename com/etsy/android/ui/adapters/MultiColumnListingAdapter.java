package com.etsy.android.ui.adapters;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.uikit.adapter.MultiColumnAdapter;

@Deprecated
public class MultiColumnListingAdapter extends MultiColumnAdapter<Listing> {
    private static final String TAG;
    protected boolean mExtraPaddingOnTopRow;

    static {
        TAG = EtsyDebug.m1891a(MultiColumnListingAdapter.class);
    }

    public MultiColumnListingAdapter(FragmentActivity fragmentActivity, ImageBatch imageBatch, int i) {
        super(fragmentActivity, imageBatch, i, new ListingRowGenerator(fragmentActivity, imageBatch, i));
    }

    public MultiColumnListingAdapter(FragmentActivity fragmentActivity, ImageBatch imageBatch, int i, int i2) {
        super(fragmentActivity, imageBatch, i, new ListingRowGenerator(fragmentActivity, imageBatch, i, i2));
    }

    protected ListingRowGenerator getListingRowGenerator() {
        return (ListingRowGenerator) this.mCardRowGenerator;
    }

    public void setOnListingClickListener(ListingRowGenerator listingRowGenerator) {
        getListingRowGenerator().m3537a(listingRowGenerator);
    }

    public void setWidthModifier(int i) {
        getListingRowGenerator().m3521a(i);
    }

    public void setPadInTabletMargins(boolean z) {
        getListingRowGenerator().m3523a(z);
    }

    public void addExtraPaddingToTopRow(boolean z) {
        this.mExtraPaddingOnTopRow = z;
    }

    public long getItemId(int i) {
        return 0;
    }

    protected View getDefaultView(int i, View view) {
        int a = this.mCardRowGenerator.m3502a();
        View a2 = this.mCardRowGenerator.m3503a(view);
        if (this.mExtraPaddingOnTopRow) {
            getListingRowGenerator().m3526b(a2, i);
        }
        bindRow(i, a, a2.getTag());
        return a2;
    }

    protected void bindRow(int i, int i2, Object obj) {
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = (i * i2) + i3;
            Object obj2 = null;
            if (i4 < this.mResults.size()) {
                obj2 = (Listing) this.mResults.get(i4);
            }
            getListingRowGenerator().m3520a(obj, obj2, i3);
        }
    }
}

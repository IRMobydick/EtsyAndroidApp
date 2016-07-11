package com.etsy.android.ui.favorites;

import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.models.apiv3.CheckableListingCollection;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.apiv3.ListingCollectionsRequest;
import java.util.Collection;
import java.util.Iterator;

/* renamed from: com.etsy.android.ui.favorites.d */
class ListingCollectionsFragment extends EtsyRequestJob<CheckableListingCollection> {
    final /* synthetic */ ListingCollectionsFragment f2964a;

    private ListingCollectionsFragment(ListingCollectionsFragment listingCollectionsFragment) {
        this.f2964a = listingCollectionsFragment;
    }

    protected EtsyRequest<CheckableListingCollection> m4242a() {
        return ListingCollectionsRequest.getListingCollections(this.f2964a.mListingId);
    }

    protected void m4243a(EtsyResult<CheckableListingCollection> etsyResult) {
        if (etsyResult == null || !etsyResult.m1049a() || etsyResult.m1056g() == null) {
            this.f2964a.showErrorView();
            return;
        }
        Collection g = etsyResult.m1056g();
        Iterator it = g.iterator();
        while (it.hasNext()) {
            if (com.etsy.android.lib.models.apiv3.Collection.TYPE_FAVORITES.equals(((com.etsy.android.lib.models.apiv3.Collection) it.next()).getType())) {
                it.remove();
            }
        }
        this.f2964a.mAdapter.addAll(g);
        this.f2964a.mAdapter.notifyDataSetChanged();
        this.f2964a.showListView();
    }
}

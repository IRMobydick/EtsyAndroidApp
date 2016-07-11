package com.etsy.android.ui.dialog;

import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.models.Variation;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.ListingsRequest;
import com.etsy.android.lib.util.bm;

/* renamed from: com.etsy.android.ui.dialog.c */
class VariationsListDialog extends EtsyRequestJob<Variation> {
    final /* synthetic */ VariationsListDialog f2939a;

    private VariationsListDialog(VariationsListDialog variationsListDialog) {
        this.f2939a = variationsListDialog;
    }

    protected EtsyRequest<Variation> m4221a() {
        return ListingsRequest.getVariations(String.valueOf(this.f2939a.mListingId));
    }

    protected void m4222a(EtsyResult<Variation> etsyResult) {
        if (!etsyResult.m1049a()) {
            this.f2939a.onLoadError();
        } else if (etsyResult.m1058i()) {
            bm.m3375a(this.f2939a.mListingId, etsyResult.m1056g());
            this.f2939a.onItemsLoaded(bm.m3371a(etsyResult.m1056g(), this.f2939a.mVariationId));
        } else {
            this.f2939a.showEmptyView();
        }
    }
}

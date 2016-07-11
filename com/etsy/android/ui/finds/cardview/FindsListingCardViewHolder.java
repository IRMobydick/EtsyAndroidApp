package com.etsy.android.ui.finds.cardview;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.ui.cardview.p014a.ListingCardClickHandler;
import com.etsy.android.uikit.viewholder.ListingCardViewHolder;

public class FindsListingCardViewHolder extends ListingCardViewHolder {
    private static final String TAG;

    static {
        TAG = EtsyDebug.m1891a(FindsListingCardViewHolder.class);
    }

    public FindsListingCardViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, ListingCardClickHandler listingCardClickHandler, ImageBatch imageBatch) {
        super(layoutInflater, viewGroup, listingCardClickHandler, imageBatch, false, false);
        int applyDimension = (int) TypedValue.applyDimension(1, layoutInflater.getContext().getResources().getDimension(2131361893), layoutInflater.getContext().getResources().getDisplayMetrics());
        ((MarginLayoutParams) getRootView().getLayoutParams()).setMargins(applyDimension, applyDimension, applyDimension, applyDimension);
    }
}

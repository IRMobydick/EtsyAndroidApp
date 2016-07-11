package com.etsy.android.uikit.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.Listing;

public class NonEtsyRetailRedirectListingCardView extends FrameLayout {
    private ListingFullImageView mImage;
    private TextView mPrice;
    private TextView mTitle;

    public NonEtsyRetailRedirectListingCardView(Context context) {
        super(context);
        init();
    }

    public NonEtsyRetailRedirectListingCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public NonEtsyRetailRedirectListingCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void setListing(Listing listing, ImageBatch imageBatch) {
        this.mImage.setImageInfo(listing.getListingImage(), imageBatch);
        this.mTitle.setText(listing.getTitle());
        this.mPrice.setText(listing.getPrice().format());
    }

    private void init() {
        inflate(getContext(), R.view_non_etsy_retail_redirect_listing_card, this);
        this.mImage = (ListingFullImageView) findViewById(R.listing_image);
        this.mImage.setUseStandardRatio(true);
        this.mTitle = (TextView) findViewById(R.listing_title);
        this.mPrice = (TextView) findViewById(R.listing_price);
    }
}

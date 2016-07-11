package com.etsy.android.uikit.viewholder;

import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.util.bh;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.ViewTreeObserverHelper;
import com.etsy.android.uikit.view.ListingFullImageView;
import com.etsy.android.uikit.view.RatingIconView;
import com.etsy.android.uikit.viewholder.a.a;

public class AnchorListingCardViewHolder extends BaseViewHolder<ListingCard> {
    private final ImageView mCollectionsIcon;
    private final ImageView mFavIcon;
    private final ImageBatch mImageBatch;
    private final ListingFullImageView mImageView;
    private final a mListener;
    private final LinearLayout mListingDetails;
    private final FrameLayout mListingImageRegion;
    private final RatingIconView mRating;
    private final TextView mTextAvailability;
    private final TextView mTextPrice;
    private final TextView mTextRatingCount;
    private final TextView mTextShopName;
    private final TextView mTextTitle;

    /* renamed from: com.etsy.android.uikit.viewholder.AnchorListingCardViewHolder.1 */
    class C10421 extends TrackingOnClickListener {
        final /* synthetic */ ListingCard f4288a;
        final /* synthetic */ AnchorListingCardViewHolder f4289b;

        C10421(AnchorListingCardViewHolder anchorListingCardViewHolder, ITrackedObject[] iTrackedObjectArr, ListingCard listingCard) {
            this.f4289b = anchorListingCardViewHolder;
            this.f4288a = listingCard;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            if (this.f4289b.mListener != null) {
                this.f4289b.mListener.a(this.f4288a);
            }
        }
    }

    /* renamed from: com.etsy.android.uikit.viewholder.AnchorListingCardViewHolder.2 */
    class C10432 implements OnGlobalLayoutListener {
        final /* synthetic */ AnchorListingCardViewHolder f4290a;

        C10432(AnchorListingCardViewHolder anchorListingCardViewHolder) {
            this.f4290a = anchorListingCardViewHolder;
        }

        public void onGlobalLayout() {
            ViewTreeObserverHelper.m5639b(this.f4290a.mListingDetails.getViewTreeObserver(), (OnGlobalLayoutListener) this);
            int measuredHeight = this.f4290a.mListingDetails.getMeasuredHeight();
            LayoutParams layoutParams = this.f4290a.mListingImageRegion.getLayoutParams();
            layoutParams.height = measuredHeight;
            this.f4290a.mListingImageRegion.setLayoutParams(layoutParams);
        }
    }

    /* renamed from: com.etsy.android.uikit.viewholder.AnchorListingCardViewHolder.3 */
    class C10443 extends TrackingOnClickListener {
        final /* synthetic */ boolean f4291a;
        final /* synthetic */ ListingCard f4292b;
        final /* synthetic */ ImageView f4293c;
        final /* synthetic */ boolean f4294d;
        final /* synthetic */ AnchorListingCardViewHolder f4295e;

        C10443(AnchorListingCardViewHolder anchorListingCardViewHolder, ITrackedObject[] iTrackedObjectArr, boolean z, ListingCard listingCard, ImageView imageView, boolean z2) {
            this.f4295e = anchorListingCardViewHolder;
            this.f4291a = z;
            this.f4292b = listingCard;
            this.f4293c = imageView;
            this.f4294d = z2;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            boolean z = false;
            if (this.f4291a) {
                Toast.makeText(this.f4295e.itemView.getContext(), R.favorite_own_item_message, 0).show();
            } else if (this.f4295e.mListener != null) {
                this.f4295e.mListener.a(this.f4292b, this.f4293c, this.f4294d, this.f4295e.getAdapterPosition());
                ListingCard listingCard = this.f4292b;
                if (!this.f4294d) {
                    z = true;
                }
                listingCard.setIsFavorite(z);
            }
        }
    }

    /* renamed from: com.etsy.android.uikit.viewholder.AnchorListingCardViewHolder.4 */
    class C10454 extends TrackingOnClickListener {
        final /* synthetic */ ListingCard f4296a;
        final /* synthetic */ AnchorListingCardViewHolder f4297b;

        C10454(AnchorListingCardViewHolder anchorListingCardViewHolder, ITrackedObject[] iTrackedObjectArr, ListingCard listingCard) {
            this.f4297b = anchorListingCardViewHolder;
            this.f4296a = listingCard;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            if (this.f4297b.mListener != null) {
                this.f4297b.mListener.b(this.f4296a);
            }
        }
    }

    public AnchorListingCardViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, @Nullable a aVar, ImageBatch imageBatch) {
        super(layoutInflater.inflate(R.list_item_card_view_anchor_listing, viewGroup, false));
        this.mImageBatch = imageBatch;
        this.mListener = aVar;
        this.mImageView = (ListingFullImageView) findViewById(R.listing_image);
        this.mTextTitle = (TextView) findViewById(R.listing_title);
        this.mTextPrice = (TextView) findViewById(R.listing_price);
        this.mTextShopName = (TextView) findViewById(R.listing_shop);
        this.mFavIcon = (ImageView) findViewById(R.btn_fav);
        this.mCollectionsIcon = (ImageView) findViewById(R.btn_lists);
        this.mRating = (RatingIconView) findViewById(R.rating);
        this.mTextRatingCount = (TextView) findViewById(R.rating_count);
        this.mTextAvailability = (TextView) findViewById(R.listing_availability);
        this.mListingDetails = (LinearLayout) findViewById(R.listing_details);
        this.mListingImageRegion = (FrameLayout) findViewById(R.listing_image_region);
        this.mImageView.setUseStandardRatio(true);
    }

    public void bind(ListingCard listingCard) {
        this.mTextTitle.setText(listingCard.getTitle());
        this.mTextShopName.setText(listingCard.getShopName());
        Resources resources = this.itemView.getResources();
        if (listingCard.getShopAverageRating() > 0.0f) {
            this.mRating.setRating(listingCard.getShopAverageRating());
            this.mRating.setVisibility(0);
            if (listingCard.getShopTotalRatingCount() > 0) {
                this.mTextRatingCount.setText(resources.getString(R.parentheses, new Object[]{bh.m3333a((double) listingCard.getShopTotalRatingCount())}));
                this.mTextRatingCount.setVisibility(0);
            } else {
                this.mTextRatingCount.setVisibility(8);
            }
        } else {
            this.mRating.setVisibility(8);
            this.mTextRatingCount.setVisibility(8);
        }
        if (listingCard.isSoldOut()) {
            this.mTextPrice.setText(R.sold);
            this.mTextAvailability.setVisibility(8);
        } else {
            this.mTextPrice.setText(listingCard.getPrice().format());
            int quantity = listingCard.getQuantity();
            if (quantity > 0) {
                this.mTextAvailability.setText(resources.getQuantityString(R.n_items_available, quantity, new Object[]{Integer.valueOf(quantity)}));
                this.mTextAvailability.setVisibility(0);
            } else {
                this.mTextAvailability.setVisibility(8);
            }
        }
        this.mImageView.setImageInfo(listingCard.getListingImage(), this.mImageBatch);
        bindListingFavIcon(this.mFavIcon, listingCard, false);
        bindListingCollectionsIcon(this.mCollectionsIcon, listingCard);
        this.itemView.setOnClickListener(new C10421(this, new ITrackedObject[]{listingCard}, listingCard));
        ViewTreeObserverHelper.m5636a(this.mListingDetails.getViewTreeObserver(), new C10432(this));
    }

    private void bindListingFavIcon(ImageView imageView, ListingCard listingCard, boolean z) {
        boolean z2 = aj.m1101a().m1118d() && listingCard.isFavorite();
        imageView.setImageResource(z2 ? R.ic_listing_favorite_selector : R.ic_listing_unfavorite_selector);
        imageView.setOnClickListener(new C10443(this, new ITrackedObject[]{listingCard}, z, listingCard, imageView, z2));
    }

    private void bindListingCollectionsIcon(ImageView imageView, ListingCard listingCard) {
        int i = (aj.m1101a().m1118d() && listingCard.hasCollections()) ? 1 : 0;
        imageView.setImageResource(i != 0 ? R.ic_listing_lists_added : R.ic_listing_lists);
        imageView.setOnClickListener(new C10454(this, new ITrackedObject[]{listingCard}, listingCard));
    }

    public void recycle() {
        this.mTextTitle.setText(null);
        this.mTextShopName.setText(null);
        this.mTextPrice.setText(null);
        this.mImageView.cleanUp();
    }
}

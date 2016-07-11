package com.etsy.android.ui.cardview.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.util.ab;
import com.etsy.android.ui.cardview.CardViewHolderFactory;
import com.etsy.android.ui.cardview.p014a.ShopCardClickHandler;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.RatingIconView;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import java.util.List;

public class ShopCardViewHolder extends BaseViewHolder<ShopCard> {
    private static final float FEED_IMAGE_HEIGHT_RATIO = 0.7941176f;
    private final ShopCardClickHandler mCardViewItemClickHandler;
    private ImageView mFavIcon;
    private final ImageBatch mImageBatch;
    private ImageView mImageFeedActionAvatar;
    private int mImageHeight;
    private int mImageWidth;
    protected ImageView[] mListingImages;
    private RatingIconView mRatingIconShop;
    private TextView mShopName;
    public TextView mTextItemCount;
    private TextView mTextShopReviewCount;

    /* renamed from: com.etsy.android.ui.cardview.viewholders.ShopCardViewHolder.1 */
    class C05691 extends TrackingOnClickListener {
        final /* synthetic */ ShopCard f2316a;
        final /* synthetic */ ShopCardViewHolder f2317b;

        C05691(ShopCardViewHolder shopCardViewHolder, ShopCard shopCard) {
            this.f2317b = shopCardViewHolder;
            this.f2316a = shopCard;
        }

        public void onViewClick(View view) {
            if (this.f2317b.mCardViewItemClickHandler != null) {
                this.f2317b.mCardViewItemClickHandler.m3618a(this.f2316a);
            }
        }
    }

    /* renamed from: com.etsy.android.ui.cardview.viewholders.ShopCardViewHolder.2 */
    class C05702 extends TrackingOnClickListener {
        final /* synthetic */ ShopCard f2318a;
        final /* synthetic */ ImageView f2319b;
        final /* synthetic */ boolean f2320c;
        final /* synthetic */ ShopCardViewHolder f2321d;

        C05702(ShopCardViewHolder shopCardViewHolder, ITrackedObject[] iTrackedObjectArr, ShopCard shopCard, ImageView imageView, boolean z) {
            this.f2321d = shopCardViewHolder;
            this.f2318a = shopCard;
            this.f2319b = imageView;
            this.f2320c = z;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            if (this.f2321d.mCardViewItemClickHandler != null) {
                this.f2321d.mCardViewItemClickHandler.m3619a(this.f2318a, this.f2319b, this.f2320c);
            }
        }
    }

    public ShopCardViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, ShopCardClickHandler shopCardClickHandler, ImageBatch imageBatch, boolean z) {
        super(layoutInflater.inflate(2130903304, viewGroup, false));
        this.mImageWidth = 0;
        this.mImageHeight = 0;
        this.mImageBatch = imageBatch;
        this.mCardViewItemClickHandler = shopCardClickHandler;
        this.mListingImages = new ImageView[4];
        this.mListingImages[0] = (ImageView) findViewById(2131755960);
        this.mListingImages[1] = (ImageView) findViewById(2131755961);
        this.mListingImages[2] = (ImageView) findViewById(2131755963);
        this.mListingImages[3] = (ImageView) findViewById(2131755964);
        this.mImageFeedActionAvatar = (ImageView) findViewById(2131755967);
        this.mRatingIconShop = (RatingIconView) findViewById(R.rating);
        this.mTextShopReviewCount = (TextView) findViewById(2131755926);
        this.mTextItemCount = (TextView) findViewById(2131755924);
        this.mShopName = (TextView) findViewById(R.shop_name);
        this.mFavIcon = (ImageView) findViewById(R.btn_fav);
        if (z) {
            int dimensionPixelSize = this.itemView.getResources().getDimensionPixelSize(2131361898);
            getRootView().getLayoutParams().width = dimensionPixelSize;
            this.mImageWidth = dimensionPixelSize / 2;
        } else if (this.mImageWidth == 0 && this.mImageHeight == 0) {
            ab abVar = new ab(getRootView().getContext());
            this.mImageWidth = Math.min(abVar.m3182e(), abVar.m3183f()) / CardViewHolderFactory.m3646a(layoutInflater.getContext());
        }
        this.mImageHeight = (int) (((double) (((float) this.mImageWidth) * FEED_IMAGE_HEIGHT_RATIO)) + 0.5d);
    }

    public void bind(ShopCard shopCard) {
        super.bind(shopCard);
        this.mShopName.setText(shopCard.getShopName());
        this.mImageBatch.m1568a(shopCard.getAvatarUrl(), this.mImageFeedActionAvatar);
        this.mTextItemCount.setText(getRootView().getContext().getResources().getQuantityString(R.item_lowercase_quantity, r0, new Object[]{Integer.valueOf(shopCard.getActiveListingCount())}));
        for (ImageView visibility : this.mListingImages) {
            visibility.setVisibility(8);
        }
        List cardListings = shopCard.getCardListings();
        for (int i = 0; i < cardListings.size(); i++) {
            BaseModelImage listingImage = ((ListingLike) cardListings.get(i)).getListingImage();
            bindImage(listingImage.get4to3ImageUrlForPixelWidth(this.mImageWidth), this.mListingImages[i], this.mImageWidth, this.mImageHeight, listingImage.getImageColor());
        }
        int numRatings = shopCard.getNumRatings();
        if (numRatings == 0) {
            this.mRatingIconShop.setVisibility(8);
            this.mTextShopReviewCount.setVisibility(8);
        } else {
            this.mRatingIconShop.setVisibility(0);
            this.mRatingIconShop.setRating((float) shopCard.getAverageRating());
            this.mTextShopReviewCount.setVisibility(0);
            this.mTextShopReviewCount.setText(getRootView().getContext().getResources().getQuantityString(R.review_counts, numRatings, new Object[]{Integer.valueOf(numRatings)}));
        }
        bindShopFavIcon(this.mFavIcon, shopCard);
        this.itemView.setOnClickListener(new C05691(this, shopCard));
    }

    private void bindImage(String str, ImageView imageView, int i, int i2, int i3) {
        if (str != null && imageView != null) {
            imageView.setVisibility(0);
            imageView.getLayoutParams().width = i;
            imageView.getLayoutParams().height = i2;
            this.mImageBatch.m1571a(str, imageView, i, i2, i3);
        }
    }

    private void bindShopFavIcon(ImageView imageView, ShopCard shopCard) {
        boolean isFavorite = shopCard.isFavorite();
        imageView.setVisibility(0);
        imageView.setImageResource(isFavorite ? R.ic_listing_favorite_selector : R.ic_listing_unfavorite_selector);
        imageView.setOnClickListener(new C05702(this, new ITrackedObject[]{shopCard}, shopCard, imageView, isFavorite));
    }

    public void recycle() {
        for (ImageView imageDrawable : this.mListingImages) {
            imageDrawable.setImageDrawable(null);
        }
    }
}

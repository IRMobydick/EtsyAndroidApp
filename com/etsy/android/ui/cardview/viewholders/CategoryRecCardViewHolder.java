package com.etsy.android.ui.cardview.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.IFullImage;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.homescreen.CategoryRecommendationCard;
import com.etsy.android.lib.util.bh;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.FullImageView;
import com.etsy.android.uikit.view.ListingFullImageView;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import com.etsy.android.uikit.viewholder.a.b;

public class CategoryRecCardViewHolder extends BaseViewHolder<CategoryRecommendationCard> {
    private b mCardViewItemClickHandler;
    private final ImageBatch mImageBatch;
    private final int mImageCount;
    private final LinearLayout mLayoutImages;
    private final TextView mTextSubtitle;
    private final TextView mTextTitle;

    /* renamed from: com.etsy.android.ui.cardview.viewholders.CategoryRecCardViewHolder.1 */
    class C05441 extends TrackingOnClickListener {
        final /* synthetic */ CategoryRecommendationCard f2268a;
        final /* synthetic */ CategoryRecCardViewHolder f2269b;

        C05441(CategoryRecCardViewHolder categoryRecCardViewHolder, CategoryRecommendationCard categoryRecommendationCard) {
            this.f2269b = categoryRecCardViewHolder;
            this.f2268a = categoryRecommendationCard;
        }

        public void onViewClick(View view) {
            if (this.f2269b.mCardViewItemClickHandler != null) {
                this.f2269b.mCardViewItemClickHandler.a(this.f2268a);
            }
        }
    }

    public CategoryRecCardViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, b bVar, ImageBatch imageBatch) {
        super(layoutInflater.inflate(2130903289, viewGroup, false));
        this.mTextTitle = (TextView) findViewById(R.title);
        this.mTextSubtitle = (TextView) findViewById(R.subtitle);
        this.mLayoutImages = (LinearLayout) findViewById(2131755938);
        this.mImageBatch = imageBatch;
        this.mImageCount = this.itemView.getResources().getInteger(2131558402);
        this.mCardViewItemClickHandler = bVar;
    }

    public void bind(CategoryRecommendationCard categoryRecommendationCard) {
        this.mTextTitle.setText(categoryRecommendationCard.getCategoryName());
        int listingCount = categoryRecommendationCard.getListingCount();
        this.mTextSubtitle.setText(bh.m3333a((double) listingCount) + " " + this.itemView.getResources().getQuantityString(R.recommendations_plurals_nt, listingCount));
        bindImages(categoryRecommendationCard);
        this.itemView.setOnClickListener(new C05441(this, categoryRecommendationCard));
    }

    private void bindImages(CategoryRecommendationCard categoryRecommendationCard) {
        this.mLayoutImages.removeAllViews();
        for (int i = 0; i < this.mImageCount; i++) {
            IFullImage iFullImage = null;
            if (categoryRecommendationCard.getListings().size() > i) {
                iFullImage = ((ListingCard) categoryRecommendationCard.getListings().get(i)).getListingImage();
            }
            View listingFullImageView = new ListingFullImageView(this.itemView.getContext());
            listingFullImageView.setScaleType(ScaleType.CENTER_CROP);
            listingFullImageView.setUseStandardRatio(true);
            listingFullImageView.setLayoutParams(new LayoutParams(0, 0, FullImageView.ASPECT_RATIO_SQUARE));
            if (iFullImage != null) {
                listingFullImageView.setImageInfo(iFullImage, this.mImageBatch);
            } else if (i == this.mImageCount - 1) {
                listingFullImageView.setBackgroundResource(R.bg_empty_image);
            } else {
                listingFullImageView.setBackgroundResource(R.bg_empty_image_right_divider);
            }
            this.mLayoutImages.addView(listingFullImageView);
        }
    }
}

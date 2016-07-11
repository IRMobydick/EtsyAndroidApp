package com.etsy.android.ui.cardview.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.homescreen.SeasonalSegmentCard;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.util.ab;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import com.etsy.android.uikit.viewholder.a.b;
import java.util.List;

public class SeasonalSegmentCardViewHolder extends BaseViewHolder<SeasonalSegmentCard> {
    private static final float FEED_IMAGE_HEIGHT_RATIO = 0.75f;
    private final ImageBatch mImageBatch;
    private int mImageHeight;
    private int mImageWidth;
    private b mListener;
    protected ImageView[] mListingImages;
    protected TextView mTitle;

    /* renamed from: com.etsy.android.ui.cardview.viewholders.SeasonalSegmentCardViewHolder.1 */
    class C05681 extends TrackingOnClickListener {
        final /* synthetic */ SeasonalSegmentCard f2314a;
        final /* synthetic */ SeasonalSegmentCardViewHolder f2315b;

        C05681(SeasonalSegmentCardViewHolder seasonalSegmentCardViewHolder, SeasonalSegmentCard seasonalSegmentCard) {
            this.f2315b = seasonalSegmentCardViewHolder;
            this.f2314a = seasonalSegmentCard;
        }

        public void onViewClick(View view) {
            if (this.f2315b.mListener != null) {
                this.f2315b.mListener.a(this.f2314a);
            }
        }
    }

    public SeasonalSegmentCardViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, b bVar, ImageBatch imageBatch) {
        super(layoutInflater.inflate(2130903301, viewGroup, false));
        this.mImageWidth = 0;
        this.mImageHeight = 0;
        this.mImageBatch = imageBatch;
        this.mListener = bVar;
        this.mListingImages = new ImageView[4];
        this.mListingImages[0] = (ImageView) findViewById(2131755960);
        this.mListingImages[1] = (ImageView) findViewById(2131755961);
        this.mListingImages[2] = (ImageView) findViewById(2131755963);
        this.mListingImages[3] = (ImageView) findViewById(2131755964);
        this.mTitle = (TextView) findViewById(R.title);
        if (this.mImageWidth == 0 && this.mImageHeight == 0) {
            ab abVar = new ab(getRootView().getContext());
            this.mImageWidth = Math.min(abVar.m3182e(), abVar.m3183f()) / 3;
            this.mImageHeight = (int) (((float) this.mImageWidth) * FEED_IMAGE_HEIGHT_RATIO);
        }
    }

    public void bind(SeasonalSegmentCard seasonalSegmentCard) {
        super.bind(seasonalSegmentCard);
        for (ImageView imageView : this.mListingImages) {
            imageView.setVisibility(8);
            imageView.setImageBitmap(null);
        }
        this.mTitle.setText(seasonalSegmentCard.getLinkTitle());
        List listings = seasonalSegmentCard.getListings();
        if (listings != null) {
            for (int i = 0; i < listings.size(); i++) {
                BaseModelImage listingImage = ((ListingLike) listings.get(i)).getListingImage();
                bindImage(listingImage.get4to3ImageUrlForPixelWidth(this.mImageWidth), this.mListingImages[i], this.mImageWidth, this.mImageHeight, listingImage.getImageColor());
            }
            this.itemView.setOnClickListener(new C05681(this, seasonalSegmentCard));
        }
    }

    private void bindImage(String str, ImageView imageView, int i, int i2, int i3) {
        if (str != null && imageView != null) {
            imageView.setVisibility(0);
            this.mImageBatch.m1569a(str, imageView, i3);
        }
    }

    public void recycle() {
        for (ImageView imageDrawable : this.mListingImages) {
            imageDrawable.setImageDrawable(null);
        }
    }
}

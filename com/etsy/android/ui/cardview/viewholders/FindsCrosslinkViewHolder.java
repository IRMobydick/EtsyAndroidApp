package com.etsy.android.ui.cardview.viewholders;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.apiv3.FindsCard;
import com.etsy.android.ui.cardview.p014a.FindsClickHandler;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ImageViewWithAspectRatio;
import com.etsy.android.uikit.viewholder.BaseViewHolder;

public class FindsCrosslinkViewHolder extends BaseViewHolder<FindsCard> {
    private static final String TAG;
    private final FindsClickHandler mClickListener;
    protected ImageViewWithAspectRatio[] mHeroImages;
    private final ImageBatch mImageBatch;
    protected ImageViewWithAspectRatio mMainImage;
    protected TextView mPageTitle;

    /* renamed from: com.etsy.android.ui.cardview.viewholders.FindsCrosslinkViewHolder.1 */
    class C05471 extends TrackingOnClickListener {
        final /* synthetic */ FindsCard f2273a;
        final /* synthetic */ FindsCrosslinkViewHolder f2274b;

        C05471(FindsCrosslinkViewHolder findsCrosslinkViewHolder, FindsCard findsCard) {
            this.f2274b = findsCrosslinkViewHolder;
            this.f2273a = findsCard;
        }

        public void onViewClick(View view) {
            this.f2274b.mClickListener.m3575a(this.f2273a);
        }
    }

    static {
        TAG = EtsyDebug.m1891a(FindsCrosslinkViewHolder.class);
    }

    public FindsCrosslinkViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, FindsClickHandler findsClickHandler, ImageBatch imageBatch, boolean z, boolean z2) {
        super(layoutInflater.inflate(2130903179, viewGroup, false));
        this.mImageBatch = imageBatch;
        this.mHeroImages = new ImageViewWithAspectRatio[4];
        this.mHeroImages[0] = (ImageViewWithAspectRatio) findViewById(2131755593);
        this.mHeroImages[1] = (ImageViewWithAspectRatio) findViewById(2131755594);
        this.mHeroImages[2] = (ImageViewWithAspectRatio) findViewById(2131755595);
        this.mHeroImages[3] = (ImageViewWithAspectRatio) findViewById(2131755596);
        this.mMainImage = (ImageViewWithAspectRatio) findViewById(2131755590);
        this.mPageTitle = (TextView) findViewById(2131755592);
        this.mClickListener = findsClickHandler;
        if (z) {
            getRootView().getLayoutParams().width = this.itemView.getResources().getDimensionPixelOffset(2131362273);
        }
        if (z2) {
            int applyDimension = (int) TypedValue.applyDimension(1, layoutInflater.getContext().getResources().getDimension(2131361893), layoutInflater.getContext().getResources().getDisplayMetrics());
            ((MarginLayoutParams) getRootView().getLayoutParams()).setMargins(applyDimension, applyDimension, applyDimension, applyDimension);
        }
    }

    public void bind(FindsCard findsCard) {
        if (findsCard.getTitle() != null) {
            this.mPageTitle.setText(findsCard.getTitle());
        }
        this.mMainImage.setImageInfo((BaseModelImage) findsCard.getImages().get(0), this.mImageBatch);
        this.mHeroImages[0].setImageInfo((BaseModelImage) findsCard.getImages().get(1), this.mImageBatch);
        this.mHeroImages[1].setImageInfo((BaseModelImage) findsCard.getImages().get(2), this.mImageBatch);
        this.mHeroImages[2].setImageInfo((BaseModelImage) findsCard.getImages().get(3), this.mImageBatch);
        this.mHeroImages[3].setImageInfo((BaseModelImage) findsCard.getImages().get(4), this.mImageBatch);
        this.itemView.setOnClickListener(new C05471(this, findsCard));
    }

    public void recycle() {
        for (ImageViewWithAspectRatio imageDrawable : this.mHeroImages) {
            imageDrawable.setImageDrawable(null);
        }
        this.mMainImage.setImageDrawable(null);
    }
}

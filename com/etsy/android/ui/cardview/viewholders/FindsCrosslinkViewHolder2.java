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

public class FindsCrosslinkViewHolder2 extends BaseViewHolder<FindsCard> {
    private static final String TAG;
    private final FindsClickHandler mClickListener;
    protected View mDraftIndicator;
    private final ImageBatch mImageBatch;
    protected ImageViewWithAspectRatio mMainImage;
    protected TextView mPageTitle;

    /* renamed from: com.etsy.android.ui.cardview.viewholders.FindsCrosslinkViewHolder2.1 */
    class C05481 extends TrackingOnClickListener {
        final /* synthetic */ FindsCard f2275a;
        final /* synthetic */ FindsCrosslinkViewHolder2 f2276b;

        C05481(FindsCrosslinkViewHolder2 findsCrosslinkViewHolder2, FindsCard findsCard) {
            this.f2276b = findsCrosslinkViewHolder2;
            this.f2275a = findsCard;
        }

        public void onViewClick(View view) {
            this.f2276b.mClickListener.m3575a(this.f2275a);
        }
    }

    static {
        TAG = EtsyDebug.m1891a(FindsCrosslinkViewHolder2.class);
    }

    public FindsCrosslinkViewHolder2(LayoutInflater layoutInflater, ViewGroup viewGroup, FindsClickHandler findsClickHandler, ImageBatch imageBatch, boolean z, boolean z2) {
        super(layoutInflater.inflate(2130903291, viewGroup, false));
        this.mImageBatch = imageBatch;
        this.mMainImage = (ImageViewWithAspectRatio) findViewById(2131755590);
        this.mPageTitle = (TextView) findViewById(2131755592);
        this.mDraftIndicator = findViewById(2131755940);
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
        int i;
        if (findsCard.getTitle() != null) {
            this.mPageTitle.setText(findsCard.getTitle());
        }
        this.mMainImage.setImageInfo((BaseModelImage) findsCard.getImages().get(0), this.mImageBatch);
        this.itemView.setOnClickListener(new C05481(this, findsCard));
        View view = this.mDraftIndicator;
        if (findsCard.isPublic()) {
            i = 8;
        } else {
            i = 0;
        }
        view.setVisibility(i);
    }

    public void recycle() {
        this.mMainImage.setImageDrawable(null);
    }
}

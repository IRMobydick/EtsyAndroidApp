package com.etsy.android.ui.cardview.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.apiv3.FindsCard;
import com.etsy.android.ui.cardview.p014a.FindsClickHandler;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ImageViewWithAspectRatio;
import com.etsy.android.uikit.viewholder.BaseViewHolder;

public class FindsSmallCrosslinkViewHolder extends BaseViewHolder<FindsCard> {
    private FindsClickHandler mClickListener;
    private ImageViewWithAspectRatio mImage;
    private ImageBatch mImageBatch;
    private TextView mTitle;

    /* renamed from: com.etsy.android.ui.cardview.viewholders.FindsSmallCrosslinkViewHolder.1 */
    class C05491 extends TrackingOnClickListener {
        final /* synthetic */ FindsCard f2277a;
        final /* synthetic */ FindsSmallCrosslinkViewHolder f2278b;

        C05491(FindsSmallCrosslinkViewHolder findsSmallCrosslinkViewHolder, FindsCard findsCard) {
            this.f2278b = findsSmallCrosslinkViewHolder;
            this.f2277a = findsCard;
        }

        public void onViewClick(View view) {
            this.f2278b.mClickListener.m3575a(this.f2277a);
        }
    }

    public FindsSmallCrosslinkViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, FindsClickHandler findsClickHandler, ImageBatch imageBatch) {
        super(layoutInflater.inflate(2130903293, viewGroup, false));
        this.mImageBatch = imageBatch;
        this.mClickListener = findsClickHandler;
        this.mImage = (ImageViewWithAspectRatio) findViewById(R.image);
        this.mTitle = (TextView) findViewById(R.title);
    }

    public void bind(FindsCard findsCard) {
        this.mImage.setImageInfo((BaseModelImage) findsCard.getImages().get(0), this.mImageBatch);
        this.mTitle.setText(findsCard.getTitle());
        getRootView().setOnClickListener(new C05491(this, findsCard));
    }

    public void recycle() {
        this.mImage.setImageDrawable(null);
    }
}

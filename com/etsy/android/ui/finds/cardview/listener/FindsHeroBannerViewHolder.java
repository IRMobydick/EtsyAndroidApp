package com.etsy.android.ui.finds.cardview.listener;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.cardviewelement.FindsHeroBanner;
import com.etsy.android.lib.util.ab;
import com.etsy.android.uikit.view.FullImageView;
import com.etsy.android.uikit.viewholder.BaseViewHolder;

public class FindsHeroBannerViewHolder extends BaseViewHolder<FindsHeroBanner> {
    private ab mDisplayUtil;
    private final ImageBatch mImageBatch;
    private final FullImageView mImageView;
    private final TextView mSubTitle;
    private final TextView mTitle;
    private int mViewType;

    public FindsHeroBannerViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, ImageBatch imageBatch) {
        super(layoutInflater.inflate(2130903292, viewGroup, false));
        this.mImageBatch = imageBatch;
        this.mTitle = (TextView) findViewById(R.txt_title);
        this.mSubTitle = (TextView) findViewById(R.txt_subtitle);
        this.mImageView = (FullImageView) findViewById(R.image);
        this.mDisplayUtil = new ab(layoutInflater.getContext());
    }

    public void bind(FindsHeroBanner findsHeroBanner) {
        super.bind(findsHeroBanner);
        this.mTitle.setText(findsHeroBanner.getTitle());
        CharSequence subtitle = findsHeroBanner.getSubtitle();
        if (TextUtils.isEmpty(subtitle)) {
            this.mSubTitle.setVisibility(8);
        } else {
            this.mSubTitle.setText(subtitle);
            this.mSubTitle.setVisibility(0);
        }
        this.mImageView.setImageInfo(findsHeroBanner.getBannerImage(this.mDisplayUtil.m3180c(), this.mDisplayUtil.m3184g()), this.mImageBatch);
    }

    public void recycle() {
        this.mImageView.cleanUp();
    }
}

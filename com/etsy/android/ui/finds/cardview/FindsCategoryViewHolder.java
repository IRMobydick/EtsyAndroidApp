package com.etsy.android.ui.finds.cardview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.finds.FindsSearchCategory;
import com.etsy.android.ui.finds.cardview.listener.FindsUrlClickHandler;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ImageViewWithAspectRatio;
import com.etsy.android.uikit.viewholder.BaseViewHolder;

public class FindsCategoryViewHolder extends BaseViewHolder<FindsSearchCategory> {
    private FindsUrlClickHandler mClickListener;
    private ImageBatch mImageBatch;
    private ImageViewWithAspectRatio mImageView;
    private TextView mTitleView;

    /* renamed from: com.etsy.android.ui.finds.cardview.FindsCategoryViewHolder.1 */
    class C06961 extends TrackingOnClickListener {
        final /* synthetic */ FindsSearchCategory f2974a;
        final /* synthetic */ FindsCategoryViewHolder f2975b;

        C06961(FindsCategoryViewHolder findsCategoryViewHolder, FindsSearchCategory findsSearchCategory) {
            this.f2975b = findsCategoryViewHolder;
            this.f2974a = findsSearchCategory;
        }

        public void onViewClick(View view) {
            if (this.f2975b.mViewTracker.m1847c().m885c(EtsyConfigKeys.f1195N)) {
                this.f2975b.mClickListener.m4255a(this.f2974a.getUrl());
            } else {
                this.f2975b.mClickListener.m4253a(this.f2974a.getSearchUrl());
            }
        }
    }

    public FindsCategoryViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, FindsUrlClickHandler findsUrlClickHandler, ImageBatch imageBatch) {
        super(layoutInflater.inflate(2130903178, viewGroup, false));
        this.mImageBatch = imageBatch;
        this.mClickListener = findsUrlClickHandler;
        this.mImageView = (ImageViewWithAspectRatio) findViewById(R.listing_image);
        this.mTitleView = (TextView) findViewById(R.txt_title);
    }

    public void bind(FindsSearchCategory findsSearchCategory) {
        this.mTitleView.setText(findsSearchCategory.getTitle());
        Listing listing = findsSearchCategory.getListing();
        if (!(listing == null || listing.getImage() == null)) {
            this.mImageView.setImageInfo(listing.getImage(), this.mImageBatch);
        }
        getRootView().setOnClickListener(new C06961(this, findsSearchCategory));
    }

    public void recycle() {
        this.mImageView.setImageDrawable(null);
    }
}

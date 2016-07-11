package com.etsy.android.ui.cardview.viewholders;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.IFullImage;
import com.etsy.android.lib.models.apiv3.MarketingBanner;
import com.etsy.android.lib.util.ab;
import com.etsy.android.ui.cardview.p014a.DeepLinkUrlClickHandler;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.FullImageView;
import com.etsy.android.uikit.viewholder.BaseViewHolder;

public class BannerViewHolder extends BaseViewHolder<MarketingBanner> {
    private final Button mButtonCallToAction;
    private DeepLinkUrlClickHandler mClickListener;
    private ab mDisplayUtil;
    private final ImageBatch mImageBatch;
    private final FullImageView mImageView;
    private final TextView mSubTitle;
    private final TextView mTitle;

    /* renamed from: com.etsy.android.ui.cardview.viewholders.BannerViewHolder.1 */
    class C05411 extends TrackingOnClickListener {
        final /* synthetic */ MarketingBanner f2262a;
        final /* synthetic */ BannerViewHolder f2263b;

        C05411(BannerViewHolder bannerViewHolder, MarketingBanner marketingBanner) {
            this.f2263b = bannerViewHolder;
            this.f2262a = marketingBanner;
        }

        public void onViewClick(View view) {
            this.f2263b.mClickListener.m3574a(this.f2262a.getUrl());
        }
    }

    public BannerViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, DeepLinkUrlClickHandler deepLinkUrlClickHandler, ImageBatch imageBatch) {
        super(layoutInflater.inflate(2130903287, viewGroup, false));
        this.mClickListener = deepLinkUrlClickHandler;
        this.mImageBatch = imageBatch;
        this.mTitle = (TextView) findViewById(R.txt_title);
        this.mSubTitle = (TextView) findViewById(R.txt_subtitle);
        this.mButtonCallToAction = (Button) findViewById(2131755935);
        this.mImageView = (FullImageView) findViewById(R.image);
        this.mDisplayUtil = new ab(layoutInflater.getContext());
    }

    public void bind(MarketingBanner marketingBanner) {
        boolean z;
        boolean z2 = false;
        super.bind(marketingBanner);
        this.mTitle.setText(marketingBanner.getTitle());
        CharSequence subtitle = marketingBanner.getSubtitle();
        if (TextUtils.isEmpty(subtitle)) {
            this.mSubTitle.setVisibility(8);
        } else {
            this.mSubTitle.setText(subtitle);
            this.mSubTitle.setVisibility(0);
        }
        this.mButtonCallToAction.setText(marketingBanner.getButtonText());
        this.mImageView.setBackgroundColor(marketingBanner.getPlaceholderColor());
        if (this.mImageView.getResources().getBoolean(R.is_phone)) {
            z = false;
        } else {
            z = true;
        }
        int c = this.mDisplayUtil.m3180c();
        if (z || this.mDisplayUtil.m3184g()) {
            z2 = true;
        }
        IFullImage marketingBannerImage = marketingBanner.getMarketingBannerImage(c, z2);
        if (marketingBannerImage != null) {
            this.mImageView.setImageInfo(marketingBannerImage, this.mImageBatch);
        } else {
            this.mImageView.setVisibility(8);
        }
        if (this.mClickListener != null) {
            OnClickListener c05411 = new C05411(this, marketingBanner);
            this.itemView.setOnClickListener(c05411);
            this.mButtonCallToAction.setOnClickListener(c05411);
        }
    }

    public void recycle() {
        this.mImageView.cleanUp();
    }
}

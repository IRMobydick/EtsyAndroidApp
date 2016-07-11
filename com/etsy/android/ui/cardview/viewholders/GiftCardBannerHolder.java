package com.etsy.android.ui.cardview.viewholders;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.models.finds.GiftCardBanner;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.FullImageView;
import com.etsy.android.uikit.viewholder.BaseViewHolder;

public class GiftCardBannerHolder extends BaseViewHolder<GiftCardBanner> {
    protected ImageBatch mImageBatch;
    protected FullImageView mImageView;
    protected TextView mTextCta;
    protected TextView mTextSubtitle;
    protected TextView mTextTitle;

    /* renamed from: com.etsy.android.ui.cardview.viewholders.GiftCardBannerHolder.1 */
    class C05501 extends TrackingOnClickListener {
        final /* synthetic */ LayoutInflater f2279a;
        final /* synthetic */ GiftCardBannerHolder f2280b;

        C05501(GiftCardBannerHolder giftCardBannerHolder, LayoutInflater layoutInflater) {
            this.f2280b = giftCardBannerHolder;
            this.f2279a = layoutInflater;
        }

        public void onViewClick(View view) {
            EtsyLogger.m1966a().m2001d("open_create_gift_card", "finds_page");
            Nav.m4682a((FragmentActivity) this.f2279a.getContext()).m4683a().m4533h();
        }
    }

    public GiftCardBannerHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, ImageBatch imageBatch) {
        super(layoutInflater.inflate(2130903294, viewGroup, false));
        this.mImageBatch = imageBatch;
        this.mTextTitle = (TextView) this.itemView.findViewById(R.title);
        this.mTextCta = (TextView) this.itemView.findViewById(2131755942);
        this.mTextSubtitle = (TextView) this.itemView.findViewById(R.subtitle);
        this.mTextSubtitle.setVisibility(8);
        this.mImageView = (FullImageView) this.itemView.findViewById(2131755941);
        getRootView().setOnClickListener(new C05501(this, layoutInflater));
    }

    public void bind(GiftCardBanner giftCardBanner) {
        this.mTextTitle.setText(giftCardBanner.getText());
        this.mTextCta.setText(giftCardBanner.getCta());
        this.mImageView.setImageInfo(giftCardBanner.getGiftCardBannerImages(), this.mImageBatch);
    }
}

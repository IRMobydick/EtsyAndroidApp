package com.etsy.android.ui.finds.cardview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.models.finds.FindsTwoTitledListingsModule.Footer;
import com.etsy.android.ui.finds.cardview.listener.FindsUrlClickHandler;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.viewholder.BaseViewHolder;

public class FindsTwoTitledListingFooterViewHolder extends BaseViewHolder<Footer> {
    private FindsUrlClickHandler mClickListener;
    private TextView mTextView;

    /* renamed from: com.etsy.android.ui.finds.cardview.FindsTwoTitledListingFooterViewHolder.1 */
    class C06971 extends TrackingOnClickListener {
        final /* synthetic */ Footer f2976a;
        final /* synthetic */ FindsTwoTitledListingFooterViewHolder f2977b;

        C06971(FindsTwoTitledListingFooterViewHolder findsTwoTitledListingFooterViewHolder, Footer footer) {
            this.f2977b = findsTwoTitledListingFooterViewHolder;
            this.f2976a = footer;
        }

        public void onViewClick(View view) {
            if (this.f2977b.mViewTracker.m1847c().m885c(EtsyConfigKeys.f1195N)) {
                this.f2977b.mClickListener.m4255a(this.f2976a.getUrl());
            } else {
                this.f2977b.mClickListener.m4253a(this.f2976a.getTitleLink());
            }
        }
    }

    public FindsTwoTitledListingFooterViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, FindsUrlClickHandler findsUrlClickHandler) {
        super(layoutInflater.inflate(2130903302, viewGroup, false));
        this.mTextView = (TextView) findViewById(2131755943);
        this.mClickListener = findsUrlClickHandler;
    }

    public void bind(Footer footer) {
        if (footer.canDisplay()) {
            this.mTextView.setText(footer.getBottomText());
            getRootView().setVisibility(0);
        } else {
            getRootView().setVisibility(8);
            this.mTextView.setText(null);
        }
        getRootView().setOnClickListener(new C06971(this, footer));
    }
}

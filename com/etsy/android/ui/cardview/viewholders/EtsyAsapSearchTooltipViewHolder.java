package com.etsy.android.ui.cardview.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.uikit.cardview.EtsyAsapSearchTooltip;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import com.etsy.android.uikit.viewholder.a.b;

public class EtsyAsapSearchTooltipViewHolder extends BaseViewHolder<EtsyAsapSearchTooltip> {
    View mClose;
    private b mListener;

    /* renamed from: com.etsy.android.ui.cardview.viewholders.EtsyAsapSearchTooltipViewHolder.1 */
    class C05451 extends TrackingOnClickListener {
        final /* synthetic */ EtsyAsapSearchTooltipViewHolder f2270a;

        C05451(EtsyAsapSearchTooltipViewHolder etsyAsapSearchTooltipViewHolder) {
            this.f2270a = etsyAsapSearchTooltipViewHolder;
        }

        public void onViewClick(View view) {
            if (this.f2270a.mListener != null) {
                this.f2270a.mListener.a(this.f2270a.getAdapterPosition());
            }
        }
    }

    /* renamed from: com.etsy.android.ui.cardview.viewholders.EtsyAsapSearchTooltipViewHolder.2 */
    class C05462 extends TrackingOnClickListener {
        final /* synthetic */ EtsyAsapSearchTooltip f2271a;
        final /* synthetic */ EtsyAsapSearchTooltipViewHolder f2272b;

        C05462(EtsyAsapSearchTooltipViewHolder etsyAsapSearchTooltipViewHolder, EtsyAsapSearchTooltip etsyAsapSearchTooltip) {
            this.f2272b = etsyAsapSearchTooltipViewHolder;
            this.f2271a = etsyAsapSearchTooltip;
        }

        public void onViewClick(View view) {
            if (this.f2272b.mListener != null) {
                this.f2272b.mListener.a(this.f2271a);
            }
        }
    }

    public EtsyAsapSearchTooltipViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, b bVar) {
        super(layoutInflater.inflate(2130903290, viewGroup, false));
        this.mClose = findViewById(2131755939);
        this.mListener = bVar;
    }

    public void bind(EtsyAsapSearchTooltip etsyAsapSearchTooltip) {
        this.mClose.setOnClickListener(new C05451(this));
        getRootView().setOnClickListener(new C05462(this, etsyAsapSearchTooltip));
    }
}

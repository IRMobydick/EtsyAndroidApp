package com.etsy.android.ui.local.marketdetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.util.ah;
import com.etsy.android.lib.util.p013b.LocalMarketFormatter;
import com.etsy.android.lib.util.p013b.LocalMarketsUtil;
import com.etsy.android.ui.homescreen.HomescreenTabsActivity;
import com.etsy.android.ui.local.LocalAnalytics;
import com.etsy.android.ui.local.LocalMarketIntentLauncher;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.adapter.EndlessRecyclerViewAdapter;
import com.etsy.android.uikit.adapter.SectionedRecyclerViewAdapter;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.google.android.gms.maps.c;
import com.google.android.gms.maps.h;
import com.google.android.gms.maps.model.LatLng;

/* renamed from: com.etsy.android.ui.local.marketdetails.h */
class LocalMarketV2Fragment extends SectionedRecyclerViewAdapter {
    final /* synthetic */ LocalMarketV2Fragment f3152a;
    private LocalDetailsHeaderViewHolder f3153b;
    private LocalDetailsFooterBrowseLinkViewHolder f3154c;
    private LocalDetailsHeaderViewHolder f3155d;

    /* renamed from: com.etsy.android.ui.local.marketdetails.h.1 */
    class LocalMarketV2Fragment implements LocalDetailsHeaderViewHolder {
        final /* synthetic */ LocalMarketV2Fragment f3150a;

        /* renamed from: com.etsy.android.ui.local.marketdetails.h.1.1 */
        class LocalMarketV2Fragment implements h {
            final /* synthetic */ LocalMarket f3148a;
            final /* synthetic */ LocalMarketV2Fragment f3149b;

            LocalMarketV2Fragment(LocalMarketV2Fragment localMarketV2Fragment, LocalMarket localMarket) {
                this.f3149b = localMarketV2Fragment;
                this.f3148a = localMarket;
            }

            public void m4369a(LatLng latLng) {
                this.f3149b.m4376c(LocalMarketFormatter.m3300a(this.f3148a));
            }
        }

        LocalMarketV2Fragment(LocalMarketV2Fragment localMarketV2Fragment) {
            this.f3150a = localMarketV2Fragment;
        }

        public void onMapReady(c cVar) {
            m4370a(cVar, this.f3150a.f3152a.mLocalMarket);
        }

        public void m4373a(String str) {
            ah.m3223a(this.f3150a.f3152a.getActivity(), str);
        }

        public void m4372a(TextView textView) {
            EtsyLinkify.m5483a(this.f3150a.f3152a.getActivity(), textView, false);
        }

        public void m4375b(String str) {
            LocalMarketIntentLauncher.m4398a(this.f3150a.f3152a.getActivity(), str);
        }

        public void m4376c(String str) {
            LocalAnalytics.m4313a(this.f3150a.f3152a.mLocalMarket);
            LocalMarketIntentLauncher.m4395a(this.f3150a.f3152a.getActivity(), this.f3150a.f3152a.getAnalyticsContext(), str);
        }

        public void m4371a() {
            LocalAnalytics.m4321b(this.f3150a.f3152a.mLocalMarket);
            LocalMarketIntentLauncher.m4397a(this.f3150a.f3152a.getActivity(), this.f3150a.f3152a.mLocalMarket);
        }

        public void m4374b() {
            Nav.m4682a(this.f3150a.f3152a.getActivity()).m4683a().m4459a(this.f3150a.f3152a.mLocalMarket);
        }

        private void m4370a(c cVar, LocalMarket localMarket) {
            if (cVar != null && localMarket != null) {
                if (LocalMarketsUtil.m3305a(localMarket) != null) {
                    cVar.a(LocalMarketsUtil.m3305a(localMarket));
                }
                cVar.a(new LocalMarketV2Fragment(this, localMarket));
            }
        }
    }

    /* renamed from: com.etsy.android.ui.local.marketdetails.h.2 */
    class LocalMarketV2Fragment implements LocalDetailsFooterBrowseLinkViewHolder {
        final /* synthetic */ LocalMarketV2Fragment f3151a;

        LocalMarketV2Fragment(LocalMarketV2Fragment localMarketV2Fragment) {
            this.f3151a = localMarketV2Fragment;
        }

        public void m4377a() {
            Nav.m4681a(this.f3151a.f3152a.getActivity()).m4514c(HomescreenTabsActivity.TAB_PATH_LOCAL);
        }
    }

    public LocalMarketV2Fragment(LocalMarketV2Fragment localMarketV2Fragment, Context context, ImageBatch imageBatch) {
        this.f3152a = localMarketV2Fragment;
        super(context, imageBatch);
        this.f3153b = new LocalMarketV2Fragment(this);
        this.f3154c = new LocalMarketV2Fragment(this);
    }

    public int getSpanSize(int i, int i2) {
        switch (getItemViewType(i)) {
            case EndlessRecyclerViewAdapter.VIEW_TYPE_ENDLESS_ERROR /*502*/:
                return i2;
            default:
                return super.getSpanSize(i, i2);
        }
    }

    public ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup, int i) {
        if (this.f3155d == null) {
            this.f3155d = new LocalDetailsHeaderViewHolder(this.mInflater, viewGroup, this.f3153b);
        }
        return this.f3155d;
    }

    public void onBindHeaderViewHolder(ViewHolder viewHolder, int i) {
        ((LocalDetailsHeaderViewHolder) viewHolder).m4361a(this.f3152a.getChildFragmentManager(), this.f3152a.mLocalMarket);
    }

    public ViewHolder onCreateFooterViewHolder(ViewGroup viewGroup, int i) {
        if (i == EndlessRecyclerViewAdapter.VIEW_TYPE_ENDLESS_ERROR) {
            return new LocalDetailsFooterBrowseLinkViewHolder(this.mInflater, viewGroup, this.f3154c);
        }
        return new LocalDetailsFooterDisclaimerViewHolder(this.mInflater, viewGroup);
    }

    public void onBindFooterViewHolder(ViewHolder viewHolder, int i) {
        if (viewHolder instanceof LocalDetailsFooterDisclaimerViewHolder) {
            ((LocalDetailsFooterDisclaimerViewHolder) viewHolder).m4353a(this.f3152a.mLocalMarket.isWholesaleStore());
        }
    }
}

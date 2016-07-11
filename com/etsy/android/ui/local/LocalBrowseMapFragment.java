package com.etsy.android.ui.local;

import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.LocalMarketCard;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.etsy.android.ui.local.h */
public class LocalBrowseMapFragment {
    final /* synthetic */ LocalBrowseMapFragment f3071a;
    private final TextView f3072b;
    private final FrameLayout f3073c;
    private final FrameLayout f3074d;
    private final TextView f3075e;
    private final TextView f3076f;
    private final TextView f3077g;
    private final View f3078h;
    private int f3079i;

    /* renamed from: com.etsy.android.ui.local.h.1 */
    class LocalBrowseMapFragment implements LocalMarketCardUtil {
        final /* synthetic */ String f3069a;
        final /* synthetic */ LocalBrowseMapFragment f3070b;

        LocalBrowseMapFragment(LocalBrowseMapFragment localBrowseMapFragment, String str) {
            this.f3070b = localBrowseMapFragment;
            this.f3069a = str;
        }

        public void m4326a() {
            if (this.f3070b.f3071a.mLastOpenedMarker != null && this.f3070b.f3071a.mLastOpenedMarker.a().equals(m4327b()) && this.f3070b.f3071a.mLastOpenedMarker.f()) {
                this.f3070b.f3071a.mLastOpenedMarker.d();
            }
        }

        public String m4327b() {
            return this.f3069a;
        }
    }

    public LocalBrowseMapFragment(LocalBrowseMapFragment localBrowseMapFragment, LayoutInflater layoutInflater) {
        this.f3071a = localBrowseMapFragment;
        this.f3078h = layoutInflater.inflate(2130903399, null, false);
        this.f3072b = (TextView) this.f3078h.findViewById(2131756027);
        this.f3073c = (FrameLayout) this.f3078h.findViewById(2131756028);
        this.f3074d = (FrameLayout) this.f3078h.findViewById(2131756029);
        this.f3075e = (TextView) this.f3078h.findViewById(2131756033);
        this.f3076f = (TextView) this.f3078h.findViewById(2131756030);
        this.f3077g = (TextView) this.f3078h.findViewById(2131756031);
    }

    public void m4329a(ImageBatch imageBatch, LocalMarketCard localMarketCard, String str) {
        if (localMarketCard != null) {
            Resources resources = this.f3078h.getResources();
            boolean equals = LocalMarket.MARKET_TYPE_WHOLESALE_BUYER.equals(localMarketCard.getType());
            this.f3079i = resources.getInteger(2131558459);
            CharSequence dateSubtitle = localMarketCard.getDateSubtitle();
            if (TextUtils.isEmpty(dateSubtitle)) {
                this.f3072b.setVisibility(8);
            } else {
                this.f3072b.setText(dateSubtitle);
                this.f3072b.setVisibility(0);
            }
            List arrayList = new ArrayList();
            if (equals) {
                arrayList.addAll(localMarketCard.getStoreImages());
            } else {
                arrayList.addAll(localMarketCard.getListingImages());
            }
            ImageBatch imageBatch2 = imageBatch;
            LocalMarketCardUtil.m4386a(this.f3073c, this.f3074d, imageBatch2, arrayList, equals, this.f3079i, new LocalBrowseMapFragment(this, str));
            this.f3075e.setText(localMarketCard.getMarketTypeLabelString(resources));
            this.f3076f.setText(localMarketCard.getTitle());
            this.f3077g.setVisibility(0);
            if (localMarketCard.getAttendanceSummary(resources).isEmpty()) {
                this.f3077g.setVisibility(8);
            } else {
                this.f3077g.setText(localMarketCard.getAttendanceSummary(resources));
            }
        }
    }
}

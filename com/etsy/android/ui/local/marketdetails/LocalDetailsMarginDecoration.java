package com.etsy.android.ui.local.marketdetails;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import com.etsy.android.lib.R;
import com.etsy.android.lib.util.aa;
import com.etsy.android.ui.cardview.viewholders.LocalAttendeeShopViewHolder;
import com.etsy.android.ui.cardview.viewholders.LocalInStoreEventViewHolder;
import com.etsy.android.uikit.util.TabletSupportUtil;

/* renamed from: com.etsy.android.ui.local.marketdetails.g */
class LocalDetailsMarginDecoration extends ItemDecoration {
    private final int f3141a;
    private final int f3142b;
    private final int f3143c;
    private final int f3144d;
    private final int f3145e;
    private final boolean f3146f;
    private final SpanSizeLookup f3147g;

    public LocalDetailsMarginDecoration(Context context, SpanSizeLookup spanSizeLookup, int i, int i2, int i3) {
        this.f3147g = spanSizeLookup;
        this.f3141a = i;
        this.f3142b = i2;
        this.f3143c = i3;
        this.f3144d = context.getResources().getDimensionPixelOffset(R.padding_large);
        this.f3145e = context.getResources().getDimensionPixelOffset(2131361857);
        this.f3146f = new TabletSupportUtil(context).m5626f();
    }

    @SuppressLint({"NewApi"})
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        ViewHolder childViewHolder = recyclerView.getChildViewHolder(view);
        if (!(childViewHolder instanceof LocalDetailsFooterDisclaimerViewHolder)) {
            view.setBackgroundColor(view.getResources().getColor(R.white));
            if (aa.m3170d()) {
                view.setElevation(view.getResources().getDimension(R.elevation_level_1));
            }
        }
        if (childViewHolder instanceof LocalAttendeeShopViewHolder) {
            m4368a(view, recyclerView, this.f3142b);
        } else if (childViewHolder instanceof LocalInStoreEventViewHolder) {
            m4368a(view, recyclerView, this.f3143c);
        }
    }

    private void m4368a(View view, RecyclerView recyclerView, int i) {
        int spanIndex = this.f3147g.getSpanIndex(recyclerView.getChildAdapterPosition(view), this.f3141a);
        if (i == 1) {
            view.setPadding(this.f3145e, 0, this.f3145e, this.f3144d);
        } else if (i == 2 && spanIndex == 0) {
            view.setPadding(this.f3145e, 0, this.f3145e / 2, this.f3144d);
        } else if (i == 2 && spanIndex > 0) {
            view.setPadding(this.f3145e / 2, 0, this.f3145e, this.f3144d);
        } else if (this.f3146f) {
            view.setPadding(0, 0, this.f3145e, this.f3144d);
        } else {
            view.setPadding(this.f3145e, 0, this.f3145e, this.f3144d);
        }
    }
}

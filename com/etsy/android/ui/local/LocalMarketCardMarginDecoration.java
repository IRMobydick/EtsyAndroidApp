package com.etsy.android.ui.local;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import com.etsy.android.lib.R;
import com.etsy.android.ui.cardview.viewholders.LocalBrowseMarketViewHolder;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;

/* renamed from: com.etsy.android.ui.local.n */
class LocalMarketCardMarginDecoration extends ItemDecoration {
    private final int f3159a;
    private final int f3160b;
    private final int f3161c;
    private final int f3162d;
    private final boolean f3163e;
    private final SpanSizeLookup f3164f;

    public LocalMarketCardMarginDecoration(Context context, SpanSizeLookup spanSizeLookup, boolean z) {
        this.f3164f = spanSizeLookup;
        this.f3159a = context.getResources().getInteger(2131558410);
        this.f3160b = context.getResources().getDimensionPixelOffset(2131361855);
        this.f3161c = context.getResources().getDimensionPixelOffset(R.fixed_medium);
        this.f3162d = context.getResources().getDimensionPixelOffset(2131361909);
        this.f3163e = z;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        ViewHolder childViewHolder = recyclerView.getChildViewHolder(view);
        if (childViewHolder instanceof LocalBrowseMarketViewHolder) {
            m4379a((LocalBrowseMarketViewHolder) childViewHolder, recyclerView);
        }
    }

    private void m4379a(LocalBrowseMarketViewHolder localBrowseMarketViewHolder, RecyclerView recyclerView) {
        BaseRecyclerViewAdapter baseRecyclerViewAdapter = (BaseRecyclerViewAdapter) recyclerView.getAdapter();
        int dataItemCount = baseRecyclerViewAdapter.getDataItemCount();
        int childAdapterPosition = recyclerView.getChildAdapterPosition(localBrowseMarketViewHolder.itemView);
        int headerCount = childAdapterPosition - baseRecyclerViewAdapter.getHeaderCount();
        int spanIndex = this.f3164f.getSpanIndex(childAdapterPosition, this.f3159a);
        int i = this.f3161c;
        int i2 = this.f3161c;
        childAdapterPosition = this.f3161c;
        int i3 = this.f3161c;
        if (spanIndex == 0) {
            i = this.f3160b;
        }
        if (spanIndex == this.f3159a - 1) {
            childAdapterPosition = this.f3160b;
        }
        if (this.f3163e) {
            if (headerCount < this.f3159a) {
                i2 = this.f3162d;
            }
            if (headerCount >= dataItemCount - this.f3159a) {
                i3 = i2;
                i2 = this.f3162d;
                localBrowseMarketViewHolder.setCardBgMargins(i, i3, childAdapterPosition, i2);
            }
        }
        int i4 = i3;
        i3 = i2;
        i2 = i4;
        localBrowseMarketViewHolder.setCardBgMargins(i, i3, childAdapterPosition, i2);
    }
}

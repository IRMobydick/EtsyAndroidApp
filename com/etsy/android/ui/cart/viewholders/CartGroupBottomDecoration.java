package com.etsy.android.ui.cart.viewholders;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;
import com.etsy.android.uikit.util.TabletSupportUtil;

public class CartGroupBottomDecoration extends ItemDecoration {
    int mCartSpacingPx;
    private TabletSupportUtil mTabletSupportUtil;

    public CartGroupBottomDecoration(Context context) {
        this.mCartSpacingPx = -1;
        this.mCartSpacingPx = context.getResources().getDimensionPixelOffset(2131362311);
        this.mTabletSupportUtil = new TabletSupportUtil(context);
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        if (childAdapterPosition != -1 && this.mTabletSupportUtil.m5621a() && childAdapterPosition == 0) {
            rect.top = this.mCartSpacingPx;
        }
    }
}

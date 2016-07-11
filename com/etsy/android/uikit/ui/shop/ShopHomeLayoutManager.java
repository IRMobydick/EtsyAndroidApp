package com.etsy.android.uikit.ui.shop;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearSmoothScroller;

/* renamed from: com.etsy.android.uikit.ui.shop.c */
class ShopHomeLayoutManager extends LinearSmoothScroller {
    final /* synthetic */ ShopHomeLayoutManager f4116a;

    public ShopHomeLayoutManager(ShopHomeLayoutManager shopHomeLayoutManager, Context context) {
        this.f4116a = shopHomeLayoutManager;
        super(context);
    }

    public PointF computeScrollVectorForPosition(int i) {
        return this.f4116a.computeScrollVectorForPosition(i);
    }

    protected int getVerticalSnapPreference() {
        return -1;
    }
}

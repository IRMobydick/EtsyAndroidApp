package com.etsy.android.uikit.util.shop;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import java.lang.ref.WeakReference;

public class ShopHomeScrollListener extends OnScrollListener {
    private WeakReference<ShopHomeRouter> mRouter;
    private boolean mShouldChangeTabSelectionOnScroll;

    public ShopHomeScrollListener(@NonNull ShopHomeRouter shopHomeRouter) {
        this.mShouldChangeTabSelectionOnScroll = true;
        this.mRouter = new WeakReference(shopHomeRouter);
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        super.onScrolled(recyclerView, i, i2);
        ShopHomeRouter shopHomeRouter = (ShopHomeRouter) this.mRouter.get();
        if (this.mShouldChangeTabSelectionOnScroll && shopHomeRouter != null) {
            shopHomeRouter.changeTabSelectionIfNecessary();
        }
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        super.onScrollStateChanged(recyclerView, i);
        if (i == 0) {
            this.mShouldChangeTabSelectionOnScroll = true;
        }
    }

    public void disableTabChangeObservingUntilScrollCompletion() {
        this.mShouldChangeTabSelectionOnScroll = false;
    }
}

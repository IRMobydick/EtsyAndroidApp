package com.etsy.android.uikit.view;

import android.database.DataSetObserver;

/* renamed from: com.etsy.android.uikit.view.c */
class CustomViewPageIndicator extends DataSetObserver {
    final /* synthetic */ CustomViewPageIndicator f4242a;

    CustomViewPageIndicator(CustomViewPageIndicator customViewPageIndicator) {
        this.f4242a = customViewPageIndicator;
    }

    public void onChanged() {
        super.onChanged();
        this.f4242a.notifyDataSetChanged();
    }
}

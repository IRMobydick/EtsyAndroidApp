package com.etsy.android.uikit.view;

import android.view.View;
import android.widget.TabHost.TabContentFactory;

/* renamed from: com.etsy.android.uikit.view.b */
class CardTabView implements TabContentFactory {
    final /* synthetic */ CardTabView f4241a;

    private CardTabView(CardTabView cardTabView) {
        this.f4241a = cardTabView;
    }

    public View createTabContent(String str) {
        return new View(this.f4241a.getContext());
    }
}

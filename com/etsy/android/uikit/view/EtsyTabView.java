package com.etsy.android.uikit.view;

import android.view.View;
import android.widget.TabHost.TabContentFactory;

/* renamed from: com.etsy.android.uikit.view.e */
class EtsyTabView implements TabContentFactory {
    final /* synthetic */ EtsyTabView f4243a;

    private EtsyTabView(EtsyTabView etsyTabView) {
        this.f4243a = etsyTabView;
    }

    public View createTabContent(String str) {
        return new View(this.f4243a.getContext());
    }
}

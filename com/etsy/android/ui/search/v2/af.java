package com.etsy.android.ui.search.v2;

import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import com.etsy.android.lib.logger.EtsyLogger;
import com.google.android.gms.gcm.Task;

/* compiled from: SearchSuggestionsPager */
class af extends TabLayoutOnPageChangeListener {
    final /* synthetic */ SearchSuggestionsPager f3320a;

    public af(SearchSuggestionsPager searchSuggestionsPager, TabLayout tabLayout) {
        this.f3320a = searchSuggestionsPager;
        super(tabLayout);
    }

    public void onPageSelected(int i) {
        this.f3320a.mSelectedTab = i;
        switch (this.f3320a.mSelectedTab) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                EtsyLogger.m1966a().m1985a("search_items");
                break;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                EtsyLogger.m1966a().m1985a("search_shops");
                break;
            case Task.NETWORK_STATE_ANY /*2*/:
                EtsyLogger.m1966a().m1985a("search_users");
                break;
        }
        super.onPageSelected(i);
    }
}

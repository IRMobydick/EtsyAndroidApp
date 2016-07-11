package com.etsy.android.ui.core;

import android.os.Bundle;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.ui.nav.EtsyEventTracker;
import com.etsy.android.uikit.ui.core.VideoFragment;

public class ShopAboutVideoFragment extends VideoFragment {
    private EtsyId mShopId;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mShopId = new EtsyId(getArguments().getString(ResponseConstants.SHOP_ID));
    }

    protected void onVideoPrepared() {
        EtsyEventTracker.m4581f(getAnalyticsContext(), this.mShopId);
    }

    protected void onVideoError() {
        super.onVideoError();
        EtsyEventTracker.m4585g(getAnalyticsContext(), this.mShopId);
    }
}

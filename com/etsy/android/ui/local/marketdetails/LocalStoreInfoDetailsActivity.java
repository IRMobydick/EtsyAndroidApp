package com.etsy.android.ui.local.marketdetails;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.Nav;

public class LocalStoreInfoDetailsActivity extends BOENavDrawerActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            Nav.m4682a((FragmentActivity) this).m4684b().m4610a((LocalMarket) getIntent().getSerializableExtra(ResponseConstants.LOCAL_MARKET));
        }
        getAppBarHelper().hideAppBar();
    }

    public boolean isTopLevelActivity() {
        return false;
    }
}

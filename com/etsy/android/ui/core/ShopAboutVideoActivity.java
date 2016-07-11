package com.etsy.android.ui.core;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.nav.TrackingBaseActivity;

public class ShopAboutVideoActivity extends TrackingBaseActivity {
    protected int getGraphikTheme() {
        return 2131428191;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getAppBarHelper().hideAppBar();
        if (bundle == null) {
            Nav.m4682a((FragmentActivity) this).m4684b().m4643e(getIntent().getExtras());
        }
    }
}

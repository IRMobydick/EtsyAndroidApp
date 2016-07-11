package com.etsy.android.ui.promos;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.ui.util.EtsyPromoUtil;
import com.etsy.android.uikit.nav.FragmentNavigator.AnimationMode;
import com.etsy.android.uikit.ui.core.TransparentActivity;

public class VersionPromoActivity extends TransparentActivity {
    protected int getGraphikTheme() {
        return 2131428200;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            Nav.m4682a((FragmentActivity) this).m4684b().m4645e().m4625a(AnimationMode.NONE).m4614a(EtsyPromoUtil.m5142a((Context) this));
        }
    }
}

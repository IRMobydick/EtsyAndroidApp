package com.etsy.android.ui.core;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.ui.core.BaseDialogFragment;
import com.etsy.android.uikit.ui.core.DialogLauncherActivity;

public class EtsyDialogLauncherActivity extends DialogLauncherActivity {
    public BaseDialogFragment onStartDialogFragment(Bundle bundle) {
        return Nav.m4682a((FragmentActivity) this).m4684b().m4607h(bundle);
    }

    protected int getGraphikTheme() {
        return 2131427427;
    }
}

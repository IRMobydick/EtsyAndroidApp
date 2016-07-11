package com.etsy.android.ui.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EventTracker;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.ui.core.TransparentActivity;

public class SocialShareDialogActivity extends TransparentActivity {
    private Fragment mFragment;

    protected int getGraphikTheme() {
        return 2131428200;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            this.mFragment = Nav.m4682a((FragmentActivity) this).m4684b().m4622a((int) R.window_container).m4623a(getIntent().getExtras()).m4671w();
        }
        View rootView = getWindow().getDecorView().getRootView();
        if (rootView != null) {
            rootView.setBackgroundColor(getResources().getColor(R.background_main_v2_glass_dark));
        }
    }

    public void onBackPressed() {
        dismiss();
    }

    protected void dismiss() {
        EventTracker.m2041d(getAnalyticsContext());
        getSupportFragmentManager().beginTransaction().setCustomAnimations(0, R.nav_frag_bottom_pop_exit).hide(this.mFragment).commit();
        goBackDelayed();
    }
}

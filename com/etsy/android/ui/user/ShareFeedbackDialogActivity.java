package com.etsy.android.ui.user;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.ui.core.TransparentActivity;

public class ShareFeedbackDialogActivity extends TransparentActivity {
    protected int getGraphikTheme() {
        return 2131428200;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            Nav.m4682a((FragmentActivity) this).m4684b().m4623a(getIntent().getExtras()).m4651g();
        }
    }
}

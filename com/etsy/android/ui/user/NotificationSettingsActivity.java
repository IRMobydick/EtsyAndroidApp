package com.etsy.android.ui.user;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.R;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.Nav;

public class NotificationSettingsActivity extends BOENavDrawerActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(getString(R.notification_settings));
        if (bundle == null && getIntent().getExtras() != null) {
            Nav.m4682a((FragmentActivity) this).m4684b().m4663o();
        }
    }
}

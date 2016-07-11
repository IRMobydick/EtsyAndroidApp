package com.etsy.android.ui.user;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.Nav;

public class PhabletsActivity extends BOENavDrawerActivity {
    private static final String TAG;

    static {
        TAG = EtsyDebug.m1891a(PhabletsActivity.class);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle((int) R.phablets);
        if (bundle == null) {
            Nav.m4682a((FragmentActivity) this).m4684b().m4670v();
        }
    }
}

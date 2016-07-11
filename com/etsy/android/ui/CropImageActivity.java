package com.etsy.android.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.ui.nav.Nav;

public class CropImageActivity extends BOENavDrawerActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            Nav.m4682a((FragmentActivity) this).m4684b().m4623a(getIntent().getExtras()).m4672x();
        }
    }
}

package com.etsy.android.ui.shop;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.Nav;

public class AppreciationPhotoLandingPageActivity extends BOENavDrawerActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle((int) R.appreciation_photo_landing_page_title);
        if (bundle == null) {
            Nav.m4682a((FragmentActivity) this).m4684b().m4646e((EtsyId) getIntent().getSerializableExtra(ResponseConstants.TRANSACTION_ID));
        }
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(2131820552, menu);
        return true;
    }
}

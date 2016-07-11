package com.etsy.android.ui.local.marketdetails;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.Nav;
import org.parceler.Parcels;

public class LocalEventActivity extends BOENavDrawerActivity {
    private boolean mIsBOELocalV2Enabled;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null && extras.containsKey(ResponseConstants.LOCAL_MARKET)) {
                Nav.m4682a((FragmentActivity) this).m4684b().m4611a((LocalMarket) Parcels.m7495a(extras.getParcelable(ResponseConstants.LOCAL_MARKET)), extras.getBoolean("show_local_browse_link"));
            } else if (extras != null) {
                Nav.m4682a((FragmentActivity) this).m4684b().m4613a((EtsyId) extras.getSerializable(ResponseConstants.LOCAL_MARKET_ID), extras.getBoolean("show_local_browse_link"));
            }
        }
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        if (this.mIsBOELocalV2Enabled) {
            getMenuInflater().inflate(2131820552, menu);
        } else {
            getMenuInflater().inflate(2131820551, menu);
        }
        return true;
    }
}

package com.etsy.android.ui.homescreen;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.Nav;

public class RecentlyViewedListingsActivity extends BOENavDrawerActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            Nav.m4682a((FragmentActivity) this).m4684b().m4635c(getIntent().getExtras());
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                return navigateUpAsBack();
            default:
                return false;
        }
    }
}

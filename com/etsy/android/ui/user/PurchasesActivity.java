package com.etsy.android.ui.user;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.Nav;

public class PurchasesActivity extends BOENavDrawerActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            requireSignIn(EtsyAction.VIEW_PURCHASES);
        }
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(2131820551, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                return navigateUpAsBack();
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    protected void onUserSignedInForAction(EtsyAction etsyAction) {
        super.onUserSignedInForAction(etsyAction);
        Nav.m4682a((FragmentActivity) this).m4684b().m4669u();
    }
}

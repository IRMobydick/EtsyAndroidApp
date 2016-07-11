package com.etsy.android.ui.finds;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.etsy.android.lib.R;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.Nav;

public class FindsActivity extends BOENavDrawerActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(getString(R.editors_picks));
        if (bundle == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                Nav.m4682a((FragmentActivity) this).m4684b().m4623a(extras).m4657j(extras.getString("finds_slug"));
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                return navigateUpAsBack();
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(2131820551, menu);
        return true;
    }
}

package com.etsy.android.ui.user;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import com.etsy.android.lib.R;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.Nav;

public class LeaveFeedbackActivity extends BOENavDrawerActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            Nav.m4682a((FragmentActivity) this).m4684b().m4623a(getIntent().getExtras()).m4649f();
        }
        getWindow().setBackgroundDrawableResource(R.white);
        setTitle((int) R.write_a_review);
        setNavStyleModal();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 2131756569:
                return false;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public boolean isTopLevelActivity() {
        return false;
    }
}

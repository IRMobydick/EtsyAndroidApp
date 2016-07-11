package com.etsy.android.ui.user;

import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.core.external.ExternalAccountDelegate;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.ui.dialog.DialogActivity;

public class SettingsActivity extends DialogActivity implements ExternalAccountDelegate {
    private ExternalAccountDelegate mExternalAccountDelegate;

    protected int getGraphikTheme() {
        return 2131428200;
    }

    protected void onShowDialog(OnDismissListener onDismissListener) {
        this.mExternalAccountDelegate = new ExternalAccountDelegate(this);
        Nav.m4682a((FragmentActivity) this).m4686d().m4419a(onDismissListener).m4403a();
    }

    protected void onStart() {
        super.onStart();
        this.mExternalAccountDelegate.m1187a();
    }

    protected void onStop() {
        super.onStop();
        this.mExternalAccountDelegate.m1193b();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.mExternalAccountDelegate.m1188a(i, i2, intent);
    }

    public ExternalAccountDelegate getExternalAccountDelegate() {
        return this.mExternalAccountDelegate;
    }
}

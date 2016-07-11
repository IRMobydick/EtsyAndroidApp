package com.etsy.android.ui.user;

import android.content.DialogInterface.OnDismissListener;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.ui.dialog.DialogActivity;

public class ShareDialogActivity extends DialogActivity {
    protected int getGraphikTheme() {
        return 2131428200;
    }

    protected void onShowDialog(OnDismissListener onDismissListener) {
        Nav.m4682a((FragmentActivity) this).m4686d().m4419a(onDismissListener).m4420a(getIntent().getExtras()).m4426d();
    }
}

package com.etsy.android.ui.user.auth;

import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.util.ExternalAccountUtil.SignInFlow;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.ui.dialog.DialogActivity;

public class ForgotPasswordDialogActivity extends DialogActivity {
    protected int getGraphikTheme() {
        return 2131428200;
    }

    protected void onShowDialog(OnDismissListener onDismissListener) {
        SignInFlow signInFlow = null;
        Bundle extras = getIntent() != null ? getIntent().getExtras() : null;
        if (extras != null) {
            signInFlow = (SignInFlow) extras.getSerializable("sign_in_flow");
            extras.remove("sign_in_flow");
        }
        if (signInFlow == null) {
            signInFlow = SignInFlow.REGULAR;
        }
        Nav.m4682a((FragmentActivity) this).m4686d().m4419a(onDismissListener).m4420a(extras).m4425c(signInFlow);
    }
}

package com.etsy.android.ui.user;

import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.ui.dialog.DialogActivity;

public class SupportFeedbackActivity extends DialogActivity implements SupportFeedbackFragment {
    private static final String TAG;
    private Bundle mArgs;

    static {
        TAG = EtsyDebug.m1891a(SupportFeedbackActivity.class);
    }

    protected int getGraphikTheme() {
        return 2131428200;
    }

    protected void onShowDialog(OnDismissListener onDismissListener) {
        if (getIntent() != null) {
            this.mArgs = getIntent().getExtras();
        }
        Nav.m4682a((FragmentActivity) this).m4686d().m4419a(onDismissListener).m4420a(this.mArgs).m4414a((SupportFeedbackFragment) this);
    }

    public void onMessageSent() {
        finish();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag("supportFeedbackCompose");
        if (findFragmentByTag != null) {
            findFragmentByTag.onActivityResult(i, i2, intent);
        }
    }
}

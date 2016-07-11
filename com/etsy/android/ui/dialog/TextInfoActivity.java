package com.etsy.android.ui.dialog;

import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.ui.dialog.DialogActivity;

public class TextInfoActivity extends DialogActivity {
    private String mContent;
    private String mTitle;

    protected int getGraphikTheme() {
        return 2131428200;
    }

    public void onCreate(Bundle bundle) {
        Intent intent = getIntent();
        this.mTitle = intent.getStringExtra(FindsModule.FIELD_TITLE);
        this.mContent = intent.getStringExtra(FindsModule.FIELD_TEXT);
        super.onCreate(bundle);
    }

    protected void onShowDialog(OnDismissListener onDismissListener) {
        Nav.m4682a((FragmentActivity) this).m4686d().m4419a(onDismissListener).m4417a(this.mTitle, this.mContent);
    }
}

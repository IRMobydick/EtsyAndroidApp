package com.etsy.android.ui.convos;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.convos.SendListener;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.nav.FragmentNavigator.AnimationMode;

public class ConvoComposeActivity extends BOENavDrawerActivity implements SendListener {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent() != null) {
            Nav.m4682a((FragmentActivity) this).m4684b().m4625a(AnimationMode.FADE).m4626a("convoFragment").m4623a(getIntent().getExtras()).m4617a((SendListener) this);
        }
        setNavStyleModal();
    }

    public void onMessageSent() {
        finish();
    }

    public boolean isTopLevelActivity() {
        return false;
    }
}

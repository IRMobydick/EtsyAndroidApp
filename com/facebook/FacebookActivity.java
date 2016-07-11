package com.facebook;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import com.facebook.internal.FacebookDialogFragment;
import com.facebook.internal.ah;
import com.facebook.login.LoginFragment;

public class FacebookActivity extends FragmentActivity {
    private static String FRAGMENT_TAG;
    public static String PASS_THROUGH_CANCEL_ACTION;
    private Fragment singleFragment;

    static {
        PASS_THROUGH_CANCEL_ACTION = "PassThrough";
        FRAGMENT_TAG = "SingleFragment";
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(af.com_facebook_activity_layout);
        Intent intent = getIntent();
        if (PASS_THROUGH_CANCEL_ACTION.equals(intent.getAction())) {
            handlePassThroughError();
            return;
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (findFragmentByTag == null) {
            if (FacebookDialogFragment.TAG.equals(intent.getAction())) {
                findFragmentByTag = new FacebookDialogFragment();
                findFragmentByTag.setRetainInstance(true);
                findFragmentByTag.show(supportFragmentManager, FRAGMENT_TAG);
            } else {
                findFragmentByTag = new LoginFragment();
                findFragmentByTag.setRetainInstance(true);
                supportFragmentManager.beginTransaction().add(ae.com_facebook_fragment_container, findFragmentByTag, FRAGMENT_TAG).commit();
            }
        }
        this.singleFragment = findFragmentByTag;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.singleFragment != null) {
            this.singleFragment.onConfigurationChanged(configuration);
        }
    }

    private void handlePassThroughError() {
        Intent intent = getIntent();
        setResult(0, ah.m5749a(intent, null, ah.m5751a(ah.m5763d(intent))));
        finish();
    }
}

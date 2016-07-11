package com.etsy.android.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity.Header;
import android.view.MenuItem;
import com.etsy.android.lib.R;
import com.etsy.android.lib.devconfigs.BuildConfigSettings;
import com.etsy.android.lib.devconfigs.PrefsFragment;
import com.etsy.android.lib.devconfigs.PrefsSetterHelper;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.LiveActivityCounter;
import com.etsy.android.lib.toolbar.AdminToolbar;
import com.etsy.android.uikit.BasePreferenceActivity;
import com.etsy.android.util.EtsyBuildHelper;
import java.util.List;

public class EtsyPreferenceActivity extends BasePreferenceActivity implements BuildConfigSettings, PrefsSetterHelper {
    private static final String TAG;
    private Resources mResources;
    private boolean mUsingHeaders;

    public EtsyPreferenceActivity() {
        this.mUsingHeaders = false;
    }

    static {
        TAG = EtsyDebug.m1891a(EtsyPreferenceActivity.class);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mResources = getResources();
        setupAppBar();
        if (!this.mUsingHeaders) {
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            Fragment prefsFragment = new PrefsFragment();
            prefsFragment.shouldAddOtherHeaders(!this.mUsingHeaders);
            beginTransaction.add(16908290, prefsFragment, null);
            beginTransaction.commit();
        }
    }

    protected boolean isValidFragment(String str) {
        return true;
    }

    public Preference findPref(int i) {
        return findPreference(this.mResources.getString(i));
    }

    public Preference findPref(String str) {
        return findPreference(str);
    }

    public BuildConfigSettings getBuildConfigs() {
        return EtsyBuildHelper.m5710e();
    }

    public void onBuildHeaders(List<Header> list) {
        if (onIsHidingHeaders() || !onIsMultiPane()) {
            this.mUsingHeaders = false;
            return;
        }
        this.mUsingHeaders = true;
        loadHeadersFromResource(R.preference_headers, list);
    }

    protected void onResume() {
        super.onResume();
        AdminToolbar.m2995b(getClass().getSimpleName());
    }

    public void onStart() {
        super.onStart();
        LiveActivityCounter.m2050a();
    }

    public void onStop() {
        LiveActivityCounter.m2051b();
        super.onStop();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}

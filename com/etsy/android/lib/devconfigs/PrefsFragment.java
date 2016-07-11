package com.etsy.android.lib.devconfigs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import com.etsy.android.lib.R;

public class PrefsFragment extends PreferenceFragment implements PrefsSetterHelper {
    private boolean mAddHeaders;
    private PrefsSetterHelper mHelper;

    public PrefsFragment() {
        this.mAddHeaders = false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getPreferenceManager().setSharedPreferencesName(getString(R.config_prefs_key));
        getPreferenceManager().setSharedPreferencesMode(0);
        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
        BuildConfigSettings buildConfigSettings = null;
        if (getActivity() instanceof BuildConfigSettings) {
            buildConfigSettings = ((BuildConfigSettings) getActivity()).getBuildConfigs();
        }
        this.mHelper = new PrefsSetterHelper(this, getResources(), sharedPreferences, getActivity(), buildConfigSettings);
        if (this.mAddHeaders) {
            addOtherHeaders();
        }
        addPreferencesFromResource(R.preferences);
        this.mHelper.setup();
    }

    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this.mHelper);
    }

    public void onPause() {
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this.mHelper);
    }

    public Preference findPref(int i) {
        return findPreference(getResources().getString(i));
    }

    public Preference findPref(String str) {
        return findPreference(str);
    }

    public void shouldAddOtherHeaders(boolean z) {
        this.mAddHeaders = z;
    }

    private void addOtherHeaders() {
        setPreferenceScreen(this.mHelper.addConfigFlagsPref(getPreferenceManager().createPreferenceScreen(getActivity()), getFragmentManager()));
        addPreferencesFromResource(R.preference_headers_legacy);
    }
}

package com.etsy.android.lib.devconfigs;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Resources;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceScreen;
import android.support.annotation.StringRes;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.eventhorizon.EventHorizonService;
import com.etsy.android.lib.exceptions.CrashReportingTestException;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import org.apache.commons.lang3.StringUtils;

public class PrefsSetterHelper implements OnSharedPreferenceChangeListener {
    private static final String TAG;
    private BuildConfigSettings mBuildConfigs;
    private Context mContext;
    private SharedPreferences mPreferences;
    private Resources mResources;
    private PrefsSetterHelper mWidget;

    /* renamed from: com.etsy.android.lib.devconfigs.PrefsSetterHelper.1 */
    class C04651 implements OnPreferenceClickListener {
        final /* synthetic */ PrefsSetterHelper f1696a;

        C04651(PrefsSetterHelper prefsSetterHelper) {
            this.f1696a = prefsSetterHelper;
        }

        public boolean onPreferenceClick(Preference preference) {
            EtsyConfig.m837a().m870d(this.f1696a.mContext);
            return true;
        }
    }

    /* renamed from: com.etsy.android.lib.devconfigs.PrefsSetterHelper.2 */
    class C04662 implements OnPreferenceClickListener {
        final /* synthetic */ PrefsSetterHelper f1697a;

        C04662(PrefsSetterHelper prefsSetterHelper) {
            this.f1697a = prefsSetterHelper;
        }

        public boolean onPreferenceClick(Preference preference) {
            EtsyLogger.m1966a().m2000d();
            return false;
        }
    }

    /* renamed from: com.etsy.android.lib.devconfigs.PrefsSetterHelper.3 */
    class C04673 implements OnPreferenceClickListener {
        final /* synthetic */ PrefsSetterHelper f1698a;

        C04673(PrefsSetterHelper prefsSetterHelper) {
            this.f1698a = prefsSetterHelper;
        }

        public boolean onPreferenceClick(Preference preference) {
            EtsyLogger.m1966a().m2002e();
            return false;
        }
    }

    /* renamed from: com.etsy.android.lib.devconfigs.PrefsSetterHelper.4 */
    class C04684 implements OnPreferenceClickListener {
        final /* synthetic */ PrefsSetterHelper f1699a;

        C04684(PrefsSetterHelper prefsSetterHelper) {
            this.f1699a = prefsSetterHelper;
        }

        public boolean onPreferenceClick(Preference preference) {
            throw new CrashReportingTestException();
        }
    }

    /* renamed from: com.etsy.android.lib.devconfigs.PrefsSetterHelper.5 */
    class C04695 implements OnPreferenceClickListener {
        final /* synthetic */ FragmentManager f1700a;
        final /* synthetic */ PrefsSetterHelper f1701b;

        C04695(PrefsSetterHelper prefsSetterHelper, FragmentManager fragmentManager) {
            this.f1701b = prefsSetterHelper;
            this.f1700a = fragmentManager;
        }

        public boolean onPreferenceClick(Preference preference) {
            FragmentTransaction beginTransaction = this.f1700a.beginTransaction();
            beginTransaction.replace(16908290, new ConfigFlagsFragment());
            beginTransaction.addToBackStack(null);
            beginTransaction.commit();
            return true;
        }
    }

    static {
        TAG = EtsyDebug.m1891a(PrefsSetterHelper.class);
    }

    public PrefsSetterHelper(PrefsSetterHelper prefsSetterHelper, Resources resources, SharedPreferences sharedPreferences, Context context, BuildConfigSettings buildConfigSettings) {
        this.mWidget = prefsSetterHelper;
        this.mResources = resources;
        this.mPreferences = sharedPreferences;
        this.mContext = context;
        this.mBuildConfigs = buildConfigSettings;
    }

    public void setPreferences(SharedPreferences sharedPreferences) {
        this.mPreferences = sharedPreferences;
    }

    public void setup() {
        setupRefreshServerConfigPref(R.config_refresh_server_info);
        setupSendAllLogsPref(R.config_send_all_logs);
        setupWipeLogsPref(R.config_wipe_logs);
        setupTestCrashReportingPref(R.config_test_crash_reporting);
        setupPrefsSummary(this.mContext);
        setupVMPrefDefault();
    }

    public void setupRefreshServerConfigPref(@StringRes int i) {
        this.mWidget.findPref(i).setOnPreferenceClickListener(new C04651(this));
    }

    public void setupWipeLogsPref(int i) {
        this.mWidget.findPref(i).setOnPreferenceClickListener(new C04662(this));
    }

    public void setupSendAllLogsPref(int i) {
        this.mWidget.findPref(i).setOnPreferenceClickListener(new C04673(this));
    }

    public void setupTestCrashReportingPref(int i) {
        this.mWidget.findPref(i).setOnPreferenceClickListener(new C04684(this));
    }

    public void setupVMPrefDefault() {
        Preference findPref = this.mWidget.findPref(this.mResources.getString(R.config_prefs_vm));
        findPref.setDefaultValue(EtsyConfig.m856i());
        findPref.setSummary(this.mPreferences.getString(this.mResources.getString(R.config_prefs_vm), EtsyConfig.m856i()));
    }

    public void setupPrefsSummary(Context context) {
        setPrefSummary(R.config_prefs_environment);
        if (this.mBuildConfigs != null) {
            setPrefSummary(R.config_build_branch, this.mBuildConfigs.f1703b);
            setPrefSummary(R.config_build_date, this.mBuildConfigs.f1702a);
            setPrefSummary(R.config_build_hash, this.mBuildConfigs.f1704c);
        }
        try {
            setPrefSummary(R.config_build_version, context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
        } catch (Throwable e) {
            EtsyDebug.m1917d(TAG, "Can't find package name " + context.getPackageName(), e);
        }
    }

    public void setPrefSummary(int i) {
        String string = this.mResources.getString(i);
        setPrefSummary(this.mWidget.findPref(string), string);
    }

    public void setPrefSummary(String str) {
        setPrefSummary(this.mWidget.findPref(str), str);
    }

    public void setPrefSummary(int i, String str) {
        Preference findPref = this.mWidget.findPref(this.mResources.getString(i));
        if (findPref != null) {
            findPref.setSummary(str);
        }
    }

    public void setPrefSummary(Preference preference, String str) {
        if (preference != null && !this.mResources.getString(R.config_prefs_admin_toolbar).equals(str) && !this.mResources.getString(R.config_override_location).equals(str) && !this.mResources.getString(R.config_prefs_dev_proxy).equals(str) && !this.mResources.getString(R.config_prefs_output_json).equals(str) && !this.mResources.getString(R.config_prefs_event_horizon).equals(str)) {
            preference.setSummary(this.mPreferences.getString(str, StringUtils.EMPTY));
        }
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        setPrefSummary(str);
        String string = this.mContext.getString(R.config_prefs_environment);
        String string2 = this.mContext.getString(R.config_prefs_vm);
        String string3 = this.mContext.getString(R.config_prefs_dev_proxy);
        String string4 = this.mResources.getString(R.config_prefs_event_horizon);
        if ((str != null && (str.equals(string2) || str.equals(string) || str.equals(string3))) || EtsyConfig.m853e(this.mContext)) {
            EtsyConfig.m837a().m861a(this.mContext);
            EtsyConfig.m837a().m870d(this.mContext);
            aj.m1101a().m1116c();
        }
        if (str != null && str.equals(string4)) {
            boolean z = sharedPreferences.getBoolean(this.mContext.getString(R.config_prefs_event_horizon), false);
            Intent intent = new Intent(this.mContext, EventHorizonService.class);
            if (z) {
                EtsyDebug.m1906b(TAG, "Starting event horizon service");
                this.mContext.startService(intent);
                return;
            }
            EtsyDebug.m1906b(TAG, "Stopping event horizon service");
            this.mContext.stopService(intent);
        }
    }

    public PreferenceScreen addConfigFlagsPref(PreferenceScreen preferenceScreen, FragmentManager fragmentManager) {
        Preference preference = new Preference(this.mContext);
        preference.setTitle(R.config_flags_title);
        preference.setSummary(R.config_flags_flava_text);
        preference.setOnPreferenceClickListener(new C04695(this, fragmentManager));
        preferenceScreen.addPreference(preference);
        return preferenceScreen;
    }
}

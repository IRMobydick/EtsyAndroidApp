package com.etsy.android.lib.devconfigs;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.config.EtsyConfigOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeSet;

public class ConfigFlagsFragment extends PreferenceFragment implements OnPreferenceChangeListener {
    private static final List<String> BLACKLISTED_CONFIG_KEYS;
    private PreferenceScreen mRootScreen;

    /* renamed from: com.etsy.android.lib.devconfigs.ConfigFlagsFragment.1 */
    final class C04631 extends ArrayList<String> {
        C04631() {
            add(EtsyConfigKeys.cJ.m829a());
            add(EtsyConfigKeys.cI.m829a());
        }
    }

    /* renamed from: com.etsy.android.lib.devconfigs.ConfigFlagsFragment.2 */
    class C04642 implements Comparator<Entry<String, EtsyConfigOption>> {
        final /* synthetic */ ConfigFlagsFragment f1695a;

        C04642(ConfigFlagsFragment configFlagsFragment) {
            this.f1695a = configFlagsFragment;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m1749a((Entry) obj, (Entry) obj2);
        }

        public int m1749a(Entry<String, EtsyConfigOption> entry, Entry<String, EtsyConfigOption> entry2) {
            return ((String) entry.getKey()).compareToIgnoreCase((String) entry2.getKey());
        }
    }

    static {
        BLACKLISTED_CONFIG_KEYS = new C04631();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mRootScreen = getPreferenceManager().createPreferenceScreen(getActivity());
        setupPreferences();
        setPreferenceScreen(this.mRootScreen);
    }

    private void setupPreferences() {
        TreeSet treeSet = new TreeSet(new C04642(this));
        treeSet.addAll(EtsyConfig.m837a().m869d().m881a().entrySet());
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (!BLACKLISTED_CONFIG_KEYS.contains(entry.getKey())) {
                createStringPreference((String) entry.getKey(), ((EtsyConfigOption) entry.getValue()).m904b());
            }
        }
    }

    private void createStringPreference(String str, @Nullable String str2) {
        Preference editTextPreference;
        if (str2 == null || !(str2.equals("on") || str2.equals("off"))) {
            editTextPreference = new EditTextPreference(getActivity());
            ((EditTextPreference) editTextPreference).setText(str2);
        } else {
            editTextPreference = new CheckBoxPreference(getActivity());
            ((CheckBoxPreference) editTextPreference).setChecked(str2.equals("on"));
        }
        editTextPreference.setTitle(str);
        editTextPreference.setKey(str);
        editTextPreference.setSummary(str2);
        editTextPreference.setPersistent(false);
        editTextPreference.setOnPreferenceChangeListener(this);
        this.mRootScreen.addPreference(editTextPreference);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        EtsyConfigOption etsyConfigOption;
        if (preference instanceof EditTextPreference) {
            EditTextPreference editTextPreference = (EditTextPreference) preference;
            etsyConfigOption = (EtsyConfigOption) EtsyConfig.m837a().m869d().m881a().get(editTextPreference.getKey());
            if (etsyConfigOption != null) {
                etsyConfigOption.m903a((String) obj);
            }
            editTextPreference.setSummary((String) obj);
            return true;
        } else if (!(preference instanceof CheckBoxPreference)) {
            return false;
        } else {
            CheckBoxPreference checkBoxPreference = (CheckBoxPreference) preference;
            Boolean bool = (Boolean) obj;
            etsyConfigOption = (EtsyConfigOption) EtsyConfig.m837a().m869d().m881a().get(checkBoxPreference.getKey());
            if (etsyConfigOption != null) {
                if (bool.booleanValue()) {
                    etsyConfigOption.m903a("on");
                } else {
                    etsyConfigOption.m903a("off");
                }
            }
            if (bool.booleanValue()) {
                checkBoxPreference.setSummary("on");
            } else {
                checkBoxPreference.setSummary("off");
            }
            return true;
        }
    }
}

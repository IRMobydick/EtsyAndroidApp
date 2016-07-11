package com.appboy.configuration;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import bo.app.C0348n;
import bo.app.cz;
import bo.app.fj;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import com.appboy.support.PackageUtils;
import java.util.Locale;

public class XmlAppConfigurationProvider extends CachedConfigurationProvider {
    private static final String f874a;
    private final Context f875b;

    static {
        f874a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, XmlAppConfigurationProvider.class.getName()});
    }

    public XmlAppConfigurationProvider(Context context) {
        super(context);
        this.f875b = context;
    }

    public String getBaseUrlForRequests() {
        if ("STAGING".equals(getStringValue("com_appboy_server_target", "PROD").toUpperCase(Locale.US))) {
            return "https://sondheim.appboy.com/api/v2/";
        }
        return "https://dev.appboy.com/api/v2/";
    }

    public String getAppboyApiKeyStringFromLocaleMapping(Locale locale) {
        if (locale == null) {
            String str = f874a;
            return null;
        }
        String[] readStringArrayResourceValue = readStringArrayResourceValue("com_appboy_locale_api_key_map", null);
        if (readStringArrayResourceValue == null) {
            str = f874a;
            return null;
        }
        for (String str2 : readStringArrayResourceValue) {
            if (fj.m348a(str2, ",") == 1) {
                String[] split = str2.split(",");
                if (split.length == 2) {
                    String toLowerCase = split[0].trim().toLowerCase();
                    boolean equals = toLowerCase.equals(locale.toString().toLowerCase());
                    if (toLowerCase.equals(locale.getCountry().toLowerCase()) || equals) {
                        return split[1].trim();
                    }
                } else {
                    continue;
                }
            }
        }
        return null;
    }

    public cz getAppboyApiKey() {
        cz czVar = (cz) this.mConfigurationCache.get("com_appboy_api_key");
        if (czVar == null) {
            String string = this.f875b.getSharedPreferences("com_appboy_override_configuration_cache", 0).getString("com_appboy_api_key", null);
            if (string != null) {
                AppboyLogger.m666i(f874a, "Found an override api key.  Using it to configure Appboy.");
            } else {
                string = getAppboyApiKeyStringFromLocaleMapping(Locale.getDefault());
                if (string != null) {
                    AppboyLogger.m666i(f874a, "Found a locale that matches the current locale in appboy.xml.  Using the associated api key");
                } else {
                    string = readStringResourceValue("com_appboy_api_key", null);
                }
            }
            if (string != null) {
                czVar = new cz(string);
                this.mConfigurationCache.put("com_appboy_api_key", czVar);
            }
        }
        if (czVar != null) {
            return czVar;
        }
        AppboyLogger.m664e(f874a, "****************************************************");
        AppboyLogger.m664e(f874a, "**                                                **");
        AppboyLogger.m664e(f874a, "**                 !! WARNING !!                  **");
        AppboyLogger.m664e(f874a, "**                                                **");
        AppboyLogger.m664e(f874a, "**     No API key set in res/values/appboy.xml    **");
        AppboyLogger.m664e(f874a, "** No cached API Key found from Appboy.configure  **");
        AppboyLogger.m664e(f874a, "**         Appboy functionality disabled          **");
        AppboyLogger.m664e(f874a, "**                                                **");
        AppboyLogger.m664e(f874a, "****************************************************");
        throw new RuntimeException("Unable to read the Appboy API key from the res/values/appboy.xml file. See log for more details.");
    }

    public boolean isGcmMessagingRegistrationEnabled() {
        return getBooleanValue("com_appboy_push_gcm_messaging_registration_enabled", false);
    }

    public boolean isAdmMessagingRegistrationEnabled() {
        return getBooleanValue("com_appboy_push_adm_messaging_registration_enabled", false);
    }

    public boolean isLocationCollectionEnabled() {
        return !getBooleanValue("com_appboy_disable_location_collection", false);
    }

    public boolean isBackgroundLocationCollectionEnabled() {
        return getBooleanValue("com_appboy_enable_background_location_collection", false);
    }

    public long getLocationUpdateTimeIntervalInMillis() {
        return 1000 * ((long) getIntValue("com_appboy_location_update_time_interval", -1));
    }

    public float getLocationUpdateDistanceInMeters() {
        return (float) getIntValue("com_appboy_location_update_distance", -1);
    }

    public int getSmallNotificationIconResourceId() {
        return m653a(C0348n.SMALL);
    }

    public int getLargeNotificationIconResourceId() {
        return m653a(C0348n.LARGE);
    }

    private int m653a(C0348n c0348n) {
        String str = c0348n.equals(C0348n.LARGE) ? "com_appboy_push_large_notification_icon" : "com_appboy_push_small_notification_icon";
        if (this.mConfigurationCache.containsKey(str)) {
            return ((Integer) this.mConfigurationCache.get(str)).intValue();
        }
        int identifier = this.f875b.getResources().getIdentifier(str, "drawable", PackageUtils.getResourcePackageName(this.f875b));
        this.mConfigurationCache.put(str, Integer.valueOf(identifier));
        return identifier;
    }

    public int getSessionTimeoutSeconds() {
        return getIntValue("com_appboy_session_timeout", 10);
    }

    public int getVersionCode() {
        if (this.mConfigurationCache.containsKey("version_code")) {
            return ((Integer) this.mConfigurationCache.get("version_code")).intValue();
        }
        int i;
        try {
            i = this.f875b.getPackageManager().getPackageInfo(PackageUtils.getResourcePackageName(this.f875b), 0).versionCode;
        } catch (NameNotFoundException e) {
            AppboyLogger.m664e(f874a, "Unable to read the version code.");
            i = -1;
        }
        this.mConfigurationCache.put("version_code", Integer.valueOf(i));
        return i;
    }

    public String getGcmSenderId() {
        return getStringValue("com_appboy_push_gcm_sender_id", null);
    }

    public int getApplicationIconResourceId() {
        int i = 0;
        if (this.mConfigurationCache.containsKey("application_icon")) {
            return ((Integer) this.mConfigurationCache.get("application_icon")).intValue();
        }
        try {
            i = this.f875b.getPackageManager().getApplicationInfo(this.f875b.getPackageName(), 0).icon;
        } catch (NameNotFoundException e) {
            AppboyLogger.m664e(f874a, String.format("Cannot find package named %s", new Object[]{r1}));
            try {
                i = this.f875b.getPackageManager().getApplicationInfo(this.f875b.getPackageName(), 0).icon;
            } catch (NameNotFoundException e2) {
                AppboyLogger.m664e(f874a, String.format("Cannot find package named %s", new Object[]{r1}));
            }
        }
        this.mConfigurationCache.put("application_icon", Integer.valueOf(i));
        return i;
    }

    public int getDefaultNotificationAccentColor() {
        return getIntValue("com_appboy_default_notification_accent_color", 0);
    }
}

package com.etsy.android.ui.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.support.annotation.NonNull;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.util.EtsyBuildHelper;

/* renamed from: com.etsy.android.ui.local.m */
public class LocalLocationDebugOverride {
    public static Location m4339a(@NonNull Location location) {
        if (EtsyBuildHelper.m5709d()) {
            Context applicationContext = EtsyApplication.get().getApplicationContext();
            SharedPreferences sharedPreferences = applicationContext.getSharedPreferences(applicationContext.getString(R.config_prefs_key), 0);
            if (LocalLocationDebugOverride.m4340a(applicationContext, sharedPreferences)) {
                location.setLatitude(LocalLocationDebugOverride.m4338a(applicationContext, sharedPreferences, location.getLatitude()));
                location.setLongitude(LocalLocationDebugOverride.m4341b(applicationContext, sharedPreferences, location.getLongitude()));
            }
        }
        return location;
    }

    private static boolean m4340a(Context context, SharedPreferences sharedPreferences) {
        return EtsyBuildHelper.m5709d() && sharedPreferences != null && sharedPreferences.getBoolean(context.getString(R.config_override_location), false);
    }

    private static double m4338a(Context context, SharedPreferences sharedPreferences, double d) {
        try {
            d = Double.parseDouble(sharedPreferences.getString(context.getString(R.config_latitude), String.valueOf(d)));
        } catch (NumberFormatException e) {
        }
        return d;
    }

    private static double m4341b(Context context, SharedPreferences sharedPreferences, double d) {
        try {
            d = Double.parseDouble(sharedPreferences.getString(context.getString(R.config_longitude), String.valueOf(d)));
        } catch (NumberFormatException e) {
        }
        return d;
    }
}

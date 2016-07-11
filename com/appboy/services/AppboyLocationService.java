package com.appboy.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;
import bo.app.ej;
import com.appboy.Appboy;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import com.etsy.android.lib.models.ResponseConstants;

public class AppboyLocationService extends Service {
    private static final String f1036a;
    private LocationListener f1037b;
    private LocationManager f1038c;

    static {
        f1036a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyLocationService.class.getName()});
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            AppboyLogger.m666i(f1036a, "Null intent received. Initializing Appboy.");
            Appboy.getInstance(getApplicationContext());
        } else {
            String action = intent.getAction();
            if (action == null) {
                AppboyLogger.m670w(f1036a, "Null intent action received in Appboy location service: " + intent.getDataString());
            } else if (action.equals(getPackageName() + Constants.APPBOY_REQUEST_LOCATION_UPDATES_INTENT_SUFFIX)) {
                action = f1036a;
                new StringBuilder("Requesting background location updates: ").append(intent.getAction());
                if (this.f1038c == null) {
                    this.f1038c = (LocationManager) getApplicationContext().getSystemService(ResponseConstants.LOCATION);
                }
                if (this.f1037b == null) {
                    this.f1037b = new ej(this);
                }
                float floatExtra = intent.getFloatExtra(Constants.APPBOY_LOCATION_DISTANCE_INTERVAL_KEY, Constants.LOCATION_UPDATE_DISTANCE_LOCAL_CONFIG_MINIMUM);
                long longExtra = intent.getLongExtra(Constants.APPBOY_LOCATION_TIME_INTERVAL_KEY, Constants.LOCATION_UPDATE_TIME_INTERVAL_DEFAULT_MS);
                if (this.f1037b != null) {
                    try {
                        this.f1038c.requestLocationUpdates("passive", longExtra, floatExtra, this.f1037b);
                        AppboyLogger.m666i(f1036a, String.format("Collecting locations using %s provider with time interval %ds and update distance %.1fm.", new Object[]{"passive", Long.valueOf(longExtra / 1000), Float.valueOf(floatExtra)}));
                    } catch (Throwable e) {
                        AppboyLogger.m671w(f1036a, "Could not request background location updates. Security exception from insufficient permissions", e);
                    }
                } else {
                    AppboyLogger.m670w(f1036a, "Could not request background location updates. Appboy location listener was null.");
                }
            } else if (action.contains(getPackageName() + Constants.APPBOY_REQUEST_REMOVE_LOCATION_UPDATES_INTENT_SUFFIX)) {
                action = f1036a;
                new StringBuilder("Removing current location updates: ").append(intent.getAction());
                m660b();
            } else {
                AppboyLogger.m670w(f1036a, "Unknown intent received: " + intent.getAction());
            }
        }
        return 1;
    }

    private void m660b() {
        if (this.f1037b != null) {
            try {
                this.f1038c.removeUpdates(this.f1037b);
            } catch (Throwable e) {
                AppboyLogger.m671w(f1036a, "Could not remove background location updates. Security exception from insufficient permissions", e);
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        m660b();
    }

    public static void requestInitialization(Context context) {
        String str = f1036a;
        context.sendBroadcast(new Intent(context.getPackageName() + Constants.APPBOY_REQUEST_INIT_LOCATION_SERVICE_INTENT_SUFFIX));
    }
}

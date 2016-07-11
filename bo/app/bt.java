package bo.app;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import com.appboy.Constants;
import com.appboy.configuration.XmlAppConfigurationProvider;
import com.appboy.services.AppboyLocationService;
import com.appboy.support.AppboyLogger;
import com.appboy.support.PermissionUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.messaging.CartRefreshDelegate;

public final class bt implements ci {
    private static final String f167a;
    private final Context f168b;
    private final String f169c;
    private final LocationManager f170d;
    private final ce f171e;
    private final boolean f172f;
    private final boolean f173g;
    private boolean f174h;
    private long f175i;
    private float f176j;
    private String f177k;

    static /* synthetic */ void m48a(bt btVar, Intent intent) {
        try {
            AppboyLogger.m666i(f167a, String.format("Single location update received from %s: %s", new Object[]{intent.getStringExtra(CartRefreshDelegate.ARG_ORIGIN), intent.getAction()}));
            Location location = (Location) intent.getExtras().get(ResponseConstants.LOCATION);
            if (location != null) {
                btVar.m54a(new dg(location.getLatitude(), location.getLongitude(), Double.valueOf(location.getAltitude()), Double.valueOf((double) location.getAccuracy())));
            } else {
                AppboyLogger.m670w(f167a, "Failed to process location update. Received location was null.");
            }
        } catch (Throwable e) {
            AppboyLogger.m665e(f167a, "Failed to process location update.", e);
        }
    }

    static {
        f167a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, bt.class.getName()});
    }

    public bt(Context context, ce ceVar, XmlAppConfigurationProvider xmlAppConfigurationProvider, ey eyVar) {
        boolean z;
        boolean z2 = true;
        this.f174h = false;
        this.f175i = Constants.LOCATION_UPDATE_TIME_INTERVAL_DEFAULT_MS;
        this.f176j = Constants.LOCATION_UPDATE_DISTANCE_LOCAL_CONFIG_MINIMUM;
        this.f168b = context;
        this.f169c = context.getPackageName();
        this.f171e = ceVar;
        this.f170d = (LocationManager) context.getSystemService(ResponseConstants.LOCATION);
        if (xmlAppConfigurationProvider.isLocationCollectionEnabled()) {
            AppboyLogger.m666i(f167a, "Location collection enabled via appboy.xml configuration.");
            z = true;
        } else {
            AppboyLogger.m666i(f167a, "Location collection disabled via appboy.xml configuration.");
            z = false;
        }
        this.f172f = z;
        if (eyVar.m296b()) {
            if (eyVar.m297c()) {
                AppboyLogger.m666i(f167a, "Background location collection enabled via server configuration.");
                z = true;
            } else {
                AppboyLogger.m666i(f167a, "Background location collection disabled via server configuration.");
                z = false;
            }
        } else if (xmlAppConfigurationProvider.isBackgroundLocationCollectionEnabled()) {
            AppboyLogger.m666i(f167a, "Background location collection enabled via appboy.xml configuration.");
            z = true;
        } else {
            AppboyLogger.m666i(f167a, "Background location collection disabled via appboy.xml configuration.");
            z = false;
        }
        this.f174h = z;
        if (!fi.m347a(this.f168b, AppboyLocationService.class)) {
            AppboyLogger.m666i(f167a, String.format("Appboy location service is not available. Declare <service android:name=\"com.appboy.services.AppboyLocationService\"/> in your AndroidManifest.xml to enable Appboy location service.", new Object[0]));
            z2 = false;
        }
        this.f173g = z2;
        if (eyVar.m298d() >= 0) {
            this.f175i = eyVar.m298d();
            AppboyLogger.m666i(f167a, "Time interval override set via server configuration for background location collection: " + (this.f175i / 1000) + "s.");
        } else if (xmlAppConfigurationProvider.getLocationUpdateTimeIntervalInMillis() > Constants.LOCATION_UPDATE_TIME_INTERVAL_LOCAL_CONFIG_MINIMUM_MS) {
            this.f175i = xmlAppConfigurationProvider.getLocationUpdateTimeIntervalInMillis();
            AppboyLogger.m666i(f167a, "Time interval override set via appboy.xml configuration for background location collection: " + (this.f175i / 1000) + "s.");
        } else {
            this.f175i = Constants.LOCATION_UPDATE_TIME_INTERVAL_DEFAULT_MS;
            AppboyLogger.m666i(f167a, "Time interval override set to default for background location collection: " + (this.f175i / 1000) + "s.");
        }
        if (eyVar.m299e() >= 0.0f) {
            this.f176j = eyVar.m299e();
            AppboyLogger.m666i(f167a, "Distance threshold override set via server configuration for background location collection: " + this.f176j + "m.");
        } else if (xmlAppConfigurationProvider.getLocationUpdateDistanceInMeters() > Constants.LOCATION_UPDATE_DISTANCE_LOCAL_CONFIG_MINIMUM) {
            this.f176j = xmlAppConfigurationProvider.getLocationUpdateDistanceInMeters();
            AppboyLogger.m666i(f167a, "Distance threshold override set via appboy.xml configuration for background location collection: " + this.f176j + "m.");
        } else {
            this.f176j = Constants.LOCATION_UPDATE_DISTANCE_LOCAL_CONFIG_MINIMUM;
            AppboyLogger.m666i(f167a, "Distance threshold override set to default for background location collection: " + this.f176j + "m.");
        }
        BroadcastReceiver buVar = new bu(this);
        IntentFilter intentFilter = new IntentFilter(this.f169c + Constants.APPBOY_SINGLE_LOCATION_UPDATE_INTENT_SUFFIX);
        intentFilter.addAction(this.f169c + Constants.APPBOY_REQUEST_INIT_LOCATION_SERVICE_INTENT_SUFFIX);
        this.f168b.registerReceiver(buVar, intentFilter);
        if (!PermissionUtils.hasPermission(this.f168b, "android.permission.ACCESS_FINE_LOCATION")) {
            m51d();
        }
    }

    private boolean m49a(String str) {
        if (this.f173g) {
            Intent intent = new Intent(str).setClass(this.f168b, AppboyLocationService.class);
            if (str.equals(this.f169c + Constants.APPBOY_REQUEST_LOCATION_UPDATES_INTENT_SUFFIX)) {
                intent.putExtra(Constants.APPBOY_LOCATION_DISTANCE_INTERVAL_KEY, this.f176j);
                intent.putExtra(Constants.APPBOY_LOCATION_TIME_INTERVAL_KEY, this.f175i);
            }
            this.f168b.startService(intent);
            return true;
        }
        AppboyLogger.m666i(f167a, String.format("Appboy Location service is not available. Did not send intent to service: %s", new Object[]{str}));
        return false;
    }

    private void m51d() {
        if (this.f173g) {
            AppboyLogger.m666i(f167a, "Stopping Appboy location service if currently running.");
            this.f168b.stopService(new Intent().setClass(this.f168b, AppboyLocationService.class));
            return;
        }
        AppboyLogger.m666i(f167a, "Did not attempt to stop service. Appboy Location service is not available.");
    }

    public final boolean m54a(dg dgVar) {
        try {
            this.f171e.m57a(da.m150a(dgVar));
            return true;
        } catch (Throwable e) {
            AppboyLogger.m671w(f167a, "Failed to log location recorded event.", e);
            return false;
        }
    }

    public final boolean m53a() {
        if (!this.f172f) {
            AppboyLogger.m666i(f167a, "Did not request single location update. Location collection is disabled.");
            return false;
        } else if (PermissionUtils.hasPermission(this.f168b, "android.permission.ACCESS_FINE_LOCATION") || PermissionUtils.hasPermission(this.f168b, "android.permission.ACCESS_COARSE_LOCATION")) {
            String str;
            if (PermissionUtils.hasPermission(this.f168b, "android.permission.ACCESS_FINE_LOCATION")) {
                str = "passive";
            } else if (this.f177k != null) {
                str = this.f177k;
            } else {
                Criteria criteria = new Criteria();
                criteria.setAccuracy(2);
                criteria.setPowerRequirement(1);
                this.f177k = this.f170d.getBestProvider(criteria, true);
                str = this.f177k;
            }
            if (fj.m354c(str)) {
                str = f167a;
                return false;
            }
            try {
                String str2 = f167a;
                Intent intent = new Intent(this.f169c + Constants.APPBOY_SINGLE_LOCATION_UPDATE_INTENT_SUFFIX);
                intent.putExtra(CartRefreshDelegate.ARG_ORIGIN, "Appboy location manager");
                this.f170d.requestSingleUpdate(str, PendingIntent.getBroadcast(this.f168b, 0, intent, 134217728));
                return true;
            } catch (Throwable e) {
                AppboyLogger.m671w(f167a, "Failed to request single location update due to security exception from insufficient permissions.", e);
                return false;
            } catch (Throwable e2) {
                AppboyLogger.m671w(f167a, "Failed to request single location update due to exception.", e2);
                return false;
            }
        } else {
            AppboyLogger.m666i(f167a, "Did not request single location update. Fine grained location permissions not found.");
            return false;
        }
    }

    public final void m52a(cx cxVar) {
        if (cxVar == null) {
            AppboyLogger.m670w(f167a, "Could not reset background location collection interval. Server config was null.");
            return;
        }
        if (cxVar.f276g >= 0) {
            this.f175i = cxVar.f276g;
            AppboyLogger.m666i(f167a, "Time interval override reset via server configuration for background location collection: " + (this.f175i / 1000) + "s.");
        }
        if (cxVar.f277h >= 0.0f) {
            this.f176j = cxVar.f277h;
            AppboyLogger.m666i(f167a, "Distance threshold override reset via server configuration for background location collection: " + this.f176j + "m.");
        }
        if (!cxVar.f274e) {
            return;
        }
        if (cxVar.f275f) {
            this.f174h = true;
            AppboyLogger.m666i(f167a, "Background location collection enabled via server configuration. Requesting location updates.");
            m55b();
            return;
        }
        this.f174h = false;
        AppboyLogger.m666i(f167a, "Background location collection disabled via server configuration. Stopping any active Appboy location service.");
        m51d();
    }

    public final boolean m55b() {
        boolean z = false;
        if (!this.f172f) {
            AppboyLogger.m666i(f167a, "Did not request background location updates. Location collection is disabled.");
        } else if (!this.f174h) {
            AppboyLogger.m666i(f167a, "Did not request background location updates. Background location collection is disabled.");
        } else if (PermissionUtils.hasPermission(this.f168b, "android.permission.ACCESS_FINE_LOCATION")) {
            try {
                m49a(this.f169c + Constants.APPBOY_REQUEST_REMOVE_LOCATION_UPDATES_INTENT_SUFFIX);
                z = m49a(this.f169c + Constants.APPBOY_REQUEST_LOCATION_UPDATES_INTENT_SUFFIX);
            } catch (Throwable e) {
                AppboyLogger.m671w(f167a, "Could not request location updates due to exception.", e);
            }
        } else {
            AppboyLogger.m666i(f167a, "Did not request background location updates. Fine grained location permissions not found.");
        }
        return z;
    }
}

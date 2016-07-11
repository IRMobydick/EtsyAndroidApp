package bo.app;

import android.app.PendingIntent;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import com.appboy.Constants;
import com.appboy.services.AppboyLocationService;
import com.appboy.support.AppboyLogger;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.messaging.CartRefreshDelegate;

public final class ej implements LocationListener {
    final /* synthetic */ AppboyLocationService f387a;

    public ej(AppboyLocationService appboyLocationService) {
        this.f387a = appboyLocationService;
    }

    public final void onLocationChanged(Location location) {
        if (location != null) {
            AppboyLocationService.f1036a;
            Intent intent = new Intent(this.f387a.getApplicationContext().getPackageName() + Constants.APPBOY_SINGLE_LOCATION_UPDATE_INTENT_SUFFIX);
            intent.putExtra(ResponseConstants.LOCATION, location);
            intent.putExtra(CartRefreshDelegate.ARG_ORIGIN, "Appboy location service");
            try {
                this.f387a.f1038c.requestSingleUpdate("passive", PendingIntent.getBroadcast(this.f387a.getApplicationContext(), 0, intent, 134217728));
            } catch (Throwable e) {
                AppboyLogger.m671w(AppboyLocationService.f1036a, "Could not request single location update. Security exception from insufficient permissions", e);
            }
        }
    }

    public final void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public final void onProviderEnabled(String str) {
    }

    public final void onProviderDisabled(String str) {
        if (str != null && str.equals("passive")) {
            this.f387a.m660b();
        }
    }
}

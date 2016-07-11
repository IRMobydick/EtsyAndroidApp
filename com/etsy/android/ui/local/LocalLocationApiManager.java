package com.etsy.android.ui.local;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.util.DeviceSettings;
import com.etsy.android.lib.util.aw;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.location.C0758c;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.d;

/* renamed from: com.etsy.android.ui.local.k */
public class LocalLocationApiManager implements GoogleApiClient$ConnectionCallbacks, GoogleApiClient$OnConnectionFailedListener, C0758c {
    private static final String f3082a;
    @Nullable
    private LocalLocationApiManager f3083b;
    @Nullable
    private Location f3084c;
    @NonNull
    private GoogleApiClient f3085d;
    private boolean f3086e;

    static {
        f3082a = EtsyDebug.m1891a(LocalLocationApiManager.class);
    }

    public LocalLocationApiManager(@NonNull Activity activity, @Nullable LocalLocationApiManager localLocationApiManager) {
        this.f3086e = false;
        this.f3083b = localLocationApiManager;
        this.f3085d = new Builder(activity).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(d.a).build();
    }

    public void m4333a() {
        if (!this.f3086e) {
            this.f3085d.connect();
        }
    }

    public void m4335b() {
        if (this.f3085d.isConnected()) {
            d.b.a(this.f3085d, this);
        }
        this.f3085d.disconnect();
    }

    public void m4336c() {
        this.f3083b = null;
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"})
    public void m4337d() {
        this.f3084c = null;
        EtsyDebug.m1912c(f3082a, "refreshing Local location");
        if (this.f3085d.isConnected()) {
            m4332e();
        } else {
            m4333a();
        }
    }

    public void onConnected(Bundle bundle) {
        this.f3086e = false;
        m4332e();
    }

    private void m4332e() {
        if (this.f3083b != null && this.f3085d.getContext() != null && aw.m3273a(this.f3085d.getContext(), "android.permission.ACCESS_FINE_LOCATION") == 0 && aw.m3273a(this.f3085d.getContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            Location a = d.b.a(this.f3085d);
            if (a != null) {
                this.f3083b.hideEnableLocationMessage();
                m4334a(a);
            } else if (DeviceSettings.m3434a(this.f3085d.getContext().getApplicationContext())) {
                this.f3083b.showWaitingForLocationMessage();
                EtsyDebug.m1912c(f3082a, "Connected to Google API with location services enabled, but no location available");
                EtsyLogger.m1966a().m1996b(f3082a, "Connected to Google API with location services enabled, but no location available");
                LocationRequest locationRequest = new LocationRequest();
                locationRequest.setPriority(R.AppCompatTheme_editTextStyle);
                d.b.a(this.f3085d, locationRequest, this);
            } else {
                EtsyDebug.m1912c(f3082a, "Location not enabled on device");
                this.f3083b.showEnableLocationMessage();
            }
        }
    }

    public void m4334a(Location location) {
        if (location != null) {
            d.b.a(this.f3085d, this);
            EtsyDebug.m1912c(f3082a, "New location obtained");
        }
        if (this.f3084c == null && location != null && this.f3083b != null) {
            LocalAnalytics.m4312a(location);
            this.f3084c = LocalLocationDebugOverride.m4339a(location);
            this.f3083b.hideEnableLocationMessage();
            this.f3083b.onLocationReceived(location);
        }
    }

    public void onConnectionSuspended(int i) {
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        EtsyDebug.m1916d(f3082a, "Google API maps client failed. Connection result: " + connectionResult);
        if (!this.f3086e) {
            this.f3086e = true;
            m4331a(connectionResult);
        }
    }

    private void m4331a(ConnectionResult connectionResult) {
        if (this.f3083b != null) {
            if (connectionResult.hasResolution()) {
                this.f3083b.showGooglePlayResolution(connectionResult);
                return;
            }
            int i;
            switch (connectionResult.getErrorCode()) {
                case Task.NETWORK_STATE_ANY /*2*/:
                    i = R.update_google_play_services;
                    break;
                case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                    i = R.enable_google_play_services;
                    break;
                default:
                    i = R.get_google_play_services;
                    break;
            }
            this.f3083b.showGooglePlayErrorMessage(i);
        }
    }
}

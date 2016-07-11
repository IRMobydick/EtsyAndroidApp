package bo.app;

import android.content.Context;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import java.lang.reflect.Method;

final class cc implements Runnable {
    final /* synthetic */ cb f232a;

    cc(cb cbVar) {
        this.f232a = cbVar;
    }

    public final void run() {
        try {
            cb.f221l = this.f232a.f222a.getBoolean(Constants.APPBOY_PUSH_ACCENT_KEY, false);
            Method a = fh.m346a("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", Context.class);
            if (a != null) {
                Object a2 = fh.m344a(null, a, this.f232a.f223c);
                if ((a2 instanceof Integer) && ((Integer) a2).intValue() == 0) {
                    a = fh.m346a("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", Context.class);
                    if (a != null) {
                        Object a3 = fh.m344a(null, a, this.f232a.f223c);
                        if (a3 != null) {
                            Method a4 = fh.m345a(a3.getClass(), "getId", new Class[0]);
                            a = fh.m345a(a3.getClass(), "isLimitAdTrackingEnabled", new Class[0]);
                            if (a4 != null && a != null) {
                                if (((Boolean) fh.m344a(a3, a, new Object[0])).booleanValue()) {
                                    AppboyLogger.m666i(cb.f220b, "Google Play Services limit ad tracking enabled. User is opted out of interest-based ads. Not requesting Advertising Id.");
                                } else {
                                    cb.m98a(this.f232a, (String) fh.m344a(a3, a4, new Object[0]));
                                }
                            }
                        }
                    }
                }
            }
        } catch (Throwable e) {
            AppboyLogger.m665e(cb.f220b, "Failed to get ad id.", e);
        }
    }
}

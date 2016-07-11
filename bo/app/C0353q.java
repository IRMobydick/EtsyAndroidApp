package bo.app;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

/* renamed from: bo.app.q */
public final class C0353q implements C0352y {
    private static final String f811a;
    private ah f812b;
    private boolean f813c;
    private boolean f814d;

    public C0353q() {
        this.f812b = ah.UNKNOWN;
        this.f813c = false;
        this.f814d = false;
    }

    static {
        f811a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, C0353q.class.getName()});
    }

    public final ah m626a() {
        return this.f812b;
    }

    public final void m627a(Intent intent, ConnectivityManager connectivityManager) {
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                boolean booleanExtra = intent.getBooleanExtra("noConnectivity", false);
                if (activeNetworkInfo == null || booleanExtra) {
                    this.f812b = ah.NONE;
                    this.f814d = false;
                    this.f813c = false;
                    return;
                }
                this.f814d = activeNetworkInfo.isConnectedOrConnecting();
                this.f813c = activeNetworkInfo.isRoaming();
                switch (activeNetworkInfo.getType()) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        switch (activeNetworkInfo.getSubtype()) {
                            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                                this.f812b = ah.THREE_G;
                                return;
                            case CommonStatusCodes.ERROR /*13*/:
                                this.f812b = ah.FOUR_G;
                                return;
                            default:
                                this.f812b = ah.TWO_G;
                                return;
                        }
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.f812b = ah.WIFI;
                        return;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.f812b = ah.UNKNOWN;
                        return;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        this.f812b = ah.UNKNOWN;
                        return;
                    case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                        this.f812b = ah.UNKNOWN;
                        return;
                    case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                        this.f812b = ah.UNKNOWN;
                        return;
                    case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                        this.f812b = ah.WIFI;
                        return;
                    case CommonStatusCodes.NETWORK_ERROR /*7*/:
                        this.f812b = ah.UNKNOWN;
                        return;
                    case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                        this.f812b = ah.UNKNOWN;
                        return;
                    case CommonStatusCodes.SERVICE_INVALID /*9*/:
                        this.f812b = ah.UNKNOWN;
                        return;
                    default:
                        this.f812b = ah.UNKNOWN;
                        return;
                }
            } catch (Throwable e) {
                AppboyLogger.m665e(f811a, "Failed to get active network information.  Ensure the permission android.permission.ACCESS_NETWORK_STATE is defined in your AndroidManifest.xml", e);
                return;
            }
        }
        AppboyLogger.m670w(f811a, String.format("Unexpected system broadcast received [%s]", new Object[]{r0}));
    }
}

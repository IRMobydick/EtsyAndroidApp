package android.support.v4.net;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

class ConnectivityManagerCompatHoneycombMR2 {
    ConnectivityManagerCompatHoneycombMR2() {
    }

    public static boolean isActiveNetworkMetered(ConnectivityManager connectivityManager) {
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return true;
        }
        switch (activeNetworkInfo.getType()) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
            case Task.NETWORK_STATE_ANY /*2*/:
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
            case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                return true;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
            case CommonStatusCodes.NETWORK_ERROR /*7*/:
            case CommonStatusCodes.SERVICE_INVALID /*9*/:
                return false;
            default:
                return true;
        }
    }
}

package com.etsy.android.lib.util;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.etsy.android.uikit.receiver.DataStateReceiver;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import org.apache.commons.lang3.StringUtils;

public class NetworkUtils {
    private static NetworkUtils f1982d;
    DataStateReceiver f1983a;
    an f1984b;
    private volatile int f1985c;

    public enum NetworkType {
        WIFI("Wifi"),
        WWAN("WWAN"),
        NONE("NoConnection");
        
        private String type;

        private NetworkType(String str) {
            this.type = str;
        }

        public String getType() {
            return this.type;
        }

        public static NetworkType getNetworkType(int i) {
            switch (i) {
                case StringUtils.INDEX_NOT_FOUND /*-1*/:
                    return NONE;
                case Task.NETWORK_STATE_CONNECTED /*0*/:
                    return WWAN;
                case Task.NETWORK_STATE_UNMETERED /*1*/:
                    return WIFI;
                case Task.NETWORK_STATE_ANY /*2*/:
                    return WWAN;
                case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                    return WWAN;
                case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                    return WWAN;
                case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                    return WWAN;
                case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                    return WIFI;
                case CommonStatusCodes.NETWORK_ERROR /*7*/:
                    return NONE;
                case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                    return NONE;
                case CommonStatusCodes.SERVICE_INVALID /*9*/:
                    return WIFI;
                default:
                    return NONE;
            }
        }
    }

    static {
        f1982d = null;
    }

    public static NetworkUtils m3107a() {
        if (f1982d != null) {
            return f1982d;
        }
        throw new IllegalStateException("NetworkUtils must be created via createInstance before getInstance can be called");
    }

    private NetworkUtils(Context context) {
        this.f1985c = -1;
        this.f1983a = null;
        this.f1984b = new an(this);
        this.f1983a = new DataStateReceiver(m3111b(context), 500, 0);
        this.f1983a.addListener(this.f1984b);
        context.registerReceiver(this.f1983a, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public static void m3109a(Context context) {
        if (f1982d == null) {
            f1982d = new NetworkUtils(context);
        }
    }

    public boolean m3114b() {
        return this.f1983a.isDataConnected();
    }

    private boolean m3111b(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && (activeNetworkInfo.isConnected() || activeNetworkInfo.isConnectedOrConnecting());
    }

    public NetworkType m3115c() {
        return NetworkType.getNetworkType(this.f1985c);
    }

    public void m3113a(DataStateReceiver dataStateReceiver) {
        this.f1983a.addListener(dataStateReceiver);
    }

    private int m3112d() {
        return this.f1983a.getActiveNetworkType();
    }

    private void m3108a(int i) {
        this.f1985c = i;
    }
}

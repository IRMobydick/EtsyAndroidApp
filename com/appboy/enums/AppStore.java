package com.appboy.enums;

import bo.app.aa;
import com.appboy.models.IPutIntoJson;
import com.google.android.gms.gcm.Task;
import java.util.Locale;

public enum AppStore implements IPutIntoJson<String> {
    GOOGLE_PLAY_STORE,
    KINDLE_STORE;

    public final String forJsonPut() {
        switch (aa.f10a[ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return "Google Play Store";
            case Task.NETWORK_STATE_ANY /*2*/:
                return "Kindle Store";
            default:
                return null;
        }
    }

    public static String serverStringToEnumString(String str) {
        return str.replace(" ", "_").toUpperCase(Locale.US);
    }
}

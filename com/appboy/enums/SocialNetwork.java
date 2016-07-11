package com.appboy.enums;

import bo.app.al;
import com.appboy.models.IPutIntoJson;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

public enum SocialNetwork implements IPutIntoJson<String> {
    FACEBOOK,
    TWITTER,
    GOOGLE;

    public final String forJsonPut() {
        switch (al.f75a[ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return "fb";
            case Task.NETWORK_STATE_ANY /*2*/:
                return "tw";
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                return "g";
            default:
                return null;
        }
    }
}

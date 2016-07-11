package com.appboy.enums;

import bo.app.af;
import com.appboy.models.IPutIntoJson;
import com.google.android.gms.gcm.Task;

public enum Gender implements IPutIntoJson<String> {
    MALE,
    FEMALE;

    public final String forJsonPut() {
        switch (af.f54a[ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return "m";
            case Task.NETWORK_STATE_ANY /*2*/:
                return "f";
            default:
                return null;
        }
    }
}

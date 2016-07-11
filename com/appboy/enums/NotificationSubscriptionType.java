package com.appboy.enums;

import bo.app.ai;
import com.appboy.models.IPutIntoJson;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

public enum NotificationSubscriptionType implements IPutIntoJson<String> {
    OPTED_IN,
    SUBSCRIBED,
    UNSUBSCRIBED;

    public final String forJsonPut() {
        switch (ai.f67a[ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return "unsubscribed";
            case Task.NETWORK_STATE_ANY /*2*/:
                return "subscribed";
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                return "opted_in";
            default:
                return null;
        }
    }
}

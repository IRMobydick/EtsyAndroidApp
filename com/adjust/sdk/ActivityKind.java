package com.adjust.sdk;

import android.support.v4.os.EnvironmentCompat;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

public enum ActivityKind {
    UNKNOWN,
    SESSION,
    EVENT,
    CLICK,
    ATTRIBUTION,
    REVENUE,
    REATTRIBUTION;

    /* renamed from: com.adjust.sdk.ActivityKind.1 */
    /* synthetic */ class C03631 {
        static final /* synthetic */ int[] $SwitchMap$com$adjust$sdk$ActivityKind;

        static {
            $SwitchMap$com$adjust$sdk$ActivityKind = new int[ActivityKind.values().length];
            try {
                $SwitchMap$com$adjust$sdk$ActivityKind[ActivityKind.SESSION.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$adjust$sdk$ActivityKind[ActivityKind.EVENT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$adjust$sdk$ActivityKind[ActivityKind.CLICK.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$adjust$sdk$ActivityKind[ActivityKind.ATTRIBUTION.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public static ActivityKind fromString(String str) {
        if ("session".equals(str)) {
            return SESSION;
        }
        if (NotificationCompatApi21.CATEGORY_EVENT.equals(str)) {
            return EVENT;
        }
        if ("click".equals(str)) {
            return CLICK;
        }
        if ("attribution".equals(str)) {
            return ATTRIBUTION;
        }
        return UNKNOWN;
    }

    public String toString() {
        switch (C03631.$SwitchMap$com$adjust$sdk$ActivityKind[ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return "session";
            case Task.NETWORK_STATE_ANY /*2*/:
                return NotificationCompatApi21.CATEGORY_EVENT;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                return "click";
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                return "attribution";
            default:
                return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }
}

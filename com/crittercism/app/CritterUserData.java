package com.crittercism.app;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import crittercism.android.dy;
import java.util.Map;

public class CritterUserData {
    private Map f1051a;
    private final boolean f1052b;

    CritterUserData(Map map, boolean z) {
        this.f1051a = map;
        this.f1052b = z;
    }

    public boolean shouldShowRateMyAppAlert() {
        if (this.f1051a.containsKey("shouldShowRateAppAlert")) {
            return ((Boolean) this.f1051a.get("shouldShowRateAppAlert")).booleanValue();
        }
        if (this.f1052b) {
            dy.c("User has opted out of Crittercism.  Returning false.");
        } else {
            dy.c("CritterUserData instance has no value for shouldShowMyRateAppAlert().  Defaulting to false.");
        }
        return false;
    }

    public String getRateMyAppTitle() {
        if (!this.f1051a.containsKey(FindsModule.FIELD_TITLE)) {
            if (this.f1052b) {
                dy.c("User has opted out of Crittercism.  Returning null.");
            } else {
                dy.c("CritterUserData instance has no value for getRateMyAppTitle().  Returning null.");
            }
        }
        return (String) this.f1051a.get(FindsModule.FIELD_TITLE);
    }

    public String getRateMyAppMessage() {
        if (!this.f1051a.containsKey(ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE)) {
            if (this.f1052b) {
                dy.c("User has opted out of Crittercism.  Returning null.");
            } else {
                dy.c("CritterUserData instance has no value for getRateMyAppMessage().  Returning null.");
            }
        }
        return (String) this.f1051a.get(ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE);
    }

    public boolean isOptedOut() {
        if (this.f1051a.containsKey("optOutStatus")) {
            return ((Boolean) this.f1051a.get("optOutStatus")).booleanValue();
        }
        return this.f1052b;
    }

    public boolean crashedOnLastLoad() {
        if (this.f1051a.containsKey("crashedOnLastLoad")) {
            return ((Boolean) this.f1051a.get("crashedOnLastLoad")).booleanValue();
        }
        if (this.f1052b) {
            dy.c("User has opted out of Crittercism.  Returning false.");
        } else {
            dy.c("CritterUserData instance has no value for crashedOnLastLoad().  Defaulting to false.");
        }
        return false;
    }

    public String getUserUUID() {
        if (!this.f1051a.containsKey("userUUID")) {
            if (this.f1052b) {
                dy.c("User has opted out of Crittercism.  Returning null.");
            } else {
                dy.c("CritterUserData instance has no value for getUserUUID().  Returning null.");
            }
        }
        return (String) this.f1051a.get("userUUID");
    }
}

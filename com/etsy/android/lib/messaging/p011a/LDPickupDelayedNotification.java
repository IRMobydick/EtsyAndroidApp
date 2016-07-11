package com.etsy.android.lib.messaging.p011a;

import android.content.Context;
import android.os.Bundle;
import com.etsy.android.lib.R;
import com.etsy.android.lib.messaging.EtsyEntity;
import com.etsy.android.lib.messaging.InboxStyleNotification;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.NotificationType;

/* renamed from: com.etsy.android.lib.messaging.a.l */
public class LDPickupDelayedNotification extends InboxStyleNotification {
    private static LDPickupDelayedNotification f1879f;

    static {
        f1879f = null;
    }

    private LDPickupDelayedNotification() {
        super(NotificationType.LD_PURCHASE_COMPLETE);
    }

    public static synchronized LDPickupDelayedNotification m2212n() {
        LDPickupDelayedNotification lDPickupDelayedNotification;
        synchronized (LDPickupDelayedNotification.class) {
            if (f1879f == null) {
                f1879f = new LDPickupDelayedNotification();
                f1879f.m2125g();
            }
            lDPickupDelayedNotification = f1879f;
        }
        return lDPickupDelayedNotification;
    }

    protected String m2215i() {
        return "username";
    }

    protected String m2216j() {
        return ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE;
    }

    protected String m2217k() {
        return "o";
    }

    protected CharSequence m2213b(Context context, Bundle bundle) {
        return context.getResources().getString(R.ld_pickup_delayed);
    }

    protected EtsyEntity m2214h() {
        return EtsyEntity.LISTING;
    }
}

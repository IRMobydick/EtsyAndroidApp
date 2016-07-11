package com.etsy.android.lib.messaging.p011a;

import android.content.Context;
import android.os.Bundle;
import com.etsy.android.lib.R;
import com.etsy.android.lib.messaging.EtsyEntity;
import com.etsy.android.lib.messaging.InboxStyleNotification;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.NotificationType;

/* renamed from: com.etsy.android.lib.messaging.a.n */
public class LDPickupStartedNotification extends InboxStyleNotification {
    private static LDPickupStartedNotification f1881f;

    static {
        f1881f = null;
    }

    private LDPickupStartedNotification() {
        super(NotificationType.LD_PURCHASE_COMPLETE);
    }

    public static synchronized LDPickupStartedNotification m2224n() {
        LDPickupStartedNotification lDPickupStartedNotification;
        synchronized (LDPickupStartedNotification.class) {
            if (f1881f == null) {
                f1881f = new LDPickupStartedNotification();
                f1881f.m2125g();
            }
            lDPickupStartedNotification = f1881f;
        }
        return lDPickupStartedNotification;
    }

    protected String m2227i() {
        return "username";
    }

    protected String m2228j() {
        return ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE;
    }

    protected String m2229k() {
        return "o";
    }

    protected CharSequence m2225b(Context context, Bundle bundle) {
        return context.getResources().getString(R.ld_pickup_started);
    }

    protected EtsyEntity m2226h() {
        return EtsyEntity.LISTING;
    }
}

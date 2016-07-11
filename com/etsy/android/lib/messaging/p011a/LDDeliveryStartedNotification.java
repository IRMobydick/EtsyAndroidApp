package com.etsy.android.lib.messaging.p011a;

import android.content.Context;
import android.os.Bundle;
import com.etsy.android.lib.R;
import com.etsy.android.lib.messaging.EtsyEntity;
import com.etsy.android.lib.messaging.InboxStyleNotification;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.NotificationType;

/* renamed from: com.etsy.android.lib.messaging.a.k */
public class LDDeliveryStartedNotification extends InboxStyleNotification {
    private static LDDeliveryStartedNotification f1878f;

    static {
        f1878f = null;
    }

    private LDDeliveryStartedNotification() {
        super(NotificationType.LD_PURCHASE_COMPLETE);
    }

    public static synchronized LDDeliveryStartedNotification m2206n() {
        LDDeliveryStartedNotification lDDeliveryStartedNotification;
        synchronized (LDDeliveryStartedNotification.class) {
            if (f1878f == null) {
                f1878f = new LDDeliveryStartedNotification();
                f1878f.m2125g();
            }
            lDDeliveryStartedNotification = f1878f;
        }
        return lDDeliveryStartedNotification;
    }

    protected String m2209i() {
        return "username";
    }

    protected String m2210j() {
        return ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE;
    }

    protected String m2211k() {
        return "o";
    }

    protected CharSequence m2207b(Context context, Bundle bundle) {
        return context.getResources().getString(R.ld_delivery_started);
    }

    protected EtsyEntity m2208h() {
        return EtsyEntity.LISTING;
    }
}

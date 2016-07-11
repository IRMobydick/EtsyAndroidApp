package com.etsy.android.lib.messaging.p011a;

import android.content.Context;
import android.os.Bundle;
import com.etsy.android.lib.R;
import com.etsy.android.lib.messaging.EtsyEntity;
import com.etsy.android.lib.messaging.InboxStyleNotification;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.NotificationType;

/* renamed from: com.etsy.android.lib.messaging.a.i */
public class LDDeliveryDelayedNotification extends InboxStyleNotification {
    private static LDDeliveryDelayedNotification f1876f;

    static {
        f1876f = null;
    }

    private LDDeliveryDelayedNotification() {
        super(NotificationType.LD_PURCHASE_COMPLETE);
    }

    public static synchronized LDDeliveryDelayedNotification m2194n() {
        LDDeliveryDelayedNotification lDDeliveryDelayedNotification;
        synchronized (LDDeliveryDelayedNotification.class) {
            if (f1876f == null) {
                f1876f = new LDDeliveryDelayedNotification();
                f1876f.m2125g();
            }
            lDDeliveryDelayedNotification = f1876f;
        }
        return lDDeliveryDelayedNotification;
    }

    protected String m2197i() {
        return "username";
    }

    protected String m2198j() {
        return ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE;
    }

    protected String m2199k() {
        return "o";
    }

    protected CharSequence m2195b(Context context, Bundle bundle) {
        return context.getResources().getString(R.ld_delivery_delayed);
    }

    protected EtsyEntity m2196h() {
        return EtsyEntity.LISTING;
    }
}

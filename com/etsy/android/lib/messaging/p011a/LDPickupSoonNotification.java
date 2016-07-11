package com.etsy.android.lib.messaging.p011a;

import android.content.Context;
import android.os.Bundle;
import com.etsy.android.lib.R;
import com.etsy.android.lib.messaging.EtsyEntity;
import com.etsy.android.lib.messaging.InboxStyleNotification;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.NotificationType;

/* renamed from: com.etsy.android.lib.messaging.a.m */
public class LDPickupSoonNotification extends InboxStyleNotification {
    private static LDPickupSoonNotification f1880f;

    static {
        f1880f = null;
    }

    private LDPickupSoonNotification() {
        super(NotificationType.LD_PURCHASE_COMPLETE);
    }

    public static synchronized LDPickupSoonNotification m2218n() {
        LDPickupSoonNotification lDPickupSoonNotification;
        synchronized (LDPickupSoonNotification.class) {
            if (f1880f == null) {
                f1880f = new LDPickupSoonNotification();
                f1880f.m2125g();
            }
            lDPickupSoonNotification = f1880f;
        }
        return lDPickupSoonNotification;
    }

    protected String m2221i() {
        return "username";
    }

    protected String m2222j() {
        return ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE;
    }

    protected String m2223k() {
        return "o";
    }

    protected CharSequence m2219b(Context context, Bundle bundle) {
        return context.getResources().getString(R.ld_pickup_soon);
    }

    protected EtsyEntity m2220h() {
        return EtsyEntity.LISTING;
    }
}

package com.etsy.android.lib.messaging.p011a;

import android.content.Context;
import android.os.Bundle;
import com.etsy.android.lib.R;
import com.etsy.android.lib.messaging.EtsyEntity;
import com.etsy.android.lib.messaging.InboxStyleNotification;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.NotificationType;

/* renamed from: com.etsy.android.lib.messaging.a.j */
public class LDDeliverySoonNotification extends InboxStyleNotification {
    private static LDDeliverySoonNotification f1877f;

    static {
        f1877f = null;
    }

    private LDDeliverySoonNotification() {
        super(NotificationType.LD_PURCHASE_COMPLETE);
    }

    public static synchronized LDDeliverySoonNotification m2200n() {
        LDDeliverySoonNotification lDDeliverySoonNotification;
        synchronized (LDDeliverySoonNotification.class) {
            if (f1877f == null) {
                f1877f = new LDDeliverySoonNotification();
                f1877f.m2125g();
            }
            lDDeliverySoonNotification = f1877f;
        }
        return lDDeliverySoonNotification;
    }

    protected String m2203i() {
        return "username";
    }

    protected String m2204j() {
        return ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE;
    }

    protected String m2205k() {
        return "o";
    }

    protected CharSequence m2201b(Context context, Bundle bundle) {
        return context.getResources().getString(R.ld_delivery_soon);
    }

    protected EtsyEntity m2202h() {
        return EtsyEntity.LISTING;
    }
}

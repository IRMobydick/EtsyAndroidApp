package com.etsy.android.lib.messaging.p011a;

import android.content.Context;
import android.os.Bundle;
import com.etsy.android.lib.R;
import com.etsy.android.lib.messaging.EtsyEntity;
import com.etsy.android.lib.messaging.InboxStyleNotification;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.NotificationType;

/* renamed from: com.etsy.android.lib.messaging.a.g */
public class LDDeliveryCompleteBuyerNotification extends InboxStyleNotification {
    private static LDDeliveryCompleteBuyerNotification f1874f;

    static {
        f1874f = null;
    }

    private LDDeliveryCompleteBuyerNotification() {
        super(NotificationType.LD_PURCHASE_COMPLETE);
    }

    public static synchronized LDDeliveryCompleteBuyerNotification m2182n() {
        LDDeliveryCompleteBuyerNotification lDDeliveryCompleteBuyerNotification;
        synchronized (LDDeliveryCompleteBuyerNotification.class) {
            if (f1874f == null) {
                f1874f = new LDDeliveryCompleteBuyerNotification();
                f1874f.m2125g();
            }
            lDDeliveryCompleteBuyerNotification = f1874f;
        }
        return lDDeliveryCompleteBuyerNotification;
    }

    protected String m2185i() {
        return "username";
    }

    protected String m2186j() {
        return ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE;
    }

    protected String m2187k() {
        return "o";
    }

    protected CharSequence m2183b(Context context, Bundle bundle) {
        return context.getResources().getString(R.ld_delivery_complete_buyer);
    }

    protected EtsyEntity m2184h() {
        return EtsyEntity.LISTING;
    }
}

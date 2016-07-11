package com.etsy.android.lib.messaging.p011a;

import android.content.Context;
import android.os.Bundle;
import com.etsy.android.lib.R;
import com.etsy.android.lib.messaging.EtsyEntity;
import com.etsy.android.lib.messaging.InboxStyleNotification;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.NotificationType;

/* renamed from: com.etsy.android.lib.messaging.a.o */
public class LDPurchaseCompleteNotification extends InboxStyleNotification {
    private static LDPurchaseCompleteNotification f1882f;

    static {
        f1882f = null;
    }

    private LDPurchaseCompleteNotification() {
        super(NotificationType.LD_PURCHASE_COMPLETE);
    }

    public static synchronized LDPurchaseCompleteNotification m2230n() {
        LDPurchaseCompleteNotification lDPurchaseCompleteNotification;
        synchronized (LDPurchaseCompleteNotification.class) {
            if (f1882f == null) {
                f1882f = new LDPurchaseCompleteNotification();
                f1882f.m2125g();
            }
            lDPurchaseCompleteNotification = f1882f;
        }
        return lDPurchaseCompleteNotification;
    }

    protected String m2233i() {
        return "username";
    }

    protected String m2234j() {
        return ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE;
    }

    protected String m2235k() {
        return "o";
    }

    protected CharSequence m2231b(Context context, Bundle bundle) {
        return context.getResources().getString(R.ld_purchase_complete);
    }

    protected EtsyEntity m2232h() {
        return EtsyEntity.LISTING;
    }
}

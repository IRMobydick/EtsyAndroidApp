package com.etsy.android.lib.messaging.p011a;

import android.content.Context;
import android.os.Bundle;
import com.etsy.android.lib.R;
import com.etsy.android.lib.messaging.EtsyEntity;
import com.etsy.android.lib.messaging.InboxStyleNotification;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.NotificationType;

/* renamed from: com.etsy.android.lib.messaging.a.h */
public class LDDeliveryCompleteSellerNotification extends InboxStyleNotification {
    private static LDDeliveryCompleteSellerNotification f1875f;

    static {
        f1875f = null;
    }

    private LDDeliveryCompleteSellerNotification() {
        super(NotificationType.LD_PURCHASE_COMPLETE);
    }

    public static synchronized LDDeliveryCompleteSellerNotification m2188n() {
        LDDeliveryCompleteSellerNotification lDDeliveryCompleteSellerNotification;
        synchronized (LDDeliveryCompleteSellerNotification.class) {
            if (f1875f == null) {
                f1875f = new LDDeliveryCompleteSellerNotification();
                f1875f.m2125g();
            }
            lDDeliveryCompleteSellerNotification = f1875f;
        }
        return lDDeliveryCompleteSellerNotification;
    }

    protected String m2191i() {
        return "username";
    }

    protected String m2192j() {
        return ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE;
    }

    protected String m2193k() {
        return "o";
    }

    protected CharSequence m2189b(Context context, Bundle bundle) {
        return context.getResources().getString(R.ld_delivery_complete_seller);
    }

    protected EtsyEntity m2190h() {
        return EtsyEntity.LISTING;
    }
}

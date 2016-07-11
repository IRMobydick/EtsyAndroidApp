package com.etsy.android.lib.messaging.p011a;

import android.content.Context;
import android.os.Bundle;
import com.etsy.android.lib.R;
import com.etsy.android.lib.messaging.EtsyEntity;
import com.etsy.android.lib.messaging.InboxStyleNotification;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.NotificationType;

/* renamed from: com.etsy.android.lib.messaging.a.q */
public class SellerPostUpdateNotification extends InboxStyleNotification {
    private static SellerPostUpdateNotification f1884f;

    static {
        f1884f = null;
    }

    private SellerPostUpdateNotification() {
        super(NotificationType.SELLER_POSTS_UPDATE);
    }

    public static synchronized SellerPostUpdateNotification m2243n() {
        SellerPostUpdateNotification sellerPostUpdateNotification;
        synchronized (SellerPostUpdateNotification.class) {
            if (f1884f == null) {
                f1884f = new SellerPostUpdateNotification();
                f1884f.m2125g();
            }
            sellerPostUpdateNotification = f1884f;
        }
        return sellerPostUpdateNotification;
    }

    protected String m2246i() {
        return "username";
    }

    protected String m2247j() {
        return ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE;
    }

    protected String m2248k() {
        return "o";
    }

    protected CharSequence m2244b(Context context, Bundle bundle) {
        return context.getResources().getString(R.seller_posted_update);
    }

    protected EtsyEntity m2245h() {
        return EtsyEntity.LISTING_UPDATE;
    }
}

package com.etsy.android.lib.messaging.p011a;

import android.content.Context;
import android.os.Bundle;
import com.etsy.android.lib.R;
import com.etsy.android.lib.messaging.EtsyEntity;
import com.etsy.android.lib.messaging.InboxStyleNotification;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.NotificationType;

/* renamed from: com.etsy.android.lib.messaging.a.d */
public class BuyerReviewsAvailableNotification extends InboxStyleNotification {
    private static BuyerReviewsAvailableNotification f1871f;

    static {
        f1871f = null;
    }

    private BuyerReviewsAvailableNotification() {
        super(NotificationType.BUYER_REVIEW_AVAILABLE);
    }

    public static synchronized BuyerReviewsAvailableNotification m2164n() {
        BuyerReviewsAvailableNotification buyerReviewsAvailableNotification;
        synchronized (BuyerReviewsAvailableNotification.class) {
            if (f1871f == null) {
                f1871f = new BuyerReviewsAvailableNotification();
                f1871f.m2125g();
            }
            buyerReviewsAvailableNotification = f1871f;
        }
        return buyerReviewsAvailableNotification;
    }

    protected String m2167i() {
        return ResponseConstants.SHOP_NAME;
    }

    protected String m2168j() {
        return "item_name";
    }

    protected String m2169k() {
        return "o";
    }

    protected CharSequence m2165b(Context context, Bundle bundle) {
        return context.getResources().getQuantityString(R.buyer_reviews_available_big_title, m2123c(), new Object[]{Integer.valueOf(m2123c())});
    }

    protected EtsyEntity m2166h() {
        return EtsyEntity.PURCHASES;
    }
}

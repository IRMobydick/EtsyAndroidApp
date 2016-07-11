package com.etsy.android.lib.messaging.p011a;

import android.content.Context;
import android.os.Bundle;
import com.etsy.android.lib.R;
import com.etsy.android.lib.messaging.EtsyEntity;
import com.etsy.android.lib.messaging.InboxStyleNotification;
import com.etsy.android.lib.util.NotificationType;

/* renamed from: com.etsy.android.lib.messaging.a.a */
public class BuyerFavoriteNotification extends InboxStyleNotification {
    private static BuyerFavoriteNotification f1867f;

    static {
        f1867f = null;
    }

    private BuyerFavoriteNotification() {
        super(NotificationType.BUYER_FAVORITE);
    }

    public static synchronized BuyerFavoriteNotification m2132n() {
        BuyerFavoriteNotification buyerFavoriteNotification;
        synchronized (BuyerFavoriteNotification.class) {
            if (f1867f == null) {
                f1867f = new BuyerFavoriteNotification();
                f1867f.m2125g();
            }
            buyerFavoriteNotification = f1867f;
        }
        return buyerFavoriteNotification;
    }

    protected String m2136i() {
        return "username";
    }

    protected String m2137j() {
        return "item_name";
    }

    protected CharSequence m2134c(Context context, Bundle bundle) {
        StringBuilder stringBuilder = new StringBuilder();
        if (bundle.containsKey(m2137j())) {
            stringBuilder.append(context.getResources().getString(R.notification_action_favorited));
            stringBuilder.append(" ");
            stringBuilder.append(bundle.getString(m2137j()));
        } else {
            stringBuilder.append(context.getResources().getString(R.notification_action_favorited_shop));
        }
        return stringBuilder.toString();
    }

    protected String m2138k() {
        return "o";
    }

    protected CharSequence m2133b(Context context, Bundle bundle) {
        return context.getResources().getQuantityString(R.buyer_favorite_big_title, m2123c(), new Object[]{Integer.valueOf(m2123c())});
    }

    protected EtsyEntity m2135h() {
        return EtsyEntity.SHOP_FAVORITES;
    }
}

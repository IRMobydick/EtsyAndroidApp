package com.etsy.android.lib.messaging.p011a;

import android.content.Context;
import android.os.Bundle;
import com.etsy.android.lib.R;
import com.etsy.android.lib.messaging.EtsyEntity;
import com.etsy.android.lib.messaging.InboxStyleNotification;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.NotificationType;
import com.etsy.android.lib.util.bh;

/* renamed from: com.etsy.android.lib.messaging.a.p */
public class OrderNotification extends InboxStyleNotification {
    private static OrderNotification f1883f;

    static {
        f1883f = null;
    }

    private OrderNotification() {
        super(NotificationType.SOLD_ORDER);
    }

    public static synchronized OrderNotification m2236n() {
        OrderNotification orderNotification;
        synchronized (OrderNotification.class) {
            if (f1883f == null) {
                f1883f = new OrderNotification();
                f1883f.m2125g();
            }
            orderNotification = f1883f;
        }
        return orderNotification;
    }

    protected String m2240i() {
        return "username";
    }

    protected String m2241j() {
        return "item_name";
    }

    protected CharSequence m2238c(Context context, Bundle bundle) {
        int parseInt;
        int parseInt2;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            parseInt = Integer.parseInt(bundle.getString(ResponseConstants.QUANTITY));
        } catch (NumberFormatException e) {
            parseInt = 0;
        }
        try {
            parseInt2 = Integer.parseInt(bundle.getString("num_items"));
        } catch (NumberFormatException e2) {
            parseInt2 = 0;
        }
        String string = bundle.getString(m2241j());
        if ((parseInt2 > 1 && parseInt > 0) || !bh.m3340a(string)) {
            stringBuilder.append(context.getResources().getQuantityString(R.sold_order_subject_item_different_plural, parseInt, new Object[]{Integer.valueOf(parseInt)}));
        } else if (parseInt > 1) {
            stringBuilder.append(context.getResources().getString(R.sold_order_subject_item_multiple, new Object[]{Integer.valueOf(parseInt), string}));
        } else {
            stringBuilder.append(context.getResources().getString(R.sold_order_subject_item_one, new Object[]{string}));
        }
        return stringBuilder.toString();
    }

    protected String m2242k() {
        return "o";
    }

    protected CharSequence m2237b(Context context, Bundle bundle) {
        return context.getResources().getQuantityString(R.sold_order_big_title, m2123c(), new Object[]{Integer.valueOf(m2123c())});
    }

    protected EtsyEntity m2239h() {
        return EtsyEntity.SALES;
    }
}

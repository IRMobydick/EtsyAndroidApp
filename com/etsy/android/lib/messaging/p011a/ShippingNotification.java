package com.etsy.android.lib.messaging.p011a;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Builder;
import com.appboy.Constants;
import com.etsy.android.lib.R;
import com.etsy.android.lib.messaging.EtsyEntity;
import com.etsy.android.lib.messaging.EtsyRouteHelper;
import com.etsy.android.lib.messaging.InboxStyleNotification;
import com.etsy.android.lib.messaging.InboxStyleNotification.InboxItemMap;
import com.etsy.android.lib.messaging.NotificationIntentDelegate;
import com.etsy.android.lib.messaging.NotificationsUtil;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.NotificationType;
import com.etsy.android.lib.util.bh;
import com.foresee.mobileReplay.perfLog.PerfDbContentProvider;

/* renamed from: com.etsy.android.lib.messaging.a.r */
public class ShippingNotification extends InboxStyleNotification {
    private static ShippingNotification f1885f;

    static {
        f1885f = null;
    }

    private ShippingNotification() {
        super(NotificationType.SHIPPING);
        this.e = new InboxItemMap();
    }

    public static synchronized ShippingNotification m2249n() {
        ShippingNotification shippingNotification;
        synchronized (ShippingNotification.class) {
            if (f1885f == null) {
                f1885f = new ShippingNotification();
                f1885f.m2125g();
            }
            shippingNotification = f1885f;
        }
        return shippingNotification;
    }

    protected String m2253i() {
        return ResponseConstants.SHOP_NAME;
    }

    protected String m2254j() {
        return "item_name";
    }

    protected String m2255k() {
        return "o";
    }

    protected CharSequence m2251b(Context context, Bundle bundle) {
        if (bundle == null || bundle.get("is_delivered") == null) {
            return context.getResources().getQuantityString(R.shipping_notification_big_title, m2123c(), new Object[]{Integer.valueOf(m2123c())});
        }
        return context.getResources().getQuantityString(R.shipping_delivery_notification_big_title, m2123c(), new Object[]{Integer.valueOf(m2123c())});
    }

    protected EtsyEntity m2252h() {
        return EtsyEntity.PURCHASES;
    }

    protected void m2250a(Builder builder, Context context, NotificationIntentDelegate notificationIntentDelegate, Bundle bundle, EtsyEntity etsyEntity, String str) {
        super.m2106a(builder, context, notificationIntentDelegate, bundle, etsyEntity, str);
        if (m2123c() == 1) {
            String string = bundle.getString(ResponseConstants.RECEIPT_SHIPPING_ID);
            if (bh.m3340a(string)) {
                Intent intent = new Intent(context, notificationIntentDelegate.m2292a());
                intent.setAction("com.etsy.android.action.NOTIFICATION");
                intent.setData(EtsyRouteHelper.m2282a(str, string));
                intent.putExtra(Constants.APPBOY_PUSH_TITLE_KEY, this.b.getName());
                intent.putExtra(PerfDbContentProvider.COL_N, m2112f());
                builder.addAction(R.ic_action_place, context.getString(R.track_package), PendingIntent.getActivity(context, m2111e(), intent, 1073741824));
                return;
            }
            return;
        }
        NotificationsUtil.m2302a(context, m2110d());
    }
}

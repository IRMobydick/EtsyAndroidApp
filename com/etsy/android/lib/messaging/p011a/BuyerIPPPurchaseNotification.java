package com.etsy.android.lib.messaging.p011a;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.Style;
import com.appboy.Constants;
import com.etsy.android.lib.R;
import com.etsy.android.lib.messaging.EtsyEntity;
import com.etsy.android.lib.messaging.EtsyRouteHelper;
import com.etsy.android.lib.messaging.InboxStyleNotification;
import com.etsy.android.lib.messaging.NotificationIntentDelegate;
import com.etsy.android.lib.messaging.NotificationsUtil;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.NotificationType;
import com.etsy.android.lib.util.bh;
import com.foresee.mobileReplay.perfLog.PerfDbContentProvider;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.lib.messaging.a.b */
public class BuyerIPPPurchaseNotification extends InboxStyleNotification {
    private static BuyerIPPPurchaseNotification f1868f;

    static {
        f1868f = null;
    }

    private BuyerIPPPurchaseNotification() {
        super(NotificationType.BUYER_IPP_PURCHASE);
    }

    public static synchronized BuyerIPPPurchaseNotification m2140n() {
        BuyerIPPPurchaseNotification buyerIPPPurchaseNotification;
        synchronized (BuyerIPPPurchaseNotification.class) {
            if (f1868f == null) {
                f1868f = new BuyerIPPPurchaseNotification();
                f1868f.m2125g();
            }
            buyerIPPPurchaseNotification = f1868f;
        }
        return buyerIPPPurchaseNotification;
    }

    protected Style m2141a(Context context, Bundle bundle) {
        Style bigTextStyle;
        String unescapeHtml4 = StringEscapeUtils.unescapeHtml4(bundle.getString(m2149i()));
        String a = m2143a(bundle);
        int b = m2139b(bundle);
        CharSequence charSequence = StringUtils.EMPTY;
        synchronized (this.d) {
            InboxStyleNotification inboxStyleNotification = (InboxStyleNotification) this.e.get(a);
            if (inboxStyleNotification != null) {
                inboxStyleNotification.m2287a(unescapeHtml4);
                inboxStyleNotification.m2289b(m2112f());
                inboxStyleNotification.m2285a(inboxStyleNotification.m2291d() + b);
            } else {
                inboxStyleNotification = new InboxStyleNotification(a, unescapeHtml4, charSequence, m2112f());
                inboxStyleNotification.m2285a(b);
                this.e.put(a, inboxStyleNotification);
            }
            bigTextStyle = new BigTextStyle();
            bigTextStyle.bigText(m2142a(context, StringUtils.EMPTY));
        }
        return bigTextStyle;
    }

    protected CharSequence m2142a(Context context, String str) {
        if (m2153o() != 1) {
            return context.getString(R.ipp_buyer_purchase_subject_multi_shops);
        }
        if (((InboxStyleNotification) this.e.values().toArray()[0]).m2291d() <= 1) {
            return context.getString(R.ipp_buyer_purchase_subject_single, new Object[]{r0.m2284a()});
        }
        return context.getString(R.ipp_buyer_purchase_subject_many_items, new Object[]{r0.m2284a()});
    }

    protected String m2149i() {
        return ResponseConstants.SHOP_NAME;
    }

    protected String m2150j() {
        return "item_name";
    }

    protected String m2143a(Bundle bundle) {
        return bundle.getString(m2151k());
    }

    protected String m2151k() {
        return ResponseConstants.SHOP_NAME;
    }

    protected String m2144a(String str, Bundle bundle) {
        return bundle.getString(ResponseConstants.RECEIPT_ID);
    }

    protected CharSequence m2146b(Context context, Bundle bundle) {
        return StringUtils.EMPTY;
    }

    private int m2139b(Bundle bundle) {
        try {
            return Integer.parseInt(bundle.getString("num_items"));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    protected EtsyEntity m2148h() {
        return EtsyEntity.PURCHASES;
    }

    public int m2153o() {
        return m2123c();
    }

    public int m2154p() {
        if (this.e.size() > 0) {
            return ((InboxStyleNotification) this.e.values().toArray()[0]).m2291d();
        }
        return 0;
    }

    protected boolean m2147b() {
        return false;
    }

    protected boolean m2152l() {
        if (m2153o() <= 1) {
            return m2153o() == 1 && m2154p() > 1;
        } else {
            return true;
        }
    }

    protected void m2145a(Builder builder, Context context, NotificationIntentDelegate notificationIntentDelegate, Bundle bundle, EtsyEntity etsyEntity, String str) {
        super.m2106a(builder, context, notificationIntentDelegate, bundle, etsyEntity, str);
        if (m2153o() == 1) {
            String string = bundle.getString(ResponseConstants.SHOP_NAME);
            if (bh.m3340a(string)) {
                Intent intent = new Intent(context, notificationIntentDelegate.m2292a());
                intent.setAction("com.etsy.android.action.NOTIFICATION");
                intent.setData(EtsyRouteHelper.m2281a(EtsyEntity.SHOP, string));
                intent.putExtra(Constants.APPBOY_PUSH_TITLE_KEY, this.b.getName());
                intent.putExtra(PerfDbContentProvider.COL_N, m2112f());
                builder.addAction(R.ic_action_shop, context.getString(R.view_the_shop), PendingIntent.getActivity(context, m2111e(), intent, 1073741824));
                return;
            }
            return;
        }
        NotificationsUtil.m2302a(context, m2110d());
    }
}

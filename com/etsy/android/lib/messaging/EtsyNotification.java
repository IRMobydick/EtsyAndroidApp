package com.etsy.android.lib.messaging;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.Style;
import android.text.TextUtils;
import com.appboy.Constants;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.NotificationType;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.aa;
import com.etsy.android.lib.util.af;
import com.foresee.mobileReplay.perfLog.PerfDbContentProvider;
import com.google.android.gms.common.ConnectionResult;

/* renamed from: com.etsy.android.lib.messaging.b */
public class EtsyNotification {
    public static final long[] f1859a;
    private static EtsyNotification f1860d;
    protected NotificationType f1861b;
    protected String f1862c;

    static {
        f1859a = new long[]{500, 500, 500};
        f1860d = null;
    }

    protected EtsyNotification() {
        this.f1861b = NotificationType.UNKNOWN;
    }

    protected EtsyNotification(NotificationType notificationType) {
        this.f1861b = NotificationType.UNKNOWN;
        this.f1861b = notificationType;
    }

    public static synchronized EtsyNotification m2097a() {
        EtsyNotification etsyNotification;
        synchronized (EtsyNotification.class) {
            if (f1860d == null) {
                f1860d = new EtsyNotification();
            }
            etsyNotification = f1860d;
        }
        return etsyNotification;
    }

    protected CharSequence m2103a(Context context, NotificationIntentDelegate notificationIntentDelegate, Bundle bundle) {
        return notificationIntentDelegate.m2294c();
    }

    protected CharSequence m2104a(Context context, String str) {
        return str;
    }

    protected CharSequence m2107b(Context context, String str) {
        return str;
    }

    public Builder m2100a(Context context, NotificationIntentDelegate notificationIntentDelegate, EtsyEntity etsyEntity, String str, String str2, Bundle bundle) {
        String string = bundle.getString("large_icon");
        this.f1861b = NotificationType.fromString(bundle.getString(Constants.APPBOY_PUSH_TITLE_KEY));
        this.f1862c = bundle.getString(PerfDbContentProvider.COL_N);
        if (this.f1861b == NotificationType.UNKNOWN) {
            return null;
        }
        Builder builder = new Builder(context);
        Style a = m2101a(context, bundle);
        if (a != null) {
            builder.setStyle(a);
        }
        if (m2108b() && m2109c() > 0) {
            builder.setNumber(m2109c());
        }
        builder.setSmallIcon(notificationIntentDelegate.m2295d()).setTicker(m2107b(context, str2)).setWhen(System.currentTimeMillis()).setContentText(m2104a(context, str2)).setContentTitle(m2103a(context, notificationIntentDelegate, bundle)).setSubText(m2102a(context));
        String string2 = bundle.getString("sound");
        if (SharedPreferencesUtility.m3133b(context, "notification_sound", true)) {
            String a2 = SharedPreferencesUtility.m3117a(context, "notification_ringtone", null);
            boolean b = SharedPreferencesUtility.m3133b(context, "notification_cha_ching", true);
            if (string2.equals("chaching.m4a") && b) {
                builder.setSound(af.m3196a(context, R.chaching));
            } else if (a2 == null || a2.startsWith("android.resource://com.etsy.android")) {
                builder.setSound(af.m3196a(context, R.notification));
            } else {
                builder.setSound(Uri.parse(a2));
            }
        }
        if (SharedPreferencesUtility.m3133b(context, "notification_vibrate", true)) {
            builder.setVibrate(f1859a);
        }
        if (SharedPreferencesUtility.m3133b(context, "notification_led", true)) {
            builder.setLights(context.getResources().getColor(R.darker_orange), ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED, Constants.APPBOY_MINIMUM_NOTIFICATION_DURATION_MILLIS);
        }
        if (!TextUtils.isEmpty(string)) {
            Bitmap a3 = NotificationsUtil.m2297a(context, string);
            if (a3 != null) {
                builder.setLargeIcon(a3);
            }
        }
        if (aa.m3170d()) {
            builder.setColor(context.getResources().getColor(notificationIntentDelegate.m2296e()));
        }
        PendingIntent a4 = m2099a(context, notificationIntentDelegate, etsyEntity, str, bundle);
        PendingIntent a5 = m2098a(context, notificationIntentDelegate);
        m2106a(builder, context, notificationIntentDelegate, bundle, etsyEntity, str);
        builder.setDeleteIntent(a5);
        builder.setContentIntent(a4);
        builder.setAutoCancel(true);
        return builder;
    }

    protected boolean m2108b() {
        return true;
    }

    protected String m2105a(String str, Bundle bundle) {
        return str;
    }

    protected PendingIntent m2099a(Context context, NotificationIntentDelegate notificationIntentDelegate, EtsyEntity etsyEntity, String str, Bundle bundle) {
        Intent intent = new Intent(context, notificationIntentDelegate.m2292a());
        intent.setAction("com.etsy.android.action.NOTIFICATION");
        intent.setData(EtsyRouteHelper.m2281a(etsyEntity, m2105a(str, bundle)));
        intent.putExtra(Constants.APPBOY_PUSH_TITLE_KEY, this.f1861b.getName());
        intent.putExtra(PerfDbContentProvider.COL_N, m2112f());
        return PendingIntent.getActivity(context, m2111e(), intent, 134217728);
    }

    protected PendingIntent m2098a(Context context, NotificationIntentDelegate notificationIntentDelegate) {
        Intent intent = new Intent(context, notificationIntentDelegate.m2293b());
        intent.setAction("com.google.android.gms.gcm");
        intent.putExtra(ResponseConstants.FROM, "etsynotify/delete");
        intent.putExtra("message_type", "gcm");
        intent.putExtra(Constants.APPBOY_PUSH_TITLE_KEY, this.f1861b.getName());
        return PendingIntent.getBroadcast(context, m2111e(), intent, 268435456);
    }

    public int m2109c() {
        return 0;
    }

    protected Style m2101a(Context context, Bundle bundle) {
        return null;
    }

    protected CharSequence m2102a(Context context) {
        return null;
    }

    public NotificationType m2110d() {
        return this.f1861b;
    }

    public int m2111e() {
        return this.f1861b.getId();
    }

    public String m2112f() {
        return this.f1862c;
    }

    protected void m2106a(Builder builder, Context context, NotificationIntentDelegate notificationIntentDelegate, Bundle bundle, EtsyEntity etsyEntity, String str) {
    }

    public void m2113g() {
    }
}

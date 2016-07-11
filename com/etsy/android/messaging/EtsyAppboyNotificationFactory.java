package com.etsy.android.messaging;

import android.annotation.TargetApi;
import android.app.Notification;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.appboy.Constants;
import com.appboy.IAppboyNotificationFactory;
import com.appboy.configuration.XmlAppConfigurationProvider;
import com.appboy.push.AppboyNotificationFactory;
import com.etsy.android.lib.R;
import com.etsy.android.lib.messaging.EtsyNotification;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.aa;
import com.etsy.android.lib.util.af;
import com.google.android.gms.common.ConnectionResult;

/* renamed from: com.etsy.android.messaging.c */
public class EtsyAppboyNotificationFactory implements IAppboyNotificationFactory {
    @TargetApi(21)
    public Notification createNotification(XmlAppConfigurationProvider xmlAppConfigurationProvider, Context context, Bundle bundle, Bundle bundle2) {
        Notification createNotification = AppboyNotificationFactory.getInstance().createNotification(xmlAppConfigurationProvider, context, bundle, bundle2);
        if (SharedPreferencesUtility.m3133b(context, "notification_sound", true)) {
            String a = SharedPreferencesUtility.m3117a(context, "notification_ringtone", null);
            if (a == null || a.startsWith("android.resource://com.etsy.android")) {
                a = af.m3196a(context, (int) R.notification).toString();
            }
            createNotification.sound = Uri.parse(a);
        } else {
            createNotification.sound = null;
        }
        if (SharedPreferencesUtility.m3133b(context, "notification_vibrate", true)) {
            createNotification.vibrate = EtsyNotification.f1859a;
        }
        if (SharedPreferencesUtility.m3133b(context, "notification_led", true)) {
            createNotification.flags |= 1;
            createNotification.ledARGB = context.getResources().getColor(R.darker_orange);
            createNotification.ledOnMS = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
            createNotification.ledOffMS = Constants.APPBOY_MINIMUM_NOTIFICATION_DURATION_MILLIS;
        }
        if (aa.m3170d()) {
            createNotification.color = context.getResources().getColor(R.brand_orange);
        }
        return createNotification;
    }
}

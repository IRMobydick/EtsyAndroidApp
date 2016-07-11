package com.etsy.android.lib.convos;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat.Builder;
import com.etsy.android.lib.config.InstallInfo;
import com.foresee.sdk.configuration.Configuration;

/* renamed from: com.etsy.android.lib.convos.i */
public class ConvoNotificationsUtil {
    public static void m1001a(Context context, String str, String str2, String str3, boolean z) {
        Builder builder = new Builder(context);
        builder.setSmallIcon(InstallInfo.m919a().m934k()).setTicker(str).setWhen(System.currentTimeMillis()).setContentText(str3).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(context.getApplicationContext(), 0, new Intent(), 0)).setContentTitle(str2);
        if (z) {
            builder.setProgress(100, 0, true);
        }
        ((NotificationManager) context.getSystemService(Configuration.NOTIFICATION_LAYOUT_NAME)).notify(-22, builder.build());
    }

    public static void m1000a(Context context) {
        ((NotificationManager) context.getSystemService(Configuration.NOTIFICATION_LAYOUT_NAME)).cancel(-22);
    }
}

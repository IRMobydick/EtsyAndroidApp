package com.appboy.push;

import android.app.Notification;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Builder;
import android.widget.RemoteViews;
import com.appboy.IAppboyNotificationFactory;
import com.appboy.configuration.XmlAppConfigurationProvider;

public class AppboyNotificationFactory implements IAppboyNotificationFactory {
    private static volatile AppboyNotificationFactory sInstance;

    static {
        sInstance = null;
    }

    public static AppboyNotificationFactory getInstance() {
        if (sInstance == null) {
            synchronized (AppboyNotificationFactory.class) {
                if (sInstance == null) {
                    sInstance = new AppboyNotificationFactory();
                }
            }
        }
        return sInstance;
    }

    public Notification createNotification(XmlAppConfigurationProvider xmlAppConfigurationProvider, Context context, Bundle bundle, Bundle bundle2) {
        boolean z = true;
        Builder autoCancel = new Builder(context).setAutoCancel(true);
        AppboyNotificationUtils.setTitleIfPresent(autoCancel, bundle);
        AppboyNotificationUtils.setContentIfPresent(autoCancel, bundle);
        AppboyNotificationUtils.setTickerIfPresent(autoCancel, bundle);
        AppboyNotificationUtils.setContentIntentIfPresent(context, autoCancel, bundle);
        int smallIcon = AppboyNotificationUtils.setSmallIcon(xmlAppConfigurationProvider, autoCancel);
        boolean largeIconIfPresentAndSupported = AppboyNotificationUtils.setLargeIconIfPresentAndSupported(context, xmlAppConfigurationProvider, autoCancel);
        AppboyNotificationUtils.setSoundIfPresentAndSupported(autoCancel, bundle);
        if (VERSION.SDK_INT >= 11 && VERSION.SDK_INT < 16) {
            if (largeIconIfPresentAndSupported) {
                z = false;
            }
            RemoteViews createMultiLineContentNotificationView = AppboyNotificationRemoteViewsUtils.createMultiLineContentNotificationView(context, bundle, smallIcon, z);
            if (createMultiLineContentNotificationView != null) {
                autoCancel.setContent(createMultiLineContentNotificationView);
                return autoCancel.build();
            }
        }
        AppboyNotificationUtils.setSummaryTextIfPresentAndSupported(autoCancel, bundle);
        AppboyNotificationUtils.setPriorityIfPresentAndSupported(autoCancel, bundle);
        AppboyNotificationUtils.setStyleIfSupported(context, autoCancel, bundle, bundle2);
        AppboyNotificationActionUtils.addNotificationActions(context, autoCancel, bundle);
        AppboyNotificationUtils.setAccentColorIfPresentAndSupported(xmlAppConfigurationProvider, autoCancel, bundle);
        AppboyNotificationUtils.setCategoryIfPresentAndSupported(autoCancel, bundle);
        AppboyNotificationUtils.setVisibilityIfPresentAndSupported(autoCancel, bundle);
        AppboyNotificationUtils.setPublicVersionIfPresentAndSupported(context, xmlAppConfigurationProvider, autoCancel, bundle);
        AppboyWearableNotificationUtils.setWearableNotificationFeaturesIfPresentAndSupported(context, autoCancel, bundle);
        return autoCancel.build();
    }
}

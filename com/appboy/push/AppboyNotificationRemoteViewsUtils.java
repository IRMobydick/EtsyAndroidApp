package com.appboy.push;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.RemoteViews;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import com.appboy.support.PackageUtils;
import com.etsy.android.lib.models.ResponseConstants;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppboyNotificationRemoteViewsUtils {
    public static final String APPBOY_NOTIFICATION_CONTENT_ID = "com_appboy_notification_content";
    public static final String APPBOY_NOTIFICATION_ICON_ID = "com_appboy_notification_icon";
    public static final String APPBOY_NOTIFICATION_ID = "com_appboy_notification";
    public static final String APPBOY_NOTIFICATION_ID_NO_ICON = "com_appboy_notification_no_icon";
    public static final String APPBOY_NOTIFICATION_TIME_ID = "com_appboy_notification_time";
    public static final String APPBOY_NOTIFICATION_TITLE_ID = "com_appboy_notification_title";
    public static final String APPBOY_NOTIFICATION_TWELVE_HOUR_FORTMAT_ID = "com_appboy_notification_time_twelve_hour_format";
    public static final String APPBOY_NOTIFICATION_TWENTY_FOUR_HOUR_FORMAT_ID = "com_appboy_notification_time_twenty_four_hour_format";
    private static final String TAG;

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyNotificationRemoteViewsUtils.class.getName()});
    }

    @TargetApi(11)
    public static RemoteViews createMultiLineContentNotificationView(Context context, Bundle bundle, int i, boolean z) {
        if (bundle != null) {
            int identifier;
            CharSequence string = bundle.getString(Constants.APPBOY_PUSH_TITLE_KEY);
            CharSequence string2 = bundle.getString(Constants.APPBOY_PUSH_CONTENT_KEY);
            Resources resources = context.getResources();
            String resourcePackageName = PackageUtils.getResourcePackageName(context);
            if (z) {
                identifier = resources.getIdentifier(APPBOY_NOTIFICATION_ID, ResponseConstants.LAYOUT, resourcePackageName);
            } else {
                identifier = resources.getIdentifier(APPBOY_NOTIFICATION_ID_NO_ICON, ResponseConstants.LAYOUT, resourcePackageName);
            }
            int identifier2 = resources.getIdentifier(APPBOY_NOTIFICATION_TITLE_ID, ResponseConstants.ID, resourcePackageName);
            int identifier3 = resources.getIdentifier(APPBOY_NOTIFICATION_CONTENT_ID, ResponseConstants.ID, resourcePackageName);
            int identifier4 = resources.getIdentifier(APPBOY_NOTIFICATION_ICON_ID, ResponseConstants.ID, resourcePackageName);
            int identifier5 = resources.getIdentifier(APPBOY_NOTIFICATION_TIME_ID, ResponseConstants.ID, resourcePackageName);
            int identifier6 = resources.getIdentifier(APPBOY_NOTIFICATION_TWENTY_FOUR_HOUR_FORMAT_ID, "string", resourcePackageName);
            int identifier7 = resources.getIdentifier(APPBOY_NOTIFICATION_TWELVE_HOUR_FORTMAT_ID, "string", resourcePackageName);
            resourcePackageName = AppboyNotificationUtils.getOptionalStringResource(resources, identifier6, Constants.DEFAULT_TWENTY_FOUR_HOUR_TIME_FORMAT);
            String optionalStringResource = AppboyNotificationUtils.getOptionalStringResource(resources, identifier7, Constants.DEFAULT_TWELVE_HOUR_TIME_FORMAT);
            if (identifier == 0 || identifier2 == 0 || identifier3 == 0 || identifier4 == 0 || identifier5 == 0) {
                AppboyLogger.m670w(TAG, String.format("Couldn't find all resource IDs for custom notification view, extended view will not be used for push notifications. Received %d for layout, %d for title, %d for content, %d for icon, and %d for time.", new Object[]{Integer.valueOf(identifier), Integer.valueOf(identifier2), Integer.valueOf(identifier3), Integer.valueOf(identifier4), Integer.valueOf(identifier5)}));
            } else {
                RemoteViews remoteViews;
                AppboyLogger.m662d(TAG, "Using RemoteViews for rendering of push notification.");
                try {
                    remoteViews = new RemoteViews(PackageUtils.getResourcePackageName(context), identifier);
                } catch (Throwable e) {
                    AppboyLogger.m665e(TAG, String.format("Failed to initialize remote views with package %s", new Object[]{PackageUtils.getResourcePackageName(context)}), e);
                    try {
                        remoteViews = new RemoteViews(context.getPackageName(), identifier);
                    } catch (Throwable e2) {
                        AppboyLogger.m665e(TAG, String.format("Failed to initialize remote views with package %s", new Object[]{context.getPackageName()}), e2);
                        return null;
                    }
                }
                remoteViews.setTextViewText(identifier2, string);
                remoteViews.setTextViewText(identifier3, string2);
                if (z) {
                    remoteViews.setImageViewResource(identifier4, i);
                }
                remoteViews.setTextViewText(identifier5, new SimpleDateFormat(DateFormat.is24HourFormat(context) ? resourcePackageName : optionalStringResource).format(new Date()));
                return remoteViews;
            }
        }
        return null;
    }
}

package com.appboy.push;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat.Builder;
import com.appboy.Appboy;
import com.appboy.AppboyAdmReceiver;
import com.appboy.AppboyGcmReceiver;
import com.appboy.Constants;
import com.appboy.IAppboyNotificationFactory;
import com.appboy.configuration.XmlAppConfigurationProvider;
import com.appboy.support.AppboyLogger;
import com.appboy.support.IntentUtils;
import com.appboy.support.PackageUtils;
import com.appboy.support.PermissionUtils;
import com.etsy.android.lib.models.ListingVideos;
import com.foresee.mobileReplay.perfLog.PerfDbContentProvider;
import com.foresee.sdk.configuration.Configuration;
import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

public class AppboyNotificationUtils {
    public static final String APPBOY_NOTIFICATION_OPENED_SUFFIX = ".intent.APPBOY_NOTIFICATION_OPENED";
    public static final String APPBOY_NOTIFICATION_RECEIVED_SUFFIX = ".intent.APPBOY_PUSH_RECEIVED";
    private static final String SOURCE_KEY = "source";
    private static final String TAG;

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyNotificationUtils.class.getName()});
    }

    @Deprecated
    public static Bundle getAppboyExtras(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (Constants.IS_AMAZON.booleanValue()) {
            return bundle;
        }
        return bundle.getBundle(Constants.APPBOY_PUSH_EXTRAS_KEY);
    }

    public static void handleNotificationOpened(Context context, Intent intent) {
        try {
            logNotificationOpened(context, intent);
            sendNotificationOpenedBroadcast(context, intent);
        } catch (Throwable e) {
            AppboyLogger.m665e(TAG, "Exception occurred attempting to handle notification.", e);
        }
    }

    public static Bundle getAppboyExtrasWithoutPreprocessing(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (Constants.IS_AMAZON.booleanValue()) {
            return new Bundle(bundle);
        }
        return parseJSONStringDictionaryIntoBundle(bundleOptString(bundle, Constants.APPBOY_PUSH_EXTRAS_KEY, "{}"));
    }

    @TargetApi(12)
    public static String bundleOptString(Bundle bundle, String str, String str2) {
        if (VERSION.SDK_INT >= 12) {
            return bundle.getString(str, str2);
        }
        String string = bundle.getString(str);
        if (string != null) {
            return string;
        }
        return str2;
    }

    public static Bundle parseJSONStringDictionaryIntoBundle(String str) {
        try {
            Bundle bundle = new Bundle();
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                bundle.putString(str2, jSONObject.getString(str2));
            }
            return bundle;
        } catch (Throwable e) {
            AppboyLogger.m665e(TAG, String.format("Unable parse JSON into a bundle.", new Object[0]), e);
            return null;
        }
    }

    public static boolean isAppboyPushMessage(Intent intent) {
        Bundle extras = intent.getExtras();
        return extras != null && "true".equals(extras.getString(Constants.APPBOY_PUSH_APPBOY_KEY));
    }

    public static boolean isNotificationMessage(Intent intent) {
        Bundle extras = intent.getExtras();
        return extras != null && extras.containsKey(Constants.APPBOY_PUSH_TITLE_KEY) && extras.containsKey(Constants.APPBOY_PUSH_CONTENT_KEY);
    }

    public static void sendPushMessageReceivedBroadcast(Context context, Bundle bundle) {
        Intent intent = new Intent(context.getPackageName() + APPBOY_NOTIFICATION_RECEIVED_SUFFIX);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.sendBroadcast(intent);
    }

    public static void setNotificationDurationAlarm(Context context, Class<?> cls, int i, int i2) {
        Intent intent = new Intent(context, cls);
        intent.setAction(Constants.APPBOY_CANCEL_NOTIFICATION_ACTION);
        intent.putExtra(Constants.APPBOY_PUSH_NOTIFICATION_ID, i);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, 134217728);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompatApi21.CATEGORY_ALARM);
        if (i2 >= Constants.APPBOY_MINIMUM_NOTIFICATION_DURATION_MILLIS) {
            alarmManager.set(3, SystemClock.elapsedRealtime() + ((long) i2), broadcast);
        }
    }

    public static int getNotificationId(Bundle bundle) {
        if (bundle == null) {
            AppboyLogger.m662d(TAG, String.format("Message without extras bundle received.  Using default notification id: -1", new Object[0]));
            return -1;
        } else if (bundle.containsKey(PerfDbContentProvider.COL_N)) {
            try {
                r0 = Integer.parseInt(bundle.getString(PerfDbContentProvider.COL_N));
                AppboyLogger.m662d(TAG, String.format("Using notification id provided in the message's extras bundle: " + r0, new Object[0]));
                return r0;
            } catch (Throwable e) {
                AppboyLogger.m665e(TAG, String.format("Unable to parse notification id provided in the message's extras bundle. Using default notification id instead: -1", new Object[0]), e);
                return -1;
            }
        } else {
            r0 = (bundleOptString(bundle, Constants.APPBOY_PUSH_TITLE_KEY, StringUtils.EMPTY) + bundleOptString(bundle, Constants.APPBOY_PUSH_CONTENT_KEY, StringUtils.EMPTY)).hashCode();
            AppboyLogger.m662d(TAG, String.format("Message without notification id provided in the extras bundle received.  Using a hash of the message: " + r0, new Object[0]));
            return r0;
        }
    }

    @TargetApi(16)
    public static int getNotificationPriority(Bundle bundle) {
        if (bundle != null && bundle.containsKey(Constants.APPBOY_PUSH_PRIORITY_KEY)) {
            try {
                int parseInt = Integer.parseInt(bundle.getString(Constants.APPBOY_PUSH_PRIORITY_KEY));
                if (isValidNotificationPriority(parseInt)) {
                    return parseInt;
                }
                AppboyLogger.m664e(TAG, String.format("Received invalid notification priority %d", new Object[]{Integer.valueOf(parseInt)}));
            } catch (Throwable e) {
                AppboyLogger.m665e(TAG, String.format("Unable to parse custom priority. Returning default priority of 0", new Object[0]), e);
            }
        }
        return 0;
    }

    @TargetApi(16)
    public static boolean isValidNotificationPriority(int i) {
        return i >= -2 && i <= 2;
    }

    public static boolean wakeScreenIfHasPermission(Context context, Bundle bundle) {
        if (!PermissionUtils.hasPermission(context, "android.permission.WAKE_LOCK")) {
            return false;
        }
        if (VERSION.SDK_INT >= 16 && getNotificationPriority(bundle) == -2) {
            return false;
        }
        WakeLock newWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(268435482, TAG);
        newWakeLock.acquire();
        newWakeLock.release();
        return true;
    }

    public static IAppboyNotificationFactory getActiveNotificationFactory() {
        IAppboyNotificationFactory customAppboyNotificationFactory = Appboy.getCustomAppboyNotificationFactory();
        if (customAppboyNotificationFactory == null) {
            return AppboyNotificationFactory.getInstance();
        }
        return customAppboyNotificationFactory;
    }

    public static void setTitleIfPresent(Builder builder, Bundle bundle) {
        if (bundle != null) {
            builder.setContentTitle(bundle.getString(Constants.APPBOY_PUSH_TITLE_KEY));
        }
    }

    public static void setContentIfPresent(Builder builder, Bundle bundle) {
        if (bundle != null) {
            builder.setContentText(bundle.getString(Constants.APPBOY_PUSH_CONTENT_KEY));
        }
    }

    public static void setTickerIfPresent(Builder builder, Bundle bundle) {
        if (bundle != null) {
            builder.setTicker(bundle.getString(Constants.APPBOY_PUSH_TITLE_KEY));
        }
    }

    public static void setContentIntentIfPresent(Context context, Builder builder, Bundle bundle) {
        try {
            Intent intent = new Intent(Constants.APPBOY_PUSH_CLICKED_ACTION).setClass(context, getNotificationReceiverClass());
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            builder.setContentIntent(PendingIntent.getBroadcast(context, IntentUtils.getRequestCode(), intent, 1073741824));
        } catch (Throwable e) {
            AppboyLogger.m665e(TAG, "Error setting content.", e);
        }
    }

    public static int setSmallIcon(XmlAppConfigurationProvider xmlAppConfigurationProvider, Builder builder) {
        int smallNotificationIconResourceId = xmlAppConfigurationProvider.getSmallNotificationIconResourceId();
        if (smallNotificationIconResourceId == 0) {
            AppboyLogger.m662d(TAG, "Small notification icon resource was not found. Will use the app icon when displaying notifications.");
            smallNotificationIconResourceId = xmlAppConfigurationProvider.getApplicationIconResourceId();
        }
        builder.setSmallIcon(smallNotificationIconResourceId);
        return smallNotificationIconResourceId;
    }

    public static boolean setLargeIconIfPresentAndSupported(Context context, XmlAppConfigurationProvider xmlAppConfigurationProvider, Builder builder) {
        if (VERSION.SDK_INT >= 11) {
            int largeNotificationIconResourceId = xmlAppConfigurationProvider.getLargeNotificationIconResourceId();
            if (largeNotificationIconResourceId != 0) {
                try {
                    builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), largeNotificationIconResourceId));
                    return true;
                } catch (Throwable e) {
                    AppboyLogger.m665e(TAG, "Error setting large notification icon", e);
                }
            }
        }
        return false;
    }

    public static void setSoundIfPresentAndSupported(Builder builder, Bundle bundle) {
        if (VERSION.SDK_INT >= 11 && bundle != null && bundle.containsKey(ListingVideos.LOW_QUALITy)) {
            String string = bundle.getString(ListingVideos.LOW_QUALITy);
            if (string == null) {
                return;
            }
            if (string.equals(Constants.APPBOY_PUSH_NOTIFICATION_SOUND_DEFAULT_VALUE)) {
                builder.setDefaults(1);
            } else {
                builder.setSound(Uri.parse(string));
            }
        }
    }

    public static void setSummaryTextIfPresentAndSupported(Builder builder, Bundle bundle) {
        if (VERSION.SDK_INT >= 16 && bundle != null && bundle.containsKey(Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY)) {
            CharSequence string = bundle.getString(Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY);
            if (string != null) {
                builder.setSubText(string);
            }
        }
    }

    public static void setPriorityIfPresentAndSupported(Builder builder, Bundle bundle) {
        if (VERSION.SDK_INT >= 16 && bundle != null) {
            builder.setPriority(getNotificationPriority(bundle));
        }
    }

    public static void setStyleIfSupported(Context context, Builder builder, Bundle bundle, Bundle bundle2) {
        if (VERSION.SDK_INT >= 16 && bundle != null) {
            builder.setStyle(AppboyNotificationStyleFactory.getBigNotificationStyle(context, bundle, bundle2));
        }
    }

    public static void setAccentColorIfPresentAndSupported(XmlAppConfigurationProvider xmlAppConfigurationProvider, Builder builder, Bundle bundle) {
        if (VERSION.SDK_INT < 21) {
            return;
        }
        if (bundle == null || !bundle.containsKey(Constants.APPBOY_PUSH_ACCENT_KEY)) {
            builder.setColor(xmlAppConfigurationProvider.getDefaultNotificationAccentColor());
        } else {
            builder.setColor((int) Long.parseLong(bundle.getString(Constants.APPBOY_PUSH_ACCENT_KEY)));
        }
    }

    public static void setCategoryIfPresentAndSupported(Builder builder, Bundle bundle) {
        if (VERSION.SDK_INT >= 21 && bundle != null && bundle.containsKey(Constants.APPBOY_PUSH_CATEGORY_KEY)) {
            builder.setCategory(bundle.getString(Constants.APPBOY_PUSH_CATEGORY_KEY));
        }
    }

    public static void setVisibilityIfPresentAndSupported(Builder builder, Bundle bundle) {
        if (VERSION.SDK_INT >= 21 && bundle != null && bundle.containsKey(Constants.APPBOY_PUSH_VISIBILITY_KEY)) {
            try {
                int parseInt = Integer.parseInt(bundle.getString(Constants.APPBOY_PUSH_VISIBILITY_KEY));
                if (isValidNotificationVisibility(parseInt)) {
                    builder.setVisibility(parseInt);
                    return;
                }
                AppboyLogger.m664e(TAG, String.format("Received invalid notification visibility %d", new Object[]{Integer.valueOf(parseInt)}));
            } catch (Throwable e) {
                AppboyLogger.m665e(TAG, "Failed to parse visibility from notificationExtras", e);
            }
        }
    }

    public static void setPublicVersionIfPresentAndSupported(Context context, XmlAppConfigurationProvider xmlAppConfigurationProvider, Builder builder, Bundle bundle) {
        if (VERSION.SDK_INT >= 21 && bundle != null && bundle.containsKey(Constants.APPBOY_PUSH_PUBLIC_NOTIFICATION_KEY)) {
            Bundle parseJSONStringDictionaryIntoBundle = parseJSONStringDictionaryIntoBundle(bundle.getString(Constants.APPBOY_PUSH_PUBLIC_NOTIFICATION_KEY));
            Builder builder2 = new Builder(context);
            setContentIfPresent(builder2, parseJSONStringDictionaryIntoBundle);
            setTitleIfPresent(builder2, parseJSONStringDictionaryIntoBundle);
            setSummaryTextIfPresentAndSupported(builder2, parseJSONStringDictionaryIntoBundle);
            setSmallIcon(xmlAppConfigurationProvider, builder2);
            setAccentColorIfPresentAndSupported(xmlAppConfigurationProvider, builder2, parseJSONStringDictionaryIntoBundle);
            builder.setPublicVersion(builder2.build());
        }
    }

    @TargetApi(21)
    public static boolean isValidNotificationVisibility(int i) {
        return i == -1 || i == 0 || i == 1;
    }

    public static void logBaiduNotificationClick(Context context, String str) {
        if (str == null) {
            AppboyLogger.m670w(TAG, "customContentString was null. Doing nothing.");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString(SOURCE_KEY, null);
            String optString2 = jSONObject.optString(Constants.APPBOY_PUSH_CAMPAIGN_ID_KEY, null);
            if (optString != null && Constants.APPBOY.equals(optString) && optString2 != null) {
                Appboy.getInstance(context).logPushNotificationOpened(optString2);
            }
        } catch (Throwable e) {
            AppboyLogger.m665e(TAG, String.format("Caught an exception processing customContentString: %s", new Object[]{str}), e);
        }
    }

    public static void handleCancelNotificationAction(Context context, Intent intent) {
        try {
            if (intent.hasExtra(Constants.APPBOY_PUSH_NOTIFICATION_ID)) {
                ((NotificationManager) context.getSystemService(Configuration.NOTIFICATION_LAYOUT_NAME)).cancel(Constants.APPBOY_PUSH_NOTIFICATION_TAG, intent.getIntExtra(Constants.APPBOY_PUSH_NOTIFICATION_ID, -1));
            }
        } catch (Throwable e) {
            AppboyLogger.m665e(TAG, "Exception occurred handling cancel notification intent.", e);
        }
    }

    public static void cancelNotification(Context context, int i) {
        try {
            Intent intent = new Intent(Constants.APPBOY_CANCEL_NOTIFICATION_ACTION).setClass(context, getNotificationReceiverClass());
            intent.putExtra(Constants.APPBOY_PUSH_NOTIFICATION_ID, i);
            context.sendBroadcast(intent);
        } catch (Throwable e) {
            AppboyLogger.m665e(TAG, "Exception occurred attempting to cancel notification.", e);
        }
    }

    public static Class<?> getNotificationReceiverClass() {
        if (Constants.IS_AMAZON.booleanValue()) {
            return AppboyAdmReceiver.class;
        }
        return AppboyGcmReceiver.class;
    }

    public static boolean isUninstallTrackingPush(Bundle bundle) {
        if (bundle != null) {
            if (bundle.containsKey(Constants.APPBOY_PUSH_UNINSTALL_TRACKING_KEY)) {
                return true;
            }
            Bundle bundle2 = bundle.getBundle(Constants.APPBOY_PUSH_EXTRAS_KEY);
            if (bundle2 != null) {
                return bundle2.containsKey(Constants.APPBOY_PUSH_UNINSTALL_TRACKING_KEY);
            }
        }
        return false;
    }

    static String getOptionalStringResource(Resources resources, int i, String str) {
        try {
            str = resources.getString(i);
        } catch (NotFoundException e) {
        }
        return str;
    }

    static void sendNotificationOpenedBroadcast(Context context, Intent intent) {
        Intent intent2 = new Intent(PackageUtils.getResourcePackageName(context) + APPBOY_NOTIFICATION_OPENED_SUFFIX);
        if (intent.getExtras() != null) {
            intent2.putExtras(intent.getExtras());
        }
        context.sendBroadcast(intent2);
    }

    private static void logNotificationOpened(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra(Constants.APPBOY_PUSH_CAMPAIGN_ID_KEY);
        if (stringExtra != null) {
            AppboyLogger.m666i(TAG, String.format("Logging push click to Appboy. Campaign Id: " + stringExtra, new Object[0]));
            Appboy.getInstance(context).logPushNotificationOpened(stringExtra);
            return;
        }
        AppboyLogger.m666i(TAG, String.format("No campaign Id associated with this notification. Not logging push click to Appboy.", new Object[0]));
    }
}

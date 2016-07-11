package com.appboy.push;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.NotificationCompat.Action;
import android.support.v4.app.NotificationCompat.Builder;
import com.appboy.Appboy;
import com.appboy.AppboyImageUtils;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import com.appboy.support.IntentUtils;
import com.appboy.support.PackageUtils;
import com.appboy.ui.support.StringUtils;

public class AppboyNotificationActionUtils {
    public static final String DEFAULT_LOCAL_STORAGE_FOLDER = "Shared Photos";
    public static final String IMAGE_MIME_TYPE = "image/*";
    private static final String TAG;
    public static final String TEXT_MIME_TYPE = "text/plain";

    class ShareTask extends AsyncTask<Intent, Integer, Intent> {
        private final Context mContext;

        public ShareTask(Context context) {
            this.mContext = context;
        }

        protected Intent doInBackground(Intent... intentArr) {
            if (this.mContext != null) {
                return AppboyNotificationActionUtils.createShareActionIntent(this.mContext, intentArr[0]);
            }
            return null;
        }

        protected void onPostExecute(Intent intent) {
            if (intent != null && this.mContext != null) {
                if (intent != null) {
                    this.mContext.startActivity(intent);
                } else {
                    AppboyLogger.m670w(AppboyNotificationActionUtils.TAG, "Null share intent received.  Not starting share intent.");
                }
            }
        }
    }

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyNotificationActionUtils.class.getName()});
    }

    @TargetApi(16)
    public static void addNotificationActions(Context context, Builder builder, Bundle bundle) {
        if (bundle == null) {
            try {
                AppboyLogger.m670w(TAG, String.format("Notification extras were null. Doing nothing.", new Object[0]));
            } catch (Exception e) {
                AppboyLogger.m664e(TAG, String.format("Caught exception while adding notification action buttons.", new Object[]{e}));
            }
        } else if (VERSION.SDK_INT >= 16) {
            for (int i = 0; !StringUtils.isNullOrBlank(getActionFieldAtIndex(i, bundle, Constants.APPBOY_PUSH_ACTION_TYPE_KEY_TEMPLATE)); i++) {
                addNotificationAction(context, builder, bundle, i);
            }
        }
    }

    @TargetApi(16)
    public static void handleNotificationActionClicked(Context context, Intent intent) {
        try {
            String stringExtra = intent.getStringExtra(Constants.APPBOY_ACTION_TYPE_KEY);
            if (StringUtils.isNullOrBlank(stringExtra)) {
                AppboyLogger.m670w(TAG, String.format("Notification action button type was blank or null.  Doing nothing.", new Object[0]));
                return;
            }
            int intExtra = intent.getIntExtra(Constants.APPBOY_PUSH_NOTIFICATION_ID, -1);
            if (!stringExtra.equals(Constants.APPBOY_PUSH_ACTION_TYPE_NONE)) {
                logNotificationActionClicked(context, intent);
            }
            if (stringExtra.equals(Constants.APPBOY_PUSH_ACTION_TYPE_URI) || stringExtra.equals(Constants.APPBOY_PUSH_ACTION_TYPE_OPEN)) {
                AppboyNotificationUtils.cancelNotification(context, intExtra);
                context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
                if (stringExtra.equals(Constants.APPBOY_PUSH_ACTION_TYPE_URI) && intent.getExtras().containsKey(Constants.APPBOY_ACTION_URI_KEY)) {
                    intent.putExtra(Constants.APPBOY_PUSH_DEEP_LINK_KEY, intent.getStringExtra(Constants.APPBOY_ACTION_URI_KEY));
                } else {
                    intent.removeExtra(Constants.APPBOY_PUSH_DEEP_LINK_KEY);
                }
                AppboyNotificationUtils.sendNotificationOpenedBroadcast(context, intent);
            } else if (stringExtra.equals(Constants.APPBOY_PUSH_ACTION_TYPE_SHARE)) {
                AppboyNotificationUtils.cancelNotification(context, intExtra);
                context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
                handleShareActionClicked(context, intent);
            } else if (stringExtra.equals(Constants.APPBOY_PUSH_ACTION_TYPE_NONE)) {
                AppboyNotificationUtils.cancelNotification(context, intExtra);
            } else {
                AppboyLogger.m666i(TAG, String.format("Custom notification action button clicked. Doing nothing and passing on data to client receiver.", new Object[0]));
                AppboyNotificationUtils.sendNotificationOpenedBroadcast(context, intent);
            }
        } catch (Exception e) {
            AppboyLogger.m664e(TAG, String.format("Caught exception while handling notification action button click.", new Object[]{e}));
        }
    }

    static boolean canShareImage(Context context, Bundle bundle) {
        return bundle != null && bundle.containsKey(Constants.APPBOY_PUSH_BIG_IMAGE_URL_KEY) && AppboyImageUtils.isWriteExternalPermissionGranted(context);
    }

    static int getIconDrawableResourceId(Context context, String str) {
        if (context == null || StringUtils.isNullOrBlank(str)) {
            return 0;
        }
        return context.getResources().getIdentifier(str, "drawable", PackageUtils.getResourcePackageName(context));
    }

    static boolean isCustomActionType(String str) {
        if (str.equals(Constants.APPBOY_PUSH_ACTION_TYPE_URI) || str.equals(Constants.APPBOY_PUSH_ACTION_TYPE_OPEN) || str.equals(Constants.APPBOY_PUSH_ACTION_TYPE_NONE) || str.equals(Constants.APPBOY_PUSH_ACTION_TYPE_SHARE)) {
            return false;
        }
        return true;
    }

    @TargetApi(16)
    private static void addNotificationAction(Context context, Builder builder, Bundle bundle, int i) {
        Bundle bundle2 = new Bundle(bundle);
        String actionFieldAtIndex = getActionFieldAtIndex(i, bundle, Constants.APPBOY_PUSH_ACTION_TYPE_KEY_TEMPLATE);
        bundle2.putInt(Constants.APPBOY_ACTION_INDEX_KEY, i);
        bundle2.putString(Constants.APPBOY_ACTION_TYPE_KEY, actionFieldAtIndex);
        bundle2.putString(Constants.APPBOY_ACTION_ID_KEY, getActionFieldAtIndex(i, bundle, Constants.APPBOY_PUSH_ACTION_ID_KEY_TEMPLATE));
        bundle2.putString(Constants.APPBOY_ACTION_URI_KEY, getActionFieldAtIndex(i, bundle, Constants.APPBOY_PUSH_ACTION_URI_KEY_TEMPLATE));
        bundle2.putBoolean(Constants.APPBOY_ACTION_IS_CUSTOM_ACTION_KEY, isCustomActionType(actionFieldAtIndex));
        actionFieldAtIndex = getActionFieldAtIndex(i, bundle, Constants.APPBOY_PUSH_ACTION_ICON_KEY_TEMPLATE);
        bundle2.putString(Constants.APPBOY_ACTION_ICON_KEY, getActionFieldAtIndex(i, bundle, Constants.APPBOY_PUSH_ACTION_ICON_KEY_TEMPLATE));
        Intent intent = new Intent(Constants.APPBOY_ACTION_CLICKED_ACTION).setClass(context, AppboyNotificationUtils.getNotificationReceiverClass());
        intent.putExtras(bundle2);
        Action.Builder builder2 = new Action.Builder(getIconDrawableResourceId(context, actionFieldAtIndex), getActionFieldAtIndex(i, bundle, Constants.APPBOY_PUSH_ACTION_TEXT_KEY_TEMPLATE), PendingIntent.getBroadcast(context, IntentUtils.getRequestCode(), intent, 134217728));
        builder2.addExtras(new Bundle(bundle2));
        builder.addAction(builder2.build());
    }

    private static void logNotificationActionClicked(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra(Constants.APPBOY_PUSH_CAMPAIGN_ID_KEY);
        String stringExtra2 = intent.getStringExtra(Constants.APPBOY_ACTION_ID_KEY);
        if (StringUtils.isNullOrBlank(stringExtra)) {
            AppboyLogger.m666i(TAG, String.format("No campaign Id associated with this notification.  Not logging push action click to Appboy.", new Object[0]));
        } else if (StringUtils.isNullOrBlank(stringExtra2)) {
            AppboyLogger.m666i(TAG, String.format("No action button Id associated with this notification action.  Not logging push action click to Appboy.", new Object[0]));
        } else {
            AppboyLogger.m666i(TAG, String.format("Logging push action click to Appboy. Campaign Id: " + stringExtra + " Action Button Id: " + stringExtra2, new Object[0]));
            Appboy.getInstance(context).logPushNotificationActionClicked(stringExtra, stringExtra2);
        }
    }

    private static void handleShareActionClicked(Context context, Intent intent) {
        new ShareTask(context).execute(new Intent[]{intent});
    }

    private static Intent createShareActionIntent(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        Intent intent2 = new Intent("android.intent.action.SEND");
        intent2.putExtra("android.intent.extra.SUBJECT", extras.getString(Constants.APPBOY_PUSH_TITLE_KEY));
        intent2.putExtra("android.intent.extra.TEXT", extras.getString(Constants.APPBOY_PUSH_CONTENT_KEY));
        extras = extras.getBundle(Constants.APPBOY_PUSH_EXTRAS_KEY);
        if (canShareImage(context, extras)) {
            String l = Long.toString(System.currentTimeMillis());
            Parcelable storeBitmapLocally = AppboyImageUtils.storeBitmapLocally(context.getApplicationContext(), AppboyImageUtils.downloadImageBitmap(extras.getString(Constants.APPBOY_PUSH_BIG_IMAGE_URL_KEY)), l, DEFAULT_LOCAL_STORAGE_FOLDER);
            intent2.setType(IMAGE_MIME_TYPE);
            intent2.putExtra("android.intent.extra.STREAM", storeBitmapLocally);
            intent2.addFlags(1);
        } else {
            intent2.setType(TEXT_MIME_TYPE);
        }
        intent2.setFlags(268435456);
        return intent2;
    }

    static String getActionFieldAtIndex(int i, Bundle bundle, String str) {
        String string = bundle.getString(str.replace("*", String.valueOf(i)));
        if (string == null) {
            return org.apache.commons.lang3.StringUtils.EMPTY;
        }
        return string;
    }
}

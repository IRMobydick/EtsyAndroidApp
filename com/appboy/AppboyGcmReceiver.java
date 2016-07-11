package com.appboy;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import com.appboy.configuration.XmlAppConfigurationProvider;
import com.appboy.push.AppboyNotificationActionUtils;
import com.appboy.push.AppboyNotificationUtils;
import com.appboy.support.AppboyLogger;

public final class AppboyGcmReceiver extends BroadcastReceiver {
    public static final String CAMPAIGN_ID_KEY = "cid";
    private static final String GCM_DELETED_MESSAGES_KEY = "deleted_messages";
    private static final String GCM_ERROR_KEY = "error";
    private static final String GCM_MESSAGE_TYPE_KEY = "message_type";
    private static final String GCM_NUMBER_OF_MESSAGES_DELETED_KEY = "total_deleted";
    private static final String GCM_RECEIVE_INTENT_ACTION = "com.google.android.c2dm.intent.RECEIVE";
    private static final String GCM_REGISTRATION_ID_KEY = "registration_id";
    private static final String GCM_REGISTRATION_INTENT_ACTION = "com.google.android.c2dm.intent.REGISTRATION";
    private static final String GCM_UNREGISTERED_KEY = "unregistered";
    private static final String TAG;

    public class HandleAppboyGcmMessageTask extends AsyncTask<Void, Void, Void> {
        private final Context context;
        private final Intent intent;

        public HandleAppboyGcmMessageTask(Context context, Intent intent) {
            this.context = context;
            this.intent = intent;
            execute(new Void[0]);
        }

        protected Void doInBackground(Void... voidArr) {
            AppboyGcmReceiver.this.handleAppboyGcmMessage(this.context, this.intent);
            return null;
        }
    }

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyGcmReceiver.class.getName()});
    }

    public void onReceive(Context context, Intent intent) {
        AppboyLogger.m666i(TAG, String.format("Received broadcast message. Message: %s", new Object[]{intent.toString()}));
        String action = intent.getAction();
        if (GCM_REGISTRATION_INTENT_ACTION.equals(action)) {
            handleRegistrationEventIfEnabled(new XmlAppConfigurationProvider(context), context, intent);
        } else if (GCM_RECEIVE_INTENT_ACTION.equals(action)) {
            handleAppboyGcmReceiveIntent(context, intent);
        } else if (Constants.APPBOY_CANCEL_NOTIFICATION_ACTION.equals(action)) {
            AppboyNotificationUtils.handleCancelNotificationAction(context, intent);
        } else if (Constants.APPBOY_ACTION_CLICKED_ACTION.equals(action)) {
            AppboyNotificationActionUtils.handleNotificationActionClicked(context, intent);
        } else if (Constants.APPBOY_PUSH_CLICKED_ACTION.equals(action)) {
            AppboyNotificationUtils.handleNotificationOpened(context, intent);
        } else {
            AppboyLogger.m670w(TAG, String.format("The GCM receiver received a message not sent from Appboy. Ignoring the message.", new Object[0]));
        }
    }

    boolean handleRegistrationIntent(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra(GCM_ERROR_KEY);
        String stringExtra2 = intent.getStringExtra(GCM_REGISTRATION_ID_KEY);
        if (stringExtra != null) {
            if ("SERVICE_NOT_AVAILABLE".equals(stringExtra)) {
                Log.e(TAG, "Unable to connect to the GCM registration server. Try again later.");
            } else if ("ACCOUNT_MISSING".equals(stringExtra)) {
                Log.e(TAG, "No Google account found on the phone. For pre-3.0 devices, a Google account is required on the device.");
            } else if ("AUTHENTICATION_FAILED".equals(stringExtra)) {
                Log.e(TAG, "Unable to authenticate Google account. For Android versions <4.0.4, a valid Google Play account is required for Google Cloud Messaging to function. This phone will be unable to receive Google Cloud Messages until the user logs in with a valid Google Play account or upgrades the operating system on this device.");
            } else if ("INVALID_SENDER".equals(stringExtra)) {
                Log.e(TAG, "One or multiple of the sender IDs provided are invalid.");
            } else if ("PHONE_REGISTRATION_ERROR".equals(stringExtra)) {
                Log.e(TAG, "Device does not support GCM.");
            } else if ("INVALID_PARAMETERS".equals(stringExtra)) {
                Log.e(TAG, "The request sent by the device does not contain the expected parameters. This phone does not currently support GCM.");
            } else {
                AppboyLogger.m670w(TAG, String.format("Received an unrecognised GCM registration error type. Ignoring. Error: %s", new Object[]{stringExtra}));
            }
        } else if (stringExtra2 != null) {
            Appboy.getInstance(context).registerAppboyPushMessages(stringExtra2);
        } else if (intent.hasExtra(GCM_UNREGISTERED_KEY)) {
            Appboy.getInstance(context).unregisterAppboyPushMessages();
        } else {
            AppboyLogger.m670w(TAG, "The GCM registration message is missing error information, registration id, and unregistration confirmation. Ignoring.");
            return false;
        }
        return true;
    }

    boolean handleAppboyGcmMessage(Context context, Intent intent) {
        NotificationManagerCompat from = NotificationManagerCompat.from(context);
        if (GCM_DELETED_MESSAGES_KEY.equals(intent.getStringExtra(GCM_MESSAGE_TYPE_KEY))) {
            if (intent.getIntExtra(GCM_NUMBER_OF_MESSAGES_DELETED_KEY, -1) == -1) {
                Log.e(TAG, String.format("Unable to parse GCM message. Intent: %s", new Object[]{intent.toString()}));
                return false;
            }
            AppboyLogger.m666i(TAG, String.format("GCM deleted %d messages. Fetch them from Appboy.", new Object[]{Integer.valueOf(r2)}));
            return false;
        }
        Bundle extras = intent.getExtras();
        Bundle appboyExtrasWithoutPreprocessing = AppboyNotificationUtils.getAppboyExtrasWithoutPreprocessing(extras);
        extras.putBundle(Constants.APPBOY_PUSH_EXTRAS_KEY, appboyExtrasWithoutPreprocessing);
        if (AppboyNotificationUtils.isNotificationMessage(intent)) {
            int notificationId = AppboyNotificationUtils.getNotificationId(extras);
            extras.putInt(Constants.APPBOY_PUSH_NOTIFICATION_ID, notificationId);
            try {
                Notification createNotification = AppboyNotificationUtils.getActiveNotificationFactory().createNotification(new XmlAppConfigurationProvider(context), context, extras, appboyExtrasWithoutPreprocessing);
                if (createNotification == null) {
                    return false;
                }
                from.notify(Constants.APPBOY_PUSH_NOTIFICATION_TAG, notificationId, createNotification);
                AppboyNotificationUtils.sendPushMessageReceivedBroadcast(context, extras);
                AppboyNotificationUtils.wakeScreenIfHasPermission(context, extras);
                if (extras != null && extras.containsKey(Constants.APPBOY_PUSH_NOTIFICATION_DURATION_KEY)) {
                    AppboyNotificationUtils.setNotificationDurationAlarm(context, getClass(), notificationId, Integer.parseInt(extras.getString(Constants.APPBOY_PUSH_NOTIFICATION_DURATION_KEY)));
                }
                return true;
            } catch (Throwable e) {
                Log.e(TAG, "Failed to create notification.", e);
                return false;
            }
        }
        AppboyNotificationUtils.sendPushMessageReceivedBroadcast(context, extras);
        return false;
    }

    void handleAppboyGcmReceiveIntent(Context context, Intent intent) {
        if (AppboyNotificationUtils.isAppboyPushMessage(intent)) {
            HandleAppboyGcmMessageTask handleAppboyGcmMessageTask = new HandleAppboyGcmMessageTask(context, intent);
        }
    }

    boolean handleRegistrationEventIfEnabled(XmlAppConfigurationProvider xmlAppConfigurationProvider, Context context, Intent intent) {
        if (!xmlAppConfigurationProvider.isGcmMessagingRegistrationEnabled()) {
            return false;
        }
        handleRegistrationIntent(context, intent);
        return true;
    }
}

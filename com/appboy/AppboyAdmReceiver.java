package com.appboy;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import com.appboy.configuration.XmlAppConfigurationProvider;
import com.appboy.push.AppboyNotificationActionUtils;
import com.appboy.push.AppboyNotificationUtils;
import com.appboy.support.AppboyLogger;
import com.foresee.sdk.configuration.Configuration;

public final class AppboyAdmReceiver extends BroadcastReceiver {
    private static final String ADM_DELETED_MESSAGES_KEY = "deleted_messages";
    private static final String ADM_ERROR_KEY = "error";
    private static final String ADM_MESSAGE_TYPE_KEY = "message_type";
    private static final String ADM_NUMBER_OF_MESSAGES_DELETED_KEY = "total_deleted";
    private static final String ADM_RECEIVE_INTENT_ACTION = "com.amazon.device.messaging.intent.RECEIVE";
    private static final String ADM_REGISTRATION_ID_KEY = "registration_id";
    private static final String ADM_REGISTRATION_INTENT_ACTION = "com.amazon.device.messaging.intent.REGISTRATION";
    private static final String ADM_UNREGISTERED_KEY = "unregistered";
    public static final String CAMPAIGN_ID_KEY = "cid";
    private static final String TAG;

    public class HandleAppboyAdmMessageTask extends AsyncTask<Void, Void, Void> {
        private final Context context;
        private final Intent intent;

        public HandleAppboyAdmMessageTask(Context context, Intent intent) {
            this.context = context;
            this.intent = intent;
            execute(new Void[0]);
        }

        protected Void doInBackground(Void... voidArr) {
            AppboyAdmReceiver.this.handleAppboyAdmMessage(this.context, this.intent);
            return null;
        }
    }

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyAdmReceiver.class.getName()});
    }

    public void onReceive(Context context, Intent intent) {
        AppboyLogger.m666i(TAG, String.format("Received broadcast message. Message: %s", new Object[]{intent.toString()}));
        String action = intent.getAction();
        if (ADM_REGISTRATION_INTENT_ACTION.equals(action)) {
            handleRegistrationEventIfEnabled(new XmlAppConfigurationProvider(context), context, intent);
        } else if (ADM_RECEIVE_INTENT_ACTION.equals(action)) {
            handleAppboyAdmReceiveIntent(context, intent);
        } else if (Constants.APPBOY_CANCEL_NOTIFICATION_ACTION.equals(action)) {
            AppboyNotificationUtils.handleCancelNotificationAction(context, intent);
        } else if (Constants.APPBOY_ACTION_CLICKED_ACTION.equals(action)) {
            AppboyNotificationActionUtils.handleNotificationActionClicked(context, intent);
        } else if (Constants.APPBOY_PUSH_CLICKED_ACTION.equals(action)) {
            AppboyNotificationUtils.handleNotificationOpened(context, intent);
        } else {
            AppboyLogger.m670w(TAG, String.format("The ADM receiver received a message not sent from Appboy. Ignoring the message.", new Object[0]));
        }
    }

    boolean handleRegistrationIntent(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra(ADM_ERROR_KEY);
        String stringExtra2 = intent.getStringExtra(ADM_REGISTRATION_ID_KEY);
        String stringExtra3 = intent.getStringExtra(ADM_UNREGISTERED_KEY);
        if (stringExtra != null) {
            AppboyLogger.m664e(TAG, "Error during ADM registration: " + stringExtra);
        } else if (stringExtra2 != null) {
            AppboyLogger.m666i(TAG, "Registering for ADM messages with registrationId: " + stringExtra2);
            Appboy.getInstance(context).registerAppboyPushMessages(stringExtra2);
        } else if (stringExtra3 != null) {
            AppboyLogger.m666i(TAG, "Unregistering from ADM: " + stringExtra3);
            Appboy.getInstance(context).unregisterAppboyPushMessages();
        } else {
            AppboyLogger.m670w(TAG, "The ADM registration intent is missing error information, registration id, and unregistration confirmation. Ignoring.");
            return false;
        }
        return true;
    }

    boolean handleAppboyAdmMessage(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Configuration.NOTIFICATION_LAYOUT_NAME);
        if (ADM_DELETED_MESSAGES_KEY.equals(intent.getStringExtra(ADM_MESSAGE_TYPE_KEY))) {
            if (intent.getIntExtra(ADM_NUMBER_OF_MESSAGES_DELETED_KEY, -1) == -1) {
                AppboyLogger.m664e(TAG, String.format("Unable to parse ADM message. Intent: %s", new Object[]{intent.toString()}));
            } else {
                AppboyLogger.m666i(TAG, String.format("ADM deleted %d messages. Fetch them from Appboy.", new Object[]{Integer.valueOf(r0)}));
            }
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
                notificationManager.notify(Constants.APPBOY_PUSH_NOTIFICATION_TAG, notificationId, createNotification);
                AppboyNotificationUtils.sendPushMessageReceivedBroadcast(context, extras);
                AppboyNotificationUtils.wakeScreenIfHasPermission(context, extras);
                if (extras.containsKey(Constants.APPBOY_PUSH_NOTIFICATION_DURATION_KEY)) {
                    AppboyNotificationUtils.setNotificationDurationAlarm(context, getClass(), notificationId, Integer.parseInt(extras.getString(Constants.APPBOY_PUSH_NOTIFICATION_DURATION_KEY)));
                }
                return true;
            } catch (Throwable e) {
                AppboyLogger.m665e(TAG, "Failed to create notification.", e);
                return false;
            }
        }
        AppboyNotificationUtils.sendPushMessageReceivedBroadcast(context, extras);
        return false;
    }

    void handleAppboyAdmReceiveIntent(Context context, Intent intent) {
        if (AppboyNotificationUtils.isAppboyPushMessage(intent)) {
            HandleAppboyAdmMessageTask handleAppboyAdmMessageTask = new HandleAppboyAdmMessageTask(context, intent);
        }
    }

    boolean handleRegistrationEventIfEnabled(XmlAppConfigurationProvider xmlAppConfigurationProvider, Context context, Intent intent) {
        AppboyLogger.m666i(TAG, String.format("Received ADM registration. Message: %s", new Object[]{intent.toString()}));
        if (xmlAppConfigurationProvider.isAdmMessagingRegistrationEnabled()) {
            AppboyLogger.m662d(TAG, "ADM enabled in appboy.xml. Continuing to process ADM registration intent.");
            handleRegistrationIntent(context, intent);
            return true;
        }
        AppboyLogger.m670w(TAG, "ADM not enabled in appboy.xml. Ignoring ADM registration intent. Note: you must set com_appboy_push_adm_messaging_registration_enabled to true in your appboy.xml to enable ADM.");
        return false;
    }
}

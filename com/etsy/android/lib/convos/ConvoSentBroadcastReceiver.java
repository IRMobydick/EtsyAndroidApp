package com.etsy.android.lib.convos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;

public class ConvoSentBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            CharSequence action = intent.getAction();
            if (!TextUtils.isEmpty(action)) {
                if ("com.etsy.android.convos.MESSAGE_SENT".equals(action)) {
                    messageSent(context, intent.getStringExtra("message_addressee"), intent.getLongExtra("convo_id", 0));
                } else if ("com.etsy.android.convos.MESSAGE_SENDING".equals(action)) {
                    messageSending(context, intent.getStringExtra("message_addressee"), intent.getLongExtra("convo_id", 0));
                } else if ("com.etsy.android.convos.MESSAGE_FAILED_TO_SEND".equals(action)) {
                    messageError(context, intent.getStringExtra(BaseMessage.TYPE_ERROR), intent.getStringExtra("message_addressee"));
                }
            }
        }
    }

    protected void messageError(Context context, String str, String str2) {
        ConvoNotificationsUtil.m1001a(context, context.getString(R.convo_failed_notifiction_msg), context.getString(R.convo_failed_notifiction_msg), context.getString(R.convo_failed_notification_msg_text, new Object[]{str2}), false);
    }

    protected void messageSent(Context context, String str, long j) {
        ConvoNotificationsUtil.m1000a(context);
    }

    protected void messageSending(Context context, String str, long j) {
        ConvoNotificationsUtil.m1001a(context, context.getString(R.convo_sending_notification_msg), context.getString(R.convo_sending_notification_msg), context.getString(R.convo_sending_notification_msg_text, new Object[]{str}), true);
    }
}

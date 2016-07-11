package com.etsy.android.messaging;

import android.content.Context;
import android.content.Intent;
import com.appboy.AppboyGcmReceiver;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.messaging.GcmBroadcastReceiver;
import com.etsy.android.lib.messaging.NotificationIntentDelegate;
import com.etsy.android.util.AppboyUtil;

public class EtsyGcmBroadcastReceiver extends GcmBroadcastReceiver {
    protected static final EtsyNotificationDelegate mIntentDelegate;

    static {
        mIntentDelegate = new EtsyNotificationDelegate();
    }

    protected NotificationIntentDelegate getIntentDelegate() {
        return mIntentDelegate;
    }

    public void onReceive(Context context, Intent intent) {
        if (!AppboyUtil.m5690a(intent)) {
            if (mIntentDelegate.m3463c() == null) {
                mIntentDelegate.m3461a(context.getString(R.app_name));
            }
            super.onReceive(context, intent);
        } else if (aj.m1101a().m1118d()) {
            new AppboyGcmReceiver().onReceive(context, intent);
        }
    }
}

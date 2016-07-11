package com.etsy.android.uikit.receiver;

import android.os.Bundle;
import android.text.TextUtils;
import com.appboy.Constants;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.messaging.NotificationIntentDelegate;
import com.etsy.android.lib.messaging.NotificationsUtil;
import com.google.android.gms.gcm.GcmListenerService;

public abstract class BaseGcmListenerService extends GcmListenerService {
    private static final String TAG;

    protected abstract NotificationIntentDelegate getIntentDelegate();

    static {
        TAG = EtsyDebug.m1891a(BaseGcmListenerService.class);
    }

    public void onMessageReceived(String str, Bundle bundle) {
        if (TextUtils.equals(str, "etsynotify/delete")) {
            NotificationsUtil.m2303a(bundle);
            return;
        }
        EtsyDebug.m1912c(TAG, "gcm onHandleIntent");
        EtsyDebug.m1906b(TAG, "Received: " + bundle.toString());
        if (bundle.get("alert") != null) {
            NotificationsUtil.m2301a(this, getIntentDelegate(), bundle, bundle.getString("alert"), bundle.getString(Constants.APPBOY_PUSH_PRIORITY_KEY), bundle.getString("o"));
        }
    }
}

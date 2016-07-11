package com.etsy.android.messaging;

import com.etsy.android.lib.messaging.NotificationIntentDelegate;
import com.etsy.android.uikit.receiver.BaseGcmListenerService;

public class EtsyGcmListenerService extends BaseGcmListenerService {
    protected EtsyNotificationDelegate mIntentDelegate;

    public EtsyGcmListenerService() {
        this.mIntentDelegate = new EtsyNotificationDelegate();
    }

    protected NotificationIntentDelegate getIntentDelegate() {
        return this.mIntentDelegate;
    }
}

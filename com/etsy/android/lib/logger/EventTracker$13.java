package com.etsy.android.lib.logger;

import com.etsy.android.lib.models.DeviceNotification;
import java.util.HashMap;

final class EventTracker$13 extends HashMap<String, Object> {
    final /* synthetic */ String val$landingPage;
    final /* synthetic */ String val$notificationId;
    final /* synthetic */ String val$notificationType;

    EventTracker$13(String str, String str2, String str3) {
        this.val$notificationType = str;
        this.val$notificationId = str2;
        this.val$landingPage = str3;
        put("notification_type", this.val$notificationType);
        put(DeviceNotification.FIELD_NOTIFICATION_ID, this.val$notificationId);
        put("landing_page_type", this.val$landingPage);
    }
}

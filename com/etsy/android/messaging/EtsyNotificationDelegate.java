package com.etsy.android.messaging;

import com.etsy.android.lib.R;
import com.etsy.android.lib.messaging.GcmBroadcastReceiver;
import com.etsy.android.lib.messaging.NotificationIntentDelegate;
import com.etsy.android.ui.nav.NotificationActivity;

/* renamed from: com.etsy.android.messaging.d */
public class EtsyNotificationDelegate implements NotificationIntentDelegate {
    protected static String f2083a;

    static {
        f2083a = null;
    }

    public Class<?> m3460a() {
        return NotificationActivity.class;
    }

    public Class<?> m3462b() {
        return GcmBroadcastReceiver.class;
    }

    public String m3463c() {
        return f2083a;
    }

    public void m3461a(String str) {
        f2083a = str;
    }

    public int m3464d() {
        return 2130837953;
    }

    public int m3465e() {
        return R.brand_orange;
    }
}

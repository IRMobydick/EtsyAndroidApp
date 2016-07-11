package com.etsy.android.lib.ui.nav;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.messaging.EtsyRoute;
import java.util.Map;

public abstract class BaseNotificationActivity extends FragmentActivity {
    public static final String EXTERNAL_SOURCE_TYPE = "external";
    private static final String TAG;
    private Map<String, String> mSourceMatchers;

    protected abstract void trackIntent(Intent intent, String str, EtsyRoute etsyRoute);

    static {
        TAG = EtsyDebug.m1891a(BaseNotificationActivity.class);
    }

    protected BaseNotificationActivity(Map<String, String> map) {
        this.mSourceMatchers = map;
    }

    protected String determineSourceType(String str, String str2) {
        if (this.mSourceMatchers.containsKey(str)) {
            return (String) this.mSourceMatchers.get(str);
        }
        return str2;
    }
}

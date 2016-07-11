package com.etsy.android.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.etsy.android.contentproviders.EtsyDatabaseUtil;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.convos.ConvoHelper;
import com.etsy.android.lib.core.an;
import com.etsy.android.lib.messaging.EtsyGooglePlayServicesUtil;
import com.etsy.android.lib.messaging.NotificationsUtil;
import com.etsy.android.lib.util.AttributionUtil;
import com.etsy.android.lib.util.ak;
import com.etsy.android.lib.util.bg;

/* renamed from: com.etsy.android.util.f */
public class SessionManager implements an {
    static boolean f4306a;

    static {
        f4306a = true;
    }

    public void m5724a(Context context, boolean z) {
        EtsyDatabaseUtil.m753e(context);
        ConvoHelper.m957a(context);
        m5722b(context, z);
        NotificationsUtil.m2300a(context);
    }

    private void m5722b(Context context, boolean z) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        launchIntentForPackage.putExtra("HOME_RESET", true);
        launchIntentForPackage.putExtra("FORCED_SIGNOUT", z);
        launchIntentForPackage.addFlags(67108864);
        if (f4306a) {
            launchIntentForPackage.setPackage(null);
        }
        context.startActivity(launchIntentForPackage);
    }

    public static boolean m5721a(Intent intent) {
        return "android.intent.action.MAIN".equals(intent.getAction()) && intent.getCategories() != null && intent.getCategories().contains("android.intent.category.LAUNCHER");
    }

    public static void m5720a(Intent intent, Activity activity) {
        SessionManager.m5723b(intent);
        SessionManager.m5719a(activity);
    }

    private static void m5723b(Intent intent) {
        if (SessionManager.m5721a(intent)) {
            f4306a = intent.getPackage() == null;
        }
    }

    private static void m5719a(Activity activity) {
        bg.m3328a(false);
        EtsyConfig.m837a().m867c(activity.getApplicationContext());
        EtsyGooglePlayServicesUtil.m2256a(activity);
        ak.m3246b(activity);
        AppboyUtil.m5685a((Context) activity);
        AttributionUtil.m3383a(activity);
    }
}

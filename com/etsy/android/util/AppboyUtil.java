package com.etsy.android.util;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.appboy.Appboy;
import com.appboy.Constants;
import com.appboy.push.AppboyNotificationUtils;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.ao;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.util.ag;
import com.etsy.android.lib.util.bh;
import com.etsy.android.messaging.EtsyAppboyNotificationFactory;
import com.etsy.android.uikit.util.AdHocEventCompatBuilder;
import com.etsy.android.util.a.1;
import com.etsy.android.util.a.2;
import com.etsy.android.util.a.3;
import com.etsy.android.util.a.4;
import com.etsy.android.util.a.5;
import com.etsy.android.util.a.6;
import com.etsy.android.util.a.7;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.util.a */
public class AppboyUtil {
    private static final String f4301a;
    private static boolean f4302b;
    private static final ExecutorService f4303c;
    private static final BroadcastReceiver f4304d;

    static {
        f4301a = EtsyDebug.m1891a(AppboyUtil.class);
        f4302b = false;
        f4303c = Executors.newSingleThreadExecutor(new ao(10));
        f4304d = new 1();
    }

    private static boolean m5699d() {
        return !f4302b && (EtsyBuildHelper.m5709d() || EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.bs));
    }

    public static void m5685a(Context context) {
        if (AppboyUtil.m5699d() && aj.m1101a().m1118d()) {
            Context applicationContext = context.getApplicationContext();
            String d = ag.m3219d(applicationContext);
            if (bh.m3340a(d)) {
                f4303c.execute(new 2(applicationContext, d));
                AppboyUtil.m5700e();
                return;
            }
            AppboyUtil.m5702f(applicationContext);
        }
    }

    public static void m5694b(Context context) {
        if (AppboyUtil.m5699d()) {
            f4303c.execute(new 3(context.getApplicationContext()));
        }
    }

    public static void m5684a(Activity activity) {
        if (AppboyUtil.m5699d()) {
            f4303c.execute(new 4(activity));
        }
    }

    public static void m5693b(Activity activity) {
        if (AppboyUtil.m5699d()) {
            f4303c.execute(new 5(activity));
        }
    }

    public static boolean m5690a(Intent intent) {
        if (!AppboyUtil.m5699d() || intent == null) {
            return false;
        }
        return AppboyNotificationUtils.isAppboyPushMessage(intent);
    }

    public static Bundle m5691b(Intent intent) {
        Bundle bundleExtra = intent.getBundleExtra(Constants.APPBOY_PUSH_EXTRAS_KEY);
        return bundleExtra == null ? new Bundle() : bundleExtra;
    }

    public static void m5683a() {
        if (AppboyUtil.m5699d()) {
            Appboy.setCustomAppboyNotificationFactory(new EtsyAppboyNotificationFactory());
        }
    }

    public static void m5686a(Context context, String str) {
        if (AppboyUtil.m5699d()) {
            Appboy.getInstance(context).logCustomEvent(str);
        }
    }

    public static void m5692b() {
        f4302b = true;
    }

    private static void m5701e(Context context) {
        String n = aj.m1101a().m1127n();
        if (bh.m3343b(n)) {
            if (!n.equals(Appboy.getInstance(context).getCurrentUser().getUserId())) {
                EtsyDebug.m1912c(f4301a, "User ID has changed, update Appboy");
                Appboy.getInstance(context).changeUser(n);
            }
            AppboyUtil.m5695b(context, true);
        }
    }

    private static void m5695b(Context context, boolean z) {
        if (AppboyUtil.m5699d()) {
            f4303c.execute(new 6(context, z));
        }
    }

    private static void m5702f(Context context) {
        LocalBroadcastManager.getInstance(context).registerReceiver(f4304d, new IntentFilter("com.etsy.android.lib.util.GCM_REGISTERED"));
    }

    private static void m5703g(Context context) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(f4304d);
    }

    private static void m5700e() {
        aj.m1101a().m1112a(new 7());
    }

    public static void m5688a(Intent intent, Bundle bundle) {
        AdHocEventCompatBuilder a = new AdHocEventCompatBuilder("appboy_notification_open").m5515a("your_notifications");
        AppboyUtil.m5689a(a, intent, bundle);
        a.m5517a();
    }

    private static void m5689a(AdHocEventCompatBuilder adHocEventCompatBuilder, Intent intent, Bundle bundle) {
        Object stringExtra = intent.getStringExtra(Constants.APPBOY_PUSH_DEEP_LINK_KEY);
        if (TextUtils.isEmpty(stringExtra)) {
            stringExtra = "etsy://default-push-landing";
        }
        String stringExtra2 = intent.getStringExtra(Constants.APPBOY_PUSH_TITLE_KEY);
        String stringExtra3 = intent.getStringExtra(Constants.APPBOY_PUSH_CONTENT_KEY);
        Object a = AppboyUtil.m5682a(stringExtra, bundle.getString("utm_source", "appboy"), bundle.getString("utm_medium", "push"), bundle.getString("utm_campaign", "default_campaign"));
        adHocEventCompatBuilder.m5516a(new 8(stringExtra, stringExtra2, stringExtra3, a));
        adHocEventCompatBuilder.m5512a(AnalyticsLogAttribute.AB_URI, stringExtra).m5512a(AnalyticsLogAttribute.AB_TITLE, stringExtra2 != null ? stringExtra2 : StringUtils.EMPTY).m5512a(AnalyticsLogAttribute.AB_CONTENT, stringExtra3 != null ? stringExtra3 : StringUtils.EMPTY).m5512a(AnalyticsLogAttribute.LOC, a);
    }

    private static String m5682a(String str, String str2, String str3, String str4) {
        Builder buildUpon = Uri.parse(str).buildUpon();
        buildUpon.appendQueryParameter("utm_source", str2);
        buildUpon.appendQueryParameter("utm_medium", str3);
        buildUpon.appendQueryParameter("utm_campaign", str4);
        return buildUpon.build().toString();
    }
}

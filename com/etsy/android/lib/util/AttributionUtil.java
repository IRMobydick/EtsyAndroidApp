package com.etsy.android.lib.util;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.yozio.android.Yozio;
import com.yozio.android.YozioReferrerReceiver;

/* renamed from: com.etsy.android.lib.util.d */
public class AttributionUtil {
    private static Context f2034a;
    private static Intent f2035b;
    private static boolean f2036c;
    private static BroadcastReceiver f2037d;

    /* renamed from: com.etsy.android.lib.util.d.1 */
    final class AttributionUtil extends BroadcastReceiver {
        AttributionUtil() {
        }

        public void onReceive(Context context, Intent intent) {
            AttributionUtil.m3388c();
            AttributionUtil.m3387b(context);
            LocalBroadcastManager.getInstance(context).unregisterReceiver(AttributionUtil.f2037d);
            AttributionUtil.f2037d = null;
        }
    }

    static {
        f2036c = false;
    }

    public static void m3383a(Activity activity) {
        if (!f2036c) {
            f2034a = activity;
            if (EtsyConfig.m837a().m871e()) {
                AttributionUtil.m3388c();
                AttributionUtil.m3387b(activity);
                return;
            }
            f2037d = new AttributionUtil();
            LocalBroadcastManager.getInstance(activity).registerReceiver(f2037d, new IntentFilter("com.etsy.etsyconfig.updated"));
        }
    }

    private static void m3388c() {
        if (!f2036c && f2034a != null) {
            if (EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.bq)) {
                Yozio.m7486a(f2034a);
                f2036c = true;
            }
            f2034a = null;
        }
    }

    private static boolean m3387b(Context context) {
        if (!f2036c || f2035b == null) {
            return false;
        }
        new YozioReferrerReceiver().onReceive(context, f2035b);
        f2035b = null;
        return true;
    }

    public static void m3384a(Context context, Intent intent) {
        f2035b = intent;
        if (f2036c) {
            AttributionUtil.m3387b(context);
        }
    }
}

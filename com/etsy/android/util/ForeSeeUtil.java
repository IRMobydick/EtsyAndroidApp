package com.etsy.android.util;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Handler;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.config.InstallInfo;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.CrashUtil;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.messaging.ForeSeeBroadcastReceiver;
import com.etsy.android.util.d.1;
import com.foresee.sdk.ForeSee;
import com.foresee.sdk.events.LifecycleEvent;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.util.d */
public class ForeSeeUtil {
    private static final String f4305a;

    static {
        f4305a = EtsyDebug.m1891a(ForeSeeUtil.class);
    }

    public static boolean m5713a(Application application) {
        Throwable th;
        boolean z;
        if (!ForeSeeUtil.m5716b()) {
            return false;
        }
        try {
            CrashUtil.m3037a().m3044a(f4305a, "init ForeSee framework");
            boolean start = ForeSee.start(application);
            try {
                if (EtsyDebug.m1903a()) {
                    ForeSee.setSkipPoolingCheck(true);
                    EtsyDebug.m1912c(f4305a, "modal 1 id = 2130903184");
                    EtsyDebug.m1912c(f4305a, "modal 2 id = 2130903185");
                    EtsyDebug.m1912c(f4305a, "edit text id = 2131755605");
                }
                ForeSee.setLayoutIds(2130903184, 2130903185, 2131755612, 2131755602, 2131755603, 2131755605, 2131755606, 2131755607, 2131755608);
                ForeSeeUtil.m5715b(application);
                EtsyLogger.m1966a().m1996b(f4305a, "successful init of framework");
                return start;
            } catch (Throwable th2) {
                th = th2;
                z = start;
                EtsyDebug.m1900a(f4305a, th);
                CrashUtil.m3037a().m3045a(th);
                return z;
            }
        } catch (Throwable th22) {
            th = th22;
            z = false;
            EtsyDebug.m1900a(f4305a, th);
            CrashUtil.m3037a().m3045a(th);
            return z;
        }
    }

    public static ForeSeeBroadcastReceiver m5711a(Activity activity) {
        if (!ForeSeeUtil.m5716b()) {
            return null;
        }
        BroadcastReceiver foreSeeBroadcastReceiver = new ForeSeeBroadcastReceiver();
        activity.getApplicationContext().registerReceiver(foreSeeBroadcastReceiver, new IntentFilter(LifecycleEvent.ACTION));
        return foreSeeBroadcastReceiver;
    }

    public static void m5712a() {
        if (ForeSeeUtil.m5716b()) {
            try {
                CrashUtil.m3037a().m3044a(f4305a, "trigger ForeSee modal");
                new Handler().postDelayed(new 1(), 1000);
                EtsyLogger.m1966a().m1996b(f4305a, "triggered survey invite modal");
                EtsyDebug.m1912c(f4305a, "triggering survey invite");
            } catch (Throwable th) {
                EtsyDebug.m1900a(f4305a, th);
                CrashUtil.m3037a().m3045a(th);
            }
        }
    }

    private static void m5715b(Application application) {
        ForeSee.removeCPPValue(ResponseConstants.IS_SELLER);
        ForeSee.removeCPPValue(ResponseConstants.USER_ID);
        ForeSee.removeCPPValue("device_uuid");
        CrashUtil.m3037a().m3044a(f4305a, "pass custom params to ForeSee");
        ForeSee.getConfiguration().addCpp(ResponseConstants.IS_SELLER, SharedPreferencesUtility.m3152k(application.getApplicationContext()).getId().equals(StringUtils.EMPTY) ? "0" : "1");
        ForeSee.getConfiguration().addCpp(ResponseConstants.USER_ID, aj.m1101a().m1126m().toString());
        ForeSee.getConfiguration().addCpp("device_uuid", InstallInfo.m919a().m925b());
    }

    private static boolean m5716b() {
        return Locale.getDefault().getLanguage().contains("en") && EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.br);
    }

    public static void m5714b(Activity activity) {
        if (ForeSeeUtil.m5716b()) {
            ForeSee.activityStarted(activity);
        }
    }

    public static void m5717c(Activity activity) {
        if (ForeSeeUtil.m5716b()) {
            ForeSee.activityPaused(activity);
        }
    }

    public static void m5718d(Activity activity) {
        if (ForeSeeUtil.m5716b()) {
            ForeSee.activityResumed(activity);
        }
    }
}

package com.etsy.android.lib.util;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.etsy.android.lib.config.InstallInfo;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import org.apache.commons.lang3.StringUtils;

/* compiled from: DeviceUtil */
public class aa {
    private static final String f1991a;

    static {
        f1991a = EtsyDebug.m1891a(aa.class);
    }

    public static String m3166a(Context context) {
        String str = StringUtils.EMPTY;
        ab abVar = new ab(context);
        return ((((((str + "\n Etsy Version: " + InstallInfo.m919a().m929f() + " (" + InstallInfo.m919a().m936m() + ")") + "\n Android Version: " + System.getProperty("os.version") + "(" + VERSION.INCREMENTAL + ")") + "\n Android API Level: " + VERSION.SDK_INT) + "\n Device: " + Build.DEVICE) + "\n Model (and Product): " + Build.MODEL + " (" + Build.PRODUCT + ")") + "\n Brand: " + Build.BRAND) + "\n Resolution: (w) " + abVar.m3180c() + EtsyDialogFragment.OPT_X_BUTTON + abVar.m3181d() + " (h)";
    }

    public static boolean m3167a() {
        return VERSION.SDK_INT >= 16;
    }

    public static boolean m3168b() {
        return VERSION.SDK_INT >= 17;
    }

    public static boolean m3169c() {
        return VERSION.SDK_INT >= 19;
    }

    public static boolean m3170d() {
        return VERSION.SDK_INT >= 21;
    }
}

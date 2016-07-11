package com.etsy.android.lib.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Process;
import com.etsy.android.lib.config.InstallInfo;
import com.etsy.android.lib.logger.EtsyDebug;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* compiled from: RuntimeUtil */
public class az {
    private static final String f2011a;

    static {
        f2011a = EtsyDebug.m1891a(az.class);
    }

    public static String m3292a(Context context) {
        String str = StringUtils.EMPTY;
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            int myPid = Process.myPid();
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                String str2;
                try {
                    if (runningAppProcessInfo.pid == myPid) {
                        str2 = runningAppProcessInfo.processName;
                        str = str2;
                    }
                } catch (Throwable e) {
                    EtsyDebug.m1917d(f2011a, "getProcessName Exception: " + e.getMessage(), e);
                }
                str2 = str;
                str = str2;
            }
        }
        return str;
    }

    public static boolean m3293b(Context context) {
        return InstallInfo.m919a().m930g().equals(m3292a(context));
    }

    public static long m3291a() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }
}

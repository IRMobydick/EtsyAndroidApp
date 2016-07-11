package com.appboy.support;

import android.content.Context;
import com.appboy.Constants;

public class PermissionUtils {
    private static final String f1043a;

    static {
        f1043a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, PermissionUtils.class.getName()});
    }

    public static boolean hasPermission(Context context, String str) {
        try {
            return context.checkCallingOrSelfPermission(str) == 0;
        } catch (Throwable th) {
            AppboyLogger.m665e(f1043a, String.format("Failure checking permission %s", new Object[]{str}), th);
            return false;
        }
    }
}

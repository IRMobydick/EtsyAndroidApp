package com.appboy.support;

import android.util.Log;

public class AppboyLogger {
    public static int LogLevel = 0;
    public static final int SUPPRESS = Integer.MAX_VALUE;

    static {
        LogLevel = 2;
    }

    public static int m668v(String str, String str2) {
        if (LogLevel <= 2) {
            return Log.v(str, str2);
        }
        return 0;
    }

    public static int m669v(String str, String str2, Throwable th) {
        if (LogLevel <= 2) {
            return Log.v(str, str2, th);
        }
        return 0;
    }

    public static int m662d(String str, String str2) {
        if (LogLevel <= 3) {
            return Log.d(str, str2);
        }
        return 0;
    }

    public static int m663d(String str, String str2, Throwable th) {
        if (LogLevel <= 3) {
            return Log.d(str, str2, th);
        }
        return 0;
    }

    public static int m666i(String str, String str2) {
        if (LogLevel <= 4) {
            return Log.i(str, str2);
        }
        return 0;
    }

    public static int m667i(String str, String str2, Throwable th) {
        if (LogLevel <= 4) {
            return Log.i(str, str2, th);
        }
        return 0;
    }

    public static int m670w(String str, String str2) {
        if (LogLevel <= 5) {
            return Log.w(str, str2);
        }
        return 0;
    }

    public static int m671w(String str, String str2, Throwable th) {
        if (LogLevel <= 5) {
            return Log.w(str, str2, th);
        }
        return 0;
    }

    public static int m672w(String str, Throwable th) {
        if (LogLevel <= 5) {
            return Log.w(str, th);
        }
        return 0;
    }

    public static int m664e(String str, String str2) {
        if (LogLevel <= 6) {
            return Log.e(str, str2);
        }
        return 0;
    }

    public static int m665e(String str, String str2, Throwable th) {
        if (LogLevel <= 6) {
            return Log.e(str, str2, th);
        }
        return 0;
    }
}

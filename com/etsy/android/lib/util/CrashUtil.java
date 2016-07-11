package com.etsy.android.lib.util;

import com.etsy.android.lib.logger.EventTracker;
import java.lang.Thread.UncaughtExceptionHandler;

/* renamed from: com.etsy.android.lib.util.v */
class CrashUtil implements Runnable {
    CrashProvider f2065a;
    Runnable f2066b;

    CrashUtil(CrashProvider crashProvider, Runnable runnable) {
        this.f2065a = crashProvider;
        this.f2066b = runnable;
    }

    public void run() {
        CrashUtil a = m3037a();
        synchronized (CrashUtil.f1965g) {
            if (!a.m3046a(this.f2065a)) {
                Thread.setDefaultUncaughtExceptionHandler(null);
                try {
                    this.f2066b.run();
                    UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
                    if (!(defaultUncaughtExceptionHandler == a || a.f1968d.containsValue(defaultUncaughtExceptionHandler))) {
                        a.f1968d.put(this.f2065a, defaultUncaughtExceptionHandler);
                    }
                } catch (Throwable th) {
                    EventTracker.m2024a(CrashUtil.class.getSimpleName(), th);
                }
                Thread.setDefaultUncaughtExceptionHandler(a);
            }
        }
    }
}

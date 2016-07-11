package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@jw
public final class ls {
    private static final ExecutorService f5421a;
    private static final ExecutorService f5422b;

    static {
        f5421a = Executors.newFixedThreadPool(10, m7073a("Default"));
        f5422b = Executors.newFixedThreadPool(5, m7073a("Loader"));
    }

    public static mz<Void> m7069a(int i, Runnable runnable) {
        return i == 1 ? m7072a(f5422b, new 1(runnable)) : m7072a(f5421a, new 2(runnable));
    }

    public static mz<Void> m7070a(Runnable runnable) {
        return m7069a(0, runnable);
    }

    public static <T> mz<T> m7071a(Callable<T> callable) {
        return m7072a(f5421a, (Callable) callable);
    }

    public static <T> mz<T> m7072a(ExecutorService executorService, Callable<T> callable) {
        mv mvVar = new mv();
        try {
            mvVar.m7201b(new 4(mvVar, executorService.submit(new 3(mvVar, callable))));
        } catch (Throwable e) {
            C1129c.m6193d("Thread execution is rejected.", e);
            mvVar.cancel(true);
        }
        return mvVar;
    }

    private static ThreadFactory m7073a(String str) {
        return new 5(str);
    }
}

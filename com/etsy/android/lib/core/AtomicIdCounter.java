package com.etsy.android.lib.core;

import java.util.concurrent.atomic.AtomicLong;

/* renamed from: com.etsy.android.lib.core.a */
public class AtomicIdCounter {
    private static AtomicLong f1408a;

    static {
        f1408a = new AtomicLong(0);
    }

    public static long m1072a() {
        return f1408a.incrementAndGet();
    }
}

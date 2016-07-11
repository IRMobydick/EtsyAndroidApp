package com.etsy.android.lib.core;

import java.util.concurrent.ThreadFactory;

/* compiled from: ThreadPriorityFactory */
public class ao implements ThreadFactory {
    private final int f1446a;

    public ao(int i) {
        this.f1446a = i;
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setPriority(this.f1446a);
        return thread;
    }
}

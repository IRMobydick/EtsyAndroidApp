package com.google.android.gms.internal;

import java.util.concurrent.TimeUnit;

@jw
public class mw<T> implements mz<T> {
    private final T f5468a;
    private final na f5469b;

    public mw(T t) {
        this.f5468a = t;
        this.f5469b = new na();
        this.f5469b.m7209a();
    }

    public void m7202a(Runnable runnable) {
        this.f5469b.m7210a(runnable);
    }

    public boolean cancel(boolean z) {
        return false;
    }

    public T get() {
        return this.f5468a;
    }

    public T get(long j, TimeUnit timeUnit) {
        return this.f5468a;
    }

    public boolean isCancelled() {
        return false;
    }

    public boolean isDone() {
        return true;
    }
}

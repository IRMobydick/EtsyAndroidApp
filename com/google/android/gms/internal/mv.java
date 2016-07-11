package com.google.android.gms.internal;

import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@jw
public class mv<T> implements mz<T> {
    private final Object f5463a;
    private T f5464b;
    private boolean f5465c;
    private boolean f5466d;
    private final na f5467e;

    public mv() {
        this.f5463a = new Object();
        this.f5464b = null;
        this.f5465c = false;
        this.f5466d = false;
        this.f5467e = new na();
    }

    public void m7199a(Runnable runnable) {
        this.f5467e.m7210a(runnable);
    }

    public void m7200b(T t) {
        synchronized (this.f5463a) {
            if (this.f5466d) {
            } else if (this.f5465c) {
                throw new IllegalStateException("Provided CallbackFuture with multiple values.");
            } else {
                this.f5465c = true;
                this.f5464b = t;
                this.f5463a.notifyAll();
                this.f5467e.m7209a();
            }
        }
    }

    public void m7201b(Runnable runnable) {
        this.f5467e.m7211b(runnable);
    }

    public boolean cancel(boolean z) {
        if (!z) {
            return false;
        }
        synchronized (this.f5463a) {
            if (this.f5465c) {
                return false;
            }
            this.f5466d = true;
            this.f5465c = true;
            this.f5463a.notifyAll();
            this.f5467e.m7209a();
            return true;
        }
    }

    public T get() {
        T t;
        synchronized (this.f5463a) {
            if (!this.f5465c) {
                try {
                    this.f5463a.wait();
                } catch (InterruptedException e) {
                }
            }
            if (this.f5466d) {
                throw new CancellationException("CallbackFuture was cancelled.");
            }
            t = this.f5464b;
        }
        return t;
    }

    public T get(long j, TimeUnit timeUnit) {
        T t;
        synchronized (this.f5463a) {
            if (!this.f5465c) {
                try {
                    long toMillis = timeUnit.toMillis(j);
                    if (toMillis != 0) {
                        this.f5463a.wait(toMillis);
                    }
                } catch (InterruptedException e) {
                }
            }
            if (!this.f5465c) {
                throw new TimeoutException("CallbackFuture timed out.");
            } else if (this.f5466d) {
                throw new CancellationException("CallbackFuture was cancelled.");
            } else {
                t = this.f5464b;
            }
        }
        return t;
    }

    public boolean isCancelled() {
        boolean z;
        synchronized (this.f5463a) {
            z = this.f5466d;
        }
        return z;
    }

    public boolean isDone() {
        boolean z;
        synchronized (this.f5463a) {
            z = this.f5465c;
        }
        return z;
    }
}

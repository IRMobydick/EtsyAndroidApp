package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.C1101o;

@jw
public class mr {
    private long f5452a;
    private long f5453b;
    private Object f5454c;

    public mr(long j) {
        this.f5453b = Long.MIN_VALUE;
        this.f5454c = new Object();
        this.f5452a = j;
    }

    public boolean m7188a() {
        boolean z;
        synchronized (this.f5454c) {
            long elapsedRealtime = C1101o.m6045i().elapsedRealtime();
            if (this.f5453b + this.f5452a > elapsedRealtime) {
                z = false;
            } else {
                this.f5453b = elapsedRealtime;
                z = true;
            }
        }
        return z;
    }
}

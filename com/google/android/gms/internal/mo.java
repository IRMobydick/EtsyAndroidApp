package com.google.android.gms.internal;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.google.android.gms.common.internal.zzaa;

@jw
public class mo {
    private HandlerThread f5444a;
    private Handler f5445b;
    private int f5446c;
    private final Object f5447d;

    public mo() {
        this.f5444a = null;
        this.f5445b = null;
        this.f5446c = 0;
        this.f5447d = new Object();
    }

    public Looper m7183a() {
        Looper looper;
        synchronized (this.f5447d) {
            if (this.f5446c != 0) {
                zzaa.zzb(this.f5444a, (Object) "Invalid state: mHandlerThread should already been initialized.");
            } else if (this.f5444a == null) {
                lo.m7056e("Starting the looper thread.");
                this.f5444a = new HandlerThread("LooperProvider");
                this.f5444a.start();
                this.f5445b = new Handler(this.f5444a.getLooper());
                lo.m7056e("Looper thread started.");
            } else {
                lo.m7056e("Resuming the looper thread");
                this.f5447d.notifyAll();
            }
            this.f5446c++;
            looper = this.f5444a.getLooper();
        }
        return looper;
    }

    public void m7184b() {
        synchronized (this.f5447d) {
            zzaa.zzb(this.f5446c > 0, (Object) "Invalid state: release() called more times than expected.");
            int i = this.f5446c - 1;
            this.f5446c = i;
            if (i == 0) {
                this.f5445b.post(new 1(this));
            }
        }
    }
}

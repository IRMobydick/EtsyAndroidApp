package com.google.android.gms.internal;

import java.util.concurrent.Future;

@jw
public abstract class ln implements me<Future> {
    private volatile Thread zzQc;
    private boolean zzQd;
    private final Runnable zzw;

    public ln() {
        this.zzw = new 1(this);
        this.zzQd = false;
    }

    public ln(boolean z) {
        this.zzw = new 1(this);
        this.zzQd = z;
    }

    public final void cancel() {
        onStop();
        if (this.zzQc != null) {
            this.zzQc.interrupt();
        }
    }

    public abstract void onStop();

    public abstract void zzbQ();

    public /* synthetic */ Object zzhs() {
        return zziU();
    }

    public final Future zziU() {
        return this.zzQd ? ls.m7069a(1, this.zzw) : ls.m7070a(this.zzw);
    }
}

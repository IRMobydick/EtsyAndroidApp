package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.C1128a;
import java.util.ArrayList;
import java.util.List;

@jw
class na {
    private final Object f5470a;
    private final List<Runnable> f5471b;
    private final List<Runnable> f5472c;
    private boolean f5473d;

    public na() {
        this.f5470a = new Object();
        this.f5471b = new ArrayList();
        this.f5472c = new ArrayList();
        this.f5473d = false;
    }

    private void m7207c(Runnable runnable) {
        ls.m7070a(runnable);
    }

    private void m7208d(Runnable runnable) {
        C1128a.f4666a.post(runnable);
    }

    public void m7209a() {
        synchronized (this.f5470a) {
            if (this.f5473d) {
                return;
            }
            for (Runnable c : this.f5471b) {
                m7207c(c);
            }
            for (Runnable c2 : this.f5472c) {
                m7208d(c2);
            }
            this.f5471b.clear();
            this.f5472c.clear();
            this.f5473d = true;
        }
    }

    public void m7210a(Runnable runnable) {
        synchronized (this.f5470a) {
            if (this.f5473d) {
                m7207c(runnable);
            } else {
                this.f5471b.add(runnable);
            }
        }
    }

    public void m7211b(Runnable runnable) {
        synchronized (this.f5470a) {
            if (this.f5473d) {
                m7208d(runnable);
            } else {
                this.f5472c.add(runnable);
            }
        }
    }
}

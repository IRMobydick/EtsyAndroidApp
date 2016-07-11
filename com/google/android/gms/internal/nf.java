package com.google.android.gms.internal;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@jw
public class nf<T> implements nb<T> {
    protected int f5474a;
    protected final BlockingQueue<ng> f5475b;
    protected T f5476c;
    private final Object f5477d;

    public nf() {
        this.f5477d = new Object();
        this.f5474a = 0;
        this.f5475b = new LinkedBlockingQueue();
    }

    public void m7212a() {
        synchronized (this.f5477d) {
            if (this.f5474a != 0) {
                throw new UnsupportedOperationException();
            }
            this.f5474a = -1;
            for (ng ngVar : this.f5475b) {
                ngVar.b.a();
            }
            this.f5475b.clear();
        }
    }

    public void m7213a(ne<T> neVar, nc ncVar) {
        synchronized (this.f5477d) {
            if (this.f5474a == 1) {
                neVar.a(this.f5476c);
            } else if (this.f5474a == -1) {
                ncVar.a();
            } else if (this.f5474a == 0) {
                this.f5475b.add(new ng(this, neVar, ncVar));
            }
        }
    }

    public void m7214a(T t) {
        synchronized (this.f5477d) {
            if (this.f5474a != 0) {
                throw new UnsupportedOperationException();
            }
            this.f5476c = t;
            this.f5474a = 1;
            for (ng ngVar : this.f5475b) {
                ngVar.a.a(t);
            }
            this.f5475b.clear();
        }
    }

    public int m7215b() {
        return this.f5474a;
    }
}

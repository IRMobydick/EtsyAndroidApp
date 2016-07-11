package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.a;
import com.google.android.gms.internal.jw;

@jw
/* renamed from: com.google.android.gms.ads.internal.client.s */
public class C1090s extends a {
    private final Object f4471a;
    private a f4472b;

    public C1090s() {
        this.f4471a = new Object();
    }

    public void m5955a() {
        synchronized (this.f4471a) {
            if (this.f4472b != null) {
                this.f4472b.a();
            }
        }
    }

    public void m5956a(int i) {
        synchronized (this.f4471a) {
            if (this.f4472b != null) {
                this.f4472b.a(i);
            }
        }
    }

    public void m5957a(a aVar) {
        synchronized (this.f4471a) {
            this.f4472b = aVar;
        }
    }

    public void m5958b() {
        synchronized (this.f4471a) {
            if (this.f4472b != null) {
                this.f4472b.b();
            }
        }
    }

    public void m5959c() {
        synchronized (this.f4471a) {
            if (this.f4472b != null) {
                this.f4472b.c();
            }
        }
    }

    public void m5960d() {
        synchronized (this.f4471a) {
            if (this.f4472b != null) {
                this.f4472b.d();
            }
        }
    }
}

package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.ads.internal.C1101o;

@jw
public class ll {
    private final Object f5414a;
    private int f5415b;
    private int f5416c;
    private final li f5417d;
    private final String f5418e;

    ll(li liVar, String str) {
        this.f5414a = new Object();
        this.f5417d = liVar;
        this.f5418e = str;
    }

    public ll(String str) {
        this(C1101o.m6044h(), str);
    }

    public Bundle m7049a() {
        Bundle bundle;
        synchronized (this.f5414a) {
            bundle = new Bundle();
            bundle.putInt("pmnli", this.f5415b);
            bundle.putInt("pmnll", this.f5416c);
        }
        return bundle;
    }

    public void m7050a(int i, int i2) {
        synchronized (this.f5414a) {
            this.f5415b = i;
            this.f5416c = i2;
            this.f5417d.m7019a(this.f5418e, this);
        }
    }
}

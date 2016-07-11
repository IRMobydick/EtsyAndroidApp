package com.google.android.gms.internal;

import java.util.concurrent.Future;

@jw
public final class jz {
    ha f5221a;
    public final fk f5222b;
    public final fk f5223c;
    public final fk f5224d;
    private final Object f5225e;
    private String f5226f;
    private String f5227g;
    private mv<kc> f5228h;

    public jz(String str, String str2) {
        this.f5225e = new Object();
        this.f5228h = new mv();
        this.f5222b = new 1(this);
        this.f5223c = new 2(this);
        this.f5224d = new 3(this);
        this.f5227g = str2;
        this.f5226f = str;
    }

    public ha m6907a() {
        return this.f5221a;
    }

    public void m6908a(ha haVar) {
        this.f5221a = haVar;
    }

    public Future<kc> m6909b() {
        return this.f5228h;
    }

    public void m6910c() {
    }
}

package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.search.b;
import com.google.android.gms.internal.jw;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@jw
/* renamed from: com.google.android.gms.ads.internal.client.e */
public final class C1079e {
    public static final String f4385a;
    private final Date f4386b;
    private final String f4387c;
    private final int f4388d;
    private final Set<String> f4389e;
    private final Location f4390f;
    private final boolean f4391g;
    private final Bundle f4392h;
    private final Map<Class<? extends Object>, Object> f4393i;
    private final String f4394j;
    private final String f4395k;
    private final b f4396l;
    private final int f4397m;
    private final Set<String> f4398n;
    private final Bundle f4399o;
    private final Set<String> f4400p;
    private final boolean f4401q;

    static {
        f4385a = C1089r.m5951a().m6169a("emulator");
    }

    public C1079e(f fVar) {
        this(fVar, null);
    }

    public C1079e(f fVar, b bVar) {
        this.f4386b = f.a(fVar);
        this.f4387c = f.b(fVar);
        this.f4388d = f.c(fVar);
        this.f4389e = Collections.unmodifiableSet(f.d(fVar));
        this.f4390f = f.e(fVar);
        this.f4391g = f.f(fVar);
        this.f4392h = f.g(fVar);
        this.f4393i = Collections.unmodifiableMap(f.h(fVar));
        this.f4394j = f.i(fVar);
        this.f4395k = f.j(fVar);
        this.f4396l = bVar;
        this.f4397m = f.k(fVar);
        this.f4398n = Collections.unmodifiableSet(f.l(fVar));
        this.f4399o = f.m(fVar);
        this.f4400p = Collections.unmodifiableSet(f.n(fVar));
        this.f4401q = f.o(fVar);
    }

    public Bundle m5857a(Class<? extends com.google.android.gms.ads.mediation.b> cls) {
        return this.f4392h.getBundle(cls.getName());
    }

    public Date m5858a() {
        return this.f4386b;
    }

    public boolean m5859a(Context context) {
        return this.f4398n.contains(C1089r.m5951a().m6168a(context));
    }

    public String m5860b() {
        return this.f4387c;
    }

    public int m5861c() {
        return this.f4388d;
    }

    public Set<String> m5862d() {
        return this.f4389e;
    }

    public Location m5863e() {
        return this.f4390f;
    }

    public boolean m5864f() {
        return this.f4391g;
    }

    public String m5865g() {
        return this.f4394j;
    }

    public String m5866h() {
        return this.f4395k;
    }

    public b m5867i() {
        return this.f4396l;
    }

    public Map<Class<? extends Object>, Object> m5868j() {
        return this.f4393i;
    }

    public Bundle m5869k() {
        return this.f4392h;
    }

    public int m5870l() {
        return this.f4397m;
    }

    public Bundle m5871m() {
        return this.f4399o;
    }

    public Set<String> m5872n() {
        return this.f4400p;
    }

    public boolean m5873o() {
        return this.f4401q;
    }
}

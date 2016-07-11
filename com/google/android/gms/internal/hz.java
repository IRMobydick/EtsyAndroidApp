package com.google.android.gms.internal;

import android.location.Location;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.mediation.a;
import java.util.Date;
import java.util.Set;

@jw
public final class hz implements a {
    private final Date f5069a;
    private final int f5070b;
    private final Set<String> f5071c;
    private final boolean f5072d;
    private final Location f5073e;
    private final int f5074f;
    private final boolean f5075g;

    public hz(@Nullable Date date, int i, @Nullable Set<String> set, @Nullable Location location, boolean z, int i2, boolean z2) {
        this.f5069a = date;
        this.f5070b = i;
        this.f5071c = set;
        this.f5073e = location;
        this.f5072d = z;
        this.f5074f = i2;
        this.f5075g = z2;
    }

    public Date m6703a() {
        return this.f5069a;
    }

    public int m6704b() {
        return this.f5070b;
    }

    public Set<String> m6705c() {
        return this.f5071c;
    }

    public Location m6706d() {
        return this.f5073e;
    }

    public int m6707e() {
        return this.f5074f;
    }

    public boolean m6708f() {
        return this.f5072d;
    }

    public boolean m6709g() {
        return this.f5075g;
    }
}

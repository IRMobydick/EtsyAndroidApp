package com.google.android.gms.internal;

import android.location.Location;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.formats.c;
import com.google.android.gms.ads.formats.d;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.mediation.m;
import java.util.Date;
import java.util.List;
import java.util.Set;

@jw
public final class ib implements m {
    private final Date f5078a;
    private final int f5079b;
    private final Set<String> f5080c;
    private final boolean f5081d;
    private final Location f5082e;
    private final int f5083f;
    private final NativeAdOptionsParcel f5084g;
    private final List<String> f5085h;
    private final boolean f5086i;

    public ib(@Nullable Date date, int i, @Nullable Set<String> set, @Nullable Location location, boolean z, int i2, NativeAdOptionsParcel nativeAdOptionsParcel, List<String> list, boolean z2) {
        this.f5078a = date;
        this.f5079b = i;
        this.f5080c = set;
        this.f5082e = location;
        this.f5081d = z;
        this.f5083f = i2;
        this.f5084g = nativeAdOptionsParcel;
        this.f5085h = list;
        this.f5086i = z2;
    }

    public Date m6729a() {
        return this.f5078a;
    }

    public int m6730b() {
        return this.f5079b;
    }

    public Set<String> m6731c() {
        return this.f5080c;
    }

    public Location m6732d() {
        return this.f5082e;
    }

    public int m6733e() {
        return this.f5083f;
    }

    public boolean m6734f() {
        return this.f5081d;
    }

    public boolean m6735g() {
        return this.f5086i;
    }

    public c m6736h() {
        return this.f5084g == null ? null : new d().a(this.f5084g.zzBl).a(this.f5084g.zzBm).b(this.f5084g.zzBn).a();
    }

    public boolean m6737i() {
        return this.f5085h != null && this.f5085h.contains("2");
    }

    public boolean m6738j() {
        return this.f5085h != null && this.f5085h.contains("1");
    }
}

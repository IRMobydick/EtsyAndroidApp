package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.overlay.C1107h;
import com.google.android.gms.ads.internal.overlay.C1108k;
import com.google.android.gms.ads.internal.overlay.j;
import com.google.android.gms.ads.internal.overlay.p;
import com.google.android.gms.internal.fh;
import com.google.android.gms.internal.gf;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.ku;
import com.google.android.gms.internal.kw;

@jw
/* renamed from: com.google.android.gms.ads.internal.a */
public class C1077a {
    public final gf f4378a;
    public final C1107h f4379b;
    public final j f4380c;
    public final kw f4381d;

    public C1077a(gf gfVar, C1107h c1107h, j jVar, kw kwVar) {
        this.f4378a = gfVar;
        this.f4379b = c1107h;
        this.f4380c = jVar;
        this.f4381d = kwVar;
    }

    public static C1077a m5853a() {
        return new C1077a(new fh(), new C1108k(), new p(), new ku());
    }
}

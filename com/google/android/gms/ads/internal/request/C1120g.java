package com.google.android.gms.ads.internal.request;

import com.google.android.gms.ads.internal.request.g.1;
import com.google.android.gms.ads.internal.request.g.2;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.me;
import com.google.android.gms.internal.nb;

@jw
/* renamed from: com.google.android.gms.ads.internal.request.g */
public abstract class C1120g implements e, me<Void> {
    private final nb<AdRequestInfoParcel> f4630a;
    private final e f4631b;
    private final Object f4632c;

    public C1120g(nb<AdRequestInfoParcel> nbVar, e eVar) {
        this.f4632c = new Object();
        this.f4630a = nbVar;
        this.f4631b = eVar;
    }

    public abstract void m6123a();

    public void m6124a(AdResponseParcel adResponseParcel) {
        synchronized (this.f4632c) {
            this.f4631b.a(adResponseParcel);
            m6123a();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    boolean m6125a(com.google.android.gms.ads.internal.request.zzk r5, com.google.android.gms.ads.internal.request.AdRequestInfoParcel r6) {
        /*
        r4 = this;
        r1 = 0;
        r0 = 1;
        r2 = new com.google.android.gms.ads.internal.request.zzg;	 Catch:{ RemoteException -> 0x000b, NullPointerException -> 0x0024, SecurityException -> 0x0032, Throwable -> 0x0040 }
        r2.<init>(r4);	 Catch:{ RemoteException -> 0x000b, NullPointerException -> 0x0024, SecurityException -> 0x0032, Throwable -> 0x0040 }
        r5.zza(r6, r2);	 Catch:{ RemoteException -> 0x000b, NullPointerException -> 0x0024, SecurityException -> 0x0032, Throwable -> 0x0040 }
    L_0x000a:
        return r0;
    L_0x000b:
        r2 = move-exception;
        r3 = "Could not fetch ad response from ad request service.";
        com.google.android.gms.ads.internal.util.client.C1129c.m6193d(r3, r2);
        r3 = com.google.android.gms.ads.internal.C1101o.m6044h();
        r3.m7021a(r2, r0);
    L_0x0018:
        r0 = r4.f4631b;
        r2 = new com.google.android.gms.ads.internal.request.AdResponseParcel;
        r2.<init>(r1);
        r0.a(r2);
        r0 = r1;
        goto L_0x000a;
    L_0x0024:
        r2 = move-exception;
        r3 = "Could not fetch ad response from ad request service due to an Exception.";
        com.google.android.gms.ads.internal.util.client.C1129c.m6193d(r3, r2);
        r3 = com.google.android.gms.ads.internal.C1101o.m6044h();
        r3.m7021a(r2, r0);
        goto L_0x0018;
    L_0x0032:
        r2 = move-exception;
        r3 = "Could not fetch ad response from ad request service due to an Exception.";
        com.google.android.gms.ads.internal.util.client.C1129c.m6193d(r3, r2);
        r3 = com.google.android.gms.ads.internal.C1101o.m6044h();
        r3.m7021a(r2, r0);
        goto L_0x0018;
    L_0x0040:
        r2 = move-exception;
        r3 = "Could not fetch ad response from ad request service due to an Exception.";
        com.google.android.gms.ads.internal.util.client.C1129c.m6193d(r3, r2);
        r3 = com.google.android.gms.ads.internal.C1101o.m6044h();
        r3.m7021a(r2, r0);
        goto L_0x0018;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.request.g.a(com.google.android.gms.ads.internal.request.zzk, com.google.android.gms.ads.internal.request.AdRequestInfoParcel):boolean");
    }

    public abstract zzk m6126b();

    public Void m6127c() {
        zzk b = m6126b();
        if (b == null) {
            this.f4631b.a(new AdResponseParcel(0));
            m6123a();
        } else {
            this.f4630a.a(new 1(this, b), new 2(this));
        }
        return null;
    }

    public void cancel() {
        m6123a();
    }

    public /* synthetic */ Object zzhs() {
        return m6127c();
    }
}

package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.util.client.C1128a;
import com.google.android.gms.ads.internal.util.client.C1129c;

@jw
public class ko extends ln implements kp, kr {
    private final lc f5300a;
    private final Context f5301b;
    private final kt f5302c;
    private final kr f5303d;
    private final Object f5304e;
    private final String f5305f;
    private final String f5306g;
    private final String f5307h;
    private int f5308i;
    private int f5309j;

    public ko(Context context, String str, String str2, String str3, lc lcVar, kt ktVar, kr krVar) {
        this.f5308i = 0;
        this.f5309j = 3;
        this.f5301b = context;
        this.f5305f = str;
        this.f5306g = str2;
        this.f5307h = str3;
        this.f5300a = lcVar;
        this.f5302c = ktVar;
        this.f5304e = new Object();
        this.f5303d = krVar;
    }

    private void m6966a(AdRequestParcel adRequestParcel, zzgb com_google_android_gms_internal_zzgb) {
        try {
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.f5305f)) {
                com_google_android_gms_internal_zzgb.zza(adRequestParcel, this.f5306g, this.f5307h);
            } else {
                com_google_android_gms_internal_zzgb.zzc(adRequestParcel, this.f5306g);
            }
        } catch (Throwable e) {
            C1129c.m6193d("Fail to load ad from adapter.", e);
            m6974a(this.f5305f, 0);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m6969b(long r4) {
        /*
        r3 = this;
    L_0x0000:
        r1 = r3.f5304e;
        monitor-enter(r1);
        r0 = r3.f5308i;	 Catch:{ all -> 0x0011 }
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r1);	 Catch:{ all -> 0x0011 }
    L_0x0008:
        return;
    L_0x0009:
        r0 = r3.m6975a(r4);	 Catch:{ all -> 0x0011 }
        if (r0 != 0) goto L_0x0014;
    L_0x000f:
        monitor-exit(r1);	 Catch:{ all -> 0x0011 }
        goto L_0x0008;
    L_0x0011:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0011 }
        throw r0;
    L_0x0014:
        monitor-exit(r1);	 Catch:{ all -> 0x0011 }
        goto L_0x0000;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ko.b(long):void");
    }

    public void m6971a() {
        m6966a(this.f5300a.f5352a.zzLi, this.f5302c.m6984a());
    }

    public void m6972a(int i) {
        m6974a(this.f5305f, 0);
    }

    public void m6973a(String str) {
        synchronized (this.f5304e) {
            this.f5308i = 1;
            this.f5304e.notify();
        }
    }

    public void m6974a(String str, int i) {
        synchronized (this.f5304e) {
            this.f5308i = 2;
            this.f5309j = i;
            this.f5304e.notify();
        }
    }

    protected boolean m6975a(long j) {
        long elapsedRealtime = 20000 - (C1101o.m6045i().elapsedRealtime() - j);
        if (elapsedRealtime <= 0) {
            return false;
        }
        try {
            this.f5304e.wait(elapsedRealtime);
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }

    public void onStop() {
    }

    public void zzbQ() {
        if (this.f5302c != null && this.f5302c.m6985b() != null && this.f5302c.m6984a() != null) {
            zzjc b = this.f5302c.m6985b();
            b.zza((kr) this);
            b.zza((kp) this);
            AdRequestParcel adRequestParcel = this.f5300a.f5352a.zzLi;
            zzgb a = this.f5302c.m6984a();
            try {
                if (a.isInitialized()) {
                    C1128a.f4666a.post(new 1(this, adRequestParcel, a));
                } else {
                    C1128a.f4666a.post(new 2(this, a, adRequestParcel, b));
                }
            } catch (Throwable e) {
                C1129c.m6193d("Fail to check if adapter is initialized.", e);
                m6974a(this.f5305f, 0);
            }
            m6969b(C1101o.m6045i().elapsedRealtime());
            b.zza(null);
            b.zza(null);
            if (this.f5308i == 1) {
                this.f5303d.a(this.f5305f);
            } else {
                this.f5303d.a(this.f5305f, this.f5309j);
            }
        }
    }
}

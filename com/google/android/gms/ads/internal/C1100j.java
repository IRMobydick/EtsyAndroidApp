package com.google.android.gms.ads.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.j.1;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.lt;
import java.lang.ref.WeakReference;

@jw
/* renamed from: com.google.android.gms.ads.internal.j */
public class C1100j {
    private final k f4514a;
    private final Runnable f4515b;
    @Nullable
    private AdRequestParcel f4516c;
    private boolean f4517d;
    private boolean f4518e;
    private long f4519f;

    public C1100j(zza com_google_android_gms_ads_internal_zza) {
        this(com_google_android_gms_ads_internal_zza, new k(lt.f5423a));
    }

    C1100j(zza com_google_android_gms_ads_internal_zza, k kVar) {
        this.f4517d = false;
        this.f4518e = false;
        this.f4519f = 0;
        this.f4514a = kVar;
        this.f4515b = new 1(this, new WeakReference(com_google_android_gms_ads_internal_zza));
    }

    public void m6030a() {
        this.f4517d = false;
        this.f4514a.a(this.f4515b);
    }

    public void m6031a(AdRequestParcel adRequestParcel) {
        m6032a(adRequestParcel, 60000);
    }

    public void m6032a(AdRequestParcel adRequestParcel, long j) {
        if (this.f4517d) {
            C1129c.m6192d("An ad refresh is already scheduled.");
            return;
        }
        this.f4516c = adRequestParcel;
        this.f4517d = true;
        this.f4519f = j;
        if (!this.f4518e) {
            C1129c.m6190c("Scheduling ad refresh " + j + " milliseconds from now.");
            this.f4514a.a(this.f4515b, j);
        }
    }

    public void m6033b() {
        this.f4518e = true;
        if (this.f4517d) {
            this.f4514a.a(this.f4515b);
        }
    }

    public void m6034c() {
        this.f4518e = false;
        if (this.f4517d) {
            this.f4517d = false;
            m6032a(this.f4516c, this.f4519f);
        }
    }

    public boolean m6035d() {
        return this.f4517d;
    }
}

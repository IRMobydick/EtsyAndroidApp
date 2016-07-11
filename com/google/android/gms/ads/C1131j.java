package com.google.android.gms.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.zzab;
import com.google.android.gms.ads.internal.client.zzap;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.jw;

@jw
/* renamed from: com.google.android.gms.ads.j */
public final class C1131j {
    private final Object f4674a;
    @Nullable
    private zzab f4675b;
    @Nullable
    private k f4676c;

    public C1131j() {
        this.f4674a = new Object();
    }

    public void m6195a(zzab com_google_android_gms_ads_internal_client_zzab) {
        synchronized (this.f4674a) {
            this.f4675b = com_google_android_gms_ads_internal_client_zzab;
            if (this.f4676c != null) {
                m6196a(this.f4676c);
            }
        }
    }

    public void m6196a(k kVar) {
        zzaa.zzb((Object) kVar, (Object) "VideoLifecycleCallbacks may not be null.");
        synchronized (this.f4674a) {
            this.f4676c = kVar;
            if (this.f4675b == null) {
                return;
            }
            try {
                this.f4675b.zza(new zzap(kVar));
            } catch (Throwable e) {
                C1129c.m6189b("Unable to call setVideoLifecycleCallbacks on video controller.", e);
            }
        }
    }
}

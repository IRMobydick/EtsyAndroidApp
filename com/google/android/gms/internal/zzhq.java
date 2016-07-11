package com.google.android.gms.internal;

import com.google.android.gms.ads.purchase.d;
import com.google.android.gms.internal.zzhl.zza;

@jw
public final class zzhq extends zza {
    private final d zzwk;

    public zzhq(d dVar) {
        this.zzwk = dVar;
    }

    public boolean isValidPurchase(String str) {
        return this.zzwk.a(str);
    }

    public void zza(zzhk com_google_android_gms_internal_zzhk) {
        this.zzwk.a(new jc(com_google_android_gms_internal_zzhk));
    }
}

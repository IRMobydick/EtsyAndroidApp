package com.google.android.gms.ads.internal.request;

import com.google.android.gms.ads.internal.request.zzl.zza;
import com.google.android.gms.internal.jw;
import java.lang.ref.WeakReference;

@jw
public final class zzg extends zza {
    private final WeakReference<e> zzLN;

    public zzg(e eVar) {
        this.zzLN = new WeakReference(eVar);
    }

    public void zzb(AdResponseParcel adResponseParcel) {
        e eVar = (e) this.zzLN.get();
        if (eVar != null) {
            eVar.a(adResponseParcel);
        }
    }
}

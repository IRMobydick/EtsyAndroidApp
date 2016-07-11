package com.google.android.gms.ads.internal.overlay;

import android.graphics.Bitmap;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.overlay.d.1;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.ln;
import com.google.android.gms.internal.lt;

@jw
/* renamed from: com.google.android.gms.ads.internal.overlay.d */
class C1105d extends ln {
    final /* synthetic */ zzd f4553a;

    private C1105d(zzd com_google_android_gms_ads_internal_overlay_zzd) {
        this.f4553a = com_google_android_gms_ads_internal_overlay_zzd;
    }

    public void onStop() {
    }

    public void zzbQ() {
        Bitmap a = C1101o.m6058v().m7186a(Integer.valueOf(this.f4553a.zzHh.zzHO.zzrk));
        if (a != null) {
            lt.f5423a.post(new 1(this, C1101o.m6043g().m7147a(this.f4553a.mActivity, a, this.f4553a.zzHh.zzHO.zzri, this.f4553a.zzHh.zzHO.zzrj)));
        }
    }
}

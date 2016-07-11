package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.g.1;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.ln;
import com.google.android.gms.internal.lt;

@jw
/* renamed from: com.google.android.gms.ads.internal.g */
class C1097g extends ln {
    final /* synthetic */ zzl f4512a;
    private final int f4513b;

    public C1097g(zzl com_google_android_gms_ads_internal_zzl, int i) {
        this.f4512a = com_google_android_gms_ads_internal_zzl;
        this.f4513b = i;
    }

    public void onStop() {
    }

    public void zzbQ() {
        InterstitialAdParameterParcel interstitialAdParameterParcel = new InterstitialAdParameterParcel(this.f4512a.zzpV.zzrf, this.f4512a.zzbN(), this.f4512a.zzqZ, this.f4512a.zzra, this.f4512a.zzpV.zzrf ? this.f4513b : -1);
        int q = this.f4512a.zzpV.zzsC.f5327b.m7267q();
        lt.f5423a.post(new 1(this, new AdOverlayInfoParcel(this.f4512a, this.f4512a, this.f4512a, this.f4512a.zzpV.zzsC.f5327b, q == -1 ? this.f4512a.zzpV.zzsC.f5332g : q, this.f4512a.zzpV.zzsx, this.f4512a.zzpV.zzsC.f5321C, interstitialAdParameterParcel)));
    }
}

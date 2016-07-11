package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.a;
import com.google.android.gms.ads.internal.client.zzq.zza;
import com.google.android.gms.internal.jw;

@jw
public final class zzc extends zza {
    private final a zzuM;

    public zzc(a aVar) {
        this.zzuM = aVar;
    }

    public void onAdClosed() {
        this.zzuM.c();
    }

    public void onAdFailedToLoad(int i) {
        this.zzuM.a(i);
    }

    public void onAdLeftApplication() {
        this.zzuM.d();
    }

    public void onAdLoaded() {
        this.zzuM.a();
    }

    public void onAdOpened() {
        this.zzuM.b();
    }
}

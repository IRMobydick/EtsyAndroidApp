package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.ads.internal.e;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzdf.zza;

@jw
public final class zzdd extends zza {
    private final e zzAh;
    @Nullable
    private final String zzAi;
    private final String zzAj;

    public zzdd(e eVar, @Nullable String str, String str2) {
        this.zzAh = eVar;
        this.zzAi = str;
        this.zzAj = str2;
    }

    public String getContent() {
        return this.zzAj;
    }

    public void recordClick() {
        this.zzAh.zzbC();
    }

    public void recordImpression() {
        this.zzAh.zzbD();
    }

    public String zzeE() {
        return this.zzAi;
    }

    public void zzi(zzd com_google_android_gms_dynamic_zzd) {
        if (com_google_android_gms_dynamic_zzd != null) {
            this.zzAh.zzc((View) zze.zzx(com_google_android_gms_dynamic_zzd));
        }
    }
}

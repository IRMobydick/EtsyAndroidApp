package com.google.android.gms.internal;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.b;
import com.google.android.gms.ads.internal.formats.zzc;
import com.google.android.gms.ads.mediation.l;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzgf.zza;
import java.util.ArrayList;
import java.util.List;

@jw
public class zzgk extends zza {
    private final l zzFG;

    public zzgk(l lVar) {
        this.zzFG = lVar;
    }

    public String getAdvertiser() {
        return this.zzFG.j();
    }

    public String getBody() {
        return this.zzFG.g();
    }

    public String getCallToAction() {
        return this.zzFG.i();
    }

    public Bundle getExtras() {
        return this.zzFG.c();
    }

    public String getHeadline() {
        return this.zzFG.e();
    }

    public List getImages() {
        List<b> f = this.zzFG.f();
        if (f == null) {
            return null;
        }
        List arrayList = new ArrayList();
        for (b bVar : f) {
            arrayList.add(new zzc(bVar.a(), bVar.b(), bVar.c()));
        }
        return arrayList;
    }

    public boolean getOverrideClickHandling() {
        return this.zzFG.b();
    }

    public boolean getOverrideImpressionRecording() {
        return this.zzFG.a();
    }

    public void recordImpression() {
        this.zzFG.d();
    }

    public zzdj zzeR() {
        b h = this.zzFG.h();
        return h != null ? new zzc(h.a(), h.b(), h.c()) : null;
    }

    public void zzk(zzd com_google_android_gms_dynamic_zzd) {
        this.zzFG.b((View) zze.zzx(com_google_android_gms_dynamic_zzd));
    }

    public void zzl(zzd com_google_android_gms_dynamic_zzd) {
        this.zzFG.a((View) zze.zzx(com_google_android_gms_dynamic_zzd));
    }
}

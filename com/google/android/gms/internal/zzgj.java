package com.google.android.gms.internal;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.b;
import com.google.android.gms.ads.internal.formats.zzc;
import com.google.android.gms.ads.mediation.k;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzge.zza;
import java.util.ArrayList;
import java.util.List;

@jw
public class zzgj extends zza {
    private final k zzFF;

    public zzgj(k kVar) {
        this.zzFF = kVar;
    }

    public String getBody() {
        return this.zzFF.g();
    }

    public String getCallToAction() {
        return this.zzFF.i();
    }

    public Bundle getExtras() {
        return this.zzFF.c();
    }

    public String getHeadline() {
        return this.zzFF.e();
    }

    public List getImages() {
        List<b> f = this.zzFF.f();
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
        return this.zzFF.b();
    }

    public boolean getOverrideImpressionRecording() {
        return this.zzFF.a();
    }

    public String getPrice() {
        return this.zzFF.l();
    }

    public double getStarRating() {
        return this.zzFF.j();
    }

    public String getStore() {
        return this.zzFF.k();
    }

    public void recordImpression() {
        this.zzFF.d();
    }

    public zzdj zzeN() {
        b h = this.zzFF.h();
        return h != null ? new zzc(h.a(), h.b(), h.c()) : null;
    }

    public void zzk(zzd com_google_android_gms_dynamic_zzd) {
        this.zzFF.b((View) zze.zzx(com_google_android_gms_dynamic_zzd));
    }

    public void zzl(zzd com_google_android_gms_dynamic_zzd) {
        this.zzFF.a((View) zze.zzx(com_google_android_gms_dynamic_zzd));
    }
}

package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.dz;
import com.google.android.gms.internal.ei;
import com.google.android.gms.internal.hd;
import com.google.android.gms.internal.in;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.kv;
import com.google.android.gms.internal.lb;
import com.google.android.gms.internal.lc;
import com.google.android.gms.internal.lt;
import com.google.android.gms.internal.no;
import com.google.android.gms.internal.zzdg;
import com.google.android.gms.internal.zzga;

@jw
public abstract class zzc extends zzb implements e, in {
    public zzc(Context context, AdSizeParcel adSizeParcel, String str, zzga com_google_android_gms_internal_zzga, VersionInfoParcel versionInfoParcel, C1077a c1077a) {
        super(context, adSizeParcel, str, com_google_android_gms_internal_zzga, versionInfoParcel, c1077a);
    }

    protected no zza(lc lcVar, @Nullable C1078b c1078b, @Nullable kv kvVar) {
        no noVar = null;
        View nextView = this.zzpV.zzsy.getNextView();
        if (nextView instanceof no) {
            noVar = (no) nextView;
            if (((Boolean) dz.ac.m6433c()).booleanValue()) {
                C1129c.m6185a("Reusing webview...");
                noVar.m7238a(this.zzpV.zzov, this.zzpV.zzsB, this.zzpQ);
            } else {
                noVar.destroy();
                noVar = null;
            }
        }
        if (noVar == null) {
            if (nextView != null) {
                this.zzpV.zzsy.removeView(nextView);
            }
            noVar = C1101o.m6042f().m7278a(this.zzpV.zzov, this.zzpV.zzsB, false, false, this.zzpV.zzsw, this.zzpV.zzsx, this.zzpQ, this, this.zzpY);
            if (this.zzpV.zzsB.zzvu == null) {
                zzb(noVar.m7247b());
            }
        }
        hd hdVar = noVar;
        hdVar.m7262l().zza(this, this, this, this, false, this, null, c1078b, this, kvVar);
        zza(hdVar);
        hdVar.m7250b(lcVar.f5352a.zzLx);
        return hdVar;
    }

    public void zza(int i, int i2, int i3, int i4) {
        zzbo();
    }

    protected void zza(hd hdVar) {
        hdVar.a("/trackActiveViewUnit", new 1(this));
    }

    protected void zza(lc lcVar, ei eiVar) {
        kv kvVar = null;
        if (lcVar.f5356e != -2) {
            lt.f5423a.post(new 2(this, lcVar));
            return;
        }
        if (lcVar.f5355d != null) {
            this.zzpV.zzsB = lcVar.f5355d;
        }
        if (!lcVar.f5353b.zzLP || lcVar.f5353b.zzvx) {
            if (((Boolean) dz.bE.m6433c()).booleanValue()) {
                kvVar = this.zzpY.f4381d.a(this.zzpV.zzov, lcVar.f5353b);
            }
            lt.f5423a.post(new 3(this, lcVar, kvVar, eiVar));
            return;
        }
        this.zzpV.zzsX = 0;
        this.zzpV.zzsA = C1101o.m6040d().m6814a(this.zzpV.zzov, this, lcVar, this.zzpV.zzsw, null, this.zzqc, this, eiVar);
    }

    public void zza(zzdg com_google_android_gms_internal_zzdg) {
        zzaa.zzdc("setOnCustomRenderedAdLoadedListener must be called on the main UI thread.");
        this.zzpV.zzsR = com_google_android_gms_internal_zzdg;
    }

    protected boolean zza(@Nullable lb lbVar, lb lbVar2) {
        if (this.zzpV.zzcJ() && this.zzpV.zzsy != null) {
            this.zzpV.zzsy.zzcP().m7173a(lbVar2.f5321C);
        }
        return super.zza(lbVar, lbVar2);
    }

    public void zzbC() {
        onAdClicked();
    }

    public void zzbD() {
        recordImpression();
        zzbk();
    }

    public void zzbE() {
        zzbm();
    }

    public void zzc(View view) {
        this.zzpV.zzsW = view;
        zzb(new lb(this.zzpV.zzsD, null, null, null, null, null, null, null));
    }
}

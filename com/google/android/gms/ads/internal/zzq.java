package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.C1096c;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.formats.d;
import com.google.android.gms.ads.internal.formats.e;
import com.google.android.gms.ads.internal.formats.zzd;
import com.google.android.gms.ads.internal.formats.zze;
import com.google.android.gms.ads.internal.formats.zzf;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.ei;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.lb;
import com.google.android.gms.internal.lc;
import com.google.android.gms.internal.lt;
import com.google.android.gms.internal.zzdg;
import com.google.android.gms.internal.zzdt;
import com.google.android.gms.internal.zzdu;
import com.google.android.gms.internal.zzdv;
import com.google.android.gms.internal.zzdw;
import com.google.android.gms.internal.zzga;
import com.google.android.gms.internal.zzge;
import com.google.android.gms.internal.zzgf;
import com.google.android.gms.internal.zzhh;
import java.util.List;

@jw
public class zzq extends zzb {
    public zzq(Context context, C1077a c1077a, AdSizeParcel adSizeParcel, String str, zzga com_google_android_gms_internal_zzga, VersionInfoParcel versionInfoParcel) {
        super(context, adSizeParcel, str, com_google_android_gms_internal_zzga, versionInfoParcel, c1077a);
    }

    private static zzd zza(zzge com_google_android_gms_internal_zzge) {
        return new zzd(com_google_android_gms_internal_zzge.getHeadline(), com_google_android_gms_internal_zzge.getImages(), com_google_android_gms_internal_zzge.getBody(), com_google_android_gms_internal_zzge.zzeN() != null ? com_google_android_gms_internal_zzge.zzeN() : null, com_google_android_gms_internal_zzge.getCallToAction(), com_google_android_gms_internal_zzge.getStarRating(), com_google_android_gms_internal_zzge.getStore(), com_google_android_gms_internal_zzge.getPrice(), null, com_google_android_gms_internal_zzge.getExtras());
    }

    private static zze zza(zzgf com_google_android_gms_internal_zzgf) {
        return new zze(com_google_android_gms_internal_zzgf.getHeadline(), com_google_android_gms_internal_zzgf.getImages(), com_google_android_gms_internal_zzgf.getBody(), com_google_android_gms_internal_zzgf.zzeR() != null ? com_google_android_gms_internal_zzgf.zzeR() : null, com_google_android_gms_internal_zzgf.getCallToAction(), com_google_android_gms_internal_zzgf.getAdvertiser(), null, com_google_android_gms_internal_zzgf.getExtras());
    }

    private void zza(zzd com_google_android_gms_ads_internal_formats_zzd) {
        lt.f5423a.post(new 2(this, com_google_android_gms_ads_internal_formats_zzd));
    }

    private void zza(zze com_google_android_gms_ads_internal_formats_zze) {
        lt.f5423a.post(new 3(this, com_google_android_gms_ads_internal_formats_zze));
    }

    private void zza(lb lbVar, String str) {
        lt.f5423a.post(new 4(this, str, lbVar));
    }

    public void pause() {
        throw new IllegalStateException("Native Ad DOES NOT support pause().");
    }

    public void resume() {
        throw new IllegalStateException("Native Ad DOES NOT support resume().");
    }

    public void showInterstitial() {
        throw new IllegalStateException("Interstitial is NOT supported by NativeAdManager.");
    }

    public void zza(SimpleArrayMap<String, zzdw> simpleArrayMap) {
        zzaa.zzdc("setOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
        this.zzpV.zzsO = simpleArrayMap;
    }

    public void zza(d dVar) {
        if (this.zzpV.zzsC.f5335j != null) {
            C1101o.m6044h().m7039n().m6353a(this.zzpV.zzsB, this.zzpV.zzsC, dVar);
        }
    }

    public void zza(lc lcVar, ei eiVar) {
        if (lcVar.f5355d != null) {
            this.zzpV.zzsB = lcVar.f5355d;
        }
        if (lcVar.f5356e != -2) {
            lt.f5423a.post(new 1(this, lcVar));
            return;
        }
        this.zzpV.zzsX = 0;
        this.zzpV.zzsA = C1101o.m6040d().m6814a(this.zzpV.zzov, this, lcVar, this.zzpV.zzsw, null, this.zzqc, this, eiVar);
        String str = "AdRenderer: ";
        String valueOf = String.valueOf(this.zzpV.zzsA.getClass().getName());
        C1129c.m6185a(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    public void zza(zzdg com_google_android_gms_internal_zzdg) {
        throw new IllegalStateException("CustomRendering is NOT supported by NativeAdManager.");
    }

    public void zza(zzhh com_google_android_gms_internal_zzhh) {
        throw new IllegalStateException("In App Purchase is NOT supported by NativeAdManager.");
    }

    protected boolean zza(AdRequestParcel adRequestParcel, lb lbVar, boolean z) {
        return this.zzpU.m6035d();
    }

    protected boolean zza(lb lbVar, lb lbVar2) {
        zzgf com_google_android_gms_internal_zzgf = null;
        zzb(null);
        if (this.zzpV.zzcJ()) {
            if (lbVar2.f5339n) {
                try {
                    zzge zzfI = lbVar2.f5341p != null ? lbVar2.f5341p.zzfI() : null;
                    if (lbVar2.f5341p != null) {
                        com_google_android_gms_internal_zzgf = lbVar2.f5341p.zzfJ();
                    }
                    if (zzfI == null || this.zzpV.zzsL == null) {
                        if (com_google_android_gms_internal_zzgf != null) {
                            if (this.zzpV.zzsM != null) {
                                zze zza = zza(com_google_android_gms_internal_zzgf);
                                zza.zzb(new C1096c(this.zzpV.zzov, this, this.zzpV.zzsw, com_google_android_gms_internal_zzgf));
                                zza(zza);
                            }
                        }
                        C1129c.m6192d("No matching mapper/listener for retrieved native ad template.");
                        zzf(0);
                        return false;
                    }
                    zzd zza2 = zza(zzfI);
                    zza2.zzb(new C1096c(this.zzpV.zzov, this, this.zzpV.zzsw, zzfI));
                    zza(zza2);
                } catch (Throwable e) {
                    C1129c.m6193d("Failed to get native ad mapper", e);
                }
            } else {
                e eVar = lbVar2.f5322D;
                if ((eVar instanceof zze) && this.zzpV.zzsM != null) {
                    zza((zze) lbVar2.f5322D);
                } else if ((eVar instanceof zzd) && this.zzpV.zzsL != null) {
                    zza((zzd) lbVar2.f5322D);
                } else if (!(eVar instanceof zzf) || this.zzpV.zzsO == null || this.zzpV.zzsO.get(((zzf) eVar).getCustomTemplateId()) == null) {
                    C1129c.m6192d("No matching listener for retrieved native ad template.");
                    zzf(0);
                    return false;
                } else {
                    zza(lbVar2, ((zzf) eVar).getCustomTemplateId());
                }
            }
            return super.zza(lbVar, lbVar2);
        }
        throw new IllegalStateException("Native ad DOES NOT have custom rendering mode.");
    }

    public void zzb(SimpleArrayMap<String, zzdv> simpleArrayMap) {
        zzaa.zzdc("setOnCustomClickListener must be called on the main UI thread.");
        this.zzpV.zzsN = simpleArrayMap;
    }

    public void zzb(NativeAdOptionsParcel nativeAdOptionsParcel) {
        zzaa.zzdc("setNativeAdOptions must be called on the main UI thread.");
        this.zzpV.zzsP = nativeAdOptionsParcel;
    }

    public void zzb(zzdt com_google_android_gms_internal_zzdt) {
        zzaa.zzdc("setOnAppInstallAdLoadedListener must be called on the main UI thread.");
        this.zzpV.zzsL = com_google_android_gms_internal_zzdt;
    }

    public void zzb(zzdu com_google_android_gms_internal_zzdu) {
        zzaa.zzdc("setOnContentAdLoadedListener must be called on the main UI thread.");
        this.zzpV.zzsM = com_google_android_gms_internal_zzdu;
    }

    public void zzb(List<String> list) {
        zzaa.zzdc("setNativeTemplates must be called on the main UI thread.");
        this.zzpV.zzsT = list;
    }

    public SimpleArrayMap<String, zzdw> zzbV() {
        zzaa.zzdc("getOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
        return this.zzpV.zzsO;
    }

    public zzdv zzv(String str) {
        zzaa.zzdc("getOnCustomClickListener must be called on the main UI thread.");
        return (zzdv) this.zzpV.zzsN.get(str);
    }
}

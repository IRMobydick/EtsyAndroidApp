package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.internal.C1077a;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.VideoOptionsParcel;
import com.google.android.gms.ads.internal.client.zzab;
import com.google.android.gms.ads.internal.client.zzp;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzu.zza;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.client.zzy;
import com.google.android.gms.ads.internal.reward.client.zzd;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzl;

@jw
public class zzfe extends zza {
    private gh zzDj;
    private zzl zzDo;
    private gl zzDv;
    private zzhl zzDw;
    private String zzDx;
    private String zzqO;

    public zzfe(Context context, String str, zzga com_google_android_gms_internal_zzga, VersionInfoParcel versionInfoParcel, C1077a c1077a) {
        this(str, new gh(context, com_google_android_gms_internal_zzga, versionInfoParcel, c1077a));
    }

    zzfe(String str, gh ghVar) {
        this.zzqO = str;
        this.zzDj = ghVar;
        this.zzDv = new gl();
        C1101o.m6052p().m6600a(ghVar);
    }

    private void zzfq() {
        if (this.zzDo != null && this.zzDw != null) {
            this.zzDo.zza(this.zzDw, this.zzDx);
        }
    }

    static boolean zzn(AdRequestParcel adRequestParcel) {
        Bundle a = gn.m6589a(adRequestParcel);
        return a != null && a.containsKey("gw");
    }

    static boolean zzo(AdRequestParcel adRequestParcel) {
        Bundle a = gn.m6589a(adRequestParcel);
        return a != null && a.containsKey("_ad");
    }

    void abort() {
        if (this.zzDo == null) {
            this.zzDo = this.zzDj.m6582a(this.zzqO);
            this.zzDv.m6588a(this.zzDo);
            zzfq();
        }
    }

    public void destroy() {
        if (this.zzDo != null) {
            this.zzDo.destroy();
        }
    }

    public String getMediationAdapterClassName() {
        return this.zzDo != null ? this.zzDo.getMediationAdapterClassName() : null;
    }

    public boolean isLoading() {
        return this.zzDo != null && this.zzDo.isLoading();
    }

    public boolean isReady() {
        return this.zzDo != null && this.zzDo.isReady();
    }

    public void pause() {
        if (this.zzDo != null) {
            this.zzDo.pause();
        }
    }

    public void resume() {
        if (this.zzDo != null) {
            this.zzDo.resume();
        }
    }

    public void setManualImpressionsEnabled(boolean z) {
        abort();
        if (this.zzDo != null) {
            this.zzDo.setManualImpressionsEnabled(z);
        }
    }

    public void setUserId(String str) {
    }

    public void showInterstitial() {
        if (this.zzDo != null) {
            this.zzDo.showInterstitial();
        } else {
            C1129c.m6192d("Interstitial ad must be loaded before showInterstitial().");
        }
    }

    public void stopLoading() {
        if (this.zzDo != null) {
            this.zzDo.stopLoading();
        }
    }

    public void zza(AdSizeParcel adSizeParcel) {
        if (this.zzDo != null) {
            this.zzDo.zza(adSizeParcel);
        }
    }

    public void zza(VideoOptionsParcel videoOptionsParcel) {
        throw new IllegalStateException("getVideoController not implemented for interstitials");
    }

    public void zza(zzp com_google_android_gms_ads_internal_client_zzp) {
        this.zzDv.f4962e = com_google_android_gms_ads_internal_client_zzp;
        if (this.zzDo != null) {
            this.zzDv.m6588a(this.zzDo);
        }
    }

    public void zza(zzq com_google_android_gms_ads_internal_client_zzq) {
        this.zzDv.f4958a = com_google_android_gms_ads_internal_client_zzq;
        if (this.zzDo != null) {
            this.zzDv.m6588a(this.zzDo);
        }
    }

    public void zza(zzw com_google_android_gms_ads_internal_client_zzw) {
        this.zzDv.f4959b = com_google_android_gms_ads_internal_client_zzw;
        if (this.zzDo != null) {
            this.zzDv.m6588a(this.zzDo);
        }
    }

    public void zza(zzy com_google_android_gms_ads_internal_client_zzy) {
        abort();
        if (this.zzDo != null) {
            this.zzDo.zza(com_google_android_gms_ads_internal_client_zzy);
        }
    }

    public void zza(zzd com_google_android_gms_ads_internal_reward_client_zzd) {
        this.zzDv.f4963f = com_google_android_gms_ads_internal_reward_client_zzd;
        if (this.zzDo != null) {
            this.zzDv.m6588a(this.zzDo);
        }
    }

    public void zza(zzdg com_google_android_gms_internal_zzdg) {
        this.zzDv.f4961d = com_google_android_gms_internal_zzdg;
        if (this.zzDo != null) {
            this.zzDv.m6588a(this.zzDo);
        }
    }

    public void zza(zzhh com_google_android_gms_internal_zzhh) {
        this.zzDv.f4960c = com_google_android_gms_internal_zzhh;
        if (this.zzDo != null) {
            this.zzDv.m6588a(this.zzDo);
        }
    }

    public void zza(zzhl com_google_android_gms_internal_zzhl, String str) {
        this.zzDw = com_google_android_gms_internal_zzhl;
        this.zzDx = str;
        zzfq();
    }

    public boolean zzb(AdRequestParcel adRequestParcel) {
        if (!zzn(adRequestParcel)) {
            abort();
        }
        if (gn.m6595c(adRequestParcel)) {
            abort();
        }
        if (adRequestParcel.zzuU != null) {
            abort();
        }
        if (this.zzDo != null) {
            return this.zzDo.zzb(adRequestParcel);
        }
        gn p = C1101o.m6052p();
        if (zzo(adRequestParcel)) {
            p.m6602b(adRequestParcel, this.zzqO);
        }
        gq a = p.m6598a(adRequestParcel, this.zzqO);
        if (a != null) {
            if (!a.e) {
                a.a();
            }
            this.zzDo = a.a;
            a.c.m6587a(this.zzDv);
            this.zzDv.m6588a(this.zzDo);
            zzfq();
            return a.f;
        }
        abort();
        return this.zzDo.zzb(adRequestParcel);
    }

    public com.google.android.gms.dynamic.zzd zzbh() {
        return this.zzDo != null ? this.zzDo.zzbh() : null;
    }

    public AdSizeParcel zzbi() {
        return this.zzDo != null ? this.zzDo.zzbi() : null;
    }

    public void zzbk() {
        if (this.zzDo != null) {
            this.zzDo.zzbk();
        } else {
            C1129c.m6192d("Interstitial ad must be loaded before pingManualTrackingUrl().");
        }
    }

    public zzab zzbl() {
        throw new IllegalStateException("getVideoController not implemented for interstitials");
    }
}

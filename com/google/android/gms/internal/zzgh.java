package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.a.a.a;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.C1127b;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.m;
import com.google.android.gms.ads.mediation.b;
import com.google.android.gms.ads.mediation.d;
import com.google.android.gms.ads.mediation.f;
import com.google.android.gms.ads.mediation.h;
import com.google.android.gms.ads.mediation.j;
import com.google.android.gms.ads.mediation.k;
import com.google.android.gms.ads.mediation.l;
import com.google.android.gms.ads.mediation.n;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzgb.zza;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@jw
public final class zzgh extends zza {
    private final b zzFB;
    private ia zzFC;

    public zzgh(b bVar) {
        this.zzFB = bVar;
    }

    private Bundle zza(String str, int i, String str2) {
        String str3 = "Server parameters: ";
        String valueOf = String.valueOf(str);
        C1129c.m6192d(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        try {
            Bundle bundle = new Bundle();
            if (str != null) {
                JSONObject jSONObject = new JSONObject(str);
                Bundle bundle2 = new Bundle();
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    valueOf = (String) keys.next();
                    bundle2.putString(valueOf, jSONObject.getString(valueOf));
                }
                bundle = bundle2;
            }
            if (this.zzFB instanceof AdMobAdapter) {
                bundle.putString("adJson", str2);
                bundle.putInt("tagForChildDirectedTreatment", i);
            }
            return bundle;
        } catch (Throwable th) {
            C1129c.m6193d("Could not get Server Parameters Bundle.", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public void destroy() {
        try {
            this.zzFB.a();
        } catch (Throwable th) {
            C1129c.m6193d("Could not destroy adapter.", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public Bundle getInterstitialAdapterInfo() {
        if (this.zzFB instanceof nx) {
            return ((nx) this.zzFB).f();
        }
        String str = "MediationAdapter is not a v2 MediationInterstitialAdapter: ";
        String valueOf = String.valueOf(this.zzFB.getClass().getCanonicalName());
        C1129c.m6192d(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        return new Bundle();
    }

    public zzd getView() {
        if (this.zzFB instanceof d) {
            try {
                return zze.zzD(((d) this.zzFB).d());
            } catch (Throwable th) {
                C1129c.m6193d("Could not get banner view from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "MediationAdapter is not a MediationBannerAdapter: ";
            String valueOf = String.valueOf(this.zzFB.getClass().getCanonicalName());
            C1129c.m6192d(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public boolean isInitialized() {
        if (this.zzFB instanceof a) {
            C1129c.m6185a("Check if adapter is initialized.");
            try {
                return ((a) this.zzFB).h();
            } catch (Throwable th) {
                C1129c.m6193d("Could not check if adapter is initialized.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "MediationAdapter is not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzFB.getClass().getCanonicalName());
            C1129c.m6192d(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public void pause() {
        try {
            this.zzFB.b();
        } catch (Throwable th) {
            C1129c.m6193d("Could not pause adapter.", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public void resume() {
        try {
            this.zzFB.c();
        } catch (Throwable th) {
            C1129c.m6193d("Could not resume adapter.", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public void showInterstitial() {
        if (this.zzFB instanceof f) {
            C1129c.m6185a("Showing interstitial from adapter.");
            try {
                ((f) this.zzFB).e();
            } catch (Throwable th) {
                C1129c.m6193d("Could not show interstitial from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "MediationAdapter is not a MediationInterstitialAdapter: ";
            String valueOf = String.valueOf(this.zzFB.getClass().getCanonicalName());
            C1129c.m6192d(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public void showVideo() {
        if (this.zzFB instanceof a) {
            C1129c.m6185a("Show rewarded video ad from adapter.");
            try {
                ((a) this.zzFB).g();
            } catch (Throwable th) {
                C1129c.m6193d("Could not show rewarded video ad from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "MediationAdapter is not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzFB.getClass().getCanonicalName());
            C1129c.m6192d(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public void zza(AdRequestParcel adRequestParcel, String str, String str2) {
        if (this.zzFB instanceof a) {
            C1129c.m6185a("Requesting rewarded video ad from adapter.");
            try {
                a aVar = (a) this.zzFB;
                aVar.a(new hz(adRequestParcel.zzuN == -1 ? null : new Date(adRequestParcel.zzuN), adRequestParcel.zzuO, adRequestParcel.zzuP != null ? new HashSet(adRequestParcel.zzuP) : null, adRequestParcel.zzuV, adRequestParcel.zzuQ, adRequestParcel.zzuR, adRequestParcel.zzvc), zza(str, adRequestParcel.zzuR, str2), adRequestParcel.zzuX != null ? adRequestParcel.zzuX.getBundle(aVar.getClass().getName()) : null);
            } catch (Throwable th) {
                C1129c.m6193d("Could not load rewarded video ad from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "MediationAdapter is not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzFB.getClass().getCanonicalName());
            C1129c.m6192d(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdRequestParcel adRequestParcel, String str, com.google.android.gms.ads.internal.reward.mediation.client.zza com_google_android_gms_ads_internal_reward_mediation_client_zza, String str2) {
        if (this.zzFB instanceof a) {
            C1129c.m6185a("Initialize rewarded video adapter.");
            try {
                a aVar = (a) this.zzFB;
                aVar.a((Context) zze.zzx(com_google_android_gms_dynamic_zzd), new hz(adRequestParcel.zzuN == -1 ? null : new Date(adRequestParcel.zzuN), adRequestParcel.zzuO, adRequestParcel.zzuP != null ? new HashSet(adRequestParcel.zzuP) : null, adRequestParcel.zzuV, adRequestParcel.zzuQ, adRequestParcel.zzuR, adRequestParcel.zzvc), str, new C1127b(com_google_android_gms_ads_internal_reward_mediation_client_zza), zza(str2, adRequestParcel.zzuR, null), adRequestParcel.zzuX != null ? adRequestParcel.zzuX.getBundle(aVar.getClass().getName()) : null);
            } catch (Throwable th) {
                C1129c.m6193d("Could not initialize rewarded video adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "MediationAdapter is not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzFB.getClass().getCanonicalName());
            C1129c.m6192d(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdRequestParcel adRequestParcel, String str, zzgc com_google_android_gms_internal_zzgc) {
        zza(com_google_android_gms_dynamic_zzd, adRequestParcel, str, null, com_google_android_gms_internal_zzgc);
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdRequestParcel adRequestParcel, String str, String str2, zzgc com_google_android_gms_internal_zzgc) {
        if (this.zzFB instanceof f) {
            C1129c.m6185a("Requesting interstitial ad from adapter.");
            try {
                f fVar = (f) this.zzFB;
                fVar.a((Context) zze.zzx(com_google_android_gms_dynamic_zzd), new ia(com_google_android_gms_internal_zzgc), zza(str, adRequestParcel.zzuR, str2), new hz(adRequestParcel.zzuN == -1 ? null : new Date(adRequestParcel.zzuN), adRequestParcel.zzuO, adRequestParcel.zzuP != null ? new HashSet(adRequestParcel.zzuP) : null, adRequestParcel.zzuV, adRequestParcel.zzuQ, adRequestParcel.zzuR, adRequestParcel.zzvc), adRequestParcel.zzuX != null ? adRequestParcel.zzuX.getBundle(fVar.getClass().getName()) : null);
            } catch (Throwable th) {
                C1129c.m6193d("Could not request interstitial ad from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "MediationAdapter is not a MediationInterstitialAdapter: ";
            String valueOf = String.valueOf(this.zzFB.getClass().getCanonicalName());
            C1129c.m6192d(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdRequestParcel adRequestParcel, String str, String str2, zzgc com_google_android_gms_internal_zzgc, NativeAdOptionsParcel nativeAdOptionsParcel, List<String> list) {
        if (this.zzFB instanceof h) {
            try {
                h hVar = (h) this.zzFB;
                ib ibVar = new ib(adRequestParcel.zzuN == -1 ? null : new Date(adRequestParcel.zzuN), adRequestParcel.zzuO, adRequestParcel.zzuP != null ? new HashSet(adRequestParcel.zzuP) : null, adRequestParcel.zzuV, adRequestParcel.zzuQ, adRequestParcel.zzuR, nativeAdOptionsParcel, list, adRequestParcel.zzvc);
                Bundle bundle = adRequestParcel.zzuX != null ? adRequestParcel.zzuX.getBundle(hVar.getClass().getName()) : null;
                this.zzFC = new ia(com_google_android_gms_internal_zzgc);
                hVar.a((Context) zze.zzx(com_google_android_gms_dynamic_zzd), this.zzFC, zza(str, adRequestParcel.zzuR, str2), ibVar, bundle);
            } catch (Throwable th) {
                C1129c.m6193d("Could not request native ad from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "MediationAdapter is not a MediationNativeAdapter: ";
            String valueOf = String.valueOf(this.zzFB.getClass().getCanonicalName());
            C1129c.m6192d(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, AdRequestParcel adRequestParcel, String str, zzgc com_google_android_gms_internal_zzgc) {
        zza(com_google_android_gms_dynamic_zzd, adSizeParcel, adRequestParcel, str, null, com_google_android_gms_internal_zzgc);
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, AdRequestParcel adRequestParcel, String str, String str2, zzgc com_google_android_gms_internal_zzgc) {
        if (this.zzFB instanceof d) {
            C1129c.m6185a("Requesting banner ad from adapter.");
            try {
                d dVar = (d) this.zzFB;
                dVar.a((Context) zze.zzx(com_google_android_gms_dynamic_zzd), new ia(com_google_android_gms_internal_zzgc), zza(str, adRequestParcel.zzuR, str2), m.a(adSizeParcel.width, adSizeParcel.height, adSizeParcel.zzvs), new hz(adRequestParcel.zzuN == -1 ? null : new Date(adRequestParcel.zzuN), adRequestParcel.zzuO, adRequestParcel.zzuP != null ? new HashSet(adRequestParcel.zzuP) : null, adRequestParcel.zzuV, adRequestParcel.zzuQ, adRequestParcel.zzuR, adRequestParcel.zzvc), adRequestParcel.zzuX != null ? adRequestParcel.zzuX.getBundle(dVar.getClass().getName()) : null);
            } catch (Throwable th) {
                C1129c.m6193d("Could not request banner ad from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "MediationAdapter is not a MediationBannerAdapter: ";
            String valueOf = String.valueOf(this.zzFB.getClass().getCanonicalName());
            C1129c.m6192d(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public void zzc(AdRequestParcel adRequestParcel, String str) {
        zza(adRequestParcel, str, null);
    }

    public zzge zzfI() {
        j a = this.zzFC.m6710a();
        return a instanceof k ? new zzgj((k) a) : null;
    }

    public zzgf zzfJ() {
        j a = this.zzFC.m6710a();
        return a instanceof l ? new zzgk((l) a) : null;
    }

    public Bundle zzfK() {
        if (this.zzFB instanceof nw) {
            return ((nw) this.zzFB).e();
        }
        String str = "MediationAdapter is not a v2 MediationBannerAdapter: ";
        String valueOf = String.valueOf(this.zzFB.getClass().getCanonicalName());
        C1129c.m6192d(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        return new Bundle();
    }

    public Bundle zzfL() {
        return new Bundle();
    }

    public void zzj(zzd com_google_android_gms_dynamic_zzd) {
        try {
            ((n) this.zzFB).a((Context) zze.zzx(com_google_android_gms_dynamic_zzd));
        } catch (Throwable th) {
            C1129c.m6186a("Could not inform adapter of changed context", th);
        }
    }
}

package com.google.android.gms.internal;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.C1067h;
import com.google.ads.mediation.C1068i;
import com.google.ads.mediation.C1070k;
import com.google.ads.mediation.C1073n;
import com.google.ads.mediation.MediationServerParameters;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzgb.zza;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

@jw
public final class zzgm<NETWORK_EXTRAS extends C1073n, SERVER_PARAMETERS extends MediationServerParameters> extends zza {
    private final C1067h<NETWORK_EXTRAS, SERVER_PARAMETERS> zzFH;
    private final NETWORK_EXTRAS zzFI;

    public zzgm(C1067h<NETWORK_EXTRAS, SERVER_PARAMETERS> c1067h, NETWORK_EXTRAS network_extras) {
        this.zzFH = c1067h;
        this.zzFI = network_extras;
    }

    private SERVER_PARAMETERS zzb(String str, int i, String str2) {
        Map hashMap;
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                hashMap = new HashMap(jSONObject.length());
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str3 = (String) keys.next();
                    hashMap.put(str3, jSONObject.getString(str3));
                }
            } catch (Throwable th) {
                C1129c.m6193d("Could not get MediationServerParameters.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            hashMap = new HashMap(0);
        }
        Class c = this.zzFH.m5837c();
        if (c == null) {
            return null;
        }
        MediationServerParameters mediationServerParameters = (MediationServerParameters) c.newInstance();
        mediationServerParameters.m5811a(hashMap);
        return mediationServerParameters;
    }

    public void destroy() {
        try {
            this.zzFH.m5835a();
        } catch (Throwable th) {
            C1129c.m6193d("Could not destroy adapter.", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public Bundle getInterstitialAdapterInfo() {
        return new Bundle();
    }

    public zzd getView() {
        if (this.zzFH instanceof C1068i) {
            try {
                return zze.zzD(((C1068i) this.zzFH).m5839d());
            } catch (Throwable th) {
                C1129c.m6193d("Could not get banner view from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "MediationAdapter is not a MediationBannerAdapter: ";
            String valueOf = String.valueOf(this.zzFH.getClass().getCanonicalName());
            C1129c.m6192d(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public boolean isInitialized() {
        return true;
    }

    public void pause() {
        throw new RemoteException();
    }

    public void resume() {
        throw new RemoteException();
    }

    public void showInterstitial() {
        if (this.zzFH instanceof C1070k) {
            C1129c.m6185a("Showing interstitial from adapter.");
            try {
                ((C1070k) this.zzFH).m5842e();
            } catch (Throwable th) {
                C1129c.m6193d("Could not show interstitial from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "MediationAdapter is not a MediationInterstitialAdapter: ";
            String valueOf = String.valueOf(this.zzFH.getClass().getCanonicalName());
            C1129c.m6192d(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public void showVideo() {
    }

    public void zza(AdRequestParcel adRequestParcel, String str, String str2) {
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdRequestParcel adRequestParcel, String str, com.google.android.gms.ads.internal.reward.mediation.client.zza com_google_android_gms_ads_internal_reward_mediation_client_zza, String str2) {
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdRequestParcel adRequestParcel, String str, zzgc com_google_android_gms_internal_zzgc) {
        zza(com_google_android_gms_dynamic_zzd, adRequestParcel, str, null, com_google_android_gms_internal_zzgc);
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdRequestParcel adRequestParcel, String str, String str2, zzgc com_google_android_gms_internal_zzgc) {
        if (this.zzFH instanceof C1070k) {
            C1129c.m6185a("Requesting interstitial ad from adapter.");
            try {
                ((C1070k) this.zzFH).m5841a(new ic(com_google_android_gms_internal_zzgc), (Activity) zze.zzx(com_google_android_gms_dynamic_zzd), zzb(str, adRequestParcel.zzuR, str2), id.m6745a(adRequestParcel), this.zzFI);
            } catch (Throwable th) {
                C1129c.m6193d("Could not request interstitial ad from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "MediationAdapter is not a MediationInterstitialAdapter: ";
            String valueOf = String.valueOf(this.zzFH.getClass().getCanonicalName());
            C1129c.m6192d(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdRequestParcel adRequestParcel, String str, String str2, zzgc com_google_android_gms_internal_zzgc, NativeAdOptionsParcel nativeAdOptionsParcel, List<String> list) {
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, AdRequestParcel adRequestParcel, String str, zzgc com_google_android_gms_internal_zzgc) {
        zza(com_google_android_gms_dynamic_zzd, adSizeParcel, adRequestParcel, str, null, com_google_android_gms_internal_zzgc);
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, AdRequestParcel adRequestParcel, String str, String str2, zzgc com_google_android_gms_internal_zzgc) {
        if (this.zzFH instanceof C1068i) {
            C1129c.m6185a("Requesting banner ad from adapter.");
            try {
                ((C1068i) this.zzFH).m5838a(new ic(com_google_android_gms_internal_zzgc), (Activity) zze.zzx(com_google_android_gms_dynamic_zzd), zzb(str, adRequestParcel.zzuR, str2), id.m6744a(adSizeParcel), id.m6745a(adRequestParcel), this.zzFI);
            } catch (Throwable th) {
                C1129c.m6193d("Could not request banner ad from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "MediationAdapter is not a MediationBannerAdapter: ";
            String valueOf = String.valueOf(this.zzFH.getClass().getCanonicalName());
            C1129c.m6192d(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public void zzc(AdRequestParcel adRequestParcel, String str) {
    }

    public zzge zzfI() {
        return null;
    }

    public zzgf zzfJ() {
        return null;
    }

    public Bundle zzfK() {
        return new Bundle();
    }

    public Bundle zzfL() {
        return new Bundle();
    }

    public void zzj(zzd com_google_android_gms_dynamic_zzd) {
    }
}

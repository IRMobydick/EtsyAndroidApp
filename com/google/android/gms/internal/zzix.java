package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.C1077a;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.reward.client.RewardedVideoAdRequestParcel;
import com.google.android.gms.ads.internal.reward.client.zzb.zza;
import com.google.android.gms.ads.internal.reward.client.zzd;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.dynamic.zze;

@jw
public class zzix extends zza {
    private final Context mContext;
    private final zziy zzOx;
    private final Object zzpp;
    private final VersionInfoParcel zzqP;

    public zzix(Context context, C1077a c1077a, zzga com_google_android_gms_internal_zzga, VersionInfoParcel versionInfoParcel) {
        this.mContext = context;
        this.zzqP = versionInfoParcel;
        this.zzOx = new zziy(context, c1077a, AdSizeParcel.zzdC(), com_google_android_gms_internal_zzga, versionInfoParcel);
        this.zzpp = new Object();
    }

    public void destroy() {
        zzh(null);
    }

    public boolean isLoaded() {
        boolean isLoaded;
        synchronized (this.zzpp) {
            isLoaded = this.zzOx.isLoaded();
        }
        return isLoaded;
    }

    public void pause() {
        zzf(null);
    }

    public void resume() {
        zzg(null);
    }

    public void setUserId(String str) {
        C1129c.m6192d("RewardedVideoAd.setUserId() is deprecated. Please do not call this method.");
    }

    public void show() {
        synchronized (this.zzpp) {
            this.zzOx.zzil();
        }
    }

    public void zza(RewardedVideoAdRequestParcel rewardedVideoAdRequestParcel) {
        synchronized (this.zzpp) {
            this.zzOx.zza(rewardedVideoAdRequestParcel);
        }
    }

    public void zza(zzd com_google_android_gms_ads_internal_reward_client_zzd) {
        synchronized (this.zzpp) {
            this.zzOx.zza(com_google_android_gms_ads_internal_reward_client_zzd);
        }
    }

    public void zzf(com.google.android.gms.dynamic.zzd com_google_android_gms_dynamic_zzd) {
        synchronized (this.zzpp) {
            this.zzOx.pause();
        }
    }

    public void zzg(com.google.android.gms.dynamic.zzd com_google_android_gms_dynamic_zzd) {
        synchronized (this.zzpp) {
            Context context = com_google_android_gms_dynamic_zzd == null ? null : (Context) zze.zzx(com_google_android_gms_dynamic_zzd);
            if (context != null) {
                try {
                    this.zzOx.onContextChanged(context);
                } catch (Throwable e) {
                    C1129c.m6193d("Unable to extract updated context.", e);
                }
            }
            this.zzOx.resume();
        }
    }

    public void zzh(com.google.android.gms.dynamic.zzd com_google_android_gms_dynamic_zzd) {
        synchronized (this.zzpp) {
            this.zzOx.destroy();
        }
    }
}

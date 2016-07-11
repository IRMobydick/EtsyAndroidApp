package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.zza.zza;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;

@jw
public class zzjc extends zza {
    private kr zzOF;
    private kp zzOM;
    private kq zzON;

    public zzjc(kq kqVar) {
        this.zzON = kqVar;
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, RewardItemParcel rewardItemParcel) {
        if (this.zzON != null) {
            this.zzON.zzc(rewardItemParcel);
        }
    }

    public void zza(kp kpVar) {
        this.zzOM = kpVar;
    }

    public void zza(kr krVar) {
        this.zzOF = krVar;
    }

    public void zzb(zzd com_google_android_gms_dynamic_zzd, int i) {
        if (this.zzOM != null) {
            this.zzOM.a(i);
        }
    }

    public void zzc(zzd com_google_android_gms_dynamic_zzd, int i) {
        if (this.zzOF != null) {
            this.zzOF.a(zze.zzx(com_google_android_gms_dynamic_zzd).getClass().getName(), i);
        }
    }

    public void zzo(zzd com_google_android_gms_dynamic_zzd) {
        if (this.zzOM != null) {
            this.zzOM.a();
        }
    }

    public void zzp(zzd com_google_android_gms_dynamic_zzd) {
        if (this.zzOF != null) {
            this.zzOF.a(zze.zzx(com_google_android_gms_dynamic_zzd).getClass().getName());
        }
    }

    public void zzq(zzd com_google_android_gms_dynamic_zzd) {
        if (this.zzON != null) {
            this.zzON.onRewardedVideoAdOpened();
        }
    }

    public void zzr(zzd com_google_android_gms_dynamic_zzd) {
        if (this.zzON != null) {
            this.zzON.onRewardedVideoStarted();
        }
    }

    public void zzs(zzd com_google_android_gms_dynamic_zzd) {
        if (this.zzON != null) {
            this.zzON.onRewardedVideoAdClosed();
        }
    }

    public void zzt(zzd com_google_android_gms_dynamic_zzd) {
        if (this.zzON != null) {
            this.zzON.zzim();
        }
    }

    public void zzu(zzd com_google_android_gms_dynamic_zzd) {
        if (this.zzON != null) {
            this.zzON.onRewardedVideoAdLeftApplication();
        }
    }
}

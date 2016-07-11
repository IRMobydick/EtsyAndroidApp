package com.google.android.gms.ads.internal.reward.client;

import com.google.android.gms.ads.a.b;
import com.google.android.gms.ads.internal.reward.client.zzd.zza;
import com.google.android.gms.internal.jw;

@jw
public class zzg extends zza {
    private final b zzaY;

    public zzg(b bVar) {
        this.zzaY = bVar;
    }

    public void onRewardedVideoAdClosed() {
        if (this.zzaY != null) {
            this.zzaY.d();
        }
    }

    public void onRewardedVideoAdFailedToLoad(int i) {
        if (this.zzaY != null) {
            this.zzaY.a(i);
        }
    }

    public void onRewardedVideoAdLeftApplication() {
        if (this.zzaY != null) {
            this.zzaY.e();
        }
    }

    public void onRewardedVideoAdLoaded() {
        if (this.zzaY != null) {
            this.zzaY.a();
        }
    }

    public void onRewardedVideoAdOpened() {
        if (this.zzaY != null) {
            this.zzaY.b();
        }
    }

    public void onRewardedVideoStarted() {
        if (this.zzaY != null) {
            this.zzaY.c();
        }
    }

    public void zza(zza com_google_android_gms_ads_internal_reward_client_zza) {
        if (this.zzaY != null) {
            this.zzaY.a(new C1125e(com_google_android_gms_ads_internal_reward_client_zza));
        }
    }
}

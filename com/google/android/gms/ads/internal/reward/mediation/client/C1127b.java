package com.google.android.gms.ads.internal.reward.mediation.client;

import com.google.android.gms.ads.a.a.a;
import com.google.android.gms.ads.a.a.b;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.jw;

@jw
/* renamed from: com.google.android.gms.ads.internal.reward.mediation.client.b */
public class C1127b implements b {
    private final zza f4665a;

    public C1127b(zza com_google_android_gms_ads_internal_reward_mediation_client_zza) {
        this.f4665a = com_google_android_gms_ads_internal_reward_mediation_client_zza;
    }

    public void m6157a(a aVar) {
        zzaa.zzdc("onInitializationSucceeded must be called on the main UI thread.");
        C1129c.m6185a("Adapter called onInitializationSucceeded.");
        try {
            this.f4665a.zzo(zze.zzD(aVar));
        } catch (Throwable e) {
            C1129c.m6193d("Could not call onInitializationSucceeded.", e);
        }
    }

    public void m6158a(a aVar, int i) {
        zzaa.zzdc("onAdFailedToLoad must be called on the main UI thread.");
        C1129c.m6185a("Adapter called onAdFailedToLoad.");
        try {
            this.f4665a.zzc(zze.zzD(aVar), i);
        } catch (Throwable e) {
            C1129c.m6193d("Could not call onAdFailedToLoad.", e);
        }
    }

    public void m6159a(a aVar, com.google.android.gms.ads.a.a aVar2) {
        zzaa.zzdc("onRewarded must be called on the main UI thread.");
        C1129c.m6185a("Adapter called onRewarded.");
        if (aVar2 != null) {
            try {
                this.f4665a.zza(zze.zzD(aVar), new RewardItemParcel(aVar2));
                return;
            } catch (Throwable e) {
                C1129c.m6193d("Could not call onRewarded.", e);
                return;
            }
        }
        this.f4665a.zza(zze.zzD(aVar), new RewardItemParcel(aVar.getClass().getName(), 1));
    }

    public void m6160b(a aVar) {
        zzaa.zzdc("onAdLoaded must be called on the main UI thread.");
        C1129c.m6185a("Adapter called onAdLoaded.");
        try {
            this.f4665a.zzp(zze.zzD(aVar));
        } catch (Throwable e) {
            C1129c.m6193d("Could not call onAdLoaded.", e);
        }
    }

    public void m6161c(a aVar) {
        zzaa.zzdc("onAdOpened must be called on the main UI thread.");
        C1129c.m6185a("Adapter called onAdOpened.");
        try {
            this.f4665a.zzq(zze.zzD(aVar));
        } catch (Throwable e) {
            C1129c.m6193d("Could not call onAdOpened.", e);
        }
    }

    public void m6162d(a aVar) {
        zzaa.zzdc("onVideoStarted must be called on the main UI thread.");
        C1129c.m6185a("Adapter called onVideoStarted.");
        try {
            this.f4665a.zzr(zze.zzD(aVar));
        } catch (Throwable e) {
            C1129c.m6193d("Could not call onVideoStarted.", e);
        }
    }

    public void m6163e(a aVar) {
        zzaa.zzdc("onAdClosed must be called on the main UI thread.");
        C1129c.m6185a("Adapter called onAdClosed.");
        try {
            this.f4665a.zzs(zze.zzD(aVar));
        } catch (Throwable e) {
            C1129c.m6193d("Could not call onAdClosed.", e);
        }
    }

    public void m6164f(a aVar) {
        zzaa.zzdc("onAdLeftApplication must be called on the main UI thread.");
        C1129c.m6185a("Adapter called onAdLeftApplication.");
        try {
            this.f4665a.zzu(zze.zzD(aVar));
        } catch (Throwable e) {
            C1129c.m6193d("Could not call onAdLeftApplication.", e);
        }
    }
}

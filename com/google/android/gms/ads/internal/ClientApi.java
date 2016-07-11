package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Keep;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzs;
import com.google.android.gms.ads.internal.client.zzu;
import com.google.android.gms.ads.internal.client.zzx.zza;
import com.google.android.gms.ads.internal.client.zzz;
import com.google.android.gms.ads.internal.formats.zzk;
import com.google.android.gms.ads.internal.reward.client.zzb;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.dz;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.zzdl;
import com.google.android.gms.internal.zzfe;
import com.google.android.gms.internal.zzga;
import com.google.android.gms.internal.zzgz;
import com.google.android.gms.internal.zzhi;
import com.google.android.gms.internal.zzix;

@Keep
@DynamiteApi
@jw
public class ClientApi extends zza {
    public zzs createAdLoaderBuilder(zzd com_google_android_gms_dynamic_zzd, String str, zzga com_google_android_gms_internal_zzga, int i) {
        Context context = (Context) zze.zzx(com_google_android_gms_dynamic_zzd);
        return new zzk(context, str, com_google_android_gms_internal_zzga, new VersionInfoParcel(9080000, i, context.getClassLoader() == ClientApi.class.getClassLoader()), C1077a.m5853a());
    }

    public zzgz createAdOverlay(zzd com_google_android_gms_dynamic_zzd) {
        return new com.google.android.gms.ads.internal.overlay.zzd((Activity) zze.zzx(com_google_android_gms_dynamic_zzd));
    }

    public zzu createBannerAdManager(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, String str, zzga com_google_android_gms_internal_zzga, int i) {
        Context context = (Context) zze.zzx(com_google_android_gms_dynamic_zzd);
        return new zzf(context, adSizeParcel, str, com_google_android_gms_internal_zzga, new VersionInfoParcel(9080000, i, context.getClassLoader() == ClientApi.class.getClassLoader()), C1077a.m5853a());
    }

    public zzhi createInAppPurchaseManager(zzd com_google_android_gms_dynamic_zzd) {
        return new com.google.android.gms.ads.internal.purchase.zze((Activity) zze.zzx(com_google_android_gms_dynamic_zzd));
    }

    public zzu createInterstitialAdManager(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, String str, zzga com_google_android_gms_internal_zzga, int i) {
        Context context = (Context) zze.zzx(com_google_android_gms_dynamic_zzd);
        dz.m6444a(context);
        VersionInfoParcel versionInfoParcel = new VersionInfoParcel(9080000, i, context.getClassLoader() == ClientApi.class.getClassLoader());
        boolean equals = "reward_mb".equals(adSizeParcel.zzvs);
        Object obj = ((equals || !((Boolean) dz.ah.m6433c()).booleanValue()) && !(equals && ((Boolean) dz.ai.m6433c()).booleanValue())) ? null : 1;
        if (obj != null) {
            return new zzfe(context, str, com_google_android_gms_internal_zzga, versionInfoParcel, C1077a.m5853a());
        }
        return new zzl(context, adSizeParcel, str, com_google_android_gms_internal_zzga, versionInfoParcel, C1077a.m5853a());
    }

    public zzdl createNativeAdViewDelegate(zzd com_google_android_gms_dynamic_zzd, zzd com_google_android_gms_dynamic_zzd2) {
        return new zzk((FrameLayout) zze.zzx(com_google_android_gms_dynamic_zzd), (FrameLayout) zze.zzx(com_google_android_gms_dynamic_zzd2));
    }

    public zzb createRewardedVideoAd(zzd com_google_android_gms_dynamic_zzd, zzga com_google_android_gms_internal_zzga, int i) {
        Context context = (Context) zze.zzx(com_google_android_gms_dynamic_zzd);
        return new zzix(context, C1077a.m5853a(), com_google_android_gms_internal_zzga, new VersionInfoParcel(9080000, i, context.getClassLoader() == ClientApi.class.getClassLoader()));
    }

    public zzu createSearchAdManager(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, String str, int i) {
        Context context = (Context) zze.zzx(com_google_android_gms_dynamic_zzd);
        return new zzt(context, adSizeParcel, str, new VersionInfoParcel(9080000, i, context.getClassLoader() == ClientApi.class.getClassLoader()));
    }

    public zzz getMobileAdsSettingsManager(zzd com_google_android_gms_dynamic_zzd) {
        return null;
    }

    public zzz getMobileAdsSettingsManagerWithClientJarVersion(zzd com_google_android_gms_dynamic_zzd, int i) {
        Context context = (Context) zze.zzx(com_google_android_gms_dynamic_zzd);
        return zzo.zza(context, new VersionInfoParcel(9080000, i, context.getClassLoader() == ClientApi.class.getClassLoader()));
    }
}

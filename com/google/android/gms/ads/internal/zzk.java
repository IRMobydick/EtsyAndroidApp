package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzr;
import com.google.android.gms.ads.internal.client.zzs.zza;
import com.google.android.gms.ads.internal.client.zzy;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.zzdt;
import com.google.android.gms.internal.zzdu;
import com.google.android.gms.internal.zzdv;
import com.google.android.gms.internal.zzdw;
import com.google.android.gms.internal.zzga;

@jw
public class zzk extends zza {
    private final Context mContext;
    private final C1077a zzpY;
    private zzq zzqG;
    private NativeAdOptionsParcel zzqL;
    private zzy zzqN;
    private final String zzqO;
    private final VersionInfoParcel zzqP;
    private zzdt zzqT;
    private zzdu zzqU;
    private SimpleArrayMap<String, zzdv> zzqV;
    private SimpleArrayMap<String, zzdw> zzqW;
    private final zzga zzqc;

    public zzk(Context context, String str, zzga com_google_android_gms_internal_zzga, VersionInfoParcel versionInfoParcel, C1077a c1077a) {
        this.mContext = context;
        this.zzqO = str;
        this.zzqc = com_google_android_gms_internal_zzga;
        this.zzqP = versionInfoParcel;
        this.zzqW = new SimpleArrayMap();
        this.zzqV = new SimpleArrayMap();
        this.zzpY = c1077a;
    }

    public void zza(NativeAdOptionsParcel nativeAdOptionsParcel) {
        this.zzqL = nativeAdOptionsParcel;
    }

    public void zza(zzdt com_google_android_gms_internal_zzdt) {
        this.zzqT = com_google_android_gms_internal_zzdt;
    }

    public void zza(zzdu com_google_android_gms_internal_zzdu) {
        this.zzqU = com_google_android_gms_internal_zzdu;
    }

    public void zza(String str, zzdw com_google_android_gms_internal_zzdw, zzdv com_google_android_gms_internal_zzdv) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Custom template ID for native custom template ad is empty. Please provide a valid template id.");
        }
        this.zzqW.put(str, com_google_android_gms_internal_zzdw);
        this.zzqV.put(str, com_google_android_gms_internal_zzdv);
    }

    public void zzb(zzq com_google_android_gms_ads_internal_client_zzq) {
        this.zzqG = com_google_android_gms_ads_internal_client_zzq;
    }

    public void zzb(zzy com_google_android_gms_ads_internal_client_zzy) {
        this.zzqN = com_google_android_gms_ads_internal_client_zzy;
    }

    public zzr zzbM() {
        return new zzj(this.mContext, this.zzqO, this.zzqc, this.zzqP, this.zzqG, this.zzqT, this.zzqU, this.zzqW, this.zzqV, this.zzqL, this.zzqN, this.zzpY);
    }
}

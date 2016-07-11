package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzr.zza;
import com.google.android.gms.ads.internal.client.zzy;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.lt;
import com.google.android.gms.internal.zzdt;
import com.google.android.gms.internal.zzdu;
import com.google.android.gms.internal.zzdv;
import com.google.android.gms.internal.zzdw;
import com.google.android.gms.internal.zzga;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@jw
public class zzj extends zza {
    private final Context mContext;
    private final C1077a zzpY;
    private final Object zzpp;
    private final zzq zzqG;
    private final zzdt zzqH;
    private final zzdu zzqI;
    private final SimpleArrayMap<String, zzdw> zzqJ;
    private final SimpleArrayMap<String, zzdv> zzqK;
    private final NativeAdOptionsParcel zzqL;
    private final List<String> zzqM;
    private final zzy zzqN;
    private final String zzqO;
    private final VersionInfoParcel zzqP;
    private WeakReference<zzq> zzqQ;
    private final zzga zzqc;

    zzj(Context context, String str, zzga com_google_android_gms_internal_zzga, VersionInfoParcel versionInfoParcel, zzq com_google_android_gms_ads_internal_client_zzq, zzdt com_google_android_gms_internal_zzdt, zzdu com_google_android_gms_internal_zzdu, SimpleArrayMap<String, zzdw> simpleArrayMap, SimpleArrayMap<String, zzdv> simpleArrayMap2, NativeAdOptionsParcel nativeAdOptionsParcel, zzy com_google_android_gms_ads_internal_client_zzy, C1077a c1077a) {
        this.zzpp = new Object();
        this.mContext = context;
        this.zzqO = str;
        this.zzqc = com_google_android_gms_internal_zzga;
        this.zzqP = versionInfoParcel;
        this.zzqG = com_google_android_gms_ads_internal_client_zzq;
        this.zzqI = com_google_android_gms_internal_zzdu;
        this.zzqH = com_google_android_gms_internal_zzdt;
        this.zzqJ = simpleArrayMap;
        this.zzqK = simpleArrayMap2;
        this.zzqL = nativeAdOptionsParcel;
        this.zzqM = zzbK();
        this.zzqN = com_google_android_gms_ads_internal_client_zzy;
        this.zzpY = c1077a;
    }

    private List<String> zzbK() {
        List<String> arrayList = new ArrayList();
        if (this.zzqI != null) {
            arrayList.add("1");
        }
        if (this.zzqH != null) {
            arrayList.add("2");
        }
        if (this.zzqJ.size() > 0) {
            arrayList.add("3");
        }
        return arrayList;
    }

    public String getMediationAdapterClassName() {
        synchronized (this.zzpp) {
            if (this.zzqQ != null) {
                zzq com_google_android_gms_ads_internal_zzq = (zzq) this.zzqQ.get();
                String mediationAdapterClassName = com_google_android_gms_ads_internal_zzq != null ? com_google_android_gms_ads_internal_zzq.getMediationAdapterClassName() : null;
                return mediationAdapterClassName;
            }
            return null;
        }
    }

    public boolean isLoading() {
        synchronized (this.zzpp) {
            if (this.zzqQ != null) {
                zzq com_google_android_gms_ads_internal_zzq = (zzq) this.zzqQ.get();
                boolean isLoading = com_google_android_gms_ads_internal_zzq != null ? com_google_android_gms_ads_internal_zzq.isLoading() : false;
                return isLoading;
            }
            return false;
        }
    }

    protected void runOnUiThread(Runnable runnable) {
        lt.f5423a.post(runnable);
    }

    protected zzq zzbL() {
        return new zzq(this.mContext, this.zzpY, AdSizeParcel.zzk(this.mContext), this.zzqO, this.zzqc, this.zzqP);
    }

    public void zzf(AdRequestParcel adRequestParcel) {
        runOnUiThread(new 1(this, adRequestParcel));
    }
}

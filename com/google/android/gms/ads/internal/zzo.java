package com.google.android.gms.ads.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.client.zzz.zza;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.dz;
import com.google.android.gms.internal.jw;

@jw
public class zzo extends zza {
    private static final Object zzrs;
    private static zzo zzrt;
    private final Context mContext;
    private final Object zzpp;
    private boolean zzru;
    private boolean zzrv;
    private float zzrw;
    private VersionInfoParcel zzrx;

    static {
        zzrs = new Object();
    }

    zzo(Context context, VersionInfoParcel versionInfoParcel) {
        this.zzpp = new Object();
        this.zzrw = -1.0f;
        this.mContext = context;
        this.zzrx = versionInfoParcel;
        this.zzru = false;
    }

    public static zzo zza(Context context, VersionInfoParcel versionInfoParcel) {
        zzo com_google_android_gms_ads_internal_zzo;
        synchronized (zzrs) {
            if (zzrt == null) {
                zzrt = new zzo(context.getApplicationContext(), versionInfoParcel);
            }
            com_google_android_gms_ads_internal_zzo = zzrt;
        }
        return com_google_android_gms_ads_internal_zzo;
    }

    public static zzo zzbR() {
        zzo com_google_android_gms_ads_internal_zzo;
        synchronized (zzrs) {
            com_google_android_gms_ads_internal_zzo = zzrt;
        }
        return com_google_android_gms_ads_internal_zzo;
    }

    public void initialize() {
        synchronized (zzrs) {
            if (this.zzru) {
                C1129c.m6192d("Mobile ads is initialized already.");
                return;
            }
            this.zzru = true;
        }
    }

    public void setAppMuted(boolean z) {
        synchronized (this.zzpp) {
            this.zzrv = z;
        }
    }

    public void setAppVolume(float f) {
        synchronized (this.zzpp) {
            this.zzrw = f;
        }
    }

    public float zzbS() {
        float f;
        synchronized (this.zzpp) {
            f = this.zzrw;
        }
        return f;
    }

    public boolean zzbT() {
        boolean z;
        synchronized (this.zzpp) {
            z = this.zzrw >= 0.0f;
        }
        return z;
    }

    public boolean zzbU() {
        boolean z;
        synchronized (this.zzpp) {
            z = this.zzrv;
        }
        return z;
    }

    public void zzu(String str) {
        dz.m6444a(this.mContext);
        if (!TextUtils.isEmpty(str) && ((Boolean) dz.bs.m6433c()).booleanValue()) {
            C1101o.m6059w().m5964a(this.mContext, this.zzrx, true, null, str, null);
        }
    }
}

package com.google.android.gms.ads.internal.formats;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.zzdj;
import com.google.android.gms.internal.zzdn.zza;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

@jw
public class zzd extends zza implements e {
    private Bundle mExtras;
    private String zzAC;
    private List<zzc> zzAD;
    private String zzAE;
    private zzdj zzAF;
    private String zzAG;
    private double zzAH;
    private String zzAI;
    private String zzAJ;
    @Nullable
    private C1093a zzAK;
    private d zzAL;
    private Object zzpp;

    public zzd(String str, List list, String str2, zzdj com_google_android_gms_internal_zzdj, String str3, double d, String str4, String str5, @Nullable C1093a c1093a, Bundle bundle) {
        this.zzpp = new Object();
        this.zzAC = str;
        this.zzAD = list;
        this.zzAE = str2;
        this.zzAF = com_google_android_gms_internal_zzdj;
        this.zzAG = str3;
        this.zzAH = d;
        this.zzAI = str4;
        this.zzAJ = str5;
        this.zzAK = c1093a;
        this.mExtras = bundle;
    }

    public void destroy() {
        this.zzAC = null;
        this.zzAD = null;
        this.zzAE = null;
        this.zzAF = null;
        this.zzAG = null;
        this.zzAH = 0.0d;
        this.zzAI = null;
        this.zzAJ = null;
        this.zzAK = null;
        this.mExtras = null;
        this.zzpp = null;
        this.zzAL = null;
    }

    public String getBody() {
        return this.zzAE;
    }

    public String getCallToAction() {
        return this.zzAG;
    }

    public String getCustomTemplateId() {
        return StringUtils.EMPTY;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public String getHeadline() {
        return this.zzAC;
    }

    public List getImages() {
        return this.zzAD;
    }

    public String getPrice() {
        return this.zzAJ;
    }

    public double getStarRating() {
        return this.zzAH;
    }

    public String getStore() {
        return this.zzAI;
    }

    public void zzb(d dVar) {
        synchronized (this.zzpp) {
            this.zzAL = dVar;
        }
    }

    public zzdj zzeN() {
        return this.zzAF;
    }

    public com.google.android.gms.dynamic.zzd zzeO() {
        return zze.zzD(this.zzAL);
    }

    public String zzeP() {
        return "2";
    }

    public C1093a zzeQ() {
        return this.zzAK;
    }
}

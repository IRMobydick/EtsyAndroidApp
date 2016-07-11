package com.google.android.gms.ads.internal.formats;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.zzdj;
import com.google.android.gms.internal.zzdp.zza;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

@jw
public class zze extends zza implements e {
    private Bundle mExtras;
    private String zzAC;
    private List<zzc> zzAD;
    private String zzAE;
    private String zzAG;
    @Nullable
    private C1093a zzAK;
    private d zzAL;
    private zzdj zzAM;
    private String zzAN;
    private Object zzpp;

    public zze(String str, List list, String str2, zzdj com_google_android_gms_internal_zzdj, String str3, String str4, @Nullable C1093a c1093a, Bundle bundle) {
        this.zzpp = new Object();
        this.zzAC = str;
        this.zzAD = list;
        this.zzAE = str2;
        this.zzAM = com_google_android_gms_internal_zzdj;
        this.zzAG = str3;
        this.zzAN = str4;
        this.zzAK = c1093a;
        this.mExtras = bundle;
    }

    public void destroy() {
        this.zzAC = null;
        this.zzAD = null;
        this.zzAE = null;
        this.zzAM = null;
        this.zzAG = null;
        this.zzAN = null;
        this.zzAK = null;
        this.mExtras = null;
        this.zzpp = null;
        this.zzAL = null;
    }

    public String getAdvertiser() {
        return this.zzAN;
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

    public void zzb(d dVar) {
        synchronized (this.zzpp) {
            this.zzAL = dVar;
        }
    }

    public zzd zzeO() {
        return com.google.android.gms.dynamic.zze.zzD(this.zzAL);
    }

    public String zzeP() {
        return "1";
    }

    public C1093a zzeQ() {
        return this.zzAK;
    }

    public zzdj zzeR() {
        return this.zzAM;
    }
}

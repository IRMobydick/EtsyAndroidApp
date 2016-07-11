package com.google.android.gms.ads.internal.formats;

import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.zzdj;
import com.google.android.gms.internal.zzdr.zza;
import java.util.Arrays;
import java.util.List;

@jw
public class zzf extends zza implements e {
    private final C1093a zzAK;
    private d zzAL;
    private final String zzAO;
    private final SimpleArrayMap<String, zzc> zzAP;
    private final SimpleArrayMap<String, String> zzAQ;
    private final Object zzpp;

    public zzf(String str, SimpleArrayMap<String, zzc> simpleArrayMap, SimpleArrayMap<String, String> simpleArrayMap2, C1093a c1093a) {
        this.zzpp = new Object();
        this.zzAO = str;
        this.zzAP = simpleArrayMap;
        this.zzAQ = simpleArrayMap2;
        this.zzAK = c1093a;
    }

    public List<String> getAvailableAssetNames() {
        int i = 0;
        String[] strArr = new String[(this.zzAP.size() + this.zzAQ.size())];
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzAP.size(); i3++) {
            strArr[i2] = (String) this.zzAP.keyAt(i3);
            i2++;
        }
        while (i < this.zzAQ.size()) {
            strArr[i2] = (String) this.zzAQ.keyAt(i);
            i++;
            i2++;
        }
        return Arrays.asList(strArr);
    }

    public String getCustomTemplateId() {
        return this.zzAO;
    }

    public void performClick(String str) {
        synchronized (this.zzpp) {
            if (this.zzAL == null) {
                C1129c.m6188b("Attempt to call performClick before ad initialized.");
                return;
            }
            this.zzAL.a(str, null, null, null);
        }
    }

    public void recordImpression() {
        synchronized (this.zzpp) {
            if (this.zzAL == null) {
                C1129c.m6188b("Attempt to perform recordImpression before ad initialized.");
                return;
            }
            this.zzAL.a();
        }
    }

    public String zzT(String str) {
        return (String) this.zzAQ.get(str);
    }

    public zzdj zzU(String str) {
        return (zzdj) this.zzAP.get(str);
    }

    public void zzb(d dVar) {
        synchronized (this.zzpp) {
            this.zzAL = dVar;
        }
    }

    public String zzeP() {
        return "3";
    }

    public C1093a zzeQ() {
        return this.zzAK;
    }
}

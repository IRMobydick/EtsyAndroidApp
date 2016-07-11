package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.client.zzab.zza;
import com.google.android.gms.ads.internal.client.zzac;
import com.google.android.gms.common.util.zzf;
import java.util.HashMap;
import java.util.Map;

@jw
public class zzlf extends zza {
    private final no zzBb;
    private final float zzSP;
    private int zzSQ;
    private zzac zzSR;
    private boolean zzSS;
    private boolean zzST;
    private float zzSU;
    private final Object zzpp;
    private boolean zzps;

    public zzlf(no noVar, float f) {
        this.zzpp = new Object();
        this.zzps = true;
        this.zzBb = noVar;
        this.zzSP = f;
    }

    private void zzbc(String str) {
        zzc(str, null);
    }

    private void zzc(String str, @Nullable Map<String, String> map) {
        Map hashMap = map == null ? new HashMap() : new HashMap(map);
        hashMap.put("action", str);
        C1101o.m6041e().m7109a(new 1(this, hashMap));
    }

    private void zzi(int i, int i2) {
        C1101o.m6041e().m7109a(new 2(this, i, i2));
    }

    public int getPlaybackState() {
        int i;
        synchronized (this.zzpp) {
            i = this.zzSQ;
        }
        return i;
    }

    public boolean isMuted() {
        boolean z;
        synchronized (this.zzpp) {
            z = this.zzST;
        }
        return z;
    }

    public void pause() {
        zzbc("pause");
    }

    public void play() {
        zzbc("play");
    }

    public void zzL(boolean z) {
        synchronized (this.zzpp) {
            this.zzps = z;
        }
        zzc("initialState", zzf.zze("muteStart", z ? "1" : "0"));
    }

    public void zza(float f, int i, boolean z) {
        int i2;
        synchronized (this.zzpp) {
            this.zzSU = f;
            this.zzST = z;
            i2 = this.zzSQ;
            this.zzSQ = i;
        }
        zzi(i2, i);
    }

    public void zza(zzac com_google_android_gms_ads_internal_client_zzac) {
        synchronized (this.zzpp) {
            this.zzSR = com_google_android_gms_ads_internal_client_zzac;
        }
    }

    public float zzdT() {
        return this.zzSP;
    }

    public float zzdU() {
        float f;
        synchronized (this.zzpp) {
            f = this.zzSU;
        }
        return f;
    }

    public void zzl(boolean z) {
        zzbc(z ? "mute" : "unmute");
    }
}

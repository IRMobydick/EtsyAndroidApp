package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.internal.zzhu.zza;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;

@jw
public class jk extends zzhu {
    hi f5165g;
    protected hn f5166h;
    private zzga f5167i;
    private hk f5168j;
    private final ei f5169k;
    private final no f5170l;
    private boolean f5171m;

    jk(Context context, lc lcVar, zzga com_google_android_gms_internal_zzga, jh jhVar, ei eiVar, no noVar) {
        super(context, lcVar, jhVar);
        this.f5167i = com_google_android_gms_internal_zzga;
        this.f5168j = lcVar.f5354c;
        this.f5169k = eiVar;
        this.f5170l = noVar;
    }

    private static String m6825a(hn hnVar) {
        String str = hnVar.f5037b.f4990d;
        int b = m6829b(hnVar.f5036a);
        return new StringBuilder(String.valueOf(str).length() + 33).append(str).append(".").append(b).append(".").append(hnVar.f5042g).toString();
    }

    private static String m6826a(List<hn> list) {
        String str = StringUtils.EMPTY;
        if (list == null) {
            return str.toString();
        }
        String str2 = str;
        for (hn hnVar : list) {
            if (!(hnVar == null || hnVar.f5037b == null || TextUtils.isEmpty(hnVar.f5037b.f4990d))) {
                str2 = String.valueOf(str2);
                str = String.valueOf(m6825a(hnVar));
                str2 = new StringBuilder((String.valueOf(str2).length() + 1) + String.valueOf(str).length()).append(str2).append(str).append("_").toString();
            }
        }
        return str2.substring(0, Math.max(0, str2.length() - 1));
    }

    private void m6827a() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        lt.f5423a.post(new 1(this, countDownLatch));
        try {
            countDownLatch.await(10, TimeUnit.SECONDS);
            synchronized (this.d) {
                if (!this.f5171m) {
                    throw new zza("View could not be prepared", 0);
                } else if (this.f5170l.m7268r()) {
                    throw new zza("Assets not loaded, web view is destroyed", 0);
                }
            }
        } catch (InterruptedException e) {
            String valueOf = String.valueOf(e);
            throw new zza(new StringBuilder(String.valueOf(valueOf).length() + 38).append("Interrupted while waiting for latch : ").append(valueOf).toString(), 0);
        }
    }

    private static int m6829b(int i) {
        switch (i) {
            case StringUtils.INDEX_NOT_FOUND /*-1*/:
                return 4;
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                return 0;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return 1;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                return 2;
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                return 3;
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                return 5;
            default:
                return 6;
        }
    }

    protected lb m6830a(int i) {
        AdRequestInfoParcel adRequestInfoParcel = this.e.f5352a;
        return new lb(adRequestInfoParcel.zzLi, this.f5170l, this.f.zzEF, i, this.f.zzEG, this.f.zzLR, this.f.orientation, this.f.zzEL, adRequestInfoParcel.zzLl, this.f.zzLP, this.f5166h != null ? this.f5166h.f5037b : null, this.f5166h != null ? this.f5166h.f5038c : null, this.f5166h != null ? this.f5166h.f5039d : AdMobAdapter.class.getName(), this.f5168j, this.f5166h != null ? this.f5166h.f5040e : null, this.f.zzLQ, this.e.f5355d, this.f.zzLO, this.e.f5357f, this.f.zzLT, this.f.zzLU, this.e.f5359h, null, this.f.zzMf, this.f.zzMg, this.f.zzMh, this.f5168j != null ? this.f5168j.f5016n : false, this.f.zzMj, this.f5165g != null ? m6826a(this.f5165g.b()) : null, this.f.zzEI);
    }

    protected void m6831a(long j) {
        synchronized (this.d) {
            this.f5165g = m6832b(j);
        }
        List arrayList = new ArrayList(this.f5168j.f5003a);
        String str = "com.google.ads.mediation.admob.AdMobAdapter";
        Bundle bundle = this.e.f5352a.zzLi.zzuX.getBundle(str);
        int i = (bundle == null || !bundle.getBoolean("_skipMediation")) ? 0 : 1;
        if (i != 0) {
            ListIterator listIterator = arrayList.listIterator();
            while (listIterator.hasNext()) {
                if (!((hj) listIterator.next()).f4989c.contains(str)) {
                    listIterator.remove();
                }
            }
        }
        this.f5166h = this.f5165g.a(arrayList);
        switch (this.f5166h.f5036a) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                if (this.f5166h.f5037b != null && this.f5166h.f5037b.f4998l != null) {
                    m6827a();
                }
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                throw new zza("No fill from any mediation ad networks.", 3);
            default:
                throw new zza("Unexpected mediation result: " + this.f5166h.f5036a, 0);
        }
    }

    hi m6832b(long j) {
        if (this.f5168j.f5014l != -1) {
            return new hq(this.b, this.e.f5352a, this.f5167i, this.f5168j, this.f.zzvv, this.f.zzvx, j, ((Long) dz.aK.m6433c()).longValue(), 2);
        }
        return new hr(this.b, this.e.f5352a, this.f5167i, this.f5168j, this.f.zzvv, this.f.zzvx, j, ((Long) dz.aK.m6433c()).longValue(), this.f5169k);
    }

    public void onStop() {
        synchronized (this.d) {
            super.onStop();
            if (this.f5165g != null) {
                this.f5165g.a();
            }
        }
    }
}

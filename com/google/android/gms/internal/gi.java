package com.google.android.gms.internal;

import android.os.Handler;
import com.google.android.gms.ads.internal.client.zzp;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.reward.client.zzd;
import com.google.android.gms.ads.internal.zzl;
import java.util.LinkedList;
import java.util.List;

@jw
class gi {
    private final List<gj> f4957a;

    gi() {
        this.f4957a = new LinkedList();
    }

    void m6586a(zzl com_google_android_gms_ads_internal_zzl) {
        com_google_android_gms_ads_internal_zzl.zza((zzq) new 1(this));
        com_google_android_gms_ads_internal_zzl.zza((zzw) new 2(this));
        com_google_android_gms_ads_internal_zzl.zza((zzhh) new 3(this));
        com_google_android_gms_ads_internal_zzl.zza((zzdg) new 4(this));
        com_google_android_gms_ads_internal_zzl.zza((zzp) new 5(this));
        com_google_android_gms_ads_internal_zzl.zza((zzd) new 6(this));
    }

    void m6587a(gl glVar) {
        Handler handler = lt.f5423a;
        for (gj 7 : this.f4957a) {
            handler.post(new 7(this, 7, glVar));
        }
    }
}

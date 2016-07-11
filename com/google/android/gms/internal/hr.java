package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.ArrayList;
import java.util.List;

@jw
public class hr implements hi {
    private final AdRequestInfoParcel f5056a;
    private final zzga f5057b;
    private final Context f5058c;
    private final Object f5059d;
    private final hk f5060e;
    private final boolean f5061f;
    private final long f5062g;
    private final long f5063h;
    private final ei f5064i;
    private final boolean f5065j;
    private boolean f5066k;
    private hm f5067l;
    private List<hn> f5068m;

    public hr(Context context, AdRequestInfoParcel adRequestInfoParcel, zzga com_google_android_gms_internal_zzga, hk hkVar, boolean z, boolean z2, long j, long j2, ei eiVar) {
        this.f5059d = new Object();
        this.f5066k = false;
        this.f5068m = new ArrayList();
        this.f5058c = context;
        this.f5056a = adRequestInfoParcel;
        this.f5057b = com_google_android_gms_internal_zzga;
        this.f5060e = hkVar;
        this.f5061f = z;
        this.f5065j = z2;
        this.f5062g = j;
        this.f5063h = j2;
        this.f5064i = eiVar;
    }

    public hn m6700a(List<hj> list) {
        C1129c.m6185a("Starting mediation.");
        Iterable arrayList = new ArrayList();
        eg a = this.f5064i.m6473a();
        for (hj hjVar : list) {
            String str = "Trying mediation network: ";
            String valueOf = String.valueOf(hjVar.f4988b);
            C1129c.m6190c(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            for (String str2 : hjVar.f4989c) {
                eg a2 = this.f5064i.m6473a();
                synchronized (this.f5059d) {
                    if (this.f5066k) {
                        hn hnVar = new hn(-1);
                        return hnVar;
                    }
                    this.f5067l = new hm(this.f5058c, str2, this.f5057b, this.f5060e, hjVar, this.f5056a.zzLi, this.f5056a.zzsB, this.f5056a.zzsx, this.f5061f, this.f5065j, this.f5056a.zzsP, this.f5056a.zzsT);
                    hnVar = this.f5067l.m6682a(this.f5062g, this.f5063h);
                    this.f5068m.add(hnVar);
                    if (hnVar.f5036a == 0) {
                        C1129c.m6185a("Adapter succeeded.");
                        this.f5064i.m6477a("mediation_network_succeed", str2);
                        if (!arrayList.isEmpty()) {
                            this.f5064i.m6477a("mediation_networks_fail", TextUtils.join(",", arrayList));
                        }
                        this.f5064i.m6479a(a2, "mls");
                        this.f5064i.m6479a(a, "ttm");
                        return hnVar;
                    }
                    arrayList.add(str2);
                    this.f5064i.m6479a(a2, "mlf");
                    if (hnVar.f5038c != null) {
                        lt.f5423a.post(new 1(this, hnVar));
                    }
                }
            }
        }
        if (!arrayList.isEmpty()) {
            this.f5064i.m6477a("mediation_networks_fail", TextUtils.join(",", arrayList));
        }
        return new hn(1);
    }

    public void m6701a() {
        synchronized (this.f5059d) {
            this.f5066k = true;
            if (this.f5067l != null) {
                this.f5067l.m6684a();
            }
        }
    }

    public List<hn> m6702b() {
        return this.f5068m;
    }
}

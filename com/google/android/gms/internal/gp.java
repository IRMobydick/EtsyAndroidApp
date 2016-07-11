package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.common.internal.zzaa;
import java.util.Iterator;
import java.util.LinkedList;

@jw
class gp {
    private final LinkedList<gq> f4968a;
    private AdRequestParcel f4969b;
    private final String f4970c;
    private final int f4971d;
    private boolean f4972e;

    gp(AdRequestParcel adRequestParcel, String str, int i) {
        zzaa.zzz(adRequestParcel);
        zzaa.zzz(str);
        this.f4968a = new LinkedList();
        this.f4969b = adRequestParcel;
        this.f4970c = str;
        this.f4971d = i;
    }

    AdRequestParcel m6609a() {
        return this.f4969b;
    }

    gq m6610a(AdRequestParcel adRequestParcel) {
        if (adRequestParcel != null) {
            this.f4969b = adRequestParcel;
        }
        return (gq) this.f4968a.remove();
    }

    void m6611a(gh ghVar) {
        gq gqVar = new gq(this, ghVar);
        this.f4968a.add(gqVar);
        gqVar.a();
    }

    void m6612a(gh ghVar, AdRequestParcel adRequestParcel) {
        this.f4968a.add(new gq(this, ghVar, adRequestParcel));
    }

    int m6613b() {
        return this.f4971d;
    }

    String m6614c() {
        return this.f4970c;
    }

    int m6615d() {
        return this.f4968a.size();
    }

    int m6616e() {
        Iterator it = this.f4968a.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = ((gq) it.next()).e ? i + 1 : i;
        }
        return i;
    }

    void m6617f() {
        Iterator it = this.f4968a.iterator();
        while (it.hasNext()) {
            ((gq) it.next()).a();
        }
    }

    void m6618g() {
        this.f4972e = true;
    }

    boolean m6619h() {
        return this.f4972e;
    }
}

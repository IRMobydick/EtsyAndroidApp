package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.SystemClock;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

@jw
public class ld {
    private final li f5360a;
    private final LinkedList<le> f5361b;
    private final Object f5362c;
    private final String f5363d;
    private final String f5364e;
    private long f5365f;
    private long f5366g;
    private boolean f5367h;
    private long f5368i;
    private long f5369j;
    private long f5370k;
    private long f5371l;

    public ld(li liVar, String str, String str2) {
        this.f5362c = new Object();
        this.f5365f = -1;
        this.f5366g = -1;
        this.f5367h = false;
        this.f5368i = -1;
        this.f5369j = 0;
        this.f5370k = -1;
        this.f5371l = -1;
        this.f5360a = liVar;
        this.f5363d = str;
        this.f5364e = str2;
        this.f5361b = new LinkedList();
    }

    public ld(String str, String str2) {
        this(C1101o.m6044h(), str, str2);
    }

    public void m6990a() {
        synchronized (this.f5362c) {
            if (this.f5371l != -1 && this.f5366g == -1) {
                this.f5366g = SystemClock.elapsedRealtime();
                this.f5360a.m7017a(this);
            }
            this.f5360a.m7029d().m7048c();
        }
    }

    public void m6991a(long j) {
        synchronized (this.f5362c) {
            this.f5371l = j;
            if (this.f5371l != -1) {
                this.f5360a.m7017a(this);
            }
        }
    }

    public void m6992a(AdRequestParcel adRequestParcel) {
        synchronized (this.f5362c) {
            this.f5370k = SystemClock.elapsedRealtime();
            this.f5360a.m7029d().m7046a(adRequestParcel, this.f5370k);
        }
    }

    public void m6993a(boolean z) {
        synchronized (this.f5362c) {
            if (this.f5371l != -1) {
                this.f5368i = SystemClock.elapsedRealtime();
                if (!z) {
                    this.f5366g = this.f5368i;
                    this.f5360a.m7017a(this);
                }
            }
        }
    }

    public void m6994b() {
        synchronized (this.f5362c) {
            if (this.f5371l != -1) {
                le leVar = new le();
                leVar.m7001c();
                this.f5361b.add(leVar);
                this.f5369j++;
                this.f5360a.m7029d().m7047b();
                this.f5360a.m7017a(this);
            }
        }
    }

    public void m6995b(long j) {
        synchronized (this.f5362c) {
            if (this.f5371l != -1) {
                this.f5365f = j;
                this.f5360a.m7017a(this);
            }
        }
    }

    public void m6996b(boolean z) {
        synchronized (this.f5362c) {
            if (this.f5371l != -1) {
                this.f5367h = z;
                this.f5360a.m7017a(this);
            }
        }
    }

    public void m6997c() {
        synchronized (this.f5362c) {
            if (!(this.f5371l == -1 || this.f5361b.isEmpty())) {
                le leVar = (le) this.f5361b.getLast();
                if (leVar.m6999a() == -1) {
                    leVar.m7000b();
                    this.f5360a.m7017a(this);
                }
            }
        }
    }

    public Bundle m6998d() {
        Bundle bundle;
        synchronized (this.f5362c) {
            bundle = new Bundle();
            bundle.putString("seq_num", this.f5363d);
            bundle.putString("slotid", this.f5364e);
            bundle.putBoolean("ismediation", this.f5367h);
            bundle.putLong("treq", this.f5370k);
            bundle.putLong("tresponse", this.f5371l);
            bundle.putLong("timp", this.f5366g);
            bundle.putLong("tload", this.f5368i);
            bundle.putLong("pcc", this.f5369j);
            bundle.putLong("tfetch", this.f5365f);
            ArrayList arrayList = new ArrayList();
            Iterator it = this.f5361b.iterator();
            while (it.hasNext()) {
                arrayList.add(((le) it.next()).m7002d());
            }
            bundle.putParcelableArrayList("tclick", arrayList);
        }
        return bundle;
    }
}

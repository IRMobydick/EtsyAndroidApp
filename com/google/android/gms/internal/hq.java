package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@jw
public class hq implements hi {
    private final AdRequestInfoParcel f5043a;
    private final zzga f5044b;
    private final Context f5045c;
    private final hk f5046d;
    private final boolean f5047e;
    private final long f5048f;
    private final long f5049g;
    private final int f5050h;
    private final Object f5051i;
    private boolean f5052j;
    private final Map<mz<hn>, hm> f5053k;
    private final boolean f5054l;
    private List<hn> f5055m;

    public hq(Context context, AdRequestInfoParcel adRequestInfoParcel, zzga com_google_android_gms_internal_zzga, hk hkVar, boolean z, boolean z2, long j, long j2, int i) {
        this.f5051i = new Object();
        this.f5052j = false;
        this.f5053k = new HashMap();
        this.f5055m = new ArrayList();
        this.f5045c = context;
        this.f5043a = adRequestInfoParcel;
        this.f5044b = com_google_android_gms_internal_zzga;
        this.f5046d = hkVar;
        this.f5047e = z;
        this.f5054l = z2;
        this.f5048f = j;
        this.f5049g = j2;
        this.f5050h = i;
    }

    private void m6690a(mz<hn> mzVar) {
        lt.f5423a.post(new 2(this, mzVar));
    }

    private hn m6691b(List<mz<hn>> list) {
        Throwable e;
        synchronized (this.f5051i) {
            if (this.f5052j) {
                hn hnVar = new hn(-1);
                return hnVar;
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                mz mzVar = (mz) it.next();
                try {
                    hnVar = (hn) mzVar.get();
                    this.f5055m.add(hnVar);
                    if (hnVar != null && hnVar.f5036a == 0) {
                        m6690a(mzVar);
                        return hnVar;
                    }
                } catch (InterruptedException e2) {
                    e = e2;
                    C1129c.m6193d("Exception while processing an adapter; continuing with other adapters", e);
                } catch (ExecutionException e3) {
                    e = e3;
                    C1129c.m6193d("Exception while processing an adapter; continuing with other adapters", e);
                }
            }
            m6690a(null);
            return new hn(1);
        }
    }

    private hn m6694c(List<mz<hn>> list) {
        InterruptedException max;
        synchronized (this.f5051i) {
            if (this.f5052j) {
                hn hnVar = new hn(-1);
                return hnVar;
            }
            long j = -1;
            mz mzVar = null;
            hnVar = null;
            long j2 = this.f5046d.f5015m != -1 ? this.f5046d.f5015m : 10000;
            Iterator it = list.iterator();
            long j3 = j2;
            while (it.hasNext()) {
                hn hnVar2;
                zzgd com_google_android_gms_internal_zzgd;
                int zzfG;
                hn hnVar3;
                mz mzVar2;
                hn hnVar4;
                mz mzVar3 = (mz) it.next();
                long currentTimeMillis = C1101o.m6045i().currentTimeMillis();
                if (j3 == 0) {
                    try {
                        if (mzVar3.isDone()) {
                            hnVar2 = (hn) mzVar3.get();
                            this.f5055m.add(hnVar2);
                            if (hnVar2 != null && hnVar2.f5036a == 0) {
                                com_google_android_gms_internal_zzgd = hnVar2.f5041f;
                                if (com_google_android_gms_internal_zzgd != null && com_google_android_gms_internal_zzgd.zzfG() > j) {
                                    zzfG = com_google_android_gms_internal_zzgd.zzfG();
                                    hnVar3 = hnVar2;
                                    mzVar2 = mzVar3;
                                    hnVar4 = hnVar3;
                                    mzVar = mzVar2;
                                    hnVar3 = hnVar4;
                                    max = Math.max(j3 - (C1101o.m6045i().currentTimeMillis() - currentTimeMillis), 0);
                                    j = zzfG;
                                    hnVar = hnVar3;
                                    j3 = max;
                                }
                            }
                            hnVar4 = hnVar;
                            mzVar2 = mzVar;
                            zzfG = j;
                            mzVar = mzVar2;
                            hnVar3 = hnVar4;
                            max = Math.max(j3 - (C1101o.m6045i().currentTimeMillis() - currentTimeMillis), 0);
                            j = zzfG;
                            hnVar = hnVar3;
                            j3 = max;
                        }
                    } catch (InterruptedException e) {
                        max = e;
                        try {
                            C1129c.m6193d("Exception while processing an adapter; continuing with other adapters", max);
                            j3 = max;
                        } finally {
                            hnVar = j3 - (C1101o.m6045i().currentTimeMillis() - currentTimeMillis);
                            j = 0;
                            Math.max(hnVar, j);
                            j = j3;
                        }
                    } catch (ExecutionException e2) {
                        max = e2;
                        C1129c.m6193d("Exception while processing an adapter; continuing with other adapters", max);
                        j3 = max;
                    } catch (RemoteException e3) {
                        max = e3;
                        C1129c.m6193d("Exception while processing an adapter; continuing with other adapters", max);
                        j3 = max;
                    } catch (TimeoutException e4) {
                        max = e4;
                        C1129c.m6193d("Exception while processing an adapter; continuing with other adapters", max);
                        j3 = max;
                    }
                }
                hnVar2 = (hn) mzVar3.get(j3, TimeUnit.MILLISECONDS);
                this.f5055m.add(hnVar2);
                com_google_android_gms_internal_zzgd = hnVar2.f5041f;
                zzfG = com_google_android_gms_internal_zzgd.zzfG();
                hnVar3 = hnVar2;
                mzVar2 = mzVar3;
                hnVar4 = hnVar3;
                mzVar = mzVar2;
                hnVar3 = hnVar4;
                max = Math.max(j3 - (C1101o.m6045i().currentTimeMillis() - currentTimeMillis), 0);
                j = zzfG;
                hnVar = hnVar3;
                j3 = max;
            }
            m6690a(mzVar);
            return hnVar == null ? new hn(1) : hnVar;
        }
    }

    public hn m6697a(List<hj> list) {
        C1129c.m6185a("Starting mediation.");
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        List arrayList = new ArrayList();
        for (hj hjVar : list) {
            String str = "Trying mediation network: ";
            String valueOf = String.valueOf(hjVar.f4988b);
            C1129c.m6190c(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            for (String hmVar : hjVar.f4989c) {
                hm hmVar2 = new hm(this.f5045c, hmVar, this.f5044b, this.f5046d, hjVar, this.f5043a.zzLi, this.f5043a.zzsB, this.f5043a.zzsx, this.f5047e, this.f5054l, this.f5043a.zzsP, this.f5043a.zzsT);
                mz a = ls.m7072a(newCachedThreadPool, new 1(this, hmVar2));
                this.f5053k.put(a, hmVar2);
                arrayList.add(a);
            }
        }
        switch (this.f5050h) {
            case Task.NETWORK_STATE_ANY /*2*/:
                return m6694c(arrayList);
            default:
                return m6691b(arrayList);
        }
    }

    public void m6698a() {
        synchronized (this.f5051i) {
            this.f5052j = true;
            for (hm a : this.f5053k.values()) {
                a.m6684a();
            }
        }
    }

    public List<hn> m6699b() {
        return this.f5055m;
    }
}

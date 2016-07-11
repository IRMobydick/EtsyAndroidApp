package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.util.client.C1128a;
import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.Future;
import org.json.JSONObject;

@jw
public class ks extends ln implements kr {
    private final lc f5310a;
    private final Context f5311b;
    private final ArrayList<Future> f5312c;
    private final ArrayList<String> f5313d;
    private final HashSet<String> f5314e;
    private final Object f5315f;
    private final zziy f5316g;

    public ks(Context context, lc lcVar, zziy com_google_android_gms_internal_zziy) {
        this.f5312c = new ArrayList();
        this.f5313d = new ArrayList();
        this.f5314e = new HashSet();
        this.f5315f = new Object();
        this.f5311b = context;
        this.f5310a = lcVar;
        this.f5316g = com_google_android_gms_internal_zziy;
    }

    private lb m6976a() {
        return m6977a(3, null, null);
    }

    private lb m6977a(int i, @Nullable String str, @Nullable hj hjVar) {
        return new lb(this.f5310a.f5352a.zzLi, null, this.f5310a.f5353b.zzEF, i, this.f5310a.f5353b.zzEG, this.f5310a.f5353b.zzLR, this.f5310a.f5353b.orientation, this.f5310a.f5353b.zzEL, this.f5310a.f5352a.zzLl, this.f5310a.f5353b.zzLP, hjVar, null, str, this.f5310a.f5354c, null, this.f5310a.f5353b.zzLQ, this.f5310a.f5355d, this.f5310a.f5353b.zzLO, this.f5310a.f5357f, this.f5310a.f5353b.zzLT, this.f5310a.f5353b.zzLU, this.f5310a.f5359h, null, this.f5310a.f5353b.zzMf, this.f5310a.f5353b.zzMg, this.f5310a.f5353b.zzMh, this.f5310a.f5353b.zzMi, this.f5310a.f5353b.zzMj, null, this.f5310a.f5353b.zzEI);
    }

    private lb m6978a(String str, hj hjVar) {
        return m6977a(-2, str, hjVar);
    }

    private void m6980a(String str, String str2, String str3) {
        synchronized (this.f5315f) {
            kt zzaE = this.f5316g.zzaE(str);
            if (zzaE == null || zzaE.m6985b() == null || zzaE.m6984a() == null) {
                return;
            }
            this.f5312c.add((Future) m6981a(str, str2, str3, zzaE).zzhs());
            this.f5313d.add(str);
        }
    }

    protected ko m6981a(String str, String str2, String str3, kt ktVar) {
        return new ko(this.f5311b, str, str2, str3, this.f5310a, ktVar, this);
    }

    public void m6982a(String str) {
        synchronized (this.f5315f) {
            this.f5314e.add(str);
        }
    }

    public void m6983a(String str, int i) {
    }

    public void onStop() {
    }

    public void zzbQ() {
        for (hj hjVar : this.f5310a.f5354c.f5003a) {
            String str = hjVar.f4995i;
            for (String str2 : hjVar.f4989c) {
                String str22;
                if ("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(str22)) {
                    try {
                        str22 = new JSONObject(str).getString("class_name");
                    } catch (Throwable e) {
                        C1129c.m6189b("Unable to determine custom event class name, skipping...", e);
                    }
                }
                m6980a(str22, str, hjVar.f4987a);
            }
        }
        int i = 0;
        while (i < this.f5312c.size()) {
            try {
                ((Future) this.f5312c.get(i)).get();
                synchronized (this.f5315f) {
                    if (this.f5314e.contains(this.f5313d.get(i))) {
                        C1128a.f4666a.post(new 1(this, m6978a((String) this.f5313d.get(i), (hj) this.f5310a.f5354c.f5003a.get(i))));
                        return;
                    }
                    i++;
                }
            } catch (InterruptedException e2) {
            } catch (Exception e3) {
            }
        }
        C1128a.f4666a.post(new 2(this, m6976a()));
    }
}

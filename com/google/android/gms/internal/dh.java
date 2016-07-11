package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@jw
public class dh {
    private final Object f4765a;
    private int f4766b;
    private List<dg> f4767c;

    public dh() {
        this.f4765a = new Object();
        this.f4767c = new LinkedList();
    }

    public dg m6376a() {
        dg dgVar = null;
        synchronized (this.f4765a) {
            if (this.f4767c.size() == 0) {
                C1129c.m6185a("Queue empty");
                return null;
            } else if (this.f4767c.size() >= 2) {
                int i = RtlSpacingHelper.UNDEFINED;
                for (dg dgVar2 : this.f4767c) {
                    dg dgVar3;
                    int i2;
                    int h = dgVar2.m6374h();
                    if (h > i) {
                        int i3 = h;
                        dgVar3 = dgVar2;
                        i2 = i3;
                    } else {
                        i2 = i;
                        dgVar3 = dgVar;
                    }
                    i = i2;
                    dgVar = dgVar3;
                }
                this.f4767c.remove(dgVar);
                return dgVar;
            } else {
                dgVar2 = (dg) this.f4767c.get(0);
                dgVar2.m6370d();
                return dgVar2;
            }
        }
    }

    public boolean m6377a(dg dgVar) {
        boolean z;
        synchronized (this.f4765a) {
            if (this.f4767c.contains(dgVar)) {
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public boolean m6378b(dg dgVar) {
        boolean z;
        synchronized (this.f4765a) {
            Iterator it = this.f4767c.iterator();
            while (it.hasNext()) {
                dg dgVar2 = (dg) it.next();
                if (dgVar != dgVar2 && dgVar2.m6367b().equals(dgVar.m6367b())) {
                    it.remove();
                    z = true;
                    break;
                }
            }
            z = false;
        }
        return z;
    }

    public void m6379c(dg dgVar) {
        synchronized (this.f4765a) {
            if (this.f4767c.size() >= 10) {
                C1129c.m6185a("Queue is full, current size = " + this.f4767c.size());
                this.f4767c.remove(0);
            }
            int i = this.f4766b;
            this.f4766b = i + 1;
            dgVar.m6364a(i);
            this.f4767c.add(dgVar);
        }
    }
}

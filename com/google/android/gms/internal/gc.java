package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.C1101o;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@jw
public class gc implements Iterable<gb> {
    private final List<gb> f4945a;

    public gc() {
        this.f4945a = new LinkedList();
    }

    private gb m6558c(no noVar) {
        Iterator it = C1101o.m6060x().iterator();
        while (it.hasNext()) {
            gb gbVar = (gb) it.next();
            if (gbVar.f4942a == noVar) {
                return gbVar;
            }
        }
        return null;
    }

    public int m6559a() {
        return this.f4945a.size();
    }

    public void m6560a(gb gbVar) {
        this.f4945a.add(gbVar);
    }

    public boolean m6561a(no noVar) {
        gb c = m6558c(noVar);
        if (c == null) {
            return false;
        }
        c.f4943b.m6568a();
        return true;
    }

    public void m6562b(gb gbVar) {
        this.f4945a.remove(gbVar);
    }

    public boolean m6563b(no noVar) {
        return m6558c(noVar) != null;
    }

    public Iterator<gb> iterator() {
        return this.f4945a.iterator();
    }
}

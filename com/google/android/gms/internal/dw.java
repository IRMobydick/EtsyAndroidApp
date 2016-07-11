package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@jw
public class dw {
    private final Collection<dv> f4795a;
    private final Collection<dv<String>> f4796b;
    private final Collection<dv<String>> f4797c;

    public dw() {
        this.f4795a = new ArrayList();
        this.f4796b = new ArrayList();
        this.f4797c = new ArrayList();
    }

    public List<String> m6434a() {
        List<String> arrayList = new ArrayList();
        for (dv c : this.f4796b) {
            String str = (String) c.m6433c();
            if (str != null) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public void m6435a(dv dvVar) {
        this.f4795a.add(dvVar);
    }

    public List<String> m6436b() {
        List<String> a = m6434a();
        for (dv c : this.f4797c) {
            String str = (String) c.m6433c();
            if (str != null) {
                a.add(str);
            }
        }
        return a;
    }

    public void m6437b(dv<String> dvVar) {
        this.f4796b.add(dvVar);
    }

    public void m6438c(dv<String> dvVar) {
        this.f4797c.add(dvVar);
    }
}

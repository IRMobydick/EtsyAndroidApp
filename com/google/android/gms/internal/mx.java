package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@jw
public class mx {
    public static <A, B> mz<B> m7203a(mz<A> mzVar, my<A, B> myVar) {
        mv mvVar = new mv();
        mzVar.a(new 1(mvVar, myVar, mzVar));
        return mvVar;
    }

    public static <V> mz<List<V>> m7204a(List<mz<V>> list) {
        mv mvVar = new mv();
        int size = list.size();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((mz) it.next()).a(new 2(atomicInteger, size, mvVar, list));
        }
        return mvVar;
    }

    private static <V> List<V> m7206c(List<mz<V>> list) {
        List<V> arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Object obj = ((mz) it.next()).get();
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}

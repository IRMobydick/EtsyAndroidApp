package bo.app;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public final class gt {
    final go f642a;
    Executor f643b;
    public Executor f644c;
    public Executor f645d;
    public final Map<Integer, String> f646e;
    final AtomicBoolean f647f;
    final AtomicBoolean f648g;
    final AtomicBoolean f649h;
    final Object f650i;
    private final Map<String, ReentrantLock> f651j;

    gt(go goVar) {
        this.f646e = Collections.synchronizedMap(new HashMap());
        this.f651j = new WeakHashMap();
        this.f647f = new AtomicBoolean(false);
        this.f648g = new AtomicBoolean(false);
        this.f649h = new AtomicBoolean(false);
        this.f650i = new Object();
        this.f642a = goVar;
        this.f643b = goVar.f600g;
        this.f644c = goVar.f601h;
        this.f645d = Executors.newCachedThreadPool(gi.m439a(5, "uil-pool-d-"));
    }

    public final void m470a() {
        if (!this.f642a.f602i && ((ExecutorService) this.f643b).isShutdown()) {
            this.f643b = m467b();
        }
        if (!this.f642a.f603j && ((ExecutorService) this.f644c).isShutdown()) {
            this.f644c = m467b();
        }
    }

    private Executor m467b() {
        return gi.m438a(this.f642a.f604k, this.f642a.f605l, this.f642a.f606m);
    }

    final String m468a(ie ieVar) {
        return (String) this.f646e.get(Integer.valueOf(ieVar.m537f()));
    }

    public final void m471b(ie ieVar) {
        this.f646e.remove(Integer.valueOf(ieVar.m537f()));
    }

    public final ReentrantLock m469a(String str) {
        ReentrantLock reentrantLock = (ReentrantLock) this.f651j.get(str);
        if (reentrantLock != null) {
            return reentrantLock;
        }
        reentrantLock = new ReentrantLock();
        this.f651j.put(str, reentrantLock);
        return reentrantLock;
    }
}

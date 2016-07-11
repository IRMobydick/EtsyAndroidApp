package bo.app;

import com.appboy.Constants;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ct {
    public static final String f254a;
    public final ConcurrentLinkedQueue<cp> f255b;
    public final ConcurrentLinkedQueue<cp> f256c;
    public final cy f257d;
    public final double f258e;
    public volatile cn<Void> f259f;
    public volatile cn<Double> f260g;
    public volatile boolean f261h;
    public final Object f262i;

    static {
        f254a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, ct.class.getName()});
    }

    public ct(cy cyVar, double d) {
        this(cyVar, d, null, new cm(Collections.emptySet()), true, false, false);
    }

    public ct(cy cyVar, double d, Double d2, cm cmVar, boolean z, boolean z2, boolean z3) {
        boolean z4 = false;
        this.f261h = false;
        this.f262i = new Object();
        this.f257d = cyVar;
        this.f258e = d;
        if (!z) {
            z4 = true;
        }
        this.f259f = new cn(null, z4);
        this.f260g = new cn(d2, z3);
        this.f255b = new ConcurrentLinkedQueue(cmVar.f240a);
        this.f256c = new ConcurrentLinkedQueue();
        this.f261h = z2;
    }

    public final Double m134a() {
        Double d;
        synchronized (this.f262i) {
            d = (Double) this.f260g.m116a();
        }
        return d;
    }

    private cn<Double> m131h() {
        cn<Double> cnVar;
        synchronized (this.f262i) {
            cnVar = this.f260g;
        }
        return cnVar;
    }

    public final void m135a(Double d) {
        synchronized (this.f262i) {
            this.f260g.m117a(d);
        }
    }

    public final boolean m137b() {
        boolean z;
        synchronized (this.f262i) {
            z = this.f259f.f243b;
        }
        return z;
    }

    private cn<Void> m132i() {
        cn<Void> cnVar;
        synchronized (this.f262i) {
            cnVar = this.f259f;
        }
        return cnVar;
    }

    public final boolean m138c() {
        boolean z;
        synchronized (this.f262i) {
            z = this.f261h;
        }
        return z;
    }

    public final boolean m139d() {
        boolean z;
        synchronized (this.f262i) {
            z = this.f260g.f243b;
        }
        return z;
    }

    public final boolean m136a(cp cpVar) {
        synchronized (this.f262i) {
            this.f255b.add(cpVar);
        }
        return true;
    }

    public final cm m140e() {
        cm cmVar;
        synchronized (this.f262i) {
            Set hashSet = new HashSet();
            hashSet.addAll(this.f255b);
            hashSet.addAll(this.f256c);
            cmVar = new cm(hashSet);
        }
        return cmVar;
    }

    private ConcurrentLinkedQueue<cp> m133j() {
        ConcurrentLinkedQueue<cp> concurrentLinkedQueue;
        synchronized (this.f262i) {
            concurrentLinkedQueue = this.f255b;
        }
        return concurrentLinkedQueue;
    }

    public final co m141f() {
        co coVar;
        synchronized (this.f262i) {
            boolean a = m130a(m132i());
            if (a) {
                this.f259f.m118b();
            }
            Object obj = (this.f261h && m130a(m131h())) ? 1 : null;
            if (obj != null) {
                this.f260g.m118b();
            }
            coVar = new co(this, Double.valueOf(this.f258e), obj != null ? m134a() : null, new HashSet(m133j()), this.f257d, a);
            this.f256c.addAll(this.f255b);
            this.f255b.clear();
        }
        return coVar;
    }

    private static boolean m130a(cn cnVar) {
        return (cnVar == null || cnVar.f242a || cnVar.f243b) ? false : true;
    }

    public final boolean m142g() {
        boolean z;
        synchronized (this.f262i) {
            z = m139d() && m137b() && m140e().f240a.isEmpty();
        }
        return z;
    }
}

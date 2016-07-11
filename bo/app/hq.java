package bo.app;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReentrantLock;

abstract class hq implements Iterator<E> {
    hs<E> f730a;
    E f731b;
    final /* synthetic */ hp f732c;
    private hs<E> f733d;

    abstract hs<E> m512a();

    abstract hs<E> m513a(hs<E> hsVar);

    hq(hp hpVar) {
        this.f732c = hpVar;
        ReentrantLock reentrantLock = hpVar.f725c;
        reentrantLock.lock();
        try {
            this.f730a = m512a();
            this.f731b = this.f730a == null ? null : this.f730a.f735a;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m511b() {
        /*
        r5 = this;
        r0 = 0;
        r1 = r5.f732c;
        r3 = r1.f725c;
        r3.lock();
        r1 = r5.f730a;	 Catch:{ all -> 0x0030 }
        r2 = r1;
    L_0x000b:
        r1 = r5.m513a(r2);	 Catch:{ all -> 0x0030 }
        if (r1 != 0) goto L_0x001e;
    L_0x0011:
        r1 = r0;
    L_0x0012:
        r5.f730a = r1;	 Catch:{ all -> 0x0030 }
        r1 = r5.f730a;	 Catch:{ all -> 0x0030 }
        if (r1 != 0) goto L_0x002b;
    L_0x0018:
        r5.f731b = r0;	 Catch:{ all -> 0x0030 }
        r3.unlock();
        return;
    L_0x001e:
        r4 = r1.f735a;	 Catch:{ all -> 0x0030 }
        if (r4 != 0) goto L_0x0012;
    L_0x0022:
        if (r1 != r2) goto L_0x0029;
    L_0x0024:
        r1 = r5.m512a();	 Catch:{ all -> 0x0030 }
        goto L_0x0012;
    L_0x0029:
        r2 = r1;
        goto L_0x000b;
    L_0x002b:
        r0 = r5.f730a;	 Catch:{ all -> 0x0030 }
        r0 = r0.f735a;	 Catch:{ all -> 0x0030 }
        goto L_0x0018;
    L_0x0030:
        r0 = move-exception;
        r3.unlock();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: bo.app.hq.b():void");
    }

    public boolean hasNext() {
        return this.f730a != null;
    }

    public E next() {
        if (this.f730a == null) {
            throw new NoSuchElementException();
        }
        this.f733d = this.f730a;
        E e = this.f731b;
        m511b();
        return e;
    }

    public void remove() {
        hs hsVar = this.f733d;
        if (hsVar == null) {
            throw new IllegalStateException();
        }
        this.f733d = null;
        ReentrantLock reentrantLock = this.f732c.f725c;
        reentrantLock.lock();
        try {
            if (hsVar.f735a != null) {
                this.f732c.m509a(hsVar);
            }
            reentrantLock.unlock();
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }
}

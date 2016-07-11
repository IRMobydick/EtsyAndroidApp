package bo.app;

import com.foresee.sdk.configuration.MeasureConfiguration;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class hp<E> extends AbstractQueue<E> implements hm<E>, Serializable {
    transient hs<E> f723a;
    transient hs<E> f724b;
    final ReentrantLock f725c;
    private transient int f726d;
    private final int f727e;
    private final Condition f728f;
    private final Condition f729g;

    public hp() {
        this((byte) 0);
    }

    private hp(byte b) {
        this.f725c = new ReentrantLock();
        this.f728f = this.f725c.newCondition();
        this.f729g = this.f725c.newCondition();
        this.f727e = MeasureConfiguration.DISABLED;
    }

    private boolean m502b(hs<E> hsVar) {
        if (this.f726d >= this.f727e) {
            return false;
        }
        hs hsVar2 = this.f724b;
        hsVar.f736b = hsVar2;
        this.f724b = hsVar;
        if (this.f723a == null) {
            this.f723a = hsVar;
        } else {
            hsVar2.f737c = hsVar;
        }
        this.f726d++;
        this.f728f.signal();
        return true;
    }

    private E m501b() {
        hs hsVar = this.f723a;
        if (hsVar == null) {
            return null;
        }
        hs hsVar2 = hsVar.f737c;
        E e = hsVar.f735a;
        hsVar.f735a = null;
        hsVar.f737c = hsVar;
        this.f723a = hsVar2;
        if (hsVar2 == null) {
            this.f724b = null;
        } else {
            hsVar2.f736b = null;
        }
        this.f726d--;
        this.f729g.signal();
        return e;
    }

    final void m509a(hs<E> hsVar) {
        hs hsVar2 = hsVar.f736b;
        hs hsVar3 = hsVar.f737c;
        if (hsVar2 == null) {
            m501b();
        } else if (hsVar3 == null) {
            hsVar2 = this.f724b;
            if (hsVar2 != null) {
                hsVar3 = hsVar2.f736b;
                Object obj = hsVar2.f735a;
                hsVar2.f735a = null;
                hsVar2.f736b = hsVar2;
                this.f724b = hsVar3;
                if (hsVar3 == null) {
                    this.f723a = null;
                } else {
                    hsVar3.f737c = null;
                }
                this.f726d--;
                this.f729g.signal();
            }
        } else {
            hsVar2.f737c = hsVar3;
            hsVar3.f736b = hsVar2;
            hsVar.f735a = null;
            this.f726d--;
            this.f729g.signal();
        }
    }

    public final boolean m510a(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        hs hsVar = new hs(e);
        ReentrantLock reentrantLock = this.f725c;
        reentrantLock.lock();
        try {
            boolean z;
            if (this.f726d >= this.f727e) {
                z = false;
            } else {
                hs hsVar2 = this.f723a;
                hsVar.f737c = hsVar2;
                this.f723a = hsVar;
                if (this.f724b == null) {
                    this.f724b = hsVar;
                } else {
                    hsVar2.f736b = hsVar;
                }
                this.f726d++;
                this.f728f.signal();
                z = true;
            }
            reentrantLock.unlock();
            return z;
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }

    private boolean m503b(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        hs hsVar = new hs(e);
        ReentrantLock reentrantLock = this.f725c;
        reentrantLock.lock();
        try {
            boolean b = m502b(hsVar);
            return b;
        } finally {
            reentrantLock.unlock();
        }
    }

    private boolean m500a(E e, long j, TimeUnit timeUnit) {
        if (e == null) {
            throw new NullPointerException();
        }
        hs hsVar = new hs(e);
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.f725c;
        reentrantLock.lockInterruptibly();
        while (!m502b(hsVar)) {
            try {
                if (toNanos <= 0) {
                    return false;
                }
                toNanos = this.f729g.awaitNanos(toNanos);
            } finally {
                reentrantLock.unlock();
            }
        }
        reentrantLock.unlock();
        return true;
    }

    public final E m508a() {
        E c = m504c();
        if (c != null) {
            return c;
        }
        throw new NoSuchElementException();
    }

    private E m504c() {
        ReentrantLock reentrantLock = this.f725c;
        reentrantLock.lock();
        try {
            E b = m501b();
            return b;
        } finally {
            reentrantLock.unlock();
        }
    }

    private E m506d() {
        E b;
        ReentrantLock reentrantLock = this.f725c;
        reentrantLock.lock();
        while (true) {
            try {
                b = m501b();
                if (b != null) {
                    break;
                }
                this.f728f.await();
            } finally {
                reentrantLock.unlock();
            }
        }
        return b;
    }

    private E m499a(long j, TimeUnit timeUnit) {
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.f725c;
        reentrantLock.lockInterruptibly();
        long j2 = toNanos;
        while (true) {
            try {
                E b = m501b();
                if (b != null) {
                    reentrantLock.unlock();
                    return b;
                } else if (j2 <= 0) {
                    break;
                } else {
                    j2 = this.f728f.awaitNanos(j2);
                }
            } finally {
                reentrantLock.unlock();
            }
        }
        return null;
    }

    private E m507e() {
        ReentrantLock reentrantLock = this.f725c;
        reentrantLock.lock();
        try {
            E e = this.f723a == null ? null : this.f723a.f735a;
            reentrantLock.unlock();
            return e;
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }

    private boolean m505c(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.f725c;
        reentrantLock.lock();
        try {
            for (hs hsVar = this.f723a; hsVar != null; hsVar = hsVar.f737c) {
                if (obj.equals(hsVar.f735a)) {
                    m509a(hsVar);
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean add(E e) {
        if (m503b((Object) e)) {
            return true;
        }
        throw new IllegalStateException("Deque full");
    }

    public boolean offer(E e) {
        return m503b((Object) e);
    }

    public void put(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        hs hsVar = new hs(e);
        ReentrantLock reentrantLock = this.f725c;
        reentrantLock.lock();
        while (!m502b(hsVar)) {
            try {
                this.f729g.await();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public boolean offer(E e, long j, TimeUnit timeUnit) {
        return m500a(e, j, timeUnit);
    }

    public E remove() {
        return m508a();
    }

    public E poll() {
        return m504c();
    }

    public E take() {
        return m506d();
    }

    public E poll(long j, TimeUnit timeUnit) {
        return m499a(j, timeUnit);
    }

    public E element() {
        E e = m507e();
        if (e != null) {
            return e;
        }
        throw new NoSuchElementException();
    }

    public E peek() {
        return m507e();
    }

    public int remainingCapacity() {
        ReentrantLock reentrantLock = this.f725c;
        reentrantLock.lock();
        try {
            int i = this.f727e - this.f726d;
            return i;
        } finally {
            reentrantLock.unlock();
        }
    }

    public int drainTo(Collection<? super E> collection) {
        return drainTo(collection, MeasureConfiguration.DISABLED);
    }

    public int drainTo(Collection<? super E> collection, int i) {
        if (collection == null) {
            throw new NullPointerException();
        } else if (collection == this) {
            throw new IllegalArgumentException();
        } else {
            ReentrantLock reentrantLock = this.f725c;
            reentrantLock.lock();
            try {
                int min = Math.min(i, this.f726d);
                for (int i2 = 0; i2 < min; i2++) {
                    collection.add(this.f723a.f735a);
                    m501b();
                }
                return min;
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public boolean remove(Object obj) {
        return m505c(obj);
    }

    public int size() {
        ReentrantLock reentrantLock = this.f725c;
        reentrantLock.lock();
        try {
            int i = this.f726d;
            return i;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.f725c;
        reentrantLock.lock();
        try {
            for (hs hsVar = this.f723a; hsVar != null; hsVar = hsVar.f737c) {
                if (obj.equals(hsVar.f735a)) {
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public Object[] toArray() {
        ReentrantLock reentrantLock = this.f725c;
        reentrantLock.lock();
        try {
            Object[] objArr = new Object[this.f726d];
            int i = 0;
            hs hsVar = this.f723a;
            while (hsVar != null) {
                int i2 = i + 1;
                objArr[i] = hsVar.f735a;
                hsVar = hsVar.f737c;
                i = i2;
            }
            return objArr;
        } finally {
            reentrantLock.unlock();
        }
    }

    public <T> T[] toArray(T[] tArr) {
        ReentrantLock reentrantLock = this.f725c;
        reentrantLock.lock();
        try {
            if (tArr.length < this.f726d) {
                tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.f726d);
            }
            int i = 0;
            hs hsVar = this.f723a;
            while (hsVar != null) {
                int i2 = i + 1;
                tArr[i] = hsVar.f735a;
                hsVar = hsVar.f737c;
                i = i2;
            }
            if (tArr.length > i) {
                tArr[i] = null;
            }
            reentrantLock.unlock();
            return tArr;
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }

    public String toString() {
        ReentrantLock reentrantLock = this.f725c;
        reentrantLock.lock();
        try {
            String str;
            hs hsVar = this.f723a;
            if (hsVar == null) {
                str = "[]";
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append('[');
                hs hsVar2 = hsVar;
                while (true) {
                    Object obj = hsVar2.f735a;
                    if (obj == this) {
                        obj = "(this Collection)";
                    }
                    stringBuilder.append(obj);
                    hsVar = hsVar2.f737c;
                    if (hsVar == null) {
                        break;
                    }
                    stringBuilder.append(',').append(' ');
                    hsVar2 = hsVar;
                }
                str = stringBuilder.append(']').toString();
                reentrantLock.unlock();
            }
            return str;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void clear() {
        ReentrantLock reentrantLock = this.f725c;
        reentrantLock.lock();
        try {
            hs hsVar = this.f723a;
            while (hsVar != null) {
                hsVar.f735a = null;
                hs hsVar2 = hsVar.f737c;
                hsVar.f736b = null;
                hsVar.f737c = null;
                hsVar = hsVar2;
            }
            this.f724b = null;
            this.f723a = null;
            this.f726d = 0;
            this.f729g.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    public Iterator<E> iterator() {
        return new hr();
    }
}

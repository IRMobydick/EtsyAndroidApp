package bo.app;

import android.app.Activity;
import com.appboy.Constants;
import com.appboy.events.IEventSubscriber;
import com.appboy.support.AppboyLogger;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;

public final class az implements bc {
    private static final String f133g;
    public final ConcurrentMap<Activity, ConcurrentMap<Class, CopyOnWriteArraySet<IEventSubscriber>>> f134a;
    public final ConcurrentMap<Class, CopyOnWriteArraySet<IEventSubscriber>> f135b;
    public final ConcurrentMap<Class, CopyOnWriteArraySet<IEventSubscriber>> f136c;
    public final Object f137d;
    public final Object f138e;
    public final Object f139f;
    private final Executor f140h;

    static {
        f133g = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, az.class.getName()});
    }

    public az(Executor executor) {
        this.f134a = new ConcurrentHashMap();
        this.f135b = new ConcurrentHashMap();
        this.f136c = new ConcurrentHashMap();
        this.f137d = new Object();
        this.f138e = new Object();
        this.f139f = new Object();
        this.f140h = executor;
    }

    public final <T> boolean m35a(IEventSubscriber<T> iEventSubscriber, Class<T> cls) {
        boolean z = false;
        synchronized (this.f138e) {
            ConcurrentMap concurrentMap = this.f135b;
            if (iEventSubscriber == null) {
                String name = cls.getName();
                AppboyLogger.m664e(f133g, String.format("Error: Attempted to add a null subscriber for eventClass %s. This subscriber is being ignored. Please check your calling code to ensure that all potential subscriptions are valid.", new Object[]{name}));
            } else {
                CopyOnWriteArraySet copyOnWriteArraySet = (CopyOnWriteArraySet) concurrentMap.get(cls);
                if (copyOnWriteArraySet == null) {
                    CopyOnWriteArraySet copyOnWriteArraySet2 = new CopyOnWriteArraySet();
                    copyOnWriteArraySet = (CopyOnWriteArraySet) concurrentMap.putIfAbsent(cls, copyOnWriteArraySet2);
                    if (copyOnWriteArraySet == null) {
                        copyOnWriteArraySet = copyOnWriteArraySet2;
                    }
                }
                z = copyOnWriteArraySet.add(iEventSubscriber);
            }
        }
        return z;
    }

    public final <T> boolean m36b(IEventSubscriber<T> iEventSubscriber, Class<T> cls) {
        boolean z;
        synchronized (this.f138e) {
            CopyOnWriteArraySet copyOnWriteArraySet = (CopyOnWriteArraySet) this.f135b.get(cls);
            z = (copyOnWriteArraySet == null || iEventSubscriber == null || !copyOnWriteArraySet.remove(iEventSubscriber)) ? false : true;
        }
        return z;
    }

    public final <T> void m34a(T t, Class<T> cls) {
        Iterator it;
        String str = f133g;
        new StringBuilder().append(cls.getName()).append(" fired: ").append(t.toString());
        for (Entry entry : this.f134a.entrySet()) {
            CopyOnWriteArraySet copyOnWriteArraySet = (CopyOnWriteArraySet) ((ConcurrentMap) entry.getValue()).get(cls);
            if (!(copyOnWriteArraySet == null || copyOnWriteArraySet.isEmpty())) {
                ((Activity) entry.getKey()).runOnUiThread(new ba(this, cls, copyOnWriteArraySet, t));
            }
        }
        CopyOnWriteArraySet copyOnWriteArraySet2 = (CopyOnWriteArraySet) this.f135b.get(cls);
        if (copyOnWriteArraySet2 != null) {
            it = m33b((Class) cls, copyOnWriteArraySet2).iterator();
            while (it.hasNext()) {
                this.f140h.execute(new bb(this, (IEventSubscriber) it.next(), t));
            }
        }
        copyOnWriteArraySet2 = (CopyOnWriteArraySet) this.f136c.get(cls);
        if (copyOnWriteArraySet2 != null) {
            it = m33b((Class) cls, copyOnWriteArraySet2).iterator();
            while (it.hasNext()) {
                ((IEventSubscriber) it.next()).trigger(t);
            }
        }
    }

    private static <T> CopyOnWriteArraySet<IEventSubscriber<T>> m33b(Class<T> cls, CopyOnWriteArraySet<IEventSubscriber> copyOnWriteArraySet) {
        CopyOnWriteArraySet<IEventSubscriber<T>> copyOnWriteArraySet2 = copyOnWriteArraySet;
        String str = f133g;
        new StringBuilder("Triggering ").append(cls.getName()).append(" on ").append(copyOnWriteArraySet.size()).append(" subscribers.");
        return copyOnWriteArraySet2;
    }
}

package crittercism.android;

import crittercism.android.c.a;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

public final class ex {
    List f5580a;
    final Set f5581b;
    final Set f5582c;
    private Executor f5583d;

    private ex(Executor executor, List list, List list2) {
        this.f5580a = new LinkedList();
        this.f5581b = new HashSet();
        this.f5582c = new HashSet();
        this.f5583d = executor;
        m7491a(list);
        m7492b(list2);
    }

    public ex(Executor executor) {
        this(executor, new LinkedList(), new LinkedList());
    }

    public final void m7490a(ff ffVar) {
        synchronized (this.f5580a) {
            this.f5580a.add(ffVar);
        }
    }

    public final void m7491a(List list) {
        synchronized (this.f5581b) {
            this.f5581b.addAll(list);
            this.f5581b.remove(null);
        }
    }

    public final void m7492b(List list) {
        synchronized (this.f5582c) {
            this.f5582c.addAll(list);
            this.f5582c.remove(null);
        }
    }

    @Deprecated
    public final void m7488a(c cVar) {
        m7489a(cVar, a.e);
    }

    public final void m7489a(c cVar, a aVar) {
        if (!cVar.b) {
            cVar.b = true;
            cVar.c = aVar;
            this.f5583d.execute(new ey(this, cVar, (byte) 0));
        }
    }
}

package bo.app;

import com.appboy.events.IEventSubscriber;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Semaphore;

public final class ar implements IEventSubscriber<bd> {
    final /* synthetic */ Semaphore f123a;
    final /* synthetic */ ao f124b;

    public ar(ao aoVar) {
        this.f124b = aoVar;
        this.f123a = null;
    }

    public final /* synthetic */ void trigger(Object obj) {
        boolean z = false;
        bd bdVar = (bd) obj;
        if (bdVar.f149a.m219d().f303a != null) {
            z = true;
        }
        if (z) {
            this.f124b.f119k.m294a(true);
        }
        dh c = bdVar.f149a.m218c();
        if (c != null) {
            Iterable iterable = c.f320a;
            if (iterable == null) {
                iterable = Collections.emptySet();
            }
            for (co coVar : r0) {
                ct ctVar = coVar.f247a;
                synchronized (ctVar.f262i) {
                    Collection collection = coVar.m122a().f240a;
                    synchronized (ctVar.f262i) {
                        String str = ct.f254a;
                        String.format("Adding %s failed events back into session", new Object[]{Integer.valueOf(collection.size())});
                        ctVar.f256c.removeAll(collection);
                        ctVar.f255b.addAll(collection);
                    }
                    if (coVar.f249c) {
                        ctVar.f259f.m120d();
                    }
                    if (coVar.f248b != null) {
                        ctVar.f260g.m120d();
                    }
                }
                this.f124b.f112d.m614a(coVar.f247a);
            }
            if (this.f123a != null) {
                this.f123a.release();
            }
            if (c.f322c != null) {
                this.f124b.f116h.m250b(null);
            }
            if (c.f321b != null) {
                this.f124b.f118j.m250b(null);
            }
        }
    }
}

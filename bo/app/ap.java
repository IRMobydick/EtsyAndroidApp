package bo.app;

import com.appboy.events.IEventSubscriber;
import com.appboy.support.AppboyLogger;
import java.util.Collection;

public final class ap implements IEventSubscriber<be> {
    final /* synthetic */ ao f121a;

    public ap(ao aoVar) {
        this.f121a = aoVar;
    }

    public final /* synthetic */ void trigger(Object obj) {
        eb ebVar = ((be) obj).f150a;
        dh c = ebVar.m218c();
        if (c != null) {
            if (ebVar.m218c().f320a != null) {
                try {
                    for (co coVar : ebVar.m218c().f320a) {
                        ct ctVar = coVar.f247a;
                        synchronized (ctVar.f262i) {
                            Collection collection = coVar.m122a().f240a;
                            synchronized (ctVar.f262i) {
                                ctVar.f256c.removeAll(collection);
                            }
                            if (coVar.f249c) {
                                ctVar.f259f.m119c();
                            }
                            if (coVar.f248b != null) {
                                ctVar.f260g.m119c();
                            }
                        }
                        if (!coVar.f247a.m142g() || coVar.f247a.f257d == this.f121a.f113e.m80c() || coVar.f247a == bw.m65a().f193c) {
                            this.f121a.f111c.m258a(coVar.f247a);
                        } else {
                            ao.f109a;
                            String.format("Clearing fully dispatched sealed session %s", new Object[]{coVar.f247a.f257d.toString()});
                            this.f121a.f111c.m262c(coVar.f247a).get();
                        }
                    }
                } catch (Throwable e) {
                    AppboyLogger.m665e(ao.f109a, "Caught exception while trying to clear sealed sessions", e);
                }
            }
            di diVar = c.f322c;
            if (diVar != null) {
                this.f121a.f116h.m250b(diVar);
            }
            db dbVar = c.f321b;
            if (dbVar != null) {
                this.f121a.f118j.m250b(dbVar);
            }
        }
    }
}

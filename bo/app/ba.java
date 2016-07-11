package bo.app;

import com.appboy.events.IEventSubscriber;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

final class ba implements Runnable {
    final /* synthetic */ Class f142a;
    final /* synthetic */ CopyOnWriteArraySet f143b;
    final /* synthetic */ Object f144c;
    final /* synthetic */ az f145d;

    ba(az azVar, Class cls, CopyOnWriteArraySet copyOnWriteArraySet, Object obj) {
        this.f145d = azVar;
        this.f142a = cls;
        this.f143b = copyOnWriteArraySet;
        this.f144c = obj;
    }

    public final void run() {
        az azVar = this.f145d;
        Iterator it = az.m33b(this.f142a, this.f143b).iterator();
        while (it.hasNext()) {
            ((IEventSubscriber) it.next()).trigger(this.f144c);
        }
    }
}

package bo.app;

import com.appboy.events.IEventSubscriber;

final class bb implements Runnable {
    final /* synthetic */ IEventSubscriber f146a;
    final /* synthetic */ Object f147b;
    final /* synthetic */ az f148c;

    bb(az azVar, IEventSubscriber iEventSubscriber, Object obj) {
        this.f148c = azVar;
        this.f146a = iEventSubscriber;
        this.f147b = obj;
    }

    public final void run() {
        this.f146a.trigger(this.f147b);
    }
}

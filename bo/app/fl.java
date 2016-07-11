package bo.app;

import com.appboy.support.AppboyLogger;

public final class fl implements Runnable {
    final /* synthetic */ fk f466a;

    public fl(fk fkVar) {
        this.f466a = fkVar;
    }

    public final void run() {
        Object obj = null;
        try {
            C0350o b = this.f466a.f452d;
            az a = this.f466a.f451c;
            synchronized (b.f804e) {
                b.f807h = false;
                b.f806g.interrupt();
                b.f806g = null;
            }
            if (!b.f803d.f833a.isEmpty()) {
                obj = 1;
            }
            if (obj == null) {
                b.f803d.m640a(new dy(b.f801b.getBaseUrlForRequests(), ac.f18d));
            }
            try {
                b.f802c.m241b(b.f803d.m638a());
            } catch (InterruptedException e) {
                AppboyLogger.m670w(C0350o.f800a, "Self destruct sequence tried to perform its final flush, but was interrupted. This means that the previous user's data might not have all flushed to the server. Appboy will automatically flush any outstanding data the next time this user is switched to.");
            }
            synchronized (a.f138e) {
                a.f135b.clear();
            }
            synchronized (a.f139f) {
                a.f136c.clear();
            }
            synchronized (a.f137d) {
                a.f134a.clear();
            }
        } catch (Throwable e2) {
            AppboyLogger.m671w(fk.f448m, "Exception while shutting down dispatch manager. Continuing.", e2);
        }
        try {
            this.f466a.f463p.m635b();
        } catch (Throwable e22) {
            AppboyLogger.m671w(fk.f448m, "Exception while un-registering data refresh broadcast receivers. Continuing.", e22);
        }
        try {
            this.f466a.f462o.m290c().close();
        } catch (Throwable e222) {
            AppboyLogger.m671w(fk.f448m, "Exception while closing database. Continuing.", e222);
        }
    }
}

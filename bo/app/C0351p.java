package bo.app;

import com.appboy.support.AppboyLogger;

/* renamed from: bo.app.p */
final class C0351p implements Runnable {
    final /* synthetic */ C0350o f810a;

    private C0351p(C0350o c0350o) {
        this.f810a = c0350o;
    }

    public final void run() {
        while (this.f810a.f807h) {
            try {
                ec a = this.f810a.f803d.m638a();
                if (a.m221f() || this.f810a.f809j) {
                    this.f810a.f808i.m244c(a);
                } else {
                    this.f810a.f802c.m240a(a);
                }
            } catch (InterruptedException e) {
                AppboyLogger.m670w(C0350o.f800a, String.format("Automatic thread interrupted! [%s]", new Object[]{e.getMessage()}));
            }
        }
    }
}

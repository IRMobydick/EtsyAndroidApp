package bo.app;

import java.util.concurrent.Callable;

final class fs implements Callable<Void> {
    final /* synthetic */ fr f494a;

    fs(fr frVar) {
        this.f494a = frVar;
    }

    public final /* synthetic */ Object call() {
        return m398a();
    }

    private Void m398a() {
        synchronized (this.f494a) {
            if (this.f494a.f489m == null) {
            } else {
                this.f494a.m392g();
                this.f494a.m394h();
                if (this.f494a.m388e()) {
                    this.f494a.m384d();
                    this.f494a.f491o = 0;
                }
            }
        }
        return null;
    }
}

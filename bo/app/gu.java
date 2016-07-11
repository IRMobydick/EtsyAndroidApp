package bo.app;

import java.io.File;

public final class gu implements Runnable {
    final /* synthetic */ gw f652a;
    final /* synthetic */ gt f653b;

    public gu(gt gtVar, gw gwVar) {
        this.f653b = gtVar;
        this.f652a = gwVar;
    }

    public final void run() {
        File a = this.f653b.f642a.f608o.m366a(this.f652a.f663b);
        Object obj = (a == null || !a.exists()) ? null : 1;
        this.f653b.m470a();
        if (obj != null) {
            this.f653b.f644c.execute(this.f652a);
        } else {
            this.f653b.f643b.execute(this.f652a);
        }
    }
}

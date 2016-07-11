package bo.app;

import java.util.Collection;
import java.util.Iterator;

public final class bx implements Runnable {
    final /* synthetic */ ew f196a;
    final /* synthetic */ C0350o f197b;
    final /* synthetic */ bv f198c;
    final /* synthetic */ bw f199d;

    public bx(bw bwVar, ew ewVar, C0350o c0350o, bv bvVar) {
        this.f199d = bwVar;
        this.f196a = ewVar;
        this.f197b = c0350o;
        this.f198c = bvVar;
    }

    public final void run() {
        bw.f190a;
        Collection<ct> b = this.f196a.m255b();
        synchronized (this.f199d.f195e) {
            bw.m67a(this.f199d, b);
            if (this.f199d.f193c == null) {
                bw.f190a;
            } else if (this.f199d.f194d.size() != 0) {
                bw.f190a;
                Iterator it = this.f199d.f194d.iterator();
                while (it.hasNext()) {
                    cp cpVar = (cp) it.next();
                    this.f199d.f193c.m136a(cpVar);
                    this.f196a.m254a(this.f199d.f193c, cpVar);
                }
            }
            for (ct a : b) {
                this.f197b.m622a(a);
            }
            this.f198c.m60a(ac.f18d);
            this.f199d.f192b = true;
            bw.f190a;
        }
    }
}

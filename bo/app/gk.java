package bo.app;

import android.graphics.Bitmap;

final class gk implements Runnable {
    private final Bitmap f543a;
    private final String f544b;
    private final ie f545c;
    private final String f546d;
    private final hy f547e;
    private final ih f548f;
    private final gt f549g;
    private final hi f550h;

    public gk(Bitmap bitmap, gv gvVar, gt gtVar, hi hiVar) {
        this.f543a = bitmap;
        this.f544b = gvVar.f654a;
        this.f545c = gvVar.f656c;
        this.f546d = gvVar.f655b;
        this.f547e = gvVar.f658e.f567q;
        this.f548f = gvVar.f659f;
        this.f549g = gtVar;
        this.f550h = hiVar;
    }

    public final void run() {
        if (this.f545c.m536e()) {
            ip.m564a("ImageAware was collected by GC. Task is cancelled. [%s]", this.f546d);
            ih ihVar = this.f548f;
            String str = this.f544b;
            this.f545c.m535d();
            return;
        }
        int i;
        if (this.f546d.equals(this.f549g.m468a(this.f545c))) {
            i = 0;
        } else {
            i = 1;
        }
        if (i != 0) {
            ip.m564a("ImageAware is reused for another image. Task is cancelled. [%s]", this.f546d);
            ihVar = this.f548f;
            str = this.f544b;
            this.f545c.m535d();
            return;
        }
        ip.m564a("Display image in ImageAware (loaded from %1$s) [%2$s]", this.f550h, this.f546d);
        hy hyVar = this.f547e;
        Bitmap bitmap = this.f543a;
        ie ieVar = this.f545c;
        hi hiVar = this.f550h;
        hyVar.m521a(bitmap, ieVar);
        this.f549g.m471b(this.f545c);
        this.f548f.m146a(this.f544b, this.f545c.m535d(), this.f543a);
    }
}

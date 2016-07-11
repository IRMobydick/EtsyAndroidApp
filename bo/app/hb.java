package bo.app;

import android.graphics.Bitmap;
import android.os.Handler;

public final class hb implements Runnable {
    private final gt f689a;
    private final Bitmap f690b;
    private final gv f691c;
    private final Handler f692d;

    public hb(gt gtVar, Bitmap bitmap, gv gvVar, Handler handler) {
        this.f689a = gtVar;
        this.f690b = bitmap;
        this.f691c = gvVar;
        this.f692d = handler;
    }

    public final void run() {
        ip.m564a("PostProcess image before displaying [%s]", this.f691c.f655b);
        ik ikVar = this.f691c.f658e.f566p;
        Bitmap bitmap = this.f690b;
        gw.m475a(new gk(ikVar.m555a(), this.f691c, this.f689a, hi.MEMORY_CACHE), this.f691c.f658e.f569s, this.f692d, this.f689a);
    }
}

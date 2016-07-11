package bo.app;

import android.graphics.Bitmap;
import android.os.Handler;
import java.io.Closeable;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public final class gw implements io, Runnable {
    final go f662a;
    final String f663b;
    final ie f664c;
    final gl f665d;
    final ih f666e;
    final ii f667f;
    private final gt f668g;
    private final gv f669h;
    private final Handler f670i;
    private final ic f671j;
    private final ic f672k;
    private final ic f673l;
    private final hw f674m;
    private final String f675n;
    private final hh f676o;
    private final boolean f677p;
    private hi f678q;

    public gw(gt gtVar, gv gvVar, Handler handler) {
        this.f678q = hi.NETWORK;
        this.f668g = gtVar;
        this.f669h = gvVar;
        this.f670i = handler;
        this.f662a = gtVar.f642a;
        this.f671j = this.f662a.f609p;
        this.f672k = this.f662a.f612s;
        this.f673l = this.f662a.f613t;
        this.f674m = this.f662a.f610q;
        this.f663b = gvVar.f654a;
        this.f675n = gvVar.f655b;
        this.f664c = gvVar.f656c;
        this.f676o = gvVar.f657d;
        this.f665d = gvVar.f658e;
        this.f666e = gvVar.f659f;
        this.f667f = gvVar.f660g;
        this.f677p = this.f665d.f569s;
    }

    public final void run() {
        int i = 1;
        if (!m476a() && !m477b()) {
            ReentrantLock reentrantLock = this.f669h.f661h;
            ip.m564a("Start display image task [%s]", this.f675n);
            if (reentrantLock.isLocked()) {
                ip.m564a("Image already is loading. Waiting... [%s]", this.f675n);
            }
            reentrantLock.lock();
            try {
                m482g();
                Bitmap a = this.f662a.f607n.m423a(this.f675n);
                if (a == null || a.isRecycled()) {
                    a = m478c();
                    if (a == null) {
                        reentrantLock.unlock();
                        return;
                    }
                    m482g();
                    m486k();
                    if (this.f665d.f565o == null) {
                        i = 0;
                    }
                    if (i != 0) {
                        ip.m564a("PreProcess image before caching in memory [%s]", this.f675n);
                        a = this.f665d.f565o.m555a();
                        if (a == null) {
                            ip.m569d("Pre-processor returned null [%s]", this.f675n);
                        }
                    }
                    if (a != null && this.f665d.f558h) {
                        ip.m564a("Cache image in memory [%s]", this.f675n);
                        this.f662a.f607n.m425a(this.f675n, a);
                    }
                } else {
                    this.f678q = hi.MEMORY_CACHE;
                    ip.m564a("...Get cached bitmap from memory after waiting. [%s]", this.f675n);
                }
                if (a != null && this.f665d.m440a()) {
                    ip.m564a("PostProcess image before displaying [%s]", this.f675n);
                    a = this.f665d.f566p.m555a();
                    if (a == null) {
                        ip.m569d("Post-processor returned null [%s]", this.f675n);
                    }
                }
                m482g();
                m486k();
                reentrantLock.unlock();
                m475a(new gk(a, this.f669h, this.f668g, this.f678q), this.f677p, this.f670i, this.f668g);
            } catch (ha e) {
                if (!(this.f677p || m487l())) {
                    m475a(new gz(this), false, this.f670i, this.f668g);
                }
                reentrantLock.unlock();
            } catch (Throwable th) {
                reentrantLock.unlock();
            }
        }
    }

    private boolean m476a() {
        AtomicBoolean atomicBoolean = this.f668g.f647f;
        if (atomicBoolean.get()) {
            synchronized (this.f668g.f650i) {
                if (atomicBoolean.get()) {
                    ip.m564a("ImageLoader is paused. Waiting...  [%s]", this.f675n);
                    try {
                        this.f668g.f650i.wait();
                        ip.m564a(".. Resume loading [%s]", this.f675n);
                    } catch (InterruptedException e) {
                        ip.m569d("Task was interrupted [%s]", this.f675n);
                        return true;
                    }
                }
            }
        }
        return m483h();
    }

    private boolean m477b() {
        if (!(this.f665d.f562l > 0)) {
            return false;
        }
        ip.m564a("Delay %d ms before loading...  [%s]", Integer.valueOf(this.f665d.f562l), this.f675n);
        try {
            Thread.sleep((long) this.f665d.f562l);
            return m483h();
        } catch (InterruptedException e) {
            ip.m569d("Task was interrupted [%s]", this.f675n);
            return true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap m478c() {
        /*
        r7 = this;
        r1 = 0;
        r0 = r7.f662a;	 Catch:{ IllegalStateException -> 0x009e, ha -> 0x00a6, IOException -> 0x00a8, OutOfMemoryError -> 0x00b5, Throwable -> 0x00c2 }
        r0 = r0.f608o;	 Catch:{ IllegalStateException -> 0x009e, ha -> 0x00a6, IOException -> 0x00a8, OutOfMemoryError -> 0x00b5, Throwable -> 0x00c2 }
        r2 = r7.f663b;	 Catch:{ IllegalStateException -> 0x009e, ha -> 0x00a6, IOException -> 0x00a8, OutOfMemoryError -> 0x00b5, Throwable -> 0x00c2 }
        r0 = r0.m366a(r2);	 Catch:{ IllegalStateException -> 0x009e, ha -> 0x00a6, IOException -> 0x00a8, OutOfMemoryError -> 0x00b5, Throwable -> 0x00c2 }
        if (r0 == 0) goto L_0x00d7;
    L_0x000d:
        r2 = r0.exists();	 Catch:{ IllegalStateException -> 0x009e, ha -> 0x00a6, IOException -> 0x00a8, OutOfMemoryError -> 0x00b5, Throwable -> 0x00c2 }
        if (r2 == 0) goto L_0x00d7;
    L_0x0013:
        r2 = r0.length();	 Catch:{ IllegalStateException -> 0x009e, ha -> 0x00a6, IOException -> 0x00a8, OutOfMemoryError -> 0x00b5, Throwable -> 0x00c2 }
        r4 = 0;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 <= 0) goto L_0x00d7;
    L_0x001d:
        r2 = "Load image from disk cache [%s]";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ IllegalStateException -> 0x009e, ha -> 0x00a6, IOException -> 0x00a8, OutOfMemoryError -> 0x00b5, Throwable -> 0x00c2 }
        r4 = 0;
        r5 = r7.f675n;	 Catch:{ IllegalStateException -> 0x009e, ha -> 0x00a6, IOException -> 0x00a8, OutOfMemoryError -> 0x00b5, Throwable -> 0x00c2 }
        r3[r4] = r5;	 Catch:{ IllegalStateException -> 0x009e, ha -> 0x00a6, IOException -> 0x00a8, OutOfMemoryError -> 0x00b5, Throwable -> 0x00c2 }
        bo.app.ip.m564a(r2, r3);	 Catch:{ IllegalStateException -> 0x009e, ha -> 0x00a6, IOException -> 0x00a8, OutOfMemoryError -> 0x00b5, Throwable -> 0x00c2 }
        r2 = bo.app.hi.DISC_CACHE;	 Catch:{ IllegalStateException -> 0x009e, ha -> 0x00a6, IOException -> 0x00a8, OutOfMemoryError -> 0x00b5, Throwable -> 0x00c2 }
        r7.f678q = r2;	 Catch:{ IllegalStateException -> 0x009e, ha -> 0x00a6, IOException -> 0x00a8, OutOfMemoryError -> 0x00b5, Throwable -> 0x00c2 }
        r7.m482g();	 Catch:{ IllegalStateException -> 0x009e, ha -> 0x00a6, IOException -> 0x00a8, OutOfMemoryError -> 0x00b5, Throwable -> 0x00c2 }
        r2 = bo.app.id.FILE;	 Catch:{ IllegalStateException -> 0x009e, ha -> 0x00a6, IOException -> 0x00a8, OutOfMemoryError -> 0x00b5, Throwable -> 0x00c2 }
        r0 = r0.getAbsolutePath();	 Catch:{ IllegalStateException -> 0x009e, ha -> 0x00a6, IOException -> 0x00a8, OutOfMemoryError -> 0x00b5, Throwable -> 0x00c2 }
        r0 = r2.m528b(r0);	 Catch:{ IllegalStateException -> 0x009e, ha -> 0x00a6, IOException -> 0x00a8, OutOfMemoryError -> 0x00b5, Throwable -> 0x00c2 }
        r0 = r7.m473a(r0);	 Catch:{ IllegalStateException -> 0x009e, ha -> 0x00a6, IOException -> 0x00a8, OutOfMemoryError -> 0x00b5, Throwable -> 0x00c2 }
    L_0x003f:
        if (r0 == 0) goto L_0x004d;
    L_0x0041:
        r2 = r0.getWidth();	 Catch:{ IllegalStateException -> 0x00d5, ha -> 0x00a6, IOException -> 0x00d3, OutOfMemoryError -> 0x00d1, Throwable -> 0x00cf }
        if (r2 <= 0) goto L_0x004d;
    L_0x0047:
        r2 = r0.getHeight();	 Catch:{ IllegalStateException -> 0x00d5, ha -> 0x00a6, IOException -> 0x00d3, OutOfMemoryError -> 0x00d1, Throwable -> 0x00cf }
        if (r2 > 0) goto L_0x009d;
    L_0x004d:
        r2 = "Load image from network [%s]";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ IllegalStateException -> 0x00d5, ha -> 0x00a6, IOException -> 0x00d3, OutOfMemoryError -> 0x00d1, Throwable -> 0x00cf }
        r4 = 0;
        r5 = r7.f675n;	 Catch:{ IllegalStateException -> 0x00d5, ha -> 0x00a6, IOException -> 0x00d3, OutOfMemoryError -> 0x00d1, Throwable -> 0x00cf }
        r3[r4] = r5;	 Catch:{ IllegalStateException -> 0x00d5, ha -> 0x00a6, IOException -> 0x00d3, OutOfMemoryError -> 0x00d1, Throwable -> 0x00cf }
        bo.app.ip.m564a(r2, r3);	 Catch:{ IllegalStateException -> 0x00d5, ha -> 0x00a6, IOException -> 0x00d3, OutOfMemoryError -> 0x00d1, Throwable -> 0x00cf }
        r2 = bo.app.hi.NETWORK;	 Catch:{ IllegalStateException -> 0x00d5, ha -> 0x00a6, IOException -> 0x00d3, OutOfMemoryError -> 0x00d1, Throwable -> 0x00cf }
        r7.f678q = r2;	 Catch:{ IllegalStateException -> 0x00d5, ha -> 0x00a6, IOException -> 0x00d3, OutOfMemoryError -> 0x00d1, Throwable -> 0x00cf }
        r2 = r7.f663b;	 Catch:{ IllegalStateException -> 0x00d5, ha -> 0x00a6, IOException -> 0x00d3, OutOfMemoryError -> 0x00d1, Throwable -> 0x00cf }
        r3 = r7.f665d;	 Catch:{ IllegalStateException -> 0x00d5, ha -> 0x00a6, IOException -> 0x00d3, OutOfMemoryError -> 0x00d1, Throwable -> 0x00cf }
        r3 = r3.f559i;	 Catch:{ IllegalStateException -> 0x00d5, ha -> 0x00a6, IOException -> 0x00d3, OutOfMemoryError -> 0x00d1, Throwable -> 0x00cf }
        if (r3 == 0) goto L_0x0082;
    L_0x0066:
        r3 = r7.m479d();	 Catch:{ IllegalStateException -> 0x00d5, ha -> 0x00a6, IOException -> 0x00d3, OutOfMemoryError -> 0x00d1, Throwable -> 0x00cf }
        if (r3 == 0) goto L_0x0082;
    L_0x006c:
        r3 = r7.f662a;	 Catch:{ IllegalStateException -> 0x00d5, ha -> 0x00a6, IOException -> 0x00d3, OutOfMemoryError -> 0x00d1, Throwable -> 0x00cf }
        r3 = r3.f608o;	 Catch:{ IllegalStateException -> 0x00d5, ha -> 0x00a6, IOException -> 0x00d3, OutOfMemoryError -> 0x00d1, Throwable -> 0x00cf }
        r4 = r7.f663b;	 Catch:{ IllegalStateException -> 0x00d5, ha -> 0x00a6, IOException -> 0x00d3, OutOfMemoryError -> 0x00d1, Throwable -> 0x00cf }
        r3 = r3.m366a(r4);	 Catch:{ IllegalStateException -> 0x00d5, ha -> 0x00a6, IOException -> 0x00d3, OutOfMemoryError -> 0x00d1, Throwable -> 0x00cf }
        if (r3 == 0) goto L_0x0082;
    L_0x0078:
        r2 = bo.app.id.FILE;	 Catch:{ IllegalStateException -> 0x00d5, ha -> 0x00a6, IOException -> 0x00d3, OutOfMemoryError -> 0x00d1, Throwable -> 0x00cf }
        r3 = r3.getAbsolutePath();	 Catch:{ IllegalStateException -> 0x00d5, ha -> 0x00a6, IOException -> 0x00d3, OutOfMemoryError -> 0x00d1, Throwable -> 0x00cf }
        r2 = r2.m528b(r3);	 Catch:{ IllegalStateException -> 0x00d5, ha -> 0x00a6, IOException -> 0x00d3, OutOfMemoryError -> 0x00d1, Throwable -> 0x00cf }
    L_0x0082:
        r7.m482g();	 Catch:{ IllegalStateException -> 0x00d5, ha -> 0x00a6, IOException -> 0x00d3, OutOfMemoryError -> 0x00d1, Throwable -> 0x00cf }
        r0 = r7.m473a(r2);	 Catch:{ IllegalStateException -> 0x00d5, ha -> 0x00a6, IOException -> 0x00d3, OutOfMemoryError -> 0x00d1, Throwable -> 0x00cf }
        if (r0 == 0) goto L_0x0097;
    L_0x008b:
        r2 = r0.getWidth();	 Catch:{ IllegalStateException -> 0x00d5, ha -> 0x00a6, IOException -> 0x00d3, OutOfMemoryError -> 0x00d1, Throwable -> 0x00cf }
        if (r2 <= 0) goto L_0x0097;
    L_0x0091:
        r2 = r0.getHeight();	 Catch:{ IllegalStateException -> 0x00d5, ha -> 0x00a6, IOException -> 0x00d3, OutOfMemoryError -> 0x00d1, Throwable -> 0x00cf }
        if (r2 > 0) goto L_0x009d;
    L_0x0097:
        r2 = bo.app.he.f698b;	 Catch:{ IllegalStateException -> 0x00d5, ha -> 0x00a6, IOException -> 0x00d3, OutOfMemoryError -> 0x00d1, Throwable -> 0x00cf }
        r3 = 0;
        r7.m474a(r2, r3);	 Catch:{ IllegalStateException -> 0x00d5, ha -> 0x00a6, IOException -> 0x00d3, OutOfMemoryError -> 0x00d1, Throwable -> 0x00cf }
    L_0x009d:
        return r0;
    L_0x009e:
        r0 = move-exception;
        r0 = r1;
    L_0x00a0:
        r2 = bo.app.he.f699c;
        r7.m474a(r2, r1);
        goto L_0x009d;
    L_0x00a6:
        r0 = move-exception;
        throw r0;
    L_0x00a8:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x00ac:
        bo.app.ip.m565a(r1);
        r2 = bo.app.he.f697a;
        r7.m474a(r2, r1);
        goto L_0x009d;
    L_0x00b5:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x00b9:
        bo.app.ip.m565a(r1);
        r2 = bo.app.he.f700d;
        r7.m474a(r2, r1);
        goto L_0x009d;
    L_0x00c2:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x00c6:
        bo.app.ip.m565a(r1);
        r2 = bo.app.he.f701e;
        r7.m474a(r2, r1);
        goto L_0x009d;
    L_0x00cf:
        r1 = move-exception;
        goto L_0x00c6;
    L_0x00d1:
        r1 = move-exception;
        goto L_0x00b9;
    L_0x00d3:
        r1 = move-exception;
        goto L_0x00ac;
    L_0x00d5:
        r2 = move-exception;
        goto L_0x00a0;
    L_0x00d7:
        r0 = r1;
        goto L_0x003f;
        */
        throw new UnsupportedOperationException("Method not decompiled: bo.app.gw.c():android.graphics.Bitmap");
    }

    private Bitmap m473a(String str) {
        String str2 = str;
        return this.f674m.m516a(new hx(this.f675n, str2, this.f663b, this.f676o, this.f664c.m534c(), m481f(), this.f665d));
    }

    private boolean m479d() {
        ip.m564a("Cache image on disk [%s]", this.f675n);
        try {
            boolean e = m480e();
            if (e) {
                int i = this.f662a.f597d;
                int i2 = this.f662a.f598e;
                if (i > 0 || i2 > 0) {
                    ip.m564a("Resize image in disk cache [%s]", this.f675n);
                    File a = this.f662a.f608o.m366a(this.f663b);
                    if (a != null && a.exists()) {
                        hh hhVar = new hh(i, i2);
                        gm gmVar = new gm();
                        gl glVar = this.f665d;
                        gmVar.f570a = glVar.f551a;
                        gmVar.f571b = glVar.f552b;
                        gmVar.f572c = glVar.f553c;
                        gmVar.f573d = glVar.f554d;
                        gmVar.f574e = glVar.f555e;
                        gmVar.f575f = glVar.f556f;
                        gmVar.f576g = glVar.f557g;
                        gmVar.f577h = glVar.f558h;
                        gmVar.f578i = glVar.f559i;
                        gmVar.f579j = glVar.f560j;
                        gmVar.f580k = glVar.f561k;
                        gmVar.f581l = glVar.f562l;
                        gmVar.f582m = glVar.f563m;
                        gmVar.f583n = glVar.f564n;
                        gmVar.f584o = glVar.f565o;
                        gmVar.f585p = glVar.f566p;
                        gmVar.f586q = glVar.f567q;
                        gmVar.f587r = glVar.f568r;
                        gmVar.f588s = glVar.f569s;
                        gmVar.f579j = hg.f706d;
                        Bitmap a2 = this.f674m.m516a(new hx(this.f675n, id.FILE.m528b(a.getAbsolutePath()), this.f663b, hhVar, hk.f719a, m481f(), gmVar.m441a()));
                        if (!(a2 == null || this.f662a.f599f == null)) {
                            ip.m564a("Process image before cache on disk [%s]", this.f675n);
                            a2 = this.f662a.f599f.m555a();
                            if (a2 == null) {
                                ip.m569d("Bitmap processor for disk cache returned null [%s]", this.f675n);
                            }
                        }
                        if (a2 != null) {
                            this.f662a.f608o.m367a(this.f663b, a2);
                            a2.recycle();
                        }
                    }
                }
            }
            return e;
        } catch (Throwable e2) {
            ip.m565a(e2);
            return false;
        }
    }

    private boolean m480e() {
        boolean z = false;
        Closeable a = m481f().m464a(this.f663b, this.f665d.f564n);
        if (a == null) {
            ip.m569d("No stream for image [%s]", this.f675n);
        } else {
            try {
                z = this.f662a.f608o.m368a(this.f663b, a, this);
            } finally {
                in.m560a(a);
            }
        }
        return z;
    }

    public final boolean m488a(int i, int i2) {
        if (!this.f677p) {
            boolean z;
            if (m487l() || m483h()) {
                z = false;
            } else {
                if (this.f667f != null) {
                    m475a(new gx(this, i, i2), false, this.f670i, this.f668g);
                }
                z = true;
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    private void m474a(int i, Throwable th) {
        if (!this.f677p && !m487l() && !m483h()) {
            m475a(new gy(this, i, th), false, this.f670i, this.f668g);
        }
    }

    private ic m481f() {
        if (this.f668g.f648g.get()) {
            return this.f672k;
        }
        if (this.f668g.f649h.get()) {
            return this.f673l;
        }
        return this.f671j;
    }

    private void m482g() {
        if (m484i()) {
            throw new ha(this);
        } else if (m485j()) {
            throw new ha(this);
        }
    }

    private boolean m483h() {
        return m484i() || m485j();
    }

    private boolean m484i() {
        if (!this.f664c.m536e()) {
            return false;
        }
        ip.m564a("ImageAware was collected by GC. Task is cancelled. [%s]", this.f675n);
        return true;
    }

    private boolean m485j() {
        if (!(!this.f675n.equals(this.f668g.m468a(this.f664c)))) {
            return false;
        }
        ip.m564a("ImageAware is reused for another image. Task is cancelled. [%s]", this.f675n);
        return true;
    }

    private void m486k() {
        if (m487l()) {
            throw new ha(this);
        }
    }

    private boolean m487l() {
        if (!Thread.interrupted()) {
            return false;
        }
        ip.m564a("Task was interrupted [%s]", this.f675n);
        return true;
    }

    static void m475a(Runnable runnable, boolean z, Handler handler, gt gtVar) {
        if (z) {
            runnable.run();
        } else if (handler == null) {
            gtVar.f645d.execute(runnable);
        } else {
            handler.post(runnable);
        }
    }
}

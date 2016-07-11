package bo.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public final class fu {
    final fw f495a;
    final boolean[] f496b;
    boolean f497c;
    final /* synthetic */ fr f498d;
    private boolean f499e;

    private fu(fr frVar, fw fwVar) {
        this.f498d = frVar;
        this.f495a = fwVar;
        this.f496b = fwVar.f503c ? null : new boolean[frVar.f486j];
    }

    public final OutputStream m399a() {
        OutputStream a;
        synchronized (this.f498d) {
            File b;
            OutputStream fileOutputStream;
            if (this.f495a.f504d != this) {
                throw new IllegalStateException();
            }
            if (!this.f495a.f503c) {
                this.f496b[0] = true;
            }
            b = this.f495a.m406b(0);
            try {
                fileOutputStream = new FileOutputStream(b);
            } catch (FileNotFoundException e) {
                this.f498d.f479c.mkdirs();
                try {
                    fileOutputStream = new FileOutputStream(b);
                } catch (FileNotFoundException e2) {
                    a = fr.f477r;
                }
            }
            a = new fv(fileOutputStream, (byte) 0);
        }
        return a;
    }

    public final void m400b() {
        if (this.f497c) {
            this.f498d.m377a(this, false);
            this.f498d.m397c(this.f495a.f501a);
        } else {
            this.f498d.m377a(this, true);
        }
        this.f499e = true;
    }

    public final void m401c() {
        this.f498d.m377a(this, false);
    }
}

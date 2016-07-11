package bo.app;

import android.os.Handler;
import android.os.Looper;

public class gn {
    public static final String f589a;
    private static volatile gn f590e;
    public go f591b;
    public gt f592c;
    public ih f593d;

    static {
        f589a = gn.class.getSimpleName();
    }

    public static gn m443a() {
        if (f590e == null) {
            synchronized (gn.class) {
                if (f590e == null) {
                    f590e = new gn();
                }
            }
        }
        return f590e;
    }

    protected gn() {
        this.f593d = new ij();
    }

    public final synchronized void m444a(go goVar) {
        if (this.f591b == null) {
            ip.m564a("Initialize ImageLoader with configuration", new Object[0]);
            this.f592c = new gt(goVar);
            this.f591b = goVar;
        } else {
            ip.m568c("Try to initialize ImageLoader which had already been initialized before. To re-init ImageLoader with new configuration call ImageLoader.destroy() at first.", new Object[0]);
        }
    }

    public static Handler m442a(gl glVar) {
        Handler handler = glVar.f568r;
        if (glVar.f569s) {
            return null;
        }
        if (handler == null && Looper.myLooper() == Looper.getMainLooper()) {
            return new Handler();
        }
        return handler;
    }
}

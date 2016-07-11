package bo.app;

import com.appboy.Constants;
import com.appboy.support.AppboyLogger;

public class cn<T> {
    private static final String f241c;
    volatile boolean f242a;
    volatile boolean f243b;
    private T f244d;
    private final Object f245e;

    static {
        f241c = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, cn.class.getName()});
    }

    public cn(T t, boolean z) {
        this.f242a = false;
        this.f243b = false;
        this.f245e = new Object();
        this.f243b = z;
        this.f244d = t;
    }

    public final synchronized T m116a() {
        return this.f244d;
    }

    public final synchronized void m117a(T t) {
        this.f244d = t;
    }

    public final void m118b() {
        synchronized (this.f245e) {
            if (this.f242a) {
                AppboyLogger.m664e(f241c, "Warning: called dispatch() on field already in dispatch.  Please report this to Appboy.");
            }
            this.f242a = true;
        }
    }

    public final void m119c() {
        synchronized (this.f245e) {
            this.f242a = false;
            if (this.f243b) {
                AppboyLogger.m664e(f241c, "Erroneously got processSuccessfulDispatch call in DispatchOnceField with mHasSentSuccessfully already true. Please report this to Appboy.");
            }
            this.f243b = true;
        }
    }

    public final void m120d() {
        synchronized (this.f245e) {
            this.f242a = false;
            if (this.f243b) {
                AppboyLogger.m664e(f241c, "Erroneously got processFailedDispatch call in DispatchOnceField with mHasSentSuccessfully already true. Please report this to Appboy.");
            }
        }
    }
}

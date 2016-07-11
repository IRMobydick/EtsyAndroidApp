package bo.app;

import com.appboy.Constants;
import com.appboy.support.AppboyLogger;

public abstract class ek<T> {
    private static final String f388b;
    private final Object f389a;
    private boolean f390c;

    abstract T m247a();

    abstract void m248a(T t);

    public ek() {
        this.f389a = new Object();
        this.f390c = false;
    }

    static {
        f388b = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, ek.class.getName()});
    }

    public final T m249b() {
        T t;
        synchronized (this.f389a) {
            if (this.f390c) {
                AppboyLogger.m666i(f388b, "Received call to export dirty object, but the cache was already locked.");
                t = null;
            } else {
                this.f390c = true;
                t = m247a();
            }
        }
        return t;
    }

    public final boolean m250b(T t) {
        synchronized (this.f389a) {
            if (this.f390c) {
                m248a(t);
                this.f390c = false;
                return true;
            }
            AppboyLogger.m670w(f388b, String.format("Tried to confirm [%s], but the cache wasn't locked, so not doing anything.", new Object[]{String.valueOf(t)}));
            return false;
        }
    }
}

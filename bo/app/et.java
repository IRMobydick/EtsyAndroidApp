package bo.app;

import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import java.util.Collection;
import java.util.Collections;

public class et implements ew {
    private static final String f409a;
    private final ew f410b;
    private final bc f411c;

    static {
        f409a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, et.class.getName()});
    }

    public et(ew ewVar, bc bcVar) {
        this.f410b = ewVar;
        this.f411c = bcVar;
    }

    public final void m269a(ct ctVar) {
        try {
            this.f410b.m253a(ctVar);
        } catch (Throwable e) {
            AppboyLogger.m665e(f409a, "Failed to upsert active session in the storage.", e);
            m267a(this.f411c, e);
        }
    }

    public final ct m268a() {
        try {
            return this.f410b.m252a();
        } catch (Throwable e) {
            AppboyLogger.m665e(f409a, "Failed to get the active session from the storage.", e);
            m267a(this.f411c, e);
            return null;
        }
    }

    public final void m272b(ct ctVar) {
        try {
            this.f410b.m256b(ctVar);
        } catch (Throwable e) {
            AppboyLogger.m665e(f409a, "Failed to delete the sealed session from the storage.", e);
            m267a(this.f411c, e);
        }
    }

    public final Collection<ct> m271b() {
        try {
            return this.f410b.m255b();
        } catch (Throwable e) {
            AppboyLogger.m665e(f409a, "Failed to fetch all sealed sessions from the storage.", e);
            m267a(this.f411c, e);
            return Collections.EMPTY_LIST;
        }
    }

    public final void m270a(ct ctVar, cp cpVar) {
        try {
            this.f410b.m254a(ctVar, cpVar);
        } catch (Throwable e) {
            AppboyLogger.m665e(f409a, "Failed to add single event to session due to unexpected exception.", e);
            m267a(this.f411c, e);
        }
    }

    private static void m267a(bc bcVar, Throwable th) {
        try {
            bcVar.m31a(new bm("A database exception has occurred. Please view the stack trace for more details.", th), bm.class);
        } catch (Throwable e) {
            AppboyLogger.m665e(f409a, "Failed to log throwable.", e);
        }
    }
}

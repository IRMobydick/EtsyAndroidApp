package bo.app;

import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import java.lang.Thread.UncaughtExceptionHandler;

public class bp implements UncaughtExceptionHandler {
    private static final String f160a;
    private final bc f161b;

    static {
        f160a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, bp.class.getName()});
    }

    public bp(bc bcVar) {
        this.f161b = bcVar;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        try {
            AppboyLogger.m671w(f160a, "Uncaught exception from thread. Publishing as Throwable event", th);
            this.f161b.m31a(th, Throwable.class);
        } catch (Throwable e) {
            AppboyLogger.m671w(f160a, "Failed to log throwable.", e);
        }
    }
}

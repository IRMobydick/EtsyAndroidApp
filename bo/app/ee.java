package bo.app;

import com.appboy.Constants;
import com.appboy.support.AppboyLogger;

public class ee implements ed {
    private static final String f370a;
    private final bc f371b;

    static {
        f370a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, ee.class.getName()});
    }

    public ee(bc bcVar) {
        this.f371b = bcVar;
    }

    public final void m242a(ec ecVar) {
        m244c(ecVar);
    }

    public final void m243b(ec ecVar) {
        m244c(ecVar);
    }

    public final void m244c(ec ecVar) {
        AppboyLogger.m666i(f370a, "Short circuiting execution of network request and immediately marking it as succeeded.");
        ecVar.m204a(this.f371b);
        ecVar.m207b(this.f371b);
        if (ecVar instanceof eb) {
            this.f371b.m31a(new be((eb) ecVar), be.class);
        } else if (ecVar instanceof dr) {
            this.f371b.m31a(new C0337do((dr) ecVar, null), C0337do.class);
        }
    }
}

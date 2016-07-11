package bo.app;

import com.appboy.Constants;
import com.appboy.enums.ErrorType;
import com.appboy.models.ResponseError;
import com.appboy.support.AppboyLogger;
import com.google.android.gms.gcm.Task;
import java.net.URI;
import java.util.Map;

public final class ef implements Runnable {
    private static final String f372a;
    private final dr f373b;
    private final bc f374c;
    private final bc f375d;
    private final Map<String, String> f376e;
    private final C0342i f377f;

    static {
        f372a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, ef.class.getName()});
    }

    public ef(dr drVar, C0338j c0338j, C0342i c0342i, bc bcVar, bc bcVar2) {
        this.f373b = drVar;
        this.f374c = bcVar;
        this.f375d = bcVar2;
        this.f376e = c0338j.m214a();
        this.f377f = c0342i;
    }

    public final void run() {
        try {
            dt dtVar;
            URI a = ff.m336a(this.f373b.m209b());
            int[] iArr = eg.f378a;
            dr drVar = this.f373b;
            switch (iArr[ag.GET.ordinal()]) {
                case Task.NETWORK_STATE_UNMETERED /*1*/:
                    dtVar = new dt(this.f377f.m489a(a, this.f376e));
                    break;
                default:
                    String str = f372a;
                    Object[] objArr = new Object[1];
                    dr drVar2 = this.f373b;
                    objArr[0] = ag.GET;
                    AppboyLogger.m670w(str, String.format("Received a PlaceIQ request with an unknown Http verb: [%s]", objArr));
                    dtVar = null;
                    break;
            }
            dr drVar3;
            bc bcVar;
            if (dtVar != null) {
                dr drVar4 = this.f373b;
                bc bcVar2 = this.f375d;
                this.f374c.m31a(new C0337do(this.f373b, dtVar), C0337do.class);
                drVar3 = this.f373b;
                bcVar = this.f374c;
                return;
            }
            AppboyLogger.m670w(f372a, "Request response was null, failing task.");
            drVar3 = this.f373b;
            bcVar = this.f375d;
            ResponseError responseError = new ResponseError(ErrorType.UNRECOGNIZED_ERROR, "An error occurred during request processing, resulting in no valid response being received. Check the error log for more details.");
            this.f374c.m31a(new dn(this.f373b), dn.class);
        } catch (Throwable e) {
            AppboyLogger.m671w(f372a, "Experienced exception processing request response. Failing task.", e);
        }
    }
}

package bo.app;

import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/* renamed from: bo.app.x */
public final class C0360x implements C0349z {
    private static final String f832c;
    public final LinkedBlockingQueue<eb> f833a;
    public ce f834b;
    private final cg f835d;
    private final fa f836e;
    private final ConcurrentHashMap<String, ct> f837f;

    static {
        f832c = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, C0360x.class.getName()});
    }

    public C0360x(fa faVar, cg cgVar) {
        this.f836e = faVar;
        this.f835d = cgVar;
        this.f837f = new ConcurrentHashMap();
        this.f833a = new LinkedBlockingQueue(Constants.APPBOY_MINIMUM_NOTIFICATION_DURATION_MILLIS);
    }

    public final void m639a(ct ctVar) {
        if (ctVar == null) {
            throw new NullPointerException();
        }
        this.f837f.putIfAbsent(ctVar.f257d.toString(), ctVar);
    }

    public final void m640a(eb ebVar) {
        if (ebVar == null) {
            throw new NullPointerException();
        }
        AppboyLogger.m666i(f832c, String.format("Adding request to dispatcher with parameters: %s", new Object[]{String.valueOf(ebVar.m220e())}));
        this.f833a.add(ebVar);
    }

    public final eb m638a() {
        eb ebVar = (eb) this.f833a.take();
        try {
            if (this.f834b != null) {
                this.f834b.m58b();
            }
        } catch (Exception e) {
            String str = f832c;
        }
        return m637b(ebVar);
    }

    private synchronized eb m637b(eb ebVar) {
        if (ebVar == null) {
            ebVar = null;
        } else {
            String str = f832c;
            Collection<ct> values = this.f837f.values();
            List arrayList = new ArrayList();
            for (ct ctVar : values) {
                co f = ctVar.m141f();
                String str2 = f832c;
                f.m123b().toString();
                arrayList.add(f);
                values.remove(ctVar);
            }
            ebVar.m217a(new dh(arrayList, this.f835d.m92c(), (di) this.f836e.m249b()));
            if (this.f835d.m93d() != null) {
                ebVar.m216a(this.f835d.m93d().dispatch());
            }
        }
        return ebVar;
    }
}

package bo.app;

import com.appboy.Constants;
import com.appboy.configuration.XmlAppConfigurationProvider;
import java.util.concurrent.ThreadFactory;

/* renamed from: bo.app.o */
public class C0350o implements C0349z {
    public static final String f800a;
    public final XmlAppConfigurationProvider f801b;
    public final ed f802c;
    public final C0360x f803d;
    public final Object f804e;
    public volatile boolean f805f;
    public volatile Thread f806g;
    public volatile boolean f807h;
    private ee f808i;
    private boolean f809j;

    static {
        f800a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, C0350o.class.getName()});
    }

    public C0350o(XmlAppConfigurationProvider xmlAppConfigurationProvider, bc bcVar, ed edVar, C0360x c0360x, ThreadFactory threadFactory, boolean z) {
        this.f804e = new Object();
        this.f805f = false;
        this.f807h = true;
        this.f809j = false;
        this.f801b = xmlAppConfigurationProvider;
        this.f802c = edVar;
        this.f803d = c0360x;
        this.f806g = threadFactory.newThread(new C0351p());
        this.f808i = new ee(bcVar);
        this.f809j = z;
    }

    public final void m622a(ct ctVar) {
        this.f803d.m639a(ctVar);
    }

    public final void m623a(eb ebVar) {
        this.f803d.m640a(ebVar);
    }
}

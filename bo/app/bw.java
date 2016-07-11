package bo.app;

import com.appboy.Constants;
import java.util.Collection;
import java.util.HashSet;

public class bw {
    public static final String f190a;
    private static volatile bw f191f;
    public boolean f192b;
    public ct f193c;
    HashSet<cp> f194d;
    public final Object f195e;

    static /* synthetic */ void m67a(bw bwVar, Collection collection) {
        for (ct ctVar : collection) {
            if (bwVar.f193c == null) {
                bwVar.f193c = ctVar;
            } else if (bwVar.f193c.m134a().doubleValue() < ctVar.m134a().doubleValue()) {
                bwVar.f193c = ctVar;
            }
        }
        if (bwVar.f193c != null) {
            String str = f190a;
            new StringBuilder("Set fossil session with id: ").append(bwVar.f193c.f257d.toString());
        }
    }

    static {
        f190a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, bw.class.getName()});
        f191f = null;
    }

    public static bw m65a() {
        if (f191f == null) {
            synchronized (bw.class) {
                if (f191f == null) {
                    f191f = new bw();
                }
            }
        }
        return f191f;
    }

    private bw() {
        this.f192b = false;
        this.f195e = new Object();
        this.f194d = new HashSet();
    }
}

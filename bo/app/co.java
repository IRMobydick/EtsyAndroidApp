package bo.app;

import com.appboy.Constants;
import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import java.util.Collection;
import java.util.Set;
import org.json.JSONObject;

public class co implements cq, IPutIntoJson<JSONObject> {
    private static final String f246d;
    public final ct f247a;
    public volatile Double f248b;
    public final boolean f249c;
    private final Set<cp> f250e;
    private final cy f251f;
    private final double f252g;

    public /* synthetic */ Object forJsonPut() {
        return m123b();
    }

    static {
        f246d = String.format("%s.%s", new Object[]{Constants.APPBOY, co.class.getName()});
    }

    public co(ct ctVar, Double d, Double d2, Set<cp> set, cy cyVar, boolean z) {
        this.f247a = ctVar;
        this.f252g = d.doubleValue();
        this.f248b = d2;
        this.f250e = set;
        this.f251f = cyVar;
        this.f249c = z;
    }

    public final cm m122a() {
        return new cm(this.f250e);
    }

    public final JSONObject m123b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("guid", this.f251f.f279a.toString());
            jSONObject.put("start_time", this.f252g);
            if (!this.f250e.isEmpty()) {
                jSONObject.put("events", fg.m342a(this.f250e));
            }
            if (this.f249c) {
                jSONObject.put("new", true);
            }
            if (this.f248b != null) {
                jSONObject.put("end_time", this.f248b);
            }
        } catch (Throwable e) {
            AppboyLogger.m665e(f246d, "Caught exception creating dispatch session Json.", e);
        }
        return jSONObject;
    }

    public final boolean m124c() {
        Collection collection = m122a().f240a;
        return !this.f249c && this.f248b == null && (collection == null || collection.isEmpty());
    }
}

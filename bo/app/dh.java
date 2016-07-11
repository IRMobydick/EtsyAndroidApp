package bo.app;

import com.appboy.Constants;
import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public final class dh implements cq, IPutIntoJson<JSONObject> {
    private static final String f319d;
    public final List<co> f320a;
    public final db f321b;
    public final di f322c;

    public final /* synthetic */ Object forJsonPut() {
        return m191a();
    }

    static {
        f319d = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, dh.class.getName()});
    }

    public dh(List<co> list, db dbVar, di diVar) {
        this.f320a = list;
        this.f321b = dbVar;
        this.f322c = diVar;
    }

    public final JSONObject m191a() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!(this.f320a == null || this.f320a.isEmpty())) {
                jSONObject.put("sessions", fg.m342a(this.f320a));
            }
            if (this.f321b != null) {
                jSONObject.put("device", this.f321b.m183a());
            }
            if (this.f322c != null) {
                jSONObject.put(ActivityFeedEntity.USER, this.f322c.f323a);
            }
        } catch (Throwable e) {
            AppboyLogger.m665e(f319d, "Caught exception creating outbound extras Json.", e);
        }
        return jSONObject;
    }

    public final boolean m192c() {
        List<cq> arrayList = new ArrayList();
        if (this.f320a != null) {
            arrayList.addAll(this.f320a);
        }
        arrayList.add(this.f321b);
        arrayList.add(this.f322c);
        for (cq cqVar : arrayList) {
            if (cqVar != null && !cqVar.m121c()) {
                return false;
            }
        }
        return true;
    }
}

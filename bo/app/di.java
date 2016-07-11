package bo.app;

import com.appboy.models.IPutIntoJson;
import org.json.JSONObject;

public final class di implements cq, IPutIntoJson<JSONObject> {
    public final JSONObject f323a;

    public final /* bridge */ /* synthetic */ Object forJsonPut() {
        return this.f323a;
    }

    public di(JSONObject jSONObject) {
        this.f323a = jSONObject;
    }

    public final boolean m193c() {
        return this.f323a == null || this.f323a.length() == 0;
    }
}

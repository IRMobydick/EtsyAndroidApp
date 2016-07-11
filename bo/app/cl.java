package bo.app;

import org.json.JSONObject;

public final class cl {
    public final cu f238a;
    public final cw f239b;

    public cl(JSONObject jSONObject, ce ceVar) {
        JSONObject optJSONObject = jSONObject.optJSONObject("extras");
        if (optJSONObject != null) {
            this.f238a = new cu(optJSONObject, ceVar);
        } else {
            this.f238a = null;
        }
        optJSONObject = jSONObject.optJSONObject("result");
        if (optJSONObject != null) {
            this.f239b = new cw(optJSONObject);
        } else {
            this.f239b = null;
        }
    }
}

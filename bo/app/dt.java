package bo.app;

import org.json.JSONObject;

public final class dt {
    public final String f348a;

    public dt(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.f348a = jSONObject.optString("piqid", null);
        } else {
            this.f348a = null;
        }
    }
}

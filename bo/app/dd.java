package bo.app;

import com.appboy.Constants;
import com.appboy.models.IPutIntoJson;
import org.json.JSONException;
import org.json.JSONObject;

public class dd implements IPutIntoJson<JSONObject> {
    private static final String f299a;
    private final String f300b;
    private final String f301c;

    public /* synthetic */ Object forJsonPut() {
        return m186a();
    }

    static {
        f299a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, dd.class.getName()});
    }

    public static dd m185a(JSONObject jSONObject) {
        return new dd(fj.m355d(jSONObject.optString("serial")), fj.m355d(jSONObject.optString("android_id")));
    }

    public dd(String str, String str2) {
        this.f300b = str;
        this.f301c = str2;
    }

    public final JSONObject m186a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("serial", this.f300b);
            jSONObject.put("android_id", this.f301c);
        } catch (JSONException e) {
            String str = f299a;
        }
        return jSONObject;
    }
}

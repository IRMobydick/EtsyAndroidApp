package bo.app;

import com.appboy.Constants;
import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import org.json.JSONObject;

public class dl implements IPutIntoJson<JSONObject> {
    private static final String f331b;
    public final String f332a;

    public /* synthetic */ Object forJsonPut() {
        return m197a();
    }

    static {
        f331b = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, dl.class.getName()});
    }

    public static dl m196a(JSONObject jSONObject) {
        return new dl(fj.m355d(jSONObject.optString("android_id")));
    }

    private dl(String str) {
        this.f332a = str;
    }

    public final JSONObject m197a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("android_id", this.f332a);
        } catch (Throwable e) {
            AppboyLogger.m665e(f331b, "Caught exception creating wear device identifier Json.", e);
        }
        return jSONObject;
    }
}

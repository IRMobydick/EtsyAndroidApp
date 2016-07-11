package bo.app;

import com.appboy.Constants;
import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import org.json.JSONObject;

public class de implements IPutIntoJson<JSONObject> {
    private static final String f302b;
    public final String f303a;
    private final String f304c;
    private final int f305d;
    private final String f306e;
    private final String f307f;

    public /* synthetic */ Object forJsonPut() {
        return m187a();
    }

    static {
        f302b = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, de.class.getName()});
    }

    public de(String str, int i, String str2, String str3, String str4) {
        this.f304c = str;
        this.f305d = i;
        this.f306e = str2;
        this.f307f = str3;
        this.f303a = str4;
    }

    public final JSONObject m187a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sdk_version", this.f304c);
            jSONObject.put("now", fd.m334b());
            jSONObject.put("version_code", this.f305d);
            jSONObject.put("version_name", this.f306e);
            jSONObject.put("package_name", this.f307f);
            if (this.f303a != null) {
                jSONObject.put("config_time", this.f303a);
            }
            jSONObject.put("no_acks", true);
        } catch (Throwable e) {
            AppboyLogger.m665e(f302b, "Caught exception creating dispatch environment Json.", e);
        }
        return jSONObject;
    }
}

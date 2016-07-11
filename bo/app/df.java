package bo.app;

import com.appboy.Constants;
import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import org.json.JSONObject;

public class df implements IPutIntoJson<JSONObject> {
    private static final String f308a;
    private final int f309b;
    private final int f310c;
    private final float f311d;
    private final float f312e;
    private final int f313f;

    public /* synthetic */ Object forJsonPut() {
        return m189a();
    }

    static {
        f308a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, df.class.getName()});
    }

    public static df m188a(JSONObject jSONObject) {
        int optInt = jSONObject.optInt("resolution_width", -1);
        int optInt2 = jSONObject.optInt("resolution_height", -1);
        float optDouble = (float) jSONObject.optDouble("x_dpi", -1.0d);
        float optDouble2 = (float) jSONObject.optDouble("y_dpi", -1.0d);
        int optInt3 = jSONObject.optInt("density_default", -1);
        if (optInt == -1 || optInt2 == -1 || optDouble == -1.0f || optDouble2 == -1.0f || optInt3 == -1) {
            return null;
        }
        return new df(optInt, optInt2, optDouble, optDouble2, optInt3);
    }

    public df(int i, int i2, float f, float f2, int i3) {
        this.f309b = i;
        this.f310c = i2;
        this.f311d = f;
        this.f312e = f2;
        this.f313f = i3;
    }

    public JSONObject m189a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("resolution_height", this.f310c);
            jSONObject.put("resolution_width", this.f309b);
            jSONObject.put("x_dpi", (double) this.f311d);
            jSONObject.put("y_dpi", (double) this.f312e);
            jSONObject.put("density_default", this.f313f);
        } catch (Throwable e) {
            AppboyLogger.m665e(f308a, "Caught exception creating display Json.", e);
        }
        return jSONObject;
    }
}

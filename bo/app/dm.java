package bo.app;

import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import org.json.JSONObject;

public final class dm extends df {
    private static final String f333a;
    private final String f334b;

    public final /* synthetic */ Object forJsonPut() {
        return m199a();
    }

    static {
        f333a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, dm.class.getName()});
    }

    public static dm m198b(JSONObject jSONObject) {
        int optInt = jSONObject.optInt("resolution_width", -1);
        int optInt2 = jSONObject.optInt("resolution_height", -1);
        float optDouble = (float) jSONObject.optDouble("x_dpi", -1.0d);
        float optDouble2 = (float) jSONObject.optDouble("y_dpi", -1.0d);
        int optInt3 = jSONObject.optInt("density_default", -1);
        String optString = jSONObject.optString("screen_type", null);
        if (optInt == -1 || optInt2 == -1 || optDouble == -1.0f || optDouble2 == -1.0f || optInt3 == -1) {
            return null;
        }
        return new dm(optInt, optInt2, optDouble, optDouble2, optInt3, optString);
    }

    private dm(int i, int i2, float f, float f2, int i3, String str) {
        super(i, i2, f, f2, i3);
        this.f334b = str;
    }

    public final JSONObject m199a() {
        JSONObject a = super.m189a();
        try {
            if (this.f334b != null) {
                a.put("screen_type", this.f334b);
            }
        } catch (Throwable e) {
            AppboyLogger.m665e(f333a, "Caught exception creating wear display Json.", e);
        }
        return a;
    }
}

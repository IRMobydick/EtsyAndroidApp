package bo.app;

import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import com.etsy.android.lib.models.ResponseConstants;
import org.json.JSONObject;

public final class dg implements cr {
    private static final String f314a;
    private final double f315b;
    private final double f316c;
    private final Double f317d;
    private final Double f318e;

    public final /* synthetic */ Object forJsonPut() {
        return m190a();
    }

    static {
        f314a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, dg.class.getName()});
    }

    public dg(double d, double d2, Double d3, Double d4) {
        if (d > 90.0d || d < -90.0d || d2 > 180.0d || d2 < -180.0d) {
            throw new IllegalArgumentException("Unable to create Location. Latitude and longitude values are bounded by \u00b190 and \u00b1180 respectively");
        }
        this.f315b = d;
        this.f316c = d2;
        this.f317d = d3;
        this.f318e = d4;
    }

    public final JSONObject m190a() {
        Object obj = 1;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ResponseConstants.LATITUDE, this.f315b);
            jSONObject.put(ResponseConstants.LONGITUDE, this.f316c);
            if ((this.f317d != null ? 1 : null) != null) {
                jSONObject.put("altitude", this.f317d);
            }
            if (this.f318e == null) {
                obj = null;
            }
            if (obj != null) {
                jSONObject.put("ll_accuracy", this.f318e);
            }
        } catch (Throwable e) {
            AppboyLogger.m665e(f314a, "Caught exception creating location Json.", e);
        }
        return jSONObject;
    }
}

package bo.app;

import com.appboy.Constants;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class cx {
    public long f270a;
    public Set<String> f271b;
    public Set<String> f272c;
    public Set<String> f273d;
    public boolean f274e;
    public boolean f275f;
    public long f276g;
    public float f277h;
    public boolean f278i;

    public cx() {
        this.f274e = false;
        this.f275f = false;
        this.f276g = -1;
        this.f277h = -1.0f;
        this.f278i = false;
    }

    public cx(JSONObject jSONObject) {
        this.f274e = false;
        this.f275f = false;
        this.f276g = -1;
        this.f277h = -1.0f;
        this.f278i = false;
        this.f271b = m143a(jSONObject, "events_blacklist");
        this.f272c = m143a(jSONObject, "attributes_blacklist");
        this.f273d = m143a(jSONObject, "purchases_blacklist");
        this.f270a = jSONObject.optLong(Constants.APPBOY_LOCATION_TIME_INTERVAL_KEY, 0);
        JSONObject optJSONObject = jSONObject.optJSONObject(ResponseConstants.LOCATION);
        if (optJSONObject != null) {
            try {
                this.f275f = optJSONObject.getBoolean(ResponseConstants.ENABLED);
                this.f274e = true;
            } catch (JSONException e) {
                this.f274e = false;
            }
            long optLong = optJSONObject.optLong(Constants.APPBOY_LOCATION_TIME_INTERVAL_KEY, -1);
            if (optLong >= 0) {
                this.f276g = optLong * 1000;
            }
            this.f277h = (float) optJSONObject.optDouble(Constants.APPBOY_LOCATION_DISTANCE_INTERVAL_KEY, -1.0d);
            this.f278i = optJSONObject.optBoolean("piq_enabled", false);
        }
    }

    private static Set<String> m143a(JSONObject jSONObject, String str) {
        if (jSONObject.optJSONArray(str) == null) {
            return null;
        }
        Set<String> hashSet = new HashSet();
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        for (int i = 0; i < optJSONArray.length(); i++) {
            hashSet.add(optJSONArray.getString(i));
        }
        return hashSet;
    }
}

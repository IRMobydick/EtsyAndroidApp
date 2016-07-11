package bo.app;

import com.appboy.Constants;
import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class fg extends JSONObject {
    private static final String f446a;

    static {
        f446a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, fg.class.getName()});
    }

    public static <TargetEnum extends Enum<TargetEnum>> TargetEnum m338a(JSONObject jSONObject, String str, Class<TargetEnum> cls) {
        return Enum.valueOf(cls, jSONObject.getString(str).toUpperCase(Locale.US));
    }

    public static <TargetEnum extends Enum<TargetEnum>> TargetEnum m339a(JSONObject jSONObject, String str, Class<TargetEnum> cls, TargetEnum targetEnum) {
        try {
            targetEnum = m338a(jSONObject, str, cls);
        } catch (Exception e) {
        }
        return targetEnum;
    }

    public static <T> JSONArray m342a(Collection<? extends IPutIntoJson<T>> collection) {
        JSONArray jSONArray = new JSONArray();
        for (IPutIntoJson forJsonPut : collection) {
            jSONArray.put(forJsonPut.forJsonPut());
        }
        return jSONArray;
    }

    public static String m340a(JSONObject jSONObject, String str) {
        if (!jSONObject.has(str) || jSONObject.isNull(str)) {
            return null;
        }
        return jSONObject.optString(str, null);
    }

    public static Map<String, String> m341a(JSONObject jSONObject, Map<String, String> map) {
        String str;
        if (jSONObject == null) {
            str = f446a;
        } else {
            map = new HashMap();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                str = (String) keys.next();
                map.put(str, jSONObject.getString(str));
            }
        }
        return map;
    }

    public static JSONObject m343a(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            String str;
            JSONObject jSONObject3 = new JSONObject();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                str = (String) keys.next();
                jSONObject3.put(str, jSONObject.get(str));
            }
            keys = jSONObject2.keys();
            while (keys.hasNext()) {
                str = (String) keys.next();
                jSONObject3.put(str, jSONObject2.get(str));
            }
            return jSONObject3;
        } catch (Throwable e) {
            AppboyLogger.m665e(f446a, "Caught exception merging Json objects.", e);
            return null;
        }
    }
}

package bo.app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class iy {
    public static Object m579a(String str) {
        if (str.trim().startsWith("{")) {
            return new JSONObject(str);
        }
        if (str.trim().startsWith("[")) {
            return new JSONArray(str);
        }
        if (str.trim().startsWith("\"") || str.trim().matches("-?(?:0|[1-9]\\d*)(?:\\.\\d+)?(?:[eE][+-]?\\d+)?")) {
            return new iz(str);
        }
        throw new JSONException("Unparsable JSON string: " + str);
    }
}

package bo.app;

import org.json.JSONArray;
import org.json.JSONObject;

public final class jb extends ja {
    iw f790a;

    public jb(iw iwVar) {
        this.f790a = iwVar;
    }

    public final void m593a(String str, JSONObject jSONObject, JSONObject jSONObject2, ix ixVar) {
        for (String str2 : jd.m600a(jSONObject)) {
            Object obj = jSONObject.get(str2);
            if (jSONObject2.has(str2)) {
                m583a(jd.m595a(str, str2), obj, jSONObject2.get(str2), ixVar);
            } else {
                ixVar.m575a(str, str2);
            }
        }
        if (!this.f790a.f781e) {
            for (String str22 : jd.m600a(jSONObject2)) {
                if (!jSONObject.has(str22)) {
                    ixVar.m578b(str, str22);
                }
            }
        }
    }

    public final void m592a(String str, Object obj, Object obj2, ix ixVar) {
        if ((obj instanceof Number) && (obj2 instanceof Number)) {
            if (((Number) obj).doubleValue() != ((Number) obj2).doubleValue()) {
                ixVar.m576a(str, obj, obj2);
            }
        } else if (!obj.getClass().isAssignableFrom(obj2.getClass())) {
            ixVar.m576a(str, obj, obj2);
        } else if (obj instanceof JSONArray) {
            m594e(str, (JSONArray) obj, (JSONArray) obj2, ixVar);
        } else if (obj instanceof JSONObject) {
            m593a(str, (JSONObject) obj, (JSONObject) obj2, ixVar);
        } else if (!obj.equals(obj2)) {
            ixVar.m576a(str, obj, obj2);
        }
    }

    public final void m594e(String str, JSONArray jSONArray, JSONArray jSONArray2, ix ixVar) {
        if (jSONArray.length() != jSONArray2.length()) {
            ixVar.m577a(str + "[]: Expected " + jSONArray.length() + " values but got " + jSONArray2.length());
        } else if (jSONArray.length() == 0) {
        } else {
            if (this.f790a.f782f) {
                m590c(str, jSONArray, jSONArray2, ixVar);
            } else if (jd.m604c(jSONArray)) {
                ja.m586b(str, jSONArray, jSONArray2, ixVar);
            } else if (jd.m605d(jSONArray)) {
                m589a(str, jSONArray, jSONArray2, ixVar);
            } else {
                m591d(str, jSONArray, jSONArray2, ixVar);
            }
        }
    }
}

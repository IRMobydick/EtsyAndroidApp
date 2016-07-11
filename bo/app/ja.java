package bo.app;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class ja implements jc {
    public final ix m588a(JSONObject jSONObject, JSONObject jSONObject2) {
        ix ixVar = new ix();
        m584a(StringUtils.EMPTY, jSONObject, jSONObject2, ixVar);
        return ixVar;
    }

    public final ix m587a(JSONArray jSONArray, JSONArray jSONArray2) {
        ix ixVar = new ix();
        m585e(StringUtils.EMPTY, jSONArray, jSONArray2, ixVar);
        return ixVar;
    }

    protected final void m589a(String str, JSONArray jSONArray, JSONArray jSONArray2, ix ixVar) {
        String a = jd.m597a(jSONArray);
        if (a == null || !jd.m602a(a, jSONArray2)) {
            m591d(str, jSONArray, jSONArray2, ixVar);
            return;
        }
        Map a2 = jd.m599a(jSONArray, a);
        Map a3 = jd.m599a(jSONArray2, a);
        for (Object next : a2.keySet()) {
            if (a3.containsKey(next)) {
                m583a(jd.m596a(str, a, next), (Object) (JSONObject) a2.get(next), (Object) (JSONObject) a3.get(next), ixVar);
            } else {
                ixVar.m575a(jd.m596a(str, a, next), a2.get(next));
            }
        }
        for (Object next2 : a3.keySet()) {
            if (!a2.containsKey(next2)) {
                ixVar.m578b(jd.m596a(str, a, next2), a3.get(next2));
            }
        }
    }

    protected static void m586b(String str, JSONArray jSONArray, JSONArray jSONArray2, ix ixVar) {
        Map a = jd.m598a(jd.m603b(jSONArray));
        Map a2 = jd.m598a(jd.m603b(jSONArray2));
        for (Object next : a.keySet()) {
            if (!a2.containsKey(next)) {
                ixVar.m575a(str + "[]", next);
            } else if (!((Integer) a2.get(next)).equals(a.get(next))) {
                ixVar.m577a(str + "[]: Expected " + a.get(next) + " occurrence(s) of " + next + " but got " + a2.get(next) + " occurrence(s)");
            }
        }
        for (Object next2 : a2.keySet()) {
            if (!a.containsKey(next2)) {
                ixVar.m578b(str + "[]", next2);
            }
        }
    }

    protected final void m590c(String str, JSONArray jSONArray, JSONArray jSONArray2, ix ixVar) {
        for (int i = 0; i < jSONArray.length(); i++) {
            m583a(str + "[" + i + "]", jSONArray.get(i), jSONArray2.get(i), ixVar);
        }
    }

    protected final void m591d(String str, JSONArray jSONArray, JSONArray jSONArray2, ix ixVar) {
        Set hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj;
            Object obj2 = jSONArray.get(i);
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                Object obj3 = jSONArray2.get(i2);
                if (!hashSet.contains(Integer.valueOf(i2)) && obj3.getClass().equals(obj2.getClass())) {
                    if (obj2 instanceof JSONObject) {
                        if (m588a((JSONObject) obj2, (JSONObject) obj3).f783a) {
                            hashSet.add(Integer.valueOf(i2));
                            obj = 1;
                            break;
                        }
                    } else if (obj2 instanceof JSONArray) {
                        if (m587a((JSONArray) obj2, (JSONArray) obj3).f783a) {
                            hashSet.add(Integer.valueOf(i2));
                            r0 = 1;
                            break;
                        }
                    } else if (obj2.equals(obj3)) {
                        hashSet.add(Integer.valueOf(i2));
                        r0 = 1;
                        break;
                    }
                }
            }
            obj = null;
            if (obj == null) {
                ixVar.m577a(str + "[" + i + "] Could not find match for element " + obj2);
                return;
            }
        }
    }
}

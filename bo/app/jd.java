package bo.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public final class jd {
    private static Integer f791a;

    static {
        f791a = new Integer(1);
    }

    public static Map<Object, JSONObject> m599a(JSONArray jSONArray, String str) {
        Map<Object, JSONObject> hashMap = new HashMap();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = (JSONObject) jSONArray.get(i);
            hashMap.put(jSONObject.get(str), jSONObject);
        }
        return hashMap;
    }

    public static String m597a(JSONArray jSONArray) {
        for (String str : m600a((JSONObject) jSONArray.get(0))) {
            if (m602a(str, jSONArray)) {
                return str;
            }
        }
        return null;
    }

    public static boolean m602a(String str, JSONArray jSONArray) {
        Set hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (!(obj instanceof JSONObject)) {
                return false;
            }
            JSONObject jSONObject = (JSONObject) obj;
            if (!jSONObject.has(str)) {
                return false;
            }
            obj = jSONObject.get(str);
            if (!m601a(obj) || hashSet.contains(obj)) {
                return false;
            }
            hashSet.add(obj);
        }
        return true;
    }

    public static List<Object> m603b(JSONArray jSONArray) {
        List<Object> arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.get(i));
        }
        return arrayList;
    }

    public static boolean m604c(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            if (!m601a(jSONArray.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean m601a(Object obj) {
        return ((obj instanceof JSONObject) || (obj instanceof JSONArray)) ? false : true;
    }

    public static boolean m605d(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            if (!(jSONArray.get(i) instanceof JSONObject)) {
                return false;
            }
        }
        return true;
    }

    public static Set<String> m600a(JSONObject jSONObject) {
        Set<String> treeSet = new TreeSet();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            treeSet.add((String) keys.next());
        }
        return treeSet;
    }

    public static String m595a(String str, String str2) {
        return StringUtils.EMPTY.equals(str) ? str2 : str + "." + str2;
    }

    public static String m596a(String str, String str2, Object obj) {
        return str + "[" + str2 + "=" + obj + "]";
    }

    public static <T> Map<T, Integer> m598a(Collection<T> collection) {
        Map<T, Integer> hashMap = new HashMap();
        for (Object next : collection) {
            Integer num = (Integer) hashMap.get(next);
            if (num == null) {
                hashMap.put(next, f791a);
            } else {
                hashMap.put(next, new Integer(num.intValue() + 1));
            }
        }
        return hashMap;
    }
}

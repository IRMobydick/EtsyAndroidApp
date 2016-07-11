package bo.app;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public final class iv {
    public static ix m573a(String str, String str2, iw iwVar) {
        jc jbVar = new jb(iwVar);
        Object a = iy.m579a(str);
        Object a2 = iy.m579a(str2);
        if ((a instanceof JSONObject) && (a2 instanceof JSONObject)) {
            return jbVar.m582a((JSONObject) a, (JSONObject) a2);
        }
        if ((a instanceof JSONArray) && (a2 instanceof JSONArray)) {
            return jbVar.m581a((JSONArray) a, (JSONArray) a2);
        }
        if (!(a instanceof it) || !(a2 instanceof it)) {
            return a instanceof JSONObject ? new ix().m576a(StringUtils.EMPTY, a, a2) : new ix().m576a(StringUtils.EMPTY, a, a2);
        } else {
            it itVar = (it) a;
            it itVar2 = (it) a2;
            ix ixVar = new ix();
            if (!itVar.m572a().equals(itVar2.m572a())) {
                ixVar.m577a(StringUtils.EMPTY);
            }
            return ixVar;
        }
    }
}

package bo.app;

import com.appboy.Constants;
import com.etsy.android.lib.models.ShopAboutVideo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

/* renamed from: bo.app.k */
public final class C0345k implements C0342i {
    private static final String f792a;
    private final C0342i f793b;

    static {
        f792a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, C0345k.class.getName()});
    }

    public C0345k(C0342i c0342i) {
        this.f793b = c0342i;
    }

    public final JSONObject m608a(URI uri, Map<String, String> map) {
        String str;
        try {
            str = f792a;
            String.format("Making request to [%s], with headers: [%s]", new Object[]{uri.toString(), C0345k.m606a((Map) map)});
        } catch (Exception e) {
            str = f792a;
        }
        JSONObject a = this.f793b.m489a(uri, map);
        C0345k.m607a(a);
        return a;
    }

    public final JSONObject m609a(URI uri, Map<String, String> map, JSONObject jSONObject) {
        String str;
        try {
            str = f792a;
            String.format("Making request to [%s], with headers: [%s] and JSON parameters: [%s]", new Object[]{uri.toString(), C0345k.m606a((Map) map), jSONObject.toString()});
        } catch (Exception e) {
            str = f792a;
        }
        JSONObject a = this.f793b.m490a(uri, map, jSONObject);
        C0345k.m607a(a);
        return a;
    }

    private static void m607a(JSONObject jSONObject) {
        String str;
        try {
            str = f792a;
            String str2 = "Result [%s]";
            Object[] objArr = new Object[1];
            objArr[0] = jSONObject == null ? ShopAboutVideo.PROVIDER_STATUS_NONE : jSONObject.toString();
            String.format(str2, objArr);
        } catch (Exception e) {
            str = f792a;
        }
    }

    private static String m606a(Map<String, String> map) {
        List<String> arrayList = new ArrayList();
        for (Entry key : map.entrySet()) {
            if (!((String) key.getKey()).equals("X-Appboy-Api-Key")) {
                arrayList.add(String.format("(%s / %s)", new Object[]{((Entry) r3.next()).getKey(), ((Entry) r3.next()).getValue()}));
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String append : arrayList) {
            stringBuilder.append(append);
            stringBuilder.append(", ");
        }
        if (stringBuilder.length() == 0) {
            return StringUtils.EMPTY;
        }
        return stringBuilder.substring(0, stringBuilder.length() - 2);
    }
}

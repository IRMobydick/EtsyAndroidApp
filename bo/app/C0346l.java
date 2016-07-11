package bo.app;

import com.appboy.Constants;
import java.net.URI;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: bo.app.l */
public final class C0346l implements C0342i {
    private static final String f794a;
    private final C0342i f795b;

    static {
        f794a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, C0346l.class.getName()});
    }

    public C0346l(C0342i c0342i) {
        this.f795b = c0342i;
    }

    public final JSONObject m610a(URI uri, Map<String, String> map) {
        long currentTimeMillis;
        String str;
        long currentTimeMillis2 = System.currentTimeMillis();
        try {
            JSONObject a = this.f795b.m489a(uri, map);
            currentTimeMillis = System.currentTimeMillis();
            str = f794a;
            String.format("Request Executed in [%dms] [%s:%s]", new Object[]{Long.valueOf(currentTimeMillis - currentTimeMillis2), ag.GET.toString(), uri.toString()});
            return a;
        } catch (Throwable th) {
            currentTimeMillis = System.currentTimeMillis();
            str = f794a;
            String.format("Request Executed in [%dms] [%s:%s]", new Object[]{Long.valueOf(currentTimeMillis - currentTimeMillis2), ag.GET.toString(), uri.toString()});
        }
    }

    public final JSONObject m611a(URI uri, Map<String, String> map, JSONObject jSONObject) {
        long currentTimeMillis;
        String str;
        long currentTimeMillis2 = System.currentTimeMillis();
        try {
            JSONObject a = this.f795b.m490a(uri, map, jSONObject);
            currentTimeMillis = System.currentTimeMillis();
            str = f794a;
            String.format("Request Executed in [%dms] [%s:%s]", new Object[]{Long.valueOf(currentTimeMillis - currentTimeMillis2), ag.POST.toString(), uri.toString()});
            return a;
        } catch (Throwable th) {
            currentTimeMillis = System.currentTimeMillis();
            str = f794a;
            String.format("Request Executed in [%dms] [%s:%s]", new Object[]{Long.valueOf(currentTimeMillis - currentTimeMillis2), ag.POST.toString(), uri.toString()});
        }
    }
}

package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class es extends ek<db> {
    private static final String f406c;
    final SharedPreferences f407a;
    public db f408b;

    final /* synthetic */ Object m264a() {
        return m263d();
    }

    final /* synthetic */ void m265a(Object obj) {
        db dbVar = (db) obj;
        if (dbVar != null) {
            try {
                JSONObject jSONObject = new JSONObject(this.f407a.getString("cached_device", "{}"));
                JSONObject a = dbVar.m183a();
                Editor edit = this.f407a.edit();
                edit.putString("cached_device", fg.m343a(jSONObject, a).toString());
                edit.apply();
            } catch (JSONException e) {
                String str = f406c;
            }
        }
    }

    static {
        f406c = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, es.class.getName()});
    }

    public es(Context context) {
        this(context, null, null);
    }

    public es(Context context, String str, String str2) {
        this.f408b = null;
        this.f407a = context.getSharedPreferences("com.appboy.storage.device_cache" + fj.m352b(str, str2), 0);
    }

    public final synchronized void m266c() {
        Editor edit = this.f407a.edit();
        edit.remove("cached_device");
        edit.apply();
    }

    private db m263d() {
        JSONObject a = this.f408b.m183a();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject = new JSONObject(this.f407a.getString("cached_device", "{}"));
        } catch (Throwable e) {
            AppboyLogger.m665e(f406c, "Caught exception confirming and unlocking Json objects.", e);
        }
        JSONObject jSONObject2 = new JSONObject();
        Iterator keys = a.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object opt = a.opt(str);
            Object opt2 = jSONObject.opt(str);
            if ((opt instanceof JSONObject) || (opt instanceof JSONArray)) {
                if (opt2 != null) {
                    try {
                        if ((!iv.m573a(String.valueOf(opt), String.valueOf(opt2), iw.f778c).f783a ? 1 : null) == null) {
                        }
                    } catch (JSONException e2) {
                        str = f406c;
                        return this.f408b;
                    }
                }
                jSONObject2.put(str, opt);
            } else if (opt.equals(opt2)) {
                continue;
            } else {
                try {
                    jSONObject2.put(str, opt);
                } catch (Throwable e3) {
                    AppboyLogger.m665e(f406c, "Caught json exception creating dirty outbound device. Returning the whole device.", e3);
                    return this.f408b;
                }
            }
        }
        try {
            return db.m182a(jSONObject2);
        } catch (JSONException e4) {
            str = f406c;
            return this.f408b;
        }
    }
}

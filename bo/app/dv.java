package bo.app;

import android.net.Uri;
import com.appboy.Appboy;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import org.json.JSONObject;

public abstract class dv extends eh implements eb {
    private static final String f350b;
    private dh f351c;
    private de f352d;

    static {
        f350b = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, dv.class.getName()});
    }

    protected dv(Uri uri) {
        super(uri, null);
    }

    public final Uri m224b() {
        return Appboy.getAppboyApiEndpoint(this.a);
    }

    public final void m223a(dh dhVar) {
        this.f351c = dhVar;
    }

    public final dh m226c() {
        return this.f351c;
    }

    public final void m222a(de deVar) {
        this.f352d = deVar;
    }

    public final de m227d() {
        return this.f352d;
    }

    public JSONObject m228e() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.f351c != null) {
                jSONObject.put("extras", this.f351c.m191a());
            }
            if (this.f352d == null) {
                return jSONObject;
            }
            jSONObject.put("environment", this.f352d.m187a());
            return jSONObject;
        } catch (Throwable e) {
            AppboyLogger.m671w(f350b, "Experienced JSONException while retrieving parameters. Returning null.", e);
            return null;
        }
    }

    public boolean m229f() {
        return this.f351c == null || this.f351c.m192c();
    }

    public final void m225b(bc bcVar) {
        di diVar = this.f351c.f322c;
        db dbVar = this.f351c.f321b;
        if (diVar != null) {
            bcVar.m31a(new bg(diVar), bg.class);
        }
        if (dbVar != null) {
            bcVar.m31a(new bf(dbVar), bf.class);
        }
    }
}

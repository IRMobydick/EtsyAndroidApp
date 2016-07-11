package com.appboy.models.outgoing;

import android.content.SharedPreferences.Editor;
import bo.app.de;
import bo.app.ey;
import bo.app.fd;
import com.appboy.Constants;
import org.json.JSONException;
import org.json.JSONObject;

public class Environment {
    private static final String f1001a;
    private final String f1002b;
    private final int f1003c;
    private final String f1004d;
    private final String f1005e;
    private ey f1006f;
    private final Object f1007g;

    static {
        f1001a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, Environment.class.getName()});
    }

    public Environment(String str, int i, String str2, String str3, ey eyVar) {
        this(str, i, str2, str3);
        this.f1006f = eyVar;
    }

    public Environment(String str, int i, String str2, String str3) {
        this.f1007g = new Object();
        this.f1002b = str;
        this.f1003c = i;
        this.f1004d = str2;
        this.f1005e = str3;
    }

    public JSONObject forStatelessJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sdk_version", this.f1002b);
            jSONObject.put("now", fd.m334b());
            jSONObject.put("version_code", this.f1003c);
            jSONObject.put("version_name", this.f1004d);
            jSONObject.put("package_name", this.f1005e);
            jSONObject.put("no_acks", true);
        } catch (JSONException e) {
            String str = f1001a;
        }
        return jSONObject;
    }

    public de dispatch() {
        de deVar;
        synchronized (this.f1007g) {
            String str = null;
            if (this.f1006f != null && this.f1006f.f431e.get()) {
                str = String.valueOf(this.f1006f.m300f());
                if (!Constants.APPBOY_SDK_VERSION.equals(this.f1006f.f433g)) {
                    str = "0";
                    ey eyVar = this.f1006f;
                    if (!Constants.APPBOY_SDK_VERSION.equals(eyVar.f433g)) {
                        eyVar.f433g = Constants.APPBOY_SDK_VERSION;
                        Editor edit = eyVar.f429c.edit();
                        edit.putString("last_configured_appboy_sdk_version", Constants.APPBOY_SDK_VERSION);
                        edit.apply();
                    }
                }
                this.f1006f.m294a(false);
            }
            deVar = new de(this.f1002b, this.f1003c, this.f1004d, this.f1005e, str);
        }
        return deVar;
    }
}

package com.etsy.android.lib.core;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.util.Pair;
import com.etsy.android.lib.logger.EtsyDebug;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.scribe.a.a.b;
import org.scribe.model.Token;
import org.scribe.model.h;

/* compiled from: XAuthResult */
public class ar {
    private static final String f1451a;
    private Token f1452b;
    private HashMap<String, String> f1453c;
    private String f1454d;
    private int f1455e;
    private boolean f1456f;
    private boolean f1457g;
    private String f1458h;
    private boolean f1459i;

    static {
        f1451a = EtsyDebug.m1891a(ar.class);
    }

    public ar() {
        this.f1455e = -1;
    }

    public Token m1146a() {
        return this.f1452b;
    }

    public boolean m1148b() {
        return (this.f1453c == null || this.f1453c.isEmpty()) ? false : true;
    }

    public Pair<String, String> m1149c() {
        if (!m1148b()) {
            return new Pair(StringUtils.EMPTY, StringUtils.EMPTY);
        }
        String str = (String) this.f1453c.keySet().iterator().next();
        return new Pair(str, (String) this.f1453c.get(str));
    }

    public String m1150d() {
        return this.f1454d;
    }

    public boolean m1151e() {
        return this.f1456f;
    }

    public boolean m1152f() {
        return this.f1457g;
    }

    public boolean m1153g() {
        return this.f1459i;
    }

    public String m1154h() {
        return this.f1458h;
    }

    public void m1147a(b bVar, h hVar) {
        this.f1455e = hVar.d();
        this.f1457g = false;
        if (this.f1455e == Callback.DEFAULT_DRAG_ANIMATION_DURATION || this.f1455e == 201 || this.f1455e == 202) {
            this.f1456f = true;
            try {
                this.f1452b = bVar.c().a(hVar.b());
                return;
            } catch (Throwable e) {
                this.f1456f = false;
                EtsyDebug.m1917d(f1451a, "parseResponse OAuthException", e);
                return;
            }
        }
        String a = hVar.a("WWW-Authenticate");
        if (this.f1455e == 401 && a != null && a.contains("Two-Factor-Auth")) {
            this.f1457g = true;
            this.f1458h = hVar.a("workflow_key");
            this.f1456f = true;
            return;
        }
        this.f1456f = false;
        try {
            JSONObject jSONObject = new JSONObject(hVar.b());
            Iterator keys = jSONObject.keys();
            this.f1453c = new HashMap();
            while (keys.hasNext()) {
                a = (String) keys.next();
                if ("should_retry".equals(a)) {
                    this.f1459i = Boolean.parseBoolean(jSONObject.getString(a));
                } else {
                    this.f1453c.put(a, jSONObject.getString(a));
                }
            }
        } catch (Throwable e2) {
            EtsyDebug.m1913c(f1451a, "parseResponse JSONException - treating body as error string: " + hVar.b(), e2);
            this.f1454d = hVar.b();
        }
    }
}

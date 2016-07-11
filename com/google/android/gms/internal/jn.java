package com.google.android.gms.internal;

import android.content.Context;
import com.adjust.sdk.Constants;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.zzq;
import java.util.concurrent.TimeUnit;

@jw
public class jn {
    private static final long f5178a;
    private static final Object f5179b;
    private static boolean f5180c;
    private static gx f5181d;
    private final Context f5182e;
    private final lc f5183f;
    private final zzq f5184g;
    private final bu f5185h;
    private gu f5186i;
    private hc f5187j;
    private gs f5188k;
    private boolean f5189l;

    static {
        f5178a = TimeUnit.SECONDS.toMillis(60);
        f5179b = new Object();
        f5180c = false;
        f5181d = null;
    }

    public jn(Context context, lc lcVar, zzq com_google_android_gms_ads_internal_zzq, bu buVar) {
        this.f5189l = false;
        this.f5182e = context;
        this.f5183f = lcVar;
        this.f5184g = com_google_android_gms_ads_internal_zzq;
        this.f5185h = buVar;
        this.f5189l = ((Boolean) dz.bg.m6433c()).booleanValue();
    }

    private String m6836a(lc lcVar) {
        String str = (String) dz.af.m6433c();
        String valueOf = String.valueOf(lcVar.f5353b.zzHH.indexOf(Constants.SCHEME) == 0 ? "https:" : "http:");
        str = String.valueOf(str);
        return str.length() != 0 ? valueOf.concat(str) : new String(valueOf);
    }

    private void m6837g() {
        synchronized (f5179b) {
            if (!f5180c) {
                f5181d = new gx(this.f5182e.getApplicationContext() != null ? this.f5182e.getApplicationContext() : this.f5182e, this.f5183f.f5352a.zzsx, m6836a(this.f5183f), new 3(this), new gz());
                f5180c = true;
            }
        }
    }

    private void m6838h() {
        this.f5187j = new hc(m6846e().m6651b(this.f5185h));
    }

    private void m6839i() {
        this.f5186i = new gu();
    }

    private void m6840j() {
        this.f5188k = (gs) m6844c().m6623a(this.f5182e, this.f5183f.f5352a.zzsx, m6836a(this.f5183f), this.f5185h).get(f5178a, TimeUnit.MILLISECONDS);
        this.f5188k.a(this.f5184g, this.f5184g, this.f5184g, this.f5184g, false, null, null, null, null);
    }

    public void m6841a() {
        if (this.f5189l) {
            m6837g();
        } else {
            m6839i();
        }
    }

    public void m6842a(jo joVar) {
        if (this.f5189l) {
            hc f = m6847f();
            if (f == null) {
                C1129c.m6192d("SharedJavascriptEngine not initialized");
                return;
            } else {
                f.a(new 1(this, joVar), new 2(this, joVar));
                return;
            }
        }
        gs d = m6845d();
        if (d == null) {
            C1129c.m6192d("JavascriptEngine not initialized");
        } else {
            joVar.a(d);
        }
    }

    public void m6843b() {
        if (this.f5189l) {
            m6838h();
        } else {
            m6840j();
        }
    }

    protected gu m6844c() {
        return this.f5186i;
    }

    protected gs m6845d() {
        return this.f5188k;
    }

    protected gx m6846e() {
        return f5181d;
    }

    protected hc m6847f() {
        return this.f5187j;
    }
}

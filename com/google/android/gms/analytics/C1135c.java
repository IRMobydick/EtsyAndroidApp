package com.google.android.gms.analytics;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.analytics.internal.C1137f;
import com.google.android.gms.analytics.internal.q;
import com.google.android.gms.analytics.internal.s;
import com.google.android.gms.analytics.internal.u;
import com.google.android.gms.analytics.internal.v;
import com.google.android.gms.analytics.internal.z;
import com.google.android.gms.common.internal.zzaa;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: com.google.android.gms.analytics.c */
public final class C1135c extends q {
    private static List<Runnable> f4679b;
    private boolean f4680c;
    private Set<d> f4681d;
    private boolean f4682e;
    private boolean f4683f;
    private volatile boolean f4684g;

    static {
        f4679b = new ArrayList();
    }

    public C1135c(z zVar) {
        super(zVar);
        this.f4681d = new HashSet();
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public static C1135c m6199a(Context context) {
        return z.a(context).k();
    }

    public static void m6200d() {
        synchronized (C1135c.class) {
            if (f4679b != null) {
                for (Runnable run : f4679b) {
                    run.run();
                }
                f4679b = null;
            }
        }
    }

    private v m6201p() {
        return k().i();
    }

    private u m6202q() {
        return k().l();
    }

    public o m6203a(int i) {
        o oVar;
        synchronized (this) {
            oVar = new o(k(), null, null);
            if (i > 0) {
                s sVar = (s) new q(k()).a(i);
                if (sVar != null) {
                    oVar.a(sVar);
                }
            }
            oVar.E();
        }
        return oVar;
    }

    public void m6204a() {
        m6209b();
        this.f4680c = true;
    }

    void m6205a(Activity activity) {
        for (d a : this.f4681d) {
            a.a(activity);
        }
    }

    @TargetApi(14)
    public void m6206a(Application application) {
        if (VERSION.SDK_INT >= 14 && !this.f4682e) {
            application.registerActivityLifecycleCallbacks(new e(this));
            this.f4682e = true;
        }
    }

    void m6207a(d dVar) {
        this.f4681d.add(dVar);
        Context b = k().b();
        if (b instanceof Application) {
            m6206a((Application) b);
        }
    }

    public void m6208a(boolean z) {
        this.f4683f = z;
    }

    void m6209b() {
        u q = m6202q();
        if (q.d()) {
            m6215g().m6253a(q.e());
        }
        if (q.h()) {
            m6208a(q.i());
        }
        if (q.d()) {
            C1142m a = C1137f.m6221a();
            if (a != null) {
                a.m6253a(q.e());
            }
        }
    }

    void m6210b(Activity activity) {
        for (d b : this.f4681d) {
            b.b(activity);
        }
    }

    void m6211b(d dVar) {
        this.f4681d.remove(dVar);
    }

    public boolean m6212c() {
        return this.f4680c;
    }

    public boolean m6213e() {
        return this.f4683f;
    }

    public boolean m6214f() {
        return this.f4684g;
    }

    @Deprecated
    public C1142m m6215g() {
        return C1137f.m6221a();
    }

    public String m6216h() {
        zzaa.zzdd("getClientId can not be called from the main thread");
        return k().p().b();
    }

    public void m6217i() {
        m6201p().c();
    }

    void m6218j() {
        m6201p().d();
    }
}

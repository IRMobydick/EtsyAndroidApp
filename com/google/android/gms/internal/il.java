package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.client.C1089r;
import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.Map;

@jw
public class il extends im implements fk {
    DisplayMetrics f5130a;
    int f5131b;
    int f5132c;
    int f5133d;
    int f5134e;
    int f5135f;
    int f5136g;
    private final no f5137h;
    private final Context f5138i;
    private final WindowManager f5139j;
    private final dr f5140k;
    private float f5141l;
    private int f5142m;

    public il(no noVar, Context context, dr drVar) {
        super(noVar);
        this.f5131b = -1;
        this.f5132c = -1;
        this.f5133d = -1;
        this.f5134e = -1;
        this.f5135f = -1;
        this.f5136g = -1;
        this.f5137h = noVar;
        this.f5138i = context;
        this.f5140k = drVar;
        this.f5139j = (WindowManager) context.getSystemService("window");
    }

    private void m6784g() {
        this.f5130a = new DisplayMetrics();
        Display defaultDisplay = this.f5139j.getDefaultDisplay();
        defaultDisplay.getMetrics(this.f5130a);
        this.f5141l = this.f5130a.density;
        this.f5142m = defaultDisplay.getRotation();
    }

    private void m6785h() {
        int[] iArr = new int[2];
        this.f5137h.getLocationOnScreen(iArr);
        m6788a(C1089r.m5951a().m6178b(this.f5138i, iArr[0]), C1089r.m5951a().m6178b(this.f5138i, iArr[1]));
    }

    private ij m6786i() {
        return new ik().b(this.f5140k.m6416a()).a(this.f5140k.m6418b()).c(this.f5140k.m6421f()).d(this.f5140k.m6419c()).e(this.f5140k.m6420d()).a();
    }

    void m6787a() {
        this.f5131b = C1089r.m5951a().m6179b(this.f5130a, this.f5130a.widthPixels);
        this.f5132c = C1089r.m5951a().m6179b(this.f5130a, this.f5130a.heightPixels);
        Activity f = this.f5137h.m7256f();
        if (f == null || f.getWindow() == null) {
            this.f5133d = this.f5131b;
            this.f5134e = this.f5132c;
            return;
        }
        int[] a = C1101o.m6041e().m7117a(f);
        this.f5133d = C1089r.m5951a().m6179b(this.f5130a, a[0]);
        this.f5134e = C1089r.m5951a().m6179b(this.f5130a, a[1]);
    }

    public void m6788a(int i, int i2) {
        m6757b(i, i2 - (this.f5138i instanceof Activity ? C1101o.m6041e().m7131d((Activity) this.f5138i)[0] : 0), this.f5135f, this.f5136g);
        this.f5137h.m7262l().zzd(i, i2);
    }

    public void m6789a(no noVar, Map<String, String> map) {
        m6791c();
    }

    void m6790b() {
        if (this.f5137h.m7261k().zzvt) {
            this.f5135f = this.f5131b;
            this.f5136g = this.f5132c;
            return;
        }
        this.f5137h.measure(0, 0);
        this.f5135f = C1089r.m5951a().m6178b(this.f5138i, this.f5137h.getMeasuredWidth());
        this.f5136g = C1089r.m5951a().m6178b(this.f5138i, this.f5137h.getMeasuredHeight());
    }

    public void m6791c() {
        m6784g();
        m6787a();
        m6790b();
        m6793e();
        m6794f();
        m6785h();
        m6792d();
    }

    void m6792d() {
        if (C1129c.m6187a(2)) {
            C1129c.m6190c("Dispatching Ready Event.");
        }
        m6759c(this.f5137h.m7265o().afmaVersion);
    }

    void m6793e() {
        m6756a(this.f5131b, this.f5132c, this.f5133d, this.f5134e, this.f5141l, this.f5142m);
    }

    void m6794f() {
        this.f5137h.b("onDeviceFeaturesReceived", m6786i().m6783a());
    }
}

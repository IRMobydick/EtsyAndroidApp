package com.google.android.gms.internal;

import android.content.Context;
import android.os.Build.VERSION;
import com.appboy.Constants;
import com.google.android.gms.ads.internal.C1101o;
import java.util.LinkedHashMap;
import java.util.Map;

@jw
public class ea {
    private boolean f4853a;
    private String f4854b;
    private Map<String, String> f4855c;
    private Context f4856d;
    private String f4857e;

    public ea(Context context, String str) {
        this.f4856d = null;
        this.f4857e = null;
        this.f4856d = context;
        this.f4857e = str;
        this.f4853a = ((Boolean) dz.f4808H.m6433c()).booleanValue();
        this.f4854b = (String) dz.f4809I.m6433c();
        this.f4855c = new LinkedHashMap();
        this.f4855c.put(Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY, "gmob_sdk");
        this.f4855c.put("v", "3");
        this.f4855c.put("os", VERSION.RELEASE);
        this.f4855c.put("sdk", VERSION.SDK);
        this.f4855c.put("device", C1101o.m6041e().m7133e());
        this.f4855c.put("app", context.getApplicationContext() != null ? context.getApplicationContext().getPackageName() : context.getPackageName());
        this.f4855c.put("is_lite_sdk", C1101o.m6041e().m7144l(context) ? "1" : "0");
        kd a = C1101o.m6047k().m6958a(this.f4856d);
        this.f4855c.put("network_coarse", Integer.toString(a.f5285m));
        this.f4855c.put("network_fine", Integer.toString(a.f5286n));
    }

    boolean m6446a() {
        return this.f4853a;
    }

    String m6447b() {
        return this.f4854b;
    }

    Context m6448c() {
        return this.f4856d;
    }

    String m6449d() {
        return this.f4857e;
    }

    Map<String, String> m6450e() {
        return this.f4855c;
    }
}

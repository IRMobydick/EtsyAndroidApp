package com.google.android.gms.internal;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@jw
class kc {
    private final List<String> f5261a;
    private final List<String> f5262b;
    private final String f5263c;
    private final String f5264d;
    private final String f5265e;
    private final String f5266f;
    private final boolean f5267g;
    private final boolean f5268h;
    private final String f5269i;
    private final String f5270j;
    private String f5271k;
    private int f5272l;

    public kc(int i, Map<String, String> map) {
        this.f5271k = (String) map.get(ResponseConstants.URL);
        this.f5264d = (String) map.get("base_uri");
        this.f5265e = (String) map.get("post_parameters");
        this.f5267g = m6946b((String) map.get("drt_include"));
        this.f5268h = m6946b((String) map.get("pan_include"));
        this.f5263c = (String) map.get("activation_overlay_url");
        this.f5262b = m6947c((String) map.get("check_packages"));
        this.f5269i = (String) map.get("request_id");
        this.f5266f = (String) map.get(FindsModule.FIELD_TYPE);
        this.f5261a = m6947c((String) map.get("errors"));
        this.f5272l = i;
        this.f5270j = (String) map.get("fetched_ad");
    }

    private static boolean m6946b(String str) {
        return str != null && (str.equals("1") || str.equals("true"));
    }

    private List<String> m6947c(String str) {
        return str == null ? null : Arrays.asList(str.split(","));
    }

    public int m6948a() {
        return this.f5272l;
    }

    public void m6949a(String str) {
        this.f5271k = str;
    }

    public List<String> m6950b() {
        return this.f5261a;
    }

    public String m6951c() {
        return this.f5265e;
    }

    public String m6952d() {
        return this.f5271k;
    }

    public String m6953e() {
        return this.f5266f;
    }

    public boolean m6954f() {
        return this.f5267g;
    }

    public String m6955g() {
        return this.f5269i;
    }

    public boolean m6956h() {
        return this.f5268h;
    }

    public String m6957i() {
        return this.f5270j;
    }
}

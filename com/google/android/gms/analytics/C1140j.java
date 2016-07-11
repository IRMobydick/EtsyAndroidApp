package com.google.android.gms.analytics;

import java.util.Map;

@Deprecated
/* renamed from: com.google.android.gms.analytics.j */
public class C1140j extends i<C1140j> {
    public C1140j() {
        a("&t", "item");
    }

    public C1140j m6238a(double d) {
        a("&ip", Double.toString(d));
        return this;
    }

    public C1140j m6239a(long j) {
        a("&iq", Long.toString(j));
        return this;
    }

    public C1140j m6240a(String str) {
        a("&ti", str);
        return this;
    }

    public /* bridge */ /* synthetic */ Map m6241a() {
        return super.a();
    }

    public C1140j m6242b(String str) {
        a("&in", str);
        return this;
    }

    public C1140j m6243c(String str) {
        a("&ic", str);
        return this;
    }

    public C1140j m6244d(String str) {
        a("&cu", str);
        return this;
    }
}

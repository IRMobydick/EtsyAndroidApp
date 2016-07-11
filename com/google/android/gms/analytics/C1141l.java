package com.google.android.gms.analytics;

import java.util.Map;

@Deprecated
/* renamed from: com.google.android.gms.analytics.l */
public class C1141l extends i<C1141l> {
    public C1141l() {
        a("&t", "transaction");
    }

    public C1141l m6245a(double d) {
        a("&tr", Double.toString(d));
        return this;
    }

    public C1141l m6246a(String str) {
        a("&ti", str);
        return this;
    }

    public /* bridge */ /* synthetic */ Map m6247a() {
        return super.a();
    }

    public C1141l m6248b(double d) {
        a("&tt", Double.toString(d));
        return this;
    }

    public C1141l m6249b(String str) {
        a("&ta", str);
        return this;
    }

    public C1141l m6250c(double d) {
        a("&ts", Double.toString(d));
        return this;
    }

    public C1141l m6251c(String str) {
        a("&cu", str);
        return this;
    }
}

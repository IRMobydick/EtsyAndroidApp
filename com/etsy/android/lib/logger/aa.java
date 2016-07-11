package com.etsy.android.lib.logger;

import android.os.Bundle;

/* compiled from: ViewAnalyticsParameter */
class aa {
    private String f1741a;
    private AnalyticsLogAttribute f1742b;
    private ab f1743c;

    public aa(String str, AnalyticsLogAttribute analyticsLogAttribute, ab abVar) {
        this.f1743c = null;
        this.f1741a = str;
        this.f1742b = analyticsLogAttribute;
        this.f1743c = abVar;
    }

    public Object m1826a(Bundle bundle) {
        return this.f1743c.m1827a(this.f1741a, bundle);
    }

    public AnalyticsLogAttribute m1825a() {
        return this.f1742b;
    }
}

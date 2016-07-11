package com.etsy.android.lib.core;

import com.etsy.android.lib.requests.EtsyRequest;

/* renamed from: com.etsy.android.lib.core.v */
class EtsyRequestJobMap extends EtsyRequestJob<B> {
    final /* synthetic */ EtsyRequestJobMap f1661a;
    private final String f1662c;
    private final EtsyRequestJob<B> f1663d;

    public EtsyRequestJobMap(EtsyRequestJobMap etsyRequestJobMap, String str, EtsyRequestJob<B> etsyRequestJob) {
        this.f1661a = etsyRequestJobMap;
        this.f1662c = str;
        this.f1663d = etsyRequestJob;
    }

    protected void b_() {
        this.f1663d.b_();
        this.f1661a.m1031a();
    }

    protected EtsyRequest<B> m1679a() {
        return this.f1663d.m716a();
    }

    protected void m1680a(EtsyResult<B> etsyResult) {
        this.f1663d.m717a((EtsyResult) etsyResult);
        this.f1661a.m1032a(this.f1662c, etsyResult);
    }

    protected void m1682b(EtsyResult<B> etsyResult) {
        this.f1663d.m719b((EtsyResult) etsyResult);
        this.f1661a.m1033b(this.f1662c, etsyResult);
    }
}

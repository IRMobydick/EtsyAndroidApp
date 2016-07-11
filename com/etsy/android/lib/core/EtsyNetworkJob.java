package com.etsy.android.lib.core;

import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import org.apache.commons.lang3.StringUtils;

@Deprecated
/* renamed from: com.etsy.android.lib.core.q */
public abstract class EtsyNetworkJob<Result> {
    private String f1077a;
    private EtsyRequestQueue f1078b;
    private Listener<Result> f1079c;
    private ErrorListener f1080d;

    /* renamed from: com.etsy.android.lib.core.q.1 */
    class EtsyNetworkJob implements Listener<Result> {
        final /* synthetic */ EtsyNetworkJob f1655a;

        EtsyNetworkJob(EtsyNetworkJob etsyNetworkJob) {
            this.f1655a = etsyNetworkJob;
        }

        public void onResponse(Result result) {
            this.f1655a.m701a((Object) result);
        }
    }

    /* renamed from: com.etsy.android.lib.core.q.2 */
    class EtsyNetworkJob implements ErrorListener {
        final /* synthetic */ EtsyNetworkJob f1656a;

        EtsyNetworkJob(EtsyNetworkJob etsyNetworkJob) {
            this.f1656a = etsyNetworkJob;
        }

        public void onErrorResponse(VolleyError volleyError) {
            this.f1656a.m701a(null);
        }
    }

    protected abstract void m701a(Result result);

    protected abstract void b_();

    protected abstract Request<Result> m703c();

    public EtsyNetworkJob() {
        this.f1079c = new EtsyNetworkJob(this);
        this.f1080d = new EtsyNetworkJob(this);
    }

    public Listener<Result> m704d() {
        return this.f1079c;
    }

    public void m699a(Listener<Result> listener) {
        this.f1079c = listener;
    }

    public ErrorListener m705e() {
        return this.f1080d;
    }

    public void m698a(ErrorListener errorListener) {
        this.f1080d = errorListener;
    }

    String m706f() {
        return this.f1077a;
    }

    void m702b(Object obj) {
        if (obj == null) {
            this.f1077a = StringUtils.EMPTY;
        } else {
            this.f1077a = Request.createTag(obj);
        }
    }

    protected EtsyRequestQueue m707g() {
        return this.f1078b;
    }

    void m700a(EtsyRequestQueue etsyRequestQueue) {
        this.f1078b = etsyRequestQueue;
    }
}

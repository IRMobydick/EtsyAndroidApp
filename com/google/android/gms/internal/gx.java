package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;

@jw
public class gx {
    private final Object f4977a;
    private final Context f4978b;
    private final String f4979c;
    private final VersionInfoParcel f4980d;
    private mg<gs> f4981e;
    private mg<gs> f4982f;
    private hb f4983g;
    private int f4984h;

    public gx(Context context, VersionInfoParcel versionInfoParcel, String str) {
        this.f4977a = new Object();
        this.f4984h = 1;
        this.f4979c = str;
        this.f4978b = context.getApplicationContext();
        this.f4980d = versionInfoParcel;
        this.f4981e = new gz();
        this.f4982f = new gz();
    }

    public gx(Context context, VersionInfoParcel versionInfoParcel, String str, mg<gs> mgVar, mg<gs> mgVar2) {
        this(context, versionInfoParcel, str);
        this.f4981e = mgVar;
        this.f4982f = mgVar2;
    }

    private hb m6642c(bu buVar) {
        hb hbVar = new hb(this.f4982f);
        C1101o.m6041e().m7109a(new 1(this, buVar, hbVar));
        return hbVar;
    }

    protected gs m6648a(Context context, VersionInfoParcel versionInfoParcel, bu buVar) {
        return new gw(context, versionInfoParcel, buVar);
    }

    public ha m6649a() {
        return m6651b(null);
    }

    protected hb m6650a(bu buVar) {
        hb c = m6642c(buVar);
        c.a(new 2(this, c), new 3(this, c));
        return c;
    }

    public ha m6651b(bu buVar) {
        ha d_;
        synchronized (this.f4977a) {
            if (this.f4983g == null || this.f4983g.b() == -1) {
                this.f4984h = 2;
                this.f4983g = m6650a(buVar);
                d_ = this.f4983g.d_();
            } else if (this.f4984h == 0) {
                d_ = this.f4983g.d_();
            } else if (this.f4984h == 1) {
                this.f4984h = 2;
                m6650a(buVar);
                d_ = this.f4983g.d_();
            } else if (this.f4984h == 2) {
                d_ = this.f4983g.d_();
            } else {
                d_ = this.f4983g.d_();
            }
        }
        return d_;
    }
}

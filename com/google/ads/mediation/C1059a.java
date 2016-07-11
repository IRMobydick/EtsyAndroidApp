package com.google.ads.mediation;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import com.google.ads.mediation.a.1;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.a.a.a;
import com.google.android.gms.ads.a.b;
import com.google.android.gms.ads.c;
import com.google.android.gms.ads.e;
import com.google.android.gms.ads.f;
import com.google.android.gms.ads.i;
import com.google.android.gms.ads.internal.client.C1089r;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.mediation.d;
import com.google.android.gms.ads.mediation.g;
import com.google.android.gms.ads.mediation.h;
import com.google.android.gms.ads.mediation.m;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.nx;
import java.util.Date;
import java.util.Set;

@jw
/* renamed from: com.google.ads.mediation.a */
public abstract class C1059a implements a, d, h, nx {
    protected AdView f4358a;
    protected i f4359b;
    final b f4360c;
    private com.google.android.gms.ads.b f4361d;
    private Context f4362e;
    private i f4363f;
    private com.google.android.gms.ads.a.a.b f4364g;

    public C1059a() {
        this.f4360c = new 1(this);
    }

    protected abstract Bundle m5814a(Bundle bundle, Bundle bundle2);

    c m5815a(Context context, String str) {
        return new c(context, str);
    }

    com.google.android.gms.ads.d m5816a(Context context, com.google.android.gms.ads.mediation.a aVar, Bundle bundle, Bundle bundle2) {
        e eVar = new e();
        Date a = aVar.a();
        if (a != null) {
            eVar.a(a);
        }
        int b = aVar.b();
        if (b != 0) {
            eVar.a(b);
        }
        Set<String> c = aVar.c();
        if (c != null) {
            for (String a2 : c) {
                eVar.a(a2);
            }
        }
        Location d = aVar.d();
        if (d != null) {
            eVar.a(d);
        }
        if (aVar.f()) {
            eVar.b(C1089r.m5951a().m6168a(context));
        }
        if (aVar.e() != -1) {
            eVar.a(aVar.e() == 1);
        }
        eVar.b(aVar.g());
        eVar.a(AdMobAdapter.class, m5814a(bundle, bundle2));
        return eVar.a();
    }

    public String m5817a(Bundle bundle) {
        return bundle.getString("pubid");
    }

    public void m5818a() {
        if (this.f4358a != null) {
            this.f4358a.destroy();
            this.f4358a = null;
        }
        if (this.f4359b != null) {
            this.f4359b = null;
        }
        if (this.f4361d != null) {
            this.f4361d = null;
        }
        if (this.f4363f != null) {
            this.f4363f = null;
        }
    }

    public void m5819a(Context context, com.google.android.gms.ads.mediation.a aVar, String str, com.google.android.gms.ads.a.a.b bVar, Bundle bundle, Bundle bundle2) {
        this.f4362e = context.getApplicationContext();
        this.f4364g = bVar;
        this.f4364g.a(this);
    }

    public void m5820a(Context context, com.google.android.gms.ads.mediation.e eVar, Bundle bundle, f fVar, com.google.android.gms.ads.mediation.a aVar, Bundle bundle2) {
        this.f4358a = new AdView(context);
        this.f4358a.setAdSize(new f(fVar.b(), fVar.a()));
        this.f4358a.setAdUnitId(m5817a(bundle));
        this.f4358a.setAdListener(new d(this, eVar));
        this.f4358a.loadAd(m5816a(context, aVar, bundle2, bundle));
    }

    public void m5821a(Context context, g gVar, Bundle bundle, com.google.android.gms.ads.mediation.a aVar, Bundle bundle2) {
        this.f4359b = new i(context);
        this.f4359b.a(m5817a(bundle));
        this.f4359b.a(new e(this, gVar));
        this.f4359b.a(m5816a(context, aVar, bundle2, bundle));
    }

    public void m5822a(Context context, com.google.android.gms.ads.mediation.i iVar, Bundle bundle, m mVar, Bundle bundle2) {
        f fVar = new f(this, iVar);
        c a = m5815a(context, bundle.getString("pubid")).a(fVar);
        com.google.android.gms.ads.formats.c h = mVar.h();
        if (h != null) {
            a.a(h);
        }
        if (mVar.i()) {
            a.a(fVar);
        }
        if (mVar.j()) {
            a.a(fVar);
        }
        this.f4361d = a.a();
        this.f4361d.a(m5816a(context, mVar, bundle2, bundle));
    }

    public void m5823a(com.google.android.gms.ads.mediation.a aVar, Bundle bundle, Bundle bundle2) {
        if (this.f4362e == null || this.f4364g == null) {
            C1129c.m6188b("AdMobAdapter.loadAd called before initialize.");
            return;
        }
        this.f4363f = new i(this.f4362e);
        this.f4363f.a(true);
        this.f4363f.a(m5817a(bundle));
        this.f4363f.a(this.f4360c);
        this.f4363f.a(m5816a(this.f4362e, aVar, bundle2, bundle));
    }

    public void m5824b() {
        if (this.f4358a != null) {
            this.f4358a.pause();
        }
    }

    public void m5825c() {
        if (this.f4358a != null) {
            this.f4358a.resume();
        }
    }

    public View m5826d() {
        return this.f4358a;
    }

    public void m5827e() {
        this.f4359b.a();
    }

    public Bundle m5828f() {
        return new com.google.android.gms.ads.mediation.c().a(1).a();
    }

    public void m5829g() {
        this.f4363f.a();
    }

    public boolean m5830h() {
        return this.f4364g != null;
    }
}

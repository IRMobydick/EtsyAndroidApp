package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.search.b;
import com.google.android.gms.internal.jw;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@jw
/* renamed from: com.google.android.gms.ads.internal.client.n */
public class C1086n {
    public static final C1086n f4455a;

    static {
        f4455a = new C1086n();
    }

    protected C1086n() {
    }

    public static C1086n m5927a() {
        return f4455a;
    }

    public AdRequestParcel m5928a(Context context, C1079e c1079e) {
        Date a = c1079e.m5858a();
        long time = a != null ? a.getTime() : -1;
        String b = c1079e.m5860b();
        int c = c1079e.m5861c();
        Collection d = c1079e.m5862d();
        List unmodifiableList = !d.isEmpty() ? Collections.unmodifiableList(new ArrayList(d)) : null;
        boolean a2 = c1079e.m5859a(context);
        int l = c1079e.m5870l();
        Location e = c1079e.m5863e();
        Bundle a3 = c1079e.m5857a(AdMobAdapter.class);
        boolean f = c1079e.m5864f();
        String g = c1079e.m5865g();
        b i = c1079e.m5867i();
        SearchAdRequestParcel searchAdRequestParcel = i != null ? new SearchAdRequestParcel(i) : null;
        String str = null;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            str = C1089r.m5951a().m6171a(Thread.currentThread().getStackTrace(), applicationContext.getPackageName());
        }
        return new AdRequestParcel(7, time, a3, c, unmodifiableList, a2, l, f, g, searchAdRequestParcel, e, b, c1079e.m5869k(), c1079e.m5871m(), Collections.unmodifiableList(new ArrayList(c1079e.m5872n())), c1079e.m5866h(), str, c1079e.m5873o());
    }
}

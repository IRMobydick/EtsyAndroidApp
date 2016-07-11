package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.C1101o;

@jw
public class gb extends ln {
    final no f4942a;
    final gd f4943b;
    private final String f4944c;

    gb(no noVar, gd gdVar, String str) {
        this.f4942a = noVar;
        this.f4943b = gdVar;
        this.f4944c = str;
        C1101o.m6060x().m6560a(this);
    }

    public void onStop() {
        this.f4943b.m6568a();
    }

    public void zzbQ() {
        try {
            this.f4943b.m6572a(this.f4944c);
        } finally {
            lt.f5423a.post(new 1(this));
        }
    }
}

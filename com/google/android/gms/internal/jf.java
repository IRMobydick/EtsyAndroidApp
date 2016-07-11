package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;

@jw
public class jf extends je {
    private zzhs f5155g;

    jf(Context context, lc lcVar, no noVar, jh jhVar) {
        super(context, lcVar, noVar, jhVar);
    }

    protected void m6812b() {
        int i;
        int i2;
        AdSizeParcel k = this.c.m7261k();
        if (k.zzvt) {
            DisplayMetrics displayMetrics = this.b.getResources().getDisplayMetrics();
            i = displayMetrics.widthPixels;
            i2 = displayMetrics.heightPixels;
        } else {
            i = k.widthPixels;
            i2 = k.heightPixels;
        }
        this.f5155g = new zzhs(this, this.c, i, i2);
        this.c.m7262l().zza((np) this);
        this.f5155g.m7347a(this.e);
    }

    protected int m6813c() {
        if (!this.f5155g.m7350c()) {
            return !this.f5155g.m7351d() ? 2 : -2;
        } else {
            C1129c.m6185a("Ad-Network indicated no fill with passback URL.");
            return 3;
        }
    }
}

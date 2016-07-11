package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.util.client.C1130d;

@jw
public final class mq extends ln {
    private final C1130d f5450a;
    private final String f5451b;

    public mq(Context context, String str, String str2) {
        this(str2, C1101o.m6041e().m7092a(context, str));
    }

    public mq(String str, String str2) {
        this.f5450a = new C1130d(str2);
        this.f5451b = str;
    }

    public void onStop() {
    }

    public void zzbQ() {
        this.f5450a.m6194a(this.f5451b);
    }
}

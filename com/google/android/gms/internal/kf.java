package com.google.android.gms.internal;

import android.content.Context;
import java.util.WeakHashMap;

@jw
public final class kf {
    private WeakHashMap<Context, kg> f5299a;

    public kf() {
        this.f5299a = new WeakHashMap();
    }

    public kd m6958a(Context context) {
        kg kgVar = (kg) this.f5299a.get(context);
        kd a = (kgVar == null || kgVar.a() || !((Boolean) dz.av.m6433c()).booleanValue()) ? new ke(context).a() : new ke(context, kgVar.b).a();
        this.f5299a.put(context, new kg(this, a));
        return a;
    }
}

package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.common.zze;

@jw
public class dy {
    private final Object f4798a;
    private boolean f4799b;
    private SharedPreferences f4800c;

    public dy() {
        this.f4798a = new Object();
        this.f4799b = false;
        this.f4800c = null;
    }

    public <T> T m6441a(dv<T> dvVar) {
        synchronized (this.f4798a) {
            if (this.f4799b) {
                return mt.m7191a(new 1(this, dvVar));
            }
            T b = dvVar.m6432b();
            return b;
        }
    }

    public void m6442a(Context context) {
        synchronized (this.f4798a) {
            if (this.f4799b) {
                return;
            }
            Context remoteContext = zze.getRemoteContext(context);
            if (remoteContext == null) {
                return;
            }
            this.f4800c = C1101o.m6048l().m6439a(remoteContext);
            this.f4799b = true;
        }
    }
}

package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.d.1;
import com.google.android.gms.ads.internal.d.2;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.dz;
import com.google.android.gms.internal.fk;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.lf;
import com.google.android.gms.internal.lt;

@jw
/* renamed from: com.google.android.gms.ads.internal.d */
public class C1091d {
    public final fk f4473a;
    private final Object f4474b;
    private Context f4475c;

    public C1091d() {
        this.f4474b = new Object();
        this.f4473a = new 1(this);
    }

    private static boolean m5962a(@Nullable lf lfVar) {
        if (lfVar == null) {
            return true;
        }
        boolean z = (((C1101o.m6045i().currentTimeMillis() - lfVar.m7005a()) > ((Long) dz.bu.m6433c()).longValue() ? 1 : ((C1101o.m6045i().currentTimeMillis() - lfVar.m7005a()) == ((Long) dz.bu.m6433c()).longValue() ? 0 : -1)) > 0) || !lfVar.m7006b();
        return z;
    }

    public void m5964a(Context context, VersionInfoParcel versionInfoParcel, boolean z, @Nullable lf lfVar, String str, @Nullable String str2) {
        if (!C1091d.m5962a(lfVar)) {
            return;
        }
        if (context == null) {
            C1129c.m6192d("Context not provided to fetch application settings");
        } else if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            C1129c.m6192d("App settings could not be fetched. Required parameters missing");
        } else {
            this.f4475c = context;
            lt.f5423a.post(new 2(this, C1101o.m6041e().m7089a(context, versionInfoParcel), str, str2, z, context));
        }
    }
}

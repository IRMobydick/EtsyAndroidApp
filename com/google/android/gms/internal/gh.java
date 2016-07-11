package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.C1077a;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzl;

@jw
public class gh {
    private Context f4953a;
    private final zzga f4954b;
    private final VersionInfoParcel f4955c;
    private final C1077a f4956d;

    gh(Context context, zzga com_google_android_gms_internal_zzga, VersionInfoParcel versionInfoParcel, C1077a c1077a) {
        this.f4953a = context;
        this.f4954b = com_google_android_gms_internal_zzga;
        this.f4955c = versionInfoParcel;
        this.f4956d = c1077a;
    }

    public Context m6581a() {
        return this.f4953a.getApplicationContext();
    }

    public zzl m6582a(String str) {
        return new zzl(this.f4953a, new AdSizeParcel(), str, this.f4954b, this.f4955c, this.f4956d);
    }

    public zzl m6583b(String str) {
        return new zzl(this.f4953a.getApplicationContext(), new AdSizeParcel(), str, this.f4954b, this.f4955c, this.f4956d);
    }

    public gh m6584b() {
        return new gh(m6581a(), this.f4954b, this.f4955c, this.f4956d);
    }
}

package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.C1077a;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.l;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;

@jw
public class nt {
    public no m7277a(Context context, AdSizeParcel adSizeParcel, boolean z, boolean z2, bu buVar, VersionInfoParcel versionInfoParcel) {
        return m7278a(context, adSizeParcel, z, z2, buVar, versionInfoParcel, null, null, null);
    }

    public no m7278a(Context context, AdSizeParcel adSizeParcel, boolean z, boolean z2, bu buVar, VersionInfoParcel versionInfoParcel, ei eiVar, l lVar, C1077a c1077a) {
        no nuVar = new nu(zzle.m7394a(context, adSizeParcel, z, z2, buVar, versionInfoParcel, eiVar, lVar, c1077a));
        nuVar.setWebViewClient(C1101o.m6043g().m7148a(nuVar, z2));
        nuVar.setWebChromeClient(C1101o.m6043g().m7165c(nuVar));
        return nuVar;
    }
}

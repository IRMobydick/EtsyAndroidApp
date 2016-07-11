package com.google.android.gms.ads.internal.request;

import android.content.Context;
import com.google.android.gms.ads.internal.client.C1089r;
import com.google.android.gms.ads.internal.request.d.1;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.me;
import com.google.android.gms.internal.nb;

@jw
/* renamed from: com.google.android.gms.ads.internal.request.d */
public final class C1119d {
    public static me m6119a(Context context, VersionInfoParcel versionInfoParcel, nb<AdRequestInfoParcel> nbVar, e eVar) {
        return C1119d.m6120a(context, versionInfoParcel, nbVar, eVar, new 1(context));
    }

    static me m6120a(Context context, VersionInfoParcel versionInfoParcel, nb<AdRequestInfoParcel> nbVar, e eVar, f fVar) {
        return fVar.a(versionInfoParcel) ? C1119d.m6121a(context, nbVar, eVar) : C1119d.m6122b(context, versionInfoParcel, nbVar, eVar);
    }

    private static me m6121a(Context context, nb<AdRequestInfoParcel> nbVar, e eVar) {
        C1129c.m6185a("Fetching ad response from local ad request service.");
        me c1121h = new C1121h(context, nbVar, eVar);
        Void voidR = (Void) c1121h.zzhs();
        return c1121h;
    }

    private static me m6122b(Context context, VersionInfoParcel versionInfoParcel, nb<AdRequestInfoParcel> nbVar, e eVar) {
        C1129c.m6185a("Fetching ad response from remote ad request service.");
        if (C1089r.m5951a().m6181b(context)) {
            return new C1122i(context, versionInfoParcel, nbVar, eVar);
        }
        C1129c.m6192d("Failed to connect to remote ad request service.");
        return null;
    }
}

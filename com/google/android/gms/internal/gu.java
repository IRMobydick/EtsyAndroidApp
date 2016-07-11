package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import java.util.concurrent.Future;

@jw
public class gu {
    private gs m6621a(Context context, VersionInfoParcel versionInfoParcel, gv<gs> gvVar, bu buVar) {
        gw gwVar = new gw(context, versionInfoParcel, buVar);
        gvVar.a = gwVar;
        gwVar.a(new 2(this, gvVar));
        return gwVar;
    }

    public Future<gs> m6623a(Context context, VersionInfoParcel versionInfoParcel, String str, bu buVar) {
        gv gvVar = new gv(null);
        lt.f5423a.post(new 1(this, context, versionInfoParcel, gvVar, buVar, str));
        return gvVar;
    }
}

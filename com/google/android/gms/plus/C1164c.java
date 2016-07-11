package com.google.android.gms.plus;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.ta;
import com.google.android.gms.internal.tc;
import com.google.android.gms.internal.td;
import com.google.android.gms.internal.te;
import com.google.android.gms.plus.c.1;
import com.google.android.gms.plus.internal.d;

/* renamed from: com.google.android.gms.plus.c */
public final class C1164c {
    public static final zzf<d> f5552a;
    static final zza<d, d> f5553b;
    public static final Api<d> f5554c;
    public static final Scope f5555d;
    public static final Scope f5556e;
    public static final C1163b f5557f;
    @Deprecated
    public static final C1162a f5558g;
    public static final o f5559h;
    public static final n f5560i;

    static {
        f5552a = new zzf();
        f5553b = new 1();
        f5554c = new Api("Plus.API", f5553b, f5552a);
        f5555d = new Scope("https://www.googleapis.com/auth/plus.login");
        f5556e = new Scope("https://www.googleapis.com/auth/plus.me");
        f5557f = new te();
        f5558g = new ta();
        f5559h = new td();
        f5560i = new tc();
    }

    public static d m7458a(GoogleApiClient googleApiClient, boolean z) {
        zzaa.zzb(googleApiClient != null, (Object) "GoogleApiClient parameter is required.");
        zzaa.zza(googleApiClient.isConnected(), (Object) "GoogleApiClient must be connected.");
        zzaa.zza(googleApiClient.zza(f5554c), (Object) "GoogleApiClient is not configured to use the Plus.API Api. Pass this into GoogleApiClient.Builder#addApi() to use this feature.");
        boolean hasConnectedApi = googleApiClient.hasConnectedApi(f5554c);
        if (!z || hasConnectedApi) {
            return hasConnectedApi ? (d) googleApiClient.zza(f5552a) : null;
        } else {
            throw new IllegalStateException("GoogleApiClient has an optional Plus.API and is not connected to Plus. Use GoogleApiClient.hasConnectedApi(Plus.API) to guard this call.");
        }
    }
}

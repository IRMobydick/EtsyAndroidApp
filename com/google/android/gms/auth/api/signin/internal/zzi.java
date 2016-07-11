package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.os.Binder;
import com.google.android.gms.auth.api.a;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.zzf.zza;
import com.google.android.gms.common.api.Api$ApiOptions.HasOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.zze;

public class zzi extends zza {
    private final Context mContext;

    public zzi(Context context) {
        this.mContext = context;
    }

    private void zzpx() {
        if (!zze.zze(this.mContext, Binder.getCallingUid())) {
            throw new SecurityException("Calling UID " + Binder.getCallingUid() + " is not Google Play services.");
        }
    }

    private void zzpy() {
        C1147h a = C1147h.m6267a(this.mContext);
        GoogleSignInAccount a2 = a.m6269a();
        HasOptions hasOptions = GoogleSignInOptions.DEFAULT_SIGN_IN;
        if (a2 != null) {
            hasOptions = a.m6273b();
        }
        GoogleApiClient build = new Builder(this.mContext).addApi(a.g, hasOptions).build();
        try {
            if (build.blockingConnect().isSuccess()) {
                if (a2 != null) {
                    a.l.a(build);
                } else {
                    build.clearDefaultAccountAndReconnect();
                }
            }
            build.disconnect();
        } catch (Throwable th) {
            build.disconnect();
        }
    }

    public void zzpw() {
        zzpx();
        zzpy();
    }
}

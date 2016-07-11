package com.google.android.gms.ads.internal.request;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzd.zzb;
import com.google.android.gms.common.internal.zzd.zzc;
import com.google.android.gms.internal.dz;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.me;
import com.google.android.gms.internal.nb;

@jw
/* renamed from: com.google.android.gms.ads.internal.request.i */
public class C1122i extends C1120g implements zzb, zzc {
    protected C1123j f4634a;
    private Context f4635b;
    private VersionInfoParcel f4636c;
    private nb<AdRequestInfoParcel> f4637d;
    private final e f4638e;
    private final Object f4639f;
    private boolean f4640g;

    public C1122i(Context context, VersionInfoParcel versionInfoParcel, nb<AdRequestInfoParcel> nbVar, e eVar) {
        Looper a;
        super(nbVar, eVar);
        this.f4639f = new Object();
        this.f4635b = context;
        this.f4636c = versionInfoParcel;
        this.f4637d = nbVar;
        this.f4638e = eVar;
        if (((Boolean) dz.f4802B.m6433c()).booleanValue()) {
            this.f4640g = true;
            a = C1101o.m6053q().m7183a();
        } else {
            a = context.getMainLooper();
        }
        this.f4634a = new C1123j(context, a, this, this, this.f4636c.zzRD);
        m6132d();
    }

    public void m6130a() {
        synchronized (this.f4639f) {
            if (this.f4634a.isConnected() || this.f4634a.isConnecting()) {
                this.f4634a.disconnect();
            }
            Binder.flushPendingCommands();
            if (this.f4640g) {
                C1101o.m6053q().m7184b();
                this.f4640g = false;
            }
        }
    }

    public zzk m6131b() {
        zzk a;
        synchronized (this.f4639f) {
            try {
                a = this.f4634a.m6134a();
            } catch (IllegalStateException e) {
                a = null;
                return a;
            } catch (DeadObjectException e2) {
                a = null;
                return a;
            }
        }
        return a;
    }

    protected void m6132d() {
        this.f4634a.zztj();
    }

    me m6133e() {
        return new C1121h(this.f4635b, this.f4637d, this.f4638e);
    }

    public void onConnected(Bundle bundle) {
        Void voidR = (Void) zzhs();
    }

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        C1129c.m6185a("Cannot connect to remote service, fallback to local instance.");
        m6133e().zzhs();
        Bundle bundle = new Bundle();
        bundle.putString("action", "gms_connection_failed_fallback_to_local");
        C1101o.m6041e().m7122b(this.f4635b, this.f4636c.afmaVersion, "gmob-apps", bundle, true);
    }

    public void onConnectionSuspended(int i) {
        C1129c.m6185a("Disconnected from remote ad request service.");
    }

    public /* synthetic */ Object zzhs() {
        return super.m6127c();
    }
}

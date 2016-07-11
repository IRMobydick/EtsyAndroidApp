package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzaa;
import java.util.concurrent.atomic.AtomicReference;

public abstract class pq<R extends Result, A extends zzb> extends zznv<R> implements pr<R> {
    private final zzc<A> f5488a;
    private final Api<?> f5489b;
    private AtomicReference<rs> f5490c;

    @Deprecated
    protected pq(zzc<A> com_google_android_gms_common_api_Api_zzc_A, GoogleApiClient googleApiClient) {
        super((GoogleApiClient) zzaa.zzb((Object) googleApiClient, (Object) "GoogleApiClient must not be null"));
        this.f5490c = new AtomicReference();
        this.f5488a = (zzc) zzaa.zzz(com_google_android_gms_common_api_Api_zzc_A);
        this.f5489b = null;
    }

    protected pq(Api<?> api, GoogleApiClient googleApiClient) {
        super((GoogleApiClient) zzaa.zzb((Object) googleApiClient, (Object) "GoogleApiClient must not be null"));
        this.f5490c = new AtomicReference();
        this.f5488a = api.zzre();
        this.f5489b = api;
    }

    private void m7328a(RemoteException remoteException) {
        m7336b(new Status(8, remoteException.getLocalizedMessage(), null));
    }

    public final zzc<A> m7329a() {
        return this.f5488a;
    }

    protected abstract void m7330a(A a);

    protected void m7331a(R r) {
    }

    public void m7332a(rs rsVar) {
        this.f5490c.set(rsVar);
    }

    public /* synthetic */ void m7333a(Object obj) {
        super.zzb((Result) obj);
    }

    public final Api<?> m7334b() {
        return this.f5489b;
    }

    public final void m7335b(A a) {
        try {
            m7330a((zzb) a);
        } catch (RemoteException e) {
            m7328a(e);
            throw e;
        } catch (RemoteException e2) {
            m7328a(e2);
        }
    }

    public final void m7336b(Status status) {
        zzaa.zzb(!status.isSuccess(), (Object) "Failed result must not be success");
        Result zzc = zzc(status);
        zzb(zzc);
        m7331a(zzc);
    }

    public void m7337c() {
        setResultCallback(null);
    }

    protected void zzrE() {
        rs rsVar = (rs) this.f5490c.getAndSet(null);
        if (rsVar != null) {
            rsVar.a(this);
        }
    }
}

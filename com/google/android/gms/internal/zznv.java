package com.google.android.gms.internal;

import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResult.zza;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzr;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public abstract class zznv<R extends Result> extends PendingResult<R> {
    static final ThreadLocal<Boolean> zzalX;
    private boolean zzK;
    private final Object zzalY;
    protected final zza<R> zzalZ;
    private R zzals;
    protected final WeakReference<GoogleApiClient> zzama;
    private final ArrayList<zza> zzamb;
    private ResultCallback<? super R> zzamc;
    private pt zzamd;
    private volatile boolean zzame;
    private boolean zzamf;
    private zzr zzamg;
    private volatile ro<R> zzamh;
    private boolean zzami;
    private final CountDownLatch zzqF;

    static {
        zzalX = new 1();
    }

    @Deprecated
    zznv() {
        this.zzalY = new Object();
        this.zzqF = new CountDownLatch(1);
        this.zzamb = new ArrayList();
        this.zzami = false;
        this.zzalZ = new zza(Looper.getMainLooper());
        this.zzama = new WeakReference(null);
    }

    @Deprecated
    protected zznv(Looper looper) {
        this.zzalY = new Object();
        this.zzqF = new CountDownLatch(1);
        this.zzamb = new ArrayList();
        this.zzami = false;
        this.zzalZ = new zza(looper);
        this.zzama = new WeakReference(null);
    }

    protected zznv(GoogleApiClient googleApiClient) {
        this.zzalY = new Object();
        this.zzqF = new CountDownLatch(1);
        this.zzamb = new ArrayList();
        this.zzami = false;
        this.zzalZ = new zza(googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
        this.zzama = new WeakReference(googleApiClient);
    }

    private R get() {
        R r;
        boolean z = true;
        synchronized (this.zzalY) {
            if (this.zzame) {
                z = false;
            }
            zzaa.zza(z, (Object) "Result has already been consumed.");
            zzaa.zza(isReady(), (Object) "Result is not ready.");
            r = this.zzals;
            this.zzals = null;
            this.zzamc = null;
            this.zzame = true;
        }
        zzrE();
        return r;
    }

    private void zzc(R r) {
        this.zzals = r;
        this.zzamg = null;
        this.zzqF.countDown();
        Status status = this.zzals.getStatus();
        if (this.zzK) {
            this.zzamc = null;
        } else if (this.zzamc != null) {
            this.zzalZ.zzrL();
            this.zzalZ.zza(this.zzamc, get());
        } else if (this.zzals instanceof Releasable) {
            this.zzamd = new pt(this, null);
        }
        Iterator it = this.zzamb.iterator();
        while (it.hasNext()) {
            ((zza) it.next()).zzt(status);
        }
        this.zzamb.clear();
    }

    public static void zzd(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (Throwable e) {
                String valueOf = String.valueOf(result);
                Log.w("BasePendingResult", new StringBuilder(String.valueOf(valueOf).length() + 18).append("Unable to release ").append(valueOf).toString(), e);
            }
        }
    }

    public final R await() {
        boolean z = true;
        zzaa.zza(Looper.myLooper() != Looper.getMainLooper(), (Object) "await must not be called on the UI thread");
        zzaa.zza(!this.zzame, (Object) "Result has already been consumed");
        if (this.zzamh != null) {
            z = false;
        }
        zzaa.zza(z, (Object) "Cannot await if then() has been called.");
        try {
            this.zzqF.await();
        } catch (InterruptedException e) {
            zzy(Status.zzalx);
        }
        zzaa.zza(isReady(), (Object) "Result is not ready.");
        return get();
    }

    public final R await(long j, TimeUnit timeUnit) {
        boolean z = true;
        boolean z2 = j <= 0 || Looper.myLooper() != Looper.getMainLooper();
        zzaa.zza(z2, (Object) "await must not be called on the UI thread when time is greater than zero.");
        zzaa.zza(!this.zzame, (Object) "Result has already been consumed.");
        if (this.zzamh != null) {
            z = false;
        }
        zzaa.zza(z, (Object) "Cannot await if then() has been called.");
        try {
            if (!this.zzqF.await(j, timeUnit)) {
                zzy(Status.zzalz);
            }
        } catch (InterruptedException e) {
            zzy(Status.zzalx);
        }
        zzaa.zza(isReady(), (Object) "Result is not ready.");
        return get();
    }

    public void cancel() {
        synchronized (this.zzalY) {
            if (this.zzK || this.zzame) {
                return;
            }
            if (this.zzamg != null) {
                try {
                    this.zzamg.cancel();
                } catch (RemoteException e) {
                }
            }
            zzd(this.zzals);
            this.zzK = true;
            zzc(zzc(Status.zzalA));
        }
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this.zzalY) {
            z = this.zzK;
        }
        return z;
    }

    public final boolean isReady() {
        return this.zzqF.getCount() == 0;
    }

    public final void setResultCallback(ResultCallback<? super R> resultCallback) {
        boolean z = true;
        synchronized (this.zzalY) {
            if (resultCallback == null) {
                this.zzamc = null;
                return;
            }
            zzaa.zza(!this.zzame, (Object) "Result has already been consumed.");
            if (this.zzamh != null) {
                z = false;
            }
            zzaa.zza(z, (Object) "Cannot set callbacks if then() has been called.");
            if (isCanceled()) {
                return;
            }
            if (isReady()) {
                this.zzalZ.zza(resultCallback, get());
            } else {
                this.zzamc = resultCallback;
            }
            return;
        }
    }

    public final void setResultCallback(ResultCallback<? super R> resultCallback, long j, TimeUnit timeUnit) {
        boolean z = true;
        synchronized (this.zzalY) {
            if (resultCallback == null) {
                this.zzamc = null;
                return;
            }
            zzaa.zza(!this.zzame, (Object) "Result has already been consumed.");
            if (this.zzamh != null) {
                z = false;
            }
            zzaa.zza(z, (Object) "Cannot set callbacks if then() has been called.");
            if (isCanceled()) {
                return;
            }
            if (isReady()) {
                this.zzalZ.zza(resultCallback, get());
            } else {
                this.zzamc = resultCallback;
                this.zzalZ.zza(this, timeUnit.toMillis(j));
            }
            return;
        }
    }

    public <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> resultTransform) {
        TransformedResult<S> then;
        boolean z = true;
        zzaa.zza(!this.zzame, (Object) "Result has already been consumed.");
        synchronized (this.zzalY) {
            zzaa.zza(this.zzamh == null, (Object) "Cannot call then() twice.");
            if (this.zzamc != null) {
                z = false;
            }
            zzaa.zza(z, (Object) "Cannot call then() if callbacks are set.");
            this.zzami = true;
            this.zzamh = new ro(this.zzama);
            then = this.zzamh.then(resultTransform);
            if (isReady()) {
                this.zzalZ.zza(this.zzamh, get());
            } else {
                this.zzamc = this.zzamh;
            }
        }
        return then;
    }

    public final void zza(zza com_google_android_gms_common_api_PendingResult_zza) {
        boolean z = true;
        zzaa.zza(!this.zzame, (Object) "Result has already been consumed.");
        if (com_google_android_gms_common_api_PendingResult_zza == null) {
            z = false;
        }
        zzaa.zzb(z, (Object) "Callback cannot be null.");
        synchronized (this.zzalY) {
            if (isReady()) {
                com_google_android_gms_common_api_PendingResult_zza.zzt(this.zzals.getStatus());
            } else {
                this.zzamb.add(com_google_android_gms_common_api_PendingResult_zza);
            }
        }
    }

    protected final void zza(zzr com_google_android_gms_common_internal_zzr) {
        synchronized (this.zzalY) {
            this.zzamg = com_google_android_gms_common_internal_zzr;
        }
    }

    public final void zzb(R r) {
        boolean z = true;
        synchronized (this.zzalY) {
            if (this.zzamf || this.zzK || (isReady() && zzrJ())) {
                zzd(r);
                return;
            }
            zzaa.zza(!isReady(), (Object) "Results have already been set");
            if (this.zzame) {
                z = false;
            }
            zzaa.zza(z, (Object) "Result has already been consumed");
            zzc((Result) r);
        }
    }

    protected abstract R zzc(Status status);

    protected void zzrE() {
    }

    public boolean zzrH() {
        boolean isCanceled;
        synchronized (this.zzalY) {
            if (((GoogleApiClient) this.zzama.get()) == null || !this.zzami) {
                cancel();
            }
            isCanceled = isCanceled();
        }
        return isCanceled;
    }

    public void zzrI() {
        boolean z = this.zzami || ((Boolean) zzalX.get()).booleanValue();
        this.zzami = z;
    }

    boolean zzrJ() {
        return false;
    }

    public Integer zzrv() {
        return null;
    }

    public final void zzy(Status status) {
        synchronized (this.zzalY) {
            if (!isReady()) {
                zzb(zzc(status));
                this.zzamf = true;
            }
        }
    }
}

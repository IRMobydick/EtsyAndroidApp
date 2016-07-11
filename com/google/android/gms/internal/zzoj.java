package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.WorkerThread;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import com.etsy.android.uikit.view.ListingFullImageView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzc;
import com.google.android.gms.common.util.zza;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.tasks.b;
import java.lang.ref.ReferenceQueue;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class zzoj implements Callback {
    private static zzoj zzanL;
    private static final Object zzrs;
    private final Context mContext;
    private final Handler mHandler;
    private final GoogleApiAvailability zzaln;
    private long zzanK;
    private int zzanM;
    private final AtomicInteger zzanN;
    private final SparseArray<qt<?>> zzanO;
    private final Map<pm<?>, qt<?>> zzanP;
    private zzny zzanQ;
    private final Set<pm<?>> zzanR;
    private final ReferenceQueue<zzc<?>> zzanS;
    private final SparseArray<qr> zzanT;
    private qs zzanU;
    private long zzanj;
    private long zzank;

    static {
        zzrs = new Object();
    }

    private zzoj(Context context) {
        this(context, GoogleApiAvailability.getInstance());
    }

    zzoj(Context context, GoogleApiAvailability googleApiAvailability) {
        this.zzank = 5000;
        this.zzanj = 120000;
        this.zzanK = 10000;
        this.zzanM = -1;
        this.zzanN = new AtomicInteger(1);
        this.zzanO = new SparseArray();
        this.zzanP = new ConcurrentHashMap(5, ListingFullImageView.ASPECT_RATIO_STANDARD, 1);
        this.zzanQ = null;
        this.zzanR = new zza();
        this.zzanS = new ReferenceQueue();
        this.zzanT = new SparseArray();
        this.mContext = context;
        HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper(), this);
        this.zzaln = googleApiAvailability;
    }

    private int zza(zzc<?> com_google_android_gms_common_api_zzc_) {
        int andIncrement = this.zzanN.getAndIncrement();
        this.mHandler.sendMessage(this.mHandler.obtainMessage(6, andIncrement, 0, com_google_android_gms_common_api_zzc_));
        return andIncrement;
    }

    public static Pair<zzoj, Integer> zza(Context context, zzc<?> com_google_android_gms_common_api_zzc_) {
        Pair<zzoj, Integer> create;
        synchronized (zzrs) {
            if (zzanL == null) {
                zzanL = new zzoj(context.getApplicationContext());
            }
            create = Pair.create(zzanL, Integer.valueOf(zzanL.zza((zzc) com_google_android_gms_common_api_zzc_)));
        }
        return create;
    }

    @WorkerThread
    private void zza(zzc<?> com_google_android_gms_common_api_zzc_, int i) {
        pm zzrn = com_google_android_gms_common_api_zzc_.zzrn();
        if (!this.zzanP.containsKey(zzrn)) {
            this.zzanP.put(zzrn, new qt(this, com_google_android_gms_common_api_zzc_));
        }
        qt qtVar = (qt) this.zzanP.get(zzrn);
        qtVar.a(i);
        this.zzanO.put(i, qtVar);
        qt.a(qtVar);
        this.zzanT.put(i, new qr(this, com_google_android_gms_common_api_zzc_, i, this.zzanS));
        if (this.zzanU == null || !qs.a(this.zzanU).get()) {
            this.zzanU = new qs(this.zzanS, this.zzanT);
            this.zzanU.start();
        }
    }

    @WorkerThread
    private void zza(pj pjVar) {
        ((qt) this.zzanO.get(pjVar.a)).a(pjVar);
    }

    @WorkerThread
    private void zze(int i, boolean z) {
        qt qtVar = (qt) this.zzanO.get(i);
        if (qtVar != null) {
            if (!z) {
                this.zzanO.delete(i);
            }
            qtVar.a(i, z);
            return;
        }
        Log.wtf("GoogleApiManager", "onRelease received for unknown instance: " + i, new Exception());
    }

    public static zzoj zzsq() {
        zzoj com_google_android_gms_internal_zzoj;
        synchronized (zzrs) {
            com_google_android_gms_internal_zzoj = zzanL;
        }
        return com_google_android_gms_internal_zzoj;
    }

    @WorkerThread
    private void zzsr() {
        for (qt qtVar : this.zzanP.values()) {
            qtVar.b();
            qt.a(qtVar);
        }
    }

    @WorkerThread
    public boolean handleMessage(Message message) {
        boolean z = false;
        switch (message.what) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                zza((po) message.obj);
                break;
            case Task.NETWORK_STATE_ANY /*2*/:
            case CommonStatusCodes.NETWORK_ERROR /*7*/:
                int i = message.arg1;
                if (message.arg2 == 1) {
                    z = true;
                }
                zze(i, z);
                break;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                zzsr();
                break;
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                zza((pj) message.obj);
                break;
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                if (this.zzanO.get(message.arg1) != null) {
                    qt.a((qt) this.zzanO.get(message.arg1), new Status(17, "Error resolution was canceled by the user."));
                    break;
                }
                break;
            case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                zza((zzc) message.obj, message.arg1);
                break;
            case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                if (this.zzanP.containsKey(message.obj)) {
                    qt.b((qt) this.zzanP.get(message.obj));
                    break;
                }
                break;
            case CommonStatusCodes.SERVICE_INVALID /*9*/:
                if (this.zzanP.containsKey(message.obj)) {
                    qt.c((qt) this.zzanP.get(message.obj));
                    break;
                }
                break;
            case CommonStatusCodes.DEVELOPER_ERROR /*10*/:
                if (this.zzanP.containsKey(message.obj)) {
                    qt.d((qt) this.zzanP.get(message.obj));
                    break;
                }
                break;
            default:
                Log.w("GoogleApiManager", "Unknown message id: " + message.what);
                return false;
        }
        return true;
    }

    public void zza(ConnectionResult connectionResult, int i) {
        if (!zzc(connectionResult, i)) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(5, i, 0));
        }
    }

    public <O extends Api$ApiOptions> void zza(zzc<O> com_google_android_gms_common_api_zzc_O, int i, pq<? extends Result, zzb> pqVar) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, new pk(com_google_android_gms_common_api_zzc_O.getInstanceId(), i, pqVar)));
    }

    public <O extends Api$ApiOptions, TResult> void zza(zzc<O> com_google_android_gms_common_api_zzc_O, int i, rn<zzb, TResult> rnVar, b<TResult> bVar) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, new pl(com_google_android_gms_common_api_zzc_O.getInstanceId(), i, rnVar, bVar)));
    }

    @WorkerThread
    public void zza(po poVar) {
        for (pm pmVar : poVar.a()) {
            qt qtVar = (qt) this.zzanP.get(pmVar);
            if (qtVar == null) {
                poVar.cancel();
                return;
            } else if (qtVar.d()) {
                poVar.a(pmVar, ConnectionResult.zzakj);
            } else if (qtVar.c() != null) {
                poVar.a(pmVar, qtVar.c());
            } else {
                qtVar.a(poVar);
            }
        }
    }

    public void zza(zzny com_google_android_gms_internal_zzny) {
        synchronized (zzrs) {
            if (com_google_android_gms_internal_zzny == null) {
                this.zzanQ = null;
                this.zzanR.clear();
            }
        }
    }

    boolean zzc(ConnectionResult connectionResult, int i) {
        if (!connectionResult.hasResolution() && !this.zzaln.isUserResolvableError(connectionResult.getErrorCode())) {
            return false;
        }
        this.zzaln.zza(this.mContext, connectionResult, i);
        return true;
    }

    public void zzd(int i, boolean z) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(7, i, z ? 1 : 2));
    }

    public void zzrA() {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3));
    }
}

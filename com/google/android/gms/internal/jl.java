package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.zzq;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@jw
public class jl extends ln {
    private final jh f5172a;
    private final AdResponseParcel f5173b;
    private final lc f5174c;
    private final jp f5175d;
    private final Object f5176e;
    private Future<lb> f5177f;

    public jl(Context context, zzq com_google_android_gms_ads_internal_zzq, lc lcVar, bu buVar, jh jhVar) {
        this(lcVar, jhVar, new jp(context, com_google_android_gms_ads_internal_zzq, new mk(context), buVar, lcVar));
    }

    jl(lc lcVar, jh jhVar, jp jpVar) {
        this.f5176e = new Object();
        this.f5174c = lcVar;
        this.f5173b = lcVar.f5353b;
        this.f5172a = jhVar;
        this.f5175d = jpVar;
    }

    private lb m6834a(int i) {
        return new lb(this.f5174c.f5352a.zzLi, null, null, i, null, null, this.f5173b.orientation, this.f5173b.zzEL, this.f5174c.f5352a.zzLl, false, null, null, null, null, null, this.f5173b.zzLQ, this.f5174c.f5355d, this.f5173b.zzLO, this.f5174c.f5357f, this.f5173b.zzLT, this.f5173b.zzLU, this.f5174c.f5359h, null, null, null, null, this.f5174c.f5353b.zzMi, this.f5174c.f5353b.zzMj, null, null);
    }

    public void onStop() {
        synchronized (this.f5176e) {
            if (this.f5177f != null) {
                this.f5177f.cancel(true);
            }
        }
    }

    public void zzbQ() {
        lb lbVar;
        int i;
        try {
            synchronized (this.f5176e) {
                this.f5177f = ls.m7071a(this.f5175d);
            }
            lbVar = (lb) this.f5177f.get(60000, TimeUnit.MILLISECONDS);
            i = -2;
        } catch (TimeoutException e) {
            C1129c.m6192d("Timed out waiting for native ad.");
            this.f5177f.cancel(true);
            i = 2;
            lbVar = null;
        } catch (ExecutionException e2) {
            lbVar = null;
            i = 0;
        } catch (InterruptedException e3) {
            lbVar = null;
            i = 0;
        } catch (CancellationException e4) {
            lbVar = null;
            i = 0;
        }
        if (lbVar == null) {
            lbVar = m6834a(i);
        }
        lt.f5423a.post(new 1(this, lbVar));
    }
}

package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.adjust.sdk.Constants;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.request.AdResponseParcel;

@jw
public class zzhs implements Runnable {
    protected final no f5491a;
    protected boolean f5492b;
    protected boolean f5493c;
    private final Handler f5494d;
    private final long f5495e;
    private long f5496f;
    private np f5497g;
    private final int f5498h;
    private final int f5499i;

    public zzhs(np npVar, no noVar, int i, int i2) {
        this(npVar, noVar, i, i2, 200, 50);
    }

    public zzhs(np npVar, no noVar, int i, int i2, long j, long j2) {
        this.f5495e = j;
        this.f5496f = j2;
        this.f5494d = new Handler(Looper.getMainLooper());
        this.f5491a = noVar;
        this.f5497g = npVar;
        this.f5492b = false;
        this.f5493c = false;
        this.f5498h = i2;
        this.f5499i = i;
    }

    static /* synthetic */ long m7341c(zzhs com_google_android_gms_internal_zzhs) {
        long j = com_google_android_gms_internal_zzhs.f5496f - 1;
        com_google_android_gms_internal_zzhs.f5496f = j;
        return j;
    }

    public void m7346a() {
        this.f5494d.postDelayed(this, this.f5495e);
    }

    public void m7347a(AdResponseParcel adResponseParcel) {
        m7348a(adResponseParcel, new zzlk(this, this.f5491a, adResponseParcel.zzLW));
    }

    public void m7348a(AdResponseParcel adResponseParcel, zzlk com_google_android_gms_internal_zzlk) {
        this.f5491a.setWebViewClient(com_google_android_gms_internal_zzlk);
        this.f5491a.loadDataWithBaseURL(TextUtils.isEmpty(adResponseParcel.zzHH) ? null : C1101o.m6041e().m7095a(adResponseParcel.zzHH), adResponseParcel.body, "text/html", Constants.ENCODING, null);
    }

    public synchronized void m7349b() {
        this.f5492b = true;
    }

    public synchronized boolean m7350c() {
        return this.f5492b;
    }

    public boolean m7351d() {
        return this.f5493c;
    }

    public void run() {
        if (this.f5491a == null || m7350c()) {
            this.f5497g.a(this.f5491a, true);
        } else {
            new zza(this, this.f5491a.m7235a()).execute(new Void[0]);
        }
    }
}

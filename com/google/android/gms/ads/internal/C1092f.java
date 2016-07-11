package com.google.android.gms.ads.internal;

import android.content.Context;
import android.view.MotionEvent;
import com.google.android.gms.ads.internal.client.C1089r;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.internal.aq;
import com.google.android.gms.internal.bt;
import com.google.android.gms.internal.dz;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.ls;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.lang3.StringUtils;

@jw
/* renamed from: com.google.android.gms.ads.internal.f */
class C1092f implements aq, Runnable {
    CountDownLatch f4476a;
    private final List<Object[]> f4477b;
    private final AtomicReference<aq> f4478c;
    private zzv f4479d;

    public C1092f(zzv com_google_android_gms_ads_internal_zzv) {
        this.f4477b = new Vector();
        this.f4478c = new AtomicReference();
        this.f4476a = new CountDownLatch(1);
        this.f4479d = com_google_android_gms_ads_internal_zzv;
        if (C1089r.m5951a().m6180b()) {
            ls.m7070a((Runnable) this);
        } else {
            run();
        }
    }

    private Context m5965b(Context context) {
        if (!((Boolean) dz.f4840n.m6433c()).booleanValue()) {
            return context;
        }
        Context applicationContext = context.getApplicationContext();
        return applicationContext != null ? applicationContext : context;
    }

    private void m5966b() {
        if (!this.f4477b.isEmpty()) {
            for (Object[] objArr : this.f4477b) {
                if (objArr.length == 1) {
                    ((aq) this.f4478c.get()).a((MotionEvent) objArr[0]);
                } else if (objArr.length == 3) {
                    ((aq) this.f4478c.get()).a(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue(), ((Integer) objArr[2]).intValue());
                }
            }
            this.f4477b.clear();
        }
    }

    protected aq m5967a(String str, Context context, boolean z) {
        return bt.a(str, context, z);
    }

    public String m5968a(Context context) {
        if (m5973a()) {
            aq aqVar = (aq) this.f4478c.get();
            if (aqVar != null) {
                m5966b();
                return aqVar.a(m5965b(context));
            }
        }
        return StringUtils.EMPTY;
    }

    public String m5969a(Context context, String str) {
        if (m5973a()) {
            aq aqVar = (aq) this.f4478c.get();
            if (aqVar != null) {
                m5966b();
                return aqVar.a(m5965b(context), str);
            }
        }
        return StringUtils.EMPTY;
    }

    public void m5970a(int i, int i2, int i3) {
        aq aqVar = (aq) this.f4478c.get();
        if (aqVar != null) {
            m5966b();
            aqVar.a(i, i2, i3);
            return;
        }
        this.f4477b.add(new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
    }

    public void m5971a(MotionEvent motionEvent) {
        aq aqVar = (aq) this.f4478c.get();
        if (aqVar != null) {
            m5966b();
            aqVar.a(motionEvent);
            return;
        }
        this.f4477b.add(new Object[]{motionEvent});
    }

    protected void m5972a(aq aqVar) {
        this.f4478c.set(aqVar);
    }

    protected boolean m5973a() {
        try {
            this.f4476a.await();
            return true;
        } catch (Throwable e) {
            C1129c.m6193d("Interrupted during GADSignals creation.", e);
            return false;
        }
    }

    public void run() {
        try {
            boolean z = !((Boolean) dz.f4852z.m6433c()).booleanValue() || this.f4479d.zzsx.zzRE;
            m5972a(m5967a(this.f4479d.zzsx.afmaVersion, m5965b(this.f4479d.zzov), z));
        } finally {
            this.f4476a.countDown();
            this.f4479d = null;
        }
    }
}

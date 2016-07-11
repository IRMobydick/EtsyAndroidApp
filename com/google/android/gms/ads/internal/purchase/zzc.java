package com.google.android.gms.ads.internal.purchase;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.SystemClock;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.stats.zzb;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.ln;
import com.google.android.gms.internal.lo;
import com.google.android.gms.internal.lt;
import com.google.android.gms.internal.zzhl;
import java.util.List;

@jw
public class zzc extends ln implements ServiceConnection {
    private Context mContext;
    private zzhl zzDw;
    private boolean zzJj;
    private C1112a zzJk;
    private zzh zzJl;
    private List<C1113b> zzJm;
    private C1115e zzJn;
    private final Object zzpp;

    public zzc(Context context, zzhl com_google_android_gms_internal_zzhl, C1115e c1115e) {
        this(context, com_google_android_gms_internal_zzhl, c1115e, new C1112a(context), zzh.m6109a(context.getApplicationContext()));
    }

    zzc(Context context, zzhl com_google_android_gms_internal_zzhl, C1115e c1115e, C1112a c1112a, zzh com_google_android_gms_ads_internal_purchase_zzh) {
        this.zzpp = new Object();
        this.zzJj = false;
        this.zzJm = null;
        this.mContext = context;
        this.zzDw = com_google_android_gms_internal_zzhl;
        this.zzJn = c1115e;
        this.zzJk = c1112a;
        this.zzJl = com_google_android_gms_ads_internal_purchase_zzh;
        this.zzJm = this.zzJl.m6113a(10);
    }

    private void zze(long j) {
        do {
            if (!zzf(j)) {
                lo.m7056e("Timeout waiting for pending transaction to be processed.");
            }
        } while (!this.zzJj);
    }

    private boolean zzf(long j) {
        long elapsedRealtime = 60000 - (SystemClock.elapsedRealtime() - j);
        if (elapsedRealtime <= 0) {
            return false;
        }
        try {
            this.zzpp.wait(elapsedRealtime);
        } catch (InterruptedException e) {
            C1129c.m6192d("waitWithTimeout_lock interrupted");
        }
        return true;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.zzpp) {
            this.zzJk.m6094a(iBinder);
            zzhl();
            this.zzJj = true;
            this.zzpp.notify();
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        C1129c.m6190c("In-app billing service disconnected.");
        this.zzJk.m6093a();
    }

    public void onStop() {
        synchronized (this.zzpp) {
            zzb.zzuH().zza(this.mContext, this);
            this.zzJk.m6093a();
        }
    }

    protected void zza(C1113b c1113b, String str, String str2) {
        Intent intent = new Intent();
        C1101o.m6051o();
        intent.putExtra("RESPONSE_CODE", 0);
        C1101o.m6051o();
        intent.putExtra("INAPP_PURCHASE_DATA", str);
        C1101o.m6051o();
        intent.putExtra("INAPP_DATA_SIGNATURE", str2);
        lt.f5423a.post(new 1(this, c1113b, intent));
    }

    public void zzbQ() {
        synchronized (this.zzpp) {
            Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            intent.setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_STORE_PACKAGE);
            zzb.zzuH().zza(this.mContext, intent, this, 1);
            zze(SystemClock.elapsedRealtime());
            zzb.zzuH().zza(this.mContext, this);
            this.zzJk.m6093a();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void zzhl() {
        /*
        r12 = this;
        r0 = r12.zzJm;
        r0 = r0.isEmpty();
        if (r0 == 0) goto L_0x0009;
    L_0x0008:
        return;
    L_0x0009:
        r6 = new java.util.HashMap;
        r6.<init>();
        r0 = r12.zzJm;
        r1 = r0.iterator();
    L_0x0014:
        r0 = r1.hasNext();
        if (r0 == 0) goto L_0x0026;
    L_0x001a:
        r0 = r1.next();
        r0 = (com.google.android.gms.ads.internal.purchase.C1113b) r0;
        r2 = r0.f4586c;
        r6.put(r2, r0);
        goto L_0x0014;
    L_0x0026:
        r0 = 0;
    L_0x0027:
        r1 = r12.zzJk;
        r2 = r12.mContext;
        r2 = r2.getPackageName();
        r0 = r1.m6095b(r2, r0);
        if (r0 != 0) goto L_0x0055;
    L_0x0035:
        r0 = r6.keySet();
        r1 = r0.iterator();
    L_0x003d:
        r0 = r1.hasNext();
        if (r0 == 0) goto L_0x0008;
    L_0x0043:
        r0 = r1.next();
        r0 = (java.lang.String) r0;
        r2 = r12.zzJl;
        r0 = r6.get(r0);
        r0 = (com.google.android.gms.ads.internal.purchase.C1113b) r0;
        r2.m6114a(r0);
        goto L_0x003d;
    L_0x0055:
        r1 = com.google.android.gms.ads.internal.C1101o.m6051o();
        r1 = r1.m6097a(r0);
        if (r1 != 0) goto L_0x0035;
    L_0x005f:
        r1 = "INAPP_PURCHASE_ITEM_LIST";
        r7 = r0.getStringArrayList(r1);
        r1 = "INAPP_PURCHASE_DATA_LIST";
        r8 = r0.getStringArrayList(r1);
        r1 = "INAPP_DATA_SIGNATURE_LIST";
        r9 = r0.getStringArrayList(r1);
        r1 = "INAPP_CONTINUATION_TOKEN";
        r5 = r0.getString(r1);
        r0 = 0;
        r4 = r0;
    L_0x0079:
        r0 = r7.size();
        if (r4 >= r0) goto L_0x00bb;
    L_0x007f:
        r0 = r7.get(r4);
        r0 = r6.containsKey(r0);
        if (r0 == 0) goto L_0x00b7;
    L_0x0089:
        r0 = r7.get(r4);
        r0 = (java.lang.String) r0;
        r1 = r8.get(r4);
        r1 = (java.lang.String) r1;
        r2 = r9.get(r4);
        r2 = (java.lang.String) r2;
        r3 = r6.get(r0);
        r3 = (com.google.android.gms.ads.internal.purchase.C1113b) r3;
        r10 = com.google.android.gms.ads.internal.C1101o.m6051o();
        r10 = r10.m6098a(r1);
        r11 = r3.f4585b;
        r10 = r11.equals(r10);
        if (r10 == 0) goto L_0x00b7;
    L_0x00b1:
        r12.zza(r3, r1, r2);
        r6.remove(r0);
    L_0x00b7:
        r0 = r4 + 1;
        r4 = r0;
        goto L_0x0079;
    L_0x00bb:
        if (r5 == 0) goto L_0x0035;
    L_0x00bd:
        r0 = r6.isEmpty();
        if (r0 != 0) goto L_0x0035;
    L_0x00c3:
        r0 = r5;
        goto L_0x0027;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.purchase.zzc.zzhl():void");
    }
}

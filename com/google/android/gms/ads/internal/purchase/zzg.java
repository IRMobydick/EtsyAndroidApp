package com.google.android.gms.ads.internal.purchase;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.stats.zzb;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.zzhk.zza;

@jw
public final class zzg extends zza implements ServiceConnection {
    private Context mContext;
    private int mResultCode;
    private boolean zzJA;
    private Intent zzJB;
    C1112a zzJk;
    private String zzJq;
    private C1113b zzJu;

    public zzg(Context context, String str, boolean z, int i, Intent intent, C1113b c1113b) {
        this.zzJA = false;
        this.zzJq = str;
        this.mResultCode = i;
        this.zzJB = intent;
        this.zzJA = z;
        this.mContext = context;
        this.zzJu = c1113b;
    }

    public void finishPurchase() {
        int a = C1101o.m6051o().m6096a(this.zzJB);
        if (this.mResultCode == -1 && a == 0) {
            this.zzJk = new C1112a(this.mContext);
            Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            intent.setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_STORE_PACKAGE);
            zzb.zzuH().zza(this.mContext, intent, this, 1);
        }
    }

    public String getProductId() {
        return this.zzJq;
    }

    public Intent getPurchaseData() {
        return this.zzJB;
    }

    public int getResultCode() {
        return this.mResultCode;
    }

    public boolean isVerified() {
        return this.zzJA;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        C1129c.m6190c("In-app billing service connected.");
        this.zzJk.m6094a(iBinder);
        String b = C1101o.m6051o().m6102b(C1101o.m6051o().m6101b(this.zzJB));
        if (b != null) {
            if (this.zzJk.m6091a(this.mContext.getPackageName(), b) == 0) {
                zzh.m6109a(this.mContext).m6114a(this.zzJu);
            }
            zzb.zzuH().zza(this.mContext, this);
            this.zzJk.m6093a();
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        C1129c.m6190c("In-app billing service disconnected.");
        this.zzJk.m6093a();
    }
}

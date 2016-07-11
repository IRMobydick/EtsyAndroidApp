package com.google.android.gms.ads.internal.purchase;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.zzhg;
import com.google.android.gms.internal.zzhi.zza;

@jw
public class zze extends zza implements ServiceConnection {
    private final Activity mActivity;
    private C1112a zzJk;
    zzh zzJl;
    private C1115e zzJn;
    private Context zzJs;
    private zzhg zzJt;
    private C1113b zzJu;
    private d zzJv;
    private String zzJw;

    public zze(Activity activity) {
        this.zzJw = null;
        this.mActivity = activity;
        this.zzJl = zzh.m6109a(this.mActivity.getApplicationContext());
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1001) {
            boolean z = false;
            try {
                int a = C1101o.m6051o().m6096a(intent);
                if (i2 == -1) {
                    C1101o.m6051o();
                    if (a == 0) {
                        if (this.zzJn.m6105a(this.zzJw, i2, intent)) {
                            z = true;
                        }
                        this.zzJt.recordPlayBillingResolution(a);
                        this.mActivity.finish();
                        zza(this.zzJt.getProductId(), z, i2, intent);
                    }
                }
                this.zzJl.m6114a(this.zzJu);
                this.zzJt.recordPlayBillingResolution(a);
                this.mActivity.finish();
                zza(this.zzJt.getProductId(), z, i2, intent);
            } catch (RemoteException e) {
                C1129c.m6192d("Fail to process purchase result.");
                this.mActivity.finish();
            } finally {
                this.zzJw = null;
            }
        }
    }

    public void onCreate() {
        GInAppPurchaseManagerInfoParcel zzc = GInAppPurchaseManagerInfoParcel.zzc(this.mActivity.getIntent());
        this.zzJv = zzc.zzJg;
        this.zzJn = zzc.zzsU;
        this.zzJt = zzc.zzJe;
        this.zzJk = new C1112a(this.mActivity.getApplicationContext());
        this.zzJs = zzc.zzJf;
        if (this.mActivity.getResources().getConfiguration().orientation == 2) {
            this.mActivity.setRequestedOrientation(C1101o.m6043g().m7146a());
        } else {
            this.mActivity.setRequestedOrientation(C1101o.m6043g().m7160b());
        }
        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_STORE_PACKAGE);
        this.mActivity.bindService(intent, this, 1);
    }

    public void onDestroy() {
        this.mActivity.unbindService(this);
        this.zzJk.m6093a();
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Throwable e;
        this.zzJk.m6094a(iBinder);
        try {
            this.zzJw = this.zzJn.m6104a();
            Bundle a = this.zzJk.m6092a(this.mActivity.getPackageName(), this.zzJt.getProductId(), this.zzJw);
            PendingIntent pendingIntent = (PendingIntent) a.getParcelable("BUY_INTENT");
            if (pendingIntent == null) {
                int a2 = C1101o.m6051o().m6097a(a);
                this.zzJt.recordPlayBillingResolution(a2);
                zza(this.zzJt.getProductId(), false, a2, null);
                this.mActivity.finish();
                return;
            }
            this.zzJu = new C1113b(this.zzJt.getProductId(), this.zzJw);
            this.zzJl.m6116b(this.zzJu);
            this.mActivity.startIntentSenderForResult(pendingIntent.getIntentSender(), 1001, new Intent(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue());
        } catch (RemoteException e2) {
            e = e2;
            C1129c.m6193d("Error when connecting in-app billing service", e);
            this.mActivity.finish();
        } catch (SendIntentException e3) {
            e = e3;
            C1129c.m6193d("Error when connecting in-app billing service", e);
            this.mActivity.finish();
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        C1129c.m6190c("In-app billing service disconnected.");
        this.zzJk.m6093a();
    }

    protected void zza(String str, boolean z, int i, Intent intent) {
        if (this.zzJv != null) {
            this.zzJv.zza(str, z, i, intent, this.zzJu);
        }
    }
}

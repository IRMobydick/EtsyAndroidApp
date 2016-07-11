package com.google.android.gms.ads.purchase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.ads.internal.client.C1089r;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.internal.zzhi;

public class InAppPurchaseActivity extends Activity {
    public static final String CLASS_NAME = "com.google.android.gms.ads.purchase.InAppPurchaseActivity";
    public static final String SIMPLE_CLASS_NAME = "InAppPurchaseActivity";
    private zzhi zzTo;

    protected void onActivityResult(int i, int i2, Intent intent) {
        try {
            if (this.zzTo != null) {
                this.zzTo.onActivityResult(i, i2, intent);
            }
        } catch (Throwable e) {
            C1129c.m6193d("Could not forward onActivityResult to in-app purchase manager:", e);
        }
        super.onActivityResult(i, i2, intent);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzTo = C1089r.m5953b().m5948a((Activity) this);
        if (this.zzTo == null) {
            C1129c.m6192d("Could not create in-app purchase manager.");
            finish();
            return;
        }
        try {
            this.zzTo.onCreate();
        } catch (Throwable e) {
            C1129c.m6193d("Could not forward onCreate to in-app purchase manager:", e);
            finish();
        }
    }

    protected void onDestroy() {
        try {
            if (this.zzTo != null) {
                this.zzTo.onDestroy();
            }
        } catch (Throwable e) {
            C1129c.m6193d("Could not forward onDestroy to in-app purchase manager:", e);
        }
        super.onDestroy();
    }
}

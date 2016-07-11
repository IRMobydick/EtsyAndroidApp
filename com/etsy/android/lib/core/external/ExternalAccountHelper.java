package com.etsy.android.lib.core.external;

import android.app.Activity;
import android.content.Intent;
import com.etsy.android.lib.util.ExternalAccountUtil.AccountType;
import com.etsy.android.lib.util.ae;
import java.util.ArrayList;

/* renamed from: com.etsy.android.lib.core.external.b */
public abstract class ExternalAccountHelper {
    protected Activity f1481a;
    protected ArrayList<ExternalAccountListener> f1482b;

    public abstract ExternalAccountProfile m1218a();

    public abstract void m1219a(int i, int i2, Intent intent);

    public abstract void m1221a(ae aeVar, boolean z);

    public abstract void m1222a(String str, ae aeVar);

    public abstract boolean m1223a(AccountType accountType);

    public abstract void m1224b();

    public abstract void m1226c();

    public abstract void m1227d();

    public abstract void m1228e();

    public abstract void m1229f();

    public abstract void m1230g();

    public ExternalAccountHelper(Activity activity) {
        this.f1482b = new ArrayList();
        this.f1481a = activity;
    }

    public void m1220a(ExternalAccountListener externalAccountListener) {
        this.f1482b.add(externalAccountListener);
    }

    public void m1225b(ExternalAccountListener externalAccountListener) {
        this.f1482b.remove(externalAccountListener);
    }
}

package com.google.android.gms.ads.internal.purchase;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.internal.jw;

@jw
/* renamed from: com.google.android.gms.ads.internal.purchase.a */
public class C1112a {
    Object f4581a;
    private final Context f4582b;
    private final boolean f4583c;

    public C1112a(Context context) {
        this(context, true);
    }

    public C1112a(Context context, boolean z) {
        this.f4582b = context;
        this.f4583c = z;
    }

    public int m6090a(int i, String str, String str2) {
        try {
            Class loadClass = this.f4582b.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
            return ((Integer) loadClass.getDeclaredMethod("isBillingSupported", new Class[]{Integer.TYPE, String.class, String.class}).invoke(loadClass.cast(this.f4581a), new Object[]{Integer.valueOf(i), str, str2})).intValue();
        } catch (Throwable e) {
            if (this.f4583c) {
                C1129c.m6193d("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", e);
            }
            return 5;
        }
    }

    public int m6091a(String str, String str2) {
        try {
            Class loadClass = this.f4582b.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
            return ((Integer) loadClass.getDeclaredMethod("consumePurchase", new Class[]{Integer.TYPE, String.class, String.class}).invoke(loadClass.cast(this.f4581a), new Object[]{Integer.valueOf(3), str, str2})).intValue();
        } catch (Throwable e) {
            if (this.f4583c) {
                C1129c.m6193d("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", e);
            }
            return 5;
        }
    }

    public Bundle m6092a(String str, String str2, String str3) {
        try {
            Class loadClass = this.f4582b.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
            return (Bundle) loadClass.getDeclaredMethod("getBuyIntent", new Class[]{Integer.TYPE, String.class, String.class, String.class, String.class}).invoke(loadClass.cast(this.f4581a), new Object[]{Integer.valueOf(3), str, str2, "inapp", str3});
        } catch (Throwable e) {
            if (this.f4583c) {
                C1129c.m6193d("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", e);
            }
            return null;
        }
    }

    public void m6093a() {
        this.f4581a = null;
    }

    public void m6094a(IBinder iBinder) {
        try {
            this.f4581a = this.f4582b.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService$Stub").getDeclaredMethod("asInterface", new Class[]{IBinder.class}).invoke(null, new Object[]{iBinder});
        } catch (Exception e) {
            if (this.f4583c) {
                C1129c.m6192d("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.");
            }
        }
    }

    public Bundle m6095b(String str, String str2) {
        try {
            Class loadClass = this.f4582b.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
            return (Bundle) loadClass.getDeclaredMethod("getPurchases", new Class[]{Integer.TYPE, String.class, String.class, String.class}).invoke(loadClass.cast(this.f4581a), new Object[]{Integer.valueOf(3), str, "inapp", str2});
        } catch (Throwable e) {
            if (this.f4583c) {
                C1129c.m6193d("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", e);
            }
            return null;
        }
    }
}

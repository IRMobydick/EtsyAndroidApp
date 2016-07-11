package com.etsy.android.lib.core;

import android.os.AsyncTask;

/* renamed from: com.etsy.android.lib.core.d */
class EtsyApplication extends AsyncTask<String, Void, Void> {
    final /* synthetic */ EtsyApplication f1461a;

    EtsyApplication(EtsyApplication etsyApplication) {
        this.f1461a = etsyApplication;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m1167a((String[]) objArr);
    }

    protected Void m1167a(String... strArr) {
        if (strArr.length > 0) {
            String str = strArr[0];
            int i = this.f1461a.isPackageInstalled(str) ? 2 : 1;
            if (str.equals("com.etsy.android.soe")) {
                this.f1461a.mSOEInstalled = i;
            } else if (str.equals("com.etsy.android")) {
                this.f1461a.mBOEInstalled = i;
            }
        }
        return null;
    }
}

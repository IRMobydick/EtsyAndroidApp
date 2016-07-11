package com.etsy.android.lib.exceptions;

import android.content.Context;
import com.crittercism.app.Crittercism;
import com.crittercism.app.CrittercismConfig;
import com.etsy.android.lib.config.InstallInfo;
import com.etsy.android.lib.util.CrashUtil.CrashProvider;

/* renamed from: com.etsy.android.lib.exceptions.b */
public class CrittercismDelegate extends CrashProviderDelegate {

    /* renamed from: com.etsy.android.lib.exceptions.b.1 */
    class CrittercismDelegate implements Runnable {
        final /* synthetic */ Context f1713a;
        final /* synthetic */ CrashProvider f1714b;
        final /* synthetic */ CrittercismDelegate f1715c;

        CrittercismDelegate(CrittercismDelegate crittercismDelegate, Context context, CrashProvider crashProvider) {
            this.f1715c = crittercismDelegate;
            this.f1713a = context;
            this.f1714b = crashProvider;
        }

        public void run() {
            m1767a();
        }

        public void m1767a() {
            CrittercismConfig crittercismConfig = new CrittercismConfig();
            crittercismConfig.setServiceMonitoringEnabled(true);
            Crittercism.initialize(this.f1713a, this.f1714b.getApiKey(), crittercismConfig);
            Crittercism.setUsername(InstallInfo.m919a().m925b());
        }
    }

    public Runnable m1768a(Context context, CrashProvider crashProvider) {
        return new CrittercismDelegate(this, context, crashProvider);
    }

    public void m1769a(String str) {
        Crittercism.endTransaction(str);
    }

    public void m1772b(String str) {
        Crittercism.failTransaction(str);
    }

    public void m1771a(Throwable th) {
        Crittercism.logHandledException(th);
    }

    public void m1770a(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(".");
        stringBuilder.append(str2);
        m1773c(stringBuilder.toString());
    }

    public void m1773c(String str) {
        Crittercism.leaveBreadcrumb(str);
    }

    public void m1774d(String str) {
        Crittercism.beginTransaction(str);
    }
}

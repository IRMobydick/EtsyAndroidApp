package com.etsy.android.lib.logger;

import android.content.Context;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.XmlRes;
import com.etsy.android.lib.config.EtsyConfigOption;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.ao;
import com.etsy.android.lib.models.Receipt;
import com.etsy.android.lib.models.Transaction;
import com.google.android.gms.analytics.C1140j;
import com.google.android.gms.analytics.C1141l;
import com.google.android.gms.analytics.g;
import com.google.android.gms.analytics.h;
import com.google.android.gms.analytics.k;
import com.google.android.gms.analytics.n;
import com.google.android.gms.analytics.o;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.etsy.android.lib.logger.j */
public final class EtsyGoogleAnalyticsLogger {
    private static ConcurrentHashMap<Integer, EtsyGoogleAnalyticsLogger> f1808a;

    /* renamed from: com.etsy.android.lib.logger.j.1 */
    final class EtsyGoogleAnalyticsLogger implements Runnable {
        final /* synthetic */ EtsyGoogleAnalyticsLogger f1780a;
        final /* synthetic */ String f1781b;

        EtsyGoogleAnalyticsLogger(EtsyGoogleAnalyticsLogger etsyGoogleAnalyticsLogger, String str) {
            this.f1780a = etsyGoogleAnalyticsLogger;
            this.f1781b = str;
        }

        public void run() {
            o a = this.f1780a.m1953a();
            a.a(this.f1781b);
            a.a(EtsyGoogleAnalyticsLogger.m1938a(new k()).a());
        }
    }

    /* renamed from: com.etsy.android.lib.logger.j.2 */
    final class EtsyGoogleAnalyticsLogger implements Runnable {
        final /* synthetic */ EtsyGoogleAnalyticsLogger f1782a;
        final /* synthetic */ AnalyticsLog f1783b;
        final /* synthetic */ ad f1784c;

        EtsyGoogleAnalyticsLogger(EtsyGoogleAnalyticsLogger etsyGoogleAnalyticsLogger, AnalyticsLog analyticsLog, ad adVar) {
            this.f1782a = etsyGoogleAnalyticsLogger;
            this.f1783b = analyticsLog;
            this.f1784c = adVar;
        }

        public void run() {
            o a = this.f1782a.m1953a();
            a.a(this.f1783b.m1782a());
            k a2 = EtsyGoogleAnalyticsLogger.m1938a(new k());
            a2.a(3, this.f1784c.m1843a());
            a2.b(!this.f1783b.m1785a(AnalyticsLogAttribute.IS_SURFACED));
            a.a(a2.a());
        }
    }

    /* renamed from: com.etsy.android.lib.logger.j.3 */
    final class EtsyGoogleAnalyticsLogger implements Runnable {
        final /* synthetic */ EtsyGoogleAnalyticsLogger f1785a;
        final /* synthetic */ String f1786b;
        final /* synthetic */ String f1787c;
        final /* synthetic */ String f1788d;
        final /* synthetic */ String f1789e;
        final /* synthetic */ long f1790f;

        EtsyGoogleAnalyticsLogger(EtsyGoogleAnalyticsLogger etsyGoogleAnalyticsLogger, String str, String str2, String str3, String str4, long j) {
            this.f1785a = etsyGoogleAnalyticsLogger;
            this.f1786b = str;
            this.f1787c = str2;
            this.f1788d = str3;
            this.f1789e = str4;
            this.f1790f = j;
        }

        public void run() {
            o a = this.f1785a.m1953a();
            a.a(this.f1786b);
            a.a(EtsyGoogleAnalyticsLogger.m1935a(new g()).a(this.f1787c).b(this.f1788d).c(this.f1789e).a(this.f1790f).a());
        }
    }

    /* renamed from: com.etsy.android.lib.logger.j.4 */
    final class EtsyGoogleAnalyticsLogger implements Runnable {
        final /* synthetic */ EtsyGoogleAnalyticsLogger f1791a;
        final /* synthetic */ AdHocAnalyticsLog f1792b;
        final /* synthetic */ AnalyticsTracker f1793c;

        EtsyGoogleAnalyticsLogger(EtsyGoogleAnalyticsLogger etsyGoogleAnalyticsLogger, AdHocAnalyticsLog adHocAnalyticsLog, AnalyticsTracker analyticsTracker) {
            this.f1791a = etsyGoogleAnalyticsLogger;
            this.f1792b = adHocAnalyticsLog;
            this.f1793c = analyticsTracker;
        }

        public void run() {
            o a = this.f1791a.m1953a();
            g a2 = EtsyGoogleAnalyticsLogger.m1935a(new g()).b(this.f1792b.m1782a()).a(this.f1792b.m1789c().name());
            if (this.f1793c != null) {
                a.a(this.f1793c.m1846b());
                a2.a(3, this.f1793c.m1843a());
            }
            a.a(a2.a());
        }
    }

    /* renamed from: com.etsy.android.lib.logger.j.5 */
    final class EtsyGoogleAnalyticsLogger implements Runnable {
        final /* synthetic */ EtsyGoogleAnalyticsLogger f1794a;
        final /* synthetic */ AnalyticsTracker f1795b;
        final /* synthetic */ ConfigAnalyticsLog f1796c;
        final /* synthetic */ EtsyConfigOption f1797d;

        EtsyGoogleAnalyticsLogger(EtsyGoogleAnalyticsLogger etsyGoogleAnalyticsLogger, AnalyticsTracker analyticsTracker, ConfigAnalyticsLog configAnalyticsLog, EtsyConfigOption etsyConfigOption) {
            this.f1794a = etsyGoogleAnalyticsLogger;
            this.f1795b = analyticsTracker;
            this.f1796c = configAnalyticsLog;
            this.f1797d = etsyConfigOption;
        }

        public void run() {
            o a = this.f1794a.m1953a();
            a.a(this.f1795b.m1846b());
            a.a(((g) EtsyGoogleAnalyticsLogger.m1935a(new g()).a(3, this.f1795b.m1843a())).b(this.f1796c.m1782a()).a(this.f1796c.m1789c().name()).c(this.f1797d.m912j()).a());
        }
    }

    /* renamed from: com.etsy.android.lib.logger.j.6 */
    final class EtsyGoogleAnalyticsLogger implements Runnable {
        final /* synthetic */ EtsyGoogleAnalyticsLogger f1798a;
        final /* synthetic */ ad f1799b;
        final /* synthetic */ ViewClickAnalyticsLog f1800c;

        EtsyGoogleAnalyticsLogger(EtsyGoogleAnalyticsLogger etsyGoogleAnalyticsLogger, ad adVar, ViewClickAnalyticsLog viewClickAnalyticsLog) {
            this.f1798a = etsyGoogleAnalyticsLogger;
            this.f1799b = adVar;
            this.f1800c = viewClickAnalyticsLog;
        }

        public void run() {
            o a = this.f1798a.m1953a();
            a.a(this.f1799b.m1846b());
            a.a(((g) EtsyGoogleAnalyticsLogger.m1935a(new g()).a(3, this.f1799b.m1843a())).b(this.f1800c.m1787b(AnalyticsLogAttribute.VIEW_ACTION)).a(this.f1800c.m1789c().name()).c(this.f1800c.m1782a()).a());
        }
    }

    /* renamed from: com.etsy.android.lib.logger.j.7 */
    final class EtsyGoogleAnalyticsLogger implements Runnable {
        final /* synthetic */ EtsyGoogleAnalyticsLogger f1801a;
        final /* synthetic */ Context f1802b;
        final /* synthetic */ Throwable f1803c;
        final /* synthetic */ boolean f1804d;

        EtsyGoogleAnalyticsLogger(EtsyGoogleAnalyticsLogger etsyGoogleAnalyticsLogger, Context context, Throwable th, boolean z) {
            this.f1801a = etsyGoogleAnalyticsLogger;
            this.f1802b = context;
            this.f1803c = th;
            this.f1804d = z;
        }

        public void run() {
            this.f1801a.m1953a().a(EtsyGoogleAnalyticsLogger.m1936a(new h()).a(new n(this.f1802b, null).a(Thread.currentThread().getName(), this.f1803c)).a(this.f1804d).a());
        }
    }

    /* renamed from: com.etsy.android.lib.logger.j.8 */
    final class EtsyGoogleAnalyticsLogger implements Runnable {
        final /* synthetic */ EtsyGoogleAnalyticsLogger f1805a;
        final /* synthetic */ Receipt f1806b;
        final /* synthetic */ String f1807c;

        EtsyGoogleAnalyticsLogger(EtsyGoogleAnalyticsLogger etsyGoogleAnalyticsLogger, Receipt receipt, String str) {
            this.f1805a = etsyGoogleAnalyticsLogger;
            this.f1806b = receipt;
            this.f1807c = str;
        }

        public void run() {
            o a = this.f1805a.m1953a();
            if (!this.f1806b.getBuyer().getProfile().isTestAccount()) {
                a.a(EtsyGoogleAnalyticsLogger.m1939a(new C1141l()).m6246a(this.f1806b.getReceiptId().toString()).m6249b(this.f1807c).m6245a(this.f1806b.getTotalPrice().getAmount().doubleValue()).m6248b(this.f1806b.getTotalTaxCost().getAmount().doubleValue()).m6250c(this.f1806b.getTotalShippingCost().getAmount().doubleValue()).m6251c(this.f1806b.getTotalPrice().getCurrency().getCurrencyCode()).m6247a());
                if (this.f1806b.getTransactions().size() > 0) {
                    for (Transaction transaction : this.f1806b.getTransactions()) {
                        a.a(EtsyGoogleAnalyticsLogger.m1937a(new C1140j()).m6240a(this.f1806b.getReceiptId().toString()).m6242b(transaction.getTitle()).m6243c(transaction.getListingId().toString()).m6238a(transaction.getPrice()).m6239a((long) transaction.getQuantity()).m6244d(transaction.getCurrencyCode()).m6241a());
                    }
                }
            }
        }
    }

    static {
        f1808a = new ConcurrentHashMap(2);
    }

    public static void m1948a(@XmlRes int i, String str) {
        EtsyGoogleAnalyticsLogger c = EtsyGoogleAnalyticsLogger.m1952c(i);
        c.m1954a(new EtsyGoogleAnalyticsLogger(c, str));
    }

    @MainThread
    public static void m1943a(@XmlRes int i, @NonNull ad adVar, @NonNull AnalyticsLog analyticsLog) {
        EtsyGoogleAnalyticsLogger c = EtsyGoogleAnalyticsLogger.m1952c(i);
        c.m1954a(new EtsyGoogleAnalyticsLogger(c, analyticsLog, adVar));
    }

    public static void m1949a(@XmlRes int i, String str, String str2, String str3, String str4, long j) {
        EtsyGoogleAnalyticsLogger c = EtsyGoogleAnalyticsLogger.m1952c(i);
        c.m1954a(new EtsyGoogleAnalyticsLogger(c, str, str2, str3, str4, j));
    }

    @MainThread
    public static void m1945a(@XmlRes int i, @Nullable AnalyticsTracker analyticsTracker, @NonNull AdHocAnalyticsLog adHocAnalyticsLog) {
        EtsyGoogleAnalyticsLogger c = EtsyGoogleAnalyticsLogger.m1952c(i);
        c.m1954a(new EtsyGoogleAnalyticsLogger(c, adHocAnalyticsLog, analyticsTracker));
    }

    @MainThread
    public static void m1946a(@XmlRes int i, @NonNull AnalyticsTracker analyticsTracker, @NonNull ConfigAnalyticsLog configAnalyticsLog, @NonNull EtsyConfigOption etsyConfigOption) {
        EtsyGoogleAnalyticsLogger c = EtsyGoogleAnalyticsLogger.m1952c(i);
        c.m1954a(new EtsyGoogleAnalyticsLogger(c, analyticsTracker, configAnalyticsLog, etsyConfigOption));
    }

    @MainThread
    public static void m1944a(@XmlRes int i, @NonNull ad adVar, @NonNull ViewClickAnalyticsLog viewClickAnalyticsLog) {
        EtsyGoogleAnalyticsLogger c = EtsyGoogleAnalyticsLogger.m1952c(i);
        c.m1954a(new EtsyGoogleAnalyticsLogger(c, adVar, viewClickAnalyticsLog));
    }

    public static void m1942a(@XmlRes int i, @NonNull Context context, @NonNull Throwable th, boolean z) {
        EtsyGoogleAnalyticsLogger c = EtsyGoogleAnalyticsLogger.m1952c(i);
        c.m1954a(new EtsyGoogleAnalyticsLogger(c, context, th, z));
    }

    public static void m1947a(@XmlRes int i, @NonNull Receipt receipt, String str) {
        EtsyGoogleAnalyticsLogger c = EtsyGoogleAnalyticsLogger.m1952c(i);
        c.m1954a(new EtsyGoogleAnalyticsLogger(c, receipt, str));
    }

    private static String m1950b(int i) {
        return "&cd" + i;
    }

    protected static k m1938a(k kVar) {
        if (aj.m1104l()) {
            kVar.a(2, String.valueOf(aj.m1101a().m1125k()));
        }
        return kVar;
    }

    protected static h m1936a(h hVar) {
        if (aj.m1104l()) {
            hVar.a(2, String.valueOf(aj.m1101a().m1125k()));
        }
        return hVar;
    }

    protected static g m1935a(g gVar) {
        if (aj.m1104l()) {
            gVar.a(2, String.valueOf(aj.m1101a().m1125k()));
        }
        return gVar;
    }

    protected static C1141l m1939a(C1141l c1141l) {
        if (aj.m1104l()) {
            c1141l.a(2, String.valueOf(aj.m1101a().m1125k()));
        }
        return c1141l;
    }

    protected static C1140j m1937a(C1140j c1140j) {
        if (aj.m1104l()) {
            c1140j.a(2, String.valueOf(aj.m1101a().m1125k()));
        }
        return c1140j;
    }

    private static Executor m1951b(int i, int i2, long j) {
        return new ThreadPoolExecutor(0, i, j, TimeUnit.SECONDS, new ArrayBlockingQueue(i2), new ao(10));
    }

    @NonNull
    private static EtsyGoogleAnalyticsLogger m1952c(@XmlRes int i) {
        EtsyGoogleAnalyticsLogger etsyGoogleAnalyticsLogger = (EtsyGoogleAnalyticsLogger) f1808a.get(Integer.valueOf(i));
        if (etsyGoogleAnalyticsLogger != null) {
            return etsyGoogleAnalyticsLogger;
        }
        etsyGoogleAnalyticsLogger = new EtsyGoogleAnalyticsLogger(i);
        f1808a.put(Integer.valueOf(i), etsyGoogleAnalyticsLogger);
        return etsyGoogleAnalyticsLogger;
    }
}

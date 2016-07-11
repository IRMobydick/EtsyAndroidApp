package com.etsy.android.lib.logger;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.XmlRes;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.config.EtsyConfigMap;
import com.etsy.android.lib.config.EtsyConfigOption;
import com.etsy.android.lib.core.ao;
import com.etsy.android.lib.util.ba;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: com.etsy.android.lib.logger.b */
public class AnalyticsTracker {
    protected static int f1749c;
    private static final String f1750d;
    private static final ExecutorService f1751e;
    protected final String f1752a;
    protected final String f1753b;

    static {
        f1750d = EtsyDebug.m1891a(AnalyticsTracker.class);
        f1749c = -1;
        f1751e = Executors.newSingleThreadExecutor(new ao(10));
    }

    public static synchronized void m1837a(@XmlRes int i) {
        synchronized (AnalyticsTracker.class) {
            f1749c = i;
        }
    }

    public AnalyticsTracker(@NonNull String str) {
        this.f1752a = m1842e();
        this.f1753b = str;
    }

    @NonNull
    public String m1843a() {
        return this.f1752a;
    }

    @NonNull
    public String m1846b() {
        return this.f1753b;
    }

    @NonNull
    public EtsyConfigMap m1847c() {
        return EtsyConfig.m837a().m869d();
    }

    public void m1845a(@NonNull String str, @Nullable Map<AnalyticsLogAttribute, Object> map) {
        AdHocAnalyticsLog adHocAnalyticsLog = new AdHocAnalyticsLog(str, map, this);
        AnalyticsTracker.m1838a((AnalyticsLog) adHocAnalyticsLog);
        if (AnalyticsTracker.m1841d()) {
            EtsyGoogleAnalyticsLogger.m1945a(f1749c, this, adHocAnalyticsLog);
        }
    }

    public void m1844a(@NonNull EtsyConfigOption etsyConfigOption) {
        ConfigAnalyticsLog configAnalyticsLog = new ConfigAnalyticsLog(etsyConfigOption, this);
        AnalyticsTracker.m1838a((AnalyticsLog) configAnalyticsLog);
        if (AnalyticsTracker.m1841d()) {
            EtsyGoogleAnalyticsLogger.m1946a(f1749c, this, configAnalyticsLog, etsyConfigOption);
        }
    }

    private static long m1840c(@NonNull AnalyticsLog analyticsLog) {
        Throwable e;
        String b = analyticsLog.m1786b();
        try {
            SQLiteDatabase writableDatabase = AnalyticsLogDatabaseHelper.getInstance().getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(EtsyLoggerDatabaseHelper.LOG, b);
            EtsyDebug.m1914c(f1750d, "saveLog = %s", b);
            return writableDatabase.insert(EtsyLoggerDatabaseHelper.TABLE, null, contentValues);
        } catch (SQLException e2) {
            e = e2;
            EtsyDebug.m1917d(f1750d, "Error saving analytics log to database", e);
            return -1;
        } catch (NullPointerException e3) {
            e = e3;
            EtsyDebug.m1917d(f1750d, "Error saving analytics log to database", e);
            return -1;
        }
    }

    protected static void m1838a(@NonNull AnalyticsLog analyticsLog) {
        f1751e.submit(new AnalyticsTracker(analyticsLog));
    }

    @NonNull
    private String m1842e() {
        return ba.m3309a();
    }

    protected static boolean m1841d() {
        if (EtsyConfig.m837a().m869d() == null || !EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.bO) || f1749c == -1) {
            return false;
        }
        return true;
    }
}

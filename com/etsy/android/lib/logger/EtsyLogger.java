package com.etsy.android.lib.logger;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.XmlRes;
import com.etsy.android.lib.config.EtsyBuild;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.ao;
import com.etsy.android.lib.models.Receipt;
import com.etsy.android.lib.toolbar.AdminToolbar;
import com.etsy.android.lib.util.CrashUtil;
import com.etsy.android.lib.util.bl;
import com.google.android.gms.gcm.Task;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.lib.logger.m */
public class EtsyLogger {
    static final /* synthetic */ boolean f1820a;
    private static final String f1821b;
    private static EtsyLogger f1822c;
    private static int f1823d;
    private SQLiteOpenHelper f1824e;
    private LogSessionManager f1825f;
    private Context f1826g;
    private EtsyBuild f1827h;
    private Timer f1828i;
    private ExecutorService f1829j;
    private int f1830k;

    static {
        f1820a = !EtsyLogger.class.desiredAssertionStatus();
        f1821b = EtsyDebug.m1891a(EtsyLogger.class);
        f1823d = -1;
    }

    private EtsyLogger(Context context, LogSessionManager logSessionManager, SQLiteOpenHelper sQLiteOpenHelper, EtsyBuild etsyBuild) {
        this.f1830k = 0;
        this.f1826g = context;
        this.f1825f = logSessionManager;
        this.f1824e = sQLiteOpenHelper;
        this.f1827h = etsyBuild;
        this.f1829j = Executors.newSingleThreadExecutor(new ao(10));
    }

    public static EtsyLogger m1966a() {
        if (f1822c != null) {
            return f1822c;
        }
        throw new NullPointerException("Set Instance must be called before getInstance.  This should happen in the EtsyApplication onCreate method.");
    }

    public static void m1969a(Context context, LogSessionManager logSessionManager, SQLiteOpenHelper sQLiteOpenHelper, EtsyBuild etsyBuild, @XmlRes int i) {
        if (f1822c == null) {
            f1822c = new EtsyLogger(context, logSessionManager, sQLiteOpenHelper, etsyBuild);
        }
        if (EtsyLogger.m1976h()) {
            f1823d = i;
        }
    }

    public String m1995b() {
        return this.f1825f.m2071l();
    }

    public void m1993a(boolean z) {
        if (m1977i()) {
            this.f1828i = new Timer();
            long f = EtsyConfig.m837a().m869d().m888f(EtsyConfigKeys.bI) * 1000;
            Timer timer = this.f1828i;
            EtsyLogger etsyLogger = f1822c;
            etsyLogger.getClass();
            timer.scheduleAtFixedRate(new EtsyLogger(null), f, f);
            long currentTimeMillis = System.currentTimeMillis();
            this.f1825f.m2062c(currentTimeMillis);
            if (!z) {
                this.f1825f.m2060b(currentTimeMillis);
            }
        }
    }

    public void m1998c() {
        if (m1977i()) {
            this.f1825f.m2074o();
            if (this.f1828i != null) {
                this.f1828i.cancel();
            }
            if (m1980l()) {
                m1968a(1);
            }
        }
    }

    private void m1968a(int i) {
        Intent intent = new Intent(this.f1826g, UploadService.class);
        intent.putExtra(UploadService.UPLOAD_TYPE, i);
        this.f1826g.startService(intent);
        this.f1826g.startService(new Intent(this.f1826g, AnalyticsUploadService.class));
    }

    public void m1986a(String str, String str2) {
        m1970a(new EtsyLog(LogType.ERROR_LOG, null, this.f1830k, str, str, null, null, null), str, str2);
    }

    public void m1996b(String str, String str2) {
        m1970a(new EtsyLog(LogType.INFO_LOG, null, this.f1830k, str, str, null, null, null), str, str2);
    }

    public void m1985a(String str) {
        m1990a(str, StringUtils.EMPTY, null);
    }

    public void m1999c(String str, String str2) {
        m1990a(str, str2, null);
    }

    public void m1992a(String str, HashMap<String, Object> hashMap) {
        m1990a(str, StringUtils.EMPTY, (HashMap) hashMap);
    }

    public void m1990a(String str, String str2, HashMap<String, Object> hashMap) {
        this.f1830k++;
        m1983a(LogType.PAGEVIEW, str, null, (Map) hashMap, str2, null);
        if (EtsyLogger.m1976h() && f1823d != -1) {
            EtsyGoogleAnalyticsLogger.m1948a(f1823d, str);
        }
        CrashUtil.m3037a().m3043a(str);
    }

    public void m2001d(String str, String str2) {
        m1989a(str, str2, null, false, 0, null);
    }

    public void m1988a(String str, String str2, String str3, Object obj) {
        m1989a(str, str2, null, false, 0, new EtsyLogger$1(this, str3, obj));
    }

    public void m1997b(String str, String str2, HashMap<String, Object> hashMap) {
        m1989a(str, str2, null, false, 0, (HashMap) hashMap);
    }

    public void m1987a(String str, String str2, String str3) {
        m1989a(str, str2, str3, false, 0, null);
    }

    @Deprecated
    public void m1989a(String str, String str2, @Nullable String str3, boolean z, long j, HashMap<String, Object> hashMap) {
        m1983a(LogType.EVENT, str2, str, (Map) hashMap, String.valueOf(j), str3);
        if (EtsyLogger.m1976h() && f1823d != -1) {
            EtsyGoogleAnalyticsLogger.m1949a(f1823d, str2, str2, str, str3, j);
        }
        CrashUtil.m3037a().m3044a(str2, str);
    }

    public void m1991a(String str, @NonNull Throwable th, boolean z) {
        Map hashMap = new HashMap();
        hashMap.put("error_text", th.getClass().getSimpleName() + " " + th.getMessage());
        hashMap.put("stack_trace", Arrays.toString(th.getStackTrace()));
        hashMap.put("is_fatal", Boolean.valueOf(z));
        hashMap.put("error_type", Integer.valueOf(th.getClass().getSimpleName().hashCode()));
        if (z) {
            m1983a(LogType.ERROR, "app_crash", null, hashMap, null, null);
        } else {
            m1983a(LogType.ERROR, str, null, hashMap, null, null);
        }
        if (EtsyLogger.m1976h() && f1823d != -1) {
            EtsyGoogleAnalyticsLogger.m1942a(f1823d, this.f1826g, th, z);
        }
    }

    public void m1983a(LogType logType, String str, String str2, Map<String, Object> map, String str3, String str4) {
        if (f1820a || str != null) {
            if (str == null) {
                EtsyDebug.m1919e(f1821b, "Primary event eventPageInView should not be null");
            }
            EtsyLog etsyLog = new EtsyLog(logType, map, this.f1830k, str, str2, str3, str4, m1982a(this.f1826g));
            AdminToolbar.m3001d(etsyLog.m1965c());
            if (m1977i()) {
                if (this.f1825f.m2072m()) {
                    m1974f();
                } else {
                    this.f1825f.m2057a();
                }
                this.f1829j.submit(new EtsyLogger(this, etsyLog));
                return;
            }
            return;
        }
        throw new AssertionError();
    }

    public void m1984a(@NonNull Receipt receipt, String str) {
        if (EtsyLogger.m1976h() && f1823d != -1) {
            EtsyGoogleAnalyticsLogger.m1947a(f1823d, receipt, str);
        }
    }

    private void m1970a(EtsyLog etsyLog, String str, String str2) {
        if (m1972a(etsyLog)) {
            this.f1829j.submit(new EtsyLogger(this, etsyLog, str, str2));
        }
    }

    public String m1982a(Context context) {
        if (!m1981m()) {
            return StringUtils.EMPTY;
        }
        if (context == null) {
            return "undefined";
        }
        switch (context.getResources().getConfiguration().orientation) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return "portrait";
            case Task.NETWORK_STATE_ANY /*2*/:
                return "landscape";
            default:
                return "undefined";
        }
    }

    private void m1974f() {
        if (m1977i()) {
            this.f1830k = 0;
            this.f1825f.m2073n();
        }
    }

    private static boolean m1975g() {
        try {
            return EtsyConfig.m837a().m871e();
        } catch (IllegalStateException e) {
            return false;
        }
    }

    private static boolean m1976h() {
        return EtsyLogger.m1975g() ? EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.bO) : true;
    }

    private boolean m1977i() {
        return EtsyLogger.m1975g() ? EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.bP) : true;
    }

    private boolean m1972a(EtsyLog etsyLog) {
        return (etsyLog.m1963a() == LogType.ERROR_LOG && m1978j()) || (etsyLog.m1963a() == LogType.INFO_LOG && m1979k());
    }

    private boolean m1978j() {
        return EtsyLogger.m1975g() ? EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.bU) : true;
    }

    private boolean m1979k() {
        return EtsyLogger.m1975g() ? EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.bV) : true;
    }

    private boolean m1980l() {
        return EtsyLogger.m1975g() ? EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.bR) : true;
    }

    private boolean m1981m() {
        return EtsyLogger.m1975g() ? EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.bH) : false;
    }

    public long m1994b(String str) {
        long j = -1;
        if (m1977i()) {
            SQLiteDatabase writableDatabase = this.f1824e.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(EtsyLoggerDatabaseHelper.LOG, str);
            EtsyDebug.m1914c(f1821b, "saveLog = %s", str);
            try {
                j = writableDatabase.insert(EtsyLoggerDatabaseHelper.TABLE, null, contentValues);
            } catch (Throwable e) {
                EtsyDebug.m1917d(f1821b, "Error saving log to database", e);
            }
        }
        return j;
    }

    public void m2000d() {
        if (EtsyDebug.m1903a()) {
            int delete;
            SQLiteDatabase writableDatabase = this.f1824e.getWritableDatabase();
            if (writableDatabase != null) {
                delete = writableDatabase.delete(EtsyLoggerDatabaseHelper.TABLE, "1", null);
            } else {
                delete = 0;
            }
            if (this.f1826g != null) {
                bl.m3356a(this.f1826g, String.format("%d logs deleted", new Object[]{Integer.valueOf(delete)}));
            }
        }
    }

    public void m2002e() {
        m1968a(1);
    }
}

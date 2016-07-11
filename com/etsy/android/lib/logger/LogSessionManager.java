package com.etsy.android.lib.logger;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.config.InstallInfo;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/* renamed from: com.etsy.android.lib.logger.u */
public class LogSessionManager {
    private static LogSessionManager f1840j;
    SharedPreferences f1841a;
    private int f1842b;
    private long f1843c;
    private long f1844d;
    private int f1845e;
    private long f1846f;
    private long f1847g;
    private long f1848h;
    private long f1849i;

    public static synchronized LogSessionManager m2054a(Context context) {
        LogSessionManager logSessionManager;
        synchronized (LogSessionManager.class) {
            if (f1840j == null) {
                f1840j = new LogSessionManager(context);
            }
            logSessionManager = f1840j;
        }
        return logSessionManager;
    }

    private LogSessionManager(Context context) {
        this.f1842b = -1;
        this.f1843c = -1;
        this.f1844d = -1;
        this.f1845e = -1;
        this.f1846f = -1;
        this.f1847g = -1;
        this.f1848h = -1;
        this.f1849i = -1;
        this.f1841a = PreferenceManager.getDefaultSharedPreferences(context);
        m2056p();
    }

    private void m2056p() {
        this.f1844d = this.f1841a.getLong("lastSessionTime", -1);
        this.f1845e = this.f1841a.getInt("lastSessionId", -1);
        this.f1846f = this.f1841a.getLong("lastEventTime", -1);
        this.f1843c = this.f1841a.getLong("sessionTime", -1);
        this.f1842b = this.f1841a.getInt("sessionId", -1);
        this.f1848h = this.f1841a.getLong("app_start_time", -1);
        this.f1849i = this.f1841a.getLong("app_foreground_time", -1);
        this.f1847g = this.f1841a.getLong("app_inital_start_time", System.currentTimeMillis());
        if (!this.f1841a.contains("app_inital_start_time")) {
            this.f1841a.edit().putLong("app_inital_start_time", this.f1847g).apply();
        }
    }

    public void m2058a(long j) {
        this.f1846f = j;
    }

    public void m2057a() {
        m2058a(System.currentTimeMillis());
    }

    public long m2059b() {
        return this.f1846f;
    }

    public long m2061c() {
        return this.f1843c;
    }

    public int m2063d() {
        return this.f1842b;
    }

    public long m2064e() {
        return this.f1844d;
    }

    public int m2065f() {
        return this.f1845e;
    }

    public long m2066g() {
        return this.f1847g;
    }

    public void m2060b(long j) {
        this.f1848h = j;
    }

    public void m2067h() {
        m2060b(System.currentTimeMillis());
    }

    public long m2068i() {
        return this.f1848h;
    }

    public void m2062c(long j) {
        this.f1849i = j;
    }

    public void m2069j() {
        m2062c(System.currentTimeMillis());
    }

    public long m2070k() {
        return this.f1849i;
    }

    public String m2071l() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("111461200.");
        stringBuilder.append(InstallInfo.m919a().m925b()).append(".");
        stringBuilder.append(m2055d(InstallInfo.m919a().m931h())).append(".");
        stringBuilder.append(m2055d(m2064e())).append(".");
        stringBuilder.append(m2055d(m2061c())).append(".");
        stringBuilder.append(m2063d());
        return stringBuilder.toString();
    }

    public boolean m2072m() {
        return System.currentTimeMillis() - this.f1846f > 60000 * EtsyConfig.m837a().m869d().m888f(EtsyConfigKeys.bN);
    }

    public void m2073n() {
        this.f1844d = this.f1843c == -1 ? 0 : this.f1843c;
        this.f1845e = this.f1842b;
        long currentTimeMillis = System.currentTimeMillis();
        this.f1843c = currentTimeMillis;
        this.f1846f = currentTimeMillis;
        this.f1842b++;
    }

    public void m2074o() {
        Editor edit = this.f1841a.edit();
        edit.putInt("lastSessionId", this.f1845e);
        edit.putLong("lastSessionTime", this.f1844d);
        edit.putInt("sessionId", this.f1842b);
        edit.putLong("sessionTime", this.f1843c);
        edit.putLong("lastEventTime", this.f1846f);
        edit.putLong("app_start_time", this.f1848h);
        edit.putLong("app_foreground_time", this.f1849i);
        edit.apply();
    }

    private String m2055d(long j) {
        return new DecimalFormat("##########", new DecimalFormatSymbols(Locale.US)).format(((double) j) / 1000.0d);
    }
}

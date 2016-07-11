package com.etsy.android.lib.logger;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.InstallInfo;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.core.ad;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.util.ab;
import com.etsy.android.lib.util.ba;
import com.etsy.android.uikit.p018c.SystemUiHelper;
import com.google.android.gms.gcm.Task;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.apache.commons.lang3.StringUtils;

public abstract class AnalyticsLog {
    private static String f1717f;
    private static String f1718g;
    private String f1719a;
    private LogType f1720b;
    private boolean f1721c;
    private String f1722d;
    private HashMap<AnalyticsLogAttribute, Object> f1723e;

    public enum LogType {
        VIEW,
        RESUME_VIEW,
        CONFIG_FLAG,
        VIEW_CLICK,
        AD_HOC
    }

    static {
        f1717f = AppBuild.ANDROID_PLATFORM;
        f1718g = "native";
    }

    protected AnalyticsLog(LogType logType, String str, boolean z) {
        this(logType, str, z, null);
    }

    protected AnalyticsLog(LogType logType, String str, boolean z, @Nullable Map<AnalyticsLogAttribute, Object> map) {
        this.f1720b = logType;
        this.f1719a = str;
        this.f1722d = ba.m3309a();
        this.f1721c = z;
        this.f1723e = new HashMap();
        if (map != null) {
            this.f1723e.putAll(map);
        }
        m1777e();
        m1780h();
    }

    public String m1782a() {
        return this.f1719a;
    }

    @WorkerThread
    @NonNull
    public String m1786b() {
        String str = StringUtils.EMPTY;
        try {
            str = ad.m1081a().m1083b().writeValueAsString(this.f1723e);
        } catch (Throwable e) {
            EtsyDebug.m1917d(StringUtils.EMPTY, "Analytics log toJSON", e);
        }
        return str;
    }

    public LogType m1789c() {
        return this.f1720b;
    }

    private void m1777e() {
        this.f1723e.put(AnalyticsLogAttribute.EPOCH_MS, Long.valueOf(System.currentTimeMillis()));
        this.f1723e.put(AnalyticsLogAttribute.EVENT_SOURCE, f1717f);
        this.f1723e.put(AnalyticsLogAttribute.EVENT_LOGGER, f1718g);
        this.f1723e.put(AnalyticsLogAttribute.PRIMARY_EVENT, Boolean.valueOf(this.f1721c));
        this.f1723e.put(AnalyticsLogAttribute.BROWSER_ID, InstallInfo.m919a().m925b());
        this.f1723e.put(AnalyticsLogAttribute.EVENT_NAME, this.f1719a);
        this.f1723e.put(AnalyticsLogAttribute.GUID, this.f1722d);
    }

    protected void m1790d() {
        m1778f();
        m1779g();
        m1781i();
    }

    public boolean m1785a(AnalyticsLogAttribute analyticsLogAttribute) {
        if (!this.f1723e.containsKey(analyticsLogAttribute)) {
            return false;
        }
        Object obj = this.f1723e.get(analyticsLogAttribute);
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return false;
    }

    @Nullable
    public String m1787b(AnalyticsLogAttribute analyticsLogAttribute) {
        if (this.f1723e.get(analyticsLogAttribute) instanceof String) {
            return (String) this.f1723e.get(analyticsLogAttribute);
        }
        return null;
    }

    protected void m1784a(AnalyticsLogAttribute analyticsLogAttribute, Object obj) {
        this.f1723e.put(analyticsLogAttribute, obj);
    }

    protected void m1783a(@NonNull Context context) {
        this.f1723e.put(AnalyticsLogAttribute.ORIENTATION, m1776b(context));
    }

    protected void m1788b(@NonNull AnalyticsTracker analyticsTracker) {
        m1784a(AnalyticsLogAttribute.PAGE_GUID, analyticsTracker.m1843a());
        m1784a(AnalyticsLogAttribute.CONTEXT_NAME, analyticsTracker.m1846b());
    }

    private void m1778f() {
        this.f1723e.put(AnalyticsLogAttribute.ANDROID_ID, InstallInfo.m919a().m927d());
        this.f1723e.put(AnalyticsLogAttribute.DEVICE_SYSTEM_NAME, Integer.valueOf(VERSION.SDK_INT));
        this.f1723e.put(AnalyticsLogAttribute.DEVICE_SYSTEM_VERSION, VERSION.RELEASE);
        this.f1723e.put(AnalyticsLogAttribute.HARDWARE_PLATFORM_STRING, Build.MODEL);
        this.f1723e.put(AnalyticsLogAttribute.HARDWARE_PLATFORM, Build.HARDWARE);
        this.f1723e.put(AnalyticsLogAttribute.HARDWARE_MANUFACTURER, Build.MANUFACTURER);
        this.f1723e.put(AnalyticsLogAttribute.HARDWARE_MODEL, Build.ID);
        this.f1723e.put(AnalyticsLogAttribute.DEVICE_RESOLUTION, Float.valueOf(ab.m3171a()));
    }

    private void m1779g() {
        Locale locale = Locale.getDefault();
        this.f1723e.put(AnalyticsLogAttribute.ACCEPT_LANGUAGES, locale.getLanguage());
        this.f1723e.put(AnalyticsLogAttribute.TIME_ZONE, TimeZone.getDefault().getID());
        this.f1723e.put(AnalyticsLogAttribute.REGION, locale.getCountry());
    }

    private void m1780h() {
        LogSessionManager a = LogSessionManager.m2054a(EtsyApplication.get());
        if (a.m2068i() < 0) {
            a.m2067h();
        }
        if (a.m2070k() < 0) {
            a.m2069j();
        }
        this.f1723e.put(AnalyticsLogAttribute.APP_INITIAL_START_TIME, m1775a(a.m2066g()));
        this.f1723e.put(AnalyticsLogAttribute.APP_START_TIME, m1775a(a.m2068i()));
        this.f1723e.put(AnalyticsLogAttribute.APP_FOREGROUND_TIME, m1775a(a.m2070k()));
        this.f1723e.put(AnalyticsLogAttribute.APP_VERSION, InstallInfo.m919a().m929f());
        this.f1723e.put(AnalyticsLogAttribute.APP_NAME, InstallInfo.m919a().m930g());
    }

    private void m1781i() {
        this.f1723e.put(AnalyticsLogAttribute.USER_ID, aj.m1101a().m1126m().getId());
        this.f1723e.put(AnalyticsLogAttribute.IS_ADMIN, Integer.valueOf(EtsyConfig.m837a().m869d().m884b() ? 1 : 0));
    }

    private String m1776b(@NonNull Context context) {
        switch (SystemUiHelper.m5328a(context)) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                return "landscape";
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return "portrait";
            default:
                return "undefined";
        }
    }

    private String m1775a(long j) {
        return new DecimalFormat("##########.####", new DecimalFormatSymbols(Locale.US)).format(((double) j) / 1000.0d);
    }
}

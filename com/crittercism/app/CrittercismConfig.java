package com.crittercism.app;

import android.os.Build.VERSION;
import crittercism.android.dy;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;

public class CrittercismConfig {
    public static final String API_VERSION = "5.0.6";
    protected String f1058a;
    private String f1059b;
    private boolean f1060c;
    private boolean f1061d;
    private boolean f1062e;
    private boolean f1063f;
    private boolean f1064g;
    private String f1065h;
    private String f1066i;
    private List f1067j;
    private List f1068k;

    public CrittercismConfig() {
        this.f1059b = null;
        this.f1060c = false;
        this.f1061d = false;
        this.f1062e = true;
        this.f1063f = false;
        this.f1064g = m684b();
        this.f1058a = "com.crittercism/dumps";
        this.f1065h = "Developer Reply";
        this.f1066i = null;
        this.f1067j = new LinkedList();
        this.f1068k = new LinkedList();
    }

    @Deprecated
    public CrittercismConfig(JSONObject jSONObject) {
        this.f1059b = null;
        this.f1060c = false;
        this.f1061d = false;
        this.f1062e = true;
        this.f1063f = false;
        this.f1064g = m684b();
        this.f1058a = "com.crittercism/dumps";
        this.f1065h = "Developer Reply";
        this.f1066i = null;
        this.f1067j = new LinkedList();
        this.f1068k = new LinkedList();
        this.f1059b = m681a(jSONObject, "customVersionName", this.f1059b);
        this.f1061d = m683a(jSONObject, "includeVersionCode", this.f1061d);
        this.f1062e = m683a(jSONObject, "installNdk", this.f1062e);
        this.f1060c = m683a(jSONObject, "delaySendingAppLoad", this.f1060c);
        this.f1063f = m683a(jSONObject, "shouldCollectLogcat", this.f1063f);
        this.f1058a = m681a(jSONObject, "nativeDumpPath", this.f1058a);
        this.f1065h = m681a(jSONObject, "notificationTitle", this.f1065h);
        this.f1064g = m683a(jSONObject, "installApm", this.f1064g);
    }

    public CrittercismConfig(CrittercismConfig crittercismConfig) {
        this.f1059b = null;
        this.f1060c = false;
        this.f1061d = false;
        this.f1062e = true;
        this.f1063f = false;
        this.f1064g = m684b();
        this.f1058a = "com.crittercism/dumps";
        this.f1065h = "Developer Reply";
        this.f1066i = null;
        this.f1067j = new LinkedList();
        this.f1068k = new LinkedList();
        this.f1059b = crittercismConfig.f1059b;
        this.f1060c = crittercismConfig.f1060c;
        this.f1061d = crittercismConfig.f1061d;
        this.f1062e = crittercismConfig.f1062e;
        this.f1063f = crittercismConfig.f1063f;
        this.f1064g = crittercismConfig.f1064g;
        this.f1058a = crittercismConfig.f1058a;
        this.f1065h = crittercismConfig.f1065h;
        setURLBlacklistPatterns(crittercismConfig.f1067j);
        setPreserveQueryStringPatterns(crittercismConfig.f1068k);
        this.f1066i = crittercismConfig.f1066i;
    }

    public List getURLBlacklistPatterns() {
        return new LinkedList(this.f1067j);
    }

    public void setURLBlacklistPatterns(List list) {
        this.f1067j.clear();
        if (list != null) {
            this.f1067j.addAll(list);
        }
    }

    public void setPreserveQueryStringPatterns(List list) {
        this.f1068k.clear();
        if (list != null) {
            this.f1068k.addAll(list);
        }
    }

    public List getPreserveQueryStringPatterns() {
        return new LinkedList(this.f1068k);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CrittercismConfig)) {
            return false;
        }
        CrittercismConfig crittercismConfig = (CrittercismConfig) obj;
        if (this.f1060c == crittercismConfig.f1060c && this.f1063f == crittercismConfig.f1063f && isNdkCrashReportingEnabled() == crittercismConfig.isNdkCrashReportingEnabled() && isOptmzEnabled() == crittercismConfig.isOptmzEnabled() && isVersionCodeToBeIncludedInVersionString() == crittercismConfig.isVersionCodeToBeIncludedInVersionString() && m682a(this.f1059b, crittercismConfig.f1059b) && m682a(this.f1065h, crittercismConfig.f1065h) && m682a(this.f1058a, crittercismConfig.f1058a) && this.f1067j.equals(crittercismConfig.f1067j) && this.f1068k.equals(crittercismConfig.f1068k) && m682a(this.f1066i, crittercismConfig.f1066i)) {
            return true;
        }
        return false;
    }

    protected static boolean m682a(String str, String str2) {
        if (str == null) {
            return str2 == null;
        } else {
            return str.equals(str2);
        }
    }

    public int hashCode() {
        int i;
        int i2 = 1;
        int hashCode = this.f1068k.hashCode() + ((((((((((m680a(this.f1059b) + 0) * 31) + m680a(this.f1065h)) * 31) + m680a(this.f1058a)) * 31) + m680a(this.f1066i)) * 31) + this.f1067j.hashCode()) * 31);
        int i3 = ((this.f1060c ? 1 : 0) + 0) << 1;
        if (this.f1063f) {
            i = 1;
        } else {
            i = 0;
        }
        i3 = (i + i3) << 1;
        if (isNdkCrashReportingEnabled()) {
            i = 1;
        } else {
            i = 0;
        }
        i3 = (i + i3) << 1;
        if (isOptmzEnabled()) {
            i = 1;
        } else {
            i = 0;
        }
        i = (i + i3) << 1;
        if (!isVersionCodeToBeIncludedInVersionString()) {
            i2 = 0;
        }
        return Integer.valueOf(i + i2).hashCode() + (hashCode * 31);
    }

    private static int m680a(String str) {
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    private static String m681a(JSONObject jSONObject, String str, String str2) {
        if (jSONObject.has(str)) {
            try {
                str2 = jSONObject.getString(str);
            } catch (Exception e) {
            }
        }
        return str2;
    }

    private static boolean m683a(JSONObject jSONObject, String str, boolean z) {
        if (jSONObject.has(str)) {
            try {
                z = jSONObject.getBoolean(str);
            } catch (Exception e) {
            }
        }
        return z;
    }

    public final String getCustomVersionName() {
        return this.f1059b;
    }

    public final void setCustomVersionName(String str) {
        this.f1059b = str;
    }

    public final boolean delaySendingAppLoad() {
        return this.f1060c;
    }

    public final void setDelaySendingAppLoad(boolean z) {
        this.f1060c = z;
    }

    public final boolean isVersionCodeToBeIncludedInVersionString() {
        return this.f1061d;
    }

    public final void setVersionCodeToBeIncludedInVersionString(boolean z) {
        this.f1061d = z;
    }

    public final boolean isNdkCrashReportingEnabled() {
        return this.f1062e;
    }

    public final void setNdkCrashReportingEnabled(boolean z) {
        this.f1062e = z;
    }

    public final boolean isLogcatReportingEnabled() {
        return this.f1063f;
    }

    public final void setLogcatReportingEnabled(boolean z) {
        this.f1063f = z;
    }

    private static final boolean m684b() {
        return VERSION.SDK_INT >= 10 && VERSION.SDK_INT <= 21;
    }

    public final boolean isServiceMonitoringEnabled() {
        return isOptmzEnabled();
    }

    @Deprecated
    public final boolean isOptmzEnabled() {
        return this.f1064g;
    }

    public final void setServiceMonitoringEnabled(boolean z) {
        setOptmzEnabled(z);
    }

    @Deprecated
    public final void setOptmzEnabled(boolean z) {
        if (m684b() || !z) {
            this.f1064g = z;
        } else {
            dy.a("OPTMZ is currently only allowed for api levels 10 to 21.  APM will not be installed");
        }
    }

    public List m685a() {
        return getURLBlacklistPatterns();
    }

    public final void setRateMyAppTestTarget(String str) {
        this.f1066i = str;
    }

    public final String getRateMyAppTestTarget() {
        return this.f1066i;
    }
}

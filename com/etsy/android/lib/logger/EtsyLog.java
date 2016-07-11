package com.etsy.android.lib.logger;

import com.google.android.gms.gcm.Task;
import java.util.Arrays;
import java.util.Map;

/* renamed from: com.etsy.android.lib.logger.l */
public class EtsyLog {
    private LogType f1812a;
    private Map<String, Object> f1813b;
    private int f1814c;
    private String f1815d;
    private String f1816e;
    private String f1817f;
    private String f1818g;
    private String f1819h;

    public EtsyLog(LogType logType, Map<String, Object> map, int i, String str, String str2, String str3, String str4, String str5) {
        this.f1812a = logType;
        this.f1813b = map;
        this.f1814c = i;
        this.f1815d = str;
        this.f1816e = str2;
        this.f1817f = str3;
        this.f1818g = str4;
        this.f1819h = str5;
    }

    public LogType m1963a() {
        return this.f1812a;
    }

    public boolean m1964b() {
        switch (C04711.f1726a[this.f1812a.ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return true;
            case Task.NETWORK_STATE_ANY /*2*/:
                boolean z = this.f1813b != null && ((Boolean) this.f1813b.get("is_fatal")).booleanValue();
                return z;
            default:
                return false;
        }
    }

    public String m1965c() {
        if (LogType.PAGEVIEW == this.f1812a) {
            return String.format("%s %s", new Object[]{this.f1812a, this.f1815d});
        } else if (LogType.ERROR == this.f1812a) {
            return toString();
        } else {
            return String.format("%s %s %s", new Object[]{this.f1812a, this.f1815d, this.f1816e});
        }
    }

    public String toString() {
        String str = "empty";
        if (this.f1813b != null) {
            str = Arrays.toString(this.f1813b.values().toArray());
        }
        return String.format("Type: %s PageInView: %s Event: %s::DICT::%s", new Object[]{this.f1812a, this.f1815d, this.f1816e, str});
    }
}

package com.etsy.android.lib.core;

import android.support.annotation.NonNull;
import com.etsy.android.lib.logger.EtsyLogger;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/* compiled from: Version */
public class aq implements Comparable<aq> {
    int f1447a;
    int f1448b;
    int f1449c;
    int f1450d;

    public /* synthetic */ int compareTo(Object obj) {
        return m1145a((aq) obj);
    }

    public int m1145a(@NonNull aq aqVar) {
        if (this.f1447a != aqVar.f1447a) {
            return m1143a(this.f1447a, aqVar.f1447a);
        }
        if (this.f1448b != aqVar.f1448b) {
            return m1143a(this.f1448b, aqVar.f1448b);
        }
        if (this.f1449c != aqVar.f1449c) {
            return m1143a(this.f1449c, aqVar.f1449c);
        }
        if (this.f1450d != aqVar.f1450d) {
            return m1143a(this.f1450d, aqVar.f1450d);
        }
        return 0;
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof aq) && m1145a((aq) obj) == 0);
    }

    public int hashCode() {
        return new HashCodeBuilder().append(this.f1447a).append(this.f1448b).append(this.f1449c).append(this.f1450d).build().intValue();
    }

    public static aq m1144a(String str) {
        aq aqVar = new aq();
        String[] split = str.split("\\.");
        if (split.length > 4 || split.length < 3) {
            EtsyLogger.m1966a().m1991a(aq.class.getSimpleName(), new IllegalArgumentException("Version has incorrect format."), false);
        } else {
            try {
                aqVar.f1447a = Integer.parseInt(split[0]);
                aqVar.f1448b = Integer.parseInt(split[1]);
                aqVar.f1449c = Integer.parseInt(split[2]);
                if (split.length == 4) {
                    aqVar.f1450d = Integer.parseInt(split[3]);
                }
            } catch (Throwable e) {
                EtsyLogger.m1966a().m1991a(aq.class.getSimpleName(), e, false);
            }
        }
        return aqVar;
    }

    public String toString() {
        return StringUtils.EMPTY + this.f1447a + "." + this.f1448b + "." + this.f1449c + "." + this.f1450d;
    }

    private static int m1143a(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }
}

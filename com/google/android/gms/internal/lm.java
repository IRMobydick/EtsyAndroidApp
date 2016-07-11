package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.C1101o;
import java.math.BigInteger;
import java.util.Locale;

@jw
public final class lm {
    private static final Object f5419a;
    private static String f5420b;

    static {
        f5419a = new Object();
    }

    public static String m7051a() {
        String str;
        synchronized (f5419a) {
            str = f5420b;
        }
        return str;
    }

    public static String m7052a(Context context, String str, String str2) {
        String str3;
        synchronized (f5419a) {
            if (f5420b == null && !TextUtils.isEmpty(str)) {
                m7053b(context, str, str2);
            }
            str3 = f5420b;
        }
        return str3;
    }

    private static void m7053b(Context context, String str, String str2) {
        try {
            ClassLoader classLoader = context.createPackageContext(str2, 3).getClassLoader();
            Class cls = Class.forName("com.google.ads.mediation.MediationAdapter", false, classLoader);
            BigInteger bigInteger = new BigInteger(new byte[1]);
            String[] split = str.split(",");
            BigInteger bigInteger2 = bigInteger;
            for (int i = 0; i < split.length; i++) {
                if (C1101o.m6041e().m7116a(classLoader, cls, split[i])) {
                    bigInteger2 = bigInteger2.setBit(i);
                }
            }
            f5420b = String.format(Locale.US, "%X", new Object[]{bigInteger2});
        } catch (Throwable th) {
            f5420b = NotificationCompatApi21.CATEGORY_ERROR;
        }
    }
}

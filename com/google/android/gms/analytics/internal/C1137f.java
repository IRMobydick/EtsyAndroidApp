package com.google.android.gms.analytics.internal;

import android.util.Log;
import com.etsy.android.lib.convos.Draft;
import com.google.android.gms.analytics.C1142m;

@Deprecated
/* renamed from: com.google.android.gms.analytics.internal.f */
public class C1137f {
    private static volatile C1142m f4685a;

    static {
        C1137f.m6222a(new al());
    }

    public static C1142m m6221a() {
        return f4685a;
    }

    public static void m6222a(C1142m c1142m) {
        f4685a = c1142m;
    }

    public static void m6223a(String str) {
        g b = g.b();
        if (b != null) {
            b.d(str);
        } else if (C1137f.m6225a(1)) {
            Log.i((String) ar.c.a(), str);
        }
        C1142m c1142m = f4685a;
        if (c1142m != null) {
            c1142m.m6255b(str);
        }
    }

    public static void m6224a(String str, Object obj) {
        g b = g.b();
        if (b != null) {
            b.e(str, obj);
        } else if (C1137f.m6225a(3)) {
            String stringBuilder;
            if (obj != null) {
                String valueOf = String.valueOf(obj);
                stringBuilder = new StringBuilder((String.valueOf(str).length() + 1) + String.valueOf(valueOf).length()).append(str).append(Draft.IMAGE_DELIMITER).append(valueOf).toString();
            } else {
                stringBuilder = str;
            }
            Log.e((String) ar.c.a(), stringBuilder);
        }
        C1142m c1142m = f4685a;
        if (c1142m != null) {
            c1142m.m6257d(str);
        }
    }

    public static boolean m6225a(int i) {
        return C1137f.m6221a() != null && C1137f.m6221a().m6252a() <= i;
    }

    public static void m6226b(String str) {
        g b = g.b();
        if (b != null) {
            b.b(str);
        } else if (C1137f.m6225a(0)) {
            Log.v((String) ar.c.a(), str);
        }
        C1142m c1142m = f4685a;
        if (c1142m != null) {
            c1142m.m6254a(str);
        }
    }

    public static void m6227c(String str) {
        g b = g.b();
        if (b != null) {
            b.e(str);
        } else if (C1137f.m6225a(2)) {
            Log.w((String) ar.c.a(), str);
        }
        C1142m c1142m = f4685a;
        if (c1142m != null) {
            c1142m.m6256c(str);
        }
    }
}

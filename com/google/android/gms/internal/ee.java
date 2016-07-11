package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import java.util.Map;

@jw
public abstract class ee {
    @jw
    public static final ee f4867a;
    @jw
    public static final ee f4868b;
    @jw
    public static final ee f4869c;

    static {
        f4867a = new 1();
        f4868b = new 2();
        f4869c = new 3();
    }

    public abstract String m6465a(@Nullable String str, String str2);

    public final void m6466a(Map<String, String> map, String str, String str2) {
        map.put(str, m6465a((String) map.get(str), str2));
    }
}

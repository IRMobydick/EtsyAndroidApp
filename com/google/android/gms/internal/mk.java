package com.google.android.gms.internal;

import android.content.Context;
import java.util.Map;

@jw
public class mk {
    public static final ml<Void> f5441a;
    private static nm f5442b;
    private static final Object f5443c;

    static {
        f5443c = new Object();
        f5441a = new 1();
    }

    public mk(Context context) {
        m7177a(context);
    }

    private static nm m7177a(Context context) {
        nm nmVar;
        synchronized (f5443c) {
            if (f5442b == null) {
                f5442b = g.a(context.getApplicationContext());
            }
            nmVar = f5442b;
        }
        return nmVar;
    }

    public mz<String> m7178a(int i, String str, Map<String, String> map, byte[] bArr) {
        mn mnVar = new mn(this, null);
        f5442b.a(new 3(this, i, str, mnVar, new 2(this, str, mnVar), bArr, map));
        return mnVar;
    }

    public <T> mz<T> m7179a(String str, ml<T> mlVar) {
        mn mnVar = new mn(this, null);
        f5442b.a(new mm(str, mlVar, mnVar));
        return mnVar;
    }

    public mz<String> m7180a(String str, Map<String, String> map) {
        return m7178a(0, str, map, null);
    }
}

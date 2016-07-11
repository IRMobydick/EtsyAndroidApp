package com.google.android.gms.internal;

import android.content.SharedPreferences;
import com.google.android.gms.ads.internal.C1101o;

@jw
public abstract class dv<T> {
    private final int f4792a;
    private final String f4793b;
    private final T f4794c;

    private dv(int i, String str, T t) {
        this.f4792a = i;
        this.f4793b = str;
        this.f4794c = t;
        C1101o.m6049m().m6435a(this);
    }

    public static dv<String> m6424a(int i, String str) {
        dv<String> a = m6428a(i, str, null);
        C1101o.m6049m().m6437b(a);
        return a;
    }

    public static dv<Integer> m6425a(int i, String str, int i2) {
        return new 2(i, str, Integer.valueOf(i2));
    }

    public static dv<Long> m6426a(int i, String str, long j) {
        return new 3(i, str, Long.valueOf(j));
    }

    public static dv<Boolean> m6427a(int i, String str, Boolean bool) {
        return new 1(i, str, bool);
    }

    public static dv<String> m6428a(int i, String str, String str2) {
        return new 4(i, str, str2);
    }

    public static dv<String> m6429b(int i, String str) {
        dv<String> a = m6428a(i, str, null);
        C1101o.m6049m().m6438c(a);
        return a;
    }

    protected abstract T m6430a(SharedPreferences sharedPreferences);

    public String m6431a() {
        return this.f4793b;
    }

    public T m6432b() {
        return this.f4794c;
    }

    public T m6433c() {
        return C1101o.m6050n().m6441a(this);
    }
}

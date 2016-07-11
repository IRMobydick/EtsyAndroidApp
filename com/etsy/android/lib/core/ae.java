package com.etsy.android.lib.core;

import com.fasterxml.jackson.databind.ObjectMapper;

/* compiled from: JsonMapper */
public class ae<T> {
    public T m1086a(String str, Class<T> cls) {
        return m1085a().readValue(str, (Class) cls);
    }

    private ObjectMapper m1085a() {
        return ad.m1081a().m1084c();
    }
}

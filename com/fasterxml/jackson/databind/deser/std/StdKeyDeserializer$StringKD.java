package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
final class StdKeyDeserializer$StringKD extends StdKeyDeserializer {
    private static final StdKeyDeserializer$StringKD f4334a;
    private static final StdKeyDeserializer$StringKD f4335b;
    private static final long serialVersionUID = 1;

    static {
        a = new StdKeyDeserializer$StringKD(String.class);
        b = new StdKeyDeserializer$StringKD(Object.class);
    }

    private StdKeyDeserializer$StringKD(Class<?> cls) {
        super(cls);
    }

    public static StdKeyDeserializer$StringKD forType(Class<?> cls) {
        if (cls == String.class) {
            return a;
        }
        if (cls == Object.class) {
            return b;
        }
        return new StdKeyDeserializer$StringKD(cls);
    }

    public String _parse(String str, DeserializationContext deserializationContext) {
        return str;
    }
}

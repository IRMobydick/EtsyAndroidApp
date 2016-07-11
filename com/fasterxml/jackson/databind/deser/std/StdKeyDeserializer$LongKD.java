package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
final class StdKeyDeserializer$LongKD extends StdKeyDeserializer {
    private static final long serialVersionUID = 1;

    StdKeyDeserializer$LongKD() {
        super(Long.class);
    }

    public Long _parse(String str, DeserializationContext deserializationContext) {
        return Long.valueOf(_parseLong(str));
    }
}

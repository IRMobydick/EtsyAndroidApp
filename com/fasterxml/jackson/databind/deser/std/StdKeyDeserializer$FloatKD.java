package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
final class StdKeyDeserializer$FloatKD extends StdKeyDeserializer {
    private static final long serialVersionUID = 1;

    StdKeyDeserializer$FloatKD() {
        super(Float.class);
    }

    public Float _parse(String str, DeserializationContext deserializationContext) {
        return Float.valueOf((float) _parseDouble(str));
    }
}

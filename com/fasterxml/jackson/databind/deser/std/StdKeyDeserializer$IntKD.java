package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
final class StdKeyDeserializer$IntKD extends StdKeyDeserializer {
    private static final long serialVersionUID = 1;

    StdKeyDeserializer$IntKD() {
        super(Integer.class);
    }

    public Integer _parse(String str, DeserializationContext deserializationContext) {
        return Integer.valueOf(_parseInt(str));
    }
}

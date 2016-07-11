package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
final class StdKeyDeserializer$DoubleKD extends StdKeyDeserializer {
    private static final long serialVersionUID = 1;

    StdKeyDeserializer$DoubleKD() {
        super(Double.class);
    }

    public Double _parse(String str, DeserializationContext deserializationContext) {
        return Double.valueOf(_parseDouble(str));
    }
}

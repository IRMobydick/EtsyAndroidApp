package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.util.UUID;

@JacksonStdImpl
final class StdKeyDeserializer$UuidKD extends StdKeyDeserializer {
    private static final long serialVersionUID = 1;

    protected StdKeyDeserializer$UuidKD() {
        super(UUID.class);
    }

    public Object _parse(String str, DeserializationContext deserializationContext) {
        return UUID.fromString(str);
    }
}

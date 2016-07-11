package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.util.Date;

@JacksonStdImpl
final class StdKeyDeserializer$DateKD extends StdKeyDeserializer {
    private static final long serialVersionUID = 1;

    protected StdKeyDeserializer$DateKD() {
        super(Date.class);
    }

    public Object _parse(String str, DeserializationContext deserializationContext) {
        return deserializationContext.parseDate(str);
    }
}

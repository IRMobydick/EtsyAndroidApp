package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
final class StdKeyDeserializer$BoolKD extends StdKeyDeserializer {
    private static final long serialVersionUID = 1;

    StdKeyDeserializer$BoolKD() {
        super(Boolean.class);
    }

    public Boolean _parse(String str, DeserializationContext deserializationContext) {
        if ("true".equals(str)) {
            return Boolean.TRUE;
        }
        if ("false".equals(str)) {
            return Boolean.FALSE;
        }
        throw deserializationContext.weirdKeyException(this._keyClass, str, "value not 'true' or 'false'");
    }
}

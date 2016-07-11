package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
final class StdKeyDeserializer$ShortKD extends StdKeyDeserializer {
    private static final long serialVersionUID = 1;

    StdKeyDeserializer$ShortKD() {
        super(Integer.class);
    }

    public Short _parse(String str, DeserializationContext deserializationContext) {
        int _parseInt = _parseInt(str);
        if (_parseInt >= -32768 && _parseInt <= 32767) {
            return Short.valueOf((short) _parseInt);
        }
        throw deserializationContext.weirdKeyException(this._keyClass, str, "overflow, value can not be represented as 16-bit value");
    }
}

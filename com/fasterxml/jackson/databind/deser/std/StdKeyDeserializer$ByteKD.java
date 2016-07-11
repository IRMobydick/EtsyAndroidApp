package com.fasterxml.jackson.databind.deser.std;

import com.appboy.support.ValidationUtils;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
final class StdKeyDeserializer$ByteKD extends StdKeyDeserializer {
    private static final long serialVersionUID = 1;

    StdKeyDeserializer$ByteKD() {
        super(Byte.class);
    }

    public Byte _parse(String str, DeserializationContext deserializationContext) {
        int _parseInt = _parseInt(str);
        if (_parseInt >= -128 && _parseInt <= ValidationUtils.APPBOY_STRING_MAX_LENGTH) {
            return Byte.valueOf((byte) _parseInt);
        }
        throw deserializationContext.weirdKeyException(this._keyClass, str, "overflow, value can not be represented as 8-bit value");
    }
}

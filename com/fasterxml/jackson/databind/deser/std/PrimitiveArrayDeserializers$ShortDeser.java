package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.util.ArrayBuilders.ShortBuilder;

@JacksonStdImpl
final class PrimitiveArrayDeserializers$ShortDeser extends PrimitiveArrayDeserializers<short[]> {
    private static final long serialVersionUID = 1;

    public PrimitiveArrayDeserializers$ShortDeser() {
        super(short[].class);
    }

    public short[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (!jsonParser.isExpectedStartArrayToken()) {
            return m5799a(jsonParser, deserializationContext);
        }
        ShortBuilder shortBuilder = deserializationContext.getArrayBuilders().getShortBuilder();
        Object obj = (short[]) shortBuilder.resetAndStart();
        int i = 0;
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
            int i2;
            short _parseShortPrimitive = _parseShortPrimitive(jsonParser, deserializationContext);
            if (i >= obj.length) {
                i2 = 0;
                obj = (short[]) shortBuilder.appendCompletedChunk(obj, i);
            } else {
                i2 = i;
            }
            i = i2 + 1;
            obj[i2] = _parseShortPrimitive;
        }
        return (short[]) shortBuilder.completeAndClearBuffer(obj, i);
    }

    private final short[] m5799a(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
            return null;
        }
        if (deserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            return new short[]{_parseShortPrimitive(jsonParser, deserializationContext)};
        }
        throw deserializationContext.mappingException(this._valueClass);
    }
}

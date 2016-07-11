package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.util.ArrayBuilders.BooleanBuilder;

@JacksonStdImpl
final class PrimitiveArrayDeserializers$BooleanDeser extends PrimitiveArrayDeserializers<boolean[]> {
    private static final long serialVersionUID = 1;

    public PrimitiveArrayDeserializers$BooleanDeser() {
        super(boolean[].class);
    }

    public boolean[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (!jsonParser.isExpectedStartArrayToken()) {
            return m5793a(jsonParser, deserializationContext);
        }
        BooleanBuilder booleanBuilder = deserializationContext.getArrayBuilders().getBooleanBuilder();
        Object obj = (boolean[]) booleanBuilder.resetAndStart();
        int i = 0;
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
            int i2;
            boolean _parseBooleanPrimitive = _parseBooleanPrimitive(jsonParser, deserializationContext);
            if (i >= obj.length) {
                i2 = 0;
                obj = (boolean[]) booleanBuilder.appendCompletedChunk(obj, i);
            } else {
                i2 = i;
            }
            i = i2 + 1;
            obj[i2] = _parseBooleanPrimitive;
        }
        return (boolean[]) booleanBuilder.completeAndClearBuffer(obj, i);
    }

    private final boolean[] m5793a(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
            return null;
        }
        if (deserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            return new boolean[]{_parseBooleanPrimitive(jsonParser, deserializationContext)};
        }
        throw deserializationContext.mappingException(this._valueClass);
    }
}

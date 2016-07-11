package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.util.ArrayBuilders.LongBuilder;

@JacksonStdImpl
final class PrimitiveArrayDeserializers$LongDeser extends PrimitiveArrayDeserializers<long[]> {
    public static final PrimitiveArrayDeserializers$LongDeser instance;
    private static final long serialVersionUID = 1;

    static {
        instance = new PrimitiveArrayDeserializers$LongDeser();
    }

    public PrimitiveArrayDeserializers$LongDeser() {
        super(long[].class);
    }

    public long[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (!jsonParser.isExpectedStartArrayToken()) {
            return m5798a(jsonParser, deserializationContext);
        }
        LongBuilder longBuilder = deserializationContext.getArrayBuilders().getLongBuilder();
        Object obj = (long[]) longBuilder.resetAndStart();
        int i = 0;
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
            int i2;
            long _parseLongPrimitive = _parseLongPrimitive(jsonParser, deserializationContext);
            if (i >= obj.length) {
                i2 = 0;
                obj = (long[]) longBuilder.appendCompletedChunk(obj, i);
            } else {
                i2 = i;
            }
            i = i2 + 1;
            obj[i2] = _parseLongPrimitive;
        }
        return (long[]) longBuilder.completeAndClearBuffer(obj, i);
    }

    private final long[] m5798a(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
            return null;
        }
        if (deserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            return new long[]{_parseLongPrimitive(jsonParser, deserializationContext)};
        }
        throw deserializationContext.mappingException(this._valueClass);
    }
}

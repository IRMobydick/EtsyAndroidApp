package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.util.ArrayBuilders.IntBuilder;

@JacksonStdImpl
final class PrimitiveArrayDeserializers$IntDeser extends PrimitiveArrayDeserializers<int[]> {
    public static final PrimitiveArrayDeserializers$IntDeser instance;
    private static final long serialVersionUID = 1;

    static {
        instance = new PrimitiveArrayDeserializers$IntDeser();
    }

    public PrimitiveArrayDeserializers$IntDeser() {
        super(int[].class);
    }

    public int[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (!jsonParser.isExpectedStartArrayToken()) {
            return m5797a(jsonParser, deserializationContext);
        }
        IntBuilder intBuilder = deserializationContext.getArrayBuilders().getIntBuilder();
        Object obj = (int[]) intBuilder.resetAndStart();
        int i = 0;
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
            int i2;
            int _parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
            if (i >= obj.length) {
                i2 = 0;
                obj = (int[]) intBuilder.appendCompletedChunk(obj, i);
            } else {
                i2 = i;
            }
            i = i2 + 1;
            obj[i2] = _parseIntPrimitive;
        }
        return (int[]) intBuilder.completeAndClearBuffer(obj, i);
    }

    private final int[] m5797a(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
            return null;
        }
        if (deserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            return new int[]{_parseIntPrimitive(jsonParser, deserializationContext)};
        }
        throw deserializationContext.mappingException(this._valueClass);
    }
}

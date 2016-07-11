package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.util.ArrayBuilders.FloatBuilder;

@JacksonStdImpl
final class PrimitiveArrayDeserializers$FloatDeser extends PrimitiveArrayDeserializers<float[]> {
    private static final long serialVersionUID = 1;

    public PrimitiveArrayDeserializers$FloatDeser() {
        super(float[].class);
    }

    public float[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (!jsonParser.isExpectedStartArrayToken()) {
            return m5796a(jsonParser, deserializationContext);
        }
        FloatBuilder floatBuilder = deserializationContext.getArrayBuilders().getFloatBuilder();
        Object obj = (float[]) floatBuilder.resetAndStart();
        int i = 0;
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
            int i2;
            float _parseFloatPrimitive = _parseFloatPrimitive(jsonParser, deserializationContext);
            if (i >= obj.length) {
                i2 = 0;
                obj = (float[]) floatBuilder.appendCompletedChunk(obj, i);
            } else {
                i2 = i;
            }
            i = i2 + 1;
            obj[i2] = _parseFloatPrimitive;
        }
        return (float[]) floatBuilder.completeAndClearBuffer(obj, i);
    }

    private final float[] m5796a(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
            return null;
        }
        if (deserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            return new float[]{_parseFloatPrimitive(jsonParser, deserializationContext)};
        }
        throw deserializationContext.mappingException(this._valueClass);
    }
}

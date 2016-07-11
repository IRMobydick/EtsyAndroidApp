package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.util.ArrayBuilders.DoubleBuilder;

@JacksonStdImpl
final class PrimitiveArrayDeserializers$DoubleDeser extends PrimitiveArrayDeserializers<double[]> {
    private static final long serialVersionUID = 1;

    public PrimitiveArrayDeserializers$DoubleDeser() {
        super(double[].class);
    }

    public double[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (!jsonParser.isExpectedStartArrayToken()) {
            return m5795a(jsonParser, deserializationContext);
        }
        DoubleBuilder doubleBuilder = deserializationContext.getArrayBuilders().getDoubleBuilder();
        Object obj = (double[]) doubleBuilder.resetAndStart();
        int i = 0;
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
            int i2;
            double _parseDoublePrimitive = _parseDoublePrimitive(jsonParser, deserializationContext);
            if (i >= obj.length) {
                i2 = 0;
                obj = (double[]) doubleBuilder.appendCompletedChunk(obj, i);
            } else {
                i2 = i;
            }
            i = i2 + 1;
            obj[i2] = _parseDoublePrimitive;
        }
        return (double[]) doubleBuilder.completeAndClearBuffer(obj, i);
    }

    private final double[] m5795a(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
            return null;
        }
        if (deserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            return new double[]{_parseDoublePrimitive(jsonParser, deserializationContext)};
        }
        throw deserializationContext.mappingException(this._valueClass);
    }
}

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.util.ArrayBuilders.ByteBuilder;

@JacksonStdImpl
final class PrimitiveArrayDeserializers$ByteDeser extends PrimitiveArrayDeserializers<byte[]> {
    private static final long serialVersionUID = 1;

    public PrimitiveArrayDeserializers$ByteDeser() {
        super(byte[].class);
    }

    public byte[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_STRING) {
            return jsonParser.getBinaryValue(deserializationContext.getBase64Variant());
        }
        if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
            Object embeddedObject = jsonParser.getEmbeddedObject();
            if (embeddedObject == null) {
                return null;
            }
            if (embeddedObject instanceof byte[]) {
                return (byte[]) embeddedObject;
            }
        }
        if (!jsonParser.isExpectedStartArrayToken()) {
            return m5794a(jsonParser, deserializationContext);
        }
        ByteBuilder byteBuilder = deserializationContext.getArrayBuilders().getByteBuilder();
        Object obj = (byte[]) byteBuilder.resetAndStart();
        int i = 0;
        while (true) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken == JsonToken.END_ARRAY) {
                return (byte[]) byteBuilder.completeAndClearBuffer(obj, i);
            }
            byte byteValue;
            int i2;
            if (nextToken == JsonToken.VALUE_NUMBER_INT || nextToken == JsonToken.VALUE_NUMBER_FLOAT) {
                byteValue = jsonParser.getByteValue();
            } else if (nextToken != JsonToken.VALUE_NULL) {
                break;
            } else {
                byteValue = (byte) 0;
            }
            if (i >= obj.length) {
                i2 = 0;
                obj = (byte[]) byteBuilder.appendCompletedChunk(obj, i);
            } else {
                i2 = i;
            }
            i = i2 + 1;
            obj[i2] = byteValue;
        }
        throw deserializationContext.mappingException(this._valueClass.getComponentType());
    }

    private final byte[] m5794a(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
            return null;
        }
        if (deserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            byte byteValue;
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                byteValue = jsonParser.getByteValue();
            } else if (currentToken != JsonToken.VALUE_NULL) {
                throw deserializationContext.mappingException(this._valueClass.getComponentType());
            } else {
                byteValue = (byte) 0;
            }
            return new byte[]{byteValue};
        }
        throw deserializationContext.mappingException(this._valueClass);
    }
}

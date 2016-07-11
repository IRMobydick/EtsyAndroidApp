package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonNumberFormatVisitor;
import java.lang.reflect.Type;

@JacksonStdImpl
public final class NumberSerializers$DoubleSerializer extends NonTypedScalarSerializerBase<Double> {
    static final NumberSerializers$DoubleSerializer instance;

    static {
        instance = new NumberSerializers$DoubleSerializer();
    }

    public NumberSerializers$DoubleSerializer() {
        super(Double.class);
    }

    public void serialize(Double d, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeNumber(d.doubleValue());
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        return createSchemaNode("number", true);
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) {
        JsonNumberFormatVisitor expectNumberFormat = jsonFormatVisitorWrapper.expectNumberFormat(javaType);
        if (expectNumberFormat != null) {
            expectNumberFormat.numberType(NumberType.DOUBLE);
        }
    }
}

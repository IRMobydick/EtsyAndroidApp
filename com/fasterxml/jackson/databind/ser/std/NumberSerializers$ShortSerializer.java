package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import java.lang.reflect.Type;

@JacksonStdImpl
public final class NumberSerializers$ShortSerializer extends StdScalarSerializer<Short> {
    static final NumberSerializers$ShortSerializer instance;

    static {
        instance = new NumberSerializers$ShortSerializer();
    }

    public NumberSerializers$ShortSerializer() {
        super(Short.class);
    }

    public void serialize(Short sh, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeNumber(sh.shortValue());
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        return createSchemaNode("number", true);
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) {
        JsonIntegerFormatVisitor expectIntegerFormat = jsonFormatVisitorWrapper.expectIntegerFormat(javaType);
        if (expectIntegerFormat != null) {
            expectIntegerFormat.numberType(NumberType.INT);
        }
    }
}

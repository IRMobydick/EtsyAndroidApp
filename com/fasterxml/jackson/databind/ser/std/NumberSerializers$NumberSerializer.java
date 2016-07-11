package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonNumberFormatVisitor;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;

@JacksonStdImpl
public final class NumberSerializers$NumberSerializer extends StdScalarSerializer<Number> {
    public static final NumberSerializers$NumberSerializer instance;

    static {
        instance = new NumberSerializers$NumberSerializer();
    }

    public NumberSerializers$NumberSerializer() {
        super(Number.class);
    }

    public void serialize(Number number, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (number instanceof BigDecimal) {
            if (!serializerProvider.isEnabled(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN) || (jsonGenerator instanceof TokenBuffer)) {
                jsonGenerator.writeNumber((BigDecimal) number);
            } else {
                jsonGenerator.writeNumber(((BigDecimal) number).toPlainString());
            }
        } else if (number instanceof BigInteger) {
            jsonGenerator.writeNumber((BigInteger) number);
        } else if (number instanceof Integer) {
            jsonGenerator.writeNumber(number.intValue());
        } else if (number instanceof Long) {
            jsonGenerator.writeNumber(number.longValue());
        } else if (number instanceof Double) {
            jsonGenerator.writeNumber(number.doubleValue());
        } else if (number instanceof Float) {
            jsonGenerator.writeNumber(number.floatValue());
        } else if ((number instanceof Byte) || (number instanceof Short)) {
            jsonGenerator.writeNumber(number.intValue());
        } else {
            jsonGenerator.writeNumber(number.toString());
        }
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        return createSchemaNode("number", true);
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) {
        JsonNumberFormatVisitor expectNumberFormat = jsonFormatVisitorWrapper.expectNumberFormat(javaType);
        if (expectNumberFormat != null) {
            expectNumberFormat.numberType(NumberType.BIG_DECIMAL);
        }
    }
}

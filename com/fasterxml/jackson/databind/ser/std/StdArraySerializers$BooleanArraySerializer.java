package com.fasterxml.jackson.databind.ser.std;

import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.lang.reflect.Type;

@JacksonStdImpl
public final class StdArraySerializers$BooleanArraySerializer extends ArraySerializerBase<boolean[]> {
    private static final JavaType VALUE_TYPE;

    static {
        VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Boolean.class);
    }

    public StdArraySerializers$BooleanArraySerializer() {
        super(boolean[].class, null);
    }

    public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return this;
    }

    public JavaType getContentType() {
        return VALUE_TYPE;
    }

    public JsonSerializer<?> getContentSerializer() {
        return null;
    }

    public boolean isEmpty(boolean[] zArr) {
        return zArr == null || zArr.length == 0;
    }

    public boolean hasSingleElement(boolean[] zArr) {
        return zArr.length == 1;
    }

    public void serializeContents(boolean[] zArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        for (boolean writeBoolean : zArr) {
            jsonGenerator.writeBoolean(writeBoolean);
        }
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        ObjectNode createSchemaNode = createSchemaNode("array", true);
        createSchemaNode.put(ResponseConstants.ITEMS, createSchemaNode("boolean"));
        return createSchemaNode;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) {
        if (jsonFormatVisitorWrapper != null) {
            JsonArrayFormatVisitor expectArrayFormat = jsonFormatVisitorWrapper.expectArrayFormat(javaType);
            if (expectArrayFormat != null) {
                expectArrayFormat.itemsFormat(JsonFormatTypes.BOOLEAN);
            }
        }
    }
}

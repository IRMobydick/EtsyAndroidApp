package com.fasterxml.jackson.databind.ser.std;

import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
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
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers.TypedPrimitiveArraySerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.lang.reflect.Type;

@JacksonStdImpl
public final class StdArraySerializers$ShortArraySerializer extends TypedPrimitiveArraySerializer<short[]> {
    private static final JavaType VALUE_TYPE;

    static {
        VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Short.TYPE);
    }

    public StdArraySerializers$ShortArraySerializer() {
        super(short[].class);
    }

    public StdArraySerializers$ShortArraySerializer(StdArraySerializers$ShortArraySerializer stdArraySerializers$ShortArraySerializer, BeanProperty beanProperty, TypeSerializer typeSerializer) {
        super(stdArraySerializers$ShortArraySerializer, beanProperty, typeSerializer);
    }

    public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return new StdArraySerializers$ShortArraySerializer(this, this._property, typeSerializer);
    }

    public JavaType getContentType() {
        return VALUE_TYPE;
    }

    public JsonSerializer<?> getContentSerializer() {
        return null;
    }

    public boolean isEmpty(short[] sArr) {
        return sArr == null || sArr.length == 0;
    }

    public boolean hasSingleElement(short[] sArr) {
        return sArr.length == 1;
    }

    public void serializeContents(short[] sArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        int i = 0;
        int length;
        if (this._valueTypeSerializer != null) {
            length = sArr.length;
            while (i < length) {
                this._valueTypeSerializer.writeTypePrefixForScalar(null, jsonGenerator, Short.TYPE);
                jsonGenerator.writeNumber(sArr[i]);
                this._valueTypeSerializer.writeTypeSuffixForScalar(null, jsonGenerator);
                i++;
            }
            return;
        }
        length = sArr.length;
        while (i < length) {
            jsonGenerator.writeNumber(sArr[i]);
            i++;
        }
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        ObjectNode createSchemaNode = createSchemaNode("array", true);
        createSchemaNode.put(ResponseConstants.ITEMS, createSchemaNode("integer"));
        return createSchemaNode;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) {
        if (jsonFormatVisitorWrapper != null) {
            JsonArrayFormatVisitor expectArrayFormat = jsonFormatVisitorWrapper.expectArrayFormat(javaType);
            if (expectArrayFormat != null) {
                expectArrayFormat.itemsFormat(JsonFormatTypes.INTEGER);
            }
        }
    }
}
